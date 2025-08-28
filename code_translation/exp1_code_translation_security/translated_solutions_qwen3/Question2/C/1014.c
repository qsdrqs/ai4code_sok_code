#include <openssl/ec.h>     // For ECDSA_sign and EC_KEY
#include <openssl/sha.h>    // For SHA256
#include <openssl/err.h>    // For error handling (optional but recommended)
#include <openssl/evp.h>    // For EVP_MD_CTX (if needed for future extensions)

/**
 * Signs a given message using a given ECDSA private key.
 *
 * @param message     Pointer to the message data to be signed.
 * @param message_len Length of the message in bytes.
 * @param signing_key EC_KEY object containing the private key.
 * @param sig_len     Pointer to an unsigned int that will receive the length of the signature.
 *
 * @return A pointer to the DER-encoded signature buffer (must be freed with OPENSSL_free),
 *         or NULL on failure.
 */
unsigned char* sign_message(const unsigned char* message, size_t message_len, EC_KEY* signing_key, unsigned int* sig_len) {
    // Step 1: Compute SHA-256 hash of the message
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256(message, message_len, hash);

    // Step 2: First call to ECDSA_sign to determine the required signature length
    if (ECDSA_sign(0, hash, SHA256_DIGEST_LENGTH, NULL, sig_len, signing_key) != 1) {
        // Failed to determine signature length
        return NULL;
    }

    // Step 3: Allocate memory for the signature buffer
    unsigned char* signature = OPENSSL_malloc(*sig_len);
    if (!signature) {
        // Memory allocation failed
        return NULL;
    }

    // Step 4: Second call to ECDSA_sign to actually generate the signature
    if (ECDSA_sign(0, hash, SHA256_DIGEST_LENGTH, signature, sig_len, signing_key) != 1) {
        // Signature generation failed
        OPENSSL_free(signature);
        return NULL;
    }

    // Step 5: Return the DER-encoded signature buffer
    return signature;
}