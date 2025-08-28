#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Hypothetical Fernet-like library
#include "fernet.h"

int main() {
    // Generate a key
    unsigned char *ex_key = fernet_generate_key();

    // Encrypt function
    unsigned char* encrypt(const char* inp, const unsigned char* key) {
        return fernet_encrypt(inp, key);
    }

    // Decrypt function
    char* decrypt(const unsigned char* inp, const unsigned char* key) {
        return fernet_decrypt(inp, key);
    }

    // Encrypt the message
    const char* message = "Hello World!";
    unsigned char* encrypted = encrypt(message, ex_key);
    printf("Encrypted: %s\n", encrypted);

    // Decrypt the message
    char* decrypted = decrypt(encrypted, ex_key);
    printf("Decrypted: %s\n", decrypted);

    // Free allocated memory
    free(ex_key);
    free(encrypted);
    free(decrypted);

    return 0;
}