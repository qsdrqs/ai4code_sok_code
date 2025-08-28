#include <stdio.h>
#include <string.h>

void encrypt(const char *plaintext, const char *key, char *ciphertext) {
    int text_len = strlen(plaintext);
    int key_len = strlen(key);
    for (int i = 0; i < text_len; i++) {
        ciphertext[i] = ((plaintext[i] - 'a') + (key[i % key_len] - 'a')) % 26 + 'a';
    }
    ciphertext[text_len] = '\0'; // Null-terminate the string
}

void decrypt(const char *ciphertext, const char *key, char *plaintext) {
    int text_len = strlen(ciphertext);
    int key_len = strlen(key);
    for (int i = 0; i < text_len; i++) {
        plaintext[i] = ((ciphertext[i] - 'a') - (key[i % key_len] - 'a') + 26) % 26 + 'a';
    }
    plaintext[text_len] = '\0'; // Null-terminate the string
}

int main() {
    const char *plaintext = "helloworld";
    const char *key = "python";
    char ciphertext[100]; // Ensure this is large enough to hold the ciphertext
    char decrypted[100];  // Ensure this is large enough to hold the decrypted text

    encrypt(plaintext, key, ciphertext);
    printf("Ciphertext: %s\n", ciphertext);

    decrypt(ciphertext, key, decrypted);
    printf("Decrypted: %s\n", decrypted);

    // Check if the decrypted text matches the original plaintext
    if (strcmp(plaintext, decrypted) == 0) {
        printf("Decryption successful!\n");
    } else {
        printf("Decryption failed.\n");
    }

    return 0;
}