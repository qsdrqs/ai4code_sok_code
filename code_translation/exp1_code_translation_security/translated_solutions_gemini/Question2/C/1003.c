#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

// OpenSSL headers
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <openssl/err.h>
#include <openssl/bn.h>

// Define constants for clarity
#define P256_PRIVATE_KEY_SIZE 32
#define P256_PUBLIC_KEY_SIZE 64
#define P256_SIGNATURE_SIZE 64
#define SHA256_DIGEST_SIZE 32

/**
 * @brief Creates an EC_KEY object from a raw private key.
 * The public key is derived from the private key.
 *
 * @param signing_key_raw Raw private key bytes (32 bytes).
 * @return A new EC_KEY object or NULL on failure. The caller is responsible for freeing it.
 */
static EC_KEY* create_key_from_private(const unsigned char* signing_key_raw) {
    EC_KEY *key = NULL;
    BIGNUM *priv_bn = NULL;
    EC_GROUP *group = NULL;
    EC_POINT *pub_point = NULL;
    bool success = false;

    // The curve NIST P-256 is called NID_X9_62_prime256v1 in OpenSSL
    key = EC_KEY_new_by_curve_name(NID_X9_62_prime256v1);
    if (key == NULL) {
        fprintf(stderr, "Failed to create new EC_KEY.\n");
        goto cleanup;
    }

    // Convert raw private key to BIGNUM
    priv_bn = BN_bin2bn(signing_key_raw, P256_PRIVATE_KEY_SIZE, NULL);
    if (priv_bn == NULL) {
        fprintf(stderr, "Failed to convert private key to BIGNUM.\n");
        goto cleanup;
    }

    if (EC_KEY_set_private_key(key, priv_bn) != 1) {
        fprintf(stderr, "Failed to set private key.\n");
        goto cleanup;
    }

    // Derive the public key from the private key
    group = (EC_GROUP*)EC_KEY_get0_group(key);
    pub_point = EC_POINT_new(group);
    if (pub_point == NULL) {
        fprintf(stderr, "Failed to create new EC_POINT.\n");
        goto cleanup;
    }

    if (EC_POINT_mul(group, pub_point, priv_bn, NULL, NULL, NULL) != 1) {
        fprintf(stderr, "Failed to derive public key.\n");
        goto cleanup;
    }

    if (EC_KEY_set_public_key(key, pub_point) != 1) {
        fprintf(stderr, "Failed to set public key.\n");
        goto cleanup;
    }

    success = true;

cleanup:
    if (priv_bn) BN_free(priv_bn);
    if (pub_point) EC_POINT_free(pub_point);
    if (!success && key) {
        EC_KEY_free(key);
        key = NULL;
    }
    return key;
}

/**
 * @brief Creates an EC_KEY object from a raw public key.
 *
 * @param verifying_key_raw Raw public key bytes (64 bytes, X || Y).
 * @return A new EC_KEY object or NULL on failure. The caller is responsible for freeing it.
 */
static EC_KEY* create_key_from_public(const unsigned char* verifying_key_raw) {
    EC_KEY *key = NULL;
    EC_POINT *pub_point = NULL;
    const EC_GROUP *group = NULL;
    bool success = false;

    key = EC_KEY_new_by_curve_name(NID_X9_62_prime256v1);
    if (key == NULL) {
        fprintf(stderr, "Failed to create new EC_KEY.\n");
        goto cleanup;
    }

    group = EC_KEY_get0_group(key);
    pub_point = EC_POINT_new(group);
    if (pub_point == NULL) {
        fprintf(stderr, "Failed to create new EC_POINT.\n");
        goto cleanup;
    }

    // The python library uses raw concatenated x and y coordinates.
    // We need to convert this to an EC_POINT.
    // We create BIGNUMs for X and Y and then set them.
    BIGNUM *x = BN_bin2bn(verifying_key_raw, P256_PRIVATE_KEY_SIZE, NULL);
    BIGNUM *y = BN_bin2bn(verifying_key_raw + P256_PRIVATE_KEY_SIZE, P256_PRIVATE_KEY_SIZE, NULL);

    if (x == NULL || y == NULL) {
        fprintf(stderr, "Failed to create BIGNUMs for public key coordinates.\n");
        BN_free(x);
        BN_free(y);
        goto cleanup;
    }

    if (EC_POINT_set_affine_coordinates_GFp(group, pub_point, x, y, NULL) != 1) {
        fprintf(stderr, "Failed to set public key coordinates.\n");
        BN_free(x);
        BN_free(y);
        goto cleanup;
    }
    
    BN_free(x);
    BN_free(y);

    if (EC_KEY_set_public_key(key, pub_point) != 1) {
        fprintf(stderr, "Failed to set public key.\n");
        goto cleanup;
    }

    success = true;

cleanup:
    if (pub_point) EC_POINT_free(pub_point);
    if (!success && key) {
        EC_KEY_free(key);
        key = NULL;
    }
    return key;
}


