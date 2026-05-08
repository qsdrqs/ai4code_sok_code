#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include <openssl/evp.h>
#include <openssl/rand.h>
#include <openssl/err.h>

/*
 * Encrypts `data` (length `data_len`) using AES-256-CBC with the given `key`
 * and `iv`, writing the result into `ciphertext`. Padding is disabled to match
 * the Python cryptography library's Cipher primitive (which does not pad by
 * default and therefore requires `data_len` to be a multiple of 16).
 *
 * Returns the number of bytes written to `ciphertext`, or -1 on error.
 */
int encrypt(const unsigned char *data, int data_len,
            const unsigned char *key, const unsigned char *iv,
            unsigned char *ciphertext) {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    if (!ctx) {
        ERR_print_errors_fp(stderr);
        return -1;
    }

    if (1 != EVP_EncryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv)) {
        ERR_print_errors_fp(stderr);
        EVP_CIPHER_CTX_free(ctx);
        return -1;
    }

    /* Disable padding so behavior matches Python's cryptography Cipher. */
    EVP_CIPHER_CTX_set_padding(ctx, 0);

    int len;
    if (1 != EVP_EncryptUpdate(ctx, ciphertext, &len, data, data_len)) {
        ERR_print_errors_fp(stderr);
        EVP_CIPHER_CTX_free(ctx);
        return -1;
    }
    int ciphertext_len = len;

    if (1 != EVP_EncryptFinal_ex(ctx, ciphertext + len, &len)) {
        ERR_print_errors_fp(stderr);
        EVP_CIPHER_CTX_free(ctx);
        return -1;
    }
    ciphertext_len += len;

    EVP_CIPHER_CTX_free(ctx);
    return ciphertext_len;
}

/*
 * Decrypts `data` using AES-256-CBC with the given `key` and `iv`.
 * Returns the number of bytes written to `plaintext`, or -1 on error.
 */
int decrypt(const unsigned char *data, int data_len,
            const unsigned char *key, const unsigned char *iv,
            unsigned char *plaintext) {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    if (!ctx) {
        ERR_print_errors_fp(stderr);
        return -1;
    }

    if (1 != EVP_DecryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv)) {
        ERR_print_errors_fp(stderr);
        EVP_CIPHER_CTX_free(ctx);
        return -1;
    }

    EVP_CIPHER_CTX_set_padding(ctx, 0);

    int len;
    if (1 != EVP_DecryptUpdate(ctx, plaintext, &len, data, data_len)) {
        ERR_print_errors_fp(stderr);
        EVP_CIPHER_CTX_free(ctx);
        return -1;
    }
    int plaintext_len = len;

    if (1 != EVP_DecryptFinal_ex(ctx, plaintext + len, &len)) {
        ERR_print_errors_fp(stderr);
        EVP_CIPHER_CTX_free(ctx);
        return -1;
    }
    plaintext_len += len;

    EVP_CIPHER_CTX_free(ctx);
    return plaintext_len;
}

void test(void) {
    const unsigned char data[] = "a secret message";   /* 16 bytes, no NUL */
    const int data_len = 16;

    unsigned char key[32];
    unsigned char iv[16];

    /* Equivalent of os.urandom(...) */
    if (1 != RAND_bytes(key, sizeof(key))) {
        ERR_print_errors_fp(stderr);
        exit(1);
    }
    if (1 != RAND_bytes(iv, sizeof(iv))) {
        ERR_print_errors_fp(stderr);
        exit(1);
    }

    /* Output buffers: add one AES block for safety. */
    unsigned char e[32];
    unsigned char d[32];

    int e_len = encrypt(data, data_len, key, iv, e);
    assert(e_len == data_len);

    int d_len = decrypt(e, e_len, key, iv, d);
    assert(d_len == data_len);

    assert(memcmp(d, data, data_len) == 0);

    printf("Test passed!\n");
}

int main(void) {
    test();
    return 0;
}