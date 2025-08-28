#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>

// Function to sign a message
unsigned char* signMessage(const char* message, EC_KEY* key, unsigned int* sig_len) {
    unsigned char hash[SHA256_DIGEST_LENGTH];
    unsigned char* signature = NULL;

    // Hash the message using SHA-256
    SHA256((unsigned char*)message, strlen(message), hash);

    // Allocate memory for the signature
    signature = (unsigned char*)malloc(ECDSA_size(key));
    if (signature == NULL) {
        fprintf(stderr, "Failed to allocate memory for signature\n");
        return NULL;
    }

    // Sign the hash
    if (ECDSA_sign(0, hash, SHA256_DIGEST_LENGTH, signature, sig_len, key) == 0) {
        fprintf(stderr, "Failed to sign the message\n");
        free(signature);
        return NULL;
    }

    return signature;
}

int main() {
    // Example usage
    const char* message = "message";
    EC_KEY* key = NULL;
    unsigned char* signature = NULL;
    unsigned int sig_len = 0;

    // Create a new EC key pair
    key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (key == NULL) {
        fprintf(stderr, "Failed to create EC key\n");
        return 1;
    }

    if (EC_KEY_generate_key(key) == 0) {
        fprintf(stderr, "Failed to generate EC key\n");
        EC_KEY_free(key);
        return 1;
    }

    // Sign the message
    signature = signMessage(message, key, &sig_len);
    if (signature == NULL) {
        EC_KEY_free(key);
        return 1;
    }

    printf("Message signed successfully. Signature length: %u\n", sig_len);

    // Clean up
    free(signature);
    EC_KEY_free(key);

    return 0;
}