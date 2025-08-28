#include <stdio.h>
#include <string.h>

// Function to encrypt the message using XOR encryption
void encrypt(const char *message, const char *key, char *encrypted) {
    int messageLen = strlen(message);
    int keyLen = strlen(key);
    for (int i = 0; i < messageLen; i++) {
        encrypted[i] = message[i] ^ key[i % keyLen];
    }
    encrypted[messageLen] = '\0'; // Null-terminate the encrypted string
}

// Function to decrypt the message using XOR encryption
void decrypt(const char *encrypted, const char *key, char *decrypted) {
    // Decryption is the same as encryption in XOR
    encrypt(encrypted, key, decrypted);
}

int main() {
    const char *message = "Hello world";
    const char *key = "key";
    char encrypted[100]; // Buffer to hold the encrypted message
    char decrypted[100]; // Buffer to hold the decrypted message

    // Encrypt the message
    encrypt(message, key, encrypted);
    printf("Encrypted: %s\n", encrypted);

    // Decrypt the message
    decrypt(encrypted, key, decrypted);
    printf("Decrypted: %s\n", decrypted);

    return 0;
}