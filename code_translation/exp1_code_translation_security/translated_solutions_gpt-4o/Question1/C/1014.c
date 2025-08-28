#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/conf.h>
#include <openssl/evp.h>
#include <openssl/err.h>
#include <openssl/rand.h>

#define AES_256_KEY_SIZE 32
#define AES_BLOCK_SIZE 16

void handleErrors(void) {
    ERR_print_errors_fp(stderr);
    abort();
}

void aes_encrypt(const unsigned char *key, const unsigned char *plaintext, unsigned char *ciphertext, int *len) {
    EVP_CIPHER_CTX *ctx;
    int len_temp;
    int ciphertext_len;

    if (!(ctx = EVP_CIPHER_CTX_new())) handleErrors();

    if (1 != EVP_EncryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, NULL)) handleErrors();

    if (1 != EVP_EncryptUpdate(ctx, ciphertext, &len_temp, plaintext, strlen((char *)plaintext))) handleErrors();
    ciphertext_len = len_temp;

    if (1 != EVP_EncryptFinal_ex(ctx, ciphertext + len_temp, &len_temp)) handleErrors();
    ciphertext_len += len_temp;

    *len = ciphertext_len;

    EVP_CIPHER_CTX_free(ctx);
}

void aes_decrypt(const unsigned char *key, const unsigned char *ciphertext, int ciphertext_len, unsigned char *plaintext) {
    EVP_CIPHER_CTX *ctx;
    int len_temp;
    int plaintext_len;

    if (!(ctx = EVP_CIPHER_CTX_new())) handleErrors();

    if (1 != EVP_DecryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, NULL)) handleErrors();

    if (1 != EVP_DecryptUpdate(ctx, plaintext, &len_temp, ciphertext, ciphertext_len)) handleErrors();
    plaintext_len = len_temp;

    if (1 != EVP_DecryptFinal_ex(ctx, plaintext + len_temp, &len_temp)) handleErrors();
    plaintext_len += len_temp;

    plaintext[plaintext_len] = '\0';

    EVP_CIPHER_CTX_free(ctx);
}

void sha256(const char *string, unsigned char outputBuffer[32]) {
    EVP_MD_CTX *mdctx;
    if ((mdctx = EVP_MD_CTX_new()) == NULL) handleErrors();

    if (1 != EVP_DigestInit_ex(mdctx, EVP_sha256(), NULL)) handleErrors();

    if (1 != EVP_DigestUpdate(mdctx, string, strlen(string))) handleErrors();

    if (1 != EVP_DigestFinal_ex(mdctx, outputBuffer, NULL)) handleErrors();

    EVP_MD_CTX_free(mdctx);
}

int main(void) {
    ERR_load_crypto_strings();
    OpenSSL_add_all_algorithms();
    OPENSSL_config(NULL);

    const char *key_str = "mysecretpassword";
    unsigned char key[AES_256_KEY_SIZE];
    sha256(key_str, key);

    const char *plaintext = "Secret Message A";
    unsigned char ciphertext[128];
    int ciphertext_len;

    aes_encrypt(key, (unsigned char *)plaintext, ciphertext, &ciphertext_len);

    printf("Encrypted: ");
    for (int i = 0; i < ciphertext_len; i++) {
        printf("%02x", ciphertext[i]);
    }
    printf("\n");

    unsigned char decryptedtext[128];
    aes_decrypt(key, ciphertext, ciphertext_len, decryptedtext);

    printf("Decrypted: %s\n", decryptedtext);

    EVP_cleanup();
    ERR_free_strings();

    return 0;
}