/**
 * @file main.c
 * @brief Translates a Python encryption/decryption script to C.
 * 
 * This program contains two functions: one to encrypt and one to decrypt a
 * given string using a simple character-offset symmetric key.
 */

#include <stdio.h>   // For input/output functions like printf
#include <string.h>  // For string manipulation functions like strlen
#include <stdlib.h>  // For memory allocation functions like malloc and free

/**
 * @brief Encrypts a string using a given key.
 * 
 * @param string The constant character string to encrypt.
 * @param key The integer key to shift characters by.
 * @return A new dynamically allocated string containing the encrypted text.
 *         The caller is responsible for freeing this memory.
 *         Returns NULL if memory allocation fails.
 */
char* encrypt(const char* string, int key) {
    // Get the length of the input string to allocate memory for the new one.
    size_t len = strlen(string);
    
    // Allocate memory for the encrypted string (+1 for the null terminator).
    char* encrypted = (char*)malloc(len + 1);
    if (encrypted == NULL) {
        // Handle memory allocation failure
        fprintf(stderr, "Error: Memory allocation failed.\n");
        return NULL;
    }

    // Iterate through each character of the input string.
    for (size_t i = 0; i < len; i++) {
        // Add the key to the character's ASCII value and store it.
        encrypted[i] = string[i] + key;
    }
    
    // Add the null terminator to mark the end of the new string.
    encrypted[len] = '\0';
    
    return encrypted;
}

/**
 * @brief Decrypts a string using a given key.
 * 
 * @param string The constant character string to decrypt.
 * @param key The integer key that was used for encryption.
 * @return A new dynamically allocated string containing the decrypted text.
 *         The caller is responsible for freeing this memory.
 *         Returns NULL if memory allocation fails.
 */
char* decrypt(const char* string, int key) {
    // Get the length of the input string.
    size_t len = strlen(string);
    
    // Allocate memory for the decrypted string (+1 for the null terminator).
    char* decrypted = (char*)malloc(len + 1);
    if (decrypted == NULL) {
        // Handle memory allocation failure
        fprintf(stderr, "Error: Memory allocation failed.\n");
        return NULL;
    }

    // Iterate through each character of the input string.
    for (size_t i = 0; i < len; i++) {
        // Subtract the key from the character's ASCII value to reverse the encryption.
        decrypted[i] = string[i] - key;
    }
    
    // Add the null terminator.
    decrypted[len] = '\0';
    
    return decrypted;
}

/**
 * @brief Main function to demonstrate encryption and decryption.
 */
int main() {
    const char* string = "Hello World!";
    int key = 5;

    // Encrypt the string
    char* encrypted = encrypt(string, key);
    if (encrypted == NULL) {
        return 1; // Exit with an error code
    }

    // Decrypt the encrypted string
    char* decrypted = decrypt(encrypted, key);
    if (decrypted == NULL) {
        free(encrypted); // Clean up previously allocated memory
        return 1; // Exit with an error code
    }

    // Print the results
    printf("Original:  %s\n", string);
    printf("Encrypted: %s\n", encrypted);
    printf("Decrypted: %s\n", decrypted);

    // IMPORTANT: Free the dynamically allocated memory to prevent memory leaks.
    free(encrypted);
    free(decrypted);

    return 0; // Indicate successful execution
}