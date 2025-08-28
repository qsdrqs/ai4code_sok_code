#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/aes.h>
#include <openssl/rand.h>
#include <openssl/evp.h>

#define AES_BLOCK_SIZE 16

// PKCS7 padding function
unsigned char* pad(const unsigned char* data, int data_len, int block_size, int* padded_len) {
    int padding = block_size - (data_len % block_size);
    *padded_len = data_len + padding;
    
    unsigned char* padded_data = (unsigned char*)malloc(*padded_len);
    memcpy(padded_data, data, data_len);
    
    // Add padding bytes
    for (int i = data_len; i < *padded_len; i++) {
        padded_data[i] = padding;
    }
    
    return padded_data;
}

// PKCS7 unpadding function
unsigned char* unpad(const unsigned char* data, int data_len, int block_size, int* unpadded_len) {
    if (data_len == 0) return NULL;
    
    int padding = data[data_len - 1];
    
    // Validate padding
    if (padding > block_size || padding == 0) return NULL;
    
    for (int i = data_len - padding; i < data_len; i++) {
        if (data[i] != padding) return NULL;
    }
    
    *unpadded_len = data_len - padding;
    unsigned char* unpadded_data = (unsigned char*)malloc(*unpadded_len);
    memcpy(unpadded_data, data, *unpadded_len);
    
    return unpadded_data;
}

// Encryption function
unsigned char* encrypt(const char* message, const unsigned char* key, int* ciphertext_len) {
    int message_len = strlen(message);
    
    // Convert message to bytes (already done since we're working with char*)
    
    // Pad message
    int padded_len;
    unsigned char* padded_message = pad((unsigned char*)message, message_len, AES_BLOCK_SIZE, &padded_len);
    
    // Generate random IV
    unsigned char iv[AES_BLOCK_SIZE];
    if (RAND_bytes(iv, AES_BLOCK_SIZE) != 1) {
        free(padded_message);
        return NULL;
    }
    
    // Create cipher context
    EVP_CIPHER_CTX* ctx = EVP_CIPHER_CTX_new();
    if (!ctx) {
        free(padded_message);
        return NULL;
    }
    
    // Initialize encryption
    if (EVP_EncryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(padded_message);
        return NULL;
    }
    
    // Allocate memory for ciphertext (IV + encrypted data)
    *ciphertext_len = AES_BLOCK_SIZE + padded_len;
    unsigned char* ciphertext = (unsigned char*)malloc(*ciphertext_len);
    
    // Copy IV to the beginning of ciphertext
    memcpy(ciphertext, iv, AES_BLOCK_SIZE);
    
    // Encrypt message
    int len;
    int final_len;
    if (EVP_EncryptUpdate(ctx, ciphertext + AES_BLOCK_SIZE, &len, padded_message, padded_len) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(padded_message);
        free(ciphertext);
        return NULL;
    }
    
    if (EVP_EncryptFinal_ex(ctx, ciphertext + AES_BLOCK_SIZE + len, &final_len) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(padded_message);
        free(ciphertext);
        return NULL;
    }
    
    // Clean up
    EVP_CIPHER_CTX_free(ctx);
    free(padded_message);
    
    return ciphertext;
}

// Decryption function
char* decrypt(const unsigned char* ciphertext, int ciphertext_len, const unsigned char* key) {
    if (ciphertext_len < AES_BLOCK_SIZE) return NULL;
    
    // Extract IV from the beginning of ciphertext
    unsigned char iv[AES_BLOCK_SIZE];
    memcpy(iv, ciphertext, AES_BLOCK_SIZE);
    
    // Get the actual encrypted data
    const unsigned char* encrypted_data = ciphertext + AES_BLOCK_SIZE;
    int encrypted_len = ciphertext_len - AES_BLOCK_SIZE;
    
    // Create cipher context
    EVP_CIPHER_CTX* ctx = EVP_CIPHER_CTX_new();
    if (!ctx) return NULL;
    
    // Initialize decryption
    if (EVP_DecryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }
    
    // Allocate memory for decrypted data
    unsigned char* decrypted = (unsigned char*)malloc(encrypted_len);
    
    // Decrypt message
    int len;
    int final_len;
    if (EVP_DecryptUpdate(ctx, decrypted, &len, encrypted_data, encrypted_len) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(decrypted);
        return NULL;
    }
    
    if (EVP_DecryptFinal_ex(ctx, decrypted + len, &final_len) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(decrypted);
        return NULL;
    }
    
    int total_len = len + final_len;
    
    // Unpad message
    int unpadded_len;
    unsigned char* unpadded_message = unpad(decrypted, total_len, AES_BLOCK_SIZE, &unpadded_len);
    
    // Clean up
    EVP_CIPHER_CTX_free(ctx);
    free(decrypted);
    
    if (!unpadded_message) return NULL;
    
    // Convert message to string (null-terminate)
    char* message = (char*)malloc(unpadded_len + 1);
    memcpy(message, unpadded_message, unpadded_len);
    message[unpadded_len] = '\0';
    
    free(unpadded_message);
    
    return message;
}