#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// Function to encrypt a given string using a given symmetric key
char* encrypt(const char* string, const char* key) {
    int string_len = strlen(string);
    int key_len = strlen(key);
    char* encrypted_string = (char*) malloc((string_len + 1) * sizeof(char));

    for (int i = 0; i < string_len; i++) {
        encrypted_string[i] = string[i] ^ key[i % key_len];
    }
    encrypted_string[string_len] = '\0'; // Null-terminate the string

    return encrypted_string;
}

// Function to decrypt a given string using a given symmetric key
char* decrypt(const char* string, const char* key) {
    int string_len = strlen(string);
    int key_len = strlen(key);
    char* decrypted_string = (char*) malloc((string_len + 1) * sizeof(char));

    for (int i = 0; i < string_len; i++) {
        decrypted_string[i] = string[i] ^ key[i % key_len];
    }
    decrypted_string[string_len] = '\0'; // Null-terminate the string

    return decrypted_string;
}

int main() {
    const char* string = "Hello, World!";
    const char* key = "secret";

    char* encrypted = encrypt(string, key);
    printf("Encrypted string: %s\n", encrypted);

    char* decrypted = decrypt(encrypted, key);
    printf("Decrypted string: %s\n", decrypted);

    free(encrypted);
    free(decrypted);

    return 0;
}