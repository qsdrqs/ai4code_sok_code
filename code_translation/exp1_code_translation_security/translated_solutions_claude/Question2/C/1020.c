#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <openssl/rand.h>
#include <openssl/err.h>

// Function to sign a message and verify it
int sign_message(EC_KEY* sk, const char* message) {
    // Calculate SHA256 hash of the message
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256_CTX sha256;
    SHA256_Init(&sha256);
    SHA256_Update(&sha256, message, strlen(message));
    SHA256_Final(hash, &sha256);
    
    // Sign the hash
    ECDSA_SIG* signature = ECDSA_do_sign(hash, SHA256_DIGEST_LENGTH, sk);
    if (signature == NULL) {
        fprintf(stderr, "Error signing message\n");
        return 0;
    }
    
    // Verify the signature using the same key (which contains both private and public parts)
    int verify_result = ECDSA_do_verify(hash, SHA256_DIGEST_LENGTH, signature, sk);
    
    // Clean up
    ECDSA_SIG_free(signature);
    
    return verify_result;
}

int main() {
    // Initialize OpenSSL
    ERR_load_crypto_strings();
    
    // Generate SECP256k1 key pair
    EC_KEY* sk = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (sk == NULL) {
        fprintf(stderr, "Error creating EC_KEY\n");
        return 1;
    }
    
    // Generate the key pair
    if (EC_KEY_generate_key(sk) != 1) {
        fprintf(stderr, "Error generating key pair\n");
        EC_KEY_free(sk);
        return 1;
    }
    
    // Test the sign_message function
    const char* message = "..";
    int result = sign_message(sk, message);
    
    printf("%s\n", result == 1 ? "True" : "False");
    
    // Clean up
    EC_KEY_free(sk);
    ERR_free_strings();
    
    return 0;
}