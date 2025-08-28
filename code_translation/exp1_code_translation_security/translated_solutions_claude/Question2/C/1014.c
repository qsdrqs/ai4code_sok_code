/**
 * Functions that signs a given message using a given ECDSA signing key
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/sha.h>
#include <openssl/evp.h>
#include <openssl/err.h>

/**
 * Signs a given message using a given ECDSA signing key
 * 
 * @param message: The message to sign
 * @param message_len: Length of the message
 * @param signing_key: The ECDSA private key for signing
 * @param signature: Output buffer for the signature (DER encoded)
 * @param sig_len: Pointer to store the signature length
 * @return: 1 on success, 0 on failure
 */
int sign_message(const unsigned char* message, size_t message_len, 
                 EC_KEY* signing_key, unsigned char** signature, 
                 unsigned int* sig_len) {
    
    if (!message || !signing_key || !signature || !sig_len) {
        return 0;
    }
    
    // Hash the message using SHA-256
    unsigned char message_hash[SHA256_DIGEST_LENGTH];
    SHA256_CTX sha256_ctx;
    
    if (!SHA256_Init(&sha256_ctx)) {
        return 0;
    }
    
    if (!SHA256_Update(&sha256_ctx, message, message_len)) {
        return 0;
    }
    
    if (!SHA256_Final(message_hash, &sha256_ctx)) {
        return 0;
    }
    
    // Sign the message hash (this produces DER-encoded signature by default)
    *signature = NULL;
    *sig_len = ECDSA_size(signing_key);
    *signature = (unsigned char*)malloc(*sig_len);
    
    if (!*signature) {
        return 0;
    }
    
    if (!ECDSA_sign(0, message_hash, SHA256_DIGEST_LENGTH, 
                    *signature, sig_len, signing_key)) {
        free(*signature);
        *signature = NULL;
        return 0;
    }
    
    return 1;
}

/**
 * Helper function to free signature memory
 */
void free_signature(unsigned char* signature) {
    if (signature) {
        free(signature);
    }
}

/**
 * Example usage function
 */
int example_usage() {
    // Initialize OpenSSL
    OpenSSL_add_all_algorithms();
    ERR_load_crypto_strings();
    
    // Create a new EC key (secp256k1 curve as example)
    EC_KEY* key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!key) {
        fprintf(stderr, "Failed to create EC key\n");
        return 0;
    }
    
    // Generate key pair
    if (!EC_KEY_generate_key(key)) {
        fprintf(stderr, "Failed to generate key pair\n");
        EC_KEY_free(key);
        return 0;
    }
    
    // Message to sign
    const char* message = "Hello, World!";
    unsigned char* signature = NULL;
    unsigned int sig_len = 0;
    
    // Sign the message
    if (sign_message((const unsigned char*)message, strlen(message), 
                     key, &signature, &sig_len)) {
        printf("Message signed successfully. Signature length: %u bytes\n", sig_len);
        
        // Print signature in hex format
        printf("Signature (hex): ");
        for (unsigned int i = 0; i < sig_len; i++) {
            printf("%02x", signature[i]);
        }
        printf("\n");
        
        // Clean up
        free_signature(signature);
    } else {
        fprintf(stderr, "Failed to sign message\n");
    }
    
    // Clean up
    EC_KEY_free(key);
    EVP_cleanup();
    ERR_free_strings();
    
    return 1;
}