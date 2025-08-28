#include <stdio.h>
#include <string.h>

// Function to encrypt a given string using a given symmetric key
void encrypt(const char *string, const char *key, char *encrypted_string) {
    size_t string_len = strlen(string);
    size_t key_len = strlen(key);
    for (size_t i = 0; i < string_len; i++) {
        encrypted_string[i] = string[i] ^ key[i % key_len];
    }
    encrypted_string[string_len] = '\0'; // Null-terminate the encrypted string
}

// Function to decrypt a given string using a given symmetric key
void decrypt(const char *string, const char *key, char *decrypted_string) {
    size_t string_len = strlen(string);
    size_t key_len = strlen(key);
    for (size_t i = 0; i < string_len; i++) {
        decrypted_string[i] = string[i] ^ key[i % key_len];
    }
    decrypted_string[string_len] = '\0'; // Null-terminate the decrypted string
}

int main() {
    const char *original_string = "Hello, World!";
    const char *key = "key";
    char encrypted_string[256];
    char decrypted_string[256];

    encrypt(original_string, key, encrypted_string);
    printf("Encrypted: %s\n", encrypted_string);

    decrypt(encrypted_string, key, decrypted_string);
    printf("Decrypted: %s\n", decrypted_string);

    return 0;
}