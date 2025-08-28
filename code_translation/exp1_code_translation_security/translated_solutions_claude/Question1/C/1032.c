#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/**
 * Encrypts a string using a given key.
 * Returns a dynamically allocated string that must be freed by the caller.
 */
char* encrypt(const char* string, int key) {
    int len = strlen(string);
    char* encrypted = (char*)malloc((len + 1) * sizeof(char));
    
    if (encrypted == NULL) {
        return NULL; // Memory allocation failed
    }
    
    for (int i = 0; i < len; i++) {
        encrypted[i] = (char)(string[i] + key);
    }
    encrypted[len] = '\0'; // Null terminate the string
    
    return encrypted;
}

/**
 * Decrypts a string using a given key.
 * Returns a dynamically allocated string that must be freed by the caller.
 */
char* decrypt(const char* string, int key) {
    int len = strlen(string);
    char* decrypted = (char*)malloc((len + 1) * sizeof(char));
    
    if (decrypted == NULL) {
        return NULL; // Memory allocation failed
    }
    
    for (int i = 0; i < len; i++) {
        decrypted[i] = (char)(string[i] - key);
    }
    decrypted[len] = '\0'; // Null terminate the string
    
    return decrypted;
}

/**
 * Main function.
 */
int main() {
    const char* string = "Hello World!";
    int key = 5;
    
    char* encrypted = encrypt(string, key);
    if (encrypted == NULL) {
        printf("Error: Memory allocation failed for encryption\n");
        return 1;
    }
    
    char* decrypted = decrypt(encrypted, key);
    if (decrypted == NULL) {
        printf("Error: Memory allocation failed for decryption\n");
        free(encrypted);
        return 1;
    }
    
    printf("Encrypted: %s\n", encrypted);
    printf("Decrypted: %s\n", decrypted);
    
    // Free dynamically allocated memory
    free(encrypted);
    free(decrypted);
    
    return 0;
}