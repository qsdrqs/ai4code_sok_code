#include <openssl/evp.h>
#include <openssl/err.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

// Placeholder for error handling
void handleErrors(void) {
    fprintf(stderr, "An error occurred during encryption/decryption.\n");
    exit(EXIT_FAILURE);
}

/**
 * Encrypts a plaintext message using AES-128 in CFB8 mode.
 *
 * @param plaintext     Pointer to the input message (binary data)
 * @param plaintext_len Length of the plaintext in bytes
 * @param key           16-byte AES key
 * @param iv            16-byte Initialization Vector
 * @param ciphertext    Output buffer for the encrypted data
 */
void encrypt_string(unsigned char *plaintext, int plaintext_len,
                    unsigned char *key, unsigned char *iv,
                    unsigned char *ciphertext) {
    EVP_CIPHER_CTX *ctx;
    int len;

    // Create and initialize the context
    if (!(ctx = EVP_CIPHER_CTX_new()))
        handleErrors();

    // Initialize encryption with AES-128-CFB8
    if (1 != EVP_EncryptInit_ex(ctx, EVP_aes_128_cfb8(), NULL, key, iv))
        handleErrors();

    // Disable padding
    EVP_CIPHER_CTX_set_padding(ctx, 0);

    // Encrypt the message
    if (1 != EVP_EncryptUpdate(ctx, ciphertext, &len, plaintext, plaintext_len))
        handleErrors();

    // Finalize encryption
    if (1 != EVP_EncryptFinal_ex(ctx, ciphertext + len, &len))
        handleErrors();

    // Clean up
    EVP_CIPHER_CTX_free(ctx);
}

/**
 * Decrypts a ciphertext using AES-128 in CFB8 mode.
 *
 * @param ciphertext     Pointer to the encrypted data
 * @param ciphertext_len Length of the ciphertext in bytes
 * @param key            16-byte AES key
 * @param iv             16-byte Initialization Vector
 * @param plaintext      Output buffer for the decrypted data
 */
void decrypt_string(unsigned char *ciphertext, int ciphertext_len,
                    unsigned char *key, unsigned char *iv,
                    unsigned char *plaintext) {
    EVP_CIPHER_CTX *ctx;
    int len;

    // Create and initialize the context
    if (!(ctx = EVP_CIPHER_CTX_new()))
        handleErrors();

    // Initialize decryption with AES-128-CFB8
    if (1 != EVP_DecryptInit_ex(ctx, EVP_aes_128_cfb8(), NULL, key, iv))
        handleErrors();

    // Disable padding
    EVP_CIPHER_CTX_set_padding(ctx, 0);

    // Decrypt the message
    if (1 != EVP_DecryptUpdate(ctx, plaintext, &len, ciphertext, ciphertext_len))
        handleErrors();

    // Finalize decryption
    if (1 != EVP_DecryptFinal_ex(ctx, plaintext + len, &len))
        handleErrors();

    // Clean up
    EVP_CIPHER_CTX_free(ctx);
}

int main() {
    // AES key and IV (16 bytes each)
    unsigned char key[] = "This is a key123";
    unsigned char iv[]  = "This is an IV456";

    // Message to encrypt (16 bytes)
    unsigned char message[] = "The answer is no";
    int message_len = strlen((char *)message);  // 16 bytes

    // Buffers for ciphertext and decrypted text
    unsigned char ciphertext[128];
    unsigned char decryptedtext[128];

    // Encrypt the message
    encrypt_string(message, message_len, key, iv, ciphertext);

    // Decrypt the ciphertext
    decrypt_string(ciphertext, message_len, key, iv, decryptedtext);

    // Null-terminate the decrypted text for printing
    decryptedtext[message_len] = '\0';

    // Output results
    printf("Original message: %s\n", message);
    printf("Decrypted text:   %s\n", decryptedtext);

    // Verify correctness
    if (strncmp((char *)message, (char *)decryptedtext, message_len) == 0) {
        printf("✅ Decryption successful.\n");
    } else {
        printf("❌ Decryption failed.\n");
    }

    return 0;
}