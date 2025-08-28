#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/**
 * Encrypts a given string by shifting each character's ASCII value by the key.
 *
 * @param string The input string to encrypt.
 * @param key    The number of positions to shift each character.
 * @return       A dynamically allocated encrypted string.
 */
char* encrypt(char* string, int key) {
    size_t len = strlen(string);
    char* encrypted = (char*)malloc(len + 1);  // +1 for null terminator
    if (!encrypted) {
        perror("Memory allocation failed in encrypt");
        exit(EXIT_FAILURE);
    }

    for (size_t i = 0; i < len; i++) {
        encrypted[i] = (char)(string[i] + key);  // Add key to each character
    }
    encrypted[len] = '\0';  // Null-terminate the string

    return encrypted;
}

/**
 * Decrypts a given string by shifting each character's ASCII value back by the key.
 *
 * @param string The input string to decrypt.
 * @param key    The number of positions to shift each character.
 * @return       A dynamically allocated decrypted string.
 */
char* decrypt(char* string, int key) {
    size_t len = strlen(string);
    char* decrypted = (char*)malloc(len + 1);  // +1 for null terminator
    if (!decrypted) {
        perror("Memory allocation failed in decrypt");
        exit(EXIT_FAILURE);
    }

    for (size_t i = 0; i < len; i++) {
        decrypted[i] = (char)(string[i] - key);  // Subtract key from each character
    }
    decrypted[len] = '\0';  // Null-terminate the string

    return decrypted;
}

/**
 * Main function to demonstrate encryption and decryption.
 */
int main() {
    char* original = "Hello World!";
    int key = 5;

    // Encrypt the string
    char* encrypted = encrypt(original, key);

    // Decrypt the encrypted string
    char* decrypted = decrypt(encrypted, key);

    // Print results
    printf("Encrypted: %s\n", encrypted);
    printf("Decrypted: %s\n", decrypted);

    // Free dynamically allocated memory
    free(encrypted);
    free(decrypted);

    return 0;
}