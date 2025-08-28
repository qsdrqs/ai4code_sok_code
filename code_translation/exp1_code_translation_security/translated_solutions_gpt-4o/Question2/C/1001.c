#include <openssl/evp.h>
#include <openssl/ec.h>
#include <openssl/sha.h>
#include <string.h>
#include <stdlib.h>

unsigned char* signusingECDSA(EC_KEY* key, const unsigned char* message, size_t message_len, size_t* signature_len) {
    EVP_MD_CTX* mdctx = NULL;
    unsigned char* signature = NULL;

    // Create and initialize the message digest context
    mdctx = EVP_MD_CTX_new();
    if (mdctx == NULL) {
        fprintf(stderr, "Error creating message digest context\n");
        return NULL;
    }

    // Initialize the signing operation
    if (EVP_DigestSignInit(mdctx, NULL, EVP_sha256(), NULL, key) <= 0) {
        fprintf(stderr, "Error initializing signing operation\n");
        EVP_MD_CTX_free(mdctx);
        return NULL;
    }

    // Update the context with the message
    if (EVP_DigestSignUpdate(mdctx, message, message_len) <= 0) {
        fprintf(stderr, "Error updating signing context\n");
        EVP_MD_CTX_free(mdctx);
        return NULL;
    }

    // Determine the size of the signature
    if (EVP_DigestSignFinal(mdctx, NULL, signature_len) <= 0) {
        fprintf(stderr, "Error determining signature size\n");
        EVP_MD_CTX_free(mdctx);
        return NULL;
    }

    // Allocate memory for the signature
    signature = (unsigned char*)malloc(*signature_len);
    if (signature == NULL) {
        fprintf(stderr, "Error allocating memory for signature\n");
        EVP_MD_CTX_free(mdctx);
        return NULL;
    }

    // Finalize the signing operation
    if (EVP_DigestSignFinal(mdctx, signature, signature_len) <= 0) {
        fprintf(stderr, "Error finalizing signing operation\n");
        free(signature);
        EVP_MD_CTX_free(mdctx);
        return NULL;
    }

    // Clean up
    EVP_MD_CTX_free(mdctx);

    return signature;
}