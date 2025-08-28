#include <stdio.h>
#include <string.h>

// Function to encrypt a string with a given key
char* encrypt(const char* string, const char* key) {
    static char encrypted[256]; // Assuming the input string won't exceed 255 characters
    int string_len = strlen(string);
    int key_len = strlen(key);

    for (int i = 0; i < string_len; i++) {
        encrypted[i] = string[i] ^ key[i % key_len];
    }
    encrypted[string_len] = '\0'; // Null-terminate the encrypted string

    return encrypted;
}

// Function to decrypt a string with a given key
char* decrypt(const char* string, const char* key) {
    return encrypt(string, key); // Decryption is the same as encryption
}

int main() {
    const char* original = "sadasdasdsa";
    const char* key = "1234";

    char* encrypted = encrypt(original, key);
    char* decrypted = decrypt(encrypted, key);

    printf("Original: %s\n", original);
    printf("Encrypted: %s\n", encrypted);
    printf("Decrypted: %s\n", decrypted);

    // Assert that decryption of the encrypted string returns the original string
    if (strcmp(decrypted, original) == 0) {
        printf("Assertion passed: Decrypted string matches the original.\n");
    } else {
        printf("Assertion failed: Decrypted string does not match the original.\n");
    }

    return 0;
}