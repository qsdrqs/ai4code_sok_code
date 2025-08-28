#include <openssl/ecdsa.h>
#include <openssl/ec.h>
#include <openssl/obj_mac.h>
#include <stdlib.h>

/**
 * Signs a message using an ECDSA private key.
 *
 * @param key        ECDSA private key (EC_KEY with private part)
 * @param message    Pointer to the message (precomputed hash)
 * @param message_len Length of the message in bytes
 * @param sig_len    Output: Length of the resulting signature
 * @return           Pointer to the DER-encoded signature (must be freed by caller), or NULL on failure
 */
unsigned char *sign(EC_KEY *key, const unsigned char *message, int message_len, int *sig_len) {
    int sig_size = ECDSA_size(key);
    unsigned char *signature = (unsigned char *)malloc(sig_size);
    if (!signature) {
        return NULL; // Memory allocation failed
    }

    unsigned int tmp_sig_len;
    if (ECDSA_sign(NID_undef, message, message_len, signature, &tmp_sig_len, key) != 1) {
        free(signature);
        return NULL;
    }

    *sig_len = tmp_sig_len;
    return signature;
}

/**
 * Verifies a DER-encoded ECDSA signature.
 *
 * @param key        ECDSA public key (EC_KEY with public part)
 * @param message    Pointer to the message (precomputed hash)
 * @param message_len Length of the message in bytes
 * @param signature  Pointer to the DER-encoded signature
 * @param sig_len    Length of the signature in bytes
 * @return           1 if the signature is valid, 0 otherwise
 */
int verify(EC_KEY *key, const unsigned char *message, int message_len, const unsigned char *signature, int sig_len) {
    int result = ECDSA_verify(NID_undef, message, message_len, signature, sig_len, key);
    return (result == 1);
}