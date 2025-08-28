#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/aes.h>
#include <openssl/evp.h>

typedef struct {
    unsigned char* key;
    int key_len;
    int blk_sz;
} AEScipher;

// Constructor equivalent
AEScipher* AEScipher_new(unsigned char* key, int key_len, int blk_sz) {
    AEScipher* cipher = (AEScipher*)malloc(sizeof(AEScipher));
    if (!cipher) return NULL;
    
    cipher->key = (unsigned char*)malloc(key_len);
    if (!cipher->key) {
        free(cipher);
        return NULL;
    }
    
    memcpy(cipher->key, key, key_len);
    cipher->key_len = key_len;
    cipher->blk_sz = blk_sz;
    
    return cipher;
}

// Destructor equivalent
void AEScipher_free(AEScipher* cipher) {
    if (cipher) {
        if (cipher->key) {
            free(cipher->key);
        }
        free(cipher);
    }
}

// Encrypt function
unsigned char* AEScipher_encrypt(AEScipher* cipher, const char* msg, int msg_len, int* output_len) {
    if (!cipher || !msg) return NULL;
    
    // Calculate padding
    int pad_len = cipher->blk_sz - (msg_len % cipher->blk_sz);
    int padded_len = msg_len + pad_len;
    
    // Create padded message
    unsigned char* padded_msg = (unsigned char*)malloc(padded_len);
    if (!padded_msg) return NULL;
    
    memcpy(padded_msg, msg, msg_len);
    
    // Add padding
    for (int i = msg_len; i < padded_len; i++) {
        padded_msg[i] = (unsigned char)pad_len;
    }
    
    // Prepare for encryption
    EVP_CIPHER_CTX* ctx = EVP_CIPHER_CTX_new();
    if (!ctx) {
        free(padded_msg);
        return NULL;
    }
    
    unsigned char* ciphertext = (unsigned char*)malloc(padded_len);
    if (!ciphertext) {
        free(padded_msg);
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }
    
    int len;
    int ciphertext_len;
    
    // Initialize encryption
    if (EVP_EncryptInit_ex(ctx, EVP_aes_128_ecb(), NULL, cipher->key, NULL) != 1) {
        free(padded_msg);
        free(ciphertext);
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }
    
    // Disable padding since we're doing it manually
    EVP_CIPHER_CTX_set_padding(ctx, 0);
    
    // Encrypt
    if (EVP_EncryptUpdate(ctx, ciphertext, &len, padded_msg, padded_len) != 1) {
        free(padded_msg);
        free(ciphertext);
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }
    ciphertext_len = len;
    
    // Finalize encryption
    if (EVP_EncryptFinal_ex(ctx, ciphertext + len, &len) != 1) {
        free(padded_msg);
        free(ciphertext);
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }
    ciphertext_len += len;
    
    // Cleanup
    free(padded_msg);
    EVP_CIPHER_CTX_free(ctx);
    
    *output_len = ciphertext_len;
    return ciphertext;
}

// Decrypt function
unsigned char* AEScipher_decrypt(AEScipher* cipher, const unsigned char* ciphertext, int ciphertext_len, int* output_len) {
    if (!cipher || !ciphertext) return NULL;
    
    // Prepare for decryption
    EVP_CIPHER_CTX* ctx = EVP_CIPHER_CTX_new();
    if (!ctx) return NULL;
    
    unsigned char* plaintext = (unsigned char*)malloc(ciphertext_len);
    if (!plaintext) {
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }
    
    int len;
    int plaintext_len;
    
    // Initialize decryption
    if (EVP_DecryptInit_ex(ctx, EVP_aes_128_ecb(), NULL, cipher->key, NULL) != 1) {
        free(plaintext);
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }
    
    // Disable padding since we're handling it manually
    EVP_CIPHER_CTX_set_padding(ctx, 0);
    
    // Decrypt
    if (EVP_DecryptUpdate(ctx, plaintext, &len, ciphertext, ciphertext_len) != 1) {
        free(plaintext);
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }
    plaintext_len = len;
    
    // Finalize decryption
    if (EVP_DecryptFinal_ex(ctx, plaintext + len, &len) != 1) {
        free(plaintext);
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }
    plaintext_len += len;
    
    // Remove padding
    if (plaintext_len > 0) {
        int pad_len = plaintext[plaintext_len - 1];
        if (pad_len > 0 && pad_len <= cipher->blk_sz && pad_len <= plaintext_len) {
            plaintext_len -= pad_len;
        }
    }
    
    // Null terminate for string handling (optional)
    plaintext[plaintext_len] = '\0';
    
    // Cleanup
    EVP_CIPHER_CTX_free(ctx);
    
    *output_len = plaintext_len;
    return plaintext;
}

// Example usage
int main() {
    // Example key (16 bytes for AES-128)
    unsigned char key[] = "0123456789abcdef";
    int key_len = 16;
    int block_size = 16;
    
    // Create cipher instance
    AEScipher* cipher = AEScipher_new(key, key_len, block_size);
    if (!cipher) {
        printf("Failed to create cipher\n");
        return 1;
    }
    
    // Test message
    const char* message = "Hello, World!";
    int msg_len = strlen(message);
    
    // Encrypt
    int encrypted_len;
    unsigned char* encrypted = AEScipher_encrypt(cipher, message, msg_len, &encrypted_len);
    if (!encrypted) {
        printf("Encryption failed\n");
        AEScipher_free(cipher);
        return 1;
    }
    
    printf("Original: %s\n", message);
    printf("Encrypted length: %d\n", encrypted_len);
    
    // Decrypt
    int decrypted_len;
    unsigned char* decrypted = AEScipher_decrypt(cipher, encrypted, encrypted_len, &decrypted_len);
    if (!decrypted) {
        printf("Decryption failed\n");
        free(encrypted);
        AEScipher_free(cipher);
        return 1;
    }
    
    printf("Decrypted: %s\n", decrypted);
    printf("Decrypted length: %d\n", decrypted_len);
    
    // Cleanup
    free(encrypted);
    free(decrypted);
    AEScipher_free(cipher);
    
    return 0;
}