#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

/**
 * Encrypts a string using a Caesar cipher with a given key.
 *
 * @param key The shift value (must be non-negative).
 * @param string The input string to encrypt.
 * @return A newly allocated encrypted string, or NULL on error.
 */
char* encrypt(int key, const char* string) {
    if (key < 0 || string == NULL) {
        return NULL;
    }

    size_t len = strlen(string);
    if (len == 0) {
        return NULL;
    }

    // Clamp key to [0, 25]
    key %= 26;

    // Allocate memory for the new string
    char* new_string = (char*)malloc(len + 1);
    if (new_string == NULL) {
        return NULL;
    }

    for (size_t i = 0; i < len; i++) {
        char c = string[i];

        if (isalpha((unsigned char)c)) {
            if (islower((unsigned char)c)) {
                new_string[i] = ((c - 'a' + key) % 26) + 'a';
            } else {
                new_string[i] = ((c - 'A' + key) % 26) + 'A';
            }
        } else {
            new_string[i] = c;
        }
    }

    new_string[len] = '\0'; // Null-terminate the string
    return new_string;
}

/**
 * Decrypts a string using a symmetric key (Vigenère cipher with a single character key).
 *
 * @param ciphertext The encrypted string to decrypt.
 * @param key The symmetric key (must be a non-empty string).
 * @return A newly allocated decrypted string, or NULL on error.
 */
char* decrypt(char* ciphertext, char* key) {
    if (ciphertext == NULL || key == NULL) {
        return NULL;
    }

    size_t cipher_len = strlen(ciphertext);
    size_t key_len = strlen(key);

    if (cipher_len == 0 || key_len == 0) {
        return NULL;
    }

    if (key_len == 1) {
        // Return a copy of the ciphertext
        char* copy = (char*)malloc(cipher_len + 1);
        if (copy == NULL) {
            return NULL;
        }
        strcpy(copy, ciphertext);
        return copy;
    }

    if (key_len > cipher_len) {
        return NULL;
    }

    // Allocate memory for the plaintext
    char* plaintext = (char*)malloc(cipher_len + 1);
    if (plaintext == NULL) {
        return NULL;
    }

    for (size_t i = 0; i < cipher_len; i++) {
        size_t key_index = i % key_len;

        int cipher_val = (int)((unsigned char)ciphertext[i]);
        int key_val = (int)((unsigned char)key[key_index]);

        int diff = cipher_val - key_val;
        int value = diff % 26;

        if (value < 0) {
            value += 26;
        }

        plaintext[i] = (char)(value + 'A');
    }

    plaintext[cipher_len] = '\0'; // Null-terminate the string
    return plaintext;
}