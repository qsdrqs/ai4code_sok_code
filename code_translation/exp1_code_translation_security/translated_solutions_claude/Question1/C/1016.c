#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* encrypt(const char* string, const char* key) {
    /**
     * Encrypts a given string using a given symmetric key.
     */
    int string_len = strlen(string);
    int key_len = strlen(key);
    
    // Allocate memory for the encrypted string (+1 for null terminator)
    char* encrypted_string = (char*)malloc((string_len + 1) * sizeof(char));
    if (encrypted_string == NULL) {
        return NULL; // Memory allocation failed
    }
    
    for (int i = 0; i < string_len; i++) {
        encrypted_string[i] = string[i] ^ key[i % key_len];
    }
    
    // Null terminate the string
    encrypted_string[string_len] = '\0';
    
    return encrypted_string;
}

char* decrypt(const char* string, const char* key) {
    /**
     * Decrypts a given string using a given symmetric key.
     */
    int string_len = strlen(string);
    int key_len = strlen(key);
    
    // Allocate memory for the decrypted string (+1 for null terminator)
    char* decrypted_string = (char*)malloc((string_len + 1) * sizeof(char));
    if (decrypted_string == NULL) {
        return NULL; // Memory allocation failed
    }
    
    for (int i = 0; i < string_len; i++) {
        decrypted_string[i] = string[i] ^ key[i % key_len];
    }
    
    // Null terminate the string
    decrypted_string[string_len] = '\0';
    
    return decrypted_string;
}

// Example usage
int main() {
    const char* original = "Hello World!";
    const char* key = "mykey";
    
    char* encrypted = encrypt(original, key);
    if (encrypted != NULL) {
        printf("Original: %s\n", original);
        printf("Encrypted: %s\n", encrypted);
        
        char* decrypted = decrypt(encrypted, key);
        if (decrypted != NULL) {
            printf("Decrypted: %s\n", decrypted);
            free(decrypted);
        }
        free(encrypted);
    }
    
    return 0;
}