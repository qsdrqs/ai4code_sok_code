#include <stdio.h>
#include <string.h>

// Function to encrypt a string using a given key
void encrypt(const char *string, int key, char *encrypted) {
    int i;
    for (i = 0; i < strlen(string); i++) {
        encrypted[i] = string[i] + key;
    }
    encrypted[i] = '\0'; // Null-terminate the encrypted string
}

// Function to decrypt a string using a given key
void decrypt(const char *string, int key, char *decrypted) {
    int i;
    for (i = 0; i < strlen(string); i++) {
        decrypted[i] = string[i] - key;
    }
    decrypted[i] = '\0'; // Null-terminate the decrypted string
}

// Main function
int main() {
    const char *string = "Hello World!";
    int key = 5;
    char encrypted[50]; // Buffer to hold the encrypted string
    char decrypted[50]; // Buffer to hold the decrypted string

    encrypt(string, key, encrypted);
    decrypt(encrypted, key, decrypted);

    printf("Encrypted: %s\n", encrypted);
    printf("Decrypted: %s\n", decrypted);

    return 0;
}