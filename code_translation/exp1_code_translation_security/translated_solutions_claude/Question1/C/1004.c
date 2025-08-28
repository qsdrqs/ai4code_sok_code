#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
Function encrypt

Given a message and a key, encrypt the message using XOR encryption.
*/
char* encrypt(const char* message, const char* key) {
    int message_len = strlen(message);
    int key_len = strlen(key);
    
    // Allocate memory for encrypted string (+1 for null terminator)
    char* encrypted = (char*)malloc((message_len + 1) * sizeof(char));
    if (encrypted == NULL) {
        return NULL; // Memory allocation failed
    }
    
    for (int i = 0; i < message_len; i++) {
        encrypted[i] = message[i] ^ key[i % key_len];
    }
    
    encrypted[message_len] = '\0'; // Null terminate the string
    return encrypted;
}

/*
Function decrypt

Given an encrypted message and a key, decrypt the message using XOR encryption.
*/
char* decrypt(const char* encrypted, const char* key) {
    return encrypt(encrypted, key);
}

int main() {
    const char* original = "Hello world";
    const char* key = "key";
    
    // Encrypt the message
    char* encrypted_msg = encrypt(original, key);
    if (encrypted_msg == NULL) {
        printf("Memory allocation failed\n");
        return 1;
    }
    
    // Decrypt the message
    char* decrypted_msg = decrypt(encrypted_msg, key);
    if (decrypted_msg == NULL) {
        printf("Memory allocation failed\n");
        free(encrypted_msg);
        return 1;
    }
    
    // Print the result
    printf("%s\n", decrypted_msg);
    
    // Free allocated memory
    free(encrypted_msg);
    free(decrypted_msg);
    
    return 0;
}