#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <secp256k1.h>

// Function to generate a signing key and sign a message
void myfunction(const unsigned char *message, size_t message_len) {
    // Initialize the secp256k1 context
    secp256k1_context *ctx = secp256k1_context_create(SECP256K1_CONTEXT_SIGN);
    if (!ctx) {
        fprintf(stderr, "Failed to create secp256k1 context\n");
        return;
    }

    // Generate a private key
    unsigned char private_key[32];
    secp256k1_generate_private_key(private_key, ctx);

    // Create a signing key
    secp256k1_ecdsa_signature sig;
    int ret = secp256k1_ecdsa_sign(ctx, &sig, message, message_len, private_key);
    if (!ret) {
        fprintf(stderr, "Failed to sign message\n");
        secp256k1_context_destroy(ctx);
        return;
    }

    // Print the signature (r and s components)
    printf("Signature (r): ");
    for (int i = 0; i < 32; i++) {
        printf("%02x", sig.data[i]);
    }
    printf("\n");

    printf("Signature (s): ");
    for (int i = 32; i < 64; i++) {
        printf("%02x", sig.data[i]);
    }
    printf("\n");

    // Clean up
    secp256k1_context_destroy(ctx);
}

int main() {
    const char *message = "Hello, World!";
    myfunction((const unsigned char *)message, strlen(message));
    return 0;
}