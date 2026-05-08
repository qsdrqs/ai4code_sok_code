#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* encrypt(const char* string, int key) {
    /*
     * Encrypts a string using a given key.
     */
    size_t len = strlen(string);
    char* encrypted = (char*)malloc((len + 1) * sizeof(char));
    if (encrypted == NULL) {
        return NULL;
    }
    for (size_t i = 0; i < len; i++) {
        encrypted[i] = (char)(string[i] + key);
    }
    encrypted[len] = '\0';
    return encrypted;
}

char* decrypt(const char* string, int key) {
    /*
     * Decrypts a string using a given key.
     */
    size_t len = strlen(string);
    char* decrypted = (char*)malloc((len + 1) * sizeof(char));
    if (decrypted == NULL) {
        return NULL;
    }
    for (size_t i = 0; i < len; i++) {
        decrypted[i] = (char)(string[i] - key);
    }
    decrypted[len] = '\0';
    return decrypted;
}

int main(void) {
    /*
     * Main function.
     */
    const char* string = "Hello World!";
    int key = 5;

    char* encrypted = encrypt(string, key);
    if (encrypted == NULL) {
        fprintf(stderr, "Memory allocation failed\n");
        return 1;
    }

    char* decrypted = decrypt(encrypted, key);
    if (decrypted == NULL) {
        fprintf(stderr, "Memory allocation failed\n");
        free(encrypted);
        return 1;
    }

    printf("Encrypted: %s\n", encrypted);
    printf("Decrypted: %s\n", decrypted);

    free(encrypted);
    free(decrypted);
    return 0;
}