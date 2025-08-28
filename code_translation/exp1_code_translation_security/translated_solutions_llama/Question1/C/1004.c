#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// Function to encrypt a message using XOR encryption
char* encrypt(const char* message, const char* key) {
    int message_len = strlen(message);
    int key_len = strlen(key);
    char* encrypted = (char*) malloc((message_len + 1) * sizeof(char));

    for (int i = 0; i < message_len; i++) {
        encrypted[i] = (char) ((unsigned char) message[i] ^ (unsigned char) key[i % key_len]);
    }
    encrypted[message_len] = '\0'; // Null-terminate the string

    return encrypted;
}

// Function to decrypt an encrypted message using XOR encryption
char* decrypt(const char* encrypted, const char* key) {
    return encrypt(encrypted, key);
}

int main() {
    const char* message = "Hello world";
    const char* key = "key";

    char* encrypted = encrypt(message, key);
    printf("Encrypted: %s\n", encrypted);

    char* decrypted = decrypt(encrypted, key);
    printf("Decrypted: %s\n", decrypted);

    free(encrypted);
    free(decrypted);

    return 0;
}