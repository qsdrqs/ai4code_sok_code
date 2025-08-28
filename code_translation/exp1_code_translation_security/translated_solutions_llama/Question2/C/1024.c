#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/err.h>

// Function to generate ECDSA signed message
unsigned char* generate_ecdsa_signed_msg(const char* message, const EC_KEY* privateKey) {
    // Create a new ECDSA signature
    ECDSA_SIG* sig = ECDSA_do_sign((const unsigned char*)message, strlen(message), privateKey);
    if (!sig) {
        fprintf(stderr, "Failed to generate ECDSA signature\n");
        return NULL;
    }

    // Get the signature length
    int sig_len = ECDSA_size(privateKey);
    unsigned char* signed_msg = (unsigned char*)malloc(sig_len);
    if (!signed_msg) {
        fprintf(stderr, "Memory allocation failed\n");
        ECDSA_SIG_free(sig);
        return NULL;
    }

    // Serialize the signature
    ECDSA_SIG_to_bytes(signed_msg, sig, sig_len);

    // Free resources
    ECDSA_SIG_free(sig);

    return signed_msg;
}

int main() {
    // Initialize OpenSSL
    OpenSSL_add_all_algorithms();

    // Generate a new ECDSA key pair
    EC_KEY* privateKey = EC_KEY_new_by_curve_name(NID_secp256r1);
    if (!privateKey) {
        fprintf(stderr, "Failed to generate ECDSA key pair\n");
        return 1;
    }

    // Check if a private key was generated
    if (!EC_KEY_generate_key(privateKey)) {
        fprintf(stderr, "Failed to generate private key\n");
        EC_KEY_free(privateKey);
        return 1;
    }

    // Get the public key
    const EC_KEY* publicKey = privateKey;

    // Message to be signed
    const char* message = "My test message";

    // Generate and print the ECDSA signed message
    unsigned char* signed_msg = generate_ecdsa_signed_msg(message, privateKey);
    if (signed_msg) {
        printf("ECDSA Signed Message: ");
        for (int i = 0; i < ECDSA_size(privateKey); i++) {
            printf("%02x", signed_msg[i]);
        }
        printf("\n");

        // Free resources
        free(signed_msg);
    }

    // Free resources
    EC_KEY_free(privateKey);

    // Clean up OpenSSL
    EVP_cleanup();

    return 0;
}