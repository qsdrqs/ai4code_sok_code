#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <stddef.h>

/**
 * Function that signs a given message using an ECDSA signing key.
 *
 * @param ecdsa_key     ECDSA signing key
 * @param message       Message to be signed
 * @param message_len   Length of the message in bytes
 * @param signature     Output buffer for signature 
 *                      (must be pre-allocated with at least ECDSA_size(ecdsa_key) bytes)
 * @param signature_len Pointer to store the actual signature length
 * @return 1 on success, 0 on failure
 */
int sign(EC_KEY *ecdsa_key, const unsigned char *message, size_t message_len,
         unsigned char *signature, unsigned int *signature_len) {
    return ECDSA_sign(0, message, message_len, signature, signature_len, ecdsa_key);
}

/**
 * Function that verifies a given signature using an ECDSA verification key.
 *
 * @param ecdsa_key     ECDSA verification key
 * @param message       Message to be verified
 * @param message_len   Length of the message in bytes
 * @param signature     Signature to be verified
 * @param signature_len Length of the signature in bytes
 * @return 1 if signature is valid, 0 if invalid, -1 on error
 */
int verify(EC_KEY *ecdsa_key, const unsigned char *message, size_t message_len,
           const unsigned char *signature, unsigned int signature_len) {
    return ECDSA_verify(0, message, message_len, signature, signature_len, ecdsa_key);
}