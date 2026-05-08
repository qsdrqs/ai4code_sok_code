#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

char* encrypt(int key, const char* string) {
    if (string == NULL) {
        return NULL;
    }
    if (key < 0) {
        return NULL;
    }
    if (strlen(string) == 0) {
        return NULL;
    }
    if (key > 26) {
        key = key % 26;
    }

    size_t len = strlen(string);
    char* new_string = (char*)malloc((len + 1) * sizeof(char));
    if (new_string == NULL) {
        return NULL;
    }

    for (size_t i = 0; i < len; i++) {
        char c = string[i];
        if (isalpha((unsigned char)c)) {
            if (islower((unsigned char)c)) {
                new_string[i] = (char)(((c - 97 + key) % 26) + 97);
            } else {
                new_string[i] = (char)(((c - 65 + key) % 26) + 65);
            }
        } else {
            new_string[i] = c;
        }
    }
    new_string[len] = '\0';
    return new_string;
}

/*
 * function that decrypts a given string using a symmetric key
 */
char* decrypt(const char* ciphertext, const char* key) {
    if (ciphertext == NULL || key == NULL) {
        return NULL;
    }

    size_t ciphertext_len = strlen(ciphertext);
    size_t key_len = strlen(key);

    if (ciphertext_len == 0 || key_len == 0) {
        return NULL;
    }

    if (key_len == 1) {
        char* result = (char*)malloc((ciphertext_len + 1) * sizeof(char));
        if (result == NULL) {
            return NULL;
        }
        strcpy(result, ciphertext);
        return result;
    }

    if (key_len > ciphertext_len) {
        return NULL;
    }

    char* plaintext = (char*)malloc((ciphertext_len + 1) * sizeof(char));
    if (plaintext == NULL) {
        return NULL;
    }

    for (size_t i = 0; i < ciphertext_len; i++) {
        /* Use ((x % 26) + 26) % 26 to handle potential negative values,
           since C's % operator can return negative results, unlike Python's. */
        int value = (((int)ciphertext[i] - (int)key[i % key_len]) % 26 + 26) % 26;
        plaintext[i] = (char)(value + 65);
    }
    plaintext[ciphertext_len] = '\0';

    return plaintext;
}