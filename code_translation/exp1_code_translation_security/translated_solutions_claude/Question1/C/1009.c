#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/evp.h>
#include <openssl/aes.h>
#include <openssl/rand.h>
#include <openssl/err.h>

// Structure to hold ciphertext and tag
typedef struct {
    unsigned char* ciphertext;
    int ciphertext_len;
    unsigned char tag[16];
} EncryptedData;

// Function to generate random bytes
void get_random_bytes(unsigned char* buffer, int length) {
    if (RAND_bytes(buffer, length) != 1) {
        fprintf(stderr, "Error generating random bytes\n");
        exit(1);
    }
}

// Function to encode string (encrypt)
EncryptedData* encodeString(const char* message, const unsigned char* key, const unsigned char* nonce) {
    EVP_CIPHER_CTX* ctx;
    int len;
    int ciphertext_len;
    unsigned char* ciphertext;
    unsigned char tag[16];
    
    // Allocate memory for result
    EncryptedData* result = malloc(sizeof(EncryptedData));
    if (!result) {
        fprintf(stderr, "Memory allocation failed\n");
        return NULL;
    }
    
    // Allocate memory for ciphertext (same size as plaintext should be sufficient for EAX mode)
    int message_len = strlen(message);
    ciphertext = malloc(message_len + 16); // Extra space just in case
    if (!ciphertext) {
        fprintf(stderr, "Memory allocation failed\n");
        free(result);
        return NULL;
    }
    
    // Create and initialize the context
    if (!(ctx = EVP_CIPHER_CTX_new())) {
        fprintf(stderr, "Error creating cipher context\n");
        free(ciphertext);
        free(result);
        return NULL;
    }
    
    // Initialize the encryption operation with AES-128-EAX
    if (1 != EVP_EncryptInit_ex(ctx, EVP_aes_128_eax(), NULL, NULL, NULL)) {
        fprintf(stderr, "Error initializing encryption\n");
        EVP_CIPHER_CTX_free(ctx);
        free(ciphertext);
        free(result);
        return NULL;
    }
    
    // Set nonce length (16 bytes for this example)
    if (1 != EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_AEAD_SET_IVLEN, 16, NULL)) {
        fprintf(stderr, "Error setting nonce length\n");
        EVP_CIPHER_CTX_free(ctx);
        free(ciphertext);
        free(result);
        return NULL;
    }
    
    // Initialize key and nonce
    if (1 != EVP_EncryptInit_ex(ctx, NULL, NULL, key, nonce)) {
        fprintf(stderr, "Error setting key and nonce\n");
        EVP_CIPHER_CTX_free(ctx);
        free(ciphertext);
        free(result);
        return NULL;
    }
    
    // Encrypt the message
    if (1 != EVP_EncryptUpdate(ctx, ciphertext, &len, (unsigned char*)message, message_len)) {
        fprintf(stderr, "Error during encryption\n");
        EVP_CIPHER_CTX_free(ctx);
        free(ciphertext);
        free(result);
        return NULL;
    }
    ciphertext_len = len;
    
    // Finalize the encryption
    if (1 != EVP_EncryptFinal_ex(ctx, ciphertext + len, &len)) {
        fprintf(stderr, "Error finalizing encryption\n");
        EVP_CIPHER_CTX_free(ctx);
        free(ciphertext);
        free(result);
        return NULL;
    }
    ciphertext_len += len;
    
    // Get the authentication tag
    if (1 != EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_AEAD_GET_TAG, 16, tag)) {
        fprintf(stderr, "Error getting authentication tag\n");
        EVP_CIPHER_CTX_free(ctx);
        free(ciphertext);
        free(result);
        return NULL;
    }
    
    // Clean up
    EVP_CIPHER_CTX_free(ctx);
    
    // Fill result structure
    result->ciphertext = ciphertext;
    result->ciphertext_len = ciphertext_len;
    memcpy(result->tag, tag, 16);
    
    return result;
}

