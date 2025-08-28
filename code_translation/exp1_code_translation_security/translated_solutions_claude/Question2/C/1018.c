#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/evp.h>
#include <openssl/pem.h>
#include <openssl/sha.h>

// Structure to hold signature data
typedef struct {
    unsigned char* data;
    size_t length;
} signature_t;

// Function to sign a message with an EC private key
signature_t* sign(const char* msg, EVP_PKEY* key) {
    // Note: The original Python code signs "message" regardless of the msg parameter
    const char* message_to_sign = "message";
    
    // Create signing context
    EVP_MD_CTX* ctx = EVP_MD_CTX_new();
    if (!ctx) {
        return NULL;
    }
    
    // Initialize signing operation with SHA256
    if (EVP_DigestSignInit(ctx, NULL, EVP_sha256(), NULL, key) <= 0) {
        EVP_MD_CTX_free(ctx);
        return NULL;
    }
    
    // Update with message data
    if (EVP_DigestSignUpdate(ctx, message_to_sign, strlen(message_to_sign)) <= 0) {
        EVP_MD_CTX_free(ctx);
        return NULL;
    }
    
    // Get signature length
    size_t sig_len = 0;
    if (EVP_DigestSignFinal(ctx, NULL, &sig_len) <= 0) {
        EVP_MD_CTX_free(ctx);
        return NULL;
    }
    
    // Allocate signature structure
    signature_t* sig = malloc(sizeof(signature_t));
    if (!sig) {
        EVP_MD_CTX_free(ctx);
        return NULL;
    }
    
    // Allocate signature buffer
    sig->data = malloc(sig_len);
    if (!sig->data) {
        free(sig);
        EVP_MD_CTX_free(ctx);
        return NULL;
    }
    
    // Generate signature
    if (EVP_DigestSignFinal(ctx, sig->data, &sig_len) <= 0) {
        free(sig->data);
        free(sig);
        EVP_MD_CTX_free(ctx);
        return NULL;
    }
    
    sig->length = sig_len;
    EVP_MD_CTX_free(ctx);
    return sig;
}

// Helper function to create an EC private key (for demonstration)
EVP_PKEY* create_ec_key() {
    EVP_PKEY_CTX* pctx = EVP_PKEY_CTX_new_id(EVP_PKEY_EC, NULL);
    if (!pctx) return NULL;
    
    if (EVP_PKEY_keygen_init(pctx) <= 0) {
        EVP_PKEY_CTX_free(pctx);
        return NULL;
    }
    
    if (EVP_PKEY_CTX_set_ec_paramgen_curve_nid(pctx, NID_secp256k1) <= 0) {
        EVP_PKEY_CTX_free(pctx);
        return NULL;
    }
    
    EVP_PKEY* key = NULL;
    if (EVP_PKEY_keygen(pctx, &key) <= 0) {
        EVP_PKEY_CTX_free(pctx);
        return NULL;
    }
    
    EVP_PKEY_CTX_free(pctx);
    return key;
}

// Helper function to free signature
void free_signature(signature_t* sig) {
    if (sig) {
        if (sig->data) {
            free(sig->data);
        }
        free(sig);
    }
}

// Example usage
int main() {
    // Create a key
    EVP_PKEY* key = create_ec_key();
    if (!key) {
        printf("Failed to create EC key\n");
        return 1;
    }
    
    // Sign message
    signature_t* sig = sign("some message", key);
    if (!sig) {
        printf("Failed to sign message\n");
        EVP_PKEY_free(key);
        return 1;
    }
    
    printf("Signature generated successfully, length: %zu bytes\n", sig->length);
    
    // Cleanup
    free_signature(sig);
    EVP_PKEY_free(key);
    
    return 0;
}