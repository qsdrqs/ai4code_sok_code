#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <openssl/rand.h>
#include <openssl/err.h>

// Structure to hold key pair
typedef struct {
    EC_KEY* ec_key;
} KeyPair;

// Structure to hold signature
typedef struct {
    ECDSA_SIG* sig;
} Signature;

// Function to generate NIST P-384 key pair
KeyPair* generate_key_pair() {
    KeyPair* kp = malloc(sizeof(KeyPair));
    if (!kp) return NULL;
    
    // Create EC_KEY structure for NIST P-384 curve
    kp->ec_key = EC_KEY_new_by_curve_name(NID_secp384r1);
    if (!kp->ec_key) {
        free(kp);
        return NULL;
    }
    
    // Generate the key pair
    if (EC_KEY_generate_key(kp->ec_key) != 1) {
        EC_KEY_free(kp->ec_key);
        free(kp);
        return NULL;
    }
    
    return kp;
}

// Function to sign a message
Signature* signMessage(KeyPair* key, const unsigned char* message, size_t msg_len) {
    if (!key || !message) return NULL;
    
    Signature* sig_struct = malloc(sizeof(Signature));
    if (!sig_struct) return NULL;
    
    // Hash the message using SHA-384 (appropriate for P-384 curve)
    unsigned char hash[SHA384_DIGEST_LENGTH];
    SHA384(message, msg_len, hash);
    
    // Sign the hash
    sig_struct->sig = ECDSA_do_sign(hash, SHA384_DIGEST_LENGTH, key->ec_key);
    if (!sig_struct->sig) {
        free(sig_struct);
        return NULL;
    }
    
    return sig_struct;
}

// Function to verify a signature
int verify_signature(KeyPair* key, Signature* signature, const unsigned char* message, size_t msg_len) {
    if (!key || !signature || !message) return 0;
    
    // Hash the message using SHA-384
    unsigned char hash[SHA384_DIGEST_LENGTH];
    SHA384(message, msg_len, hash);
    
    // Verify the signature
    int result = ECDSA_do_verify(hash, SHA384_DIGEST_LENGTH, signature->sig, key->ec_key);
    return result;
}

// Function to free key pair
void free_key_pair(KeyPair* kp) {
    if (kp) {
        if (kp->ec_key) {
            EC_KEY_free(kp->ec_key);
        }
        free(kp);
    }
}

// Function to free signature
void free_signature(Signature* sig) {
    if (sig) {
        if (sig->sig) {
            ECDSA_SIG_free(sig->sig);
        }
        free(sig);
    }
}

int main() {
    // Initialize OpenSSL
    ERR_load_crypto_strings();
    
    // Generate key pair (equivalent to SigningKey.generate(curve=NIST384p))
    KeyPair* sk = generate_key_pair();
    if (!sk) {
        fprintf(stderr, "Failed to generate key pair\n");
        return 1;
    }
    
    // The verifying key is the same EC_KEY object (contains both private and public key)
    KeyPair* vk = sk;  // In OpenSSL, the same EC_KEY contains both keys
    
    // Sign the message
    const char* test_message = "testing";
    Signature* signature = signMessage(sk, (const unsigned char*)test_message, strlen(test_message));
    if (!signature) {
        fprintf(stderr, "Failed to sign message\n");
        free_key_pair(sk);
        return 1;
    }
    
    // Verify the signature
    int verification_result = verify_signature(vk, signature, (const unsigned char*)test_message, strlen(test_message));
    
    // Print result (1 for success, 0 for failure)
    if (verification_result == 1) {
        printf("True\n");  // Equivalent to Python's True
    } else {
        printf("False\n"); // Equivalent to Python's False
    }
    
    // Cleanup
    free_signature(signature);
    free_key_pair(sk);
    
    // Cleanup OpenSSL
    ERR_free_strings();
    
    return 0;
}