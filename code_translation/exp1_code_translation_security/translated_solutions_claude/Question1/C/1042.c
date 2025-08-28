#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

char* encrypt(int key, const char* string) {
    // Check if string is NULL (equivalent to type check in Python)
    if (string == NULL) {
        return NULL;
    }
    
    // Check if key is negative
    if (key < 0) {
        return NULL;
    }
    
    // Check if string is empty
    if (strlen(string) == 0) {
        return NULL;
    }
    
    // Adjust key if greater than 26
    if (key > 26) {
        key = key % 26;
    }
    
    // Allocate memory for new string
    int len = strlen(string);
    char* new_string = (char*)malloc((len + 1) * sizeof(char));
    if (new_string == NULL) {
        return NULL;
    }
    
    // Process each character
    for (int i = 0; i < len; i++) {
        char c = string[i];
        if (isalpha(c)) {
            if (islower(c)) {
                new_string[i] = ((c - 'a' + key) % 26) + 'a';
            } else {
                new_string[i] = ((c - 'A' + key) % 26) + 'A';
            }
        } else {
            new_string[i] = c;
        }
    }
    
    new_string[len] = '\0';  // Null terminate
    return new_string;
}

char* decrypt(const char* ciphertext, const char* key) {
    // Check if inputs are NULL (equivalent to isinstance check in Python)
    if (ciphertext == NULL || key == NULL) {
        return NULL;
    }
    
    int ciphertext_len = strlen(ciphertext);
    int key_len = strlen(key);
    
    // Check if either string is empty
    if (ciphertext_len == 0 || key_len == 0) {
        return NULL;
    }
    
    // If key length is 1, return copy of ciphertext
    if (key_len == 1) {
        char* result = (char*)malloc((ciphertext_len + 1) * sizeof(char));
        if (result == NULL) {
            return NULL;
        }
        strcpy(result, ciphertext);
        return result;
    }
    
    // Check if key is longer than ciphertext
    if (key_len > ciphertext_len) {
        return NULL;
    }
    
    // Allocate memory for plaintext
    char* plaintext = (char*)malloc((ciphertext_len + 1) * sizeof(char));
    if (plaintext == NULL) {
        return NULL;
    }
    
    // Decrypt each character
    for (int i = 0; i < ciphertext_len; i++) {
        int value = (ciphertext[i] - key[i % key_len]) % 26;
        // Handle negative modulo in C
        if (value < 0) {
            value += 26;
        }
        plaintext[i] = value + 'A';
    }
    
    plaintext[ciphertext_len] = '\0';  // Null terminate
    return plaintext;
}

// Helper function to free allocated memory
void free_string(char* str) {
    if (str != NULL) {
        free(str);
    }
}

// Example usage and testing
int main() {
    // Test encrypt function
    char* encrypted = encrypt(3, "Hello World!");
    if (encrypted != NULL) {
        printf("Encrypted: %s\n", encrypted);
        free_string(encrypted);
    }
    
    // Test decrypt function
    char* decrypted = decrypt("KHOOR", "ABC");
    if (decrypted != NULL) {
        printf("Decrypted: %s\n", decrypted);
        free_string(decrypted);
    }
    
    return 0;
}