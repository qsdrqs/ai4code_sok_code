#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/sha.h>
#include <openssl/bn.h>
#include <openssl/evp.h>
#include <openssl/pem.h>

/**
 * Sign a message using a given ECDSA signing key.
 * 
 * @param message: The message to be signed
 * @param message_len: Length of the message
 * @param private_key: The private key bytes (32 bytes for SECP256k1)
 * @param signature: Output buffer for the signature (at least 64 bytes)
 * @param sig_len: Pointer to store the actual signature length
 * @return: 1 on success, 0 on failure
 */
int sign_message(const unsigned char* message, size_t message_len, 
                 const unsigned char* private_key, 
                 unsigned char* signature, unsigned int* sig_len) {
    
    EC_KEY* ec_key = NULL;
    ECDSA_SIG* ecdsa_sig = NULL;
    BIGNUM* priv_bn = NULL;
    unsigned char hash[SHA256_DIGEST_LENGTH];
    int result = 0;
    
    // Create EC_KEY structure for SECP256k1 curve
    ec_key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!ec_key) {
        fprintf(stderr, "Failed to create EC_KEY\n");
        goto cleanup;
    }
    
    // Convert private key bytes to BIGNUM
    priv_bn = BN_bin2bn(private_key, 32, NULL);
    if (!priv_bn) {
        fprintf(stderr, "Failed to convert private key to BIGNUM\n");
        goto cleanup;
    }
    
    // Set the private key
    if (EC_KEY_set_private_key(ec_key, priv_bn) != 1) {
        fprintf(stderr, "Failed to set private key\n");
        goto cleanup;
    }
    
    // Generate the corresponding public key
    const EC_GROUP* group = EC_KEY_get0_group(ec_key);
    EC_POINT* pub_key_point = EC_POINT_new(group);
    if (!pub_key_point) {
        fprintf(stderr, "Failed to create public key point\n");
        goto cleanup;
    }
    
    if (EC_POINT_mul(group, pub_key_point, priv_bn, NULL, NULL, NULL) != 1) {
        fprintf(stderr, "Failed to generate public key\n");
        EC_POINT_free(pub_key_point);
        goto cleanup;
    }
    
    if (EC_KEY_set_public_key(ec_key, pub_key_point) != 1) {
        fprintf(stderr, "Failed to set public key\n");
        EC_POINT_free(pub_key_point);
        goto cleanup;
    }
    EC_POINT_free(pub_key_point);
    
    // Hash the message using SHA-256
    if (SHA256(message, message_len, hash) == NULL) {
        fprintf(stderr, "Failed to hash message\n");
        goto cleanup;
    }
    
    // Sign the hash
    ecdsa_sig = ECDSA_do_sign(hash, SHA256_DIGEST_LENGTH, ec_key);
    if (!ecdsa_sig) {
        fprintf(stderr, "Failed to sign message\n");
        goto cleanup;
    }
    
    // Convert signature to DER format
    unsigned char* sig_ptr = signature;
    int der_len = i2d_ECDSA_SIG(ecdsa_sig, &sig_ptr);
    if (der_len <= 0) {
        fprintf(stderr, "Failed to encode signature\n");
        goto cleanup;
    }
    
    *sig_len = der_len;
    result = 1;
    
cleanup:
    if (ec_key) EC_KEY_free(ec_key);
    if (ecdsa_sig) ECDSA_SIG_free(ecdsa_sig);
    if (priv_bn) BN_free(priv_bn);
    
    return result;
}

/**
 * Alternative function that returns signature in raw format (r,s components)
 * This is closer to what the Python ecdsa library typically returns
 */
