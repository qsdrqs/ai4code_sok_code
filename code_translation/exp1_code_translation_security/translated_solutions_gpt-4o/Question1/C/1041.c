#include <stdio.h>
#include <string.h>

void encrypt(const char* string, int key, char* new_string) {
    int i;
    for (i = 0; string[i] != '\0'; i++) {
        new_string[i] = string[i] + key;
    }
    new_string[i] = '\0'; // Null-terminate the new string
}

void decrypt(const char* string, int key, char* new_string) {
    int i;
    for (i = 0; string[i] != '\0'; i++) {
        new_string[i] = string[i] - key;
    }
    new_string[i] = '\0'; // Null-terminate the new string
}

int main() {
    const char* original_msg = "this is cool if it really works";
    char encrypted_msg[100]; // Ensure this is large enough to hold the encrypted message
    char decrypted_msg[100]; // Ensure this is large enough to hold the decrypted message

    encrypt(original_msg, 2, encrypted_msg);
    decrypt(encrypted_msg, 2, decrypted_msg);

    printf("Encrypted: %s\n", encrypted_msg);
    printf("Decrypted: %s\n", decrypted_msg);

    return 0;
}