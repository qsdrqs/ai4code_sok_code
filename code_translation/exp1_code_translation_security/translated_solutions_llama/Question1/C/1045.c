#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// Function to encrypt a given string
char* encrypt(const char* text, int key) {
    int text_len = strlen(text);
    char* encrypted_text = (char*) malloc((text_len + 1) * sizeof(char));

    // Copy the original string to the new string
    strcpy(encrypted_text, text);

    for (int i = 0; i < text_len; i++) {
        encrypted_text[i] = (char) ((unsigned char) encrypted_text[i] + key);
    }

    return encrypted_text;
}

// Function to decrypt a given string
char* decrypt(const char* text, int key) {
    int text_len = strlen(text);
    char* decrypted_text = (char*) malloc((text_len + 1) * sizeof(char));

    // Copy the original string to the new string
    strcpy(decrypted_text, text);

    for (int i = 0; i < text_len; i++) {
        decrypted_text[i] = (char) ((unsigned char) decrypted_text[i] - key);
    }

    return decrypted_text;
}

int main() {
    const char* original_text = "name";
    int key = 1234;

    char* encrypted_text = encrypt(original_text, key);
    printf("Encrypted text: %s\n", encrypted_text);
    free(encrypted_text); // Don't forget to free the allocated memory

    char* decrypted_text = decrypt("ՀԳԿԷ", key);
    printf("Decrypted text: %s\n", decrypted_text);
    free(decrypted_text); // Don't forget to free the allocated memory

    return 0;
}