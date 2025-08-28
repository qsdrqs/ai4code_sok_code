#include <stdio.h>  // For printf
#include <stdlib.h> // For malloc and free
#include <string.h> // For strlen

/**
 * @brief Encrypts a string using a given key.
 * 
 * @param string The input string to encrypt.
 * @param key The integer key to shift characters by.
 * @return A new, dynamically allocated string containing the encrypted text.
 *         The caller is responsible for freeing this memory.
 */
char* encrypt(const char* string, int key) {
    // Get the length of the input string
    size_t len = strlen(string);
    
    // Allocate memory for the new encrypted string.
    // We need space for all characters plus one for the null terminator ('\0').
    char* encrypted = (char*)malloc(len + 1);
    if (encrypted == NULL) {
        // Handle memory allocation failure
        fprintf(stderr, "Error: Memory allocation failed.\n");
        return NULL;
    }

    // Iterate through each character of the input string
    for (size_t i = 0; i < len; i++) {
        // Add the key to the character's ASCII value and store it
        encrypted[i] = string[i] + key;
    }
    
    // Add the null terminator to mark the end of the new string
    encrypted[len] = '\0';
    
    return encrypted;
}

/**
 * @brief Decrypts a string using a given key.
 * 
 * @param string The input string to decrypt.
 * @param key The integer key that was used for encryption.
 * @return A new, dynamically allocated string containing the decrypted text.
 *         The caller is responsible for freeing this memory.
 */
char* decrypt(const char* string, int key) {
    // Get the length of the input string
    size_t len = strlen(string);
    
    // Allocate memory for the new decrypted string.
    char* decrypted = (char*)malloc(len + 1);
    if (decrypted == NULL) {
        // Handle memory allocation failure
        fprintf(stderr, "Error: Memory allocation failed.\n");
        return NULL;
    }

    // Iterate through each character of the input string
    for (size_t i = 0; i < len; i++) {
        // Subtract the key from the character's ASCII value
        decrypted[i] = string[i] - key;
    }
    
    // Add the null terminator
    decrypted[len] = '\0';
    
    return decrypted;
}

/**
 * @brief Main function.
 */
int main() {
    const char* string = "Hello World!";
    int key = 5;

    // Encrypt the string
    char* encrypted = encrypt(string, key);
    if (encrypted == NULL) {
        return 1; // Exit with an error code if encryption failed
    }

    // Decrypt the encrypted string
    char* decrypted = decrypt(encrypted, key);
    if (decrypted == NULL) {
        free(encrypted); // Clean up memory from the first allocation
        return 1; // Exit with an error code if decryption failed
    }

    // Print the results
    printf("Encrypted: %s\n", encrypted);
    printf("Decrypted: %s\n", decrypted);

    // IMPORTANT: Free the dynamically allocated memory to prevent leaks
    free(encrypted);
    free(decrypted);

    return 0; // Indicate successful execution
}