#include <stdio.h>
#include <string.h>
#include <stdint.h>
#include "include/secp256k1.h"

// Function to sign a message with an ECDSA key
int signMessage(const char* message, const unsigned char* private_key, unsigned char* signature) {
    // Initialize the secp256k1 context
    secp256k1_context* ctx = secp256k1_context_create(SECP256K1_CONTEXT_SIGN);

    // Set up the private key
    secp256k1_ecdsa_signing_key key;
    memcpy(key.data, private_key, 32);

    // Sign the message
    size_t signature_len = 64;
    int ret = secp256k1_ecdsa_sign(ctx, &key, message, strlen(message), signature, &signature_len, NULL);

    // Clean up
    secp256k1_context_destroy(ctx);

    return ret;
}

int main() {
    // Example usage
    const char* message = "message";
    unsigned char private_key[32] = {0}; // Initialize with your private key
    unsigned char signature[64] = {0};

    int ret = signMessage(message, private_key, signature);
    if (ret == 1) {
        printf("Message signed successfully\n");
    } else {
        printf("Failed to sign message\n");
    }

    return 0;
}