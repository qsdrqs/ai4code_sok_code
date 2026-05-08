/*
 * Fernet encryption/decryption in C, equivalent to Python's cryptography.fernet.
 *
 * Fernet key format (32 bytes, base64url-encoded):
 *   signing_key (16 bytes) || encryption_key (16 bytes)
 *
 * Token layout (then base64url-encoded):
 *   Version(1) || Timestamp(8, big-endian) || IV(16) || Ciphertext(n*16) || HMAC(32)
 *
 * Compile: gcc fernet.c -o fernet -lcrypto
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>
#include <time.h>

#include <openssl/evp.h>
#include <openssl/hmac.h>
#include <openssl/rand.h>
#include <openssl/bio.h>
#include <openssl/buffer.h>
#include <openssl/crypto.h>

#define FERNET_VERSION 0x80

/* ---------- Base64 URL-safe helpers (OpenSSL based) ---------- */

static char *b64url_encode(const unsigned char *input, size_t length) {
    BIO *bio, *b64;
    BUF_MEM *buffer_ptr;
    char *output = NULL;

    b64 = BIO_new(BIO_f_base64());
    bio = BIO_new(BIO_s_mem());
    bio = BIO_push(b64, bio);
    BIO_set_flags(bio, BIO_FLAGS_BASE64_NO_NL);
    BIO_write(bio, input, (int)length);
    BIO_flush(bio);
    BIO_get_mem_ptr(bio, &buffer_ptr);

    output = (char *)malloc(buffer_ptr->length + 1);
    if (output) {
        memcpy(output, buffer_ptr->data, buffer_ptr->length);
        output[buffer_ptr->length] = '\0';
        /* Translate standard base64 to URL-safe alphabet */
        for (size_t i = 0; output[i]; i++) {
            if (output[i] == '+') output[i] = '-';
            else if (output[i] == '/') output[i] = '_';
        }
    }

    BIO_free_all(bio);
    return output;
}

static int b64url_decode(const char *input, unsigned char **output) {
    *output = NULL;
    size_t input_len = strlen(input);
    char *b64 = (char *)malloc(input_len + 5);
    if (!b64) return -1;

    memcpy(b64, input, input_len);
    /* Translate URL-safe alphabet back to standard */
    for (size_t i = 0; i < input_len; i++) {
        if (b64[i] == '-') b64[i] = '+';
        else if (b64[i] == '_') b64[i] = '/';
    }
    /* Re-add any missing padding */
    size_t padded_len = input_len;
    while (padded_len % 4 != 0) b64[padded_len++] = '=';
    b64[padded_len] = '\0';

    size_t buf_size = (padded_len * 3) / 4;
    *output = (unsigned char *)malloc(buf_size);
    if (!*output) { free(b64); return -1; }

    BIO *bio = BIO_new_mem_buf(b64, -1);
    BIO *b64_bio = BIO_new(BIO_f_base64());
    bio = BIO_push(b64_bio, bio);
    BIO_set_flags(bio, BIO_FLAGS_BASE64_NO_NL);

    int len = BIO_read(bio, *output, (int)buf_size);

    BIO_free_all(bio);
    free(b64);
    return len;
}

/* ---------- Fernet API ---------- */

/* Function to encrypt msg with key using Fernet (AES-128-CBC with HMAC).
 * Returns a newly allocated base64url-encoded token (caller must free).
 * Returns NULL on error. */
char *encrypt(const unsigned char *msg, size_t msg_len, const char *key) {
    unsigned char *key_bytes = NULL, *ciphertext = NULL, *token = NULL;
    char *result = NULL;
    EVP_CIPHER_CTX *ctx = NULL;
    int len = 0, ciphertext_len = 0;

    int key_len = b64url_decode(key, &key_bytes);
    if (key_len != 32) goto cleanup;

    unsigned char *signing_key    = key_bytes;
    unsigned char *encryption_key = key_bytes + 16;

    /* Random IV */
    unsigned char iv[16];
    if (RAND_bytes(iv, 16) != 1) goto cleanup;

    /* Current timestamp, 64-bit big-endian */
    uint64_t ts = (uint64_t)time(NULL);
    unsigned char ts_bytes[8];
    for (int i = 0; i < 8; i++)
        ts_bytes[i] = (unsigned char)((ts >> (8 * (7 - i))) & 0xFF);

    /* AES-128-CBC encryption (PKCS7 padding enabled by default) */
    ctx = EVP_CIPHER_CTX_new();
    if (!ctx) goto cleanup;
    if (EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, encryption_key, iv) != 1) goto cleanup;

    ciphertext = (unsigned char *)malloc(msg_len + 16);
    if (!ciphertext) goto cleanup;

    if (EVP_EncryptUpdate(ctx, ciphertext, &len, msg, (int)msg_len) != 1) goto cleanup;
    ciphertext_len = len;
    if (EVP_EncryptFinal_ex(ctx, ciphertext + len, &len) != 1) goto cleanup;
    ciphertext_len += len;

    /* Assemble: Version || Timestamp || IV || Ciphertext || HMAC */
    size_t token_len = 1 + 8 + 16 + (size_t)ciphertext_len + 32;
    token = (unsigned char *)malloc(token_len);
    if (!token) goto cleanup;

    token[0] = FERNET_VERSION;
    memcpy(token + 1,  ts_bytes,   8);
    memcpy(token + 9,  iv,         16);
    memcpy(token + 25, ciphertext, (size_t)ciphertext_len);

    /* HMAC-SHA256 over everything except the HMAC field itself */
    unsigned char hmac[32];
    unsigned int  hmac_len = 32;
    if (HMAC(EVP_sha256(), signing_key, 16,
             token, 25 + (size_t)ciphertext_len,
             hmac, &hmac_len) == NULL) goto cleanup;

    memcpy(token + 25 + ciphertext_len, hmac, 32);

    /* Final: base64url-encode */
    result = b64url_encode(token, token_len);

