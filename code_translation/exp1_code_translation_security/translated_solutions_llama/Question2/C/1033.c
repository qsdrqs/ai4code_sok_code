#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/rand.h>

// Function to sign a message using a given ECDSA signing key
unsigned char* sign_message(const unsigned char* message, size_t message_len, const EC_KEY* key) {
    // Create a new ECDSA signature
    ECDSA_SIG* signature = ECDSA_do_sign(message, message_len, key);
    if (!signature) {
        fprintf(stderr, "Failed to generate signature\n");
        return NULL;
    }

    // Get the signature length
    int sig_len = ECDSA_size(key);
    if (sig_len <= 0) {
        fprintf(stderr, "Failed to get signature length\n");
        ECDSA_SIG_free(signature);
        return NULL;
    }

    // Allocate memory for the signature
    unsigned char* sig_bytes = (unsigned char*)malloc(sig_len);
    if (!sig_bytes) {
        fprintf(stderr, "Failed to allocate memory for signature\n");
        ECDSA_SIG_free(signature);
        return NULL;
    }

    // Convert the signature to bytes
    if (ECDSA_SIG_to_bytes(signature, sig_bytes, sig_len) != 1) {
        fprintf(stderr, "Failed to convert signature to bytes\n");
        free(sig_bytes);
        ECDSA_SIG_free(signature);
        return NULL;
    }

    // Free the ECDSA signature
    ECDSA_SIG_free(signature);

    return sig_bytes;
}

int main() {
    // Define the curve (e.g., secp256r1)
    const char* curve_name = "secp256r1";

    // Create a new ECDSA key
    EC_KEY* key = EC_KEY_new_by_curve_name(OBJ_nid2ln(OBJ_create("secp256r1", "secp256r1", "ECDSA")));
    if (!key) {
        fprintf(stderr, "Failed to create ECDSA key\n");
        return 1;
    }

    // Generate a random private key
    if (EC_KEY_generate_key(key) != 1) {
        fprintf(stderr, "Failed to generate private key\n");
        EC_KEY_free(key);
        return 1;
    }

    // Define the message to sign
    const unsigned char* message = (const unsigned char*)"Hello, World!";
    size_t message_len = strlen((const char*)message);

    // Sign the message
    unsigned char* signature = sign_message(message, message_len, key);
    if (!signature) {
        EC_KEY_free(key);
        return 1;
    }

    // Print the signature
    printf("Signature: ");
    for (int i = 0; i < ECDSA_size(key); i++) {
        printf("%02x", signature[i]);
    }
    printf("\n");

    // Free the signature and key
    free(signature);
    EC_KEY_free(key);

    return 0;
}