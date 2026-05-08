#include <sodium.h>
#include <stdlib.h>

/*
 * Sign a message using the provided signing key.
 *
 * The returned buffer contains the detached signature concatenated with the
 * original message (signature || message), mirroring the behavior of PyNaCl's
 * SigningKey.sign() which returns a SignedMessage.
 *
 * Parameters:
 *   message             - the message bytes to sign
 *   message_len         - length of the message
 *   signing_key         - the ed25519 secret key (crypto_sign_SECRETKEYBYTES bytes)
 *   signed_message_len  - output parameter for the signed message length
 *
 * Returns: newly allocated buffer containing the signed message, or NULL on
 *          failure. The caller is responsible for freeing the returned buffer.
 */
unsigned char *sign_message(const unsigned char *message,
                            unsigned long long message_len,
                            const unsigned char *signing_key,
                            unsigned long long *signed_message_len) {
    unsigned char *signed_message = malloc(crypto_sign_BYTES + message_len);
    if (signed_message == NULL) {
        return NULL;
    }

    if (crypto_sign(signed_message, signed_message_len,
                    message, message_len, signing_key) != 0) {
        free(signed_message);
        return NULL;
    }

    return signed_message;
}