cleanup:
    if (ctx)        EVP_CIPHER_CTX_free(ctx);
    if (ciphertext) free(ciphertext);
    if (token)      free(token);
    if (key_bytes)  free(key_bytes);
    return result;
}

/* Function to decrypt msg with key using Fernet (AES-128-CBC with HMAC).
 * On success, returns a newly allocated plaintext buffer (caller must free)
 * and writes its length to *out_len. Returns NULL on error (invalid HMAC,
 * wrong version, malformed token, ...). */
unsigned char *decrypt(const char *ciphertext, const char *key, size_t *out_len) {
    unsigned char *key_bytes = NULL, *token = NULL, *plaintext = NULL, *result = NULL;
    EVP_CIPHER_CTX *ctx = NULL;
    int len = 0, plaintext_len = 0;

    if (out_len) *out_len = 0;

    int key_len = b64url_decode(key, &key_bytes);
    if (key_len != 32) goto cleanup;

    unsigned char *signing_key    = key_bytes;
    unsigned char *encryption_key = key_bytes + 16;

    int token_len = b64url_decode(ciphertext, &token);
    /* Minimum token: 1 + 8 + 16 + 16 (one AES block) + 32 = 73 bytes */
    if (token_len < 73) goto cleanup;
    if (token[0] != FERNET_VERSION) goto cleanup;

    /* Verify HMAC in constant time */
    unsigned char expected[32];
    unsigned int  expected_len = 32;
    if (HMAC(EVP_sha256(), signing_key, 16,
             token, (size_t)(token_len - 32),
             expected, &expected_len) == NULL) goto cleanup;
    if (CRYPTO_memcmp(expected, token + token_len - 32, 32) != 0) goto cleanup;

    /* AES-128-CBC decryption */
    unsigned char *iv          = token + 9;
    unsigned char *enc_content = token + 25;
    int enc_content_len        = token_len - 25 - 32;

    ctx = EVP_CIPHER_CTX_new();
    if (!ctx) goto cleanup;
    if (EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, encryption_key, iv) != 1) goto cleanup;

    plaintext = (unsigned char *)malloc(enc_content_len);
    if (!plaintext) goto cleanup;

    if (EVP_DecryptUpdate(ctx, plaintext, &len, enc_content, enc_content_len) != 1) goto cleanup;
    plaintext_len = len;
    if (EVP_DecryptFinal_ex(ctx, plaintext + len, &len) != 1) goto cleanup;
    plaintext_len += len;

    if (out_len) *out_len = (size_t)plaintext_len;
    result = plaintext;
    plaintext = NULL;  /* ownership transferred to caller */

cleanup:
    if (ctx)       EVP_CIPHER_CTX_free(ctx);
    if (plaintext) free(plaintext);
    if (token)     free(token);
    if (key_bytes) free(key_bytes);
    return result;
}

/* ---------- Example usage ---------- */
#ifdef TEST_MAIN
int main(void) {
    /* A valid Fernet key is 32 random bytes, base64url-encoded. */
    const char *key = "Ri0tSkxVMEFhMWdlUTQyWkZhcTFJRFdPQ1A0VFFjamo=";
    const unsigned char *msg = (const unsigned char *)"Hello, Fernet!";
    size_t msg_len = strlen((const char *)msg);

    char *token = encrypt(msg, msg_len, key);
    if (!token) { fprintf(stderr, "encrypt failed\n"); return 1; }
    printf("Token: %s\n", token);

    size_t pt_len;
    unsigned char *pt = decrypt(token, key, &pt_len);
    if (!pt)    { fprintf(stderr, "decrypt failed\n"); free(token); return 1; }
    printf("Plaintext: %.*s\n", (int)pt_len, pt);

    free(token);
    free(pt);
    return 0;
}
#endif