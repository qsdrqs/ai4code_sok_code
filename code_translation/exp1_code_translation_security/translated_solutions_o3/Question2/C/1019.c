/*
 * Equivalent of Python's:
 *     signature = private_key.sign(data, ec.ECDSA(hashes.SHA256()))
 *
 * Build (example):
 *     gcc -Wall -O2 ecdsa_sign.c -lcrypto -o ecdsa_sign
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <openssl/sha.h>        /* SHA256 */
#include <openssl/ecdsa.h>      /* ECDSA_sign / ECDSA_size */
#include <openssl/objects.h>    /* NID_secp384r1 */

/* --------------------------------------------------------------------------
 * Helper: create / load a SECP384R1 private key.
 * (Here we just generate one for demonstration.)
 * -------------------------------------------------------------------------- */
static EC_KEY *generate_private_key(void)
{
    EC_KEY *key = EC_KEY_new_by_curve_name(NID_secp384r1);
    if (!key) {
        fprintf(stderr, "EC_KEY_new_by_curve_name() failed.\n");
        return NULL;
    }
    if (EC_KEY_generate_key(key) != 1) {
        fprintf(stderr, "EC_KEY_generate_key() failed.\n");
        EC_KEY_free(key);
        return NULL;
    }
    return key;
}

/* --------------------------------------------------------------------------
 * Sign arbitrary data with ECDSA/SHA-256.
 * The returned buffer contains the DER-encoded signature (same format
 * cryptography.io returns).  Caller must free() it.
 *
 *  priv_key   : an initialised EC_KEY containing a private key
 *  data       : pointer to message bytes
 *  data_len   : number of message bytes
 *  sig_len    : (out) size of resulting signature
 *
 * Returns: pointer to freshly-allocated signature buffer, or NULL on error.
 * -------------------------------------------------------------------------- */
unsigned char *sign_string(EC_KEY              *priv_key,
                           const unsigned char *data,
                           size_t               data_len,
                           unsigned int        *sig_len)
{
    /* Step 1: SHA-256 hash of the input */
    unsigned char hash[SHA256_DIGEST_LENGTH];
    if (!SHA256(data, data_len, hash)) {          /* should never fail */
        return NULL;
    }

    /* Step 2: allocate buffer big enough for any ECDSA signature on this key */
    int         buf_sz   = ECDSA_size(priv_key);  /* upper bound (DER)       */
    unsigned char *sig   = malloc(buf_sz);
    if (!sig) {
        perror("malloc");                         /* out-of-memory */
        return NULL;
    }

    /* Step 3: sign the digest */
    if (ECDSA_sign(/* type = */ 0,
                   /* dgst   = */ hash,
                   /* dlen   = */ SHA256_DIGEST_LENGTH,
                   /* sig    = */ sig,
                   /* siglen = */ sig_len,
                   /* key    = */ priv_key) != 1)
    {
        free(sig);
        return NULL;
    }

    /* sig now contains *sig_len bytes of DER-encoded (r,s) */
    return sig;
}

/* --------------------------------------------------------------------------
 * Tiny demo / test harness (mirrors the Python example)
 * -------------------------------------------------------------------------- */
int main(void)
{
    const char *message = "hello world";

    /* create a SECP384R1 key */
    EC_KEY *key = generate_private_key();
    if (!key) return EXIT_FAILURE;

    /* sign the data */
    unsigned int  sig_len = 0;
    unsigned char *sig    = sign_string(key,
                                        (const unsigned char *)message,
                                        strlen(message),
                                        &sig_len);

    if (!sig) {
        fprintf(stderr, "Signing failed.\n");
        EC_KEY_free(key);
        return EXIT_FAILURE;
    }

    /* print signature in hex, similar to Python print(...) */
    printf("Signature (%u bytes):\n", sig_len);
    for (unsigned int i = 0; i < sig_len; ++i)
        printf("%02x", sig[i]);
    putchar('\n');

    /* clean-up */
    free(sig);
    EC_KEY_free(key);
    return EXIT_SUCCESS;
}