#include <stdio.h>
#include <string.h>
#include <assert.h>

/**
 * Encrypts the given plaintext using the Vigenère cipher with the provided key.
 *
 * @param plaintext The input string to encrypt (must be lowercase letters only).
 * @param key       The key string (must be lowercase letters only).
 * @param ciphertext Output buffer to store the encrypted string.
 */
void encrypt(const char *plaintext, const char *key, char *ciphertext) {
    size_t plain_len = strlen(plaintext);
    size_t key_len = strlen(key);

    for (size_t i = 0; i < plain_len; i++) {
        size_t j = i % key_len;
        ciphertext[i] = ((plaintext[i] + key[j] - 2 * 'a') % 26) + 'a';
    }

    ciphertext[plain_len] = '\0';  // Null-terminate the result
}

/**
 * Decrypts the given ciphertext using the Vigenère cipher with the provided key.
 *
 * @param ciphertext The encrypted string to decrypt.
 * @param key         The key string (must be lowercase letters only).
 * @param plaintext   Output buffer to store the decrypted string.
 */
void decrypt(const char *ciphertext, const char *key, char *plaintext) {
    size_t cipher_len = strlen(ciphertext);
    size_t key_len = strlen(key);

    for (size_t i = 0; i < cipher_len; i++) {
        size_t j = i % key_len;
        plaintext[i] = ((ciphertext[i] - key[j] + 26) % 26) + 'a';
    }

    plaintext[cipher_len] = '\0';  // Null-terminate the result
}

/**
 * Main function to demonstrate encryption and decryption.
 */
int main() {
    const char *plaintext = "helloworld";
    const char *key = "python";

    // Allocate space for ciphertext and decrypted text
    char ciphertext[11];  // 10 characters + null terminator
    char decrypted[11];

    // Perform encryption
    encrypt(plaintext, key, ciphertext);
    printf("Ciphertext: %s\n", ciphertext);

    // Perform decryption
    decrypt(ciphertext, key, decrypted);
    printf("Decrypted: %s\n", decrypted);

    // Verify correctness
    assert(strcmp(plaintext, decrypted) == 0);

    return 0;
}