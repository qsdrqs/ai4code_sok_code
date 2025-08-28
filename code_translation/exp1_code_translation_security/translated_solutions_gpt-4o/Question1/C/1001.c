#include <stdio.h>
#include <string.h>

void encrypt(const char *key, const char *string, char *encrypted_text) {
    int key_length = strlen(key);
    int string_length = strlen(string);

    for (int i = 0; i < string_length; i++) {
        char key_c = key[i % key_length];
        char encrypted_c = (string[i] + key_c) % 256;
        encrypted_text[i] = encrypted_c;
    }
    encrypted_text[string_length] = '\0'; // Null-terminate the string
}

void decrypt(const char *key, const char *string, char *decrypted_text) {
    int key_length = strlen(key);
    int string_length = strlen(string);

    for (int i = 0; i < string_length; i++) {
        char key_c = key[i % key_length];
        char decrypted_c = (string[i] - key_c + 256) % 256;
        decrypted_text[i] = decrypted_c;
    }
    decrypted_text[string_length] = '\0'; // Null-terminate the string
}

int main() {
    const char *key = "mysecretkey";
    const char *text = "Hello, World!";
    char encrypted_text[256];
    char decrypted_text[256];

    encrypt(key, text, encrypted_text);
    printf("Encrypted: %s\n", encrypted_text);

    decrypt(key, encrypted_text, decrypted_text);
    printf("Decrypted: %s\n", decrypted_text);

    return 0;
}