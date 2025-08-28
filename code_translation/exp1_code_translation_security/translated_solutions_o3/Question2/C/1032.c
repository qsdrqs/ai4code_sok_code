/*
 * Sign a message with an ECDSA-secp256k1 private key (OpenSSL version)
 *
 * Equivalent to the original Python implementation that used
 *   hashlib.sha256()  +  ecdsa.SigningKey.sign()
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>

#include <openssl/obj_mac.h>   /* NID_secp256k1                */
#include <openssl/ec.h>        /* EC_KEY, EC_GROUP, etc.       */
#include <openssl/ecdsa.h>     /* ECDSA_sign()                 */
#include <openssl/sha.h>       /* SHA256()                     */
#include <openssl/bn.h>        /* BIGNUM helpers               */

/* ------------------------------------------------------------------------- */
/* Small helper: convert ASCII hex -> raw bytes                              */
static int hex2bin(const char *hex, uint8_t *out, size_t out_len)
{
    size_t len = strlen(hex);
    if (len & 1 || len / 2 != out_len) {
        return 0;   /* length mismatch                                      */
    }

    for (size_t i = 0; i < len / 2; ++i) {
        unsigned int byte;
        if (sscanf(hex + 2*i, "%2x", &byte) != 1)
            return 0;
        out[i] = (uint8_t)byte;
    }
    return 1;
}

/* ------------------------------------------------------------------------- */
/* Core signing function                                                     */
static int sign_message(const char        *message,
                        const uint8_t     *priv_key_bytes,
                        size_t             priv_key_len,
                        uint8_t          **sig,        /* malloc-ed, set by fn */
                        unsigned int      *sig_len)
{
    int ret = 0;   /* 0 = failure, 1 = success */

    /* ---- Hash the message (SHA-256) ---- */
    uint8_t hash[SHA256_DIGEST_LENGTH];
    SHA256_CTX sha;
    SHA256_Init(&sha);
    SHA256_Update(&sha, message, strlen(message));
    SHA256_Final(hash, &sha);

    /* ---- Create and populate EC_KEY object ---- */
    EC_KEY *ec_key = NULL;
    BIGNUM *priv   = NULL;
    const EC_GROUP *group;

    if ((ec_key = EC_KEY_new_by_curve_name(NID_secp256k1)) == NULL)
        goto cleanup;

    /* Set the private key */
    if ((priv = BN_bin2bn(priv_key_bytes, priv_key_len, NULL)) == NULL)
        goto cleanup;
    if (!EC_KEY_set_private_key(ec_key, priv))
        goto cleanup;

    /* Derive and set the corresponding public key */
    group = EC_KEY_get0_group(ec_key);
    EC_POINT *pub = EC_POINT_new(group);
    if (pub == NULL)
        goto cleanup;
    if (!EC_POINT_mul(group, pub, priv, NULL, NULL, NULL))
        goto cleanup;
    if (!EC_KEY_set_public_key(ec_key, pub))
        goto cleanup;
    EC_POINT_free(pub); pub = NULL;

    /* ---- Sign the hash ---- */
    *sig_len = ECDSA_size(ec_key);              /* upper-bound */
    *sig     = malloc(*sig_len);
    if (*sig == NULL)
        goto cleanup;

    if (!ECDSA_sign(0, hash, sizeof(hash), *sig, sig_len, ec_key))
        goto cleanup;

    /* Success */
    ret = 1;

cleanup:
    if (!ret && *sig) {                 /* on failure, free any partial buf  */
        free(*sig);
        *sig = NULL;
        *sig_len = 0;
    }
    BN_clear_free(priv);
    EC_KEY_free(ec_key);
    return ret;
}

/* ------------------------------------------------------------------------- */
int main(void)
{
    const char *message = "Hello, world!";

    /* Same private key used in the Python example */
    const char *priv_hex =
        "18E14A7B6A307F426A94F8114701E7C8E774E7F9A47E2C2035DB29A206321725";

    uint8_t priv_key[32];                        /* 256-bit private key */

    if (!hex2bin(priv_hex, priv_key, sizeof(priv_key))) {
        fprintf(stderr, "Bad hex private key\n");
        return EXIT_FAILURE;
    }

    uint8_t      *signature = NULL;
    unsigned int  sig_len   = 0;

    if (!sign_message(message, priv_key, sizeof(priv_key),
                      &signature, &sig_len)) {
        fprintf(stderr, "Signing failed\n");
        return EXIT_FAILURE;
    }

    /* Print signature bytes in hex (DER-encoded) */
    printf("Signature (%u bytes):\n", sig_len);
    for (unsigned int i = 0; i < sig_len; ++i)
        printf("%02X", signature[i]);
    printf("\n");

    free(signature);
    return EXIT_SUCCESS;
}