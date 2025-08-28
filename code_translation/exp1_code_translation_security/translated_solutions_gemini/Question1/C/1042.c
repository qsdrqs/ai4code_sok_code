#include <stdio.h>
#include <stdlib.h> // For malloc(), free()
#include <string.h> // For strlen(), strcpy()
#include <ctype.h>  // For isalpha(), islower()

// Forward declarations
char* encrypt(int key, const char* string);
char* decrypt(const char* ciphertext, const char* key);

/**
 * @brief Encrypts a string using a Caesar cipher.
 *
 * @param key The integer key to shift letters by.
 * @param string The input string to encrypt.
 * @return A new dynamically allocated string with the encrypted content,
 *         or NULL if an error occurs (invalid input).
 * @note The caller is responsible for freeing the returned string using free().
 */
char* encrypt(int key, const char* string) {
    // In C, type checks are implicit in the function signature.
    // We check for NULL pointers and invalid values.
    if (string == NULL || key < 0 || strlen(string) == 0) {
        return NULL;
    }

    // Normalize key to be within the 0-25 range
    if (key >= 26) {
        key = key % 26;
    }

    size_t len = strlen(string);
    // Allocate memory for the new string (+1 for the null terminator)
    char* new_string = (char*)malloc(len + 1);
    if (new_string == NULL) {
        // Failed to allocate memory
        return NULL;
    }

    for (size_t i = 0; i < len; i++) {
        char c = string[i];
        if (isalpha(c)) {
            // Determine the base character ('a' for lowercase, 'A' for uppercase)
            char base = islower(c) ? 'a' : 'A';
            // Apply the shift
            new_string[i] = (c - base + key) % 26 + base;
        } else {
            // Copy non-alphabetic characters as is
            new_string[i] = c;
        }
    }

    // Add the null terminator to mark the end of the string
    new_string[len] = '\0';

    return new_string;
}

/**
 * @brief Decrypts a given string using a Vigenère-like cipher.
 *
 * @param ciphertext The string to be decrypted.
 * @param key The symmetric key string used for decryption.
 * @return A new dynamically allocated string with the decrypted content,
 *         or NULL if an error occurs (invalid input).
 * @note The Python implementation has unusual logic (e.g., always maps to uppercase).
 *       This C code is a direct translation of that logic.
 * @note The caller is responsible for freeing the returned string using free().
 */
char* decrypt(const char* ciphertext, const char* key) {
    // Check for NULL pointers or empty strings
    if (ciphertext == NULL || key == NULL || strlen(ciphertext) == 0 || strlen(key) == 0) {
        return NULL;
    }

    size_t ciphertext_len = strlen(ciphertext);
    size_t key_len = strlen(key);

    if (key_len > ciphertext_len) {
        return NULL;
    }

    // This condition is strange, but we replicate it from the Python code.
    // We return a copy so the caller can always safely free the result.
    if (key_len == 1) {
        char* copy = (char*)malloc(ciphertext_len + 1);
        if (copy == NULL) return NULL;
        strcpy(copy, ciphertext);
        return copy;
    }

    // Allocate memory for the plaintext
    char* plaintext = (char*)malloc(ciphertext_len + 1);
    if (plaintext == NULL) {
        return NULL;
    }

    for (size_t i = 0; i < ciphertext_len; i++) {
        // Perform the subtraction
        int value = ciphertext[i] - key[i % key_len];
        
        // The `(value % 26 + 26) % 26` trick ensures the result is always
        // a positive number in [0, 25], matching Python's % behavior.
        int decrypted_char_code = (value % 26 + 26) % 26;
        
        // The Python code adds 65 ('A'), so the plaintext is always uppercase.
        plaintext[i] = decrypted_char_code + 'A';
    }

    // Add the null terminator
    plaintext[ciphertext_len] = '\0';

    return plaintext;
}


// Main function to demonstrate usage
int main() {
    // --- Test encrypt function ---
    const char* original_text = "Hello, World! 123";
    int encryption_key = 3;
    
    char* encrypted_text = encrypt(encryption_key, original_text);
    
    if (encrypted_text != NULL) {
        printf("--- Encrypt Demo ---\n");
        printf("Original:  %s\n", original_text);
        printf("Key:       %d\n", encryption_key);
        printf("Encrypted: %s\n", encrypted_text);
        // IMPORTANT: Free the memory allocated by encrypt()
        free(encrypted_text);
    } else {
        printf("Encryption failed.\n");
    }

    printf("\n");

    // --- Test decrypt function ---
    const char* ciphertext_to_decrypt = "JQTQKYG"; // Example ciphertext
    const char* decryption_key = "SECRET";      // Example key
    
    char* decrypted_text = decrypt(ciphertext_to_decrypt, decryption_key);

    if (decrypted_text != NULL) {
        printf("--- Decrypt Demo ---\n");
        printf("Ciphertext: %s\n", ciphertext_to_decrypt);
        printf("Key:        %s\n", decryption_key);
        printf("Decrypted:  %s\n", decrypted_text);
        // IMPORTANT: Free the memory allocated by decrypt()
        free(decrypted_text);
    } else {
        printf("Decryption failed.\n");
    }

    return 0;
}