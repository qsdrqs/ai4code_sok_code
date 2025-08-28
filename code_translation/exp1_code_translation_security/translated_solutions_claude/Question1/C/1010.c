#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/evp.h>
#include <openssl/aes.h>
#include <openssl/rand.h>
#include <openssl/gcm.h>

#define AES_KEY_SIZE 16
#define AES_NONCE_SIZE 16
#define AES_TAG_SIZE 16

/**
 * Structure to hold encryption result
 */
typedef struct {
    unsigned char *ciphertext;
    int ciphertext_len;
    unsigned char nonce[AES_NONCE_SIZE];
    unsigned char tag[AES_TAG_SIZE];
} EncryptResult;

/**
 * Function to generate a symmetric key
 */
void generate_key(unsigned char *key) {
    if (RAND_bytes(key, AES_KEY_SIZE) != 1) {
        fprintf(stderr, "Error generating random key\n");
        exit(1);
    }
}

/**
 * Function to encrypt a string using a symmetric key
 */
EncryptResult* encrypt(const unsigned char *key, const char *data) {
    EVP_CIPHER_CTX *ctx;
    int len;
    int ciphertext_len;
    
    // Allocate result structure
    EncryptResult *result = malloc(sizeof(EncryptResult));
    if (!result) {
        fprintf(stderr, "Memory allocation failed\n");
        return NULL;
    }
    
    // Generate random nonce
    if (RAND_bytes(result->nonce, AES_NONCE_SIZE) != 1) {
        fprintf(stderr, "Error generating nonce\n");
        free(result);
        return NULL;
    }
    
    // Allocate memory for ciphertext (same size as plaintext should be sufficient)
    int data_len = strlen(data);
    result->ciphertext = malloc(data_len + AES_BLOCK_SIZE);
    if (!result->ciphertext) {
        fprintf(stderr, "Memory allocation failed\n");
        free(result);
        return NULL;
    }
    
    // Create and initialize the context
    if (!(ctx = EVP_CIPHER_CTX_new())) {
        fprintf(stderr, "Error creating cipher context\n");
        free(result->ciphertext);
        free(result);
        return NULL;
    }
    
    // Initialize the encryption operation with AES-128-GCM (similar to EAX mode)
    if (EVP_EncryptInit_ex(ctx, EVP_aes_128_gcm(), NULL, NULL, NULL) != 1) {
        fprintf(stderr, "Error initializing encryption\n");
        EVP_CIPHER_CTX_free(ctx);
        free(result->ciphertext);
        free(result);
        return NULL;
    }
    
    // Set nonce length
    if (EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_SET_IVLEN, AES_NONCE_SIZE, NULL) != 1) {
        fprintf(stderr, "Error setting nonce length\n");
        EVP_CIPHER_CTX_free(ctx);
        free(result->ciphertext);
        free(result);
        return NULL;
    }
    
    // Initialize key and nonce
    if (EVP_EncryptInit_ex(ctx, NULL, NULL, key, result->nonce) != 1) {
        fprintf(stderr, "Error setting key and nonce\n");
        EVP_CIPHER_CTX_free(ctx);
        free(result->ciphertext);
        free(result);
        return NULL;
    }
    
    // Encrypt the data
    if (EVP_EncryptUpdate(ctx, result->ciphertext, &len, (unsigned char*)data, data_len) != 1) {
        fprintf(stderr, "Error during encryption\n");
        EVP_CIPHER_CTX_free(ctx);
        free(result->ciphertext);
        free(result);
        return NULL;
    }
    ciphertext_len = len;
    
    // Finalize the encryption
    if (EVP_EncryptFinal_ex(ctx, result->ciphertext + len, &len) != 1) {
        fprintf(stderr, "Error finalizing encryption\n");
        EVP_CIPHER_CTX_free(ctx);
        free(result->ciphertext);
        free(result);
        return NULL;
    }
    ciphertext_len += len;
    result->ciphertext_len = ciphertext_len;
    
    // Get the authentication tag
    if (EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_GET_TAG, AES_TAG_SIZE, result->tag) != 1) {
        fprintf(stderr, "Error getting authentication tag\n");
        EVP_CIPHER_CTX_free(ctx);
        free(result->ciphertext);
        free(result);
        return NULL;
    }
    
    // Clean up
    EVP_CIPHER_CTX_free(ctx);
    
    return result;
}

