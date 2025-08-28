/*
 * Function that signs a given message using a given ECDSA signing key
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/sha.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/evp.h>

/*
 * Function to sign a message using ECDSA
 */
unsigned char* sign_message(const unsigned char* message, size_t message_len, EC_KEY* signing_key, unsigned int* signature_len) {
    // Hash the message using SHA-256
    unsigned char message_hash[SHA256_DIGEST_LENGTH];
    SHA256(message, message_len, message_hash);

    // Sign the message hash
    unsigned char* signature = NULL;
    signature = OPENSSL_malloc(ECDSA_size(signing_key)); // Allocate memory for the signature
    if (signature == NULL) {
        fprintf(stderr, "Failed to allocate memory for signature\n");
        return NULL;
    }

    if (ECDSA_sign(0, message_hash, SHA256_DIGEST_LENGTH, signature, signature_len, signing_key) == 0) {
        fprintf(stderr, "Failed to sign the message\n");
        OPENSSL_free(signature);
        return NULL;
    }

    return signature;
}

int main() {
    // Example usage of the sign_message function

    // Generate a new EC key pair (secp256k1 curve)
    EC_KEY* signing_key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (signing_key == NULL) {
        fprintf(stderr, "Failed to create EC key\n");
        return 1;
    }

    if (EC_KEY_generate_key(signing_key) == 0) {
        fprintf(stderr, "Failed to generate EC key\n");
        EC_KEY_free(signing_key);
        return 1;
    }

    // Message to be signed
    const char* message = "Hello, ECDSA!";
    size_t message_len = strlen(message);

    // Sign the message
    unsigned int signature_len = 0;
    unsigned char* signature = sign_message((const unsigned char*)message, message_len, signing_key, &signature_len);

    if (signature != NULL) {
        printf("Message signed successfully. Signature length: %u\n", signature_len);

        // Free the allocated signature memory
        OPENSSL_free(signature);
    }

    // Free the EC key
    EC_KEY_free(signing_key);

    return 0;
}