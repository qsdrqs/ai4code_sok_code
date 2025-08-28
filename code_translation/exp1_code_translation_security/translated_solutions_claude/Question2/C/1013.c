#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <openssl/rand.h>
#include <openssl/err.h>

// Function to generate a new ECDSA key pair (equivalent to SigningKey.generate())
EC_KEY* generate_signing_key() {
    EC_KEY *key = NULL;
    
    // Create key using NIST P-192 curve (secp192r1)
    key = EC_KEY_new_by_curve_name(NID_X9_62_prime192v1);
    if (key == NULL) {
        fprintf(stderr, "Error creating EC_KEY\n");
        return NULL;
    }
    
    // Generate the key pair
    if (EC_KEY_generate_key(key) != 1) {
        fprintf(stderr, "Error generating key pair\n");
        EC_KEY_free(key);
        return NULL;
    }
    
    return key;
}

// Function to sign a message (equivalent to key.sign())
ECDSA_SIG* sign(const char* message, EC_KEY* key) {
    if (message == NULL || key == NULL) {
        return NULL;
    }
    
    // Calculate SHA-1 hash of the message (ecdsa library default)
    unsigned char hash[SHA_DIGEST_LENGTH];
    SHA1((unsigned char*)message, strlen(message), hash);
    
    // Sign the hash
    ECDSA_SIG *signature = ECDSA_do_sign(hash, SHA_DIGEST_LENGTH, key);
    if (signature == NULL) {
        fprintf(stderr, "Error signing message\n");
        return NULL;
    }
    
    return signature;
}

// Helper function to print signature in hex format
void print_signature(ECDSA_SIG* sig) {
    if (sig == NULL) {
        printf("NULL signature\n");
        return;
    }
    
    const BIGNUM *r, *s;
    ECDSA_SIG_get0(sig, &r, &s);
    
    char *r_hex = BN_bn2hex(r);
    char *s_hex = BN_bn2hex(s);
    
    printf("Signature:\n");
    printf("r: %s\n", r_hex);
    printf("s: %s\n", s_hex);
    
    OPENSSL_free(r_hex);
    OPENSSL_free(s_hex);
}

int main() {
    // Initialize OpenSSL
    ERR_load_crypto_strings();
    
    // Generate a signing key (equivalent to SigningKey.generate())
    EC_KEY* signing_key = generate_signing_key();
    if (signing_key == NULL) {
        fprintf(stderr, "Failed to generate signing key\n");
        return 1;
    }
    
    // Sign the message "something"
    ECDSA_SIG* signature = sign("something", signing_key);
    if (signature == NULL) {
        fprintf(stderr, "Failed to sign message\n");
        EC_KEY_free(signing_key);
        return 1;
    }
    
    // Print the signature (equivalent to print())
    print_signature(signature);
    
    // Cleanup
    ECDSA_SIG_free(signature);
    EC_KEY_free(signing_key);
    ERR_free_strings();
    
    return 0;
}