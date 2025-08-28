#include <stdio.h>   // For printf
#include <stdlib.h>  // For malloc, free
#include <string.h>  // For strlen, strcmp
#include <assert.h>  // For assert

/**
 * @brief Encrypts a string with a given key using a repeating-key XOR cipher.
 *
 * @param string The input string to encrypt.
 * @param key The key to use for encryption.
 * @return A new, dynamically allocated string containing the encrypted text.
 *         The caller is responsible for freeing this memory with free().
 *         Returns NULL on memory allocation failure or if the key is NULL/empty.
 */
char* encrypt(const char* string, const char* key) {
    // Get the lengths of the input string and the key
    size_t string_len = strlen(string);
    size_t key_len = strlen(key);

    // An empty key cannot be used for encryption.
    if (key_len == 0) {
        fprintf(stderr, "Error: Key cannot be empty.\n");
        return NULL;
    }

    // Allocate memory for the new encrypted string.
    // The length is the same as the original, plus one for the null terminator.
    char* encrypted = (char*)malloc(string_len + 1);
    if (encrypted == NULL) {
        fprintf(stderr, "Error: Memory allocation failed.\n");
        return NULL; // Return NULL if malloc fails
    }

    // Iterate over the input string and apply the XOR cipher
    for (size_t i = 0; i < string_len; i++) {
        // The core logic: XOR the character from the string with a character from the key.
        // The modulo operator (%) wraps the key around if it's shorter than the string.
        encrypted[i] = string[i] ^ key[i % key_len];
    }

    // Add the null terminator to make it a valid C-string
    encrypted[string_len] = '\0';

    return encrypted;
}

/**
 * @brief Decrypts a string with a given key.
 *
 * @note For an XOR cipher, decryption is the same operation as encryption.
 *
 * @param string The encrypted string to decrypt.
 * @param key The key used for encryption.
 * @return A new, dynamically allocated string containing the decrypted text.
 *         The caller is responsible for freeing this memory with free().
 */
char* decrypt(const char* string, const char* key) {
    // Decryption is identical to encryption for a simple XOR cipher.
    return encrypt(string, key);
}

int main() {
    const char* original_string = "sadasdasdsa";
    const char* key = "1234";

    printf("Original String:  \"%s\"\n", original_string);
    printf("Key:              \"%s\"\n", key);

    // Encrypt the string
    char* encrypted_string = encrypt(original_string, key);
    if (encrypted_string == NULL) {
        return 1; // Exit with an error code
    }
    // Note: The encrypted string may contain non-printable characters,
    // so printing it might produce strange output.
    printf("Encrypted (raw):  \"%s\"\n", encrypted_string);

    // Decrypt the string
    char* decrypted_string = decrypt(encrypted_string, key);
    if (decrypted_string == NULL) {
        free(encrypted_string); // Clean up memory before exiting
        return 1; // Exit with an error code
    }
    printf("Decrypted String: \"%s\"\n", decrypted_string);

    // Assert that the decrypted string is identical to the original.
    // strcmp returns 0 if the strings are equal.
    assert(strcmp(decrypted_string, original_string) == 0);

    printf("\nAssertion successful: The decrypted string matches the original.\n");

    // IMPORTANT: Free the memory allocated by encrypt() and decrypt()
    free(encrypted_string);
    free(decrypted_string);

    return 0; // Indicate successful execution
}