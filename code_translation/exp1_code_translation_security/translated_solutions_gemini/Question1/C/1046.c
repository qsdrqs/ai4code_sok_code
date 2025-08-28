#include <stdio.h>  // For printf
#include <stdlib.h> // For malloc, realloc, free (memory management)
#include <string.h> // For strlen, strcat (string manipulation)

// Forward declarations of the functions
char* encrypt(const char* msg, const char* key);
char* decrypt(const char* msg, const char* key);


/**
 * @brief Encrypts a message by interleaving each character with a key.
 * 
 * For each character 'c' in the message, it appends 'c' and then the 'key'
 * to the result.
 * Example: encrypt("a", "b") -> "ab"
 * Example: encrypt("ac", "b") -> "ab" + "cb" -> "abcb"
 * 
 * @param msg The original message string to encrypt.
 * @param key The key string to use for encryption.
 * @return A new, dynamically allocated string containing the encrypted message.
 *         The caller is responsible for freeing this memory with free().
 */
char* encrypt(const char* msg, const char* key) {
    // Start with an empty but valid C-string (contains only the null terminator).
    // We allocate 1 byte for the null terminator '\0'.
    char* temp = (char*)malloc(1);
    if (temp == NULL) {
        perror("Failed to allocate memory");
        return NULL;
    }
    temp[0] = '\0';

    size_t key_len = strlen(key);
    
    // Iterate over each character in the input message
    for (int i = 0; msg[i] != '\0'; i++) {
        // This is the C equivalent of the Python debug print: print(temp)
        // printf("%s\n", temp);

        size_t old_len = strlen(temp);
        
        // Calculate the size needed for the new string:
        // old_len + 1 (for the new character) + key_len + 1 (for the null terminator)
        size_t new_size = old_len + 1 + key_len + 1;

        // Reallocate the memory for 'temp' to fit the new content
        char* new_temp = (char*)realloc(temp, new_size);
        if (new_temp == NULL) {
            perror("Failed to reallocate memory");
            free(temp); // Free the old memory before returning
            return NULL;
        }
        temp = new_temp;

        // Append the character from the message
        temp[old_len] = msg[i];
        temp[old_len + 1] = '\0'; // Temporarily null-terminate for strcat

        // Append the key
        strcat(temp, key);
    }
    
    return temp;
}

/**
 * @brief Decrypts a message created by the encrypt function.
 * 
 * It reconstructs the original message by taking every other character,
 * starting with the first one. The key is not needed for this specific
 * decryption logic but is kept for function signature consistency.
 * 
 * @param msg The encrypted message string.
 * @param key The key (unused in this implementation).
 * @return A new, dynamically allocated string containing the decrypted message.
 *         The caller is responsible for freeing this memory with free().
 */
char* decrypt(const char* msg, const char* key) {
    // The key is not used in the decryption logic, but we include it
    // to match the function signature. This line prevents a compiler
    // warning about an unused parameter.
    (void)key;

    size_t msg_len = strlen(msg);
    
    // Allocate enough memory for the result. The decrypted message will be,
    // at most, the length of the encrypted one. We add +1 for the null terminator.
    char* temp = (char*)malloc(msg_len + 1);
    if (temp == NULL) {
        perror("Failed to allocate memory");
        return NULL;
    }

    int control = 0;
    int temp_index = 0; // Index for our new 'temp' string

    // Iterate over each character in the encrypted message
    for (int i = 0; i < msg_len; i++) {
        // If the character is at an even position (0, 2, 4, ...), add it to our result.
        if (control % 2 == 0) {
            temp[temp_index] = msg[i];
            temp_index++;
        }
        control++;
    }
    
    // Add the null terminator to make it a valid C-string
    temp[temp_index] = '\0';
    
    return temp;
}


int main() {
    // --- Test Case 1 ---
    printf("Encrypting 'a' with key 'b'...\n");
    // The 'encrypt' function returns memory that we now own.
    char* encrypted_message = encrypt("a", "b"); 
    if (encrypted_message) {
        printf("Result: %s\n", encrypted_message);
        // We must free the memory to prevent a memory leak.
        free(encrypted_message);
    }
    printf("\n");

    // --- Test Case 2 ---
    printf("Decrypting 'ab' with key 'b'...\n");
    // The 'decrypt' function also returns memory we must manage.
    char* decrypted_message = decrypt("ab", "b");
    if (decrypted_message) {
        printf("Result: %s\n", decrypted_message);
        // Free the memory.
        free(decrypted_message);
    }
    printf("\n");

    // --- A more complex example ---
    printf("Encrypting 'hello' with key 'x'...\n");
    encrypted_message = encrypt("hello", "x");
    if (encrypted_message) {
        printf("Result: %s\n", encrypted_message); // Expected: hxexlxlxox
        
        printf("Decrypting the result...\n");
        decrypted_message = decrypt(encrypted_message, "x");
        if (decrypted_message) {
            printf("Result: %s\n", decrypted_message); // Expected: hello
            free(decrypted_message);
        }
        
        free(encrypted_message);
    }

    return 0;
}