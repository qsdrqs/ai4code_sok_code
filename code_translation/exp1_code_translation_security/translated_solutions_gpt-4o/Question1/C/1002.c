#include <openssl/evp.h>
#include <openssl/rand.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

// Function to encrypt data
unsigned char* encrypt(const unsigned char* data, int data_len, const unsigned char* key, const unsigned char* iv, int* out_len) {
    EVP_CIPHER_CTX* ctx = EVP_CIPHER_CTX_new();
    if (!ctx) {
        fprintf(stderr, "Failed to create cipher context\n");
        exit(EXIT_FAILURE);
    }

    if (EVP_EncryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv) != 1) {
        fprintf(stderr, "Failed to initialize encryption\n");
        EVP_CIPHER_CTX_free(ctx);
        exit(EXIT_FAILURE);
    }

    int block_size = EVP_CIPHER_block_size(EVP_aes_256_cbc());
    int ciphertext_len = data_len + block_size;
    unsigned char* ciphertext = (unsigned char*)malloc(ciphertext_len);
    if (!ciphertext) {
        fprintf(stderr, "Failed to allocate memory for ciphertext\n");
        EVP_CIPHER_CTX_free(ctx);
        exit(EXIT_FAILURE);
    }

    int len;
    if (EVP_EncryptUpdate(ctx, ciphertext, &len, data, data_len) != 1) {
        fprintf(stderr, "Failed to encrypt data\n");
        free(ciphertext);
        EVP_CIPHER_CTX_free(ctx);
        exit(EXIT_FAILURE);
    }
    *out_len = len;

    if (EVP_EncryptFinal_ex(ctx, ciphertext + len, &len) != 1) {
        fprintf(stderr, "Failed to finalize encryption\n");
        free(ciphertext);
        EVP_CIPHER_CTX_free(ctx);
        exit(EXIT_FAILURE);
    }
    *out_len += len;

    EVP_CIPHER_CTX_free(ctx);
    return ciphertext;
}

// Function to decrypt data
unsigned char* decrypt(const unsigned char* data, int data_len, const unsigned char* key, const unsigned char* iv, int* out_len) {
    EVP_CIPHER_CTX* ctx = EVP_CIPHER_CTX_new();
    if (!ctx) {
        fprintf(stderr, "Failed to create cipher context\n");
        exit(EXIT_FAILURE);
    }

    if (EVP_DecryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv) != 1) {
        fprintf(stderr, "Failed to initialize decryption\n");
        EVP_CIPHER_CTX_free(ctx);
        exit(EXIT_FAILURE);
    }

    int plaintext_len = data_len;
    unsigned char* plaintext = (unsigned char*)malloc(plaintext_len);
    if (!plaintext) {
        fprintf(stderr, "Failed to allocate memory for plaintext\n");
        EVP_CIPHER_CTX_free(ctx);
        exit(EXIT_FAILURE);
    }

    int len;
    if (EVP_DecryptUpdate(ctx, plaintext, &len, data, data_len) != 1) {
        fprintf(stderr, "Failed to decrypt data\n");
        free(plaintext);
        EVP_CIPHER_CTX_free(ctx);
        exit(EXIT_FAILURE);
    }
    *out_len = len;

    if (EVP_DecryptFinal_ex(ctx, plaintext + len, &len) != 1) {
        fprintf(stderr, "Failed to finalize decryption\n");
        free(plaintext);
        EVP_CIPHER_CTX_free(ctx);
        exit(EXIT_FAILURE);
    }
    *out_len += len;

    EVP_CIPHER_CTX_free(ctx);
    return plaintext;
}

// Test function
void test() {
    const char* data = "a secret message";
    int data_len = strlen(data);

    unsigned char key[32];
    unsigned char iv[16];

    if (RAND_bytes(key, sizeof(key)) != 1 || RAND_bytes(iv, sizeof(iv)) != 1) {
        fprintf(stderr, "Failed to generate random key or IV\n");
        exit(EXIT_FAILURE);
    }

    int encrypted_len;
    unsigned char* encrypted = encrypt((const unsigned char*)data, data_len, key, iv, &encrypted_len);

    int decrypted_len;
    unsigned char* decrypted = decrypt(encrypted, encrypted_len, key, iv, &decrypted_len);

    // Ensure the decrypted data matches the original
    assert(decrypted_len == data_len);
    assert(memcmp(data, decrypted, data_len) == 0);

    printf("Encryption and decryption successful!\n");

    free(encrypted);
    free(decrypted);
}

int main() {
    test();
    return 0;
}