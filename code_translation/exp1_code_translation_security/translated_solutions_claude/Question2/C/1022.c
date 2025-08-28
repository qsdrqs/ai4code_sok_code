#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>
#include <time.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <openssl/rand.h>
#include <openssl/bn.h>
#include <openssl/evp.h>

// Structure to hold ECDSA key pair
typedef struct {
    EC_KEY *ec_key;
    EC_GROUP *group;
} ECDSAKeyPair;

// Structure to hold signature
typedef struct {
    BIGNUM *r;
    BIGNUM *s;
} ECDSASignature;

// Initialize ECDSA with secp256k1 curve (commonly used)
ECDSAKeyPair* ecdsa_generate_keypair() {
    ECDSAKeyPair *keypair = malloc(sizeof(ECDSAKeyPair));
    if (!keypair) return NULL;
    
    // Create EC_KEY object
    keypair->ec_key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!keypair->ec_key) {
        free(keypair);
        return NULL;
    }
    
    // Generate key pair
    if (EC_KEY_generate_key(keypair->ec_key) != 1) {
        EC_KEY_free(keypair->ec_key);
        free(keypair);
        return NULL;
    }
    
    keypair->group = (EC_GROUP*)EC_KEY_get0_group(keypair->ec_key);
    
    return keypair;
}

// Hash message using SHA-256
int hash_message(const char *message, unsigned char *hash) {
    SHA256_CTX sha256;
    SHA256_Init(&sha256);
    SHA256_Update(&sha256, message, strlen(message));
    SHA256_Final(hash, &sha256);
    return 1;
}

// Sign message
ECDSASignature* ecdsa_sign(const char *message, ECDSAKeyPair *keypair) {
    if (!message || !keypair) return NULL;
    
    unsigned char hash[SHA256_DIGEST_LENGTH];
    if (!hash_message(message, hash)) return NULL;
    
    ECDSA_SIG *sig = ECDSA_do_sign(hash, SHA256_DIGEST_LENGTH, keypair->ec_key);
    if (!sig) return NULL;
    
    ECDSASignature *signature = malloc(sizeof(ECDSASignature));
    if (!signature) {
        ECDSA_SIG_free(sig);
        return NULL;
    }
    
    const BIGNUM *r, *s;
    ECDSA_SIG_get0(sig, &r, &s);
    
    signature->r = BN_dup(r);
    signature->s = BN_dup(s);
    
    ECDSA_SIG_free(sig);
    
    return signature;
}

// Verify signature
int ecdsa_verify(const char *message, ECDSASignature *signature, ECDSAKeyPair *keypair) {
    if (!message || !signature || !keypair) return 0;
    
    unsigned char hash[SHA256_DIGEST_LENGTH];
    if (!hash_message(message, hash)) return 0;
    
    ECDSA_SIG *sig = ECDSA_SIG_new();
    if (!sig) return 0;
    
    ECDSA_SIG_set0(sig, BN_dup(signature->r), BN_dup(signature->s));
    
    int result = ECDSA_do_verify(hash, SHA256_DIGEST_LENGTH, sig, keypair->ec_key);
    
    ECDSA_SIG_free(sig);
    
    return result;
}

// Free signature
void ecdsa_signature_free(ECDSASignature *signature) {
    if (signature) {
        if (signature->r) BN_free(signature->r);
        if (signature->s) BN_free(signature->s);
        free(signature);
    }
}

// Free keypair
void ecdsa_keypair_free(ECDSAKeyPair *keypair) {
    if (keypair) {
        if (keypair->ec_key) EC_KEY_free(keypair->ec_key);
        free(keypair);
    }
}

// Print signature in hex format (for debugging)
void print_signature(ECDSASignature *signature) {
    if (!signature) return;
    
    char *r_hex = BN_bn2hex(signature->r);
    char *s_hex = BN_bn2hex(signature->s);
    
    printf("Signature:\n");
    printf("r: %s\n", r_hex);
    printf("s: %s\n", s_hex);
    
    OPENSSL_free(r_hex);
    OPENSSL_free(s_hex);
}

int main() {
    // Initialize OpenSSL
    OpenSSL_add_all_algorithms();
    
    // Generate Keys (equivalent to privateKey = PrivateKey() and publicKey = privateKey.publicKey())
    ECDSAKeyPair *keypair = ecdsa_generate_keypair();
    if (!keypair) {
        printf("Failed to generate keypair\n");
        return 1;
    }
    
    const char *message = "My test message";
    
    // Generate Signature (equivalent to signature = Ecdsa.sign(message, privateKey))
    ECDSASignature *signature = ecdsa_sign(message, keypair);
    if (!signature) {
        printf("Failed to sign message\n");
        ecdsa_keypair_free(keypair);
        return 1;
    }
    
    // Verify if signature is valid (equivalent to print(Ecdsa.verify(message, signature, publicKey)))
    int is_valid = ecdsa_verify(message, signature, keypair);
    printf("%s\n", is_valid ? "true" : "false");
    
    // Optional: Print signature details
    // print_signature(signature);
    
    // Cleanup
    ecdsa_signature_free(signature);
    ecdsa_keypair_free(keypair);
    
    // Cleanup OpenSSL
    EVP_cleanup();
    
    return 0;
}