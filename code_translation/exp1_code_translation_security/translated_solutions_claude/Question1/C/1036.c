#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* encrypt(const char* string, int key) {
    /*
     * Encrypts a string using a given key.
     */
    int len = strlen(string);
    char* encrypted = (char*)malloc((len + 1) * sizeof(char));
    
    for (int i = 0; i < len; i++) {
        encrypted[i] = (char)(string[i] + key);
    }
    encrypted[len] = '\0';  // Null terminate the string
    
    return encrypted;
}

char* decrypt(const char* string, int key) {
    /*
     * Decrypts a string using a given key.
     */
    int len = strlen(string);
    char* decrypted = (char*)malloc((len + 1) * sizeof(char));
    
    for (int i = 0; i < len; i++) {
        decrypted[i] = (char)(string[i] - key);
    }
    decrypted[len] = '\0';  // Null terminate the string
    
    return decrypted;
}

int main() {
    /*
     * Main function.
     */
    const char* string = "Hello World!";
    int key = 5;
    
    char* encrypted = encrypt(string, key);
    char* decrypted = decrypt(encrypted, key);
    
    printf("Encrypted: %s\n", encrypted);
    printf("Decrypted: %s\n", decrypted);
    
    // Free allocated memory
    free(encrypted);
    free(decrypted);
    
    return 0;
}