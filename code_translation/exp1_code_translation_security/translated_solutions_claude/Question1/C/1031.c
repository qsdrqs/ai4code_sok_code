#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/evp.h>
#include <openssl/rand.h>
#include <openssl/aes.h>

#define KEY_SIZE 32
#define IV_SIZE 16
#define MAX_MESSAGE_SIZE 1024

// Structure to hold encrypted data
typedef struct {
    unsigned char *data;
    int length;
} EncryptedMessage;

// Global key (equivalent to the fernet object in Python)
unsigned char global_key[KEY_SIZE];

/*
 * Generate a random key (equivalent to Fernet.generate_key())
 */
void generate_key(unsigned char *key) {
    if (RAND_bytes(key, KEY_SIZE) != 1) {
        fprintf(stderr, "Error generating random key\n");
        exit(1);
    }
}

/*
 * encrypt using a symmetric key
 */
EncryptedMessage encrypt(const char *message) {
    EVP_CIPHER_CTX *ctx;
    EncryptedMessage result;
    unsigned char iv[IV_SIZE];
    unsigned char *ciphertext;
    int len;
    int ciphertext_len;
    int message_len = strlen(message);
    
    // Generate random IV
    if (RAND_bytes(iv, IV_SIZE) != 1) {
        fprintf(stderr, "Error generating IV\n");
        exit(1);
    }
    
    // Allocate memory for ciphertext (IV + encrypted data)
    ciphertext = malloc(IV_SIZE + message_len + AES_BLOCK_SIZE);
    if (!ciphertext) {
        fprintf(stderr, "Memory allocation failed\n");
        exit(1);
    }
    
    // Copy IV to the beginning of ciphertext
    memcpy(ciphertext, iv, IV_SIZE);
    
    // Create and initialize the context
    if (!(ctx = EVP_CIPHER_CTX_new())) {
        fprintf(stderr, "Error creating cipher context\n");
        exit(1);
    }
    
    // Initialize encryption operation
    if (EVP_EncryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, global_key, iv) != 1) {
        fprintf(stderr, "Error initializing encryption\n");
        exit(1);
    }
    
    // Encrypt the message
    if (EVP_EncryptUpdate(ctx, ciphertext + IV_SIZE, &len, (unsigned char*)message, message_len) != 1) {
        fprintf(stderr, "Error during encryption\n");
        exit(1);
    }
    ciphertext_len = len;
    
    // Finalize encryption
    if (EVP_EncryptFinal_ex(ctx, ciphertext + IV_SIZE + len, &len) != 1) {
        fprintf(stderr, "Error finalizing encryption\n");
        exit(1);
    }
    ciphertext_len += len;
    
    // Clean up
    EVP_CIPHER_CTX_free(ctx);
    
    result.data = ciphertext;
    result.length = IV_SIZE + ciphertext_len;
    
    return result;
}

/*
 * decrypt using a symmetric key
 */
char* decrypt(EncryptedMessage encMessage) {
    EVP_CIPHER_CTX *ctx;
    unsigned char *iv;
    unsigned char *ciphertext;
    char *plaintext;
    int len;
    int plaintext_len;
    int ciphertext_len;
    
    // Extract IV from the beginning of encrypted data
    iv = encMessage.data;
    ciphertext = encMessage.data + IV_SIZE;
    ciphertext_len = encMessage.length - IV_SIZE;
    
    // Allocate memory for plaintext
    plaintext = malloc(ciphertext_len + 1);
    if (!plaintext) {
        fprintf(stderr, "Memory allocation failed\n");
        exit(1);
    }
    
    // Create and initialize the context
    if (!(ctx = EVP_CIPHER_CTX_new())) {
        fprintf(stderr, "Error creating cipher context\n");
        exit(1);
    }
    
    // Initialize decryption operation
    if (EVP_DecryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, global_key, iv) != 1) {
        fprintf(stderr, "Error initializing decryption\n");
        exit(1);
    }
    
    // Decrypt the message
    if (EVP_DecryptUpdate(ctx, (unsigned char*)plaintext, &len, ciphertext, ciphertext_len) != 1) {
        fprintf(stderr, "Error during decryption\n");
        exit(1);
    }
    plaintext_len = len;
    
    // Finalize decryption
    if (EVP_DecryptFinal_ex(ctx, (unsigned char*)plaintext + len, &len) != 1) {
        fprintf(stderr, "Error finalizing decryption\n");
        exit(1);
    }
    plaintext_len += len;
    
    // Null terminate the string
    plaintext[plaintext_len] = '\0';
    
    // Clean up
    EVP_CIPHER_CTX_free(ctx);
    
    return plaintext;
}

/*
 * Print encrypted message in hexadecimal format
 */
void print_hex(EncryptedMessage encMessage) {
    printf("b'");
    for (int i = 0; i < encMessage.length; i++) {
        printf("\\x%02x", encMessage.data[i]);
    }
    printf("'\n");
}

int main() {
    const char *message = "the solutions are here somewhere";
    printf("Secret Message: %s\n", message);
    
    // Generate key (equivalent to Fernet.generate_key())
    generate_key(global_key);
    
    // Encrypt the message
    EncryptedMessage encMessage = encrypt(message);
    print_hex(encMessage);
    
    // Decrypt the message
    char *decMessage = decrypt(encMessage);
    printf("decrypted Message: %s\n", decMessage);
    
    // Clean up memory
    free(encMessage.data);
    free(decMessage);
    
    return 0;
}