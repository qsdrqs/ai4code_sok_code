/*
 *  fernet.c  ──  Minimal Fernet implementation in C
 *
 *  Build example:
 *      cc -std=c11 -Wall -Wextra -O2 fernet.c -o fernet \
 *          -lcrypto          # OpenSSL 1.1+/3.x
 *
 *  Example use:
 *      char *token = encrypt("hello", "XKrx9M-iqvQQArdfV3a4q_9aTAqWAYhgpWuFDx4JmdM=");
 *      char *plain = decrypt(token, "XKrx9M-iqvQQArdfV3a4q_9aTAqWAYhgpWuFDx4JmdM=");
 */

#include <openssl/evp.h>
#include <openssl/hmac.h>
#include <openssl/rand.h>
#include <openssl/buffer.h>
#include <string.h>
#include <stdint.h>
#include <stdlib.h>
#include <stdio.h>
#include <time.h>

#define VERSION_BYTE     0x80
#define AES_BLOCK        16
#define IV_LEN           16
#define HMAC_LEN         32          /* SHA-256 */
#define KEY_BYTES        32          /* after base64-decode: 16 enc + 16 sign */

/* -------------------------------------------------------------------------- */
/*                     Helper: constant-time buffer compare                   */
/* -------------------------------------------------------------------------- */
static int
ct_memeq(const unsigned char *a, const unsigned char *b, size_t n)
{
    unsigned char res = 0;
    for (size_t i = 0; i < n; ++i)
        res |= a[i] ^ b[i];
    return res == 0;
}

/* -------------------------------------------------------------------------- */
/*                URL-safe base64  (no '=' padding on encode)                 */
/* -------------------------------------------------------------------------- */
static unsigned char *
urlsafe_b64_decode(const char *msg, size_t *out_len)
{
    size_t l = strlen(msg);
    size_t pads   = (4 - (l & 3)) & 3;   /* 0, 2 or 3 '=' to add back */
    char  *tmp    = malloc(l + pads + 1);
    if (!tmp) return NULL;

    /* translate -/_ back to +/ / and add '=' padding */
    for (size_t i = 0; i < l; ++i)
        tmp[i] = (msg[i] == '-') ? '+' :
                 (msg[i] == '_') ? '/' : msg[i];
    for (size_t i = 0; i < pads; ++i)
        tmp[l + i] = '=';
    tmp[l + pads] = '\0';

    BIO *bio, *b64;
    size_t buflen = (l + pads) * 3 / 4 + 1;
    unsigned char *buf = malloc(buflen);
    if (!buf) { free(tmp); return NULL; }

    b64 = BIO_new(BIO_f_base64());
    BIO_set_flags(b64, BIO_FLAGS_BASE64_NO_NL);
    bio = BIO_new_mem_buf(tmp, (int)(l + pads));
    bio = BIO_push(b64, bio);

    *out_len = BIO_read(bio, buf, (int)buflen);
    BIO_free_all(bio);
    free(tmp);
    return buf;
}

static char *
urlsafe_b64_encode(const unsigned char *src, size_t len)
{
    BIO *b64 = BIO_new(BIO_f_base64());
    BIO_set_flags(b64, BIO_FLAGS_BASE64_NO_NL);
    BIO *mem = BIO_new(BIO_s_mem());
    b64 = BIO_push(b64, mem);

    BIO_write(b64, src, (int)len);
    BIO_flush(b64);

    BUF_MEM *bptr;
    BIO_get_mem_ptr(b64, &bptr);

    /* copy so we can mangle in place */
    char *raw = malloc(bptr->length + 1);
    memcpy(raw, bptr->data, bptr->length);
    raw[bptr->length] = '\0';
    BIO_free_all(b64);

    /* translate to url-safe alphabet, strip '=' */
    for (size_t i = 0; raw[i]; ++i) {
        if (raw[i] == '+') raw[i] = '-';
        else if (raw[i] == '/') raw[i] = '_';
    }
    while (strlen(raw) && raw[strlen(raw)-1] == '=')   /* strip '=' */
        raw[strlen(raw)-1] = '\0';

    return raw;
}

/* -------------------------------------------------------------------------- */
/*               Split Fernet key into ENC-key || SIGN-key (two 16B)          */
/* -------------------------------------------------------------------------- */
static int
split_key(const char *key_b64,
          unsigned char enc_key[16], unsigned char sign_key[16])
{
    size_t key_len;
    unsigned char *k = urlsafe_b64_decode(key_b64, &key_len);
    if (!k || key_len != KEY_BYTES) { free(k); return -1; }

    memcpy(enc_key,  k,         16);
    memcpy(sign_key, k + 16,    16);
    free(k);
    return 0;
}

/* -------------------------------------------------------------------------- */
/*                     PKCS#7 padding helpers                                 */
/* -------------------------------------------------------------------------- */
static unsigned char *
pad_dup(const unsigned char *in, size_t len, size_t *out_len)
{
    size_t pad = AES_BLOCK - (len & (AES_BLOCK-1));
    *out_len = len + pad;
    unsigned char *out = malloc(*out_len);
    if (!out) return NULL;
    memcpy(out, in, len);
    memset(out + len, (int)pad, pad);
    return out;
}

static int
unpad(unsigned char *buf, size_t *len)
{
    if (*len == 0) return -1;
    unsigned char pad = buf[*len - 1];
    if (pad == 0 || pad > AES_BLOCK) return -1;
    for (size_t i = 0; i < pad; ++i)
        if (buf[*len - 1 - i] != pad) return -1;
    *len -= pad;
    buf[*len] = '\0';
    return 0;
}

