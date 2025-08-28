#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// Function to encrypt a string by shifting each character by a given key
char* encrypt(const char* string, int key) {
    int length = strlen(string);
    char* new_string = (char*) malloc((length + 1) * sizeof(char));
    for (int i = 0; i < length; i++) {
        new_string[i] = (char) ((unsigned char) string[i] + key);
    }
    new_string[length] = '\0'; // Null-terminate the string
    return new_string;
}

// Function to decrypt a string by shifting each character back by a given key
char* decrypt(const char* string, int key) {
    int length = strlen(string);
    char* new_string = (char*) malloc((length + 1) * sizeof(char));
    for (int i = 0; i < length; i++) {
        new_string[i] = (char) ((unsigned char) string[i] - key);
    }
    new_string[length] = '\0'; // Null-terminate the string
    return new_string;
}

int main() {
    const char* msg = "this is cool if it really works";
    int key = 2;

    // Encrypt the message
    char* encrypted_msg = encrypt(msg, key);
    printf("Encrypted Message: %s\n", encrypted_msg);

    // Decrypt the message
    char* decrypted_msg = decrypt(encrypted_msg, key);
    printf("Decrypted Message: %s\n", decrypted_msg);

    // Free allocated memory
    free(encrypted_msg);
    free(decrypted_msg);

    return 0;
}