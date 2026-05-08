/*
 * C translation of the Python Fernet-based encryption code.
 * Depends on OpenSSL (libcrypto).
 * 
 * Compile with:
 *   gcc -o fernet fernet.c -lcrypto
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>
#include <time.h>
#include <openssl/evp.h>
#include <openssl/hmac.h>
#include <openssl/rand.h>

#define SALT_SIZE 16
#define IV_SIZE   16
#define KEY_SIZE  32
#define HMAC_SIZE 32

static const char b64_chars[] =
    "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";

/* URL-safe Base64 encoding (Python's urlsafe_b64encode) */
static char *b64_urlsafe_encode(const unsigned char *data, size_t len, size_t *out_len) {
    size_t encoded_len = 4 * ((len + 2) / 3);
    char *encoded = malloc(encoded_len + 1);
    if (!encoded) return NULL;

    size_t i, j = 0;
    for (i = 0; i + 3 <= len; i += 3) {
        encoded[j++] = b64_chars[(data[i] >> 2) & 0x3F];
        encoded[j++] = b64_chars[((data[i] & 0x3) << 4) | ((data[i+1] >> 4) & 0xF)];
        encoded[j++] = b64_chars[((data[i+1] & 0xF) << 2) | ((data[i+2] >> 6) & 0x3)];
        encoded[j++] = b64_chars[data[i+2] & 0x3F];
    }
    if (i < len) {
        encoded[j++] = b64_chars[(data[i] >> 2) & 0x3F];
        if (i + 1 < len) {
            encoded[j++] = b64_chars[((data[i] & 0x3) << 4) | ((data[i+1] >> 4) & 0xF)];
            encoded[j++] = b64_chars[((data[i+1] & 0xF) << 2)];
            encoded[j++] = '=';
        } else {
            encoded[j++] = b64_chars[(data[i] & 0x3) << 4];
            encoded[j++] = '=';
            encoded[j++] = '=';
        }
    }
    encoded[j] = '\0';
    *out_len = j;
    return encoded;
}

/* URL-safe Base64 decoding (Python's urlsafe_b64decode) */
static unsigned char *b64_urlsafe_decode(const char *data, size_t len, size_t *out_len) {
    int decode_table[256];
    for (int k = 0; k < 256; k++) decode_table[k] = -1;
    for (int k = 0; k < 64; k++) decode_table[(unsigned char)b64_chars[k]] = k;

    size_t actual_len = len;
    while (actual_len > 0 && data[actual_len - 1] == '=') actual_len--;

    size_t out_size = (actual_len * 3) / 4 + 3;
    unsigned char *decoded = malloc(out_size);
    if (!decoded) return NULL;

    size_t i, j = 0;
    for (i = 0; i + 4 <= actual_len; i += 4) {
        int a = decode_table[(unsigned char)data[i]];
        int b = decode_table[(unsigned char)data[i+1]];
        int c = decode_table[(unsigned char)data[i+2]];
        int d = decode_table[(unsigned char)data[i+3]];
        decoded[j++] = (a << 2) | (b >> 4);
        decoded[j++] = ((b & 0xF) << 4) | (c >> 2);
        decoded[j++] = ((c & 0x3) << 6) | d;
    }
    if (i + 2 <= actual_len) {
        int a = decode_table[(unsigned char)data[i]];
        int b = decode_table[(unsigned char)data[i+1]];
        decoded[j++] = (a << 2) | (b >> 4);
        if (i + 3 <= actual_len) {
            int c = decode_table[(unsigned char)data[i+2]];
            decoded[j++] = ((b & 0xF) << 4) | (c >> 2);
        }
    }
    *out_len = j;
    return decoded;
}

/* Derive a secret key from a given password and salt (PBKDF2-HMAC-SHA256) */
static void derive_key(const unsigned char *password, size_t password_len,
                       const unsigned char *salt, size_t salt_len,
                       int iterations, unsigned char *key) {
    PKCS5_PBKDF2_HMAC((const char *)password, (int)password_len,
                      salt, (int)salt_len, iterations,
                      EVP_sha256(), KEY_SIZE, key);
}

