#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/aes.h>
#include <openssl/err.h>

// Function to handle AES encryption
void encrypt(const unsigned char *key, const unsigned char *msg, int msg_len, 
             unsigned char **ciphertext, unsigned char **tag, unsigned char **nonce) {
    // Initialize AES context
    AES_KEY aes_key;
    if (AES_set_encrypt_key(key, 128, &aes_key) != 0) {
        fprintf(stderr, "AES_set_encrypt_key failed\n");
        exit(1);
    }

    // Generate random nonce (IV)
    *nonce = (unsigned char *)malloc(16);
    if (RAND_bytes(*nonce, 16) != 1) {
        fprintf(stderr, "RAND_bytes failed\n");
        exit(1);
    }

    // Initialize GCM context
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    if (EVP_EncryptInit_ex(ctx, EVP_aes_128_gcm(), NULL, key, *nonce) != 1) {
        fprintf(stderr, "EVP_EncryptInit_ex failed\n");
        EVP_CIPHER_CTX_free(ctx);
        exit(1);
    }

    // Allocate memory for ciphertext
    *ciphertext = (unsigned char *)malloc(msg_len + EVP_MAX_BLOCK_LENGTH);
    int ciphertext_len;

    // Encrypt message
    if (EVP_EncryptUpdate(ctx, *ciphertext, &ciphertext_len, msg, msg_len) != 1) {
        fprintf(stderr, "EVP_EncryptUpdate failed\n");
        EVP_CIPHER_CTX_free(ctx);
        free(*ciphertext);
        exit(1);
    }

    int final_len;
    if (EVP_EncryptFinal_ex(ctx, *ciphertext + ciphertext_len, &final_len) != 1) {
        fprintf(stderr, "EVP_EncryptFinal_ex failed\n");
        EVP_CIPHER_CTX_free(ctx);
        free(*ciphertext);
        exit(1);
    }

    // Get authentication tag
    *tag = (unsigned char *)malloc(16);
    if (EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_GET_TAG, 16, *tag) != 1) {
        fprintf(stderr, "EVP_CIPHER_CTX_ctrl failed\n");
        EVP_CIPHER_CTX_free(ctx);
        free(*ciphertext);
        free(*tag);
        exit(1);
    }

    EVP_CIPHER_CTX_free(ctx);
}

// Function to handle AES decryption
unsigned char *decrypt(const unsigned char *key, const unsigned char *ciphertext, int ciphertext_len, 
                       const unsigned char *nonce, const unsigned char *tag) {
    // Initialize AES context
    AES_KEY aes_key;
    if (AES_set_encrypt_key(key, 128, &aes_key) != 0) {
        fprintf(stderr, "AES_set_encrypt_key failed\n");
        exit(1);
    }

    // Initialize GCM context
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    if (EVP_DecryptInit_ex(ctx, EVP_aes_128_gcm(), NULL, key, nonce) != 1) {
        fprintf(stderr, "EVP_DecryptInit_ex failed\n");
        EVP_CIPHER_CTX_free(ctx);
        exit(1);
    }

    // Set authentication tag
    if (EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_SET_TAG, 16, (void *)tag) != 1) {
        fprintf(stderr, "EVP_CIPHER_CTX_ctrl failed\n");
        EVP_CIPHER_CTX_free(ctx);
        exit(1);
    }

    // Allocate memory for plaintext
    unsigned char *plaintext = (unsigned char *)malloc(ciphertext_len);
    int plaintext_len;

    // Decrypt ciphertext
    if (EVP_DecryptUpdate(ctx, plaintext, &plaintext_len, ciphertext, ciphertext_len) != 1) {
        fprintf(stderr, "EVP_DecryptUpdate failed\n");
        EVP_CIPHER_CTX_free(ctx);
        free(plaintext);
        exit(1);
    }

    int final_len;
    if (EVP_DecryptFinal_ex(ctx, plaintext + plaintext_len, &final_len) != 1) {
        fprintf(stderr, "EVP_DecryptFinal_ex failed\n");
        EVP_CIPHER_CTX_free(ctx);
        free(plaintext);
        exit(1);
    }

    EVP_CIPHER_CTX_free(ctx);
    return plaintext;
}

int main() {
    // Define key and message
    const unsigned char *key = (const unsigned char *)"Sixteen byte key";
    const unsigned char *msg = (const unsigned char *)"message";
    int msg_len = strlen((const char *)msg);

    // Encrypt message
    unsigned char *ciphertext;
    unsigned char *tag;
    unsigned char *nonce;
    encrypt(key, msg, msg_len, &ciphertext, &tag, &nonce);

    // Print decrypted message
    unsigned char *plaintext = decrypt(key, ciphertext, msg_len, nonce, tag);
    printf("%s\n", plaintext);

    // Free allocated memory
    free(ciphertext);
    free(tag);
    free(nonce);
    free(plaintext);

    return 0;
}