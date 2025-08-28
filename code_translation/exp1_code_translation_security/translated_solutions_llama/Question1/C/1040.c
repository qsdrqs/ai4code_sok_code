#include <stdio.h>
#include <string.h>
#include <assert.h>

// Function to encrypt a string with a given key
char* encrypt(const char* string, const char* key) {
    int string_len = strlen(string);
    int key_len = strlen(key);
    char* encrypted = (char*) malloc((string_len + 1) * sizeof(char));

    for (int i = 0; i < string_len; i++) {
        encrypted[i] = (char) ((unsigned char) string[i] ^ (unsigned char) key[i % key_len]);
    }
    encrypted[string_len] = '\0'; // Null-terminate the string

    return encrypted;
}

// Function to decrypt a string with a given key
char* decrypt(const char* string, const char* key) {
    return encrypt(string, key);
}

int main() {
    const char* original_string = "sadasdasdsa";
    const char* key = "1234";

    char* encrypted_string = encrypt(original_string, key);
    char* decrypted_string = decrypt(encrypted_string, key);

    // Check if decryption was successful
    if (strcmp(original_string, decrypted_string) == 0) {
        printf("Decryption successful.\n");
    } else {
        printf("Decryption failed.\n");
    }

    free(encrypted_string);
    free(decrypted_string);

    return 0;
}