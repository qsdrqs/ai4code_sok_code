#include <stdlib.h>
#include <string.h>

/**
 * Encrypts a given string using a given symmetric key.
 * 
 * @param string The input string to be encrypted.
 * @param key    The symmetric key used for encryption.
 * @return       A dynamically allocated string containing the encrypted result.
 *               The caller is responsible for freeing the returned memory.
 */
char* encrypt(const char* string, const char* key) {
    size_t string_len = strlen(string);
    size_t key_len = strlen(key);

    // Allocate memory for the encrypted string (+1 for null terminator)
    char* encrypted = (char*)malloc(string_len + 1);
    if (!encrypted) {
        return NULL; // Memory allocation failed
    }

    // Perform XOR encryption
    for (size_t i = 0; i < string_len; ++i) {
        encrypted[i] = string[i] ^ key[i % key_len];
    }

    // Null-terminate the result
    encrypted[string_len] = '\0';

    return encrypted;
}

/**
 * Decrypts a given string using a given symmetric key.
 * 
 * Since XOR is symmetric, this function is identical to `encrypt`.
 * 
 * @param string The input string to be decrypted.
 * @param key    The symmetric key used for decryption.
 * @return       A dynamically allocated string containing the decrypted result.
 *               The caller is responsible for freeing the returned memory.
 */
char* decrypt(const char* string, const char* key) {
    return encrypt(string, key);
}