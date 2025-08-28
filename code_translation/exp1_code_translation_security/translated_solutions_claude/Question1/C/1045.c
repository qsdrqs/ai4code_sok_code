#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
 * Function to encrypt a given string
 */
char* encrypt(const char* text, int key) {
    int len = strlen(text);
    char* result = (char*)malloc((len + 1) * sizeof(char));
    
    for (int i = 0; i < len; i++) {
        result[i] = (char)(text[i] + key);
    }
    result[len] = '\0';  // Null terminate the string
    
    return result;
}

/*
 * Function to decrypt a given string
 */
char* decrypt(const char* text, int key) {
    int len = strlen(text);
    char* result = (char*)malloc((len + 1) * sizeof(char));
    
    for (int i = 0; i < len; i++) {
        result[i] = (char)(text[i] - key);
    }
    result[len] = '\0';  // Null terminate the string
    
    return result;
}

int main() {
    char* encrypted = encrypt("name", 1234);
    printf("%s\n", encrypted);
    
    char* decrypted = decrypt("ՀԳԿԷ", 1234);
    printf("%s\n", decrypted);
    
    // Free allocated memory
    free(encrypted);
    free(decrypted);
    
    return 0;
}