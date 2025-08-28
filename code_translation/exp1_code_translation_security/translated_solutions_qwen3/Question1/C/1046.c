#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/**
 * Encrypts a message by appending the key character after each character in the message.
 *
 * @param msg - The input string to encrypt.
 * @param key - The character key to append after each character.
 * @return A dynamically allocated encrypted string.
 */
char* encrypt(char* msg, char key) {
    int len = strlen(msg);
    // Allocate space for each character + key, plus null terminator
    char* result = (char*)malloc(2 * len + 1);
    if (!result) {
        fprintf(stderr, "Memory allocation failed in encrypt.\n");
        exit(EXIT_FAILURE);
    }

    int j = 0;
    for (int i = 0; i < len; i++) {
        result[j++] = msg[i];     // Add the original character
        result[j++] = key;        // Add the key
    }
    result[j] = '\0';             // Null-terminate the result
    return result;
}

/**
 * Decrypts an encrypted message by taking every other character starting from index 0.
 * Note: The key is not used in this function, as per the original Python logic.
 *
 * @param msg - The encrypted string.
 * @param key - The character key (unused in this function).
 * @return A dynamically allocated decrypted string.
 */
char* decrypt(char* msg, char key) {
    int len = strlen(msg);
    int decrypted_len = len / 2;
    // Allocate space for the decrypted string plus null terminator
    char* result = (char*)malloc(decrypted_len + 1);
    if (!result) {
        fprintf(stderr, "Memory allocation failed in decrypt.\n");
        exit(EXIT_FAILURE);
    }

    int j = 0;
    for (int i = 0; i < len; i++) {
        if (i % 2 == 0) {
            result[j++] = msg[i];  // Take every other character starting from index 0
        }
    }
    result[j] = '\0';              // Null-terminate the result
    return result;
}

/**
 * Main function to demonstrate the encrypt and decrypt functions.
 */
int main() {
    // Encrypt the string "a" with key 'b'
    char* encrypted = encrypt("a", 'b');
    printf("Encrypted: %s\n", encrypted);

    // Decrypt the string "ab" with key 'b'
    char* decrypted = decrypt("ab", 'b');
    printf("Decrypted: %s\n", decrypted);

    // Free dynamically allocated memory
    free(encrypted);
    free(decrypted);

    return 0;
}