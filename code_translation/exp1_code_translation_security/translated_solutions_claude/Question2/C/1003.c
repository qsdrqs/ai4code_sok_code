#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/evp.h>
#include <openssl/sha.h>
#include <openssl/bn.h>
#include <openssl/obj_mac.h>

// Function to sign a message using NIST P-256 curve
// Returns signature length on success, -1 on failure
int sign_nist256(const unsigned char* message, size_t message_len, 
                 const unsigned char* signing_key_raw, size_t key_len,
                 unsigned char* signature, size_t* sig_len) {
    
    EC_KEY* ec_key = NULL;
    BIGNUM* priv_key_bn = NULL;
    unsigned char hash[SHA256_DIGEST_LENGTH];
    int result = -1;
    
    // Create EC_KEY for NIST P-256 curve
    ec_key = EC_KEY_new_by_curve_name(NID_X9_62_prime256v1);
    if (!ec_key) {
        goto cleanup;
    }
    
    // Convert raw private key to BIGNUM
    priv_key_bn = BN_bin2bn(signing_key_raw, key_len, NULL);
    if (!priv_key_bn) {
        goto cleanup;
    }
    
    // Set private key
    if (EC_KEY_set_private_key(ec_key, priv_key_bn) != 1) {
        goto cleanup;
    }
    
    // Generate public key from private key
    const EC_GROUP* group = EC_KEY_get0_group(ec_key);
    EC_POINT* pub_key = EC_POINT_new(group);
    if (!pub_key) {
        goto cleanup;
    }
    
    if (EC_POINT_mul(group, pub_key, priv_key_bn, NULL, NULL, NULL) != 1) {
        EC_POINT_free(pub_key);
        goto cleanup;
    }
    
    if (EC_KEY_set_public_key(ec_key, pub_key) != 1) {
        EC_POINT_free(pub_key);
        goto cleanup;
    }
    EC_POINT_free(pub_key);
    
    // Hash the message with SHA-256
    SHA256(message, message_len, hash);
    
    // Sign the hash
    unsigned int actual_sig_len = *sig_len;
    if (ECDSA_sign(0, hash, SHA256_DIGEST_LENGTH, signature, &actual_sig_len, ec_key) == 1) {
        *sig_len = actual_sig_len;
        result = actual_sig_len;
    }
    
cleanup:
    if (ec_key) EC_KEY_free(ec_key);
    if (priv_key_bn) BN_free(priv_key_bn);
    return result;
}

// Function to verify a signature using NIST P-256 curve
// Returns 1 for valid signature, 0 for invalid/error
int verify_nist256(const unsigned char* message, size_t message_len,
                   const unsigned char* signature, size_t sig_len,
                   const unsigned char* verifying_key_raw, size_t key_len) {
    
    EC_KEY* ec_key = NULL;
    EC_POINT* pub_key = NULL;
    unsigned char hash[SHA256_DIGEST_LENGTH];
    int result = 0;
    
    // Create EC_KEY for NIST P-256 curve
    ec_key = EC_KEY_new_by_curve_name(NID_X9_62_prime256v1);
    if (!ec_key) {
        goto cleanup;
    }
    
    const EC_GROUP* group = EC_KEY_get0_group(ec_key);
    pub_key = EC_POINT_new(group);
    if (!pub_key) {
        goto cleanup;
    }
    
    // Convert raw public key to EC_POINT
    // Assuming uncompressed format (0x04 + 32 bytes x + 32 bytes y)
    if (key_len == 64) {
        // Raw format without 0x04 prefix
        unsigned char full_key[65];
        full_key[0] = 0x04;
        memcpy(full_key + 1, verifying_key_raw, 64);
        
        if (EC_POINT_oct2point(group, pub_key, full_key, 65, NULL) != 1) {
            goto cleanup;
        }
    } else if (key_len == 65 && verifying_key_raw[0] == 0x04) {
        // Already in correct format
        if (EC_POINT_oct2point(group, pub_key, verifying_key_raw, key_len, NULL) != 1) {
            goto cleanup;
        }
    } else {
        goto cleanup;
    }
    
    // Set public key
    if (EC_KEY_set_public_key(ec_key, pub_key) != 1) {
        goto cleanup;
    }
    
    // Hash the message with SHA-256
    SHA256(message, message_len, hash);
    
    // Verify the signature
    result = ECDSA_verify(0, hash, SHA256_DIGEST_LENGTH, signature, sig_len, ec_key);
    
cleanup:
    if (ec_key) EC_KEY_free(ec_key);
    if (pub_key) EC_POINT_free(pub_key);
    return result;
}

// Example usage
int main() {
    // Initialize OpenSSL
    OpenSSL_add_all_algorithms();
    
    // Example private key (32 bytes for P-256)
    unsigned char private_key[32] = {
        0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08,
        0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f, 0x10,
        0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x18,
        0x19, 0x1a, 0x1b, 0x1c, 0x1d, 0x1e, 0x1f, 0x20
    };
    
    const char* message = "Hello, World!";
    unsigned char signature[256];
    size_t sig_len = sizeof(signature);
    
    // Sign the message
    int sign_result = sign_nist256((unsigned char*)message, strlen(message), 
                                   private_key, sizeof(private_key), 
                                   signature, &sig_len);
    
    if (sign_result > 0) {
        printf("Signature created successfully, length: %d\n", sign_result);
        
        // For verification, you would need the corresponding public key
        // This is just a placeholder - in practice, you'd derive or have the public key
        unsigned char public_key[64]; // This should be the actual public key
        
        // Note: In a real implementation, you would have the actual public key
        // corresponding to the private key used for signing
        
        printf("Signing completed successfully\n");
    } else {
        printf("Signing failed\n");
    }
    
    // Cleanup OpenSSL
    EVP_cleanup();
    
    return 0;
}