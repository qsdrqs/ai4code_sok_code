#include <stdio.h>
#include <stdint.h>
#include <string.h>
#include <openssl/ecdsa.h>
#include <openssl/sha.h>
#include <openssl/obj_mac.h>

// Function to sign a message using NIST256p curve
unsigned char* sign_nist256(const unsigned char* message, size_t message_len, const unsigned char* signing_key_raw, size_t* signature_len) {
    // Create an ECDSA key from the provided raw key
    EC_KEY* key = EC_KEY_new_by_curve_name(NID_secp256r1);
    if (!key) {
        return NULL;
    }

    const EC_POINT* public_key = NULL;
    const BIGNUM* private_key = NULL;
    if (!EC_KEY_octet_string_to_key(key, signing_key_raw, 32, &public_key, &private_key)) {
        EC_KEY_free(key);
        return NULL;
    }

    // Set the private key
    if (!EC_KEY_set_private_key(key, private_key)) {
        EC_KEY_free(key);
        BN_free(private_key);
        return NULL;
    }

    // Create a new ECDSA signature
    ECDSA_SIG* signature = ECDSA_do_sign_ex(message, message_len, SHA256, key);
    if (!signature) {
        EC_KEY_free(key);
        BN_free(private_key);
        return NULL;
    }

    // Get the signature length
    *signature_len = ECDSA_size(key);

    // Allocate memory for the signature
    unsigned char* signature_bytes = (unsigned char*)malloc(*signature_len);
    if (!signature_bytes) {
        ECDSA_free(signature);
        EC_KEY_free(key);
        BN_free(private_key);
        return NULL;
    }

    // Convert the signature to bytes
    if (!ECDSA_SIG_to_octet_string(signature, signature_bytes, *signature_len)) {
        free(signature_bytes);
        ECDSA_free(signature);
        EC_KEY_free(key);
        BN_free(private_key);
        return NULL;
    }

    // Clean up
    ECDSA_free(signature);
    EC_KEY_free(key);
    BN_free(private_key);

    return signature_bytes;
}

// Function to verify a signature using NIST256p curve
int verify_nist256(const unsigned char* message, size_t message_len, const unsigned char* signature, size_t signature_len, const unsigned char* verifying_key_raw) {
    // Create an ECDSA key from the provided raw key
    EC_KEY* key = EC_KEY_new_by_curve_name(NID_secp256r1);
    if (!key) {
        return 0;
    }

    const EC_POINT* public_key = NULL;
    if (!EC_KEY_octet_string_to_key(key, verifying_key_raw, 32, &public_key, NULL)) {
        EC_KEY_free(key);
        return 0;
    }

    // Set the public key
    if (!EC_KEY_set_public_key(key, public_key)) {
        EC_KEY_free(key);
        return 0;
    }

    // Create a new ECDSA signature
    ECDSA_SIG* ecdsa_signature = ECDSA_SIG_new();
    if (!ecdsa_signature) {
        EC_KEY_free(key);
        return 0;
    }

    // Convert the signature to ECDSA_SIG
    if (!ECDSA_SIG_from_octet_string(ecdsa_signature, signature, signature_len)) {
        ECDSA_SIG_free(ecdsa_signature);
        EC_KEY_free(key);
        return 0;
    }

    // Verify the signature
    int result = ECDSA_do_verify_ex(message, message_len, ecdsa_signature, key, SHA256);

    // Clean up
    ECDSA_SIG_free(ecdsa_signature);
    EC_KEY_free(key);

    return result;
}

int main() {
    // Example usage
    const unsigned char* message = (const unsigned char*)"Hello, World!";
    size_t message_len = strlen((const char*)message);

    // Generate a private key (for demonstration purposes only)
    EC_KEY* key = EC_KEY_new_by_curve_name(NID_secp256r1);
    BIGNUM* private_key = BN_new();
    BN_rand(private_key, 256, 0, 0, 0);
    EC_KEY_set_private_key(key, private_key);
    const EC_POINT* public_key = EC_KEY_get0_public_key(key);

    // Get the private key bytes
    unsigned char signing_key_raw[32];
    BN_bn2bin(EC_KEY_get0_private_key(key), signing_key_raw);

    // Get the public key bytes
    unsigned char verifying_key_raw[32];
    EC_POINT_point2oct(EC_KEY_get0_group(key), public_key, POINT_CONVERSION_COMPRESSED, verifying_key_raw, 32, NULL);

    // Sign the message
    size_t signature_len;
    unsigned char* signature = sign_nist256(message, message_len, signing_key_raw, &signature_len);

    // Verify the signature
    int result = verify_nist256(message, message_len, signature, signature_len, verifying_key_raw);

    // Print the result
    printf("Verification result: %d\n", result);

    // Clean up
    free(signature);
    EC_KEY_free(key);
    BN_free(private_key);

    return 0;
}