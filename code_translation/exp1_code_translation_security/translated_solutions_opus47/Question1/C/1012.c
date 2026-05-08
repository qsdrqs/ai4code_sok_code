#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>
#include <time.h>
#include <openssl/evp.h>
#include <openssl/hmac.h>
#include <openssl/rand.h>

/* Fernet protocol constants */
#define FERNET_KEY_SIZE  32
#define FERNET_IV_SIZE   16
#define FERNET_HMAC_SIZE 32
#define FERNET_VERSION   0x80

/* Global key (equivalent to: key = Fernet.generate_key()) */
static unsigned char key[FERNET_KEY_SIZE];

static const char b64_chars[] =
    "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";

/* ---------- Key generation ---------- */
void generate_key(void) {
    if (RAND_bytes(key, FERNET_KEY_SIZE) != 1) {
        fprintf(stderr, "Error: could not generate random key\n");
        exit(EXIT_FAILURE);
    }
}

/* ---------- URL-safe Base64 encode ---------- */
static char *b64_url_encode(const unsigned char *in, size_t in_len) {
    size_t out_len = 4 * ((in_len + 2) / 3);
    char *out = malloc(out_len + 1);
    if (!out) return NULL;

    size_t i = 0, j = 0;
    while (i < in_len) {
        uint32_t a = i < in_len ? in[i++] : 0;
        uint32_t b = i < in_len ? in[i++] : 0;
        uint32_t c = i < in_len ? in[i++] : 0;
        uint32_t triple = (a << 16) | (b << 8) | c;
        out[j++] = b64_chars[(triple >> 18) & 0x3F];
        out[j++] = b64_chars[(triple >> 12) & 0x3F];
        out[j++] = b64_chars[(triple >>  6) & 0x3F];
        out[j++] = b64_chars[ triple        & 0x3F];
    }
    size_t pad = (3 - in_len % 3) % 3;
    for (size_t k = 0; k < pad; k++) out[out_len - 1 - k] = '=';
    out[out_len] = '\0';
    return out;
}

/* ---------- URL-safe Base64 decode ---------- */
static unsigned char *b64_url_decode(const char *in, size_t *out_len) {
    static int tbl[256];
    static int init = 0;
    if (!init) {
        memset(tbl, 0xFF, sizeof(tbl));
        for (int i = 0; i < 64; i++) tbl[(unsigned char)b64_chars[i]] = i;
        init = 1;
    }

    size_t in_len = strlen(in);
    char *padded = NULL;
    if (in_len % 4 != 0) {
        size_t pad_len = in_len + (4 - in_len % 4);
        padded = malloc(pad_len + 1);
        if (!padded) return NULL;
        memcpy(padded, in, in_len);
        memset(padded + in_len, '=', pad_len - in_len);
        padded[pad_len] = '\0';
        in = padded;
        in_len = pad_len;
    }

    *out_len = in_len / 4 * 3;
    if (in_len >= 1 && in[in_len - 1] == '=') (*out_len)--;
    if (in_len >= 2 && in[in_len - 2] == '=') (*out_len)--;

    unsigned char *out = malloc(*out_len ? *out_len : 1);
    if (!out) { free(padded); return NULL; }

    size_t i = 0, j = 0;
    while (i < in_len) {
        uint32_t a = in[i] == '=' ? 0 : (uint32_t)tbl[(unsigned char)in[i]]; i++;
        uint32_t b = in[i] == '=' ? 0 : (uint32_t)tbl[(unsigned char)in[i]]; i++;
        uint32_t c = in[i] == '=' ? 0 : (uint32_t)tbl[(unsigned char)in[i]]; i++;
        uint32_t d = in[i] == '=' ? 0 : (uint32_t)tbl[(unsigned char)in[i]]; i++;
        uint32_t triple = (a << 18) | (b << 12) | (c << 6) | d;
        if (j < *out_len) out[j++] = (triple >> 16) & 0xFF;
        if (j < *out_len) out[j++] = (triple >>  8) & 0xFF;
        if (j < *out_len) out[j++] =  triple        & 0xFF;
    }
    free(padded);
    return out;
}

