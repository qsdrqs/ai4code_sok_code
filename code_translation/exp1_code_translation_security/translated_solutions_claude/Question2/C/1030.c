#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/evp.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/bn.h>
#include <openssl/rand.h>
#include <openssl/obj_mac.h>

// Function to compute SHA3-256 hash and return as BIGNUM
BIGNUM* sha3_256Hash(const char* msg) {
    EVP_MD_CTX* ctx = EVP_MD_CTX_new();
    const EVP_MD* md = EVP_sha3_256();
    unsigned char hash[32]; // SHA3-256 produces 32 bytes
    unsigned int hash_len;
    
    EVP_DigestInit_ex(ctx, md, NULL);
    EVP_DigestUpdate(ctx, msg, strlen(msg));
    EVP_DigestFinal_ex(ctx, hash, &hash_len);
    EVP_MD_CTX_free(ctx);
    
    // Convert hash bytes to BIGNUM (big endian)
    BIGNUM* hash_bn = BN_new();
    BN_bin2bn(hash, hash_len, hash_bn);
    
    return hash_bn;
}

// Function to sign message using ECDSA secp256k1
ECDSA_SIG* signECDSAsecp256k1(const char* msg, const BIGNUM* privKey) {
    // Create EC_KEY for secp256k1
    EC_KEY* key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!key) {
        printf("Error creating EC_KEY\n");
        return NULL;
    }
    
    // Set private key
    if (!EC_KEY_set_private_key(key, privKey)) {
        printf("Error setting private key\n");
        EC_KEY_free(key);
        return NULL;
    }
    
    // Generate corresponding public key
    const EC_GROUP* group = EC_KEY_get0_group(key);
    EC_POINT* pub_key = EC_POINT_new(group);
    if (!EC_POINT_mul(group, pub_key, privKey, NULL, NULL, NULL)) {
        printf("Error generating public key\n");
        EC_POINT_free(pub_key);
        EC_KEY_free(key);
        return NULL;
    }
    EC_KEY_set_public_key(key, pub_key);
    EC_POINT_free(pub_key);
    
    // Hash the message
    BIGNUM* msgHash = sha3_256Hash(msg);
    
    // Convert hash to bytes for signing
    unsigned char hash_bytes[32];
    int hash_len = BN_bn2binpad(msgHash, hash_bytes, 32);
    
    // Sign the hash
    ECDSA_SIG* signature = ECDSA_do_sign(hash_bytes, hash_len, key);
    
    // Cleanup
    BN_free(msgHash);
    EC_KEY_free(key);
    
    return signature;
}

// Function to verify ECDSA signature
int verifyECDSAsecp256k1(const char* msg, const ECDSA_SIG* signature, const EC_POINT* pubKey) {
    // Create EC_KEY for secp256k1
    EC_KEY* key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!key) {
        printf("Error creating EC_KEY for verification\n");
        return 0;
    }
    
    // Set public key
    if (!EC_KEY_set_public_key(key, pubKey)) {
        printf("Error setting public key\n");
        EC_KEY_free(key);
        return 0;
    }
    
    // Hash the message
    BIGNUM* msgHash = sha3_256Hash(msg);
    
    // Convert hash to bytes for verification
    unsigned char hash_bytes[32];
    int hash_len = BN_bn2binpad(msgHash, hash_bytes, 32);
    
    // Verify signature
    int valid = ECDSA_do_verify(hash_bytes, hash_len, signature, key);
    
    // Cleanup
    BN_free(msgHash);
    EC_KEY_free(key);
    
    return valid;
}

// Function to generate random private key
BIGNUM* generate_private_key() {
    EC_KEY* temp_key = EC_KEY_new_by_curve_name(NID_secp256k1);
    const EC_GROUP* group = EC_KEY_get0_group(temp_key);
    BIGNUM* order = BN_new();
    BIGNUM* privKey = BN_new();
    
    // Get the order of the curve
    EC_GROUP_get_order(group, order, NULL);
    
    // Generate random number less than order
    BN_rand_range(privKey, order);
    
    BN_free(order);
    EC_KEY_free(temp_key);
    
    return privKey;
}

// Function to get public key from private key
EC_POINT* get_public_key(const BIGNUM* privKey) {
    EC_KEY* key = EC_KEY_new_by_curve_name(NID_secp256k1);
    const EC_GROUP* group = EC_KEY_get0_group(key);
    EC_POINT* pubKey = EC_POINT_new(group);
    
    // Generate public key: pubKey = privKey * G
    EC_POINT_mul(group, pubKey, privKey, NULL, NULL, NULL);
    
    EC_KEY_free(key);
    return pubKey;
}

int main() {
    // Initialize OpenSSL
    OpenSSL_add_all_algorithms();
    
    const char* msg = "Message for ECDSA signing";
    
    // Generate private key (random number)
    BIGNUM* privKey = generate_private_key();
    
    // Sign the message
    ECDSA_SIG* signature = signECDSAsecp256k1(msg, privKey);
    
    if (signature) {
        // Get signature components
        const BIGNUM* r = ECDSA_SIG_get0_r(signature);
        const BIGNUM* s = ECDSA_SIG_get0_s(signature);
        
        // Print results
        printf("Message: %s\n", msg);
        printf("Private key: 0x%s\n", BN_bn2hex(privKey));
        printf("Signature: r=0x%s, s=0x%s\n", BN_bn2hex(r), BN_bn2hex(s));
        
        // Optional: Verify the signature
        EC_POINT* pubKey = get_public_key(privKey);
        int valid = verifyECDSAsecp256k1(msg, signature, pubKey);
        printf("Signature valid: %s\n", valid ? "true" : "false");
        
        // Cleanup
        EC_POINT_free(pubKey);
        ECDSA_SIG_free(signature);
    } else {
        printf("Error creating signature\n");
    }
    
    BN_free(privKey);
    EVP_cleanup();
    
    return 0;
}