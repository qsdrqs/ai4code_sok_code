#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function to encrypt a string by shifting each character by a given key
char* encrypt(const char* string, int key) {
    size_t len = strlen(string);
    char* new_string = (char*)malloc(len + 1);  // Allocate memory for new string
    if (new_string == NULL) {
        fprintf(stderr, "Memory allocation failed in encrypt.\n");
        exit(EXIT_FAILURE);
    }

    for (size_t i = 0; i < len; i++) {
        new_string[i] = (char)((unsigned char)string[i] + key);
    }
    new_string[len] = '\0';  // Null-terminate the new string
    return new_string;
}

// Function to decrypt a string by shifting each character back by the key
char* decrypt(const char* string, int key) {
    size_t len = strlen(string);
    char* new_string = (char*)malloc(len + 1);  // Allocate memory for new string
    if (new_string == NULL) {
        fprintf(stderr, "Memory allocation failed in decrypt.\n");
        exit(EXIT_FAILURE);
    }

    for (size_t i = 0; i < len; i++) {
        new_string[i] = (char)((unsigned char)string[i] - key);
    }
    new_string[len] = '\0';  // Null-terminate the new string
    return new_string;
}

int main() {
    // Test the encryption and decryption
    char* msg = encrypt("this is cool if it really works", 2);
    char* res = decrypt(msg, 2);

    // Print the results
    printf("Encrypted: %s\n", msg);
    printf("Decrypted: %s\n", res);

    // Free dynamically allocated memory
    free(msg);
    free(res);

    return 0;
}