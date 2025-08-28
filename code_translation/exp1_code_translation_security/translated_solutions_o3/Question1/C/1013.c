/*  fernet_demo.c
 *
 *  C translation of the Python snippet that
 *      • derives a 32-byte key from password+salt  (PBKDF2-HMAC-SHA256)
 *      • performs Fernet encryption / decryption
 *      • wraps / unwraps the extra   salt‖iter_count   envelope
 *
 *  Build:  gcc fernet_demo.c -o fernet_demo -lcrypto
 */

#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <string.h>
#include <time.h>

#include <openssl/evp.h>
#include <openssl/rand.h>
#include <openssl/hmac.h>
#include <openssl/aes.h>
#include <openssl/sha.h>

/* --------------------------------------------------------------------------
 *  Helpers – URL-safe base-64
 * -------------------------------------------------------------------------- */

static char *b64url_encode(const unsigned char *in, size_t inlen,
                           size_t *outlen)          /* returns malloc'ed */
{
    /* OpenSSL gives normal “+/=” alphabet, we convert to URL-safe */
    size_t tlen = 4 * ((inlen + 2) / 3);
    unsigned char *tmp = malloc(tlen + 1);
    if (!tmp) return NULL;

    EVP_EncodeBlock(tmp, in, (int)inlen);

    for (size_t i = 0; i < tlen; ++i) {
        if (tmp[i] == '+') tmp[i] = '-';
        else if (tmp[i] == '/') tmp[i] = '_';
    }

    if (outlen) *outlen = tlen;
    return (char *)tmp;        /* still contains '=' padding, like Python */
}

static unsigned char *b64url_decode(const char *in, size_t *outlen)
/* returns malloc'ed buffer with binary; outlen filled */
{
    size_t inlen = strlen(in);

    /* reconstruct normal alphabet, work copy */
    char *copy = malloc(inlen + 5);                 /* 4 extra for padding */
    if (!copy) return NULL;
    strcpy(copy, in);

    for (size_t i = 0; i < inlen; ++i) {
        if (copy[i] == '-') copy[i] = '+';
        else if (copy[i] == '_') copy[i] = '/';
    }

    /* restore '=' padding so length is multiple of 4 */
    size_t pad = (4 - (inlen % 4)) % 4;
    for (size_t i = 0; i < pad; ++i) copy[inlen + i] = '=';
    copy[inlen + pad] = '\0';
    inlen += pad;

    unsigned char *out = malloc((inlen / 4) * 3 + 1);
    if (!out) { free(copy); return NULL; }

    int l = EVP_DecodeBlock(out, (unsigned char *)copy, (int)inlen);
    if (l < 0) { free(copy); free(out); return NULL; }
    if (outlen) *outlen = (size_t)l;

    free(copy);
    return out;
}

/* --------------------------------------------------------------------------
 *  Constant-time comparison (for MAC)
 * -------------------------------------------------------------------------- */
static int consttime_eq(const unsigned char *a, const unsigned char *b,
                        size_t len)
{
    unsigned char diff = 0;
    for (size_t i = 0; i < len; ++i) diff |= a[i] ^ b[i];
    return diff == 0;
}

/* --------------------------------------------------------------------------
 *  Key derivation –  PBKDF2-HMAC-SHA256
 * -------------------------------------------------------------------------- */
static int derive_key(const unsigned char *pwd, size_t pwdlen,
                      const unsigned char *salt, size_t saltlen,
                      uint32_t iterations,
                      unsigned char key[32])            /* 32-byte output */
{
    return PKCS5_PBKDF2_HMAC((const char *)pwd, (int)pwdlen,
                             salt, (int)saltlen,
                             iterations,
                             EVP_sha256(),
                             32, key);
}

/* --------------------------------------------------------------------------
 *  PKCS7 padding helpers
 * -------------------------------------------------------------------------- */
