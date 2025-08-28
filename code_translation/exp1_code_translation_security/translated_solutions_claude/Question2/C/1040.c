#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <openssl/bn.h>
#include <openssl/evp.h>
#include <openssl/pem.h>

/**
 * Sign message using ECDSA with SECP256k1 curve
 * 
 * @param message: message to sign
 * @param message_len: length of the message
 * @param private_key: private key bytes (32 bytes for secp256k1)
 * @param private_key_len: length of private key (should be 32)
 * @param signature: output buffer for signature
 * @param sig_len: pointer to signature length (input: buffer size, output: actual signature size)
 * @return: 1 on success, 0 on failure
 */
int sign_message(const unsigned char* message, size_t message_len, 
                 const unsigned char* private_key, size_t private_key_len,
                 unsigned char* signature, size_t* sig_len) {
    
    EC_KEY* ec_key = NULL;
    BIGNUM* priv_bn = NULL;
    ECDSA_SIG* ecdsa_sig = NULL;
    int ret = 0;
    
    // Create EC_KEY structure for secp256k1
    ec_key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!ec_key) {
        fprintf(stderr, "Failed to create EC_KEY\n");
        goto cleanup;
    }
    
    // Convert private key bytes to BIGNUM
    priv_bn = BN_bin2bn(private_key, private_key_len, NULL);
    if (!priv_bn) {
        fprintf(stderr, "Failed to convert private key to BIGNUM\n");
        goto cleanup;
    }
    
    // Set private key
    if (!EC_KEY_set_private_key(ec_key, priv_bn)) {
        fprintf(stderr, "Failed to set private key\n");
        goto cleanup;
    }
    
    // Generate public key from private key
    const EC_GROUP* group = EC_KEY_get0_group(ec_key);
    EC_POINT* pub_key = EC_POINT_new(group);
    if (!pub_key) {
        fprintf(stderr, "Failed to create public key point\n");
        goto cleanup;
    }
    
    if (!EC_POINT_mul(group, pub_key, priv_bn, NULL, NULL, NULL)) {
        fprintf(stderr, "Failed to generate public key\n");
        EC_POINT_free(pub_key);
        goto cleanup;
    }
    
    if (!EC_KEY_set_public_key(ec_key, pub_key)) {
        fprintf(stderr, "Failed to set public key\n");
        EC_POINT_free(pub_key);
        goto cleanup;
    }
    EC_POINT_free(pub_key);
    
    // Sign the message
    ecdsa_sig = ECDSA_do_sign(message, message_len, ec_key);
    if (!ecdsa_sig) {
        fprintf(stderr, "Failed to sign message\n");
        goto cleanup;
    }
    
    // Convert signature to DER format
    int der_len = i2d_ECDSA_SIG(ecdsa_sig, NULL);
    if (der_len <= 0 || (size_t)der_len > *sig_len) {
        fprintf(stderr, "Signature buffer too small or encoding failed\n");
        goto cleanup;
    }
    
    unsigned char* sig_ptr = signature;
    if (i2d_ECDSA_SIG(ecdsa_sig, &sig_ptr) != der_len) {
        fprintf(stderr, "Failed to encode signature\n");
        goto cleanup;
    }
    
    *sig_len = der_len;
    ret = 1;
    
cleanup:
    if (ec_key) EC_KEY_free(ec_key);
    if (priv_bn) BN_free(priv_bn);
    if (ecdsa_sig) ECDSA_SIG_free(ecdsa_sig);
    
    return ret;
}

/**
 * Alternative function that returns signature as r,s components
 * 
 * @param message: message to sign
 * @param message_len: length of the message
 * @param private_key: private key bytes (32 bytes for secp256k1)
 * @param private_key_len: length of private key (should be 32)
 * @param r_out: output buffer for r component (32 bytes)
 * @param s_out: output buffer for s component (32 bytes)
 * @return: 1 on success, 0 on failure
 */
