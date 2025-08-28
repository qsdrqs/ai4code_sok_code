#include <openssl/evp.h>
#include <openssl/rand.h>
#include <string.h>
#include <assert.h>
#include <stdio.h>
#include <stdlib.h>

/**
 * Encrypts the given data using AES-256-CBC with PKCS7 padding.
 *
 * @param data      Pointer to the plaintext data to encrypt.
 * @param len       Length of the plaintext data in bytes.
 * @param key       32-byte AES-256 key.
 * @param iv        16-byte initialization vector.
 * @param out_len   Pointer to store the length of the resulting ciphertext.
 * @return          Pointer to the encrypted data (must be freed by the caller), or NULL on failure.
 */
unsigned char* encrypt(const unsigned char *data, size_t len, const unsigned char *key, const unsigned char *iv, size_t *out_len) {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    if (!ctx) return NULL;

    // Initialize encryption context for AES-256-CBC
    if (EVP_EncryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }

    // Enable PKCS7 padding
    EVP_CIPHER_CTX_set_padding(ctx, 1);

    // Determine block size and allocate buffer for encrypted data
    size_t block_size = EVP_CIPHER_CTX_block_size(ctx);
    size_t buf_len = len + block_size;
    unsigned char *buf = malloc(buf_len);
    if (!buf) {
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }

    // Encrypt the data
    int encrypted_len = 0;
    if (EVP_EncryptUpdate(ctx, buf, &encrypted_len, data, len) != 1) {
        free(buf);
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }

    // Finalize encryption and add padding
    int final_len;
    if (EVP_EncryptFinal_ex(ctx, buf + encrypted_len, &final_len) != 1) {
        free(buf);
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }

    encrypted_len += final_len;
    *out_len = encrypted_len;

    EVP_CIPHER_CTX_free(ctx);
    return buf;
}

/**
 * Decrypts the given data using AES-256-CBC with PKCS7 padding.
 *
 * @param data      Pointer to the ciphertext data to decrypt.
 * @param len       Length of the ciphertext data in bytes.
 * @param key       32-byte AES-256 key.
 * @param iv        16-byte initialization vector.
 * @param out_len   Pointer to store the length of the resulting plaintext.
 * @return          Pointer to the decrypted data (must be freed by the caller), or NULL on failure.
 */
unsigned char* decrypt(const unsigned char *data, size_t len, const unsigned char *key, const unsigned char *iv, size_t *out_len) {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    if (!ctx) return NULL;

    // Initialize decryption context for AES-256-CBC
    if (EVP_DecryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }

    // Enable PKCS7 padding
    EVP_CIPHER_CTX_set_padding(ctx, 1);

    // Allocate buffer for decrypted data
    size_t block_size = EVP_CIPHER_CTX_block_size(ctx);
    size_t buf_len = len + block_size;
    unsigned char *buf = malloc(buf_len);
    if (!buf) {
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }

    // Decrypt the data
    int decrypted_len = 0;
    if (EVP_DecryptUpdate(ctx, buf, &decrypted_len, data, len) != 1) {
        free(buf);
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }

    // Finalize decryption and remove padding
    int final_len;
    if (EVP_DecryptFinal_ex(ctx, buf + decrypted_len, &final_len) != 1) {
        free(buf);
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }

    decrypted_len += final_len;
    *out_len = decrypted_len;

    EVP_CIPHER_CTX_free(ctx);
    return buf;
}

/**
 * Test function to verify encryption and decryption.
 */
void test() {
    const unsigned char *data = (const unsigned char *)"a secret message";
    size_t data_len = 16;

    unsigned char key[32];
    unsigned char iv[16];

    // Generate random key and IV
    if (!RAND_bytes(key, 32) || !RAND_bytes(iv, 16)) {
        fprintf(stderr, "Failed to generate random key or IV.\n");
        return;
    }

    // Encrypt
    size_t e_len;
    unsigned char *e = encrypt(data, data_len, key, iv, &e_len);
    if (!e) {
        fprintf(stderr, "Encryption failed.\n");
        return;
    }

    // Decrypt
    size_t d_len;
    unsigned char *d = decrypt(e, e_len, key, iv, &d_len);
    if (!d) {
        fprintf(stderr, "Decryption failed.\n");
        free(e);
        return;
    }

    // Validate result
    assert(d_len == data_len);
    assert(memcmp(d, data, data_len) == 0);

    // Clean up
    free(e);
    free(d);
}

int main() {
    test();
    return 0;
}