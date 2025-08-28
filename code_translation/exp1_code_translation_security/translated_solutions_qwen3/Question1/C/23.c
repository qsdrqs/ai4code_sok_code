#include <stdio.h>
#include <string.h>

/**
 * Encrypts the input text using a key by adding the ASCII values of corresponding characters.
 * 
 * @param text   The input text to encrypt.
 * @param key    The key used for encryption (must be same length as text).
 * @param result The buffer to store the encrypted result.
 */
void encrypt(const char* text, const char* key, char* result) {
    size_t len = strlen(text);
    for (size_t i = 0; i < len; i++) {
        // Cast to unsigned char to ensure correct arithmetic
        result[i] = (char)((unsigned char)text[i] + (unsigned char)key[i]);
    }
    result[len] = '\0';  // Null-terminate the result
}

/**
 * Decrypts the encrypted text using the key by subtracting the ASCII values of corresponding characters.
 * 
 * @param text   The encrypted text to decrypt.
 * @param key    The key used for decryption (must be same length as text).
 * @param result The buffer to store the decrypted result.
 */
void decrypt(const char* text, const char* key, char* result) {
    size_t len = strlen(text);
    for (size_t i = 0; i < len; i++) {
        // Cast to unsigned char to ensure correct arithmetic
        result[i] = (char)((unsigned char)text[i] - (unsigned char)key[i]);
    }
    result[len] = '\0';  // Null-terminate the result
}

int main() {
    const char* key = "aaaaaaa";       // Key must be same length as text
    const char* decrypted = "abcdefg"; // Original text
    char encrypted[8];                 // Buffer for encrypted text (7 chars + null)
    char decrypted_result[8];          // Buffer for decrypted result

    // Encrypt the text
    encrypt(decrypted, key, encrypted);
    printf("Encrypted: %s\n", encrypted);  // May not display correctly if non-printable

    // Decrypt the encrypted text
    decrypt(encrypted, key, decrypted_result);
    printf("Decrypted: %s\n", decrypted_result);  // Should print "abcdefg"

    return 0;
}