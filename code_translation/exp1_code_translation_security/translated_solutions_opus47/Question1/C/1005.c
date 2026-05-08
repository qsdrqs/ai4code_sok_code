/*
 * Fernet encryption/decryption in C using OpenSSL
 * Compile: gcc -o fernet fernet.c -lcrypto
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>
#include <time.h>
#include <openssl/evp.h>
#include <openssl/hmac.h>
#include <openssl/rand.h>
#include <openssl/crypto.h>

#define FERNET_VERSION     0x80
#define FERNET_KEY_SIZE    32
#define FERNET_BLOCK_SIZE  16
#define FERNET_IV_SIZE     16
#define FERNET_HMAC_SIZE   32

/* URL-safe base64 alphabet */
static const char b64_urlsafe_chars[] =
    "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";

static int b64_decode_table[256];
static int b64_table_initialized = 0;

static void init_b64_table(void) {
    if (b64_table_initialized) return;
    for (int i = 0; i < 256; i++) b64_decode_table[i] = -1;
    for (int i = 0; i < 64; i++)
        b64_decode_table[(unsigned char)b64_urlsafe_chars[i]] = i;
    b64_table_initialized = 1;
}

static char *b64_urlsafe_encode(const unsigned char *data, size_t len, size_t *out_len) {
    size_t enc_len = 4 * ((len + 2) / 3);
    char *out = (char *)malloc(enc_len + 1);
    if (!out) return NULL;

    size_t i = 0, j = 0;
    while (i < len) {
        uint32_t a = i < len ? data[i++] : 0;
        uint32_t b = i < len ? data[i++] : 0;
        uint32_t c = i < len ? data[i++] : 0;
        uint32_t triple = (a << 16) | (b << 8) | c;
        out[j++] = b64_urlsafe_chars[(triple >> 18) & 0x3F];
        out[j++] = b64_urlsafe_chars[(triple >> 12) & 0x3F];
        out[j++] = b64_urlsafe_chars[(triple >> 6)  & 0x3F];
        out[j++] = b64_urlsafe_chars[ triple        & 0x3F];
    }

    size_t pad = (len % 3 == 0) ? 0 : (3 - (len % 3));
    for (size_t k = 0; k < pad; k++)
        out[enc_len - 1 - k] = '=';

    out[enc_len] = '\0';
    if (out_len) *out_len = enc_len;
    return out;
}

static unsigned char *b64_urlsafe_decode(const char *data, size_t len, size_t *out_len) {
    init_b64_table();
    if (len == 0 || len % 4 != 0) return NULL;

    size_t dec_len = (len / 4) * 3;
    if (data[len - 1] == '=') dec_len--;
    if (len > 1 && data[len - 2] == '=') dec_len--;

    unsigned char *out = (unsigned char *)malloc(dec_len + 1);
    if (!out) return NULL;

    size_t j = 0;
    for (size_t i = 0; i < len; i += 4) {
        uint32_t sa = data[i]   == '=' ? 0 : (uint32_t)b64_decode_table[(unsigned char)data[i]];
        uint32_t sb = data[i+1] == '=' ? 0 : (uint32_t)b64_decode_table[(unsigned char)data[i+1]];
        uint32_t sc = data[i+2] == '=' ? 0 : (uint32_t)b64_decode_table[(unsigned char)data[i+2]];
        uint32_t sd = data[i+3] == '=' ? 0 : (uint32_t)b64_decode_table[(unsigned char)data[i+3]];
        uint32_t triple = (sa << 18) | (sb << 12) | (sc << 6) | sd;
        if (j < dec_len) out[j++] = (triple >> 16) & 0xFF;
        if (j < dec_len) out[j++] = (triple >> 8)  & 0xFF;
        if (j < dec_len) out[j++] =  triple        & 0xFF;
    }
    out[dec_len] = '\0';
    if (out_len) *out_len = dec_len;
    return out;
}

/* Equivalent to Fernet.generate_key() */
char *generate_key(void) {
    unsigned char raw_key[FERNET_KEY_SIZE];
    if (RAND_bytes(raw_key, FERNET_KEY_SIZE) != 1) return NULL;
    return b64_urlsafe_encode(raw_key, FERNET_KEY_SIZE, NULL);
}

