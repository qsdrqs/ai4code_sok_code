#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/evp.h>
#include <openssl/rand.h>
#include <openssl/err.h>

#define KEY_SIZE 16
#define NONCE_SIZE 12
#define TAG_SIZE 16

typedef struct {
    unsigned char *ciphertext;
    unsigned char *tag;
    unsigned char *nonce;
    int ciphertext_len;
} EncryptResult;

void handle_errors(void) {
    ERR_print_errors_fp(stderr);
    abort();
}

EncryptResult encrypt(unsigned char *key, unsigned char *msg, int msg_len) {
    EVP_CIPHER_CTX *ctx;
    int len;
    int ciphertext_len;
    EncryptResult result;
    
    // Allocate memory for result components
    result.ciphertext = malloc(msg_len + 16); // Extra space for potential padding
    result.tag = malloc(TAG_SIZE);
    result.nonce = malloc(NONCE_SIZE);
    
    // Generate random nonce
    if (RAND_bytes(result.nonce, NONCE_SIZE) != 1) {
        handle_errors();
    }
    
    // Create and initialize the context
    if (!(ctx = EVP_CIPHER_CTX_new())) {
        handle_errors();
    }
    
    // Initialize the encryption operation with AES-GCM
    if (EVP_EncryptInit_ex(ctx, EVP_aes_128_gcm(), NULL, NULL, NULL) != 1) {
        handle_errors();
    }
    
    // Set nonce length
    if (EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_SET_IVLEN, NONCE_SIZE, NULL) != 1) {
        handle_errors();
    }
    
    // Initialize key and nonce
    if (EVP_EncryptInit_ex(ctx, NULL, NULL, key, result.nonce) != 1) {
        handle_errors();
    }
    
    // Encrypt the message
    if (EVP_EncryptUpdate(ctx, result.ciphertext, &len, msg, msg_len) != 1) {
        handle_errors();
    }
    ciphertext_len = len;
    
    // Finalize the encryption
    if (EVP_EncryptFinal_ex(ctx, result.ciphertext + len, &len) != 1) {
        handle_errors();
    }
    ciphertext_len += len;
    result.ciphertext_len = ciphertext_len;
    
    // Get the authentication tag
    if (EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_GET_TAG, TAG_SIZE, result.tag) != 1) {
        handle_errors();
    }
    
    // Clean up
    EVP_CIPHER_CTX_free(ctx);
    
    return result;
}

unsigned char* decrypt(unsigned char *key, unsigned char *ciphertext, int ciphertext_len, 
                      unsigned char *nonce, unsigned char *tag, int *plaintext_len) {
    EVP_CIPHER_CTX *ctx;
    int len;
    int ret;
    unsigned char *plaintext = malloc(ciphertext_len);
    
    // Create and initialize the context
    if (!(ctx = EVP_CIPHER_CTX_new())) {
        handle_errors();
    }
    
    // Initialize the decryption operation with AES-GCM
    if (EVP_DecryptInit_ex(ctx, EVP_aes_128_gcm(), NULL, NULL, NULL) != 1) {
        handle_errors();
    }
    
    // Set nonce length
    if (EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_SET_IVLEN, NONCE_SIZE, NULL) != 1) {
        handle_errors();
    }
    
    // Initialize key and nonce
    if (EVP_DecryptInit_ex(ctx, NULL, NULL, key, nonce) != 1) {
        handle_errors();
    }
    
    // Decrypt the message
    if (EVP_DecryptUpdate(ctx, plaintext, &len, ciphertext, ciphertext_len) != 1) {
        handle_errors();
    }
    *plaintext_len = len;
    
    // Set expected tag value
    if (EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_SET_TAG, TAG_SIZE, tag) != 1) {
        handle_errors();
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
        // Tag verification failed
        free(plaintext);
        printf("Tag verification failed!\n");
        return NULL;
    }
}

void free_encrypt_result(EncryptResult *result) {
    free(result->ciphertext);
    free(result->tag);
    free(result->nonce);
}

int main() {
    unsigned char key[] = "Sixteen byte key";
    unsigned char message[] = "message";
    int message_len = strlen((char*)message);
    
    // Encrypt the message
    EncryptResult result = encrypt(key, message, message_len);
    
    // Decrypt the message
    int plaintext_len;
    unsigned char *plaintext = decrypt(key, result.ciphertext, result.ciphertext_len, 
                                     result.nonce, result.tag, &plaintext_len);
    
    if (plaintext != NULL) {
        printf("Decrypted message: ");
        for (int i = 0; i < plaintext_len; i++) {
            printf("%c", plaintext[i]);
        }
        printf("\n");
        free(plaintext);
    }
    
    // Clean up
    free_encrypt_result(&result);
    
    return 0;
}