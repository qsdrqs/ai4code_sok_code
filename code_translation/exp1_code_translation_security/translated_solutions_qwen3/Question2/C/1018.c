#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/sha.h>
#include <stdlib.h>
#include <string.h>

/**
 * Signs the fixed string "message" using the provided EC private key.
 *
 * @param msg   Unused parameter (as in the original Python code).
 * @param key   EC private key to use for signing.
 * @param sig_len Pointer to store the length of the returned signature.
 * @return      Dynamically allocated buffer containing the DER-encoded signature,
 *              or NULL on failure. The caller must free this buffer.
 */
unsigned char *sign(const char *msg, EC_KEY *key, size_t *sig_len) {
    // Silence unused parameter warning
    (void)msg;

    // Compute SHA-1 hash of the string "message"
    unsigned char hash[SHA_DIGEST_LENGTH];
    SHA1((const unsigned char *)"message", 7, hash);  // "message" is 7 bytes

    // Determine the required buffer size for the signature
    size_t buffer_len = ECDSA_size(key);
    if (buffer_len <= 0) {
        return NULL;
    }

    // Allocate buffer for the signature
    unsigned char *sig = malloc(buffer_len);
    if (!sig) {
        return NULL;
    }

    // Sign the hash
    unsigned int temp_sig_len;
    if (ECDSA_sign(0, hash, SHA_DIGEST_LENGTH, sig, &temp_sig_len, key) != 1) {
        free(sig);
        return NULL;
    }

    // Return the signature and its length
    *sig_len = temp_sig_len;
    return sig;
}