#include <openssl/evp.h>
#include <openssl/rand.h>
#include <openssl/err.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Structure to hold encrypted data and nonce
typedef struct {
    unsigned char *ciphertext;
    size_t ciphertext_len;
    unsigned char nonce[16];  // EAX mode uses 16-byte nonce
} EncryptedData;

// Function to handle OpenSSL errors
void handle_errors() {
    ERR_print_errors_fp(stderr);
    exit(EXIT_FAILURE);
}

// Encrypt function: returns ciphertext and nonce
EncryptedData* encrypt(const unsigned char *plaintext, size_t plaintext_len, const unsigned char *key, size_t key_len) {
    EVP_CIPHER_CTX *ctx;
    int len;
    EncryptedData *result = (EncryptedData*)malloc(sizeof(EncryptedData));
    if (!result) handle_errors();

    // Allocate space for ciphertext (with padding)
    result->ciphertext = (unsigned char*)malloc(plaintext_len + EVP_CIPHER_block_size(EVP_aes_128_eax()));
    if (!result->ciphertext) {
        free(result);
        handle_errors();
    }

    // Generate a 16-byte nonce
    if (!RAND_bytes(result->nonce, 16)) {
        free(result->ciphertext);
        free(result);
        handle_errors();
    }

    // Create and initialize the cipher context
    ctx = EVP_CIPHER_CTX_new();
    if (!ctx) {
        free(result->ciphertext);
        free(result);
        handle_errors();
    }

    // Select cipher based on key length
    const EVP_CIPHER *cipher;
    if (key_len == 16) {
        cipher = EVP_aes_128_eax();
    } else if (key_len == 24) {
        cipher = EVP_aes_192_eax();
    } else if (key_len == 32) {
        cipher = EVP_aes_256_eax();
    } else {
        EVP_CIPHER_CTX_free(ctx);
        free(result->ciphertext);
        free(result);
        return NULL;
    }

    // Initialize encryption
    if (!EVP_EncryptInit_ex(ctx, cipher, NULL, NULL, NULL)) {
        EVP_CIPHER_CTX_free(ctx);
        free(result->ciphertext);
        free(result);
        handle_errors();
    }

    // Set nonce
    if (!EVP_CIPHER_CTX_set_iv_length(ctx, 16) ||
        !EVP_EncryptInit_ex(ctx, NULL, NULL, key, result->nonce)) {
        EVP_CIPHER_CTX_free(ctx);
        free(result->ciphertext);
        free(result);
        handle_errors();
    }

    // Encrypt the plaintext
    if (!EVP_EncryptUpdate(ctx, result->ciphertext, &len, plaintext, plaintext_len)) {
        EVP_CIPHER_CTX_free(ctx);
        free(result->ciphertext);
        free(result);
        handle_errors();
    }
    result->ciphertext_len = len;

    if (!EVP_EncryptFinal_ex(ctx, result->ciphertext + len, &len)) {
        EVP_CIPHER_CTX_free(ctx);
        free(result->ciphertext);
        free(result);
        handle_errors();
    }
    result->ciphertext_len += len;

    // Get the tag (but ignore it)
    unsigned char tag[16];
    if (!EVP_CIPHER_CTX_get_tag(ctx, tag, sizeof(tag))) {
        EVP_CIPHER_CTX_free(ctx);
        free(result->ciphertext);
        free(result);
        handle_errors();
    }

    EVP_CIPHER_CTX_free(ctx);
    return result;
}

// Decrypt function: takes ciphertext and nonce, ignores tag
unsigned char* decrypt(EncryptedData *data, const unsigned char *key, size_t key_len, size_t *plaintext_len) {
    EVP_CIPHER_CTX *ctx;
    unsigned char *plaintext;
    int len;

    // Allocate space for plaintext
    plaintext = (unsigned char*)malloc(data->ciphertext_len + EVP_CIPHER_block_size(EVP_aes_128_eax()));
    if (!plaintext) handle_errors();

    // Create and initialize the cipher context
    ctx = EVP_CIPHER_CTX_new();
    if (!ctx) {
        free(plaintext);
        handle_errors();
    }

    // Select cipher based on key length
    const EVP_CIPHER *cipher;
    if (key_len == 16) {
        cipher = EVP_aes_128_eax();
    } else if (key_len == 24) {
        cipher = EVP_aes_192_eax();
    } else if (key_len == 32) {
        cipher = EVP_aes_256_eax();
    } else {
        EVP_CIPHER_CTX_free(ctx);
        free(plaintext);
        return NULL;
    }

    // Initialize decryption
    if (!EVP_DecryptInit_ex(ctx, cipher, NULL, NULL, NULL)) {
        EVP_CIPHER_CTX_free(ctx);
        free(plaintext);
        handle_errors();
    }

    // Set nonce
    if (!EVP_CIPHER_CTX_set_iv_length(ctx, 16) ||
        !EVP_DecryptInit_ex(ctx, NULL, NULL, key, data->nonce)) {
        EVP_CIPHER_CTX_free(ctx);
        free(plaintext);
        handle_errors();
    }

    // Decrypt the ciphertext
    if (!EVP_DecryptUpdate(ctx, plaintext, &len, data->ciphertext, data->ciphertext_len)) {
        EVP_CIPHER_CTX_free(ctx);
        free(plaintext);
        handle_errors();
    }

    // Finalize decryption (this will fail because the tag is not set)
    if (!EVP_DecryptFinal_ex(ctx, plaintext + len, &len)) {
        EVP_CIPHER_CTX_free(ctx);
        free(plaintext);
        handle_errors();
    }

    EVP_CIPHER_CTX_free(ctx);
    *plaintext_len = len;
    return plaintext;
}