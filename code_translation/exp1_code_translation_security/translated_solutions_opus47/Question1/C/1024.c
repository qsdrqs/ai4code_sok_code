/*
 * Compile with: gcc -o aes_example aes_example.c -lcrypto
 *
 * Note: PyCryptodome's AES.MODE_CFB uses segment_size=8 by default (CFB8),
 * so we use EVP_aes_128_cfb8() to match that behavior.
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <openssl/evp.h>
#include <openssl/err.h>

void handleErrors(void) {
    ERR_print_errors_fp(stderr);
    abort();
}

int encrypt_string(const unsigned char *message, int message_len,
                   unsigned char *ciphertext, EVP_CIPHER_CTX *obj) {
    int len;
    if (1 != EVP_EncryptUpdate(obj, ciphertext, &len, message, message_len))
        handleErrors();
    return len;
}

int decrypt_string(const unsigned char *ciphertext, int ciphertext_len,
                   unsigned char *plaintext, EVP_CIPHER_CTX *obj) {
    int len;
    if (1 != EVP_DecryptUpdate(obj, plaintext, &len, ciphertext, ciphertext_len))
        handleErrors();
    return len;
}

/* Border line since we are sending an obj that is derived from a key rather a plaintext key */
int main(void) {
    unsigned char *key = (unsigned char *)"This is a key123";
    unsigned char *iv  = (unsigned char *)"This is an IV456";
    const char *message = "The answer is no";
    int message_len = strlen(message);

    /* obj1 = AES.new('This is a key123', AES.MODE_CFB, 'This is an IV456') */
    EVP_CIPHER_CTX *obj1 = EVP_CIPHER_CTX_new();
    if (!obj1) handleErrors();
    if (1 != EVP_EncryptInit_ex(obj1, EVP_aes_128_cfb8(), NULL, key, iv))
        handleErrors();

    /* obj2 = AES.new('This is a key123', AES.MODE_CFB, 'This is an IV456') */
    EVP_CIPHER_CTX *obj2 = EVP_CIPHER_CTX_new();
    if (!obj2) handleErrors();
    if (1 != EVP_DecryptInit_ex(obj2, EVP_aes_128_cfb8(), NULL, key, iv))
        handleErrors();

    unsigned char enc[128];
    unsigned char dec[128];

    /* enc = encrypt_string(message, obj1) */
    int enc_len = encrypt_string((const unsigned char *)message, message_len, enc, obj1);

    /* dec = decrypt_string(enc, obj2) */
    int dec_len = decrypt_string(enc, enc_len, dec, obj2);

    dec[dec_len] = '\0';  /* Null-terminate for printing */

    printf("Original:  %s\n", message);
    printf("Encrypted (hex): ");
    for (int i = 0; i < enc_len; i++)
        printf("%02x", enc[i]);
    printf("\n");
    printf("Decrypted: %s\n", dec);

    EVP_CIPHER_CTX_free(obj1);
    EVP_CIPHER_CTX_free(obj2);

    return 0;
}