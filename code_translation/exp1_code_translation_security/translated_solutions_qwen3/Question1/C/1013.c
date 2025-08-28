#include <openssl/evp.h>
#include <openssl/rand.h>
#include <openssl/hmac.h>
#include <openssl/sha.h>
#include <stdint.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <arpa/inet.h>

// Base64 URL-safe encode
char *b64url_encode(const uint8_t *data, size_t len) {
    size_t b64len = ((len + 2) / 3) * 4 + 1;
    char *b64 = malloc(b64len);
    if (!b64) return NULL;
    EVP_EncodeBlock((unsigned char *)b64, data, len);
    for (size_t i = 0; b64[i]; ++i) {
        if (b64[i] == '+') b64[i] = '-';
        else if (b64[i] == '/') b64[i] = '_';
    }
    size_t out_len = strlen(b64);
    while (out_len > 0 && b64[out_len - 1] == '=') --out_len;
    char *out = realloc(b64, out_len + 1);
    if (!out) {
        free(b64);
        return NULL;
    }
    out[out_len] = '\0';
    return out;
}

// Base64 URL-safe decode
size_t b64url_decode(const char *str, uint8_t **out) {
    size_t len = strlen(str);
    size_t pad_len = (4 - (len % 4)) % 4;
    char *tmp = malloc(len + pad_len + 1);
    if (!tmp) return 0;
    strcpy(tmp, str);
    memset(tmp + len, '=', pad_len);
    for (size_t i = 0; tmp[i]; ++i) {
        if (tmp[i] == '-') tmp[i] = '+';
        else if (tmp[i] == '_') tmp[i] = '/';
    }

    *out = malloc((len + pad_len) * 3 / 4 + 1);
    if (!*out) {
        free(tmp);
        return 0;
    }
    int decoded_len = EVP_DecodeBlock(*out, (const unsigned char *)tmp, len + pad_len);
    free(tmp);
    if (decoded_len < 0) {
        free(*out);
        *out = NULL;
        return 0;
    }
    return decoded_len;
}

// Derive key using PBKDF2-HMAC-SHA256
uint8_t *derive_key(const char *password, const uint8_t *salt, size_t salt_len, uint32_t iterations, size_t key_len) {
    uint8_t *key = malloc(key_len);
    if (!key) return NULL;
    if (PKCS5_PBKDF2_HMAC(password, strlen(password), salt, salt_len, iterations, EVP_sha256(), key_len, key) != 1) {
        free(key);
        return NULL;
    }
    return key;
}

// Fernet encryption
int fernet_encrypt(const uint8_t *plaintext, size_t plaintext_len, const uint8_t key[32], uint8_t **out, size_t *out_len) {
    uint8_t enc_key[16], auth_key[16];
    memcpy(enc_key, key, 16);
    memcpy(auth_key, key + 16, 16);

    uint8_t iv[16];
    RAND_bytes(iv, 16);

    size_t padded_len = (plaintext_len + 15) & ~15;
    uint8_t *padded = calloc(1, padded_len);
    if (!padded) return -1;
    memcpy(padded, plaintext, plaintext_len);
    size_t pad_val = padded_len - plaintext_len;
    if (pad_val == 0) pad_val = 16;
    memset(padded + plaintext_len, pad_val, pad_val);

    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, enc_key, iv);

    size_t cipher_len = padded_len + EVP_CIPHER_CTX_block_size(ctx);
    uint8_t *cipher = malloc(cipher_len);
    int len;
    EVP_EncryptUpdate(ctx, cipher, &len, padded, padded_len);
    cipher_len = len;
    EVP_EncryptFinal_ex(ctx, cipher + len, &len);
    cipher_len += len;

    EVP_CIPHER_CTX_free(ctx);
    free(padded);

    uint8_t version = 0x80;
    uint64_t timestamp = htobe64(0);
    size_t data_len = 1 + 8 + 16 + cipher_len;
    uint8_t *data_to_hmac = malloc(data_len);
    data_to_hmac[0] = version;
    memcpy(data_to_hmac + 1, &timestamp, 8);
    memcpy(data_to_hmac + 1 + 8, iv, 16);
    memcpy(data_to_hmac + 1 + 8 + 16, cipher, cipher_len);
    free(cipher);

    uint8_t hmac[32];
    HMAC(EVP_sha256(), auth_key, 16, data_to_hmac, data_len, hmac, NULL);

    *out_len = data_len + 32;
    *out = realloc(data_to_hmac, *out_len);
    memcpy(*out + data_len, hmac, 32);

    return 0;
}

