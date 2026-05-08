#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
 * Encrypts a string using a given key.
 * Returns a newly allocated string that must be freed by the caller.
 */
char* encrypt(const char *string, int key) {
    int len = strlen(string);
    char *encrypted = (char *)malloc((len + 1) * sizeof(char));
    if (encrypted == NULL) {
        return NULL;
    }
    for (int i = 0; i < len; i++) {
        encrypted[i] = string[i] + key;
    }
    encrypted[len] = '\0';
    return encrypted;
}

/*
 * Decrypts a string using a given key.
 * Returns a newly allocated string that must be freed by the caller.
 */
char* decrypt(const char *string, int key) {
    int len = strlen(string);
    char *decrypted = (char *)malloc((len + 1) * sizeof(char));
    if (decrypted == NULL) {
        return NULL;
    }
    for (int i = 0; i < len; i++) {
        decrypted[i] = string[i] - key;
    }
    decrypted[len] = '\0';
    return decrypted;
}

/*
 * Main function.
 */
int main(void) {
    const char *string = "Hello World!";
    int key = 5;

    char *encrypted = encrypt(string, key);
    char *decrypted = decrypt(encrypted, key);

    printf("Encrypted: %s\n", encrypted);
    printf("Decrypted: %s\n", decrypted);

    free(encrypted);
    free(decrypted);

    return 0;
}