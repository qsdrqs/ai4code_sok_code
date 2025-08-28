#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// Function to encrypt a string using a provided key
char* encrypt(const char* key, const char* string) {
    int key_len = strlen(key);
    int string_len = strlen(string);
    char* encrypted_text = (char*) malloc((string_len + 1) * sizeof(char));

    for (int i = 0; i < string_len; i++) {
        char key_c = key[i % key_len];
        char encrypted_c = (string[i] + key_c) % 256;
        encrypted_text[i] = encrypted_c;
    }
    encrypted_text[string_len] = '\0'; // Null-terminate the string

    return encrypted_text;
}

// Function to decrypt a string using a provided key
char* decrypt(const char* key, const char* string) {
    int key_len = strlen(key);
    int string_len = strlen(string);
    char* decrypted_text = (char*) malloc((string_len + 1) * sizeof(char));

    for (int i = 0; i < string_len; i++) {
        char key_c = key[i % key_len];
        char decrypted_c = (string[i] - key_c + 256) % 256;
        decrypted_text[i] = decrypted_c;
    }
    decrypted_text[string_len] = '\0'; // Null-terminate the string

    return decrypted_text;
}

int main() {
    const char* key = "my_secret_key";
    const char* original_string = "Hello, World!";
    
    // Encrypt the string
    char* encrypted_string = encrypt(key, original_string);
    printf("Encrypted String: %s\n", encrypted_string);
    free(encrypted_string); // Don't forget to free the allocated memory

    // Decrypt the string
    char* decrypted_string = decrypt(key, encrypted_string);
    printf("Decrypted String: %s\n", decrypted_string);
    free(decrypted_string); // Don't forget to free the allocated memory

    return 0;
}