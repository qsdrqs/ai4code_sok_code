#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/evp.h>
#include <openssl/sha.h>
#include <openssl/err.h>
#include <openssl/bn.h>

// Structure to hold signature data
typedef struct {
    unsigned char* data;
    size_t length;
} signature_result_t;

// Function to sign data using ECDSA with SHA256
signature_result_t* sign_string(EVP_PKEY* private_key, const unsigned char* data, size_t data_len) {
    EVP_MD_CTX* md_ctx = NULL;
    signature_result_t* result = NULL;
    unsigned char* signature = NULL;
    size_t sig_len = 0;
    
    // Allocate result structure
    result = malloc(sizeof(signature_result_t));
    if (!result) {
        fprintf(stderr, "Memory allocation failed\n");
        return NULL;
    }
    
    // Create message digest context
    md_ctx = EVP_MD_CTX_new();
    if (!md_ctx) {
        fprintf(stderr, "Failed to create MD context\n");
        free(result);
        return NULL;
    }
    
    // Initialize signing operation with SHA256
    if (EVP_DigestSignInit(md_ctx, NULL, EVP_sha256(), NULL, private_key) <= 0) {
        fprintf(stderr, "Failed to initialize digest sign\n");
        EVP_MD_CTX_free(md_ctx);
        free(result);
        return NULL;
    }
    
    // Update with data to be signed
    if (EVP_DigestSignUpdate(md_ctx, data, data_len) <= 0) {
        fprintf(stderr, "Failed to update digest sign\n");
        EVP_MD_CTX_free(md_ctx);
        free(result);
        return NULL;
    }
    
    // Get signature length
    if (EVP_DigestSignFinal(md_ctx, NULL, &sig_len) <= 0) {
        fprintf(stderr, "Failed to get signature length\n");
        EVP_MD_CTX_free(md_ctx);
        free(result);
        return NULL;
    }
    
    // Allocate signature buffer
    signature = malloc(sig_len);
    if (!signature) {
        fprintf(stderr, "Memory allocation failed for signature\n");
        EVP_MD_CTX_free(md_ctx);
        free(result);
        return NULL;
    }
    
    // Generate signature
    if (EVP_DigestSignFinal(md_ctx, signature, &sig_len) <= 0) {
        fprintf(stderr, "Failed to generate signature\n");
        free(signature);
        EVP_MD_CTX_free(md_ctx);
        free(result);
        return NULL;
    }
    
    // Set result
    result->data = signature;
    result->length = sig_len;
    
    // Cleanup
    EVP_MD_CTX_free(md_ctx);
    
    return result;
}

// Helper function to generate SECP384R1 private key
EVP_PKEY* generate_secp384r1_key() {
    EVP_PKEY_CTX* pkey_ctx = NULL;
    EVP_PKEY* pkey = NULL;
    
    // Create key generation context
    pkey_ctx = EVP_PKEY_CTX_new_id(EVP_PKEY_EC, NULL);
    if (!pkey_ctx) {
        fprintf(stderr, "Failed to create key context\n");
        return NULL;
    }
    
    // Initialize key generation
    if (EVP_PKEY_keygen_init(pkey_ctx) <= 0) {
        fprintf(stderr, "Failed to initialize key generation\n");
        EVP_PKEY_CTX_free(pkey_ctx);
        return NULL;
    }
    
    // Set curve to SECP384R1
    if (EVP_PKEY_CTX_set_ec_paramgen_curve_nid(pkey_ctx, NID_secp384r1) <= 0) {
        fprintf(stderr, "Failed to set curve\n");
        EVP_PKEY_CTX_free(pkey_ctx);
        return NULL;
    }
    
    // Generate key
    if (EVP_PKEY_keygen(pkey_ctx, &pkey) <= 0) {
        fprintf(stderr, "Failed to generate key\n");
        EVP_PKEY_CTX_free(pkey_ctx);
        return NULL;
    }
    
    EVP_PKEY_CTX_free(pkey_ctx);
    return pkey;
}

// Helper function to decode DER signature to r,s values (similar to utils.decode_dss_signature)
void decode_dss_signature(const unsigned char* sig_data, size_t sig_len) {
    ECDSA_SIG* sig = NULL;
    const BIGNUM* r = NULL;
    const BIGNUM* s = NULL;
    char* r_str = NULL;
    char* s_str = NULL;
    
    // Parse DER signature
    sig = d2i_ECDSA_SIG(NULL, &sig_data, sig_len);
    if (!sig) {
        fprintf(stderr, "Failed to parse signature\n");
        return;
    }
    
    // Get r and s values
    ECDSA_SIG_get0(sig, &r, &s);
    
    // Convert to strings for display
    r_str = BN_bn2hex(r);
    s_str = BN_bn2hex(s);
    
    printf("r: %s\n", r_str);
    printf("s: %s\n", s_str);
    
    // Cleanup
    OPENSSL_free(r_str);
    OPENSSL_free(s_str);
    ECDSA_SIG_free(sig);
}

// Helper function to free signature result
void free_signature_result(signature_result_t* result) {
    if (result) {
        if (result->data) {
            free(result->data);
        }
        free(result);
    }
}

// Example usage (equivalent to the commented Python code)
int main() {
    // Initialize OpenSSL
    OpenSSL_add_all_algorithms();
    ERR_load_crypto_strings();
    
    // Generate private key
    EVP_PKEY* private_key = generate_secp384r1_key();
    if (!private_key) {
        fprintf(stderr, "Failed to generate private key\n");
        return 1;
    }
    
    // Data to sign
    const char* data = "hello world";
    
    // Sign the data
    signature_result_t* signature = sign_string(private_key, (const unsigned char*)data, strlen(data));
    if (!signature) {
        fprintf(stderr, "Failed to sign data\n");
        EVP_PKEY_free(private_key);
        return 1;
    }
    
    printf("Signature generated successfully (%zu bytes)\n", signature->length);
    
    // Decode and display signature (equivalent to utils.decode_dss_signature)
    decode_dss_signature(signature->data, signature->length);
    
    // Cleanup
    free_signature_result(signature);
    EVP_PKEY_free(private_key);
    EVP_cleanup();
    ERR_free_strings();
    
    return 0;
}