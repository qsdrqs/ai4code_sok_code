#include <openssl/evp.h>
#include <openssl/rand.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

#define AES_256_KEY_SIZE 32
#define AES_BLOCK_SIZE 16

void handleErrors(void) {
    ERR_print_errors_fp(stderr);
    abort();
}

int encrypt(const unsigned char *plaintext, int plaintext_len, const unsigned char *key,
            unsigned char *ciphertext) {
    EVP_CIPHER_CTX *ctx;
    int len;
    int ciphertext_len;

    if (!(ctx = EVP_CIPHER_CTX_new())) handleErrors();

    if (1 != EVP_EncryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, key + AES_256_KEY_SIZE / 2))
        handleErrors();

    if (1 != EVP_EncryptUpdate(ctx, ciphertext, &len, plaintext, plaintext_len))
        handleErrors();
    ciphertext_len = len;

    if (1 != EVP_EncryptFinal_ex(ctx, ciphertext + len, &len)) handleErrors();
    ciphertext_len += len;

    EVP_CIPHER_CTX_free(ctx);

    return ciphertext_len;
}

int decrypt(const unsigned char *ciphertext, int ciphertext_len, const unsigned char *key,
            unsigned char *plaintext) {
    EVP_CIPHER_CTX *ctx;
    int len;
    int plaintext_len;

    if (!(ctx = EVP_CIPHER_CTX_new())) handleErrors();

    if (1 != EVP_DecryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, key + AES_256_KEY_SIZE / 2))
        handleErrors();

    if (1 != EVP_DecryptUpdate(ctx, plaintext, &len, ciphertext, ciphertext_len))
        handleErrors();
    plaintext_len = len;

    if (1 != EVP_DecryptFinal_ex(ctx, plaintext + len, &len)) handleErrors();
    plaintext_len += len;

    EVP_CIPHER_CTX_free(ctx);

    return plaintext_len;
}

int main() {
    // Example key and plaintext
    unsigned char key[AES_256_KEY_SIZE];
    if (!RAND_bytes(key, sizeof(key))) {
        fprintf(stderr, "Could not generate random bytes for key\n");
        return 1;
    }

    const char *plaintext = "This is a secret message";
    unsigned char ciphertext[128];
    unsigned char decryptedtext[128];

    int ciphertext_len = encrypt((unsigned char *)plaintext, strlen(plaintext), key, ciphertext);

    int decryptedtext_len = decrypt(ciphertext, ciphertext_len, key, decryptedtext);
    decryptedtext[decryptedtext_len] = '\0';

    printf("Decrypted text is: %s\n", decryptedtext);

    return 0;
}