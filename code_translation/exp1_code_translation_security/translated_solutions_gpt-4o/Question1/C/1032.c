#include <stdio.h>
#include <string.h>

// Function to encrypt a string using a given key
char* encrypt(const char* string, int key) {
    static char encrypted[256]; // Static to return a pointer
    int i;
    for (i = 0; i < strlen(string); i++) {
        encrypted[i] = string[i] + key;
    }
    encrypted[i] = '\0'; // Null-terminate the string
    return encrypted;
}

// Function to decrypt a string using a given key
char* decrypt(const char* string, int key) {
    static char decrypted[256]; // Static to return a pointer
    int i;
    for (i = 0; i < strlen(string); i++) {
        decrypted[i] = string[i] - key;
    }
    decrypted[i] = '\0'; // Null-terminate the string
    return decrypted;
}

int main() {
    const char* string = "Hello World!";
    int key = 5;
    char* encrypted = encrypt(string, key);
    char* decrypted = decrypt(encrypted, key);

    printf("Encrypted: %s\n", encrypted);
    printf("Decrypted: %s\n", decrypted);

    return 0;
}