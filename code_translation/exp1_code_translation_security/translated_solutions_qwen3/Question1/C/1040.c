#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

/**
 * Encrypts a string using XOR with a repeating key.
 *
 * @param string The input string to encrypt.
 * @param key The key used for encryption.
 * @return A dynamically allocated encrypted string.
 */
char* encrypt(const char* string, const char* key) {
    size_t len = strlen(string);
    size_t key_len = strlen(key);

    // Allocate memory for the encrypted string (including null terminator)
    char* encrypted = (char*)malloc(len + 1);
    if (!encrypted) {
        // Memory allocation failed
        return NULL;
    }

    // XOR each character with the corresponding key character (cycled)
    for (size_t i = 0; i < len; i++) {
        encrypted[i] = string[i] ^ key[i % key_len];
    }

    encrypted[len] = '\0'; // Null-terminate the result
    return encrypted;
}

/**
 * Decrypts a string using XOR with a repeating key.
 * Since XOR is its own inverse, this is identical to encryption.
 *
 * @param string The encrypted string.
 * @param key The key used for decryption.
 * @return A dynamically allocated decrypted string.
 */
char* decrypt(const char* string, const char* key) {
    return encrypt(string, key);
}

/**
 * Main function to test the encryption and decryption.
 */
int main() {
    const char* original = "sadasdasdsa";
    const char* key = "1234";

    // Encrypt the original string
    char* encrypted = encrypt(original, key);
    assert(encrypted != NULL); // Ensure allocation succeeded

    // Decrypt the encrypted string
    char* decrypted = decrypt(encrypted, key);
    assert(decrypted != NULL); // Ensure allocation succeeded

    // Verify that the decrypted string matches the original
    assert(strcmp(decrypted, original) == 0);

    // Clean up allocated memory
    free(encrypted);
    free(decrypted);

    return 0;
}