#include <stdlib.h>  // For malloc and free
#include <string.h>  // For strlen

/**
 * Encrypts a given string using a symmetric key.
 *
 * @param string The input string to encrypt.
 * @param key    The symmetric key used for encryption.
 * @return       A dynamically allocated encrypted string. Must be freed by the caller.
 */
char* encrypt(const char* string, const char* key) {
    size_t len = strlen(string);
    size_t key_len = strlen(key);

    // Allocate memory for the encrypted string (+1 for null terminator)
    char* encrypted = (char*)malloc(len + 1);
    if (encrypted == NULL) {
        return NULL; // Memory allocation failed
    }

    // XOR each character with the corresponding key character (cycled)
    for (size_t i = 0; i < len; ++i) {
        encrypted[i] = (char)((unsigned char)string[i] ^ (unsigned char)key[i % key_len]);
    }

    encrypted[len] = '\0'; // Null-terminate the result
    return encrypted;
}

/**
 * Decrypts a given string using a symmetric key.
 *
 * @param string The input string to decrypt.
 * @param key    The symmetric key used for decryption.
 * @return       A dynamically allocated decrypted string. Must be freed by the caller.
 */
char* decrypt(const char* string, const char* key) {
    size_t len = strlen(string);
    size_t key_len = strlen(key);

    // Allocate memory for the decrypted string (+1 for null terminator)
    char* decrypted = (char*)malloc(len + 1);
    if (decrypted == NULL) {
        return NULL; // Memory allocation failed
    }

    // XOR each character with the corresponding key character (cycled)
    for (size_t i = 0; i < len; ++i) {
        decrypted[i] = (char)((unsigned char)string[i] ^ (unsigned char)key[i % key_len]);
    }

    decrypted[len] = '\0'; // Null-terminate the result
    return decrypted;
}