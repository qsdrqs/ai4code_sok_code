#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/evp.h>
#include <openssl/aes.h>
#include <openssl/rand.h>
#include <openssl/err.h>

// Structure to hold encrypted data components
typedef struct {
    unsigned char *nonce;
    unsigned char *tag;
    unsigned char *ciphertext;
    int nonce_len;
    int tag_len;
    int ciphertext_len;
} encrypted_data_t;

// Function to encrypt plaintext using AES-GCM (equivalent to AES-EAX)
encrypted_data_t* encrypt(const unsigned char *key, int key_len, 
                         const unsigned char *plaintext, int plaintext_len) {
    EVP_CIPHER_CTX *ctx;
    int len;
    int ciphertext_len;
    
    // Allocate memory for the result structure
    encrypted_data_t *result = malloc(sizeof(encrypted_data_t));
    if (!result) return NULL;
    
    // Allocate memory for components
    result->nonce = malloc(12); // 96-bit nonce for GCM
    result->tag = malloc(16);   // 128-bit tag
    result->ciphertext = malloc(plaintext_len + 16); // Extra space for safety
    result->nonce_len = 12;
    result->tag_len = 16;
    
    // Generate random nonce
    if (RAND_bytes(result->nonce, result->nonce_len) != 1) {
        free(result->nonce);
        free(result->tag);
        free(result->ciphertext);
        free(result);
        return NULL;
    }
    
    // Create and initialize the context
    if (!(ctx = EVP_CIPHER_CTX_new())) {
        free(result->nonce);
        free(result->tag);
        free(result->ciphertext);
        free(result);
        return NULL;
    }
    
    // Initialize the encryption operation with AES-GCM
    if (EVP_EncryptInit_ex(ctx, EVP_aes_256_gcm(), NULL, NULL, NULL) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(result->nonce);
        free(result->tag);
        free(result->ciphertext);
        free(result);
        return NULL;
    }
    
    // Set nonce length
    if (EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_SET_IVLEN, result->nonce_len, NULL) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(result->nonce);
        free(result->tag);
        free(result->ciphertext);
        free(result);
        return NULL;
    }
    
    // Initialize key and nonce
    if (EVP_EncryptInit_ex(ctx, NULL, NULL, key, result->nonce) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(result->nonce);
        free(result->tag);
        free(result->ciphertext);
        free(result);
        return NULL;
    }
    
    // Encrypt the plaintext
    if (EVP_EncryptUpdate(ctx, result->ciphertext, &len, plaintext, plaintext_len) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(result->nonce);
        free(result->tag);
        free(result->ciphertext);
        free(result);
        return NULL;
    }
    ciphertext_len = len;
    
    // Finalize the encryption
    if (EVP_EncryptFinal_ex(ctx, result->ciphertext + len, &len) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(result->nonce);
        free(result->tag);
        free(result->ciphertext);
        free(result);
        return NULL;
    }
    ciphertext_len += len;
    result->ciphertext_len = ciphertext_len;
    
    // Get the authentication tag
    if (EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_GET_TAG, result->tag_len, result->tag) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(result->nonce);
        free(result->tag);
        free(result->ciphertext);
        free(result);
        return NULL;
    }
    
    // Clean up
    EVP_CIPHER_CTX_free(ctx);
    
    return result;
}

// Function to decrypt ciphertext using AES-GCM
unsigned char* decrypt(const unsigned char *key, int key_len, 
                      encrypted_data_t *encrypted_data, int *plaintext_len) {
    EVP_CIPHER_CTX *ctx;
    int len;
    int ret;
    
    // Allocate memory for plaintext
    unsigned char *plaintext = malloc(encrypted_data->ciphertext_len + 16);
    if (!plaintext) return NULL;
    
    // Create and initialize the context
    if (!(ctx = EVP_CIPHER_CTX_new())) {
        free(plaintext);
        return NULL;
    }
    
    // Initialize the decryption operation with AES-GCM
    if (EVP_DecryptInit_ex(ctx, EVP_aes_256_gcm(), NULL, NULL, NULL) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(plaintext);
        return NULL;
    }
    
    // Set nonce length
    if (EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_SET_IVLEN, encrypted_data->nonce_len, NULL) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(plaintext);
        return NULL;
    }
    
    // Initialize key and nonce
    if (EVP_DecryptInit_ex(ctx, NULL, NULL, key, encrypted_data->nonce) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(plaintext);
        return NULL;
    }
    
    // Decrypt the ciphertext
    if (EVP_DecryptUpdate(ctx, plaintext, &len, encrypted_data->ciphertext, 
                         encrypted_data->ciphertext_len) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(plaintext);
        return NULL;
    }
    *plaintext_len = len;
    
    // Set expected tag value
    if (EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_SET_TAG, encrypted_data->tag_len, 
                           encrypted_data->tag) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(plaintext);
        return NULL;
    }
    
    // Finalize the decryption and verify the tag
    ret = EVP_DecryptFinal_ex(ctx, plaintext + len, &len);
    
    // Clean up
    EVP_CIPHER_CTX_free(ctx);
    
    if (ret > 0) {
        // Success - tag verification passed
        *plaintext_len += len;
        return plaintext;
    } else {
        // Failed - tag verification failed
        free(plaintext);
        return NULL;
    }
}

// Helper function to free encrypted_data_t structure
void free_encrypted_data(encrypted_data_t *data) {
    if (data) {
        free(data->nonce);
        free(data->tag);
        free(data->ciphertext);
        free(data);
    }
}

// Example usage
int main() {
    // Example key (32 bytes for AES-256)
    unsigned char key[32] = "this_is_a_32_byte_key_for_aes256";
    
    // Example plaintext
    const char *plaintext_str = "Hello, World! This is a secret message.";
    unsigned char *plaintext = (unsigned char *)plaintext_str;
    int plaintext_len = strlen(plaintext_str);
    
    // Encrypt
    encrypted_data_t *encrypted = encrypt(key, 32, plaintext, plaintext_len);
    if (!encrypted) {
        printf("Encryption failed!\n");
        return 1;
    }
    
    printf("Encryption successful!\n");
    printf("Nonce length: %d\n", encrypted->nonce_len);
    printf("Tag length: %d\n", encrypted->tag_len);
    printf("Ciphertext length: %d\n", encrypted->ciphertext_len);
    
    // Decrypt
    int decrypted_len;
    unsigned char *decrypted = decrypt(key, 32, encrypted, &decrypted_len);
    if (!decrypted) {
        printf("Decryption failed!\n");
        free_encrypted_data(encrypted);
        return 1;
    }
    
    printf("Decryption successful!\n");
    printf("Decrypted text: %.*s\n", decrypted_len, decrypted);
    
    // Clean up
    free(decrypted);
    free_encrypted_data(encrypted);
    
    return 0;
}