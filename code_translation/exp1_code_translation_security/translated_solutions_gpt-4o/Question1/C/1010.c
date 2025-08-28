#include <openssl/evp.h>
#include <openssl/rand.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define AES_KEYLEN 16  // AES key length in bytes
#define AES_BLOCK_SIZE 16

// Function to generate a symmetric key
void generate_key(unsigned char *key) {
    // Generate a random key
    if (!RAND_bytes(key, AES_KEYLEN)) {
        fprintf(stderr, "Error generating random bytes for key\n");
        exit(EXIT_FAILURE);
    }
}

// Function to encrypt a string using a symmetric key
int encrypt(unsigned char *key, unsigned char *data, int data_len, unsigned char *ciphertext, unsigned char *nonce) {
    EVP_CIPHER_CTX *ctx;
    int len;
    int ciphertext_len;

    // Create and initialize the context
    if (!(ctx = EVP_CIPHER_CTX_new())) {
        fprintf(stderr, "Error creating cipher context\n");
        return -1;
    }

    // Initialize the encryption operation with AES in EAX mode
    if (1 != EVP_EncryptInit_ex(ctx, EVP_aes_128_eax(), NULL, NULL, NULL)) {
        fprintf(stderr, "Error initializing encryption\n");
        return -1;
    }

    // Generate a random nonce
    if (!RAND_bytes(nonce, AES_BLOCK_SIZE)) {
        fprintf(stderr, "Error generating random bytes for nonce\n");
        return -1;
    }

    // Set the nonce
    if (1 != EVP_EncryptInit_ex(ctx, NULL, NULL, key, nonce)) {
        fprintf(stderr, "Error setting nonce\n");
        return -1;
    }

    // Provide the message to be encrypted, and obtain the encrypted output
    if (1 != EVP_EncryptUpdate(ctx, ciphertext, &len, data, data_len)) {
        fprintf(stderr, "Error during encryption\n");
        return -1;
    }
    ciphertext_len = len;

    // Finalize the encryption
    if (1 != EVP_EncryptFinal_ex(ctx, ciphertext + len, &len)) {
        fprintf(stderr, "Error finalizing encryption\n");
        return -1;
    }
    ciphertext_len += len;

    // Clean up
    EVP_CIPHER_CTX_free(ctx);

    return ciphertext_len;
}

// Function to decrypt a string
int decrypt(unsigned char *key, unsigned char *nonce, unsigned char *ciphertext, int ciphertext_len, unsigned char *plaintext) {
    EVP_CIPHER_CTX *ctx;
    int len;
    int plaintext_len;

    // Create and initialize the context
    if (!(ctx = EVP_CIPHER_CTX_new())) {
        fprintf(stderr, "Error creating cipher context\n");
        return -1;
    }

    // Initialize the decryption operation with AES in EAX mode
    if (1 != EVP_DecryptInit_ex(ctx, EVP_aes_128_eax(), NULL, NULL, NULL)) {
        fprintf(stderr, "Error initializing decryption\n");
        return -1;
    }

    // Set the nonce
    if (1 != EVP_DecryptInit_ex(ctx, NULL, NULL, key, nonce)) {
        fprintf(stderr, "Error setting nonce\n");
        return -1;
    }

    // Provide the message to be decrypted, and obtain the plaintext output
    if (1 != EVP_DecryptUpdate(ctx, plaintext, &len, ciphertext, ciphertext_len)) {
        fprintf(stderr, "Error during decryption\n");
        return -1;
    }
    plaintext_len = len;

    // Finalize the decryption
    if (1 != EVP_DecryptFinal_ex(ctx, plaintext + len, &len)) {
        fprintf(stderr, "Error finalizing decryption\n");
        return -1;
    }
    plaintext_len += len;

    // Clean up
    EVP_CIPHER_CTX_free(ctx);

    return plaintext_len;
}

int main() {
    unsigned char key[AES_KEYLEN];
    unsigned char nonce[AES_BLOCK_SIZE];
    unsigned char *test_data = (unsigned char *)"Jim's test";
    unsigned char ciphertext[128];
    unsigned char decryptedtext[128];
    int decryptedtext_len, ciphertext_len;

    // Generate a key
    generate_key(key);

    // Encrypt the data
    ciphertext_len = encrypt(key, test_data, strlen((char *)test_data), ciphertext, nonce);

    // Decrypt the data
    decryptedtext_len = decrypt(key, nonce, ciphertext, ciphertext_len, decryptedtext);

    // Add a NULL terminator to the decrypted text
    decryptedtext[decryptedtext_len] = '\0';

    // Print the decrypted text
    printf("Decrypted text: %s\n", decryptedtext);

    return 0;
}