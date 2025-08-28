#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/evp.h>
#include <openssl/sha.h>
#include <openssl/rand.h>
#include <openssl/err.h>

// Function to print bytes in hex format
void print_hex(const unsigned char* data, size_t len) {
    for (size_t i = 0; i < len; i++) {
        printf("%02x", data[i]);
    }
    printf("\n");
}

// Sign function equivalent
unsigned char* sign_data(EVP_PKEY* private_key, const unsigned char* data, size_t data_len, size_t* sig_len) {
    EVP_MD_CTX* md_ctx = NULL;
    unsigned char* signature = NULL;
    
    // Create message digest context
    md_ctx = EVP_MD_CTX_new();
    if (!md_ctx) {
        fprintf(stderr, "Failed to create MD context\n");
        return NULL;
    }
    
    // Initialize signing operation with SHA256
    if (EVP_DigestSignInit(md_ctx, NULL, EVP_sha256(), NULL, private_key) <= 0) {
        fprintf(stderr, "Failed to initialize digest sign\n");
        EVP_MD_CTX_free(md_ctx);
        return NULL;
    }
    
    // Update with data to be signed
    if (EVP_DigestSignUpdate(md_ctx, data, data_len) <= 0) {
        fprintf(stderr, "Failed to update digest sign\n");
        EVP_MD_CTX_free(md_ctx);
        return NULL;
    }
    
    // Get signature length
    if (EVP_DigestSignFinal(md_ctx, NULL, sig_len) <= 0) {
        fprintf(stderr, "Failed to get signature length\n");
        EVP_MD_CTX_free(md_ctx);
        return NULL;
    }
    
    // Allocate memory for signature
    signature = malloc(*sig_len);
    if (!signature) {
        fprintf(stderr, "Failed to allocate memory for signature\n");
        EVP_MD_CTX_free(md_ctx);
        return NULL;
    }
    
    // Generate signature
    if (EVP_DigestSignFinal(md_ctx, signature, sig_len) <= 0) {
        fprintf(stderr, "Failed to generate signature\n");
        free(signature);
        EVP_MD_CTX_free(md_ctx);
        return NULL;
    }
    
    EVP_MD_CTX_free(md_ctx);
    return signature;
}

// Generate SECP384R1 private key
EVP_PKEY* generate_private_key() {
    EVP_PKEY_CTX* pkey_ctx = NULL;
    EVP_PKEY* private_key = NULL;
    
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
    
    // Generate key pair
    if (EVP_PKEY_keygen(pkey_ctx, &private_key) <= 0) {
        fprintf(stderr, "Failed to generate key pair\n");
        EVP_PKEY_CTX_free(pkey_ctx);
        return NULL;
    }
    
    EVP_PKEY_CTX_free(pkey_ctx);
    return private_key;
}

void test() {
    // Generate private key
    EVP_PKEY* private_key = generate_private_key();
    if (!private_key) {
        fprintf(stderr, "Failed to generate private key\n");
        return;
    }
    
    // Data to sign
    const char* data_str = "this is some data I'd like to sign";
    const unsigned char* data = (const unsigned char*)data_str;
    size_t data_len = strlen(data_str);
    
    // Sign the data
    size_t sig_len;
    unsigned char* signature = sign_data(private_key, data, data_len, &sig_len);
    
    if (signature) {
        printf("Signature: ");
        print_hex(signature, sig_len);
        free(signature);
    } else {
        fprintf(stderr, "Failed to sign data\n");
    }
    
    // Clean up
    EVP_PKEY_free(private_key);
}

int main() {
    // Initialize OpenSSL
    OpenSSL_add_all_algorithms();
    ERR_load_crypto_strings();
    
    test();
    
    // Cleanup OpenSSL
    EVP_cleanup();
    ERR_free_strings();
    
    return 0;
}