static void pkcs7_pad(const unsigned char *in, size_t inlen,
                      unsigned char **out, size_t *outlen)
{
    size_t blk = AES_BLOCK_SIZE;
    size_t pad = blk - (inlen % blk);
    *outlen = inlen + pad;
    *out = malloc(*outlen);
    memcpy(*out, in, inlen);
    memset(*out + inlen, (unsigned char)pad, pad);
}

static int pkcs7_unpad(unsigned char *buf, size_t *len)
{
    if (*len == 0) return 0;
    unsigned char pad = buf[*len - 1];
    if (!pad || pad > AES_BLOCK_SIZE) return 0;
    for (size_t i = 0; i < pad; ++i)
        if (buf[*len - 1 - i] != pad) return 0;
    *len -= pad;
    return 1;
}

/* --------------------------------------------------------------------------
 *  Fernet encrypt / decrypt  (binary, not base-64)
 * -------------------------------------------------------------------------- */

static int fernet_encrypt(const unsigned char key32[32],
                          const unsigned char *msg, size_t msglen,
                          unsigned char **token, size_t *toklen)
{
    /* Split key */
    const unsigned char *sign_key = key32 + 16;
    const unsigned char *enc_key  = key32;

    /* Version + timestamp */
    unsigned char hdr[1 + 8];
    hdr[0] = 0x80;
    uint64_t ts = (uint64_t)time(NULL);
    for (int i = 0; i < 8; ++i)
        hdr[1 + 7 - i] = (unsigned char)(ts >> (8 * i));

    /* IV */
    unsigned char iv[16];
    if (RAND_bytes(iv, sizeof(iv)) != 1) return 0;

    /* pad & AES-CBC */
    unsigned char *pmsg;
    size_t plen;
    pkcs7_pad(msg, msglen, &pmsg, &plen);

    unsigned char *cipher = malloc(plen);
    int outl1 = 0, outl2 = 0;
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, enc_key, iv);
    EVP_EncryptUpdate(ctx, cipher, &outl1, pmsg, (int)plen);
    EVP_EncryptFinal_ex(ctx, cipher + outl1, &outl2);
    EVP_CIPHER_CTX_free(ctx);
    size_t cipherlen = (size_t)(outl1 + outl2);

    free(pmsg);

    /* HMAC */
    size_t to_mac_len = sizeof(hdr) + sizeof(iv) + cipherlen;
    unsigned char *to_mac = malloc(to_mac_len);
    memcpy(to_mac, hdr, sizeof(hdr));
    memcpy(to_mac + sizeof(hdr), iv, sizeof(iv));
    memcpy(to_mac + sizeof(hdr) + sizeof(iv), cipher, cipherlen);

    unsigned char hmac_val[SHA256_DIGEST_LENGTH];
    unsigned int hmac_len = 0;
    HMAC(EVP_sha256(), sign_key, 16, to_mac, (int)to_mac_len,
         hmac_val, &hmac_len);

    /* final token = hdr|iv|cipher|hmac */
    *toklen = to_mac_len + hmac_len;
    *token  = malloc(*toklen);
    memcpy(*token, to_mac, to_mac_len);
    memcpy(*token + to_mac_len, hmac_val, hmac_len);

    free(to_mac);
    free(cipher);
    return 1;
}

static int fernet_decrypt(const unsigned char key32[32],
                          const unsigned char *token, size_t toklen,
                          unsigned char **msg, size_t *msglen)
{
    if (toklen < 1 + 8 + 16 + 32) return 0; /* minimal */

    const unsigned char *sign_key = key32 + 16;
    const unsigned char *enc_key  = key32;

    const unsigned char *hmac_part = token + toklen - 32;
    const size_t signed_len = toklen - 32;

    /* recompute HMAC */
    unsigned char hmac_chk[SHA256_DIGEST_LENGTH];
    unsigned int  hlen = 0;
    HMAC(EVP_sha256(), sign_key, 16, token, (int)signed_len,
         hmac_chk, &hlen);

    if (!consttime_eq(hmac_chk, hmac_part, 32)) return 0;

    const unsigned char *iv = token + 1 + 8;
    const unsigned char *cipher = iv + 16;
    size_t cipherlen = signed_len - (1 + 8 + 16);

    unsigned char *plain = malloc(cipherlen);   /* will shrink after unpad */
    int outl1=0,outl2=0;
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, enc_key, iv);
    EVP_DecryptUpdate(ctx, plain, &outl1, cipher, (int)cipherlen);
    EVP_DecryptFinal_ex(ctx, plain + outl1, &outl2);
    EVP_CIPHER_CTX_free(ctx);

    size_t plen = (size_t)(outl1 + outl2);
    if (!pkcs7_unpad(plain, &plen)) { free(plain); return 0; }

    *msg = plain;
    *msglen = plen;
    return 1;
}

