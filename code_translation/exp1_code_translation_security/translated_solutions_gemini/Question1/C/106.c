#include <stdio.h>   // For printf
#include <string.h>  // For strlen, strcmp
#include <stdlib.h>  // For malloc, free
#include <assert.h>  // For assert

/**
 * @brief Encrypts a plaintext string using a Vigenère cipher.
 *
 * @param plaintext The string to encrypt (assumed to be lowercase a-z).
 * @param key The key to use for encryption (assumed to be lowercase a-z).
 * @return A new, dynamically allocated string containing the ciphertext.
 *         The caller is responsible for freeing this memory with free().
 */
char* encrypt(const char* plaintext, const char* key) {
    size_t text_len = strlen(plaintext);
    size_t key_len = strlen(key);

    // Allocate memory for the ciphertext. +1 for the null terminator.
    char* ciphertext = (char*)malloc(text_len + 1);
    if (ciphertext == NULL) {
        // Handle memory allocation failure
        return NULL;
    }

    for (size_t i = 0; i < text_len; ++i) {
        // The core logic is a direct translation from the Python code.
        // In C, characters can be treated as integers (their ASCII values),
        // so ord() is not needed.
        char p = plaintext[i];
        char k = key[i % key_len];
        
        // (p-'a') converts char to 0-25 range.
        // Add the key value, wrap around 26, and convert back to a char.
        ciphertext[i] = ((p - 'a') + (k - 'a')) % 26 + 'a';
    }

    // Add the null terminator to mark the end of the string.
    ciphertext[text_len] = '\0';

    return ciphertext;
}

/**
 * @brief Decrypts a ciphertext string using a Vigenère cipher.
 *
 * @param ciphertext The string to decrypt (assumed to be lowercase a-z).
 * @param key The key used for encryption (assumed to be lowercase a-z).
 * @return A new, dynamically allocated string containing the plaintext.
 *         The caller is responsible for freeing this memory with free().
 */
char* decrypt(const char* ciphertext, const char* key) {
    size_t text_len = strlen(ciphertext);
    size_t key_len = strlen(key);

    // Allocate memory for the plaintext. +1 for the null terminator.
    char* plaintext = (char*)malloc(text_len + 1);
    if (plaintext == NULL) {
        // Handle memory allocation failure
        return NULL;
    }

    for (size_t i = 0; i < text_len; ++i) {
        char c = ciphertext[i];
        char k = key[i % key_len];

        // (c-'a') converts char to 0-25 range.
        // Subtract the key value. The "+ 26" ensures the result is
        // non-negative before the modulo operation.
        plaintext[i] = ((c - 'a') - (k - 'a') + 26) % 26 + 'a';
    }

    // Add the null terminator.
    plaintext[text_len] = '\0';

    return plaintext;
}

int main() {
    const char* plaintext = "helloworld";
    const char* key = "python";

    // Encrypt the plaintext
    char* ciphertext = encrypt(plaintext, key);
    if (ciphertext) {
        printf("Ciphertext: %s\n", ciphertext);
    }

    // Decrypt the ciphertext
    char* decrypted = decrypt(ciphertext, key);
    if (decrypted) {
        printf("Decrypted: %s\n", decrypted);
    }

    // Assert that the original plaintext and the decrypted text are the same.
    // strcmp returns 0 if the strings are equal.
    assert(strcmp(plaintext, decrypted) == 0);
    
    printf("Assertion passed: Original plaintext matches decrypted text.\n");

    // IMPORTANT: Free the memory allocated by encrypt() and decrypt()
    free(ciphertext);
    free(decrypted);

    return 0;
}