/* Produce a raw Fernet token (no base64 wrapper) */
static unsigned char *fernet_encrypt_raw(const unsigned char *key,
                                         const unsigned char *message, size_t message_len,
                                         size_t *out_len) {
    const unsigned char *signing_key    = key;        /* first 16 bytes */
    const unsigned char *encryption_key = key + 16;   /* last  16 bytes */

    unsigned char iv[IV_SIZE];
    if (RAND_bytes(iv, IV_SIZE) != 1) return NULL;

    /* Current timestamp as 8-byte big-endian */
    uint64_t timestamp = (uint64_t)time(NULL);
    unsigned char ts[8];
    for (int k = 0; k < 8; k++)
        ts[7 - k] = (timestamp >> (k * 8)) & 0xFF;

    /* Encrypt with AES-128-CBC (PKCS7 padding by default) */
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    if (!ctx) return NULL;
    EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, encryption_key, iv);

    unsigned char *ciphertext = malloc(message_len + 16);
    if (!ciphertext) { EVP_CIPHER_CTX_free(ctx); return NULL; }

    int ciphertext_len, len;
    EVP_EncryptUpdate(ctx, ciphertext, &len, message, (int)message_len);
    ciphertext_len = len;
    EVP_EncryptFinal_ex(ctx, ciphertext + len, &len);
    ciphertext_len += len;
    EVP_CIPHER_CTX_free(ctx);

    /* Fernet token: Version(1) || Timestamp(8) || IV(16) || Ciphertext || HMAC(32) */
    size_t token_len = 1 + 8 + IV_SIZE + (size_t)ciphertext_len + HMAC_SIZE;
    unsigned char *token = malloc(token_len);
    if (!token) { free(ciphertext); return NULL; }

    token[0] = 0x80;
    memcpy(token + 1, ts, 8);
    memcpy(token + 9, iv, IV_SIZE);
    memcpy(token + 25, ciphertext, (size_t)ciphertext_len);

    unsigned char hmac_out[HMAC_SIZE];
    unsigned int hmac_len;
    HMAC(EVP_sha256(), signing_key, 16,
         token, 25 + (size_t)ciphertext_len, hmac_out, &hmac_len);
    memcpy(token + 25 + ciphertext_len, hmac_out, HMAC_SIZE);

    free(ciphertext);
    *out_len = token_len;
    return token;
}

/* Decrypt a raw Fernet token */
static unsigned char *fernet_decrypt_raw(const unsigned char *key,
                                         const unsigned char *token, size_t token_len,
                                         size_t *out_len) {
    const unsigned char *signing_key    = key;
    const unsigned char *encryption_key = key + 16;

    if (token_len < 1 + 8 + IV_SIZE + HMAC_SIZE) return NULL;
    if (token[0] != 0x80) return NULL;

    /* Verify HMAC */
    unsigned char hmac_out[HMAC_SIZE];
    unsigned int hmac_len;
    HMAC(EVP_sha256(), signing_key, 16,
         token, token_len - HMAC_SIZE, hmac_out, &hmac_len);
    if (memcmp(hmac_out, token + token_len - HMAC_SIZE, HMAC_SIZE) != 0)
        return NULL;

    const unsigned char *iv = token + 9;
    const unsigned char *ciphertext = token + 25;
    size_t ciphertext_len = token_len - 25 - HMAC_SIZE;

    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    if (!ctx) return NULL;
    EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, encryption_key, iv);

    unsigned char *plaintext = malloc(ciphertext_len + 16);
    if (!plaintext) { EVP_CIPHER_CTX_free(ctx); return NULL; }

    int len, plaintext_len;
    EVP_DecryptUpdate(ctx, plaintext, &len, ciphertext, (int)ciphertext_len);
    plaintext_len = len;
    if (!EVP_DecryptFinal_ex(ctx, plaintext + len, &len)) {
        EVP_CIPHER_CTX_free(ctx);
        free(plaintext);
        return NULL;
    }
    plaintext_len += len;
    EVP_CIPHER_CTX_free(ctx);

    plaintext[plaintext_len] = '\0';
    *out_len = (size_t)plaintext_len;
    return plaintext;
}

