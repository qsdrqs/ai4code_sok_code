#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/err.h>
#include <string.h>

// Function to sign a given message using a given ECDSA signing key
unsigned char* sign_message(const unsigned char* message, size_t message_len, EC_KEY* signing_key, unsigned int* sig_len) {
    // Create a buffer to hold the signature
    unsigned char* signature = NULL;
    signature = OPENSSL_malloc(ECDSA_size(signing_key));
    if (signature == NULL) {
        fprintf(stderr, "Failed to allocate memory for signature\n");
        return NULL;
    }

    // Sign the message using the signing key
    if (ECDSA_sign(0, message, message_len, signature, sig_len, signing_key) == 0) {
        fprintf(stderr, "Failed to sign message: %s\n", ERR_error_string(ERR_get_error(), NULL));
        OPENSSL_free(signature);
        return NULL;
    }

    // Return the signature
    return signature;
}