/* Equivalent to Fernet(key).encrypt(msg) */
char *encrypt(const unsigned char *msg, size_t msg_len, const char *key) {
    size_t key_len;
    unsigned char *raw_key = b64_urlsafe_decode(key, strlen(key), &key_len);
    if (!raw_key || key_len != FERNET_KEY_SIZE) { free(raw_key); return NULL; }

    unsigned char *signing_key    = raw_key;
    unsigned char *encryption_key = raw_key + 16;

    unsigned char iv[FERNET_IV_SIZE];
    if (RAND_bytes(iv, FERNET_IV_SIZE) != 1) { free(raw_key); return NULL; }

    uint64_t timestamp = (uint64_t)time(NULL);
    unsigned char timestamp_bytes[8];
    for (int i = 0; i < 8; i++)
        timestamp_bytes[i] = (timestamp >> ((7 - i) * 8)) & 0xFF;

    /* PKCS7 padding */
    size_t pad_len    = FERNET_BLOCK_SIZE - (msg_len % FERNET_BLOCK_SIZE);
    size_t padded_len = msg_len + pad_len;
    unsigned char *padded_msg = (unsigned char *)malloc(padded_len);
    if (!padded_msg) { free(raw_key); return NULL; }
    memcpy(padded_msg, msg, msg_len);
    memset(padded_msg + msg_len, (unsigned char)pad_len, pad_len);

    /* AES-128-CBC encrypt */
    unsigned char *ciphertext = (unsigned char *)malloc(padded_len);
    if (!ciphertext) { free(raw_key); free(padded_msg); return NULL; }

    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, encryption_key, iv);
    EVP_CIPHER_CTX_set_padding(ctx, 0);

    int len, ciphertext_len;
    EVP_EncryptUpdate(ctx, ciphertext, &len, padded_msg, (int)padded_len);
    ciphertext_len = len;
    EVP_EncryptFinal_ex(ctx, ciphertext + len, &len);
    ciphertext_len += len;
    EVP_CIPHER_CTX_free(ctx);
    free(padded_msg);

    /* Build token body: version || timestamp || IV || ciphertext */
    size_t body_len = 1 + 8 + 16 + (size_t)ciphertext_len;
    unsigned char *body = (unsigned char *)malloc(body_len);
    if (!body) { free(raw_key); free(ciphertext); return NULL; }
    body[0] = FERNET_VERSION;
    memcpy(body + 1,  timestamp_bytes, 8);
    memcpy(body + 9,  iv, 16);
    memcpy(body + 25, ciphertext, ciphertext_len);
    free(ciphertext);

    /* Append HMAC-SHA256 */
    unsigned char hmac_out[FERNET_HMAC_SIZE];
    unsigned int hmac_len;
    HMAC(EVP_sha256(), signing_key, 16, body, body_len, hmac_out, &hmac_len);

    size_t full_len = body_len + FERNET_HMAC_SIZE;
    unsigned char *full = (unsigned char *)malloc(full_len);
    if (!full) { free(raw_key); free(body); return NULL; }
    memcpy(full, body, body_len);
    memcpy(full + body_len, hmac_out, FERNET_HMAC_SIZE);

    free(body);
    free(raw_key);

    char *result = b64_urlsafe_encode(full, full_len, NULL);
    free(full);
    return result;
}

/* Equivalent to Fernet(key).decrypt(msg) */
unsigned char *decrypt(const char *token, const char *key, size_t *msg_len) {
    size_t key_len;
    unsigned char *raw_key = b64_urlsafe_decode(key, strlen(key), &key_len);
    if (!raw_key || key_len != FERNET_KEY_SIZE) { free(raw_key); return NULL; }

    unsigned char *signing_key    = raw_key;
    unsigned char *encryption_key = raw_key + 16;

    size_t token_len;
    unsigned char *raw_token = b64_urlsafe_decode(token, strlen(token), &token_len);
    if (!raw_token || token_len < 1 + 8 + 16 + 16 + 32) {
        free(raw_key); free(raw_token); return NULL;
    }

    if (raw_token[0] != FERNET_VERSION) {
        free(raw_key); free(raw_token); return NULL;
    }

    size_t body_len = token_len - 32;
    unsigned char hmac_computed[FERNET_HMAC_SIZE];
    unsigned int hmac_len;
    HMAC(EVP_sha256(), signing_key, 16, raw_token, body_len, hmac_computed, &hmac_len);

    if (CRYPTO_memcmp(hmac_computed, raw_token + body_len, FERNET_HMAC_SIZE) != 0) {
        free(raw_key); free(raw_token); return NULL;
    }

    unsigned char *iv         = raw_token + 9;
    unsigned char *ciphertext = raw_token + 25;
    size_t ciphertext_len     = body_len - 25;

    unsigned char *plaintext = (unsigned char *)malloc(ciphertext_len + 1);
    if (!plaintext) { free(raw_key); free(raw_token); return NULL; }

    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, encryption_key, iv);
    EVP_CIPHER_CTX_set_padding(ctx, 0);

    int len, plaintext_len;
    EVP_DecryptUpdate(ctx, plaintext, &len, ciphertext, (int)ciphertext_len);
    plaintext_len = len;
    EVP_DecryptFinal_ex(ctx, plaintext + len, &len);
    plaintext_len += len;
    EVP_CIPHER_CTX_free(ctx);

    /* Remove PKCS7 padding */
    unsigned char padding = plaintext[plaintext_len - 1];
    if (padding == 0 || padding > FERNET_BLOCK_SIZE) {
        free(raw_key); free(raw_token); free(plaintext); return NULL;
    }
    plaintext_len -= padding;
    plaintext[plaintext_len] = '\0';

    free(raw_key);
    free(raw_token);

    if (msg_len) *msg_len = (size_t)plaintext_len;
    return plaintext;
}

int main(void) {
    char *key = generate_key();
    if (!key) { fprintf(stderr, "Failed to generate key\n"); return 1; }

    const char *msg = "a much longer message with punctuation!";
    size_t msg_len = strlen(msg);

    char *e = encrypt((const unsigned char *)msg, msg_len, key);
    if (!e) { fprintf(stderr, "Encryption failed\n"); free(key); return 1; }

    size_t d_len;
    unsigned char *d = decrypt(e, key, &d_len);
    if (!d) { fprintf(stderr, "Decryption failed\n"); free(key); free(e); return 1; }

    printf("b'%s'\n", e);
    printf("b'%.*s'\n", (int)d_len, d);

    free(key);
    free(e);
    free(d);
    return 0;
}