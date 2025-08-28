#include <stdio.h>
#include <string.h>

// Function to encrypt the plaintext with the given key
void encrypt(const char *key, const char *plaintext, char *ciphertext) {
    int key_len = strlen(key);
    int text_len = strlen(plaintext);

    for (int i = 0; i < text_len; i++) {
        char key_c = key[i % key_len];
        char cipher_c = (plaintext[i] + key_c) % 256;
        ciphertext[i] = cipher_c;
    }
    ciphertext[text_len] = '\0'; // Null-terminate the ciphertext string
}

// Function to decrypt the ciphertext with the given key
void decrypt(const char *key, const char *ciphertext, char *plaintext) {
    int key_len = strlen(key);
    int text_len = strlen(ciphertext);

    for (int i = 0; i < text_len; i++) {
        char key_c = key[i % key_len];
        char plain_c = (256 + ciphertext[i] - key_c) % 256;
        plaintext[i] = plain_c;
    }
    plaintext[text_len] = '\0'; // Null-terminate the plaintext string
}

int main() {
    const char *key = "123";
    const char *text = "helloworld";
    char encrypted[256];
    char decrypted[256];

    encrypt(key, text, encrypted);
    printf("Encrypted: %s\n", encrypted);

    decrypt(key, encrypted, decrypted);
    printf("Decrypted: %s\n", decrypted);

    return 0;
}