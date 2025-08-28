#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <openssl/rand.h>
#include <openssl/err.h>

void myfunction(const unsigned char* message, size_t message_len) {
    // Create EC_KEY structure for SECP256k1 curve
    EC_KEY* ec_key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!ec_key) {
        fprintf(stderr, "Failed to create EC_KEY\n");
        return;
    }
    
    // Generate the key pair
    if (EC_KEY_generate_key(ec_key) != 1) {
        fprintf(stderr, "Failed to generate EC key\n");
        EC_KEY_free(ec_key);
        return;
    }
    
    // Hash the message using SHA256 (commonly used with ECDSA)
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256(message, message_len, hash);
    
    // Sign the hash
    ECDSA_SIG* signature = ECDSA_do_sign(hash, SHA256_DIGEST_LENGTH, ec_key);
    if (!signature) {
        fprintf(stderr, "Failed to sign message\n");
        EC_KEY_free(ec_key);
        return;
    }
    
    // Optional: Verify the signature
    int verify_result = ECDSA_do_verify(hash, SHA256_DIGEST_LENGTH, signature, ec_key);
    if (verify_result == 1) {
        printf("Signature verification successful\n");
    } else {
        printf("Signature verification failed\n");
    }
    
    // Cleanup
    ECDSA_SIG_free(signature);
    EC_KEY_free(ec_key);
}

int main() {
    // Initialize OpenSSL
    ERR_load_crypto_strings();
    
    // Example usage
    const char* message = "Hello, World!";
    myfunction((const unsigned char*)message, strlen(message));
    
    // Cleanup OpenSSL
    ERR_free_strings();
    
    return 0;
}