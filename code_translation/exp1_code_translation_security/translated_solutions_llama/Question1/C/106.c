#include <stdio.h>
#include <string.h>
#include <assert.h>

// Function to encrypt plaintext using a given key
void encrypt(const char *plaintext, const char *key, char *ciphertext) {
    int plaintext_len = strlen(plaintext);
    int key_len = strlen(key);
    for (int i = 0; i < plaintext_len; i++) {
        // Ensure character is in lowercase
        char c = plaintext[i];
        if (c >= 'A' && c <= 'Z') c += 'a' - 'A';
        if (c >= 'a' && c <= 'z') {
            ciphertext[i] = 'a' + (c - 'a' + key[i % key_len] - 'a') % 26;
        } else {
            // If character is not a letter, leave it as is
            ciphertext[i] = c;
        }
    }
    ciphertext[plaintext_len] = '\0'; // Null-terminate the string
}

// Function to decrypt ciphertext using a given key
void decrypt(const char *ciphertext, const char *key, char *plaintext) {
    int ciphertext_len = strlen(ciphertext);
    int key_len = strlen(key);
    for (int i = 0; i < ciphertext_len; i++) {
        // Ensure character is in lowercase
        char c = ciphertext[i];
        if (c >= 'A' && c <= 'Z') c += 'a' - 'A';
        if (c >= 'a' && c <= 'z') {
            plaintext[i] = 'a' + (c - 'a' - (key[i % key_len] - 'a') + 26) % 26;
        } else {
            // If character is not a letter, leave it as is
            plaintext[i] = c;
        }
    }
    plaintext[ciphertext_len] = '\0'; // Null-terminate the string
}

int main() {
    const char *plaintext = "helloworld";
    const char *key = "python";
    char ciphertext[strlen(plaintext) + 1];
    char decrypted[strlen(plaintext) + 1];

    encrypt(plaintext, key, ciphertext);
    printf("Ciphertext: %s\n", ciphertext);

    decrypt(ciphertext, key, decrypted);
    printf("Decrypted: %s\n", decrypted);

    assert(strcmp(plaintext, decrypted) == 0);

    return 0;
}