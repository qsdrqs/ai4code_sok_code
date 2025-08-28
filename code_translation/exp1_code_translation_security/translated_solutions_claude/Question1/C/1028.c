#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/**
 * Encrypts a given string using a given symmetric key.
 * @param string: The string to encrypt
 * @param key: The symmetric key to use for encryption
 * @return: A newly allocated encrypted string (caller must free)
 */
char* encrypt(const char* string, const char* key) {
    if (string == NULL || key == NULL || strlen(key) == 0) {
        return NULL;
    }
    
    int string_len = strlen(string);
    int key_len = strlen(key);
    
    // Allocate memory for encrypted string
    char* encrypted_string = (char*)malloc((string_len + 1) * sizeof(char));
    if (encrypted_string == NULL) {
        return NULL; // Memory allocation failed
    }
    
    // Encrypt each character
    for (int i = 0; i < string_len; i++) {
        encrypted_string[i] = string[i] ^ key[i % key_len];
    }
    
    // Null terminate the string
    encrypted_string[string_len] = '\0';
    
    return encrypted_string;
}

/**
 * Decrypts a given string using a given symmetric key.
 * @param string: The string to decrypt
 * @param key: The symmetric key to use for decryption
 * @return: A newly allocated decrypted string (caller must free)
 */
char* decrypt(const char* string, const char* key) {
    if (string == NULL || key == NULL || strlen(key) == 0) {
        return NULL;
    }
    
    int string_len = strlen(string);
    int key_len = strlen(key);
    
    // Allocate memory for decrypted string
    char* decrypted_string = (char*)malloc((string_len + 1) * sizeof(char));
    if (decrypted_string == NULL) {
        return NULL; // Memory allocation failed
    }
    
    // Decrypt each character
    for (int i = 0; i < string_len; i++) {
        decrypted_string[i] = string[i] ^ key[i % key_len];
    }
    
    // Null terminate the string
    decrypted_string[string_len] = '\0';
    
    return decrypted_string;
}

// Example usage and test function
int main() {
    const char* original = "Hello, World!";
    const char* key = "mykey";
    
    printf("Original: %s\n", original);
    
    // Encrypt the string
    char* encrypted = encrypt(original, key);
    if (encrypted != NULL) {
        printf("Encrypted: ");
        for (int i = 0; i < strlen(original); i++) {
            printf("%02x ", (unsigned char)encrypted[i]);
        }
        printf("\n");
        
        // Decrypt the string
        char* decrypted = decrypt(encrypted, key);
        if (decrypted != NULL) {
            printf("Decrypted: %s\n", decrypted);
            free(decrypted);
        }
        
        free(encrypted);
    }
    
    return 0;
}