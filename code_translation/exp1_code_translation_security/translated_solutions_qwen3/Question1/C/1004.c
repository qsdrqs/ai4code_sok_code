#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function to encrypt a message using XOR with a repeating key
char* encrypt(const char* message, const char* key) {
    size_t msg_len = strlen(message);
    size_t key_len = strlen(key);
    char* encrypted = (char*)malloc(msg_len + 1);  // +1 for null terminator
    if (!encrypted) {
        return NULL;  // Memory allocation failed
    }

    for (size_t i = 0; i < msg_len; ++i) {
        encrypted[i] = message[i] ^ key[i % key_len];
    }
    encrypted[msg_len] = '\0';  // Null-terminate the string
    return encrypted;
}

// Function to decrypt an encrypted message using XOR with the same key
char* decrypt(const char* encrypted, const char* key) {
    return encrypt(encrypted, key);  // XOR is symmetric
}

int main() {
    // Encrypt and decrypt the message
    char* encrypted = encrypt("Hello world", "key");
    char* decrypted = decrypt(encrypted, "key");

    // Print the result
    printf("%s\n", decrypted);

    // Free allocated memory
    free(encrypted);
    free(decrypted);

    return 0;
}