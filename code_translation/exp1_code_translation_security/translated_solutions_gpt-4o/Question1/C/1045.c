#include <stdio.h>
#include <string.h>

// Function to encrypt a given string
void encrypt(char* text, int key, char* encrypted_text) {
    int length = strlen(text);
    for (int i = 0; i < length; i++) {
        encrypted_text[i] = text[i] + key;
    }
    encrypted_text[length] = '\0'; // Null-terminate the string
}

// Function to decrypt a given string
void decrypt(char* text, int key, char* decrypted_text) {
    int length = strlen(text);
    for (int i = 0; i < length; i++) {
        decrypted_text[i] = text[i] - key;
    }
    decrypted_text[length] = '\0'; // Null-terminate the string
}

int main() {
    char text[] = "name";
    int key = 1234;
    char encrypted_text[100];
    char decrypted_text[100];

    encrypt(text, key, encrypted_text);
    printf("Encrypted: %s\n", encrypted_text);

    decrypt(encrypted_text, key, decrypted_text);
    printf("Decrypted: %s\n", decrypted_text);

    return 0;
}