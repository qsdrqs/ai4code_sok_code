#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* encrypt(const char* key, const char* string) {
    int string_len = strlen(string);
    int key_len = strlen(key);
    
    // Allocate memory for the encrypted text (including null terminator)
    char* encrypted_text = (char*)malloc((string_len + 1) * sizeof(char));
    if (encrypted_text == NULL) {
        return NULL; // Memory allocation failed
    }
    
    for (int i = 0; i < string_len; i++) {
        char key_c = key[i % key_len];
        char encrypted_c = (char)(((unsigned char)string[i] + (unsigned char)key_c) % 256);
        encrypted_text[i] = encrypted_c;
    }
    
    encrypted_text[string_len] = '\0'; // Null terminate the string
    return encrypted_text;
}

char* decrypt(const char* key, const char* string) {
    int string_len = strlen(string);
    int key_len = strlen(key);
    
    // Allocate memory for the decrypted text (including null terminator)
    char* decrypted_text = (char*)malloc((string_len + 1) * sizeof(char));
    if (decrypted_text == NULL) {
        return NULL; // Memory allocation failed
    }
    
    for (int i = 0; i < string_len; i++) {
        char key_c = key[i % key_len];
        char decrypted_c = (char)(((unsigned char)string[i] - (unsigned char)key_c + 256) % 256);
        decrypted_text[i] = decrypted_c;
    }
    
    decrypted_text[string_len] = '\0'; // Null terminate the string
    return decrypted_text;
}

// Example usage (optional)
int main() {
    const char* key = "mykey";
    const char* original = "Hello World!";
    
    char* encrypted = encrypt(key, original);
    if (encrypted != NULL) {
        printf("Original: %s\n", original);
        printf("Encrypted: ");
        for (int i = 0; i < strlen(original); i++) {
            printf("%02x ", (unsigned char)encrypted[i]);
        }
        printf("\n");
        
        char* decrypted = decrypt(key, encrypted);
        if (decrypted != NULL) {
            printf("Decrypted: %s\n", decrypted);
            free(decrypted);
        }
        free(encrypted);
    }
    
    return 0;
}