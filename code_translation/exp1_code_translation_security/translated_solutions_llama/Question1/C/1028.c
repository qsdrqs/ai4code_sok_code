#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

// Function to encrypt a string using a symmetric key
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

// Function to decrypt a string using a symmetric key
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
    const char* original_string = "Hello, World!";
    const char* symmetric_key = "secret";

    char* encrypted = encrypt(original_string, symmetric_key);
    printf("Encrypted string: ");
    for (int i = 0; i < strlen(encrypted); i++) {
        printf("%02x ", (unsigned char) encrypted[i]);
    }
    printf("\n");

    char* decrypted = decrypt(encrypted, symmetric_key);
    printf("Decrypted string: %s\n", decrypted);

    free(encrypted);
    free(decrypted);

    return 0;
}