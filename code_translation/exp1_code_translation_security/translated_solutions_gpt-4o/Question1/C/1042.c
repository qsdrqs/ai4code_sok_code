#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

// Function to encrypt a string using a key
char* encrypt(int key, const char* string) {
    if (key < 0 || string == NULL || strlen(string) == 0) {
        return NULL;
    }

    // Adjust key if it's greater than 26
    if (key > 26) {
        key = key % 26;
    }

    // Allocate memory for the new string
    char* new_string = (char*)malloc(strlen(string) + 1);
    if (new_string == NULL) {
        return NULL; // Memory allocation failed
    }

    int i = 0;
    while (string[i] != '\0') {
        if (isalpha(string[i])) {
            if (islower(string[i])) {
                new_string[i] = ((string[i] - 'a' + key) % 26) + 'a';
            } else {
                new_string[i] = ((string[i] - 'A' + key) % 26) + 'A';
            }
        } else {
            new_string[i] = string[i];
        }
        i++;
    }

    new_string[i] = '\0'; // Null-terminate the string
    return new_string;
}

// Function to decrypt a string using a symmetric key
char* decrypt(const char* ciphertext, const char* key) {
    if (ciphertext == NULL || key == NULL || strlen(ciphertext) == 0 || strlen(key) == 0) {
        return NULL;
    }

    int key_len = strlen(key);
    int ciphertext_len = strlen(ciphertext);

    if (key_len == 1) {
        // If the key length is 1, return the ciphertext as is
        char* plaintext = (char*)malloc(ciphertext_len + 1);
        if (plaintext == NULL) {
            return NULL; // Memory allocation failed
        }
        strcpy(plaintext, ciphertext);
        return plaintext;
    }

    if (key_len > ciphertext_len) {
        return NULL;
    }

    // Allocate memory for the plaintext
    char* plaintext = (char*)malloc(ciphertext_len + 1);
    if (plaintext == NULL) {
        return NULL; // Memory allocation failed
    }

    int i;
    for (i = 0; i < ciphertext_len; i++) {
        int key_ascii = key[i % key_len];
        int ciphertext_ascii = ciphertext[i];
        int value = (ciphertext_ascii - key_ascii) % 26;
        if (value < 0) {
            value += 26; // Handle negative values
        }
        plaintext[i] = value + 'A';
    }

    plaintext[i] = '\0'; // Null-terminate the string
    return plaintext;
}

// Main function for testing
int main() {
    // Test the encrypt function
    char* encrypted = encrypt(3, "Hello, World!");
    if (encrypted != NULL) {
        printf("Encrypted: %s\n", encrypted);
        free(encrypted); // Free the allocated memory
    } else {
        printf("Encryption failed.\n");
    }

    // Test the decrypt function
    char* decrypted = decrypt("KHOOR", "KEY");
    if (decrypted != NULL) {
        printf("Decrypted: %s\n", decrypted);
        free(decrypted); // Free the allocated memory
    } else {
        printf("Decryption failed.\n");
    }

    return 0;
}