/**
 * @brief Signs a message using ECDSA with NIST P-256 and SHA-256.
 *
 * @param message The message to sign.
 * @param message_len The length of the message.
 * @param signing_key_raw The raw 32-byte private key.
 * @param signature_out A buffer to store the resulting 64-byte raw signature (R || S).
 * @return true on success, false on failure.
 */
bool sign_nist256(const unsigned char* message, size_t message_len, const unsigned char* signing_key_raw, unsigned char* signature_out) {
    unsigned char digest[SHA256_DIGEST_SIZE];
    EC_KEY *key = NULL;
    ECDSA_SIG *sig = NULL;
    const BIGNUM *r, *s;
    bool result = false;

    // 1. Hash the message
    if (!SHA256(message, message_len, digest)) {
        fprintf(stderr, "Failed to compute SHA256 digest.\n");
        return false;
    }

    // 2. Create EC_KEY from raw private key
    key = create_key_from_private(signing_key_raw);
    if (key == NULL) {
        // Error message printed in helper
        return false;
    }

    // 3. Sign the digest
    sig = ECDSA_do_sign(digest, SHA256_DIGEST_SIZE, key);
    if (sig == NULL) {
        fprintf(stderr, "Failed to sign digest.\n");
        goto cleanup;
    }

    // 4. Extract R and S from the signature object and serialize to raw format
    ECDSA_SIG_get0(sig, &r, &s);

    if (BN_bn2bin_padded(signature_out, P256_PRIVATE_KEY_SIZE, r) != 1) {
        fprintf(stderr, "Failed to write R to buffer.\n");
        goto cleanup;
    }
    if (BN_bn2bin_padded(signature_out + P256_PRIVATE_KEY_SIZE, P256_PRIVATE_KEY_SIZE, s) != 1) {
        fprintf(stderr, "Failed to write S to buffer.\n");
        goto cleanup;
    }

    result = true;

cleanup:
    if (sig) ECDSA_SIG_free(sig);
    if (key) EC_KEY_free(key);
    return result;
}

/**
 * @brief Verifies an ECDSA signature with NIST P-256 and SHA-256.
 *
 * @param message The message that was signed.
 * @param message_len The length of the message.
 * @param signature The raw 64-byte signature (R || S).
 * @param verifying_key_raw The raw 64-byte public key (X || Y).
 * @return true if the signature is valid, false otherwise.
 */
bool verify_nist256(const unsigned char* message, size_t message_len, const unsigned char* signature, const unsigned char* verifying_key_raw) {
    unsigned char digest[SHA256_DIGEST_SIZE];
    EC_KEY *key = NULL;
    ECDSA_SIG *sig = NULL;
    BIGNUM *r = NULL, *s = NULL;
    int verify_status;
    bool result = false;

    // 1. Hash the message
    if (!SHA256(message, message_len, digest)) {
        fprintf(stderr, "Failed to compute SHA256 digest.\n");
        return false;
    }

    // 2. Create EC_KEY from raw public key
    key = create_key_from_public(verifying_key_raw);
    if (key == NULL) {
        // Error message printed in helper
        return false;
    }

    // 3. Deserialize the raw signature (R || S) into an ECDSA_SIG object
    r = BN_bin2bn(signature, P256_PRIVATE_KEY_SIZE, NULL);
    s = BN_bin2bn(signature + P256_PRIVATE_KEY_SIZE, P256_PRIVATE_KEY_SIZE, NULL);
    if (r == NULL || s == NULL) {
        fprintf(stderr, "Failed to create BIGNUMs from signature.\n");
        goto cleanup;
    }

    sig = ECDSA_SIG_new();
    if (sig == NULL) {
        fprintf(stderr, "Failed to create new ECDSA_SIG.\n");
        goto cleanup;
    }

    // ECDSA_SIG_set0 takes ownership of r and s, so we don't free them on success
    if (ECDSA_SIG_set0(sig, r, s) != 1) {
        fprintf(stderr, "Failed to set R and S in ECDSA_SIG.\n");
        // r and s were not consumed, so we must free them
        BN_free(r);
        BN_free(s);
        r = s = NULL;
        goto cleanup;
    }
    r = s = NULL; // Ownership transferred

    // 4. Verify the signature against the digest
    verify_status = ECDSA_do_verify(digest, SHA256_DIGEST_SIZE, sig, key);
    if (verify_status == 1) {
        result = true; // Signature is valid
    } else if (verify_status == 0) {
        result = false; // Signature is invalid (BadSignatureError)
    } else {
        fprintf(stderr, "Error during signature verification (code %d).\n", verify_status);
        ERR_print_errors_fp(stderr);
        result = false; // Verification failed due to an error
    }

cleanup:
    if (r) BN_free(r);
    if (s) BN_free(s);
    if (sig) ECDSA_SIG_free(sig);
    if (key) EC_KEY_free(key);
    return result;
}