int sign_message_raw(const unsigned char* message, size_t message_len,
                     const unsigned char* private_key,
                     unsigned char* signature, unsigned int* sig_len) {
    
    EC_KEY* ec_key = NULL;
    ECDSA_SIG* ecdsa_sig = NULL;
    BIGNUM* priv_bn = NULL;
    unsigned char hash[SHA256_DIGEST_LENGTH];
    int result = 0;
    
    // Create EC_KEY structure for SECP256k1 curve
    ec_key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!ec_key) {
        fprintf(stderr, "Failed to create EC_KEY\n");
        goto cleanup;
    }
    
    // Convert private key bytes to BIGNUM
    priv_bn = BN_bin2bn(private_key, 32, NULL);
    if (!priv_bn) {
        fprintf(stderr, "Failed to convert private key to BIGNUM\n");
        goto cleanup;
    }
    
    // Set the private key
    if (EC_KEY_set_private_key(ec_key, priv_bn) != 1) {
        fprintf(stderr, "Failed to set private key\n");
        goto cleanup;
    }
    
    // Generate the corresponding public key
    const EC_GROUP* group = EC_KEY_get0_group(ec_key);
    EC_POINT* pub_key_point = EC_POINT_new(group);
    if (!pub_key_point) {
        fprintf(stderr, "Failed to create public key point\n");
        goto cleanup;
    }
    
    if (EC_POINT_mul(group, pub_key_point, priv_bn, NULL, NULL, NULL) != 1) {
        fprintf(stderr, "Failed to generate public key\n");
        EC_POINT_free(pub_key_point);
        goto cleanup;
    }
    
    if (EC_KEY_set_public_key(ec_key, pub_key_point) != 1) {
        fprintf(stderr, "Failed to set public key\n");
        EC_POINT_free(pub_key_point);
        goto cleanup;
    }
    EC_POINT_free(pub_key_point);
    
    // Hash the message using SHA-256
    if (SHA256(message, message_len, hash) == NULL) {
        fprintf(stderr, "Failed to hash message\n");
        goto cleanup;
    }
    
    // Sign the hash
    ecdsa_sig = ECDSA_do_sign(hash, SHA256_DIGEST_LENGTH, ec_key);
    if (!ecdsa_sig) {
        fprintf(stderr, "Failed to sign message\n");
        goto cleanup;
    }
    
    // Extract r and s components
    const BIGNUM* r = ECDSA_SIG_get0_r(ecdsa_sig);
    const BIGNUM* s = ECDSA_SIG_get0_s(ecdsa_sig);
    
    // Convert r and s to binary format (32 bytes each for SECP256k1)
    if (BN_bn2binpad(r, signature, 32) != 32) {
        fprintf(stderr, "Failed to convert r component\n");
        goto cleanup;
    }
    
    if (BN_bn2binpad(s, signature + 32, 32) != 32) {
        fprintf(stderr, "Failed to convert s component\n");
        goto cleanup;
    }
    
    *sig_len = 64; // 32 bytes for r + 32 bytes for s
    result = 1;
    
cleanup:
    if (ec_key) EC_KEY_free(ec_key);
    if (ecdsa_sig) ECDSA_SIG_free(ecdsa_sig);
    if (priv_bn) BN_free(priv_bn);
    
    return result;
}

// Example usage
int main() {
    // Example private key (32 bytes)
    unsigned char private_key[32] = {
        0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08,
        0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f, 0x10,
        0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x18,
        0x19, 0x1a, 0x1b, 0x1c, 0x1d, 0x1e, 0x1f, 0x20
    };
    
    const char* message = "Hello, World!";
    unsigned char signature[128]; // Buffer for signature
    unsigned int sig_len;
    
    // Sign the message
    if (sign_message_raw((unsigned char*)message, strlen(message), 
                        private_key, signature, &sig_len)) {
        printf("Message signed successfully!\n");
        printf("Signature length: %u bytes\n", sig_len);
        
        printf("Signature (hex): ");
        for (unsigned int i = 0; i < sig_len; i++) {
            printf("%02x", signature[i]);
        }
        printf("\n");
    } else {
        printf("Failed to sign message\n");
        return 1;
    }
    
    return 0;
}