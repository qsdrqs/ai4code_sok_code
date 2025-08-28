#include <openssl/evp.h>
#include <openssl/rand.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

#define AES_KEYLEN 16  // AES key length in bytes (128 bits)
#define AES_BLOCK_SIZE 16

typedef struct {
    unsigned char *ciphertext;
    int ciphertext_len;
    unsigned char nonce[AES_BLOCK_SIZE];
} EncryptedData;

EncryptedData encrypt(const unsigned char *plaintext, int plaintext_len, const unsigned char *key) {
    EVP_CIPHER_CTX *ctx;
    int len;
    int ciphertext_len;
    unsigned char *ciphertext = (unsigned char *)malloc(plaintext_len + AES_BLOCK_SIZE);
    unsigned char nonce[AES_BLOCK_SIZE];

    // Create and initialize the context
    if (!(ctx = EVP_CIPHER_CTX_new())) {
        fprintf(stderr, "Error initializing cipher context\n");
        exit(EXIT_FAILURE);
    }

    // Generate a random nonce
    if (!RAND_bytes(nonce, sizeof(nonce))) {
        fprintf(stderr, "Error generating random nonce\n");
        exit(EXIT_FAILURE);
    }

    // Initialize the encryption operation
    if (1 != EVP_EncryptInit_ex(ctx, EVP_aes_128_eax(), NULL, key, nonce)) {
        fprintf(stderr, "Error initializing encryption\n");
        exit(EXIT_FAILURE);
    }

    // Provide the message to be encrypted, and obtain the encrypted output
    if (1 != EVP_EncryptUpdate(ctx, ciphertext, &len, plaintext, plaintext_len)) {
        fprintf(stderr, "Error during encryption\n");
        exit(EXIT_FAILURE);
    }
    ciphertext_len = len;

    // Finalize the encryption
    if (1 != EVP_EncryptFinal_ex(ctx, ciphertext + len, &len)) {
        fprintf(stderr, "Error finalizing encryption\n");
        exit(EXIT_FAILURE);
    }
    ciphertext_len += len;

    // Clean up
    EVP_CIPHER_CTX_free(ctx);

    EncryptedData result;
    result.ciphertext = ciphertext;
    result.ciphertext_len = ciphertext_len;
    memcpy(result.nonce, nonce, AES_BLOCK_SIZE);

    return result;
}

unsigned char *decrypt(const EncryptedData *encrypted_data, const unsigned char *key, int *plaintext_len) {
    EVP_CIPHER_CTX *ctx;
    int len;
    int plaintext_len_temp;
    unsigned char *plaintext = (unsigned char *)malloc(encrypted_data->ciphertext_len);

    // Create and initialize the context
    if (!(ctx = EVP_CIPHER_CTX_new())) {
        fprintf(stderr, "Error initializing cipher context\n");
        exit(EXIT_FAILURE);
    }

    // Initialize the decryption operation
    if (1 != EVP_DecryptInit_ex(ctx, EVP_aes_128_eax(), NULL, key, encrypted_data->nonce)) {
        fprintf(stderr, "Error initializing decryption\n");
        exit(EXIT_FAILURE);
    }

    // Provide the message to be decrypted, and obtain the plaintext output
    if (1 != EVP_DecryptUpdate(ctx, plaintext, &len, encrypted_data->ciphertext, encrypted_data->ciphertext_len)) {
        fprintf(stderr, "Error during decryption\n");
        exit(EXIT_FAILURE);
    }
    plaintext_len_temp = len;

    // Finalize the decryption
    if (1 != EVP_DecryptFinal_ex(ctx, plaintext + len, &len)) {
        fprintf(stderr, "Error finalizing decryption\n");
        exit(EXIT_FAILURE);
    }
    plaintext_len_temp += len;

    // Clean up
    EVP_CIPHER_CTX_free(ctx);

    *plaintext_len = plaintext_len_temp;
    return plaintext;
}

int main() {
    // Example usage
    const unsigned char key[AES_KEYLEN] = "thisisakey123456";  // 16 bytes key
    const unsigned char plaintext[] = "Hello, World!";
    int plaintext_len = strlen((const char *)plaintext);

    // Encrypt
    EncryptedData encrypted_data = encrypt(plaintext, plaintext_len, key);

    // Decrypt
    int decrypted_len;
    unsigned char *decrypted_text = decrypt(&encrypted_data, key, &decrypted_len);

    // Print decrypted text
    printf("Decrypted text: %.*s\n", decrypted_len, decrypted_text);

    // Free allocated memory
    free(encrypted_data.ciphertext);
    free(decrypted_text);

    return 0;
}