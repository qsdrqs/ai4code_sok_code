#include <openssl/evp.h>
#include <openssl/rand.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

#define KEY_SIZE 32
#define BLOCK_SIZE 16

// Function to handle errors
void handleErrors(void) {
    ERR_print_errors_fp(stderr);
    abort();
}

// Function to generate a random key
void generate_key(unsigned char *key) {
    if (!RAND_bytes(key, KEY_SIZE)) {
        handleErrors();
    }
}

// Function to encrypt the plaintext
int encrypt_text(unsigned char *plaintext, int plaintext_len, unsigned char *key, unsigned char *iv, unsigned char *ciphertext) {
    EVP_CIPHER_CTX *ctx;
    int len;
    int ciphertext_len;

    // Create and initialize the context
    if (!(ctx = EVP_CIPHER_CTX_new())) handleErrors();

    // Initialize the encryption operation
    if (1 != EVP_EncryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv)) handleErrors();

    // Provide the message to be encrypted, and obtain the encrypted output
    if (1 != EVP_EncryptUpdate(ctx, ciphertext, &len, plaintext, plaintext_len)) handleErrors();
    ciphertext_len = len;

    // Finalize the encryption
    if (1 != EVP_EncryptFinal_ex(ctx, ciphertext + len, &len)) handleErrors();
    ciphertext_len += len;

    // Clean up
    EVP_CIPHER_CTX_free(ctx);

    return ciphertext_len;
}

// Function to decrypt the ciphertext
int decrypt_text(unsigned char *ciphertext, int ciphertext_len, unsigned char *key, unsigned char *iv, unsigned char *plaintext) {
    EVP_CIPHER_CTX *ctx;
    int len;
    int plaintext_len;

    // Create and initialize the context
    if (!(ctx = EVP_CIPHER_CTX_new())) handleErrors();

    // Initialize the decryption operation
    if (1 != EVP_DecryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv)) handleErrors();

    // Provide the message to be decrypted, and obtain the plaintext output
    if (1 != EVP_DecryptUpdate(ctx, plaintext, &len, ciphertext, ciphertext_len)) handleErrors();
    plaintext_len = len;

    // Finalize the decryption
    if (1 != EVP_DecryptFinal_ex(ctx, plaintext + len, &len)) handleErrors();
    plaintext_len += len;

    // Clean up
    EVP_CIPHER_CTX_free(ctx);

    return plaintext_len;
}

int main(void) {
    // Load the human readable error strings for libcrypto
    ERR_load_crypto_strings();
    // Load all digest and cipher algorithms
    OpenSSL_add_all_algorithms();
    // Load config file, and other important initialisation
    OPENSSL_config(NULL);

    unsigned char key[KEY_SIZE];
    unsigned char iv[BLOCK_SIZE];
    unsigned char *plaintext = (unsigned char *)"This is a test.";
    unsigned char ciphertext[128];
    unsigned char decryptedtext[128];
    int decryptedtext_len, ciphertext_len;

    // Generate a random key and IV
    generate_key(key);
    if (!RAND_bytes(iv, BLOCK_SIZE)) {
        handleErrors();
    }

    // Encrypt the plaintext
    ciphertext_len = encrypt_text(plaintext, strlen((char *)plaintext), key, iv, ciphertext);

    // Decrypt the ciphertext
    decryptedtext_len = decrypt_text(ciphertext, ciphertext_len, key, iv, decryptedtext);

    // Add a NULL terminator
    decryptedtext[decryptedtext_len] = '\0';

    // Show the decrypted text
    printf("Decrypted text is: %s\n", decryptedtext);

    // Clean up
    EVP_cleanup();
    ERR_free_strings();

    return 0;
}