// Function to decode string (decrypt)
char* decodeString(EncryptedData* encrypted_data, const unsigned char* key, const unsigned char* nonce) {
    EVP_CIPHER_CTX* ctx;
    int len;
    int plaintext_len;
    unsigned char* plaintext;
    
    // Allocate memory for plaintext
    plaintext = malloc(encrypted_data->ciphertext_len + 1); // +1 for null terminator
    if (!plaintext) {
        fprintf(stderr, "Memory allocation failed\n");
        return NULL;
    }
    
    // Create and initialize the context
    if (!(ctx = EVP_CIPHER_CTX_new())) {
        fprintf(stderr, "Error creating cipher context\n");
        free(plaintext);
        return NULL;
    }
    
    // Initialize the decryption operation
    if (1 != EVP_DecryptInit_ex(ctx, EVP_aes_128_eax(), NULL, NULL, NULL)) {
        fprintf(stderr, "Error initializing decryption\n");
        EVP_CIPHER_CTX_free(ctx);
        free(plaintext);
        return NULL;
    }
    
    // Set nonce length
    if (1 != EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_AEAD_SET_IVLEN, 16, NULL)) {
        fprintf(stderr, "Error setting nonce length\n");
        EVP_CIPHER_CTX_free(ctx);
        free(plaintext);
        return NULL;
    }
    
    // Initialize key and nonce
    if (1 != EVP_DecryptInit_ex(ctx, NULL, NULL, key, nonce)) {
        fprintf(stderr, "Error setting key and nonce\n");
        EVP_CIPHER_CTX_free(ctx);
        free(plaintext);
        return NULL;
    }
    
    // Decrypt the message
    if (1 != EVP_DecryptUpdate(ctx, plaintext, &len, encrypted_data->ciphertext, encrypted_data->ciphertext_len)) {
        fprintf(stderr, "Error during decryption\n");
        EVP_CIPHER_CTX_free(ctx);
        free(plaintext);
        return NULL;
    }
    plaintext_len = len;
    
    // Set expected tag value
    if (1 != EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_AEAD_SET_TAG, 16, encrypted_data->tag)) {
        fprintf(stderr, "Error setting authentication tag\n");
        EVP_CIPHER_CTX_free(ctx);
        free(plaintext);
        return NULL;
    }
    
    // Finalize the decryption and verify the tag
    int ret = EVP_DecryptFinal_ex(ctx, plaintext + len, &len);
    if (ret <= 0) {
        fprintf(stderr, "Error: Authentication verification failed\n");
        EVP_CIPHER_CTX_free(ctx);
        free(plaintext);
        return NULL;
    }
    plaintext_len += len;
    
    // Clean up
    EVP_CIPHER_CTX_free(ctx);
    
    // Null terminate the string
    plaintext[plaintext_len] = '\0';
    
    return (char*)plaintext;
}

// Function to free EncryptedData structure
void freeEncryptedData(EncryptedData* data) {
    if (data) {
        if (data->ciphertext) {
            free(data->ciphertext);
        }
        free(data);
    }
}

int main() {
    // Initialize OpenSSL
    OpenSSL_add_all_algorithms();
    ERR_load_crypto_strings();
    
    const char* originalMessage = "secret data";
    unsigned char nonce[16];
    unsigned char key[16];
    
    // Generate random nonce and key
    get_random_bytes(nonce, 16);
    get_random_bytes(key, 16);
    
    printf("%s\n", originalMessage);
    
    // Encrypt the message
    EncryptedData* ciphertext = encodeString(originalMessage, key, nonce);
    if (!ciphertext) {
        fprintf(stderr, "Encryption failed\n");
        return 1;
    }
    
    // Decrypt the message
    char* decodedString = decodeString(ciphertext, key, nonce);
    if (!decodedString) {
        fprintf(stderr, "Decryption failed\n");
        freeEncryptedData(ciphertext);
        return 1;
    }
    
    printf("%s\n", decodedString);
    
    // Clean up
    freeEncryptedData(ciphertext);
    free(decodedString);
    
    // Cleanup OpenSSL
    EVP_cleanup();
    ERR_free_strings();
    
    return 0;
}