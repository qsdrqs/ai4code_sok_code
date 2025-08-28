#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include <openssl/evp.h>
#include <openssl/aes.h>
#include <openssl/rand.h>
#include <openssl/err.h>

// Function to handle OpenSSL errors
void handle_errors(void) {
    ERR_print_errors_fp(stderr);
    abort();
}

// Encrypt function using AES-256-CBC
int encrypt(unsigned char *plaintext, int plaintext_len, unsigned char *key,
            unsigned char *iv, unsigned char *ciphertext) {
    EVP_CIPHER_CTX *ctx;
    int len;
    int ciphertext_len;

    // Create and initialize the context
    if(!(ctx = EVP_CIPHER_CTX_new()))
        handle_errors();

    // Initialize the encryption operation with AES-256-CBC
    if(1 != EVP_EncryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv))
        handle_errors();

    // Provide the message to be encrypted, and obtain the encrypted output
    if(1 != EVP_EncryptUpdate(ctx, ciphertext, &len, plaintext, plaintext_len))
        handle_errors();
    ciphertext_len = len;

    // Finalize the encryption
    if(1 != EVP_EncryptFinal_ex(ctx, ciphertext + len, &len))
        handle_errors();
    ciphertext_len += len;

    // Clean up
    EVP_CIPHER_CTX_free(ctx);

    return ciphertext_len;
}

// Decrypt function using AES-256-CBC
int decrypt(unsigned char *ciphertext, int ciphertext_len, unsigned char *key,
            unsigned char *iv, unsigned char *plaintext) {
    EVP_CIPHER_CTX *ctx;
    int len;
    int plaintext_len;

    // Create and initialize the context
    if(!(ctx = EVP_CIPHER_CTX_new()))
        handle_errors();

    // Initialize the decryption operation with AES-256-CBC
    if(1 != EVP_DecryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv))
        handle_errors();

    // Provide the message to be decrypted, and obtain the plaintext output
    if(1 != EVP_DecryptUpdate(ctx, plaintext, &len, ciphertext, ciphertext_len))
        handle_errors();
    plaintext_len = len;

    // Finalize the decryption
    if(1 != EVP_DecryptFinal_ex(ctx, plaintext + len, &len))
        handle_errors();
    plaintext_len += len;

    // Clean up
    EVP_CIPHER_CTX_free(ctx);

    return plaintext_len;
}

void test(void) {
    // Test data
    unsigned char data[] = "a secret message";
    int data_len = strlen((char*)data);
    
    // Generate random key (32 bytes for AES-256) and IV (16 bytes)
    unsigned char key[32];
    unsigned char iv[16];
    
    if(RAND_bytes(key, sizeof(key)) != 1)
        handle_errors();
    if(RAND_bytes(iv, sizeof(iv)) != 1)
        handle_errors();
    
    // Buffers for encrypted and decrypted data
    // Add extra space for padding
    unsigned char encrypted[1024];
    unsigned char decrypted[1024];
    
    // Encrypt the data
    int encrypted_len = encrypt(data, data_len, key, iv, encrypted);
    
    // Decrypt the data
    int decrypted_len = decrypt(encrypted, encrypted_len, key, iv, decrypted);
    
    // Null terminate the decrypted string for comparison
    decrypted[decrypted_len] = '\0';
    
    // Verify that decrypted data matches original
    assert(decrypted_len == data_len);
    assert(memcmp(data, decrypted, data_len) == 0);
    
    printf("Test passed! Original: %s, Decrypted: %s\n", data, decrypted);
}

int main(void) {
    // Initialize OpenSSL
    ERR_load_crypto_strings();
    OpenSSL_add_all_algorithms();
    
    // Run the test
    test();
    
    // Cleanup OpenSSL
    EVP_cleanup();
    ERR_free_strings();
    
    return 0;
}