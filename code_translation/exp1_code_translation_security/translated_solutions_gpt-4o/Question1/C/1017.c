#include <openssl/evp.h>
#include <openssl/rand.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

// Function to encrypt plaintext using AES in EAX mode
int encrypt(const unsigned char *key, const unsigned char *plaintext, int plaintext_len,
            unsigned char **nonce, unsigned char **tag, unsigned char **ciphertext) {
    EVP_CIPHER_CTX *ctx;
    int len;
    int ciphertext_len;

    // Allocate memory for nonce, tag, and ciphertext
    *nonce = (unsigned char *)malloc(16); // 16 bytes for nonce
    *tag = (unsigned char *)malloc(16);   // 16 bytes for tag
    *ciphertext = (unsigned char *)malloc(plaintext_len + 16); // Allocate enough space for ciphertext

    // Create and initialize the context
    if (!(ctx = EVP_CIPHER_CTX_new())) return -1;

    // Generate a random nonce
    if (RAND_bytes(*nonce, 16) != 1) return -1;

    // Initialize the encryption operation
    if (1 != EVP_EncryptInit_ex(ctx, EVP_aes_256_eax(), NULL, NULL, NULL)) return -1;

    // Set the nonce
    if (1 != EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_AEAD_SET_IVLEN, 16, NULL)) return -1;
    if (1 != EVP_EncryptInit_ex(ctx, NULL, NULL, key, *nonce)) return -1;

    // Provide the message to be encrypted, and obtain the encrypted output
    if (1 != EVP_EncryptUpdate(ctx, *ciphertext, &len, plaintext, plaintext_len)) return -1;
    ciphertext_len = len;

    // Finalize the encryption
    if (1 != EVP_EncryptFinal_ex(ctx, *ciphertext + len, &len)) return -1;
    ciphertext_len += len;

    // Get the tag
    if (1 != EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_AEAD_GET_TAG, 16, *tag)) return -1;

    // Clean up
    EVP_CIPHER_CTX_free(ctx);

    return ciphertext_len;
}

// Function to decrypt ciphertext using AES in EAX mode
int decrypt(const unsigned char *key, const unsigned char *nonce, const unsigned char *tag,
            const unsigned char *ciphertext, int ciphertext_len, unsigned char **plaintext) {
    EVP_CIPHER_CTX *ctx;
    int len;
    int plaintext_len;
    int ret;

    // Allocate memory for plaintext
    *plaintext = (unsigned char *)malloc(ciphertext_len);

    // Create and initialize the context
    if (!(ctx = EVP_CIPHER_CTX_new())) return -1;

    // Initialize the decryption operation
    if (!EVP_DecryptInit_ex(ctx, EVP_aes_256_eax(), NULL, NULL, NULL)) return -1;

    // Set the nonce
    if (!EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_AEAD_SET_IVLEN, 16, NULL)) return -1;
    if (!EVP_DecryptInit_ex(ctx, NULL, NULL, key, nonce)) return -1;

    // Provide the message to be decrypted, and obtain the plaintext output
    if (!EVP_DecryptUpdate(ctx, *plaintext, &len, ciphertext, ciphertext_len)) return -1;
    plaintext_len = len;

    // Set expected tag value
    if (!EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_AEAD_SET_TAG, 16, (void *)tag)) return -1;

    // Finalize the decryption
    ret = EVP_DecryptFinal_ex(ctx, *plaintext + len, &len);

    // Clean up
    EVP_CIPHER_CTX_free(ctx);

    if (ret > 0) {
        // Success
        plaintext_len += len;
        return plaintext_len;
    } else {
        // Verification failed
        free(*plaintext);
        return -1;
    }
}

int main() {
    // Example usage
    unsigned char *key = (unsigned char *)"01234567890123456789012345678901"; // 32 bytes key for AES-256
    unsigned char *plaintext = (unsigned char *)"Hello, World!";
    unsigned char *nonce, *tag, *ciphertext, *decryptedtext;
    int ciphertext_len, decryptedtext_len;

    // Encrypt the plaintext
    ciphertext_len = encrypt(key, plaintext, strlen((char *)plaintext), &nonce, &tag, &ciphertext);

    if (ciphertext_len < 0) {
        fprintf(stderr, "Encryption failed\n");
        return 1;
    }

    // Decrypt the ciphertext
    decryptedtext_len = decrypt(key, nonce, tag, ciphertext, ciphertext_len, &decryptedtext);

    if (decryptedtext_len < 0) {
        fprintf(stderr, "Decryption failed\n");
        return 1;
    }

    // Add a NULL terminator to the decrypted text
    decryptedtext[decryptedtext_len] = '\0';

    // Print the decrypted text
    printf("Decrypted text is: %s\n", decryptedtext);

    // Free allocated memory
    free(nonce);
    free(tag);
    free(ciphertext);
    free(decryptedtext);

    return 0;
}