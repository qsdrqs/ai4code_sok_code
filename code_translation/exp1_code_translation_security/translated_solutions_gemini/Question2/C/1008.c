#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// OpenSSL headers
#include <openssl/ec.h>      // for EC_KEY, EC_GROUP, etc.
#include <openssl/ecdsa.h>   // for ECDSA_sign, ECDSA_SIG, etc.
#include <openssl/obj_mac.h> // for NID_secp256k1
#include <openssl/sha.h>     // for SHA256
#include <openssl/bn.h>      // for BIGNUM

/**
 * @brief Signs a message using a raw private key with ECDSA (SECP256k1).
 *
 * @param message The message string to sign.
 * @param private_key_bytes A 32-byte array representing the private key.
 * @param signature_buf A buffer to store the resulting 64-byte raw signature (r || s).
 * @return Returns 1 on success, 0 on failure.
 */
int sign_message(const char *message, const unsigned char *private_key_bytes, unsigned char *signature_buf) {
    int result = 0;

    // 1. Create an EC_KEY object from the private key.
    // =================================================
    EC_KEY *eckey = NULL;
    BIGNUM *priv_key_bn = NULL;
    const EC_GROUP *group = NULL;
    EC_POINT *pub_key_point = NULL;

    // Create a new EC_KEY object for the SECP256k1 curve.
    eckey = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (eckey == NULL) {
        fprintf(stderr, "Error: Failed to create new EC_KEY.\n");
        goto cleanup;
    }

    // Convert the raw private key bytes to a BIGNUM.
    priv_key_bn = BN_bin2bn(private_key_bytes, 32, NULL);
    if (priv_key_bn == NULL) {
        fprintf(stderr, "Error: Failed to convert private key to BIGNUM.\n");
        goto cleanup;
    }

    // Set the private key for the EC_KEY object.
    if (EC_KEY_set_private_key(eckey, priv_key_bn) != 1) {
        fprintf(stderr, "Error: Failed to set private key.\n");
        goto cleanup;
    }

    // Derive the public key from the private key. This is required for signing.
    group = EC_KEY_get0_group(eckey);
    pub_key_point = EC_POINT_new(group);
    if (pub_key_point == NULL) {
        fprintf(stderr, "Error: Failed to create new EC_POINT for public key.\n");
        goto cleanup;
    }
    if (EC_POINT_mul(group, pub_key_point, priv_key_bn, NULL, NULL, NULL) != 1) {
        fprintf(stderr, "Error: Failed to derive public key.\n");
        goto cleanup;
    }
    if (EC_KEY_set_public_key(eckey, pub_key_point) != 1) {
        fprintf(stderr, "Error: Failed to set public key.\n");
        goto cleanup;
    }


    // 2. Hash the message.
    // =================================================
    unsigned char msg_hash[SHA256_DIGEST_LENGTH];
    SHA256((const unsigned char *)message, strlen(message), msg_hash);


    // 3. Sign the hash.
    // =================================================
    ECDSA_SIG *signature = ECDSA_do_sign(msg_hash, SHA256_DIGEST_LENGTH, eckey);
    if (signature == NULL) {
        fprintf(stderr, "Error: Failed to sign the message hash.\n");
        goto cleanup;
    }


    // 4. Convert the signature to raw (r || s) format.
    // =================================================
    const BIGNUM *r, *s;
    ECDSA_SIG_get0(&r, &s, signature);

    // BN_bn2bin_padded ensures the output is 32 bytes, left-padding with zeros if needed.
    if (BN_bn2bin_padded(signature_buf, 32, r) != 1) {
        fprintf(stderr, "Error: Failed to serialize 'r' component of signature.\n");
        ECDSA_SIG_free(signature); // Free signature before goto
        goto cleanup;
    }
    if (BN_bn2bin_padded(signature_buf + 32, 32, s) != 1) {
        fprintf(stderr, "Error: Failed to serialize 's' component of signature.\n");
        ECDSA_SIG_free(signature); // Free signature before goto
        goto cleanup;
    }

    // Success!
    result = 1;
    ECDSA_SIG_free(signature);


cleanup:
    // Free all allocated objects.
    if (pub_key_point) EC_POINT_free(pub_key_point);
    if (priv_key_bn) BN_free(priv_key_bn);
    if (eckey) EC_KEY_free(eckey);

    return result;
}

int main() {
    // The same message and private key from the Python example.
    const char *message = "hello world";
    // The private key must be exactly 32 bytes.
    const unsigned char private_key[] = "12345678901234567890123456789012";

    // Buffer to hold the 64-byte signature.
    unsigned char signature[64];

    printf("Signing message: \"%s\"\n", message);
    printf("Using private key: \"%s\"\n", private_key);

    if (sign_message(message, private_key, signature)) {
        printf("Signature (64 bytes, hex):\n");
        for (int i = 0; i < 64; i++) {
            printf("%02x", signature[i]);
        }
        printf("\n");
    } else {
        printf("Failed to sign the message.\n");
        return 1; // Indicate error
    }

    return 0;
}