/* --------------------------------------------------------------------------
 *  Envelope – salt(16) ‖ iter(4) ‖ fernet_token(bytes)
 * -------------------------------------------------------------------------- */

#define ITER_DEFAULT 100000

static char *encrypt_top(const char *plaintext,
                         const char *password)            /* b64url token */
{
    /* salt */
    unsigned char salt[16];
    RAND_bytes(salt, sizeof(salt));

    /* derive */
    unsigned char key32[32];
    derive_key((const unsigned char *)password, strlen(password),
               salt, sizeof(salt), ITER_DEFAULT, key32);

    /* fernet */
    unsigned char *fernet_bin;
    size_t fernet_len;
    if (!fernet_encrypt(key32,
                        (const unsigned char *)plaintext, strlen(plaintext),
                        &fernet_bin, &fernet_len))
        return NULL;

    /* build envelope */
    size_t env_len = 16 + 4 + fernet_len;
    unsigned char *env = malloc(env_len);

    memcpy(env, salt, 16);
    uint32_t iter_be = htonl(ITER_DEFAULT);
    memcpy(env + 16, &iter_be, 4);                /* big-endian */
    memcpy(env + 20, fernet_bin, fernet_len);

    /* base-64 url */
    size_t outlen;
    char *b64 = b64url_encode(env, env_len, &outlen);

    free(env);
    free(fernet_bin);
    return b64;               /* malloc'ed, = padding kept */
}

static char *decrypt_top(const char *token_b64,
                         const char *password)            /* plaintext */
{
    /* decode envelope */
    size_t env_len;
    unsigned char *env = b64url_decode(token_b64, &env_len);
    if (!env || env_len < 20) { free(env); return NULL; }

    const unsigned char *salt = env;              /* 16 bytes */
    uint32_t iter_be;
    memcpy(&iter_be, env + 16, 4);
    uint32_t iterations = ntohl(iter_be);

    const unsigned char *fernet_bin = env + 20;
    size_t fernet_len = env_len - 20;

    /* derive same key */
    unsigned char key32[32];
    derive_key((const unsigned char *)password, strlen(password),
               salt, 16, iterations, key32);

    /* decrypt Fernet */
    unsigned char *plain;
    size_t plainlen;
    if (!fernet_decrypt(key32, fernet_bin, fernet_len,
                        &plain, &plainlen)) {
        free(env); return NULL;
    }

    /* null-terminate for convenience */
    char *ret = malloc(plainlen + 1);
    memcpy(ret, plain, plainlen);
    ret[plainlen] = '\0';

    free(plain);
    free(env);
    return ret;
}

/* --------------------------------------------------------------------------
 *  Demo / main
 * -------------------------------------------------------------------------- */

int main(void)
{
    const char *msg = "nice123";
    const char *pwd = "password123";

    char *token = encrypt_top(msg, pwd);
    if (!token) { fprintf(stderr, "encryption failed\n"); return 1; }

    printf("Token  : %s\n", token);

    char *out = decrypt_top(token, pwd);
    if (!out) { fprintf(stderr, "decryption failed\n"); free(token); return 1; }

    printf("Decoded: %s\n", out);

    free(token);
    free(out);
    return 0;
}