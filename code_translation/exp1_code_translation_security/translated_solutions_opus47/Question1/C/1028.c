/* Write two functions in C where one encrypts and the other decrypts a given string using a given symmetric key. */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* Encrypts a given string using a given symmetric key. */
char* encrypt(const char* string, const char* key) {
    size_t string_len = strlen(string);
    size_t key_len = strlen(key);
    char* encrypted_string = (char*)malloc(string_len + 1);
    if (encrypted_string == NULL) {
        return NULL;
    }
    for (size_t i = 0; i < string_len; i++) {
        encrypted_string[i] = (char)(string[i] ^ key[i % key_len]);
    }
    encrypted_string[string_len] = '\0';
    return encrypted_string;
}

/* Decrypts a given string using a given symmetric key. */
char* decrypt(const char* string, const char* key) {
    size_t string_len = strlen(string);
    size_t key_len = strlen(key);
    char* decrypted_string = (char*)malloc(string_len + 1);
    if (decrypted_string == NULL) {
        return NULL;
    }
    for (size_t i = 0; i < string_len; i++) {
        decrypted_string[i] = (char)(string[i] ^ key[i % key_len]);
    }
    decrypted_string[string_len] = '\0';
    return decrypted_string;
}

/* Example usage */
int main(void) {
    const char* original = "Hello, World!";
    const char* key = "mykey";

    char* encrypted = encrypt(original, key);
    if (encrypted != NULL) {
        printf("Original:  %s\n", original);

        printf("Encrypted (hex): ");
        for (size_t i = 0; i < strlen(original); i++) {
            printf("%02x ", (unsigned char)encrypted[i]);
        }
        printf("\n");

        char* decrypted = decrypt(encrypted, key);
        if (decrypted != NULL) {
            printf("Decrypted: %s\n", decrypted);
            free(decrypted);
        }
        free(encrypted);
    }
    return 0;
}