#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/evp.h>
#include <openssl/aes.h>
#include <openssl/rand.h>

#define AES_KEY_SIZE 32  // 256-bit key
#define NONCE_SIZE 12    // 96-bit nonce for GCM
#define TAG_SIZE 16      // 128-bit authentication tag

typedef struct {
    unsigned char *ciphertext;
    unsigned char nonce[NONCE_SIZE];
    unsigned char tag[TAG_SIZE];
    int ciphertext_len;
} encrypted_data_t;

// Function to encrypt plaintext
encrypted_data_t* encrypt(const unsigned char *plaintext, int plaintext_len, const unsigned char *key) {
    EVP_CIPHER_CTX *ctx;
    encrypted_data_t *result;
    int len;
    int ciphertext_len;
    
    // Allocate result structure
    result = malloc(sizeof(encrypted_data_t));
    if (!result) return NULL;
    
    // Allocate memory for ciphertext
    result->ciphertext = malloc(plaintext_len);
    if (!result->ciphertext) {
        free(result);
        return NULL;
    }
    
    // Generate random nonce
    if (RAND_bytes(result->nonce, NONCE_SIZE) != 1) {
        free(result->ciphertext);
        free(result);
        return NULL;
    }
    
    // Create and initialize the context
    if (!(ctx = EVP_CIPHER_CTX_new())) {
        free(result->ciphertext);
        free(result);
        return NULL;
    }
    
    // Initialize the encryption operation with AES-256-GCM
    if (EVP_EncryptInit_ex(ctx, EVP_aes_256_gcm(), NULL, NULL, NULL) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(result->ciphertext);
        free(result);
        return NULL;
    }
    
    // Set nonce length
    if (EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_SET_IVLEN, NONCE_SIZE, NULL) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(result->ciphertext);
        free(result);
        return NULL;
    }
    
    // Initialize key and nonce
    if (EVP_EncryptInit_ex(ctx, NULL, NULL, key, result->nonce) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(result->ciphertext);
        free(result);
        return NULL;
    }
    
    // Encrypt the plaintext
    if (EVP_EncryptUpdate(ctx, result->ciphertext, &len, plaintext, plaintext_len) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(result->ciphertext);
        free(result);
        return NULL;
    }
    ciphertext_len = len;
    
    // Finalize the encryption
    if (EVP_EncryptFinal_ex(ctx, result->ciphertext + len, &len) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(result->ciphertext);
        free(result);
        return NULL;
    }
    ciphertext_len += len;
    
    // Get the authentication tag
    if (EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_GET_TAG, TAG_SIZE, result->tag) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(result->ciphertext);
        free(result);
        return NULL;
    }
    
    result->ciphertext_len = ciphertext_len;
    
    // Clean up
    EVP_CIPHER_CTX_free(ctx);
    
    return result;
}

// Function to decrypt ciphertext
unsigned char* decrypt(const encrypted_data_t *encrypted, const unsigned char *key, int *plaintext_len) {
    EVP_CIPHER_CTX *ctx;
    unsigned char *plaintext;
    int len;
    int ret;
    
    // Allocate memory for plaintext
    plaintext = malloc(encrypted->ciphertext_len);
    if (!plaintext) return NULL;
    
    // Create and initialize the context
    if (!(ctx = EVP_CIPHER_CTX_new())) {
        free(plaintext);
        return NULL;
    }
    
    // Initialize the decryption operation with AES-256-GCM
    if (EVP_DecryptInit_ex(ctx, EVP_aes_256_gcm(), NULL, NULL, NULL) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(plaintext);
        return NULL;
    }
    
    // Set nonce length
    if (EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_SET_IVLEN, NONCE_SIZE, NULL) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(plaintext);
        return NULL;
    }
    
    // Initialize key and nonce
    if (EVP_DecryptInit_ex(ctx, NULL, NULL, key, encrypted->nonce) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(plaintext);
        return NULL;
    }
    
    // Decrypt the ciphertext
    if (EVP_DecryptUpdate(ctx, plaintext, &len, encrypted->ciphertext, encrypted->ciphertext_len) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(plaintext);
        return NULL;
    }
    *plaintext_len = len;
    
    // Set expected tag value
    if (EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_SET_TAG, TAG_SIZE, (void*)encrypted->tag) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(plaintext);
        return NULL;
    }
    
    // Finalize the decryption and verify the tag
    ret = EVP_DecryptFinal_ex(ctx, plaintext + len, &len);
    
    // Clean up
    EVP_CIPHER_CTX_free(ctx);
    
    if (ret > 0) {
        // Success
        *plaintext_len += len;
        return plaintext;
    } else {
        // Authentication failed
        free(plaintext);
        return NULL;
    }
}

// Helper function to free encrypted data
void free_encrypted_data(encrypted_data_t *data) {
    if (data) {
        if (data->ciphertext) {
            free(data->ciphertext);
        }
        free(data);
    }
}

// Example usage
int main() {
    // Example key (32 bytes for AES-256)
    unsigned char key[AES_KEY_SIZE] = {
        0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
        0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f,
        0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17,
        0x18, 0x19, 0x1a, 0x1b, 0x1c, 0x1d, 0x1e, 0x1f
    };
    
    const char *message = "Hello, World!";
    int message_len = strlen(message);
    
    // Encrypt
    encrypted_data_t *encrypted = encrypt((unsigned char*)message, message_len, key);
    if (!encrypted) {
        printf("Encryption failed\n");
        return 1;
    }
    
    printf("Encryption successful\n");
    printf("Ciphertext length: %d\n", encrypted->ciphertext_len);
    
    // Decrypt
    int decrypted_len;
    unsigned char *decrypted = decrypt(encrypted, key, &decrypted_len);
    if (!decrypted) {
        printf("Decryption failed\n");
        free_encrypted_data(encrypted);
        return 1;
    }
    
    printf("Decryption successful\n");
    printf("Decrypted message: %.*s\n", decrypted_len, decrypted);
    
    // Clean up
    free(decrypted);
    free_encrypted_data(encrypted);
    
    return 0;
}