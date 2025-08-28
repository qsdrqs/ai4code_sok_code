#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <openssl/rand.h>
#include <openssl/bn.h>

// Structure to hold key pair
typedef struct {
    EC_KEY* ec_key;
} SigningKey;

// Function to generate a new signing key
SigningKey* generate_signing_key() {
    SigningKey* sk = malloc(sizeof(SigningKey));
    if (!sk) return NULL;
    
    sk->ec_key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!sk->ec_key) {
        free(sk);
        return NULL;
    }
    
    if (EC_KEY_generate_key(sk->ec_key) != 1) {
        EC_KEY_free(sk->ec_key);
        free(sk);
        return NULL;
    }
    
    return sk;
}

// Function to free signing key
void free_signing_key(SigningKey* sk) {
    if (sk) {
        if (sk->ec_key) {
            EC_KEY_free(sk->ec_key);
        }
        free(sk);
    }
}

// Function to sign a message
ECDSA_SIG* sign(const unsigned char* msg, size_t msg_len, SigningKey* key) {
    // Hash the message using SHA256
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256(msg, msg_len, hash);
    
    // Sign the hash
    ECDSA_SIG* signature = ECDSA_do_sign(hash, SHA256_DIGEST_LENGTH, key->ec_key);
    if (!signature) {
        return NULL;
    }
    
    // Verify the signature (assertion equivalent)
    int verify_result = ECDSA_do_verify(hash, SHA256_DIGEST_LENGTH, signature, key->ec_key);
    assert(verify_result == 1);
    
    return signature;
}

// Function to print signature (helper function)
void print_signature(ECDSA_SIG* sig) {
    if (!sig) {
        printf("NULL signature\n");
        return;
    }
    
    const BIGNUM* r;
    const BIGNUM* s;
    ECDSA_SIG_get0(sig, &r, &s);
    
    char* r_str = BN_bn2hex(r);
    char* s_str = BN_bn2hex(s);
    
    printf("Signature:\n");
    printf("r: %s\n", r_str);
    printf("s: %s\n", s_str);
    
    OPENSSL_free(r_str);
    OPENSSL_free(s_str);
}

// Test function
void test() {
    // Initialize OpenSSL
    RAND_poll();
    
    // Generate signing key
    SigningKey* sk = generate_signing_key();
    if (!sk) {
        printf("Failed to generate signing key\n");
        return;
    }
    
    // Sign message
    const char* message = "message";
    ECDSA_SIG* signature = sign((const unsigned char*)message, strlen(message), sk);
    
    // Print signature
    print_signature(signature);
    
    // Cleanup
    if (signature) {
        ECDSA_SIG_free(signature);
    }
    free_signing_key(sk);
}

int main() {
    test();
    return 0;
}