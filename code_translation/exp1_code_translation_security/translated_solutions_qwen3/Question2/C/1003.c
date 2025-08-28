#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <openssl/bn.h>
#include <openssl/err.h>
#include <string.h>

/**
 * Signs a message using ECDSA with the NIST P-256 curve.
 *
 * @param message         The message to sign.
 * @param message_len     Length of the message in bytes.
 * @param signing_key_raw The raw private key (32 bytes).
 * @param key_len         Length of the private key (must be 32).
 * @param sig_len_out     Output parameter for the length of the signature.
 * @return                A pointer to the DER-encoded signature (must be freed with OPENSSL_free).
 */
unsigned char* sign_nist256(const unsigned char* message, size_t message_len,
                             const unsigned char* signing_key_raw, size_t key_len,
                             unsigned int* sig_len_out) {
    EC_KEY* key = NULL;
    BIGNUM* priv = NULL;
    unsigned char* sig = NULL;
    unsigned char hash[SHA256_DIGEST_LENGTH];
    unsigned int sig_len = 0;

    // Validate private key length
    if (key_len != 32) {
        return NULL;
    }

    // Create EC_KEY for NIST P-256 (secp256r1)
    key = EC_KEY_new_by_curve_name(NID_X9_62_prime256v1);
    if (!key) {
        goto err;
    }

    // Convert raw private key to BIGNUM
    priv = BN_bin2bn(signing_key_raw, 32, NULL);
    if (!priv) {
        goto err;
    }

    // Set private key
    if (EC_KEY_set_private_key(key, priv) != 1) {
        goto err;
    }

    // Hash the message
    SHA256(message, message_len, hash);

    // Allocate buffer for signature
    sig_len = ECDSA_size(key);
    sig = OPENSSL_malloc(sig_len);
    if (!sig) {
        goto err;
    }

    // Sign the hash
    if (ECDSA_sign(0, hash, SHA256_DIGEST_LENGTH, sig, &sig_len, key) != 1) {
        OPENSSL_free(sig);
        sig = NULL;
        goto err;
    }

    *sig_len_out = sig_len;

err:
    if (priv) BN_free(priv);
    if (key) EC_KEY_free(key);
    return sig;
}

/**
 * Verifies an ECDSA signature using the NIST P-256 curve.
 *
 * @param message             The message to verify.
 * @param message_len         Length of the message in bytes.
 * @param signature           The DER-encoded signature.
 * @param sig_len             Length of the signature in bytes.
 * @param verifying_key_raw   The raw public key (65 bytes, uncompressed format).
 * @param key_raw_len         Length of the public key (must be 65).
 * @return                    1 if the signature is valid, 0 otherwise.
 */
int verify_nist256(const unsigned char* message, size_t message_len,
                   const unsigned char* signature, size_t sig_len,
                   const unsigned char* verifying_key_raw, size_t key_raw_len) {
    EC_KEY* key = NULL;
    EC_POINT* pub_key = NULL;
    int ret = 0;
    unsigned char hash[SHA256_DIGEST_LENGTH];

    // Validate public key length
    if (key_raw_len != 65) {
        return 0;
    }

    // Create EC_KEY for NIST P-256 (secp256r1)
    key = EC_KEY_new_by_curve_name(NID_X9_62_prime256v1);
    if (!key) {
        return 0;
    }

    // Create public key point
    pub_key = EC_POINT_new(EC_KEY_get0_group(key));
    if (!pub_key) {
        goto err;
    }

    // Convert raw public key to EC_POINT
    if (EC_POINT_oct2point(EC_KEY_get0_group(key), pub_key, verifying_key_raw, key_raw_len, NULL) != 1) {
        goto err;
    }

    // Set public key in EC_KEY
    if (EC_KEY_set_public_key(key, pub_key) != 1) {
        goto err;
    }

    // Hash the message
    SHA256(message, message_len, hash);

    // Verify the signature
    int verify_status = ECDSA_verify(0, hash, SHA256_DIGEST_LENGTH, signature, sig_len, key);
    if (verify_status == 1) {
        ret = 1;
    } else {
        ret = 0;
    }

err:
    if (pub_key) EC_POINT_free(pub_key);
    if (key) EC_KEY_free(key);
    return ret;
}