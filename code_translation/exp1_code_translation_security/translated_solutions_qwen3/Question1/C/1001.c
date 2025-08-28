#include <string.h>  // For strlen
#include <stdlib.h> // For malloc

/**
 * Encrypts a string using a repeating key.
 *
 * @param key The encryption key (null-terminated string).
 * @param string The input string to encrypt (null-terminated).
 * @return A dynamically allocated encrypted string, or NULL on failure.
 */
char* encrypt(const char* key, const char* string) {
    size_t len = strlen(string);
    size_t key_len = strlen(key);
    char* encrypted = malloc(len + 1); // +1 for null terminator
    if (!encrypted) return NULL;

    encrypted[len] = '\0'; // Null-terminate the result

    for (size_t i = 0; i < len; i++) {
        encrypted[i] = ( (unsigned char)string[i] + (unsigned char)key[i % key_len] ) % 256;
    }

    return encrypted;
}

/**
 * Decrypts a string using a repeating key.
 *
 * @param key The decryption key (null-terminated string).
 * @param string The encrypted string (null-terminated).
 * @return A dynamically allocated decrypted string, or NULL on failure.
 */
char* decrypt(const char* key, const char* string) {
    size_t len = strlen(string);
    size_t key_len = strlen(key);
    char* decrypted = malloc(len + 1); // +1 for null terminator
    if (!decrypted) return NULL;

    decrypted[len] = '\0'; // Null-terminate the result

    for (size_t i = 0; i < len; i++) {
        decrypted[i] = ( ( (unsigned char)string[i] - (unsigned char)key[i % key_len] ) + 256 ) % 256;
    }

    return decrypted;
}