#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/bn.h>
#include <openssl/err.h>
#include <stdlib.h>

/**
 * Signs a message using an ECDSA private key.
 *
 * @param message     Pointer to the message data (assumed to be a hash digest).
 * @param message_len Length of the message data.
 * @param signing_key EC_KEY object containing the ECDSA private key.
 * @param sig_len     Output parameter to store the length of the signature.
 *
 * @return A pointer to the DER-encoded ECDSA signature, or NULL on failure.
 *         The caller is responsible for freeing the returned buffer using OPENSSL_free().
 */
unsigned char* sign_message(const unsigned char* message, size_t message_len, EC_KEY* signing_key, size_t* sig_len) {
    if (!message || !signing_key || !sig_len) {
        return NULL;
    }

    ECDSA_SIG* sig = ECDSA_do_sign(message, message_len, signing_key);
    if (!sig) {
        return NULL;
    }

    int der_size = i2d_ECDSA_SIG(sig, NULL);
    if (der_size <= 0) {
        ECDSA_SIG_free(sig);
        return NULL;
    }

    unsigned char* der_sig = OPENSSL_malloc(der_size);
    if (!der_sig) {
        ECDSA_SIG_free(sig);
        return NULL;
    }

    unsigned char* p = der_sig;
    int len = i2d_ECDSA_SIG(sig, &p);
    ECDSA_SIG_free(sig);

    if (len <= 0) {
        OPENSSL_free(der_sig);
        return NULL;
    }

    *sig_len = (size_t)len;
    return der_sig;
}