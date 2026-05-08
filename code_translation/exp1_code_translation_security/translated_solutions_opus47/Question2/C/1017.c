#include <openssl/evp.h>
#include <openssl/ec.h>
#include <stdlib.h>
#include <string.h>

// Function in C that signs a given message using a given ECDSA signing key
// Returns a dynamically allocated signature (caller must free) or NULL on error
// The signature length is stored in *signature_len
unsigned char* sign_message(const unsigned char* message, size_t message_len,
                            EVP_PKEY* signing_key, size_t* signature_len) {
    // Create a message digest context
    EVP_MD_CTX* mdctx = EVP_MD_CTX_new();
    if (mdctx == NULL) {
        return NULL;
    }

    // Initialize the signing operation (using SHA-256 as the hash algorithm)
    if (EVP_DigestSignInit(mdctx, NULL, EVP_sha256(), NULL, signing_key) != 1) {
        EVP_MD_CTX_free(mdctx);
        return NULL;
    }

    // Feed the message into the context
    if (EVP_DigestSignUpdate(mdctx, message, message_len) != 1) {
        EVP_MD_CTX_free(mdctx);
        return NULL;
    }

    // First call: determine the required signature buffer size
    if (EVP_DigestSignFinal(mdctx, NULL, signature_len) != 1) {
        EVP_MD_CTX_free(mdctx);
        return NULL;
    }

    // Allocate memory for the signature
    unsigned char* signature = (unsigned char*)malloc(*signature_len);
    if (signature == NULL) {
        EVP_MD_CTX_free(mdctx);
        return NULL;
    }

    // Sign the message using the signing key
    if (EVP_DigestSignFinal(mdctx, signature, signature_len) != 1) {
        free(signature);
        EVP_MD_CTX_free(mdctx);
        return NULL;
    }

    // Clean up the context
    EVP_MD_CTX_free(mdctx);

    // Return the signature
    return signature;
}