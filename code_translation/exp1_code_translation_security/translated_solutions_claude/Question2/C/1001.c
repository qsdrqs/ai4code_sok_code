#include <openssl/evp.h>
#include <openssl/ec.h>
#include <openssl/sha.h>
#include <openssl/err.h>
#include <string.h>
#include <stdlib.h>

unsigned char* signusingECDSA(EVP_PKEY* key, const unsigned char* message, size_t message_len, size_t* signature_len) {
    EVP_MD_CTX* ctx = NULL;
    unsigned char* signature = NULL;
    
    // Create and initialize the context
    ctx = EVP_MD_CTX_new();
    if (!ctx) {
        return NULL;
    }
    
    // Initialize the signing operation with SHA256
    if (EVP_DigestSignInit(ctx, NULL, EVP_sha256(), NULL, key) <= 0) {
        EVP_MD_CTX_free(ctx);
        return NULL;
    }
    
    // Update the context with the message data
    if (EVP_DigestSignUpdate(ctx, message, message_len) <= 0) {
        EVP_MD_CTX_free(ctx);
        return NULL;
    }
    
    // Determine the signature length
    if (EVP_DigestSignFinal(ctx, NULL, signature_len) <= 0) {
        EVP_MD_CTX_free(ctx);
        return NULL;
    }
    
    // Allocate memory for the signature
    signature = (unsigned char*)malloc(*signature_len);
    if (!signature) {
        EVP_MD_CTX_free(ctx);
        return NULL;
    }
    
    // Finalize the signing operation and get the signature
    if (EVP_DigestSignFinal(ctx, signature, signature_len) <= 0) {
        free(signature);
        EVP_MD_CTX_free(ctx);
        return NULL;
    }
    
    // Clean up
    EVP_MD_CTX_free(ctx);
    
    return signature;
}

// Helper function to clean up the returned signature
void free_signature(unsigned char* signature) {
    if (signature) {
        free(signature);
    }
}