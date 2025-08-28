#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <string.h>
#include <stdbool.h>

// Function to sign a message using NIST P-256 curve
unsigned char* sign_nist256(const unsigned char* message, size_t message_len, const unsigned char* signing_key_raw, size_t* signature_len) {
    EC_KEY* ec_key = NULL;
    unsigned char* signature = NULL;

    // Create a new EC_KEY object for the NIST P-256 curve
    ec_key = EC_KEY_new_by_curve_name(NID_X9_62_prime256v1);
    if (!ec_key) {
        fprintf(stderr, "Error creating EC_KEY object\n");
        return NULL;
    }

    // Set the private key
    BIGNUM* priv_key = BN_bin2bn(signing_key_raw, 32, NULL); // 32 bytes for NIST P-256 private key
    if (!EC_KEY_set_private_key(ec_key, priv_key)) {
        fprintf(stderr, "Error setting private key\n");
        BN_free(priv_key);
        EC_KEY_free(ec_key);
        return NULL;
    }
    BN_free(priv_key);

    // Hash the message using SHA-256
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256(message, message_len, hash);

    // Sign the hash
    signature = OPENSSL_malloc(ECDSA_size(ec_key));
    if (!signature) {
        fprintf(stderr, "Error allocating memory for signature\n");
        EC_KEY_free(ec_key);
        return NULL;
    }

    if (!ECDSA_sign(0, hash, SHA256_DIGEST_LENGTH, signature, (unsigned int*)signature_len, ec_key)) {
        fprintf(stderr, "Error signing message\n");
        OPENSSL_free(signature);
        EC_KEY_free(ec_key);
        return NULL;
    }

    EC_KEY_free(ec_key);
    return signature;
}

// Function to verify a signature using NIST P-256 curve
bool verify_nist256(const unsigned char* message, size_t message_len, const unsigned char* signature, size_t signature_len, const unsigned char* verifying_key_raw) {
    EC_KEY* ec_key = NULL;
    bool result = false;

    // Create a new EC_KEY object for the NIST P-256 curve
    ec_key = EC_KEY_new_by_curve_name(NID_X9_62_prime256v1);
    if (!ec_key) {
        fprintf(stderr, "Error creating EC_KEY object\n");
        return false;
    }

    // Set the public key
    EC_POINT* pub_key = EC_POINT_new(EC_KEY_get0_group(ec_key));
    if (!pub_key) {
        fprintf(stderr, "Error creating EC_POINT object\n");
        EC_KEY_free(ec_key);
        return false;
    }

    if (!EC_POINT_oct2point(EC_KEY_get0_group(ec_key), pub_key, verifying_key_raw, 65, NULL)) { // 65 bytes for uncompressed public key
        fprintf(stderr, "Error setting public key\n");
        EC_POINT_free(pub_key);
        EC_KEY_free(ec_key);
        return false;
    }

    if (!EC_KEY_set_public_key(ec_key, pub_key)) {
        fprintf(stderr, "Error setting public key in EC_KEY\n");
        EC_POINT_free(pub_key);
        EC_KEY_free(ec_key);
        return false;
    }
    EC_POINT_free(pub_key);

    // Hash the message using SHA-256
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256(message, message_len, hash);

    // Verify the signature
    if (ECDSA_verify(0, hash, SHA256_DIGEST_LENGTH, signature, signature_len, ec_key) == 1) {
        result = true;
    }

    EC_KEY_free(ec_key);
    return result;
}