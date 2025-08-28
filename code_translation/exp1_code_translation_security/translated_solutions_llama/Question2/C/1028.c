#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ecdsa.h>
#include <openssl/err.h>

// Function to sign a message using an ECDSA signing key
unsigned char* sign(ECDSA* ecdsa, const unsigned char* message, size_t message_len, int* signature_len) {
    // Create a new ECDSA signature
    ECDSA_SIG* sig = ECDSA_do_sign(message, message_len, ecdsa);
    if (!sig) {
        *signature_len = 0;
        return NULL;
    }

    // Get the size of the signature
    *signature_len = ECDSA_size(ecdsa);

    // Allocate memory for the signature
    unsigned char* signature = (unsigned char*)malloc(*signature_len);
    if (!signature) {
        ECDSA_SIG_free(sig);
        *signature_len = 0;
        return NULL;
    }

    // Get the signature
    ECDSA_SIG_to_bytes(sig, signature, *signature_len);

    // Free the ECDSA signature
    ECDSA_SIG_free(sig);

    return signature;
}

// Function to verify a signature using an ECDSA verification key
int verify(ECDSA* ecdsa, const unsigned char* message, size_t message_len, const unsigned char* signature, int signature_len) {
    // Create a new ECDSA signature from the provided bytes
    ECDSA_SIG* sig = ECDSA_SIG_new();
    if (!ECDSA_SIG_from_bytes(sig, signature, signature_len)) {
        ECDSA_SIG_free(sig);
        return 0; // Invalid signature
    }

    // Verify the signature
    int result = ECDSA_do_verify(message, message_len, sig, ecdsa);

    // Free the ECDSA signature
    ECDSA_SIG_free(sig);

    return result;
}

int main() {
    // Initialize OpenSSL
    OpenSSL_add_all_algorithms();

    // Example usage
    const char* message = "Hello, World!";
    size_t message_len = strlen(message);

    // Create an ECDSA key
    ECDSA* ecdsa = ECDSA_new_by_curve_name(NID_secp256r1);
    if (!ecdsa) {
        ERR_print_errors_fp(stderr);
        return 1;
    }

    // Generate a key pair (for demonstration purposes only)
    if (!ECDSA_generate_key(ecdsa)) {
        ERR_print_errors_fp(stderr);
        ECDSA_free(ecdsa);
        return 1;
    }

    // Sign the message
    int signature_len;
    unsigned char* signature = sign(ecdsa, (const unsigned char*)message, message_len, &signature_len);
    if (!signature) {
        ECDSA_free(ecdsa);
        return 1;
    }

    // Verify the signature
    int result = verify(ecdsa, (const unsigned char*)message, message_len, signature, signature_len);

    // Print the result
    printf("Signature verification result: %s\n", result ? "Valid" : "Invalid");

    // Clean up
    free(signature);
    ECDSA_free(ecdsa);

    // Clean up OpenSSL
    EVP_cleanup();

    return 0;
}