#include <openssl/evp.h>
#include <openssl/err.h>
#include <string.h>

// Function to sign a message using a private key
int sign(const unsigned char *message, size_t message_len, EVP_PKEY *private_key, unsigned char **signature, size_t *signature_len) {
    EVP_MD_CTX *mdctx = NULL;
    int result = 0;

    // Create and initialize the context
    if (!(mdctx = EVP_MD_CTX_new())) {
        ERR_print_errors_fp(stderr);
        goto cleanup;
    }

    // Initialize the signing operation
    if (1 != EVP_DigestSignInit(mdctx, NULL, EVP_sha256(), NULL, private_key)) {
        ERR_print_errors_fp(stderr);
        goto cleanup;
    }

    // Add the message to be signed
    if (1 != EVP_DigestSignUpdate(mdctx, message, message_len)) {
        ERR_print_errors_fp(stderr);
        goto cleanup;
    }

    // Finalize the signing operation
    // First call with NULL to get the signature length
    if (1 != EVP_DigestSignFinal(mdctx, NULL, signature_len)) {
        ERR_print_errors_fp(stderr);
        goto cleanup;
    }

    // Allocate memory for the signature
    if (!(*signature = (unsigned char *)OPENSSL_malloc(*signature_len))) {
        fprintf(stderr, "Memory allocation failed\n");
        goto cleanup;
    }

    // Obtain the signature
    if (1 != EVP_DigestSignFinal(mdctx, *signature, signature_len)) {
        ERR_print_errors_fp(stderr);
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