/**
 * Function to decrypt a string
 */
char* decrypt(const unsigned char *key, const unsigned char *nonce, 
              const unsigned char *ciphertext, int ciphertext_len, 
              const unsigned char *tag) {
    EVP_CIPHER_CTX *ctx;
    int len;
    int plaintext_len;
    
    // Allocate memory for plaintext
    char *plaintext = malloc(ciphertext_len + 1); // +1 for null terminator
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
    if (EVP_DecryptInit_ex(ctx, EVP_aes_128_gcm(), NULL, NULL, NULL) != 1) {
        fprintf(stderr, "Error initializing decryption\n");
        EVP_CIPHER_CTX_free(ctx);
        free(plaintext);
        return NULL;
    }
    
    // Set nonce length
    if (EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_SET_IVLEN, AES_NONCE_SIZE, NULL) != 1) {
        fprintf(stderr, "Error setting nonce length\n");
        EVP_CIPHER_CTX_free(ctx);
        free(plaintext);
        return NULL;
    }
    
    // Initialize key and nonce
    if (EVP_DecryptInit_ex(ctx, NULL, NULL, key, nonce) != 1) {
        fprintf(stderr, "Error setting key and nonce\n");
        EVP_CIPHER_CTX_free(ctx);
        free(plaintext);
        return NULL;
    }
    
    // Decrypt the data
    if (EVP_DecryptUpdate(ctx, (unsigned char*)plaintext, &len, ciphertext, ciphertext_len) != 1) {
        fprintf(stderr, "Error during decryption\n");
        EVP_CIPHER_CTX_free(ctx);
        free(plaintext);
        return NULL;
    }
    plaintext_len = len;
    
    // Set expected tag value
    if (EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_SET_TAG, AES_TAG_SIZE, (void*)tag) != 1) {
        fprintf(stderr, "Error setting authentication tag\n");
        EVP_CIPHER_CTX_free(ctx);
        free(plaintext);
        return NULL;
    }
    
    // Finalize the decryption and verify the tag
    int ret = EVP_DecryptFinal_ex(ctx, (unsigned char*)plaintext + len, &len);
    if (ret <= 0) {
        fprintf(stderr, "Error finalizing decryption or authentication failed\n");
        EVP_CIPHER_CTX_free(ctx);
        free(plaintext);
        return NULL;
    }
    plaintext_len += len;
    
    // Null terminate the string
    plaintext[plaintext_len] = '\0';
    
    // Clean up
    EVP_CIPHER_CTX_free(ctx);
    
    return plaintext;
}

/**
 * Function to free EncryptResult structure
 */
void free_encrypt_result(EncryptResult *result) {
    if (result) {
        if (result->ciphertext) {
            free(result->ciphertext);
        }
        free(result);
    }
}

/**
 * Example usage
 */
int main() {
    // Generate a key
    unsigned char key[AES_KEY_SIZE];
    generate_key(key);
    
    // Test data
    const char *test_data = "Jim's test";
    
    // Encrypt the data
    EncryptResult *encrypted = encrypt(key, test_data);
    if (!encrypted) {
        fprintf(stderr, "Encryption failed\n");
        return 1;
    }
    
    // Decrypt the data
    char *restored_data = decrypt(key, encrypted->nonce, encrypted->ciphertext, 
                                 encrypted->ciphertext_len, encrypted->tag);
    if (!restored_data) {
        fprintf(stderr, "Decryption failed\n");
        free_encrypt_result(encrypted);
        return 1;
    }
    
    // Print the result
    printf("Original: %s\n", test_data);
    printf("Restored: %s\n", restored_data);
    
    // Clean up
    free(restored_data);
    free_encrypt_result(encrypted);
    
    return 0;
}