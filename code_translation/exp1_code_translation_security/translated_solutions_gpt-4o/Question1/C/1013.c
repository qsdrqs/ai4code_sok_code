#include <openssl/evp.h>
#include <openssl/rand.h>
#include <openssl/sha.h>
#include <openssl/err.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

#define SALT_SIZE 16
#define KEY_SIZE 32
#define ITERATIONS 100000

void handleErrors(void) {
    ERR_print_errors_fp(stderr);
    abort();
}

void derive_key(const char *password, const unsigned char *salt, int iterations, unsigned char *key) {
    if (!PKCS5_PBKDF2_HMAC(password, strlen(password), salt, SALT_SIZE, iterations, EVP_sha256(), KEY_SIZE, key)) {
        handleErrors();
    }
}

void encrypt(const char *message, const char *password, unsigned char **encrypted, int *encrypted_len) {
    unsigned char salt[SALT_SIZE];
    unsigned char key[KEY_SIZE];
    unsigned char iv[EVP_MAX_IV_LENGTH];
    int len;
    int ciphertext_len;
    EVP_CIPHER_CTX *ctx;

    if (!RAND_bytes(salt, sizeof(salt))) {
        handleErrors();
    }

    derive_key(password, salt, ITERATIONS, key);

    if (!(ctx = EVP_CIPHER_CTX_new())) {
        handleErrors();
    }

    if (1 != EVP_EncryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv)) {
        handleErrors();
    }

    *encrypted = (unsigned char *)malloc(strlen(message) + EVP_CIPHER_block_size(EVP_aes_256_cbc()));
    if (!*encrypted) {
        handleErrors();
    }

    if (1 != EVP_EncryptUpdate(ctx, *encrypted, &len, (unsigned char *)message, strlen(message))) {
        handleErrors();
    }
    ciphertext_len = len;

    if (1 != EVP_EncryptFinal_ex(ctx, *encrypted + len, &len)) {
        handleErrors();
    }
    ciphertext_len += len;

    EVP_CIPHER_CTX_free(ctx);

    *encrypted_len = ciphertext_len;
}

void decrypt(const unsigned char *encrypted, int encrypted_len, const char *password, unsigned char **decrypted, int *decrypted_len) {
    unsigned char salt[SALT_SIZE];
    unsigned char key[KEY_SIZE];
    unsigned char iv[EVP_MAX_IV_LENGTH];
    int len;
    int plaintext_len;
    EVP_CIPHER_CTX *ctx;

    memcpy(salt, encrypted, SALT_SIZE);
    derive_key(password, salt, ITERATIONS, key);

    if (!(ctx = EVP_CIPHER_CTX_new())) {
        handleErrors();
    }

    if (1 != EVP_DecryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv)) {
        handleErrors();
    }

    *decrypted = (unsigned char *)malloc(encrypted_len);
    if (!*decrypted) {
        handleErrors();
    }

    if (1 != EVP_DecryptUpdate(ctx, *decrypted, &len, encrypted + SALT_SIZE, encrypted_len - SALT_SIZE)) {
        handleErrors();
    }
    plaintext_len = len;

    if (1 != EVP_DecryptFinal_ex(ctx, *decrypted + len, &len)) {
        handleErrors();
    }
    plaintext_len += len;

    EVP_CIPHER_CTX_free(ctx);

    *decrypted_len = plaintext_len;
}

int main() {
    const char *message = "nice123";
    const char *password = "password123";
    unsigned char *encrypted;
    int encrypted_len;
    unsigned char *decrypted;
    int decrypted_len;

    encrypt(message, password, &encrypted, &encrypted_len);
    printf("Encrypted: ");
    for (int i = 0; i < encrypted_len; i++) {
        printf("%02x", encrypted[i]);
    }
    printf("\n");

    decrypt(encrypted, encrypted_len, password, &decrypted, &decrypted_len);
    printf("Decrypted: %.*s\n", decrypted_len, decrypted);

    free(encrypted);
    free(decrypted);

    return 0;
}