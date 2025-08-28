#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// Function to encrypt plaintext with a given key
char* encrypt(const char* key, const char* plaintext) {
    int key_len = strlen(key);
    int plaintext_len = strlen(plaintext);
    char* ciphertext = (char*)malloc((plaintext_len + 1) * sizeof(char));

    for (int i = 0; i < plaintext_len; i++) {
        char key_c = key[i % key_len];
        char cipher_c = (unsigned char)((plaintext[i] + key_c) % 256);
        ciphertext[i] = cipher_c;
    }
    ciphertext[plaintext_len] = '\0'; // Null-terminate the string

    return ciphertext;
}

// Function to decrypt ciphertext with a given key
char* decrypt(const char* key, const char* ciphertext) {
    int key_len = strlen(key);
    int ciphertext_len = strlen(ciphertext);
    char* plaintext = (char*)malloc((ciphertext_len + 1) * sizeof(char));

    for (int i = 0; i < ciphertext_len; i++) {
        char key_c = key[i % key_len];
        char plain_c = (unsigned char)((256 + ciphertext[i] - key_c) % 256);
        plaintext[i] = plain_c;
    }
    plaintext[ciphertext_len] = '\0'; // Null-terminate the string

    return plaintext;
}

int main() {
    const char* key = "123";
    const char* plaintext = "helloworld";

    // Encrypt the plaintext
    char* ciphertext = encrypt(key, plaintext);
    printf("Ciphertext: %s\n", ciphertext);

    // Decrypt the ciphertext
    char* decrypted_text = decrypt(key, ciphertext);
    printf("Decrypted Text: %s\n", decrypted_text);

    // Free allocated memory
    free(ciphertext);
    free(decrypted_text);

    return 0;
}