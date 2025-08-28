#include <stdio.h>  // For printf
#include <string.h> // For strlen
#include <stdlib.h> // For malloc and free

/**
 * @brief Encrypts a message using a key with XOR encryption.
 * 
 * @param message The string to be encrypted.
 * @param key The key to use for encryption.
 * @return A new dynamically allocated string containing the encrypted message.
 *         The caller is responsible for freeing this memory.
 *         Returns NULL if memory allocation fails or the key is empty.
 */
char* encrypt(const char* message, const char* key) {
    size_t message_len = strlen(message);
    size_t key_len = strlen(key);

    // An empty key would cause a division by zero error in the loop.
    if (key_len == 0) {
        fprintf(stderr, "Error: Key cannot be empty.\n");
        return NULL;
    }

    // Allocate memory for the new encrypted string (+1 for the null terminator).
    char* encrypted = (char*)malloc(message_len + 1);
    if (encrypted == NULL) {
        fprintf(stderr, "Error: Memory allocation failed.\n");
        return NULL; // Failed to allocate memory
    }

    for (size_t i = 0; i < message_len; ++i) {
        // XOR the character from the message with the corresponding character from the key.
        // The modulo operator (%) wraps the key if it's shorter than the message.
        encrypted[i] = message[i] ^ key[i % key_len];
    }

    // Add the null terminator to make it a valid C-string.
    encrypted[message_len] = '\0';

    return encrypted;
}

/**
 * @brief Decrypts a message using a key with XOR encryption.
 * 
 * @note For XOR encryption, the decryption process is identical to the encryption process.
 * 
 * @param encrypted The encrypted string to be decrypted.
 * @param key The key used for encryption.
 * @return A new dynamically allocated string containing the decrypted message.
 *         The caller is responsible for freeing this memory.
 */
char* decrypt(const char* encrypted, const char* key) {
    // Decryption is the same as encryption for XOR.
    return encrypt(encrypted, key);
}

int main() {
    const char* original_message = "Hello world";
    const char* secret_key = "key";

    // Encrypt the message
    char* encrypted_message = encrypt(original_message, secret_key);
    if (encrypted_message == NULL) {
        return 1; // Exit with an error code
    }

    // Decrypt the message
    char* decrypted_message = decrypt(encrypted_message, secret_key);
    if (decrypted_message == NULL) {
        free(encrypted_message); // Clean up previous allocation
        return 1; // Exit with an error code
    }

    // Print the final decrypted message to verify it matches the original
    printf("%s\n", decrypted_message);

    // Clean up: free the dynamically allocated memory
    free(encrypted_message);
    free(decrypted_message);

    return 0; // Success
}