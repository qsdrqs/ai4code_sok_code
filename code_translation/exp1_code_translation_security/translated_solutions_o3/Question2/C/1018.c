/*
 *  sign.c
 *
 *  C translation of the Python snippet:
 *
 *      import ecdsa
 *      def sign(msg, key):
 *          sig = key.sign(b"message")
 *          return sig
 *
 *  All external ECDSA/crypto helpers are assumed to be available in the
 *  build environment as requested.
 */

#include <stdlib.h>
#include <string.h>

/* The hosting project is expected to provide these headers / APIs. */
#include "ecdsa.h"          /* generic ECDSA helpers                */
#include "sha256.h"         /* or any hash the library requires      */

/*
 * Sign the fixed string "message" with the supplied private key.
 *
 * Parameters
 * ----------
 *   msg      : (unused) kept only to match the Python signature
 *   key      : pointer to an already-initialised private key object
 *   sig_len  : (output) length of the generated signature
 *
 * Returns
 * -------
 *   A freshly-allocated buffer holding the signature on success,
 *   or NULL on failure.  Caller must free() the returned buffer.
 */
unsigned char *sign(const char *msg /* unused */, ecdsa_key *key,
                    size_t *sig_len)
{
    (void)msg;                 /* suppress unused-parameter warning */

    /* 1.  Hash the constant message. */
    static const char payload[] = "message";
    uint8_t digest[SHA256_DIGEST_LENGTH];

    sha256((const uint8_t *)payload, sizeof(payload) - 1, digest);

    /* 2.  Allocate a buffer large enough to hold the signature. */
    size_t needed = ecdsa_signature_size(key);   /* helper provided by lib */
    unsigned char *sig = (unsigned char *)malloc(needed);
    if (!sig) {
        return NULL;
    }

    /* 3.  Produce the signature.  Convention: returns 0 on success. */
    if (ecdsa_sign(key, digest, sizeof(digest), sig, sig_len) != 0) {
        free(sig);
        return NULL;
    }

    return sig;   /* caller is responsible for free()-ing this buffer */
}