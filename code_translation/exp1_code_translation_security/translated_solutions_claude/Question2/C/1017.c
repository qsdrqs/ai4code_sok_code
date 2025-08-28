#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/sha.h>
#include <openssl/evp.h>
#include <openssl/pem.h>
#include <openssl/err.h>

// Structure to hold signature data
typedef struct {
    unsigned char* signature_data;
    size_t signature_length;
} ECDSASignature;

// Function in C that signs a given message using a given ECDSA signing key
ECDSASignature* sign_message(const unsigned char* message, size_t message_len, EC_KEY* signing_key) {
    // Allocate memory for signature structure
    ECDSASignature* signature = (ECDSASignature*)malloc(sizeof(ECDSASignature));
    if (!signature) {
        fprintf(stderr, "Memory allocation failed for signature structure\n");
        return NULL;
    }
    
    // Initialize signature structure
    signature->signature_data = NULL;
    signature->signature_length = 0;
    
    // Create hash of the message (SHA-256)
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256(message, message_len, hash);
    
    // Sign the hash using the signing key
    unsigned int sig_len = ECDSA_size(signing_key);
    signature->signature_data = (unsigned char*)malloc(sig_len);
    
    if (!signature->signature_data) {
        fprintf(stderr, "Memory allocation failed for signature data\n");
        free(signature);
        return NULL;
    }
    
    // Perform the actual signing
    if (ECDSA_sign(0, hash, SHA256_DIGEST_LENGTH, signature->signature_data, &sig_len, signing_key) != 1) {
        fprintf(stderr, "ECDSA signing failed: %s\n", ERR_error_string(ERR_get_error(), NULL));
        free(signature->signature_data);
        free(signature);
        return NULL;
    }
    
    signature->signature_length = sig_len;
    
    // Return the signature
    return signature;
}

// Helper function to free signature memory
void free_signature(ECDSASignature* signature) {
    if (signature) {
        if (signature->signature_data) {
            free(signature->signature_data);
        }
        free(signature);
    }
}

// Example usage function
int main() {
    // Initialize OpenSSL
    OpenSSL_add_all_algorithms();
    ERR_load_crypto_strings();
    
    // Example message
    const char* message = "Hello, this is a test message!";
    size_t message_len = strlen(message);
    
    // Generate a key pair for demonstration (in practice, you'd load an existing key)
    EC_KEY* key_pair = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!key_pair || EC_KEY_generate_key(key_pair) != 1) {
        fprintf(stderr, "Key generation failed\n");
        return 1;
    }
    
    // Sign the message
    ECDSASignature* signature = sign_message((const unsigned char*)message, message_len, key_pair);
    
    if (signature) {
        printf("Message signed successfully!\n");
        printf("Signature length: %zu bytes\n", signature->signature_length);
        
        // Print signature in hex format
        printf("Signature: ");
        for (size_t i = 0; i < signature->signature_length; i++) {
            printf("%02x", signature->signature_data[i]);
        }
        printf("\n");
        
        // Clean up
        free_signature(signature);
    } else {
        printf("Signing failed!\n");
    }
    
    // Clean up
    EC_KEY_free(key_pair);
    EVP_cleanup();
    ERR_free_strings();
    
    return 0;
}