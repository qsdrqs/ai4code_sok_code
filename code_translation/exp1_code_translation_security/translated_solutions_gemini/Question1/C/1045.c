#include <stdio.h>  // For input/output functions like printf
#include <string.h> // For string manipulation functions like strlen and strcpy
#include <stdlib.h> // For memory allocation functions like malloc and free

/**
 * @brief Encrypts a given string by adding a key to each character's value.
 * 
 * @param text The constant character string to encrypt.
 * @param key The integer key to add to each character.
 * @return A new, dynamically allocated string containing the encrypted text.
 *         The caller is responsible for freeing this memory using free().
 *         Returns NULL if memory allocation fails.
 */
char* encrypt(const char* text, int key) {
    // Get the length of the input string
    size_t len = strlen(text);
    
    // Allocate memory for the new encrypted string.
    // We need space for all characters plus one for the null terminator '\0'.
    char* encrypted_text = (char*)malloc(len + 1);
    
    // Check if memory allocation was successful
    if (encrypted_text == NULL) {
        fprintf(stderr, "Error: Memory allocation failed.\n");
        return NULL;
    }
    
    // Copy the original string into our new memory block
    strcpy(encrypted_text, text);
    
    // Iterate over each character and add the key to its ASCII/Unicode value
    for (size_t i = 0; i < len; i++) {
        encrypted_text[i] += key;
    }
    
    // The string is already null-terminated by strcpy, so we can return it.
    return encrypted_text;
}

/**
 * @brief Decrypts a given string by subtracting a key from each character's value.
 * 
 * @param text The constant character string to decrypt.
 * @param key The integer key to subtract from each character.
 * @return A new, dynamically allocated string containing the decrypted text.
 *         The caller is responsible for freeing this memory using free().
 *         Returns NULL if memory allocation fails.
 */
char* decrypt(const char* text, int key) {
    // Get the length of the input string
    size_t len = strlen(text);
    
    // Allocate memory for the new decrypted string.
    char* decrypted_text = (char*)malloc(len + 1);

    // Check if memory allocation was successful
    if (decrypted_text == NULL) {
        fprintf(stderr, "Error: Memory allocation failed.\n");
        return NULL;
    }
    
    // Copy the original string into our new memory block
    strcpy(decrypted_text, text);
    
    // Iterate over each character and subtract the key from its ASCII/Unicode value
    for (size_t i = 0; i < len; i++) {
        decrypted_text[i] -= key;
    }
    
    // The string is already null-terminated by strcpy, so we can return it.
    return decrypted_text;
}

int main() {
    // Encrypt the string "name" with key 1234
    char* encrypted_result = encrypt("name", 1234);
    if (encrypted_result != NULL) {
        printf("%s\n", encrypted_result);
        
        // Now, decrypt the result to get the original string back
        char* decrypted_result = decrypt(encrypted_result, 1234);
        if (decrypted_result != NULL) {
            printf("%s\n", decrypted_result);
            
            // IMPORTANT: Free the memory allocated for the decrypted string
            free(decrypted_result);
        }
        
        // IMPORTANT: Free the memory allocated for the encrypted string
        free(encrypted_result);
    }

    return 0;
}