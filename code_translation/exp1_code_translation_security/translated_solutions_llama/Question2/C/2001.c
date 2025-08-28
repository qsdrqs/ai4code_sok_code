#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/sha.h>

// Function that signs input with the given ECDSA private key
unsigned char* sign(const unsigned char* message, size_t message_len, const EC_KEY* private_key, size_t* signature_len) {
    // Create a new ECDSA signature
    ECDSA_SIG* signature = ECDSA_do_sign(message, message_len, private_key);
    if (!signature) {
        return NULL;
    }

    // Get the size of the signature
    *signature_len = ECDSA_size(private_key);

    // Allocate memory for the signature
    unsigned char* signature_bytes = (unsigned char*)malloc(*signature_len);
    if (!signature_bytes) {
        ECDSA_SIG_free(signature);
        return NULL;
    }

    // Convert the signature to bytes
    if (!ECDSA_SIG_to_bytes(signature, signature_bytes, *signature_len)) {
        ECDSA_SIG_free(signature);
        free(signature_bytes);
        return NULL;
    }

    // Free the ECDSA signature
    ECDSA_SIG_free(signature);

    return signature_bytes;
}

int main() {
    // Example usage
    const char* message = "Hello, World!";
    size_t message_len = strlen(message);

    // Create a new ECDSA key
    EC_KEY* private_key = EC_KEY_new_by_curve_name(NID_secp256r1);
    if (!private_key) {
        return 1;
    }

    // Generate a private key
    if (!EC_KEY_generate_key(private_key)) {
        EC_KEY_free(private_key);
        return 1;
    }

    // Sign the message
    size_t signature_len;
    unsigned char* signature_bytes = sign((const unsigned char*)message, message_len, private_key, &signature_len);
    if (!signature_bytes) {
        EC_KEY_free(private_key);
        return 1;
    }

    // Print the signature
    printf("Signature: ");
    for (size_t i = 0; i < signature_len; i++) {
        printf("%02x", signature_bytes[i]);
    }
    printf("\n");

    // Free the signature and private key
    free(signature_bytes);
    EC_KEY_free(private_key);

    return 0;
}