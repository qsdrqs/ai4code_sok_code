#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/evp.h>
#include <openssl/rand.h>

#define AES_256_KEY_SIZE 32
#define AES_BLOCK_SIZE 16

void handleErrors(void) {
    fprintf(stderr, "An error occurred\n");
    exit(EXIT_FAILURE);
}

void encrypt(const unsigned char *plaintext, int plaintext_len, const unsigned char *key, unsigned char *ciphertext, int *ciphertext_len) {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    if (!ctx) handleErrors();

    if (1 != EVP_EncryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, key + AES_256_KEY_SIZE / 2))
        handleErrors();

    int len;
    if (1 != EVP_EncryptUpdate(ctx, ciphertext, &len, plaintext, plaintext_len))
        handleErrors();
    *ciphertext_len = len;

    if (1 != EVP_EncryptFinal_ex(ctx, ciphertext + len, &len))
        handleErrors();
    *ciphertext_len += len;

    EVP_CIPHER_CTX_free(ctx);
}

void decrypt(const unsigned char *ciphertext, int ciphertext_len, const unsigned char *key, unsigned char *plaintext, int *plaintext_len) {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    if (!ctx) handleErrors();

    if (1 != EVP_DecryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, key + AES_256_KEY_SIZE / 2))
        handleErrors();

    int len;
    if (1 != EVP_DecryptUpdate(ctx, plaintext, &len, ciphertext, ciphertext_len))
        handleErrors();
    *plaintext_len = len;

    if (1 != EVP_DecryptFinal_ex(ctx, plaintext + len, &len))
        handleErrors();
    *plaintext_len += len;

    EVP_CIPHER_CTX_free(ctx);
}

int main() {
    unsigned char key[AES_256_KEY_SIZE];
    if (!RAND_bytes(key, sizeof(key))) {
        fprintf(stderr, "Error generating random key\n");
        return EXIT_FAILURE;
    }

    unsigned char *input = (unsigned char *)"super secret";
    int input_len = strlen((char *)input);

    unsigned char ciphertext[128];
    int ciphertext_len;

    encrypt(input, input_len, key, ciphertext, &ciphertext_len);

    unsigned char decryptedtext[128];
    int decryptedtext_len;

    decrypt(ciphertext, ciphertext_len, key, decryptedtext, &decryptedtext_len);
    decryptedtext[decryptedtext_len] = '\0';

    printf("Original: %s\n", input);
    printf("Decrypted: %s\n", decryptedtext);

    return 0;
}