#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ec.h>      // For Elliptic Curve functions
#include <openssl/ecdsa.h>   // For ECDSA signing
#include <openssl/obj_mac.h> // For NID_secp256k1
#include <openssl/sha.h>     // For SHA256
#include <openssl/bn.h>      // For BIGNUM

// Helper function to convert a byte array to a hex string.
// The caller is responsible for freeing the returned string.
char* bytes_to_hex_string(const unsigned char* bytes, size_t len) {
    if (bytes == NULL || len == 0) {
        return NULL;
    }
    char* hex_str = malloc(len * 2 + 1);
    if (hex_str == NULL) {
        fprintf(stderr, "Failed to allocate memory for hex string\n");
        return NULL;
    }
    for (size_t i = 0; i < len; i++) {
        sprintf(hex_str + i * 2, "%02x", bytes[i]);
    }
    hex_str[len * 2] = '\0';
    return hex_str;
}

// Helper function to convert a hex string to a byte array.
// The caller is responsible for freeing the returned byte array.
unsigned char* hex_string_to_bytes(const char* hex_str, size_t* out_len) {
    size_t len = strlen(hex_str);
    if (len % 2 != 0) {
        return NULL; // Invalid hex string
    }
    *out_len = len / 2;
    unsigned char* bytes = malloc(*out_len);
    if (bytes == NULL) {
        fprintf(stderr, "Failed to allocate memory for bytes\n");
        return NULL;
    }
    for (size_t i = 0; i < *out_len; i++) {
        sscanf(hex_str + 2 * i, "%2hhx", &bytes[i]);
    }
    return bytes;
}

/**
 * @brief Signs a message using a private key with ECDSA (secp256k1).
 *
 * @param message The message string to sign.
 * @param private_key The raw private key bytes (32 bytes).
 * @param private_key_len The length of the private key.
 * @return A hex-encoded DER signature string. The caller must free this string.
 *         Returns NULL on failure.
 */
char* sign_message(const char* message, const unsigned char* private_key, size_t private_key_len) {
    // 1. Hash the message
    unsigned char message_hash[SHA256_DIGEST_LENGTH];
    SHA256((const unsigned char*)message, strlen(message), message_hash);

    // 2. Set up the ECDSA key
    EC_KEY* ec_key = NULL;
    BIGNUM* priv_key_bn = NULL;
    char* hex_signature = NULL;

    // Create a new EC key object for the secp256k1 curve
    ec_key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (ec_key == NULL) {
        fprintf(stderr, "Error: Failed to create new EC key.\n");
        return NULL;
    }

    // Convert the raw private key bytes to a BIGNUM
    priv_key_bn = BN_bin2bn(private_key, private_key_len, NULL);
    if (priv_key_bn == NULL) {
        fprintf(stderr, "Error: Failed to convert private key to BIGNUM.\n");
        EC_KEY_free(ec_key);
        return NULL;
    }

    // Set the private key
    if (!EC_KEY_set_private_key(ec_key, priv_key_bn)) {
        fprintf(stderr, "Error: Failed to set private key.\n");
        BN_free(priv_key_bn);
        EC_KEY_free(ec_key);
        return NULL;
    }

    // It's good practice to derive the public key from the private key
    const EC_GROUP* group = EC_KEY_get0_group(ec_key);
    EC_POINT* pub_key_point = EC_POINT_new(group);
    if (!EC_POINT_mul(group, pub_key_point, priv_key_bn, NULL, NULL, NULL)) {
        fprintf(stderr, "Error: Failed to derive public key.\n");
        BN_free(priv_key_bn);
        EC_POINT_free(pub_key_point);
        EC_KEY_free(ec_key);
        return NULL;
    }
    EC_KEY_set_public_key(ec_key, pub_key_point);
    EC_POINT_free(pub_key_point);


    // 3. Sign the message hash (DER format is the default for ECDSA_sign)
    unsigned int sig_len = ECDSA_size(ec_key);
    unsigned char* signature = malloc(sig_len);
    if (signature == NULL) {
        fprintf(stderr, "Error: Failed to allocate memory for signature.\n");
        BN_free(priv_key_bn);
        EC_KEY_free(ec_key);
        return NULL;
    }

    if (ECDSA_sign(0, message_hash, SHA256_DIGEST_LENGTH, signature, &sig_len, ec_key) != 1) {
        fprintf(stderr, "Error: Failed to sign message hash.\n");
        free(signature);
        BN_free(priv_key_bn);
        EC_KEY_free(ec_key);
        return NULL;
    }

    // 4. Convert the signature to a hex string
    hex_signature = bytes_to_hex_string(signature, sig_len);

    // Cleanup
    free(signature);
    BN_free(priv_key_bn);
    EC_KEY_free(ec_key);

    return hex_signature;
}

int main() {
    // Test the function
    const char* private_key_hex = "e331b6d69882b4cb4ea581d88e0b604039a3de5967688d3dcffdd2270c0fd109";
    const char* message = "Hello";

    // Convert hex private key to bytes
    size_t private_key_len;
    unsigned char* private_key_bytes = hex_string_to_bytes(private_key_hex, &private_key_len);
    if (private_key_bytes == NULL) {
        return 1;
    }

    // Sign the message
    char* signature = sign_message(message, private_key_bytes, private_key_len);

    if (signature) {
        printf("%s\n", signature);
        // The signature produced should be:
        // 3045022100f25a958c2236192f453c43e15e15dc384a2b02fa48e82f71348b2e5504e0532202200514fed431bffb41a56b0a0d602885581d5d385444343f32365a1a1d69c67507
        free(signature);
    }

    // Clean up allocated memory
    free(private_key_bytes);

    return 0;
}