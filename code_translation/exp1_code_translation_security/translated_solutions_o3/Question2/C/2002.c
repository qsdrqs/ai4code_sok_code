/**********************************************************************
 *  demo.c  —  rough C translation of the given Python/ecdsa sample
 *********************************************************************/
#include <stdio.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/err.h>

/* ------------------------------------------------------------------ */
/* Helper that signs a message.                                       */
/* On success returns a malloc’ed buffer holding the DER-encoded      */
/* signature; *sig_len receives its length.  Caller must free().      */
unsigned char *sign_message(EC_KEY *key,
                            const char *msg,
                            unsigned int *sig_len)
{
    unsigned char *sig;
    size_t msg_len = strlen(msg);

    /* Allocate a buffer large enough for any signature for this key.  */
    sig = OPENSSL_malloc(ECDSA_size(key));
    if (sig == NULL)
        return NULL;

    if (ECDSA_sign(0,                      /* unused type field        */
                   (const unsigned char *)msg, (int)msg_len,
                   sig, sig_len, key) != 1)
    {
        OPENSSL_free(sig);
        return NULL;
    }
    return sig;                            /* success                  */
}

/* ------------------------------------------------------------------ */
int main(void)
{
    /* 1. Generate a fresh key pair on curve prime256v1 (a.k.a. P-256) */
    EC_KEY *sk = EC_KEY_new_by_curve_name(NID_X9_62_prime256v1);
    if (!sk || EC_KEY_generate_key(sk) != 1)
    {
        fprintf(stderr, "Key generation failed\n");
        return 1;
    }

    /* 2. Sign “hello world”                                            */
    unsigned char *signature = NULL;
    unsigned int   sig_len   = 0;

    signature = sign_message(sk, "hello world", &sig_len);
    if (!signature)
    {
        fprintf(stderr, "Signing failed\n");
        EC_KEY_free(sk);
        return 1;
    }

    /* 3. Verify the signature over the TYPED message “hello worfld”    */
    const char *tampered = "hello worfld";
    int ok = ECDSA_verify(0,
                          (const unsigned char *)tampered,
                          (int)strlen(tampered),
                          signature, sig_len,
                          sk);             /* the public key is inside */

    printf("Verification result: %s\n", ok == 1 ? "true" : "false");

    /* 4. Clean up                                                      */
    OPENSSL_free(signature);
    EC_KEY_free(sk);

    /* Optional: show any crypto errors that might have happened        */
    if (ok != 1)
        ERR_print_errors_fp(stderr);

    return 0;
}