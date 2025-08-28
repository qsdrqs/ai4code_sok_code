#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// Function to encrypt a string using a given key
char* encrypt(const char* string, int key) {
    int length = strlen(string);
    char* encrypted = (char*) malloc((length + 1) * sizeof(char));
    for (int i = 0; i < length; i++) {
        encrypted[i] = (char) ((unsigned char) string[i] + key);
    }
    encrypted[length] = '\0'; // Null-terminate the string
    return encrypted;
}

// Function to decrypt a string using a given key
char* decrypt(const char* string, int key) {
    int length = strlen(string);
    char* decrypted = (char*) malloc((length + 1) * sizeof(char));
    for (int i = 0; i < length; i++) {
        decrypted[i] = (char) ((unsigned char) string[i] - key);
    }
    decrypted[length] = '\0'; // Null-terminate the string
    return decrypted;
}

int main() {
    const char* string = "Hello World!";
    int key = 5;
    char* encrypted = encrypt(string, key);
    char* decrypted = decrypt(encrypted, key);

    printf("Original: %s\n", string);
    printf("Encrypted: %s\n", encrypted);
    printf("Decrypted: %s\n", decrypted);

    // Don't forget to free the allocated memory
    free(encrypted);
    free(decrypted);

    return 0;
}