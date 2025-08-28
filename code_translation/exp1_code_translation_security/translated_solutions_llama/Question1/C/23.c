#include <stdio.h>
#include <string.h>

// Function to encrypt text using a given key
char* encrypt(const char* text, const char* key, int text_len, int key_len) {
    char* result = (char*) malloc((text_len + 1) * sizeof(char));
    for (int i = 0; i < text_len; i++) {
        // Calculate the ASCII value of the encrypted character
        int this_char = (text[i] + key[i % key_len]) % 256;
        result[i] = (char) this_char;
    }
    result[text_len] = '\0'; // Null-terminate the string
    return result;
}

// Function to decrypt text using a given key
char* decrypt(const char* text, const char* key, int text_len, int key_len) {
    char* result = (char*) malloc((text_len + 1) * sizeof(char));
    for (int i = 0; i < text_len; i++) {
        // Calculate the ASCII value of the decrypted character
        int this_char = (text[i] - key[i % key_len] + 256) % 256;
        result[i] = (char) this_char;
    }
    result[text_len] = '\0'; // Null-terminate the string
    return result;
}

int main() {
    const char* key = "aaaaaaa";
    const char* decrypted = "abcdefg";

    int text_len = strlen(decrypted);
    int key_len = strlen(key);

    // Encrypt the decrypted text
    char* encrypted = encrypt(decrypted, key, text_len, key_len);
    printf("Encrypted: %s\n", encrypted);

    // Decrypt the encrypted text
    char* decrypted_again = decrypt(encrypted, key, text_len, key_len);
    printf("Decrypted: %s\n", decrypted_again);

    // Free allocated memory
    free(encrypted);
    free(decrypted_again);

    return 0;
}