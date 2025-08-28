#include <openssl/evp.h>
#include <openssl/err.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

// Function to sign a message using a private key
unsigned char* sign_message(const unsigned char* message, size_t message_len, EVP_PKEY* signing_key, size_t* signature_len) {
    EVP_MD_CTX* mdctx = NULL;
    unsigned char* signature = NULL;

    // Create and initialize the context
    if (!(mdctx = EVP_MD_CTX_new())) {
        fprintf(stderr, "Error creating context\n");
        return NULL;
    }

    // Initialize the signing operation
    if (1 != EVP_DigestSignInit(mdctx, NULL, EVP_sha256(), NULL, signing_key)) {
        fprintf(stderr, "Error initializing signing operation\n");
        EVP_MD_CTX_free(mdctx);
        return NULL;
    }

    // Add the message to be signed
    if (1 != EVP_DigestSignUpdate(mdctx, message, message_len)) {
        fprintf(stderr, "Error adding message to signing operation\n");
        EVP_MD_CTX_free(mdctx);
        return NULL;
    }

    // Determine the size of the signature
    if (1 != EVP_DigestSignFinal(mdctx, NULL, signature_len)) {
        fprintf(stderr, "Error determining signature size\n");
        EVP_MD_CTX_free(mdctx);
        return NULL;
    }

    // Allocate memory for the signature
    if (!(signature = (unsigned char*)malloc(*signature_len))) {
        fprintf(stderr, "Error allocating memory for signature\n");
        EVP_MD_CTX_free(mdctx);
        return NULL;
    }

    // Obtain the signature
    if (1 != EVP_DigestSignFinal(mdctx, signature, signature_len)) {
        fprintf(stderr, "Error obtaining signature\n");
        free(signature);
        EVP_MD_CTX_free(mdctx);
        return NULL;
    }

    // Clean up
    EVP_MD_CTX_free(mdctx);

    return signature;
}