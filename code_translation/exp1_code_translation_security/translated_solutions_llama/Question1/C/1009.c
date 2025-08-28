#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/aes.h>
#include <openssl/rand.h>
#include <openssl/err.h>

// Function to handle OpenSSL errors
void handleError(const char* message) {
    unsigned long errCode;
    char errString[256];

    while ((errCode = ERR_get_error()) != 0) {
        ERR_error_string_n(errCode, errString, 256);
        fprintf(stderr, "%s: %s\n", message, errString);
    }
    exit(1);
}

// Function to encode a string using AES
void encodeString(const char* message, const unsigned char* key, const unsigned char* nonce,
                  unsigned char** ciphertext, unsigned char** tag, size_t* ciphertextLen) {
    AES_KEY aesKey;
    EVP_CIPHER_CTX* ctx = NULL;

    // Initialize AES context
    if (AES_set_encrypt_key(key, 128, &aesKey) != 0) {
        handleError("Failed to set AES encrypt key");
    }

    // Create a new EVP_CIPHER_CTX
    ctx = EVP_CIPHER_CTX_new();
    if (ctx == NULL) {
        handleError("Failed to create EVP_CIPHER_CTX");
    }

    // Initialize EVP_CIPHER_CTX for AES-EAX
    if (EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, NULL, NULL) != 1) {
        handleError("Failed to initialize EVP_CIPHER_CTX");
    }

    // Set nonce
    if (EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_AEAD_SET_IVLEN, 16, NULL) != 1) {
        handleError("Failed to set IV length");
    }

    if (EVP_EncryptInit_ex(ctx, NULL, NULL, key, nonce) != 1) {
        handleError("Failed to initialize EVP_CIPHER_CTX with key and nonce");
    }

    // Calculate required buffer size
    *ciphertextLen = strlen(message) + EVP_MAX_BLOCK_LENGTH;
    *ciphertext = (unsigned char*)malloc(*ciphertextLen);
    *tag = (unsigned char*)malloc(16);

    // Encrypt and get tag
    int len;
    if (EVP_EncryptUpdate(ctx, *ciphertext, &len, (const unsigned char*)message, strlen(message)) != 1) {
        handleError("Failed to encrypt message");
    }
    if (EVP_EncryptFinal_ex(ctx, *ciphertext + len, &len) != 1) {
        handleError("Failed to finalize encryption");
    }
    if (EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_AEAD_GET_TAG, 16, *tag) != 1) {
        handleError("Failed to get tag");
    }

    // Clean up
    EVP_CIPHER_CTX_free(ctx);
}

// Function to decode a string using AES
char* decodeString(const unsigned char* ciphertext, size_t ciphertextLen, const unsigned char* tag,
                   const unsigned char* key, const unsigned char* nonce) {
    AES_KEY aesKey;
    EVP_CIPHER_CTX* ctx = NULL;

    // Initialize AES context
    if (AES_set_decrypt_key(key, 128, &aesKey) != 0) {
        handleError("Failed to set AES decrypt key");
    }

    // Create a new EVP_CIPHER_CTX
    ctx = EVP_CIPHER_CTX_new();
    if (ctx == NULL) {
        handleError("Failed to create EVP_CIPHER_CTX");
    }

    // Initialize EVP_CIPHER_CTX for AES-EAX
    if (EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, NULL, NULL) != 1) {
        handleError("Failed to initialize EVP_CIPHER_CTX");
    }

    // Set nonce
    if (EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_AEAD_SET_IVLEN, 16, NULL) != 1) {
        handleError("Failed to set IV length");
    }

    if (EVP_DecryptInit_ex(ctx, NULL, NULL, key, nonce) != 1) {
        handleError("Failed to initialize EVP_CIPHER_CTX with key and nonce");
    }

    // Set tag
    if (EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_AEAD_SET_TAG, 16, (void*)tag) != 1) {
        handleError("Failed to set tag");
    }

    // Decrypt
    char* plaintext = (char*)malloc(ciphertextLen + 1);
    int len;
    if (EVP_DecryptUpdate(ctx, (unsigned char*)plaintext, &len, ciphertext, ciphertextLen) != 1) {
        handleError("Failed to decrypt message");
    }
    if (EVP_DecryptFinal_ex(ctx, (unsigned char*)plaintext + len, &len) != 1) {
        handleError("Failed to finalize decryption");
    }

    // Clean up
    EVP_CIPHER_CTX_free(ctx);

    // Null-terminate the plaintext
    plaintext[len] = '\0';

    return plaintext;
}

int main() {
    const char* originalMessage = "secret data";
    unsigned char key[16];
    unsigned char nonce[16];

    // Generate random key and nonce
    if (RAND_bytes(key, 16) != 1) {
        handleError("Failed to generate random key");
    }
    if (RAND_bytes(nonce, 16) != 1) {
        handleError("Failed to generate random nonce");
    }

    printf("Original Message: %s\n", originalMessage);

    // Encode string
    unsigned char* ciphertext;
    unsigned char* tag;
    size_t ciphertextLen;
    encodeString(originalMessage, key, nonce, &ciphertext, &tag, &ciphertextLen);

    // Decode string
    char* decodedString = decodeString(ciphertext, ciphertextLen, tag, key, nonce);

    printf("Decoded String: %s\n", decodedString);

    // Free allocated memory
    free(ciphertext);
    free(tag);
    free(decodedString);

    return 0;
}