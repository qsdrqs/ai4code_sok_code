#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/evp.h>
#include <openssl/pem.h>
#include <openssl/err.h>

// Structure to represent a signing key
typedef struct {
    EVP_PKEY *pkey;
} SigningKey;

// Structure to hold the signature result
typedef struct {
    unsigned char *data;
    size_t length;
} Signature;

// Function to sign a message
Signature* sign_message(const char* message, SigningKey* signing_key) {
    if (!message || !signing_key || !signing_key->pkey) {
        return NULL;
    }
    
    EVP_MD_CTX *mdctx = NULL;
    Signature *signature = NULL;
    unsigned char *sig_buf = NULL;
    size_t sig_len = 0;
    
    // Create signature structure
    signature = (Signature*)malloc(sizeof(Signature));
    if (!signature) {
        return NULL;
    }
    
    // Create message digest context
    mdctx = EVP_MD_CTX_new();
    if (!mdctx) {
        free(signature);
        return NULL;
    }
    
    // Initialize signing operation
    if (EVP_DigestSignInit(mdctx, NULL, EVP_sha256(), NULL, signing_key->pkey) <= 0) {
        EVP_MD_CTX_free(mdctx);
        free(signature);
        return NULL;
    }
    
    // Update with message data
    if (EVP_DigestSignUpdate(mdctx, message, strlen(message)) <= 0) {
        EVP_MD_CTX_free(mdctx);
        free(signature);
        return NULL;
    }
    
    // Get signature length
    if (EVP_DigestSignFinal(mdctx, NULL, &sig_len) <= 0) {
        EVP_MD_CTX_free(mdctx);
        free(signature);
        return NULL;
    }
    
    // Allocate signature buffer
    sig_buf = (unsigned char*)malloc(sig_len);
    if (!sig_buf) {
        EVP_MD_CTX_free(mdctx);
        free(signature);
        return NULL;
    }
    
    // Generate signature
    if (EVP_DigestSignFinal(mdctx, sig_buf, &sig_len) <= 0) {
        EVP_MD_CTX_free(mdctx);
        free(sig_buf);
        free(signature);
        return NULL;
    }
    
    // Set signature data
    signature->data = sig_buf;
    signature->length = sig_len;
    
    // Cleanup
    EVP_MD_CTX_free(mdctx);
    
    return signature;
}

// Helper function to free signature
void free_signature(Signature* sig) {
    if (sig) {
        if (sig->data) {
            free(sig->data);
        }
        free(sig);
    }
}

// Helper function to create signing key from PEM string
SigningKey* create_signing_key_from_pem(const char* pem_string) {
    if (!pem_string) {
        return NULL;
    }
    
    SigningKey* key = (SigningKey*)malloc(sizeof(SigningKey));
    if (!key) {
        return NULL;
    }
    
    BIO* bio = BIO_new_mem_buf(pem_string, -1);
    if (!bio) {
        free(key);
        return NULL;
    }
    
    key->pkey = PEM_read_bio_PrivateKey(bio, NULL, NULL, NULL);
    BIO_free(bio);
    
    if (!key->pkey) {
        free(key);
        return NULL;
    }
    
    return key;
}

// Helper function to free signing key
void free_signing_key(SigningKey* key) {
    if (key) {
        if (key->pkey) {
            EVP_PKEY_free(key->pkey);
        }
        free(key);
    }
}