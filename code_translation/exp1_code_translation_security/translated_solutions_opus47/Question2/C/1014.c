/**
 * Functions that signs a given message using a given ECDSA signing key
 */

#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/sha.h>
#include <stdlib.h>

/**
 * Signs a given message using a given ECDSA signing key.
 *
 * @param message       Pointer to the message bytes to sign
 * @param message_len   Length of the message in bytes
 * @param signing_key   The ECDSA signing key (EC_KEY*)
 * @param signature_len Output parameter: length of the returned signature
 * @return              The DER-encoded signature (caller must free() it),
 *                      or NULL on failure.
 */
unsigned char *sign_message(const unsigned char *message, size_t message_len,
                            EC_KEY *signing_key, unsigned int *signature_len)
{
    /* Hash the message using SHA-256 */
    unsigned char message_hash[SHA256_DIGEST_LENGTH];
    SHA256(message, message_len, message_hash);

    /* Allocate buffer large enough to hold the DER-encoded signature */
    unsigned int sig_max_size = ECDSA_size(signing_key);
    unsigned char *signature = (unsigned char *)malloc(sig_max_size);
    if (signature == NULL) {
        return NULL;
    }

    /* Sign the message hash. OpenSSL's ECDSA_sign() produces a DER-encoded
     * signature, which matches Python's ecdsa.util.sigencode_der. */
    if (ECDSA_sign(0, message_hash, SHA256_DIGEST_LENGTH,
                   signature, signature_len, signing_key) != 1) {
        free(signature);
        return NULL;
    }

    return signature;
}