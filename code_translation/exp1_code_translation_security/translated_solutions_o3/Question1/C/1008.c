/*
 * Simple mod-256 “Vigenère” encrypt / decrypt demo
 * (translation of the supplied Python code)
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* -----------------------------------------------------------
 * Encrypt
 * ----------------------------------------------------------- */
char *encrypt(const char *key, const char *plaintext)
{
    size_t key_len  = strlen(key);
    size_t text_len = strlen(plaintext);

    /* +1 for the terminating '\0' so we can treat it as a C string */
    char *ciphertext = (char *)malloc(text_len + 1);
    if (ciphertext == NULL) {
        return NULL;   /* allocation failed */
    }

    for (size_t i = 0; i < text_len; ++i) {
        unsigned char key_c   = (unsigned char)key[i % key_len];
        unsigned char plain_c = (unsigned char)plaintext[i];
        ciphertext[i] = (char)((plain_c + key_c) % 256);
    }
    ciphertext[text_len] = '\0';
    return ciphertext;
}

/* -----------------------------------------------------------
 * Decrypt
 * ----------------------------------------------------------- */
char *decrypt(const char *key, const char *ciphertext)
{
    size_t key_len  = strlen(key);
    size_t text_len = strlen(ciphertext);   /* OK because we added '\0' */

    char *plaintext = (char *)malloc(text_len + 1);
    if (plaintext == NULL) {
        return NULL;
    }

    for (size_t i = 0; i < text_len; ++i) {
        unsigned char key_c    = (unsigned char)key[i % key_len];
        unsigned char cipher_c = (unsigned char)ciphertext[i];
        plaintext[i] = (char)((256 + cipher_c - key_c) % 256);
    }
    plaintext[text_len] = '\0';
    return plaintext;
}

/* -----------------------------------------------------------
 * Demo (matches the Python “print(decrypt('123', encrypt('123', 'helloworld')))”)
 * ----------------------------------------------------------- */
int main(void)
{
    const char *key      = "123";
    const char *original = "helloworld";

    char *cipher = encrypt(key, original);
    if (cipher == NULL) {
        fprintf(stderr, "Memory allocation failed (cipher).\n");
        return 1;
    }

    char *plain = decrypt(key, cipher);
    if (plain == NULL) {
        fprintf(stderr, "Memory allocation failed (plain).\n");
        free(cipher);
        return 1;
    }

    printf("%s\n", plain);          /* prints: helloworld */

    /* Tidy-up */
    free(cipher);
    free(plain);
    return 0;
}