int sign_message_raw(const unsigned char* message, size_t message_len,
                     const unsigned char* private_key, size_t private_key_len,
                     unsigned char* r_out, unsigned char* s_out) {
    
    EC_KEY* ec_key = NULL;
    BIGNUM* priv_bn = NULL;
    ECDSA_SIG* ecdsa_sig = NULL;
    int ret = 0;
    
    // Create EC_KEY structure for secp256k1
    ec_key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!ec_key) {
        fprintf(stderr, "Failed to create EC_KEY\n");
        goto cleanup;
    }
    
    // Convert private key bytes to BIGNUM
    priv_bn = BN_bin2bn(private_key, private_key_len, NULL);
    if (!priv_bn) {
        fprintf(stderr, "Failed to convert private key to BIGNUM\n");
        goto cleanup;
    }
    
    // Set private key
    if (!EC_KEY_set_private_key(ec_key, priv_bn)) {
        fprintf(stderr, "Failed to set private key\n");
        goto cleanup;
    }
    
    // Generate public key from private key
    const EC_GROUP* group = EC_KEY_get0_group(ec_key);
    EC_POINT* pub_key = EC_POINT_new(group);
    if (!pub_key) {
        fprintf(stderr, "Failed to create public key point\n");
        goto cleanup;
    }
    
    if (!EC_POINT_mul(group, pub_key, priv_bn, NULL, NULL, NULL)) {
        fprintf(stderr, "Failed to generate public key\n");
        EC_POINT_free(pub_key);
        goto cleanup;
    }
    
    if (!EC_KEY_set_public_key(ec_key, pub_key)) {
        fprintf(stderr, "Failed to set public key\n");
        EC_POINT_free(pub_key);
        goto cleanup;
    }
    EC_POINT_free(pub_key);
    
    // Sign the message
    ecdsa_sig = ECDSA_do_sign(message, message_len, ec_key);
    if (!ecdsa_sig) {
        fprintf(stderr, "Failed to sign message\n");
        goto cleanup;
    }
    
    // Extract r and s components
    const BIGNUM* r = NULL;
    const BIGNUM* s = NULL;
    ECDSA_SIG_get0(ecdsa_sig, &r, &s);
    
    // Convert r and s to 32-byte arrays
    memset(r_out, 0, 32);
    memset(s_out, 0, 32);
    
    int r_len = BN_num_bytes(r);
    int s_len = BN_num_bytes(s);
    
    if (r_len > 32 || s_len > 32) {
        fprintf(stderr, "Invalid signature component size\n");
        goto cleanup;
    }
    
    BN_bn2bin(r, r_out + (32 - r_len));
    BN_bn2bin(s, s_out + (32 - s_len));
    
    ret = 1;
    
cleanup:
    if (ec_key) EC_KEY_free(ec_key);
    if (priv_bn) BN_free(priv_bn);
    if (ecdsa_sig) ECDSA_SIG_free(ecdsa_sig);
    
    return ret;
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
    
    // Message to sign
    const char* message = "Hello, World!";
    
    // Buffer for signature
    unsigned char signature[256];
    size_t sig_len = sizeof(signature);
    
    // Sign the message
    if (sign_message((const unsigned char*)message, strlen(message), 
                     private_key, sizeof(private_key), 
                     signature, &sig_len)) {
        printf("Message signed successfully!\n");
        printf("Signature length: %zu bytes\n", sig_len);
        
        // Print signature in hex
        printf("Signature: ");
        for (size_t i = 0; i < sig_len; i++) {
            printf("%02x", signature[i]);
        }
        printf("\n");
    } else {
        printf("Failed to sign message\n");
        return 1;
    }
    
    // Example using raw signature format
    unsigned char r[32], s[32];
    if (sign_message_raw((const unsigned char*)message, strlen(message),
                         private_key, sizeof(private_key), r, s)) {
        printf("\nRaw signature components:\n");
        printf("r: ");
        for (int i = 0; i < 32; i++) {
            printf("%02x", r[i]);
        }
        printf("\ns: ");
        for (int i = 0; i < 32; i++) {
            printf("%02x", s[i]);
        }
        printf("\n");
    }
    
    return 0;
}