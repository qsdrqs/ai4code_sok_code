#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/**
 * Encrypts a string by shifting each character by the given key.
 *
 * @param string The input string to encrypt.
 * @param key    The symmetric key used for encryption.
 * @return       A dynamically allocated encrypted string.
 */
char* encrypt(const char* string, int key) {
    size_t len = strlen(string);
    char* encrypted = (char*)malloc(len + 1);  // +1 for null terminator
    if (!encrypted) {
        fprintf(stderr, "Memory allocation failed in encrypt.\n");
        exit(EXIT_FAILURE);
    }

    for (size_t i = 0; i < len; i++) {
        encrypted[i] = string[i] + key;
    }
    encrypted[len] = '\0';  // Null-terminate the string

    return encrypted;
}

/**
 * Decrypts a string by shifting each character back by the given key.
 *
 * @param string The input string to decrypt.
 * @param key    The symmetric key used for decryption.
 * @return       A dynamically allocated decrypted string.
 */
char* decrypt(const char* string, int key) {
    size_t len = strlen(string);
    char* decrypted = (char*)malloc(len + 1);  // +1 for null terminator
    if (!decrypted) {
        fprintf(stderr, "Memory allocation failed in decrypt.\n");
        exit(EXIT_FAILURE);
    }

    for (size_t i = 0; i < len; i++) {
        decrypted[i] = string[i] - key;
    }
    decrypted[len] = '\0';  // Null-terminate the string

    return decrypted;
}

/**
 * Main function to demonstrate encryption and decryption.
 */
int main() {
    const char* original = "Hello World!";
    int key = 5;

    char* encrypted = encrypt(original, key);
    char* decrypted = decrypt(encrypted, key);

    printf("Encrypted: %s\n", encrypted);
    printf("Decrypted: %s\n", decrypted);

    // Free dynamically allocated memory
    free(encrypted);
    free(decrypted);

    return 0;
}