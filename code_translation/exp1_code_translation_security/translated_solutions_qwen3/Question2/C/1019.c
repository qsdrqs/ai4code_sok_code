#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <openssl/err.h>
#include <stdlib.h>

/**
 * Signs a byte string using ECDSA with SHA-256.
 *
 * @param private_key  A valid EC private key (must not be NULL).
 * @param data         The data to be signed (must not be NULL).
 * @param data_len     Length of the data in bytes.
 * @param sig_len      Output parameter: length of the returned signature.
 *
 * @return A pointer to the DER-encoded ECDSA signature (must be freed with OPENSSL_free),
 *         or NULL on failure.
 */
unsigned char* sign_string(EC_KEY *private_key, const unsigned char *data, size_t data_len, size_t *sig_len) {
    // Validate input parameters
    if (!private_key || !data || !sig_len) {
        return NULL;
    }

    // Ensure the EC key is valid and has a private component
    if (EC_KEY_check_key(private_key) <= 0) {
        return NULL;
    }

    // Compute SHA-256 digest of the input data
    unsigned char digest[SHA256_DIGEST_LENGTH];
    SHA256_CTX sha_ctx;

    if (!SHA256_Init(&sha_ctx)) {
        return NULL;
    }

    if (!SHA256_Update(&sha_ctx, data, data_len)) {
        return NULL;
    }

    if (!SHA256_Final(digest, &sha_ctx)) {
        return NULL;
    }

    // Determine the required buffer size for the signature
    size_t signature_len = ECDSA_size(private_key);
    unsigned char *signature = OPENSSL_malloc(signature_len);

    if (!signature) {
        return NULL;
    }

    // Sign the digest using ECDSA
    if (ECDSA_sign(0, digest, SHA256_DIGEST_LENGTH, signature, &signature_len, private_key) != 1) {
        OPENSSL_free(signature);
        return NULL;
    }

    // Return the signature and its length
    *sig_len = signature_len;
    return signature;
}