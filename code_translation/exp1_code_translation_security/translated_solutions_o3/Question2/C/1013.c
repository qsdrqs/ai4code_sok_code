/* sign.c */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <openssl/ec.h>       /* EC_KEY, EC_KEY_new_by_curve_name, …  */
#include <openssl/ecdsa.h>    /* ECDSA_sign, ECDSA_size               */
#include <openssl/obj_mac.h>  /* curve NIDs                            */
#include <openssl/sha.h>      /* SHA256                               */

/* ------------------------------------------------------------------ */
/* Sign `message` with an already-generated EC_KEY.                    */
/* The function returns a malloc’ed buffer holding the signature and   */
/* sets *sig_len to the signature’s length. Caller must free().        */
/* ------------------------------------------------------------------ */
static unsigned char *sign_message(const char *message,
                                   EC_KEY       *key,
                                   unsigned int *sig_len)
{
    unsigned char hash[SHA256_DIGEST_LENGTH];

    /* Hash the input (ECDSA signs the hash, not the raw message)      */
    SHA256((const unsigned char *)message, strlen(message), hash);

    /* Allocate enough space for the signature                        */
    unsigned char *sig = OPENSSL_malloc(ECDSA_size(key));
    if (!sig) return NULL;

    /* Perform the signature                                          */
    if (ECDSA_sign(0, hash, SHA256_DIGEST_LENGTH, sig, sig_len, key) == 0)
    {
        OPENSSL_free(sig);
        return NULL;
    }
    return sig;   /* success */
}

int main(void)
{
    /* -------------------------------------------------------------- */
    /* 1. Generate a new EC private key using the NIST-P192 curve     */
    /*    (called prime192v1 in OpenSSL, matches Python’s NIST192p).  */
    /* -------------------------------------------------------------- */
    EC_KEY *key = EC_KEY_new_by_curve_name(NID_X9_62_prime192v1);
    if (!key)
    {
        fprintf(stderr, "Failed to create EC_KEY object.\n");
        return EXIT_FAILURE;
    }

    if (EC_KEY_generate_key(key) == 0)
    {
        fprintf(stderr, "Key generation failure.\n");
        EC_KEY_free(key);
        return EXIT_FAILURE;
    }

    /* -------------------------------------------------------------- */
    /* 2. Sign the message                                            */
    /* -------------------------------------------------------------- */
    unsigned int sig_len = 0;
    unsigned char *signature = sign_message("something", key, &sig_len);

    if (!signature)
    {
        fprintf(stderr, "Signing failed.\n");
        EC_KEY_free(key);
        return EXIT_FAILURE;
    }

    /* -------------------------------------------------------------- */
    /* 3. Print signature in hex (similar to repr() in Python)        */
    /* -------------------------------------------------------------- */
    printf("Signature (%u bytes): ", sig_len);
    for (unsigned int i = 0; i < sig_len; ++i)
        printf("%02X", signature[i]);
    printf("\n");

    /* -------------------------------------------------------------- */
    /* 4. Clean up                                                    */
    /* -------------------------------------------------------------- */
    OPENSSL_free(signature);
    EC_KEY_free(key);
    return EXIT_SUCCESS;
}