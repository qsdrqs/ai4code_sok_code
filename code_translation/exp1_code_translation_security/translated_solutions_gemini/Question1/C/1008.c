#include <stdio.h>  // For printf
#include <string.h> // For strlen
#include <stdlib.h> // For malloc and free

/**
 * @brief Encrypts a plaintext string using a repeating key.
 *
 * @param key The encryption key.
 * @param plaintext The string to encrypt.
 * @return A new, dynamically allocated string containing the ciphertext.
 *         The caller is responsible for freeing this memory with free().
 *         Returns NULL on memory allocation failure.
 */
char* encrypt(const char* key, const char* plaintext) {
    size_t key_len = strlen(key);
    size_t text_len = strlen(plaintext);

    // Allocate memory for the ciphertext (+1 for the null terminator)
    char* ciphertext = (char*)malloc(text_len + 1);
    if (ciphertext == NULL) {
        // Handle memory allocation failure
        return NULL;
    }

    for (size_t i = 0; i < text_len; ++i) {
        char key_c = key[i % key_len];
        // In C, chars are just small integers, so ord() is implicit.
        // The modulo 256 ensures the result is a valid byte.
        ciphertext[i] = (plaintext[i] + key_c) % 256;
    }

    // Add the null terminator to make it a valid C string
    ciphertext[text_len] = '\0';

    return ciphertext;
}

/**
 * @brief Decrypts a ciphertext string using a repeating key.
 *
 * @param key The decryption key.
 * @param ciphertext The string to decrypt.
 * @return A new, dynamically allocated string containing the plaintext.
 *         The caller is responsible for freeing this memory with free().
 *         Returns NULL on memory allocation failure.
 */
char* decrypt(const char* key, const char* ciphertext) {
    size_t key_len = strlen(key);
    size_t text_len = strlen(ciphertext);

    // Allocate memory for the plaintext (+1 for the null terminator)
    char* plaintext = (char*)malloc(text_len + 1);
    if (plaintext == NULL) {
        // Handle memory allocation failure
        return NULL;
    }

    for (size_t i = 0; i < text_len; ++i) {
        char key_c = key[i % key_len];
        // The `(256 + ...)` trick prevents negative results from the subtraction
        // before the modulo operation, which is equivalent to Python's % behavior.
        plaintext[i] = (256 + (unsigned char)ciphertext[i] - (unsigned char)key_c) % 256;
    }

    // Add the null terminator
    plaintext[text_len] = '\0';

    return plaintext;
}

int main() {
    const char* key = "123";
    const char* original_text = "helloworld";

    printf("Original Text:  %s\n", original_text);

    // Encrypt the text
    char* encrypted_text = encrypt(key, original_text);
    if (encrypted_text == NULL) {
        fprintf(stderr, "Encryption failed due to memory allocation error.\n");
        return 1;
    }
    // Note: Encrypted text may contain non-printable characters,
    // so printing it might look strange or terminate early.
    // For this example, it's shown for completeness.
    printf("Encrypted (raw): %s\n", encrypted_text);

    // Decrypt the text
    char* decrypted_text = decrypt(key, encrypted_text);
    if (decrypted_text == NULL) {
        fprintf(stderr, "Decryption failed due to memory allocation error.\n");
        free(encrypted_text); // Clean up previous allocation
        return 1;
    }
    printf("Decrypted Text: %s\n", decrypted_text);

    // Clean up: free the memory allocated by encrypt and decrypt
    free(encrypted_text);
    free(decrypted_text);

    return 0;
}