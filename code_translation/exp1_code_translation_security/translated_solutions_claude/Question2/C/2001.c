#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/sha.h>
#include <openssl/evp.h>
#include <openssl/err.h>
#include <string.h>
#include <stdlib.h>

// Function that signs input with the given ECDSA private key
// Returns the signature length on success, -1 on failure
int sign(const unsigned char* message, size_t message_len, 
         EVP_PKEY* private_key, unsigned char** signature) {
    
    EVP_MD_CTX* md_ctx = NULL;
    size_t sig_len = 0;
    int ret = -1;
    
    // Create message digest context
    md_ctx = EVP_MD_CTX_new();
    if (!md_ctx) {
        goto cleanup;
    }
    
    // Initialize signing context with SHA256
    if (EVP_DigestSignInit(md_ctx, NULL, EVP_sha256(), NULL, private_key) != 1) {
        goto cleanup;
    }
    
    // Update with message data
    if (EVP_DigestSignUpdate(md_ctx, message, message_len) != 1) {
        goto cleanup;
    }
    
    // Get signature length
    if (EVP_DigestSignFinal(md_ctx, NULL, &sig_len) != 1) {
        goto cleanup;
    }
    
    // Allocate memory for signature
    *signature = (unsigned char*)malloc(sig_len);
    if (!*signature) {
        goto cleanup;
    }
    
    // Generate signature
    if (EVP_DigestSignFinal(md_ctx, *signature, &sig_len) != 1) {
        free(*signature);
        *signature = NULL;
        goto cleanup;
    }
    
    ret = (int)sig_len;
    
cleanup:
    if (md_ctx) {
        EVP_MD_CTX_free(md_ctx);
    }
    
    return ret;
}

// Helper function to create an ECDSA private key from raw bytes (optional)
EVP_PKEY* create_ec_private_key(const unsigned char* private_key_bytes, 
                                size_t key_len, int curve_nid) {
    EVP_PKEY* pkey = NULL;
    EVP_PKEY_CTX* pkey_ctx = NULL;
    BIGNUM* priv_bn = NULL;
    EC_KEY* ec_key = NULL;
    
    // Create EC key with specified curve (e.g., NID_X9_62_prime256v1 for P-256)
    ec_key = EC_KEY_new_by_curve_name(curve_nid);
    if (!ec_key) {
        goto cleanup;
    }
    
    // Convert private key bytes to BIGNUM
    priv_bn = BN_bin2bn(private_key_bytes, key_len, NULL);
    if (!priv_bn) {
        goto cleanup;
    }
    
    // Set private key
    if (EC_KEY_set_private_key(ec_key, priv_bn) != 1) {
        goto cleanup;
    }
    
    // Generate corresponding public key
    const EC_GROUP* group = EC_KEY_get0_group(ec_key);
    EC_POINT* pub_point = EC_POINT_new(group);
    if (!pub_point) {
        goto cleanup;
    }
    
    if (EC_POINT_mul(group, pub_point, priv_bn, NULL, NULL, NULL) != 1) {
        EC_POINT_free(pub_point);
        goto cleanup;
    }
    
    if (EC_KEY_set_public_key(ec_key, pub_point) != 1) {
        EC_POINT_free(pub_point);
        goto cleanup;
    }
    
    EC_POINT_free(pub_point);
    
    // Create EVP_PKEY from EC_KEY
    pkey = EVP_PKEY_new();
    if (!pkey) {
        goto cleanup;
    }
    
    if (EVP_PKEY_set1_EC_KEY(pkey, ec_key) != 1) {
        EVP_PKEY_free(pkey);
        pkey = NULL;
        goto cleanup;
    }
    
cleanup:
    if (ec_key) EC_KEY_free(ec_key);
    if (priv_bn) BN_free(priv_bn);
    
    return pkey;
}

// Example usage
/*
int main() {
    // Initialize OpenSSL
    OpenSSL_add_all_algorithms();
    ERR_load_crypto_strings();
    
    // Example message
    const char* message = "Hello, World!";
    unsigned char* signature = NULL;
    
    // Create or load your private key here
    // EVP_PKEY* private_key = ...;
    
    // Sign the message
    int sig_len = sign((const unsigned char*)message, strlen(message), 
                       private_key, &signature);
    
    if (sig_len > 0) {
        printf("Signature generated successfully, length: %d bytes\n", sig_len);
        // Use signature...
        free(signature);
    } else {
        printf("Signature generation failed\n");
        ERR_print_errors_fp(stderr);
    }
    
    // Cleanup
    EVP_PKEY_free(private_key);
    EVP_cleanup();
    ERR_free_strings();
    
    return 0;
}
*/