/* ecdsa_sign_verify.c
 *
 * One-file replacement for the Python snippet:
 *     sig  = sign(priv_key, message)
 *     good = verify(pub_key, message, sig)
 *
 * Compile (example):
 *     gcc -Wall -O2 ecdsa_sign_verify.c -o ecdsa_sign_verify \
 *         -lcrypto
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <openssl/ecdsa.h>   /* ECDSA_sign, ECDSA_verify, EC_KEY          */
#include <openssl/objects.h> /* NID_secp256k1 etc.                        */
#include <openssl/err.h>     /* For error printing                        */


/* ------------------------------------------------------------------ */
/*  sign()  –  exact analogue of the Python “sign(ECDSA, message)”    */
/* ------------------------------------------------------------------ */
/*
 * ecdsa      : pointer to an EC_KEY that *has* a private part.
 * msg / len  : data to be signed.
 * sig_len    : (output) number of bytes written to *sig.
 *
 * returns    : malloc’ed buffer containing the DER-encoded signature,
 *              or NULL on failure.  Caller must OPENSSL_free(sig).
 */
unsigned char *sign_message(EC_KEY        *ecdsa,
                            const void    *msg,
                            size_t         len,
                            unsigned int  *sig_len)
{
    unsigned char *sig = NULL;

    /* Allocate a buffer big enough for any ECDSA signature on this key. */
    int buf_len = ECDSA_size(ecdsa);
    sig = OPENSSL_malloc(buf_len);
    if (sig == NULL) {
        return NULL;
    }

    /* Do the actual signing.  ECDSA_sign returns 1 on success. */
    if (ECDSA_sign(/*type=*/0,
                   (const unsigned char *)msg, (int)len,
                   sig, sig_len, ecdsa) != 1)
    {
        OPENSSL_free(sig);
        return NULL;
    }
    return sig;   /* success – caller must free */
}


/* ------------------------------------------------------------------ */
/*  verify()  –  analogue of Python “verify(ECDSA, message, signature)”*/
/* ------------------------------------------------------------------ */
/*
 * ecdsa      : pointer to an EC_KEY containing the *public* parameters.
 * msg / len  : data whose signature we are checking.
 * sig / sLen : DER-encoded signature to be tested.
 *
 * returns    : 1 if signature valid, 0 if invalid, −1 on internal error.
 */
int verify_signature(EC_KEY              *ecdsa,
                     const void          *msg,
                     size_t               len,
                     const unsigned char *sig,
                     unsigned int         sig_len)
{
    /* ECDSA_verify returns:
     *   1 – valid signature
     *   0 – signature mismatch
     *  −1 – internal error
     */
    return ECDSA_verify(/*type=*/0,
                        (const unsigned char *)msg, (int)len,
                        sig, sig_len, ecdsa);
}


/* ------------------------------------------------------------------ */
/*  Tiny self-test / usage example                                    */
/* ------------------------------------------------------------------ */
#ifdef SELFTEST
int main(void)
{
    const char *msg = "hello, elliptic world!";
    unsigned int sig_len;
    unsigned char *sig = NULL;

    /* 1. Create a key pair for demonstration (secp256k1). */
    EC_KEY *key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!key || !EC_KEY_generate_key(key)) {
        fprintf(stderr, "Key generation failed\n");
        goto cleanup;
    }

    /* 2. Sign. */
    sig = sign_message(key, msg, strlen(msg), &sig_len);
    if (!sig) {
        fprintf(stderr, "Signing failed\n");
        goto cleanup;
    }
    printf("Signature (%u bytes) generated.\n", sig_len);

    /* 3. Verify with the public part of the same key. */
    int ok = verify_signature(key, msg, strlen(msg), sig, sig_len);
    printf("Verification returned: %d (1==valid)\n", ok);

cleanup:
    if (sig) OPENSSL_free(sig);
    if (key) EC_KEY_free(key);
    if (ok != 1) ERR_print_errors_fp(stderr);
    return (ok == 1) ? EXIT_SUCCESS : EXIT_FAILURE;
}
#endif /* SELFTEST */