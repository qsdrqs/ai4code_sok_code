#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Forward declaration for the core processing function
char* process_string(const char* string, const char* key);

/**
 * @brief Encrypts a given string using a given symmetric key.
 *
 * @param string The plaintext string to encrypt.
 * @param key The symmetric key to use for encryption.
 * @return A new dynamically allocated string containing the encrypted text.
 *         The caller is responsible for freeing this memory with free().
 *         Returns NULL on error (e.g., memory allocation failure).
 */
char* encrypt(const char* string, const char* key) {
    return process_string(string, key);
}

/**
 * @brief Decrypts a given string using a given symmetric key.
 *
 * @param string The encrypted string to decrypt.
 * @param key The symmetric key to use for decryption.
 * @return A new dynamically allocated string containing the decrypted text.
 *         The caller is responsible for freeing this memory with free().
 *         Returns NULL on error (e.g., memory allocation failure).
 */
char* decrypt(const char* string, const char* key) {
    return process_string(string, key);
}

/**
 * @brief Performs a symmetric XOR cipher on a string.
 *        This is the core logic used by both encrypt() and decrypt().
 *
 * @param string The input string (can be plaintext or ciphertext).
 * @param key The symmetric key.
 * @return A new dynamically allocated string containing the result.
 *         The caller is responsible for freeing this memory with free().
 *         Returns NULL on error.
 */
char* process_string(const char* string, const char* key) {
    if (string == NULL || key == NULL) {
        return NULL;
    }

    size_t string_len = strlen(string);
    size_t key_len = strlen(key);

    // A key of length 0 would cause a division by zero error in the loop.
    if (key_len == 0) {
        fprintf(stderr, "Error: Key cannot be empty.\n");
        return NULL;
    }

    // Allocate memory for the new string.
    // We need space for all characters plus the null terminator ('\0').
    char* result = (char*)malloc(string_len + 1);
    if (result == NULL) {
        fprintf(stderr, "Error: Memory allocation failed.\n");
        return NULL;
    }

    // Loop through each character of the input string.
    for (size_t i = 0; i < string_len; i++) {
        // The core XOR logic.
        // We use the modulo operator (%) to loop through the key if it's
        // shorter than the string.
        result[i] = string[i] ^ key[i % key_len];
    }

    // Add the null terminator to make it a valid C-string.
    result[string_len] = '\0';

    return result;
}

// --- Main function for demonstration ---
int main() {
    const char* original_message = "This is a secret message.";
    const char* secret_key = "MySecretKey123";

    printf("Original:  \"%s\"\n", original_message);
    printf("Key:       \"%s\"\n\n", secret_key);

    // Encrypt the message
    char* encrypted_message = encrypt(original_message, secret_key);
    if (encrypted_message) {
        // Note: Encrypted strings can contain non-printable characters,
        // so printing them directly might look strange or do nothing.
        // For demonstration, we'll show it, but in a real application,
        // you would likely encode it (e.g., Base64) before printing or transmitting.
        printf("Encrypted: \"%s\" (raw output, may not be printable)\n", encrypted_message);

        // Decrypt the message
        char* decrypted_message = decrypt(encrypted_message, secret_key);
        if (decrypted_message) {
            printf("Decrypted: \"%s\"\n", decrypted_message);

            // Clean up the memory allocated for the decrypted message
            free(decrypted_message);
        }

        // Clean up the memory allocated for the encrypted message
        free(encrypted_message);
    }

    return 0;
}