/* -------------------------------------------------------------------------- */
/*                               encrypt                                      */
/* -------------------------------------------------------------------------- */
char *
encrypt(const char *plain, const char *key_b64)
{
    unsigned char enc_key[16], sign_key[16];
    if (split_key(key_b64, enc_key, sign_key) != 0) return NULL;

    /* 1. timestamp (big-endian uint64) */
    uint64_t ts = (uint64_t)time(NULL);
    unsigned char ts_buf[8];
    for (int i = 7; i >= 0; --i) {
        ts_buf[i] = ts & 0xff;
        ts >>= 8;
    }

    /* 2. IV */
    unsigned char iv[IV_LEN];
    if (RAND_bytes(iv, sizeof iv) != 1) return NULL;

    /* 3. AES-CBC encrypt with PKCS7 padding */
    size_t pt_len = strlen(plain);
    size_t padded_len;
    unsigned char *padded = pad_dup((const unsigned char *)plain,
                                    pt_len, &padded_len);
    if (!padded) return NULL;

    unsigned char *cipher = malloc(padded_len);
    int outl1=0,outl2=0;
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, enc_key, iv);
    EVP_EncryptUpdate(ctx, cipher, &outl1, padded, (int)padded_len);
    EVP_EncryptFinal_ex(ctx, cipher + outl1, &outl2);
    EVP_CIPHER_CTX_free(ctx);
    free(padded);

    size_t ciph_len = outl1 + outl2;

    /* 4. assemble payload: ver || ts || iv || ciphertext */
    size_t payload_len = 1 + 8 + IV_LEN + ciph_len;
    unsigned char *payload = malloc(payload_len);
    size_t off = 0;
    payload[off++] = VERSION_BYTE;
    memcpy(payload+off, ts_buf, 8);             off += 8;
    memcpy(payload+off, iv, IV_LEN);            off += IV_LEN;
    memcpy(payload+off, cipher, ciph_len);      off += ciph_len;
    free(cipher);

    /* 5. HMAC-SHA256 over payload */
    unsigned int mac_len = 0;
    unsigned char *mac = HMAC(EVP_sha256(), sign_key, 16,
                              payload, (int)payload_len,
                              NULL, &mac_len);

    /* 6. token = payload || mac  */
    size_t token_len = payload_len + mac_len;
    unsigned char *token_raw = malloc(token_len);
    memcpy(token_raw,           payload, payload_len);
    memcpy(token_raw+payload_len, mac,    mac_len);
    free(payload);

    /* 7. urlsafe-b64 encode */
    char *token_b64 = urlsafe_b64_encode(token_raw, token_len);
    free(token_raw);
    return token_b64;                    /* caller frees */
}

/* -------------------------------------------------------------------------- */
/*                               decrypt                                      */
/* -------------------------------------------------------------------------- */
char *
decrypt(const char *token_b64, const char *key_b64)
{
    unsigned char enc_key[16], sign_key[16];
    if (split_key(key_b64, enc_key, sign_key) != 0) return NULL;

    size_t tok_len;
    unsigned char *tok = urlsafe_b64_decode(token_b64, &tok_len);
    if (!tok || tok_len < 1+8+IV_LEN+HMAC_LEN) { free(tok); return NULL; }

    /* split pieces */
    size_t payload_len = tok_len - HMAC_LEN;
    unsigned char *payload = tok;
    unsigned char *mac     = tok + payload_len;

    /* verify HMAC */
    unsigned int mac2_len=0;
    unsigned char mac2[HMAC_LEN];
    HMAC(EVP_sha256(), sign_key, 16,
         payload, (int)payload_len, mac2, &mac2_len);
    if (!ct_memeq(mac, mac2, HMAC_LEN)) { free(tok); return NULL; }

    /* grab fields */
    const unsigned char *ver  = payload;
    const unsigned char *ts   = payload + 1;
    const unsigned char *iv   = payload + 1 + 8;
    const unsigned char *ciph = iv + IV_LEN;
    size_t ciph_len           = payload_len - 1 - 8 - IV_LEN;

    if (*ver != VERSION_BYTE) { free(tok); return NULL; }

    /* AES-CBC decrypt */
    unsigned char *ptext = malloc(ciph_len);
    int outl1=0,outl2=0;
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, enc_key, iv);
    EVP_DecryptUpdate(ctx, ptext, &outl1, ciph, (int)ciph_len);
    EVP_DecryptFinal_ex(ctx, ptext + outl1, &outl2);
    EVP_CIPHER_CTX_free(ctx);
    size_t pt_len = outl1 + outl2;

    /* remove padding */
    if (unpad(ptext, &pt_len) != 0) { free(tok); free(ptext); return NULL; }

    char *out = malloc(pt_len + 1);
    memcpy(out, ptext, pt_len);
    out[pt_len] = '\0';

    free(tok);
    free(ptext);
    return out;            /* caller frees */
}

/* -------------------------------------------------------------------------- */
/*                         Optional self-test main                            */
/* -------------------------------------------------------------------------- */
#ifdef FERNET_TEST
int main(void)
{
    const char *key =
        "XKrx9M-iqvQQArdfV3a4q_9aTAqWAYhgpWuFDx4JmdM=";   /* Fernet.generate_key() */

    char *tok = encrypt("hello world", key);
    printf("token: %s\n", tok);

    char *plain = decrypt(tok, key);
    printf("plain: %s\n", plain ? plain : "(decrypt failed)");

    free(tok);
    free(plain);
    return 0;
}
#endif