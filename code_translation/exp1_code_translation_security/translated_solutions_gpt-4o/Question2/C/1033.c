#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/err.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

// Function to sign a message using ECDSA
unsigned char* sign_message(const unsigned char* message, size_t message_len, int curve_name, size_t* signature_len) {
    // Create a new EC_KEY object
    EC_KEY* ec_key = EC_KEY_new_by_curve_name(curve_name);
    if (ec_key == NULL) {
        fprintf(stderr, "Error creating EC_KEY object\n");
        return NULL;
    }

    // Generate a new private key
    if (EC_KEY_generate_key(ec_key) != 1) {
        fprintf(stderr, "Error generating EC key\n");
        EC_KEY_free(ec_key);
        return NULL;
    }

    // Allocate memory for the signature
    unsigned char* signature = (unsigned char*)malloc(ECDSA_size(ec_key));
    if (signature == NULL) {
        fprintf(stderr, "Error allocating memory for signature\n");
        EC_KEY_free(ec_key);
        return NULL;
    }

    // Sign the message
    if (ECDSA_sign(0, message, message_len, signature, (unsigned int*)signature_len, ec_key) != 1) {
        fprintf(stderr, "Error signing message\n");
        free(signature);
        EC_KEY_free(ec_key);
        return NULL;
    }

    // Free the EC_KEY object
    EC_KEY_free(ec_key);

    // Return the signature
    return signature;
}

int main() {
    // Example usage of the sign_message function
    const char* message = "Hello, ECDSA!";
    size_t message_len = strlen(message);
    size_t signature_len = 0;

    // Use the NIST P-256 curve (secp256r1)
    int curve_name = NID_X9_62_prime256v1;

    // Sign the message
    unsigned char* signature = sign_message((const unsigned char*)message, message_len, curve_name, &signature_len);
    if (signature == NULL) {
        fprintf(stderr, "Failed to sign the message\n");
        return 1;
    }

    // Print the signature in hexadecimal format
    printf("Signature: ");
    for (size_t i = 0; i < signature_len; i++) {
        printf("%02x", signature[i]);
    }
    printf("\n");

    // Free the allocated memory for the signature
    free(signature);

    return 0;
}