/*
 *  Compile (OpenSSL ≥ 1.1.1):
 *      gcc -Wall -Wextra -pedantic -std=c11 \
 *          -o ecdsa_sign_example ecdsa_sign_example.c \
 *          -lcrypto
 *
 *  Run:
 *      ./ecdsa_sign_example
 */

#include <openssl/evp.h>
#include <openssl/ec.h>
#include <openssl/err.h>
#include <stdio.h>
#include <stdlib.h>

/* ------------------------------------------------------------------ */
/*  sign() – ECDSA-P384 + SHA-256                                      */
/* ------------------------------------------------------------------ */
int sign(EVP_PKEY      *key,
         const void    *data,
         size_t         data_len,
         unsigned char **sig,
         size_t        *sig_len)
{
    int ok = 0;                            /* return value (1 == success) */
    EVP_MD_CTX *mdctx = NULL;

    if (!key || !data || !sig || !sig_len)
        return 0;

    *sig     = NULL;
    *sig_len = 0;

    /* Create a message-digest context for signing */
    mdctx = EVP_MD_CTX_new();
    if (!mdctx)
        goto cleanup;

    /* Initialise: ECDSA using SHA-256 */
    if (EVP_DigestSignInit(mdctx, NULL, EVP_sha256(), NULL, key) != 1)
        goto cleanup;

    /* Feed the data */
    if (EVP_DigestSignUpdate(mdctx, data, data_len) != 1)
        goto cleanup;

    /* Determine signature length */
    if (EVP_DigestSignFinal(mdctx, NULL, sig_len) != 1)
        goto cleanup;

    /* Allocate buffer and actually obtain the signature */
    *sig = OPENSSL_malloc(*sig_len);
    if (!*sig)
        goto cleanup;

    if (EVP_DigestSignFinal(mdctx, *sig, sig_len) != 1)
        goto cleanup;

    ok = 1;                                /* Success */

cleanup:
    if (!ok && *sig) {                     /* on error free partial sig */
        OPENSSL_clear_free(*sig, *sig_len);
        *sig     = NULL;
        *sig_len = 0;
    }
    EVP_MD_CTX_free(mdctx);
    return ok;
}

/* ------------------------------------------------------------------ */
/*  Helper to print a buffer as hex                                    */
/* ------------------------------------------------------------------ */
static void print_hex(const unsigned char *buf, size_t len)
{
    for (size_t i = 0; i < len; ++i)
        printf("%02x", buf[i]);
    putchar('\n');
}

/* ------------------------------------------------------------------ */
/*  Equivalent of the Python “test” function                           */
/* ------------------------------------------------------------------ */
int main(void)
{
    int ret = EXIT_FAILURE;

    /* === 1. Generate EC (P-384) private key ======================== */
    EVP_PKEY_CTX *pctx = NULL;
    EVP_PKEY     *pkey = NULL;

    /* Create context for EC key generation */
    pctx = EVP_PKEY_CTX_new_id(EVP_PKEY_EC, NULL);
    if (!pctx) goto cleanup;

    if (EVP_PKEY_keygen_init(pctx) <= 0) goto cleanup;
    if (EVP_PKEY_CTX_set_ec_paramgen_curve_nid(pctx, NID_secp384r1) <= 0)
        goto cleanup;

    /* Generate the key */
    if (EVP_PKEY_keygen(pctx, &pkey) <= 0) goto cleanup;

    /* === 2. Sign some data ======================================== */
    const char *msg = "this is some data I'd like to sign";
    unsigned char *signature = NULL;
    size_t sig_len = 0;

    if (!sign(pkey, msg, strlen(msg), &signature, &sig_len))
        goto cleanup;

    /* === 3. Print the DER-encoded signature ======================= */
    printf("Signature (%zu bytes):\n", sig_len);
    print_hex(signature, sig_len);

    ret = EXIT_SUCCESS;                    /* Everything OK */

cleanup:
    if (ret != EXIT_SUCCESS) {             /* Show errors if any */
        unsigned long err;
        while ((err = ERR_get_error()))
            fprintf(stderr, "OpenSSL error: %s\n",
                    ERR_error_string(err, NULL));
    }

    /* Free resources */
    OPENSSL_clear_free(signature, sig_len);
    EVP_PKEY_free(pkey);
    EVP_PKEY_CTX_free(pctx);

    return ret;
}