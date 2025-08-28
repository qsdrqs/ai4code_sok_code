#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/evp.h>
#include <openssl/pem.h>
#include <openssl/sha.h>

// Function to sign a message using ECDSA
ECDSA_SIG* signMessage(const char* message, EC_KEY* key) {
    // Calculate SHA-256 hash of the message
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256_CTX sha256;
    SHA256_Init(&sha256);
    SHA256_Update(&sha256, message, strlen(message));
    SHA256_Final(hash, &sha256);
    
    // Sign the hash using ECDSA
    ECDSA_SIG* signature = ECDSA_do_sign(hash, SHA256_DIGEST_LENGTH, key);
    
    return signature;
}

// Example usage function
int main() {
    // Initialize OpenSSL
    OpenSSL_add_all_algorithms();
    
    // Create a new EC_KEY for secp256k1 curve (commonly used)
    EC_KEY* key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!key) {
        fprintf(stderr, "Failed to create EC_KEY\n");
        return 1;
    }
    
    // Generate key pair
    if (EC_KEY_generate_key(key) != 1) {
        fprintf(stderr, "Failed to generate key pair\n");
        EC_KEY_free(key);
        return 1;
    }
    
    // Sign the message
    const char* message = "message";
    ECDSA_SIG* signature = signMessage(message, key);
    
    if (signature) {
        printf("Message signed successfully\n");
        
        // Clean up signature
        ECDSA_SIG_free(signature);
    } else {
        printf("Failed to sign message\n");
    }
    
    // Clean up
    EC_KEY_free(key);
    EVP_cleanup();
    
    return 0;
}