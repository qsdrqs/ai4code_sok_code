#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>

// Function to sign a message using a given private key
unsigned char* sign_message(const char* message, EC_KEY* private_key, unsigned int* sig_len) {
    unsigned char hash[SHA256_DIGEST_LENGTH];
    unsigned char* signature = NULL;

    // Hash the message using SHA-256
    SHA256((unsigned char*)message, strlen(message), hash);

    // Allocate memory for the signature
    signature = (unsigned char*)malloc(ECDSA_size(private_key));
    if (signature == NULL) {
        fprintf(stderr, "Failed to allocate memory for signature\n");
        return NULL;
    }

    // Sign the hash
    if (!ECDSA_sign(0, hash, SHA256_DIGEST_LENGTH, signature, sig_len, private_key)) {
        fprintf(stderr, "Failed to sign the message\n");
        free(signature);
        return NULL;
    }

    return signature;
}

int main() {
    // Generate a new EC key pair using the NIST P-192 curve
    EC_KEY* private_key = EC_KEY_new_by_curve_name(NID_X9_62_prime192v1);
    if (private_key == NULL) {
        fprintf(stderr, "Failed to create EC key\n");
        return 1;
    }

    if (!EC_KEY_generate_key(private_key)) {
        fprintf(stderr, "Failed to generate EC key\n");
        EC_KEY_free(private_key);
        return 1;
    }

    // Message to be signed
    const char* message = "something";

    // Sign the message
    unsigned int sig_len = 0;
    unsigned char* signature = sign_message(message, private_key, &sig_len);
    if (signature == NULL) {
        EC_KEY_free(private_key);
        return 1;
    }

    // Print the signature in hexadecimal format
    printf("Signature: ");
    for (unsigned int i = 0; i < sig_len; i++) {
        printf("%02x", signature[i]);
    }
    printf("\n");

    // Free allocated resources
    free(signature);
    EC_KEY_free(private_key);

    return 0;
}