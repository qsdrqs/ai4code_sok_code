#include <stdio.h>
#include <string.h>
#include <stdlib.h>

/**
 * @brief A helper function that performs an XOR cipher on a string.
 *        This function is used by both encrypt and decrypt.
 * 
 * @param input The string to process.
 * @param key The symmetric key to use for the cipher.
 * @return A new, dynamically allocated string containing the result.
 *         The caller is responsible for freeing this memory with free().
 *         Returns NULL if memory allocation fails or the key is empty.
 */
char* xor_cipher(const char* input, const char* key) {
    size_t input_len = strlen(input);
    size_t key_len = strlen(key);

    // An empty key is invalid for this operation.
    if (key_len == 0) {
        return NULL;
    }

    // Allocate memory for the output string.
    // It will have the same length as the input string, plus a null terminator.
    char* output = (char*)malloc(input_len + 1);
    if (output == NULL) {
        // Failed to allocate memory
        return NULL;
    }

    // Perform the XOR operation for each character.
    for (size_t i = 0; i < input_len; ++i) {
        // The key wraps around using the modulo operator.
        output[i] = input[i] ^ key[i % key_len];
    }

    // Add the null terminator to make it a valid C-string.
    output[input_len] = '\0';

    return output;
}

/**
 * @brief Encrypts a given string using a given symmetric key.
 * 
 * @param string The plaintext string to encrypt.
 * @param key The symmetric key.
 * @return A new, dynamically allocated string containing the encrypted text.
 *         The caller must free this memory.
 */
char* encrypt(const char* string, const char* key) {
    return xor_cipher(string, key);
}

/**
 * @brief Decrypts a given string using a given symmetric key.
 * 
 * @param string The encrypted string to decrypt.
 * @param key The symmetric key.
 * @return A new, dynamically allocated string containing the decrypted text.
 *         The caller must free this memory.
 */
char* decrypt(const char* string, const char* key) {
    // The XOR cipher is symmetric, so decryption is the same as encryption.
    return xor_cipher(string, key);
}

// --- Example Usage ---
int main() {
    const char* my_string = "Hello, World! This is a secret message.";
    const char* my_key = "SuperSecretKey123";

    printf("Original String:  %s\n", my_string);
    printf("Key:              %s\n\n", my_key);

    // Encrypt the string
    char* encrypted_string = encrypt(my_string, my_key);
    if (encrypted_string) {
        printf("Encrypted String: %s\n", encrypted_string);

        // Decrypt the string
        char* decrypted_string = decrypt(encrypted_string, my_key);
        if (decrypted_string) {
            printf("Decrypted String: %s\n", decrypted_string);
            
            // IMPORTANT: Free the memory allocated by decrypt()
            free(decrypted_string);
        }

        // IMPORTANT: Free the memory allocated by encrypt()
        free(encrypted_string);
    }

    return 0;
}