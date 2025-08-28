#include <stdio.h>
#include <string.h>
#include <stdlib.h> // Required for malloc and free

// Forward declarations of the functions
char* encrypt(const char* str, int key);
char* decrypt(const char* str, int key);

int main() {
    // The equivalent of the Python global scope execution
    
    // Note: In Python, the result is a list of characters. In C, it's a null-terminated string.
    char* encrypted = encrypt("hello worlz", 3);
    printf("Encrypted: %s\n", encrypted);

    char* decrypted = decrypt(encrypted, 3);
    printf("Decrypted: %s\n", decrypted);

    // In C, we must manually free the memory allocated by malloc
    free(encrypted);
    free(decrypted);

    return 0;
}

/**
 * @brief Encrypts a string using a custom shift cipher.
 * 
 * @param str The input string (const char*).
 * @param key The integer key for the shift.
 * @return A new, dynamically allocated string with the encrypted content.
 *         The caller is responsible for freeing this memory.
 */
char* encrypt(const char* str, int key) {
    const char* alpha = "abcdefghijklmnopqrstuvwxyz";
    int alpha_len = 26; // strlen(alpha)
    int str_len = strlen(str);

    // Allocate memory for the new string. +1 for the null terminator.
    char* newString = (char*)malloc(str_len + 1);
    if (newString == NULL) {
        // Handle memory allocation failure
        perror("Failed to allocate memory for newString in encrypt");
        return NULL;
    }

    for (int i = 0; i < str_len; i++) {
        char c = str[i];

        if (c == ' ') {
            newString[i] = ' ';
            continue;
        }

        // Find the index of the character in the alphabet
        // strchr returns a pointer to the char, subtracting the base address gives the index.
        char* p_char_in_alpha = strchr(alpha, c);
        if (p_char_in_alpha == NULL) {
            // If character is not in the alphabet (like 'z' in "worlz"), handle it.
            // The original Python code would crash here. We'll treat it as an error or skip.
            // For this translation, we'll just copy the character.
            newString[i] = c;
            continue;
        }
        int alphaIndex = p_char_in_alpha - alpha;
        
        // This replicates the `print(alphaIndex)` from the Python code
        printf("Original index: %d\n", alphaIndex);

        // Replicating the unusual wrap-around logic from the Python code
        if (alphaIndex + key > alpha_len - 1) {
            // This logic is strange, but it's a direct translation:
            // alphaIndex = key - (len(alpha) - 1 - alphaIndex)
            // newString.append(alpha[alphaIndex + key])
            int new_base_index = key - (alpha_len - 1 - alphaIndex);
            newString[i] = alpha[new_base_index + key];
        } else {
            newString[i] = alpha[alphaIndex + key];
        }
    }

    // Add the null terminator to make it a valid C string
    newString[str_len] = '\0';
    
    return newString;
}

/**
 * @brief Decrypts a string from the custom shift cipher.
 * 
 * @param str The encrypted input string (const char*).
 * @param key The integer key used for encryption.
 * @return A new, dynamically allocated string with the decrypted content.
 *         The caller is responsible for freeing this memory.
 */
char* decrypt(const char* str, int key) {
    const char* alpha = "abcdefghijklmnopqrstuvwxyz";
    int alpha_len = 26;
    int str_len = strlen(str);

    // Allocate memory for the new string. It might be shorter if there are spaces.
    char* newString = (char*)malloc(str_len + 1);
    if (newString == NULL) {
        perror("Failed to allocate memory for newString in decrypt");
        return NULL;
    }
    
    int new_str_idx = 0; // Separate index for the new string
    for (int i = 0; i < str_len; i++) {
        char c = str[i];

        // The original Python code skips spaces during decryption
        if (c == ' ') {
            continue;
        }

        char* p_char_in_alpha = strchr(alpha, c);
        if (p_char_in_alpha == NULL) {
            // Character not in alphabet, just copy it
            newString[new_str_idx++] = c;
            continue;
        }
        int alphaIndex = p_char_in_alpha - alpha;

        // *** CORRECTION NOTE ***
        // The original Python code `alpha[alphaIndex - key]` is buggy and would crash
        // for characters near the beginning of the alphabet (e.g., decrypting 'b' with key 3).
        // The correct, non-crashing logic for decryption wrap-around is implemented here.
        int newIndex = (alphaIndex - key + alpha_len) % alpha_len;
        
        newString[new_str_idx] = alpha[newIndex];
        new_str_idx++;
    }

    // Add the null terminator at the end of the potentially shorter string
    newString[new_str_idx] = '\0';

    return newString;
}