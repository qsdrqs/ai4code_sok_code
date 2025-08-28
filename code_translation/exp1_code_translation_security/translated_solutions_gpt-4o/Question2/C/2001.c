#include <openssl/evp.h>
#include <openssl/ec.h>
#include <openssl/sha.h>
#include <openssl/err.h>
#include <string.h>
#include <stdio.h>

// Function that signs input with the given ECDSA private key
int sign(const unsigned char *message, size_t message_len, EC_KEY *private_key, unsigned char **signature, unsigned int *signature_len) {
    int result = 0;
    EVP_MD_CTX *mdctx = NULL;

    // Create and initialize the context
    if (!(mdctx = EVP_MD_CTX_new())) {
        fprintf(stderr, "Error creating context\n");
        goto cleanup;
    }

    // Initialize the signing operation
    if (1 != EVP_DigestSignInit(mdctx, NULL, EVP_sha256(), NULL, private_key)) {
        fprintf(stderr, "Error initializing DigestSign\n");
        goto cleanup;
    }

    // Add the message to be signed
    if (1 != EVP_DigestSignUpdate(mdctx, message, message_len)) {
        fprintf(stderr, "Error updating DigestSign\n");
        goto cleanup;
    }

    // Finalize the signature
    // First call with NULL to get the signature length
    if (1 != EVP_DigestSignFinal(mdctx, NULL, signature_len)) {
        fprintf(stderr, "Error finalizing DigestSign (getting length)\n");
        goto cleanup;
    }

    // Allocate memory for the signature
    if (!(*signature = (unsigned char *)OPENSSL_malloc(*signature_len))) {
        fprintf(stderr, "Error allocating memory for signature\n");
        goto cleanup;
    }

    // Second call to get the actual signature
    if (1 != EVP_DigestSignFinal(mdctx, *signature, signature_len)) {
        fprintf(stderr, "Error finalizing DigestSign (getting signature)\n");
        goto cleanup;
    }

    result = 1; // Success

cleanup:
    if (mdctx) EVP_MD_CTX_free(mdctx);
    if (!result && *signature) {
        OPENSSL_free(*signature);
        *signature = NULL;
    }
    return result;
}

int main() {
    // Example usage
    const char *message = "This is a test message.";
    size_t message_len = strlen(message);

    // Generate a new EC key pair
    EC_KEY *private_key = EC_KEY_new_by_curve_name(NID_X9_62_prime256v1);
    if (!private_key || !EC_KEY_generate_key(private_key)) {
        fprintf(stderr, "Error generating EC key\n");
        return 1;
    }

    unsigned char *signature = NULL;
    unsigned int signature_len = 0;

    if (sign((const unsigned char *)message, message_len, private_key, &signature, &signature_len)) {
        printf("Signature generated successfully.\n");
    } else {
        fprintf(stderr, "Error signing message\n");
    }

    // Clean up
    if (signature) OPENSSL_free(signature);
    if (private_key) EC_KEY_free(private_key);

    return 0;
}