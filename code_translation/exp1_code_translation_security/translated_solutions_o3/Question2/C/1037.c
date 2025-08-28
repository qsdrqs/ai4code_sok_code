#include <stdio.h>
#include <string.h>

#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/err.h>
#include <openssl/crypto.h>

/* ------------------------------------------------------------------ */
/*  signMessage: replicate Python helper                               */
/* ------------------------------------------------------------------ */
int signMessage(EC_KEY      *key,
                const unsigned char *msg,
                size_t       msg_len,
                unsigned char **sig,      /* out: malloc’d with OPENSSL_malloc */
                unsigned int *sig_len)    /* out */
{
    if (!key || !msg || !sig || !sig_len)
        return 0;

    /* Maximum signature size for this key */
    *sig_len = (unsigned int)ECDSA_size(key);
    *sig     = OPENSSL_malloc(*sig_len);
    if (*sig == NULL)
        return 0;

    /* The 'type' parameter is 0 – we supply raw data just like python-ecdsa
       does when no hash function is specified.                          */
    if (!ECDSA_sign(0, msg, (int)msg_len, *sig, sig_len, key))
    {
        OPENSSL_free(*sig);
        *sig = NULL;
        *sig_len = 0;
        return 0;
    }
    return 1;
}

/* ------------------------------------------------------------------ */
int main(void)
{
    const char  *message = "testing";
    const size_t msg_len = strlen(message);

    /* 1) Generate an EC key on secp384r1 (NIST-384p) */
    EC_KEY *sk = EC_KEY_new_by_curve_name(NID_secp384r1);
    if (!sk || !EC_KEY_generate_key(sk))
    {
        fprintf(stderr, "Cannot create/generate EC key\n");
        EC_KEY_free(sk);
        return 1;
    }

    /* 2) Sign the message */
    unsigned char *signature = NULL;
    unsigned int   sig_len   = 0;

    if (!signMessage(sk,
                     (const unsigned char *)message, msg_len,
                     &signature, &sig_len))
    {
        fprintf(stderr, "Signing failed\n");
        EC_KEY_free(sk);
        return 1;
    }

    /* 3) Verify the signature with the public key (vk in python) */
    int ok = ECDSA_verify(0,
                          (const unsigned char *)message, (int)msg_len,
                          signature, sig_len,
                          sk);                   /* sk already holds the pubkey */

    printf("%s\n", ok == 1 ? "True" : "False");

    /* ------------------------------------------------------------------ */
    OPENSSL_free(signature);
    EC_KEY_free(sk);
    return 0;
}