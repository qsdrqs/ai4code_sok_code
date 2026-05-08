#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/evp.h>
#include <openssl/rand.h>

#define AES_BLOCK_SIZE 16

/* 
 * Encrypt a message using AES-128-CBC.
 * Returns a heap-allocated ciphertext buffer (caller must free).
 * ciphertext_len is set to the length of the returned buffer.
 */
unsigned char* encrypt(const char* message, const unsigned char* key, int* ciphertext_len) {
    // convert message to bytes (in C, strings are already bytes)
    int message_len = strlen(message);
    
    // create cipher (generate random IV, matching PyCryptodome's default behavior)
    unsigned char iv[AES_BLOCK_SIZE];
    RAND_bytes(iv, AES_BLOCK_SIZE);
    
    EVP_CIPHER_CTX* ctx = EVP_CIPHER_CTX_new();
    EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, key, iv);
    
    // allocate buffer (need extra block for padding)
    unsigned char* ciphertext = malloc(message_len + AES_BLOCK_SIZE);
    int len, total_len = 0;
    
    // pad message and encrypt (EVP applies PKCS7 padding by default)
    EVP_EncryptUpdate(ctx, ciphertext, &len, (const unsigned char*)message, message_len);
    total_len += len;
    
    EVP_EncryptFinal_ex(ctx, ciphertext + total_len, &len);
    total_len += len;
    
    *ciphertext_len = total_len;
    EVP_CIPHER_CTX_free(ctx);
    
    // return ciphertext
    return ciphertext;
}

/*
 * Decrypt a ciphertext using AES-128-CBC.
 * Returns a heap-allocated null-terminated string (caller must free).
 */
char* decrypt(const unsigned char* ciphertext, int ciphertext_len, const unsigned char* key) {
    // create cipher (generate random IV, matching PyCryptodome's default behavior)
    unsigned char iv[AES_BLOCK_SIZE];
    RAND_bytes(iv, AES_BLOCK_SIZE);
    
    EVP_CIPHER_CTX* ctx = EVP_CIPHER_CTX_new();
    EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, key, iv);
    
    // allocate buffer for plaintext (+1 for null terminator)
    unsigned char* plaintext = malloc(ciphertext_len + 1);
    int len, total_len = 0;
    
    // decrypt message
    EVP_DecryptUpdate(ctx, plaintext, &len, ciphertext, ciphertext_len);
    total_len += len;
    
    // unpad message (EVP removes PKCS7 padding automatically)
    EVP_DecryptFinal_ex(ctx, plaintext + total_len, &len);
    total_len += len;
    
    // convert message to string (null-terminate)
    plaintext[total_len] = '\0';
    
    EVP_CIPHER_CTX_free(ctx);
    
    // return message
    return (char*)plaintext;
}