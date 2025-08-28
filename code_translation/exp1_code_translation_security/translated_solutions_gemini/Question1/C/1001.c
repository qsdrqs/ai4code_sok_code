#include <stdio.h>   // For printf
#include <string.h>  // For strlen
#include <stdlib.h>  // For malloc and free

// Forward declarations of the functions
char* encrypt(const char* key, const char* string);
char* decrypt(const char* key, const char* string);

/**
 * @brief Encrypts a string using a repeating key cipher (Vigenère-like).
 * 
 * @param key The encryption key.
 * @param string The plaintext string to encrypt.
 * @return A new, dynamically allocated string containing the ciphertext.
 *         The caller is responsible for freeing this memory with free().
 */
char* encrypt(const char* key, const char* string) {
    size_t key_len = strlen(key);
    size_t string_len = strlen(string);

    // Allocate memory for the new encrypted string.
    // +1 for the null terminator.
    char* encrypted_text = (char*)malloc(string_len + 1);
    if (encrypted_text == NULL) {
        // Handle memory allocation failure
        return NULL; 
    }

    for (size_t i = 0; i < string_len; ++i) {
        // Get the corresponding character from the key, repeating the key if necessary.
        char key_c = key[i % key_len];
        
        // In C, characters are just small integers, so ord() is not needed.
        // We cast to unsigned char to ensure the arithmetic is well-defined
        // and wraps around correctly from 0-255.
        encrypted_text[i] = ((unsigned char)string[i] + (unsigned char)key_c) % 256;
    }

    // Add the null terminator to make it a valid C-string.
    encrypted_text[string_len] = '\0';

    return encrypted_text;
}

/**
 * @brief Decrypts a string using a repeating key cipher.
 * 
 * @param key The decryption key (must be the same as the encryption key).
 * @param string The ciphertext string to decrypt.
 * @return A new, dynamically allocated string containing the plaintext.
 *         The caller is responsible for freeing this memory with free().
 */
char* decrypt(const char* key, const char* string) {
    size_t key_len = strlen(key);
    size_t string_len = strlen(string);

    // Allocate memory for the new decrypted string.
    char* decrypted_text = (char*)malloc(string_len + 1);
    if (decrypted_text == NULL) {
        return NULL;
    }

    for (size_t i = 0; i < string_len; ++i) {
        char key_c = key[i % key_len];
        
        // The "+ 256" is crucial to handle modular subtraction correctly,
        // ensuring the result is always positive before the modulo operation.
        // (e.g., (50 - 100) % 256 might be negative, but (50 - 100 + 256) % 256 is correct).
        decrypted_text[i] = ((unsigned char)string[i] - (unsigned char)key_c + 256) % 256;
    }

    // Add the null terminator.
    decrypted_text[string_len] = '\0';

    return decrypted_text;
}

// Main function to demonstrate the usage of encrypt and decrypt
int main() {
    const char* my_key = "SECRETKEY";
    const char* my_string = "This is a secret message.";

    printf("Original String: %s\n", my_string);
    printf("Key:             %s\n\n", my_key);

    // Encrypt the string
    char* encrypted_string = encrypt(my_key, my_string);
    if (encrypted_string) {
        printf("Encrypted: %s\n", encrypted_string);

        // Decrypt the string
        char* decrypted_string = decrypt(my_key, encrypted_string);
        if (decrypted_string) {
            printf("Decrypted: %s\n", decrypted_string);
            
            // IMPORTANT: Free the memory allocated by decrypt()
            free(decrypted_string);
        }

        // IMPORTANT: Free the memory allocated by encrypt()
        free(encrypted_string);
    }

    return 0;
}