/* Matches Python's encrypt() */
char *encrypt_message(const char *message, const char *password,
                      int iterations, size_t *out_len) {
    unsigned char salt[SALT_SIZE];
    if (RAND_bytes(salt, SALT_SIZE) != 1) return NULL;

    unsigned char raw_key[KEY_SIZE];
    /* Note: Python's encrypt() hardcodes 100_000 here even though it takes
     * an `iterations` argument (only the argument is encoded into the token). */
    derive_key((const unsigned char *)password, strlen(password),
               salt, SALT_SIZE, 100000, raw_key);

    size_t fernet_raw_len;
    unsigned char *fernet_raw = fernet_encrypt_raw(
        raw_key, (const unsigned char *)message, strlen(message), &fernet_raw_len);
    if (!fernet_raw) return NULL;

    /* salt(16) || iterations(4 big-endian) || fernet_raw */
    size_t total_len = SALT_SIZE + 4 + fernet_raw_len;
    unsigned char *final_data = malloc(total_len);
    if (!final_data) { free(fernet_raw); return NULL; }

    memcpy(final_data, salt, SALT_SIZE);
    final_data[SALT_SIZE + 0] = (unsigned char)((iterations >> 24) & 0xFF);
    final_data[SALT_SIZE + 1] = (unsigned char)((iterations >> 16) & 0xFF);
    final_data[SALT_SIZE + 2] = (unsigned char)((iterations >> 8)  & 0xFF);
    final_data[SALT_SIZE + 3] = (unsigned char)( iterations        & 0xFF);
    memcpy(final_data + SALT_SIZE + 4, fernet_raw, fernet_raw_len);

    char *result = b64_urlsafe_encode(final_data, total_len, out_len);

    free(fernet_raw);
    free(final_data);
    return result;
}

/* Matches Python's decrypt() */
unsigned char *decrypt_message(const char *token, const char *password, size_t *out_len) {
    size_t decoded_len;
    unsigned char *decoded = b64_urlsafe_decode(token, strlen(token), &decoded_len);
    if (!decoded) return NULL;
    if (decoded_len < 20) { free(decoded); return NULL; }

    unsigned char salt[SALT_SIZE];
    memcpy(salt, decoded, SALT_SIZE);

    uint32_t iterations = ((uint32_t)decoded[16] << 24)
                        | ((uint32_t)decoded[17] << 16)
                        | ((uint32_t)decoded[18] << 8)
                        |  (uint32_t)decoded[19];

    unsigned char raw_key[KEY_SIZE];
    derive_key((const unsigned char *)password, strlen(password),
               salt, SALT_SIZE, (int)iterations, raw_key);

    size_t fernet_token_len = decoded_len - 20;
    unsigned char *plaintext = fernet_decrypt_raw(
        raw_key, decoded + 20, fernet_token_len, out_len);

    free(decoded);
    return plaintext;
}

int main(void) {
    const char *message  = "nice123";
    const char *password = "password123";

    size_t token_len;
    char *token = encrypt_message(message, password, 100000, &token_len);
    if (!token) { fprintf(stderr, "Encryption failed\n"); return 1; }
    printf("b'%s'\n", token);

    size_t plaintext_len;
    unsigned char *plaintext = decrypt_message(token, password, &plaintext_len);
    if (!plaintext) {
        fprintf(stderr, "Decryption failed\n");
        free(token);
        return 1;
    }
    printf("%.*s\n", (int)plaintext_len, plaintext);

    free(token);
    free(plaintext);
    return 0;
}