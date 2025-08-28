#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ecdsa.h>
#include <openssl/sha.h>
#include <openssl/obj_mac.h>

/**
 * Signs a given message using a given ECDSA signing key.
 *
 * @param message The message to be signed.
 * @param message_len The length of the message.
 * @param signing_key The ECDSA signing key.
 * @param signing_key_len The length of the signing key.
 * @param signature A pointer to store the signature.
 * @param signature_len A pointer to store the length of the signature.
 *
 * @return 1 on success, 0 on failure.
 */
int sign_message(const unsigned char *message, size_t message_len,
                 const unsigned char *signing_key, size_t signing_key_len,
                 unsigned char **signature, size_t *signature_len) {
    // Create an ECDSA context
    ECDSA *ecdsa_ctx = NULL;
    const EC_METHOD *ec_method = EC_METHOD_for_nistp256();
    if ((ecdsa_ctx = ECDSA_new(ec_method)) == NULL) {
        return 0;
    }

    // Load the signing key
    const unsigned char *key_ptr = signing_key;
    EC_KEY *ec_key = d2i_EC_KEY(NULL, &key_ptr, signing_key_len);
    if (ec_key == NULL) {
        ECDSA_free(ecdsa_ctx);
        return 0;
    }

    // Set the signing key for the ECDSA context
    if (ECDSA_set_key(ecdsa_ctx, ec_key) != 1) {
        ECDSA_free(ecdsa_ctx);
        EC_KEY_free(ec_key);
        return 0;
    }

    // Hash the message
    unsigned char message_hash[SHA256_DIGEST_LENGTH];
    SHA256_CTX sha256_ctx;
    SHA256_Init(&sha256_ctx);
    SHA256_Update(&sha256_ctx, message, message_len);
    SHA256_Final(message_hash, &sha256_ctx);

    // Sign the message hash
    unsigned char *sig = NULL;
    ECDSA_sign(0, message_hash, SHA256_DIGEST_LENGTH, &sig, signature_len, ecdsa_ctx);

    // Store the signature
    *signature = sig;

    // Clean up
    ECDSA_free(ecdsa_ctx);
    EC_KEY_free(ec_key);

    return 1;
}

int main() {
    // Example usage
    const unsigned char *message = (const unsigned char *)"Hello, World!";
    size_t message_len = strlen((const char *)message);

    // Generate or load an ECDSA signing key (example: NIST P-256)
    EC_KEY *ec_key = EC_KEY_new_by_curve_name(NID_secp256r1);
    if (ec_key == NULL) {
        return 1;
    }

    // Set a private key (example: randomly generated)
    BIGNUM *private_key = BN_new();
    BN_rand(private_key, 256, BN_RAND_TOP_TWO, BN_RAND_NO_CONST, NULL);
    EC_KEY_set_private_key(ec_key, private_key);
    BN_free(private_key);

    // Serialize the signing key
    unsigned char *signing_key = NULL;
    size_t signing_key_len = 0;
    unsigned char *key_ptr = NULL;
    signing_key_len = i2d_EC_KEY(ec_key, &key_ptr);
    signing_key = malloc(signing_key_len);
    memcpy(signing_key, key_ptr, signing_key_len);
    OPENSSL_free(key_ptr);

    // Sign the message
    unsigned char *signature = NULL;
    size_t signature_len = 0;
    if (sign_message(message, message_len, signing_key, signing_key_len, &signature, &signature_len)) {
        printf("Signature: ");
        for (size_t i = 0; i < signature_len; i++) {
            printf("%02x", signature[i]);
        }
        printf("\n");
    } else {
        printf("Failed to sign the message.\n");
    }

    // Clean up
    free(signing_key);
    free(signature);
    EC_KEY_free(ec_key);

    return 0;
}