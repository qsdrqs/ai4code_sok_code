#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ecdsa.h>
#include <openssl/err.h>

// Function to sign a message using an ECDSA signing key
unsigned char* sign_message(const unsigned char* message, size_t message_len, 
                            const EC_KEY* signing_key, size_t* signature_len) {
    // Create a new ECDSA signature
    ECDSA_SIG* signature = ECDSA_do_sign(message, message_len, signing_key);
    if (!signature) {
        // Handle error
        unsigned char* error = NULL;
        return error;
    }

    // Get the size of the signature
    const int r_len = BN_num_bytes(ECDSA_SIG_get0_r(signature));
    const int s_len = BN_num_bytes(ECDSA_SIG_get0_s(signature));
    *signature_len = r_len + s_len;

    // Allocate memory for the signature
    unsigned char* signature_bytes = (unsigned char*)malloc(*signature_len);
    if (!signature_bytes) {
        // Handle memory allocation error
        ECDSA_SIG_free(signature);
        return NULL;
    }

    // Convert the signature to bytes
    BIGNUM* r = ECDSA_SIG_get0_r(signature);
    BIGNUM* s = ECDSA_SIG_get0_s(signature);
    BN_bn2binpad(r, signature_bytes, r_len);
    BN_bn2binpad(s, signature_bytes + r_len, s_len);

    // Free the ECDSA signature
    ECDSA_SIG_free(signature);

    return signature_bytes;
}

int main() {
    // Initialize OpenSSL
    OpenSSL_add_all_algorithms();

    // Example usage
    const char* message = "Hello, World!";
    size_t message_len = strlen(message);

    // Create an ECDSA key
    EC_KEY* signing_key = EC_KEY_new_by_curve_name(NID_secp256r1);
    if (!signing_key) {
        // Handle error
        return 1;
    }

    // Set up the key for signing
    if (!EC_KEY_set_private_key(signing_key, EC_KEY_get0_private_key(signing_key))) {
        // Handle error
        EC_KEY_free(signing_key);
        return 1;
    }

    size_t signature_len;
    unsigned char* signature = sign_message((const unsigned char*)message, message_len, signing_key, &signature_len);

    if (signature) {
        printf("Signature: ");
        for (size_t i = 0; i < signature_len; i++) {
            printf("%02x", signature[i]);
        }
        printf("\n");

        // Don't forget to free the signature
        free(signature);
    }

    // Clean up
    EC_KEY_free(signing_key);
    EVP_cleanup();

    return 0;
}