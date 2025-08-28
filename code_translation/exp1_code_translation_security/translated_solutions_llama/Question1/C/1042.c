#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

// Function to encrypt a string using a Caesar cipher
char* encrypt(int key, const char* string) {
    // Check for invalid inputs
    if (string == NULL || *string == '\0') {
        return NULL;
    }
    if (key < 0) {
        return NULL;
    }
    if (key > 26) {
        key = key % 26;
    }

    // Calculate the length of the input string
    int length = strlen(string);

    // Allocate memory for the new string
    char* new_string = (char*) malloc((length + 1) * sizeof(char));
    if (new_string == NULL) {
        return NULL;
    }

    // Initialize the new string
    new_string[0] = '\0';

    // Iterate over each character in the string
    for (int i = 0; i < length; i++) {
        char c = string[i];
        if (isalpha(c)) {
            if (islower(c)) {
                new_string[i] = ((c - 97 + key) % 26) + 97;
            } else {
                new_string[i] = ((c - 65 + key) % 26) + 65;
            }
        } else {
            new_string[i] = c;
        }
    }

    // Null-terminate the new string
    new_string[length] = '\0';

    return new_string;
}

// Function to decrypt a string using a Vigenère cipher
char* decrypt(const char* ciphertext, const char* key) {
    // Check for invalid inputs
    if (ciphertext == NULL || *ciphertext == '\0' || key == NULL || *key == '\0') {
        return NULL;
    }
    if (strlen(key) == 1) {
        // If key length is 1, return ciphertext as it is (no decryption needed)
        char* plaintext = (char*) malloc((strlen(ciphertext) + 1) * sizeof(char));
        strcpy(plaintext, ciphertext);
        return plaintext;
    }
    if (strlen(key) > strlen(ciphertext)) {
        return NULL;
    }

    // Calculate the lengths of the input strings
    int ciphertext_len = strlen(ciphertext);
    int key_len = strlen(key);

    // Allocate memory for the plaintext
    char* plaintext = (char*) malloc((ciphertext_len + 1) * sizeof(char));
    if (plaintext == NULL) {
        return NULL;
    }

    // Initialize the plaintext
    plaintext[0] = '\0';

    // Iterate over each character in the ciphertext
    for (int i = 0; i < ciphertext_len; i++) {
        char c = ciphertext[i];
        if (isalpha(c)) {
            int shift = toupper(key[i % key_len]) - 65;
            if (islower(c)) {
                plaintext[i] = ((c - 97 - shift + 26) % 26) + 97;
            } else {
                plaintext[i] = ((c - 65 - shift + 26) % 26) + 65;
            }
        } else {
            plaintext[i] = c;
        }
    }

    // Null-terminate the plaintext
    plaintext[ciphertext_len] = '\0';

    return plaintext;
}

int main() {
    // Example usage:
    char* encrypted = encrypt(3, "Hello, World!");
    printf("Encrypted: %s\n", encrypted);
    free(encrypted);

    char* decrypted = decrypt(encrypted, "test"); // Note: decryption using "test" as key won't work as expected
    printf("Decrypted: %s\n", decrypted);
    free(decrypted);

    return 0;
}