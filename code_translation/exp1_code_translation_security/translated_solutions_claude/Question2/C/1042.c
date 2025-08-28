#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/bn.h>
#include <openssl/sha.h>
#include <openssl/evp.h>
#include <string.h>
#include <stdlib.h>

/**
 * Signs a given message using ECDSA signing key
 * @param message: message to be signed
 * @param message_len: length of the message
 * @param private_key: private key bytes to sign the message
 * @param private_key_len: length of the private key (should be 32 for SECP256k1)
 * @param signature: output buffer for signature (should be at least 72 bytes)
 * @param sig_len: pointer to store the actual signature length
 * @return: 1 on success, 0 on failure
 */
int sign_message(const unsigned char* message, size_t message_len, 
                 const unsigned char* private_key, size_t private_key_len,
                 unsigned char* signature, size_t* sig_len) {
    
    EC_KEY* ec_key = NULL;
    BIGNUM* priv_bn = NULL;
    unsigned char hash[SHA256_DIGEST_LENGTH];
    ECDSA_SIG* ecdsa_sig = NULL;
    int result = 0;
    
    // Create EC_KEY for SECP256k1 curve
    ec_key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!ec_key) {
        goto cleanup;
    }
    
    // Convert private key bytes to BIGNUM
    priv_bn = BN_bin2bn(private_key, private_key_len, NULL);
    if (!priv_bn) {
        goto cleanup;
    }
    
    // Set the private key
    if (!EC_KEY_set_private_key(ec_key, priv_bn)) {
        goto cleanup;
    }
    
    // Generate the corresponding public key
    const EC_GROUP* group = EC_KEY_get0_group(ec_key);
    EC_POINT* pub_key = EC_POINT_new(group);
    if (!pub_key) {
        goto cleanup;
    }
    
    if (!EC_POINT_mul(group, pub_key, priv_bn, NULL, NULL, NULL)) {
        EC_POINT_free(pub_key);
        goto cleanup;
    }
    
    if (!EC_KEY_set_public_key(ec_key, pub_key)) {
        EC_POINT_free(pub_key);
        goto cleanup;
    }
    EC_POINT_free(pub_key);
    
    // Hash the message using SHA256
    SHA256(message, message_len, hash);
    
    // Sign the hash
    ecdsa_sig = ECDSA_do_sign(hash, SHA256_DIGEST_LENGTH, ec_key);
    if (!ecdsa_sig) {
        goto cleanup;
    }
    
    // Convert signature to DER format
    int der_len = i2d_ECDSA_SIG(ecdsa_sig, NULL);
    if (der_len <= 0) {
        goto cleanup;
    }
    
    unsigned char* der_ptr = signature;
    if (i2d_ECDSA_SIG(ecdsa_sig, &der_ptr) != der_len) {
        goto cleanup;
    }
    
    *sig_len = der_len;
    result = 1;
    
cleanup:
    if (ec_key) EC_KEY_free(ec_key);
    if (priv_bn) BN_free(priv_bn);
    if (ecdsa_sig) ECDSA_SIG_free(ecdsa_sig);
    
    return result;
}

// Alternative version that returns raw r,s values instead of DER encoding
int sign_message_raw(const unsigned char* message, size_t message_len,
                     const unsigned char* private_key, size_t private_key_len,
                     unsigned char* r_bytes, unsigned char* s_bytes) {
    
    EC_KEY* ec_key = NULL;
    BIGNUM* priv_bn = NULL;
    unsigned char hash[SHA256_DIGEST_LENGTH];
    ECDSA_SIG* ecdsa_sig = NULL;
    const BIGNUM* r = NULL;
    const BIGNUM* s = NULL;
    int result = 0;
    
    // Create EC_KEY for SECP256k1 curve
    ec_key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!ec_key) {
        goto cleanup;
    }
    
    // Convert private key bytes to BIGNUM
    priv_bn = BN_bin2bn(private_key, private_key_len, NULL);
    if (!priv_bn) {
        goto cleanup;
    }
    
    // Set the private key
    if (!EC_KEY_set_private_key(ec_key, priv_bn)) {
        goto cleanup;
    }
    
    // Generate the corresponding public key
    const EC_GROUP* group = EC_KEY_get0_group(ec_key);
    EC_POINT* pub_key = EC_POINT_new(group);
    if (!pub_key) {
        goto cleanup;
    }
    
    if (!EC_POINT_mul(group, pub_key, priv_bn, NULL, NULL, NULL)) {
        EC_POINT_free(pub_key);
        goto cleanup;
    }
    
    if (!EC_KEY_set_public_key(ec_key, pub_key)) {
        EC_POINT_free(pub_key);
        goto cleanup;
    }
    EC_POINT_free(pub_key);
    
    // Hash the message using SHA256
    SHA256(message, message_len, hash);
    
    // Sign the hash
    ecdsa_sig = ECDSA_do_sign(hash, SHA256_DIGEST_LENGTH, ec_key);
    if (!ecdsa_sig) {
        goto cleanup;
    }
    
    // Extract r and s values
    ECDSA_SIG_get0(ecdsa_sig, &r, &s);
    
    // Convert r and s to 32-byte arrays
    if (BN_bn2binpad(r, r_bytes, 32) != 32 || 
        BN_bn2binpad(s, s_bytes, 32) != 32) {
        goto cleanup;
    }
    
    result = 1;
    
cleanup:
    if (ec_key) EC_KEY_free(ec_key);
    if (priv_bn) BN_free(priv_bn);
    if (ecdsa_sig) ECDSA_SIG_free(ecdsa_sig);
    
    return result;
}