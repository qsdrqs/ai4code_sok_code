#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/evp.h>
#include <openssl/rand.h>

#define KEY_SIZE 32  // 256-bit key
#define IV_SIZE 16   // 128-bit IV

// Function to encrypt data
unsigned char* encrypt(const unsigned char* plaintext, int plaintext_len, const unsigned char* key, const unsigned char* iv, int* ciphertext_len) {
    EVP_CIPHER_CTX* ctx = EVP_CIPHER_CTX_new();
    if (!ctx) {
        fprintf(stderr, "Error initializing cipher context\n");
        return NULL;
    }

    if (EVP_EncryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv) != 1) {
        fprintf(stderr, "Error initializing encryption\n");
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }

    unsigned char* ciphertext = (unsigned char*)malloc(plaintext_len + EVP_CIPHER_block_size(EVP_aes_256_cbc()));
    if (!ciphertext) {
        fprintf(stderr, "Memory allocation error\n");
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }

    int len;
    if (EVP_EncryptUpdate(ctx, ciphertext, &len, plaintext, plaintext_len) != 1) {
        fprintf(stderr, "Error during encryption\n");
        free(ciphertext);
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }
    *ciphertext_len = len;

    if (EVP_EncryptFinal_ex(ctx, ciphertext + len, &len) != 1) {
        fprintf(stderr, "Error finalizing encryption\n");
        free(ciphertext);
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }
    *ciphertext_len += len;

    EVP_CIPHER_CTX_free(ctx);
    return ciphertext;
}

// Function to decrypt data
unsigned char* decrypt(const unsigned char* ciphertext, int ciphertext_len, const unsigned char* key, const unsigned char* iv, int* plaintext_len) {
    EVP_CIPHER_CTX* ctx = EVP_CIPHER_CTX_new();
    if (!ctx) {
        fprintf(stderr, "Error initializing cipher context\n");
        return NULL;
    }

    if (EVP_DecryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv) != 1) {
        fprintf(stderr, "Error initializing decryption\n");
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }

    unsigned char* plaintext = (unsigned char*)malloc(ciphertext_len);
    if (!plaintext) {
        fprintf(stderr, "Memory allocation error\n");
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }

    int len;
    if (EVP_DecryptUpdate(ctx, plaintext, &len, ciphertext, ciphertext_len) != 1) {
        fprintf(stderr, "Error during decryption\n");
        free(plaintext);
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }
    *plaintext_len = len;

    if (EVP_DecryptFinal_ex(ctx, plaintext + len, &len) != 1) {
        fprintf(stderr, "Error finalizing decryption\n");
        free(plaintext);
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }
    *plaintext_len += len;

    EVP_CIPHER_CTX_free(ctx);
    return plaintext;
}

// Main function
int main() {
    // Generate a random key and IV
    unsigned char key[KEY_SIZE];
    unsigned char iv[IV_SIZE];
    if (!RAND_bytes(key, KEY_SIZE) || !RAND_bytes(iv, IV_SIZE)) {
        fprintf(stderr, "Error generating random key/IV\n");
        return 1;
    }

    // Example plaintext
    const char* plaintext = "/safedir/test.txt";
    int plaintext_len = strlen(plaintext);

    // Encrypt the plaintext
    int ciphertext_len;
    unsigned char* ciphertext = encrypt((unsigned char*)plaintext, plaintext_len, key, iv, &ciphertext_len);
    if (!ciphertext) {
        fprintf(stderr, "Encryption failed\n");
        return 1;
    }

    printf("Encryption successful. Ciphertext length: %d\n", ciphertext_len);

    // Decrypt the ciphertext
    int decrypted_len;
    unsigned char* decrypted = decrypt(ciphertext, ciphertext_len, key, iv, &decrypted_len);
    if (!decrypted) {
        fprintf(stderr, "Decryption failed\n");
        free(ciphertext);
        return 1;
    }

    printf("Decryption successful. Decrypted text: %.*s\n", decrypted_len, decrypted);

    // Free allocated memory
    free(ciphertext);
    free(decrypted);

    return 0;
}