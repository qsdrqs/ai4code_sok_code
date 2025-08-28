#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/evp.h>
#include <openssl/sha.h>
#include <openssl/rand.h>
#include <openssl/err.h>

// Function to sign a message
ECDSA_SIG* sign_message(EC_KEY* sk, const unsigned char* message, size_t message_len) {
    // Hash the message using SHA-384 (appropriate for NIST P-384)
    unsigned char hash[SHA384_DIGEST_LENGTH];
    SHA384(message, message_len, hash);
    
    // Sign the hash
    ECDSA_SIG* signature = ECDSA_do_sign(hash, SHA384_DIGEST_LENGTH, sk);
    return signature;
}

// Function to verify a signature
int verify_signature(EC_KEY* sk, ECDSA_SIG* signature, const unsigned char* message, size_t message_len) {
    // Hash the message using SHA-384
    unsigned char hash[SHA384_DIGEST_LENGTH];
    SHA384(message, message_len, hash);
    
    // Verify the signature
    int result = ECDSA_do_verify(hash, SHA384_DIGEST_LENGTH, signature, sk);
    return result;
}

int main() {
    // Initialize OpenSSL
    OpenSSL_add_all_algorithms();
    ERR_load_crypto_strings();
    
    // Message to sign
    const unsigned char message[] = "hehehe";
    size_t message_len = strlen((const char*)message);
    
    // Generate a new EC key pair using NIST P-384 curve
    EC_KEY* sk = EC_KEY_new_by_curve_name(NID_secp384r1); // NIST P-384
    if (!sk) {
        fprintf(stderr, "Error creating EC_KEY\n");
        return 1;
    }
    
    // Generate the key pair
    if (EC_KEY_generate_key(sk) != 1) {
        fprintf(stderr, "Error generating EC key pair\n");
        EC_KEY_free(sk);
        return 1;
    }
    
    // Sign the message
    ECDSA_SIG* sig = sign_message(sk, message, message_len);
    if (!sig) {
        fprintf(stderr, "Error signing message\n");
        EC_KEY_free(sk);
        return 1;
    }
    
    // Verify the signature
    int verification_result = verify_signature(sk, sig, message, message_len);
    
    // Print the result (1 for success, 0 for failure)
    if (verification_result == 1) {
        printf("True\n");
    } else if (verification_result == 0) {
        printf("False\n");
    } else {
        printf("Error during verification\n");
    }
    
    // Cleanup
    ECDSA_SIG_free(sig);
    EC_KEY_free(sk);
    EVP_cleanup();
    ERR_free_strings();
    
    return 0;
}