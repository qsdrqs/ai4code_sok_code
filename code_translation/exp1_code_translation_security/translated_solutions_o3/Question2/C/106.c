/* ecdsa_demo.c  –  translation of the Python code                 */
/* --------------------------------------------------------------- */
/* Dependencies: OpenSSL (libcrypto)                               */
/* --------------------------------------------------------------- */

#include <stdio.h>
#include <string.h>
#include <openssl/obj_mac.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/err.h>

/*
 * Signs a given message using a given EC private key
 *
 * Args:
 *     msg     : pointer to the message bytes
 *     msg_len : length of the message
 *     key     : EC_KEY containing the private part
 *     sig_len : receives the produced signature length
 *
 * Return:
 *     pointer to the DER-encoded signature (must be OPENSSL_free’d)
 *     or NULL on error
 */
static unsigned char *sign_message(const unsigned char *msg,
                                   size_t              msg_len,
                                   EC_KEY             *key,
                                   unsigned int       *sig_len)
{
    /* Allocate a buffer large enough for the signature */
    unsigned char *sig = OPENSSL_malloc(ECDSA_size(key));
    if (!sig) return NULL;

    if (ECDSA_sign(0, msg, (int)msg_len, sig, sig_len, key) != 1) {
        OPENSSL_free(sig);
        return NULL;
    }
    return sig;
}

int main(void)
{
    /* ------------------------------------------------------------------ */
    /* 1. Generate an EC key pair on the secp256k1 curve                   */
    /* ------------------------------------------------------------------ */
    EC_KEY *sk = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!sk) {
        fprintf(stderr, "EC_KEY_new_by_curve_name failed\n");
        return 1;
    }
    if (EC_KEY_generate_key(sk) != 1) {
        fprintf(stderr, "EC_KEY_generate_key failed\n");
        EC_KEY_free(sk);
        return 1;
    }

    /* ------------------------------------------------------------------ */
    /* 2. Sign the message "helloworld"                                    */
    /* ------------------------------------------------------------------ */
    const char *message = "helloworld";
    size_t      msg_len = strlen(message);

    unsigned int  sig_len = 0;
    unsigned char *signature =
        sign_message((const unsigned char *)message, msg_len, sk, &sig_len);

    if (!signature) {
        fprintf(stderr, "Signing failed\n");
        EC_KEY_free(sk);
        return 1;
    }

    /* ------------------------------------------------------------------ */
    /* 3. Verify the signature                                             */
    /*    (We use the same EC_KEY; only the public part is required.)      */
    /* ------------------------------------------------------------------ */
    int ok = ECDSA_verify(0,
                          (const unsigned char *)message,
                          (int)msg_len,
                          signature,
                          sig_len,
                          sk);

    printf("%s\n", ok == 1 ? "True" : "False");

    /* ------------------------------------------------------------------ */
    /*               Clean-up                                             */
    /* ------------------------------------------------------------------ */
    OPENSSL_free(signature);
    EC_KEY_free(sk);
    return 0;
}