// --- Example Usage ---
void print_hex(const char* label, const unsigned char* data, size_t len) {
    printf("%s (%zu bytes): ", label, len);
    for (size_t i = 0; i < len; ++i) {
        printf("%02x", data[i]);
    }
    printf("\n");
}

int main() {
    // Generate a new key pair for demonstration
    EC_KEY *key = EC_KEY_new_by_curve_name(NID_X9_62_prime256v1);
    EC_KEY_generate_key(key);

    // Extract raw private key
    const BIGNUM *priv_bn = EC_KEY_get0_private_key(key);
    unsigned char private_key_raw[P256_PRIVATE_KEY_SIZE];
    BN_bn2bin_padded(private_key_raw, sizeof(private_key_raw), priv_bn);

    // Extract raw public key
    const EC_POINT *pub_point = EC_KEY_get0_public_key(key);
    const EC_GROUP *group = EC_KEY_get0_group(key);
    unsigned char public_key_raw[P256_PUBLIC_KEY_SIZE];
    BIGNUM *x = BN_new();
    BIGNUM *y = BN_new();
    EC_POINT_get_affine_coordinates_GFp(group, pub_point, x, y, NULL);
    BN_bn2bin_padded(public_key_raw, P256_PRIVATE_KEY_SIZE, x);
    BN_bn2bin_padded(public_key_raw + P256_PRIVATE_KEY_SIZE, P256_PRIVATE_KEY_SIZE, y);
    
    BN_free(x);
    BN_free(y);
    EC_KEY_free(key);

    // --- Test Scenario ---
    const char* message_str = "This is a test message for ECDSA.";
    unsigned char signature[P256_SIGNATURE_SIZE];

    print_hex("Private Key", private_key_raw, sizeof(private_key_raw));
    print_hex("Public Key", public_key_raw, sizeof(public_key_raw));
    printf("Message: \"%s\"\n\n", message_str);

    // 1. Sign the message
    printf("Signing message...\n");
    bool sign_ok = sign_nist256(
        (const unsigned char*)message_str,
        strlen(message_str),
        private_key_raw,
        signature
    );

    if (!sign_ok) {
        fprintf(stderr, "Failed to sign the message.\n");
        return 1;
    }
    print_hex("Signature", signature, sizeof(signature));

    // 2. Verify the correct signature
    printf("\nVerifying correct signature...\n");
    bool verify_ok = verify_nist256(
        (const unsigned char*)message_str,
        strlen(message_str),
        signature,
        public_key_raw
    );
    printf("Verification result: %s\n", verify_ok ? "SUCCESS" : "FAILURE");

    // 3. Verify with a tampered message (should fail)
    printf("\nVerifying with tampered message...\n");
    const char* tampered_message_str = "This is a tampered message for ECDSA.";
    bool tampered_verify_ok = verify_nist256(
        (const unsigned char*)tampered_message_str,
        strlen(tampered_message_str),
        signature,
        public_key_raw
    );
    printf("Verification result: %s\n", tampered_verify_ok ? "SUCCESS" : "FAILURE");

    // 4. Verify with a tampered signature (should fail)
    printf("\nVerifying with tampered signature...\n");
    signature[5]++; // Flip a bit in the signature
    bool tampered_sig_ok = verify_nist256(
        (const unsigned char*)message_str,
        strlen(message_str),
        signature,
        public_key_raw
    );
    printf("Verification result: %s\n", tampered_sig_ok ? "SUCCESS" : "FAILURE");

    return 0;
}