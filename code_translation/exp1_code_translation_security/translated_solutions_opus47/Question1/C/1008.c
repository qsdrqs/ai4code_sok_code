#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
 * encrypt with key
 */
char* encrypt(const char* key, const char* plaintext) {
    size_t plaintext_len = strlen(plaintext);
    size_t key_len = strlen(key);
    char* ciphertext = (char*)malloc(plaintext_len + 1);
    if (!ciphertext) return NULL;

    for (size_t i = 0; i < plaintext_len; i++) {
        unsigned char key_c = (unsigned char)key[i % key_len];
        ciphertext[i] = (char)(((unsigned char)plaintext[i] + key_c) % 256);
    }
    ciphertext[plaintext_len] = '\0';
    return ciphertext;
}

/*
 * decrypt with key
 */
char* decrypt(const char* key, const char* ciphertext) {
    size_t ciphertext_len = strlen(ciphertext);
    size_t key_len = strlen(key);
    char* plaintext = (char*)malloc(ciphertext_len + 1);
    if (!plaintext) return NULL;

    for (size_t i = 0; i < ciphertext_len; i++) {
        unsigned char key_c = (unsigned char)key[i % key_len];
        plaintext[i] = (char)((256 + (unsigned char)ciphertext[i] - key_c) % 256);
    }
    plaintext[ciphertext_len] = '\0';
    return plaintext;
}

int main(void) {
    char* ciphertext = encrypt("123", "helloworld");
    char* plaintext = decrypt("123", ciphertext);
    printf("%s\n", plaintext);
    free(ciphertext);
    free(plaintext);
    return 0;
}