/* ---------- encrypt_text ---------- */
char *encrypt_text(const char *plain_text) {
    size_t pt_len = strlen(plain_text);
    const unsigned char *signing_key    = key;       /* first 16 bytes */
    const unsigned char *encryption_key = key + 16;  /* last 16 bytes  */

    unsigned char iv[FERNET_IV_SIZE];
    if (RAND_bytes(iv, FERNET_IV_SIZE) != 1) return NULL;

    /* Big-endian 8-byte timestamp */
    uint64_t ts = (uint64_t)time(NULL);
    unsigned char ts_b[8];
    for (int i = 0; i < 8; i++) ts_b[i] = (ts >> (56 - i * 8)) & 0xFF;

    /* AES-128-CBC with PKCS7 padding */
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    if (!ctx) return NULL;
    EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, encryption_key, iv);

    unsigned char *ct = malloc(pt_len + EVP_MAX_BLOCK_LENGTH);
    if (!ct) { EVP_CIPHER_CTX_free(ctx); return NULL; }

    int len, flen, ct_len;
    EVP_EncryptUpdate(ctx, ct, &len, (const unsigned char *)plain_text, pt_len);
    ct_len = len;
    EVP_EncryptFinal_ex(ctx, ct + len, &flen);
    ct_len += flen;
    EVP_CIPHER_CTX_free(ctx);

    /* Build signed portion: Version | Timestamp | IV | Ciphertext */
    size_t sign_len = 1 + 8 + FERNET_IV_SIZE + ct_len;
    unsigned char *sign_in = malloc(sign_len);
    if (!sign_in) { free(ct); return NULL; }

    sign_in[0] = FERNET_VERSION;
    memcpy(sign_in + 1, ts_b, 8);
    memcpy(sign_in + 9, iv, FERNET_IV_SIZE);
    memcpy(sign_in + 9 + FERNET_IV_SIZE, ct, ct_len);

    /* HMAC-SHA256 */
    unsigned char mac[FERNET_HMAC_SIZE];
    unsigned int mac_len = FERNET_HMAC_SIZE;
    HMAC(EVP_sha256(), signing_key, 16, sign_in, sign_len, mac, &mac_len);

    /* Full token = sign_in || HMAC */
    size_t token_len = sign_len + FERNET_HMAC_SIZE;
    unsigned char *token = malloc(token_len);
    if (!token) { free(ct); free(sign_in); return NULL; }
    memcpy(token, sign_in, sign_len);
    memcpy(token + sign_len, mac, FERNET_HMAC_SIZE);

    char *encoded = b64_url_encode(token, token_len);

    free(ct); free(sign_in); free(token);
    return encoded;
}

/* ---------- decrypt_text ---------- */
char *decrypt_text(const char *encrypted_text) {
    size_t token_len;
    unsigned char *token = b64_url_decode(encrypted_text, &token_len);
    if (!token) return NULL;

    size_t min_len = 1 + 8 + FERNET_IV_SIZE + FERNET_HMAC_SIZE;
    if (token_len < min_len || token[0] != FERNET_VERSION) {
        free(token); return NULL;
    }

    const unsigned char *signing_key    = key;
    const unsigned char *encryption_key = key + 16;

    /* Verify HMAC */
    unsigned char mac[FERNET_HMAC_SIZE];
    unsigned int mac_len = FERNET_HMAC_SIZE;
    HMAC(EVP_sha256(), signing_key, 16,
         token, token_len - FERNET_HMAC_SIZE, mac, &mac_len);
    if (memcmp(mac, token + token_len - FERNET_HMAC_SIZE, FERNET_HMAC_SIZE) != 0) {
        free(token); return NULL;
    }

    unsigned char *iv = token + 9;
    unsigned char *ct = token + 9 + FERNET_IV_SIZE;
    size_t ct_len = token_len - min_len;

    /* AES-128-CBC decrypt */
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    if (!ctx) { free(token); return NULL; }
    EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, encryption_key, iv);

    unsigned char *pt = malloc(ct_len + 1);
    if (!pt) { EVP_CIPHER_CTX_free(ctx); free(token); return NULL; }

    int len, flen;
    if (EVP_DecryptUpdate(ctx, pt, &len, ct, ct_len) != 1 ||
        EVP_DecryptFinal_ex(ctx, pt + len, &flen) != 1) {
        EVP_CIPHER_CTX_free(ctx); free(pt); free(token);
        return NULL;
    }
    pt[len + flen] = '\0';

    EVP_CIPHER_CTX_free(ctx);
    free(token);
    return (char *)pt;
}

/* ---------- Demo ---------- */
int main(void) {
    generate_key();

    const char *original = "Hello, World!";
    printf("Original : %s\n", original);

    char *encrypted = encrypt_text(original);
    if (!encrypted) { fprintf(stderr, "Encryption failed\n"); return 1; }
    printf("Encrypted: %s\n", encrypted);

    char *decrypted = decrypt_text(encrypted);
    if (!decrypted) { fprintf(stderr, "Decryption failed\n"); free(encrypted); return 1; }
    printf("Decrypted: %s\n", decrypted);

    free(encrypted);
    free(decrypted);
    return 0;
}