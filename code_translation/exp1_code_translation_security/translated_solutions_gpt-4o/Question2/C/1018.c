#include <stdio.h>
#include <string.h>
#include "ecdsa.h" // Assuming you have an ECDSA library that provides the necessary functions

// Function to sign a message
unsigned char* sign(const char* msg, const ecdsa_key* key) {
    unsigned char* signature = NULL;
    size_t sig_len = 0;

    // Assuming the ECDSA library provides a function like `ecdsa_sign`
    // Allocate memory for the signature
    sig_len = ecdsa_signature_size(key); // Get the signature size
    signature = (unsigned char*)malloc(sig_len);
    if (signature == NULL) {
        fprintf(stderr, "Memory allocation failed for signature\n");
        return NULL;
    }

    // Sign the message
    if (ecdsa_sign(key, (const unsigned char*)msg, strlen(msg), signature, &sig_len) != 0) {
        fprintf(stderr, "Failed to sign the message\n");
        free(signature);
        return NULL;
    }

    return signature;
}

int main() {
    // Example usage
    const char* message = "message";
    ecdsa_key key;

    // Assuming the ECDSA library provides a function to initialize/load the key
    if (ecdsa_key_load(&key, "private_key.pem") != 0) {
        fprintf(stderr, "Failed to load the private key\n");
        return 1;
    }

    unsigned char* signature = sign(message, &key);
    if (signature != NULL) {
        printf("Message signed successfully\n");
        // Do something with the signature
        free(signature);
    }

    // Clean up
    ecdsa_key_free(&key); // Assuming the library provides a function to free the key
    return 0;
}