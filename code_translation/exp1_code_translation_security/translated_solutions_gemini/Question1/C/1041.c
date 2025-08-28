#include <stdio.h>  // For printf
#include <string.h> // For strlen
#include <stdlib.h> // For malloc and free

/**
 * @brief Encrypts a string using a simple character offset (Caesar cipher).
 * 
 * @param string The constant character string to encrypt.
 * @param key The integer offset to apply to each character.
 * @return A new, dynamically allocated string containing the encrypted text.
 *         The caller is responsible for freeing this memory with free().
 */
char* encrypt(const char* string, int key) {
    // Get the length of the input string to allocate memory for the new one.
    size_t len = strlen(string);
    
    // In C, we must manually allocate memory for new strings.
    // We need space for all characters plus one for the null terminator ('\0').
    char* new_string = (char*)malloc(len + 1);
    
    // Always check if malloc was successful.
    if (new_string == NULL) {
        fprintf(stderr, "Memory allocation failed\n");
        return NULL;
    }

    // Iterate through the original string and create the new one.
    for (size_t i = 0; i < len; i++) {
        // In C, a 'char' is just a small integer, so we can do math on it directly.
        // This is equivalent to Python's ord() and chr().
        new_string[i] = string[i] + key;
    }
    
    // Add the null terminator to mark the end of the string. This is crucial.
    new_string[len] = '\0';
    
    return new_string;
}

/**
 * @brief Decrypts a string by reversing the character offset.
 * 
 * @param string The constant character string to decrypt.
 * @param key The integer offset to subtract from each character.
 * @return A new, dynamically allocated string containing the decrypted text.
 *         The caller is responsible for freeing this memory with free().
 */
char* decrypt(const char* string, int key) {
    size_t len = strlen(string);
    char* new_string = (char*)malloc(len + 1);

    if (new_string == NULL) {
        fprintf(stderr, "Memory allocation failed\n");
        return NULL;
    }

    for (size_t i = 0; i < len; i++) {
        new_string[i] = string[i] - key;
    }
    
    new_string[len] = '\0';
    
    return new_string;
}

int main() {
    const char* original_message = "this is cool if it really works";
    int key = 2;

    // Encrypt the message
    // 'msg' now points to a new block of memory allocated inside encrypt()
    char* msg = encrypt(original_message, key);
    
    // Decrypt the message
    // 'res' points to another new block of memory from decrypt()
    char* res = decrypt(msg, key);

    // Print the results
    printf("Encrypted: %s\n", msg);
    printf("Decrypted: %s\n", res);

    // In C, we must manually free the memory we allocated with malloc().
    // Failure to do so results in a "memory leak".
    free(msg);
    free(res);

    return 0; // Indicate successful execution
}