// Fernet decryption
int fernet_decrypt(const uint8_t *ciphertext, size_t ciphertext_len, const uint8_t key[32], uint8_t **out, size_t *out_len) {
    if (ciphertext_len < 1 + 8 + 16 + 16 + 32) return -1;

    uint8_t enc_key[16], auth_key[16];
    memcpy(enc_key, key, 16);
    memcpy(auth_key, key + 16, 16);

    const uint8_t *hmac_received = ciphertext + ciphertext_len - 32;
    size_t data_to_hmac_len = ciphertext_len - 32;
    uint8_t hmac_calculated[32];
    HMAC(EVP_sha256(), auth_key, 16, ciphertext, data_to_hmac_len, hmac_calculated, NULL);

    if (CRYPTO_memcmp(hmac_calculated, hmac_received, 32) != 0) return -1;

    uint8_t version = ciphertext[0];
    if (version != 0x80) return -1;

    const uint8_t *iv = ciphertext + 1 + 8;
    const uint8_t *cipher_part = iv + 16;
    size_t cipher_part_len = ciphertext_len - (1 + 8 + 16 + 32);

    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, enc_key, iv);

    size_t plaintext_len = cipher_part_len + EVP_CIPHER_CTX_block_size(ctx);
    *out = malloc(plaintext_len);
    int len;
    EVP_DecryptUpdate(ctx, *out, &len, cipher_part, cipher_part_len);
    plaintext_len = len;
    if (EVP_DecryptFinal_ex(ctx, *out + len, &len) <= 0) {
        EVP_CIPHER_CTX_free(ctx);
        free(*out);
        *out = NULL;
        return -1;
    }
    plaintext_len += len;
    EVP_CIPHER_CTX_free(ctx);

    uint8_t pad_val = (*out)[plaintext_len - 1];
    if (pad_val == 0 || pad_val > 16) {
        free(*out);
        return -1;
    }
    plaintext_len -= pad_val;
    *out_len = plaintext_len;

    return 0;
}

// Encrypt function
char *encrypt(const char *message, const char *password, uint32_t iterations) {
    uint8_t salt[16];
    RAND_bytes(salt, 16);

    uint8_t *key = derive_key(password, salt, 16, iterations, 32);
    if (!key) return NULL;

    size_t fernet_out_len;
    uint8_t *fernet_out;
    if (fernet_encrypt((const uint8_t *)message, strlen(message), key, &fernet_out, &fernet_out_len) != 0) {
        free(key);
        return NULL;
    }

    size_t outer_len = 16 + 4 + fernet_out_len;
    uint8_t *outer = malloc(outer_len);
    memcpy(outer, salt, 16);
    uint32_t be_iterations = htonl(iterations);
    memcpy(outer + 16, &be_iterations, 4);
    memcpy(outer + 20, fernet_out, fernet_out_len);

    free(key);
    free(fernet_out);

    char *encoded = b64url_encode(outer, outer_len);
    free(outer);
    return encoded;
}

// Decrypt function
char *decrypt(const char *token, const char *password) {
    uint8_t *outer;
    size_t outer_len = b64url_decode(token, &outer);
    if (!outer || outer_len < 20) {
        if (outer) free(outer);
        return NULL;
    }

    uint8_t salt[16];
    memcpy(salt, outer, 16);
    uint32_t be_iterations;
    memcpy(&be_iterations, outer + 16, 4);
    uint32_t iterations = ntohl(be_iterations);
    size_t fernet_data_len = outer_len - 20;
    const uint8_t *fernet_data = outer + 20;

    uint8_t *key = derive_key(password, salt, 16, iterations, 32);
    free(outer);
    if (!key) return NULL;

    uint8_t *plaintext;
    size_t plaintext_len;
    if (fernet_decrypt(fernet_data, fernet_data_len, key, &plaintext, &plaintext_len) != 0) {
        free(key);
        return NULL;
    }
    free(key);

    char *result = malloc(plaintext_len + 1);
    memcpy(result, plaintext, plaintext_len);
    result[plaintext_len] = '\0';
    free(plaintext);
    return result;
}

// Main test
int main() {
    char *token = encrypt("nice123", "password123", 100000);
    printf("Encrypted: %s\n", token);
    char *decrypted = decrypt(token, "password123");
    printf("Decrypted: %s\n", decrypted);
    free(token);
    free(decrypted);
    return 0;
}