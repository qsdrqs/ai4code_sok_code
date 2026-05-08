#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/pem.h>
#include <openssl/rsa.h>
#include <openssl/sha.h>
#include <openssl/evp.h>
#include <openssl/err.h>

/*
 * sign a message
 *
 * Equivalent to PyCryptodome's:
 *     signer = PKCS1_v1_5.new(priv_key)
 *     digest = SHA256.new()
 *     digest.update(msg)
 *     signature = signer.sign(digest)
 *
 * Parameters:
 *   msg       - pointer to message bytes
 *   msg_len   - length of message
 *   priv_key  - RSA private key as EVP_PKEY*
 *   signature - output: pointer to allocated signature (caller must free with OPENSSL_free)
 *   sig_len   - output: length of signature
 *
 * Returns: 1 on success, 0 on failure
 */
int sign(const unsigned char *msg, size_t msg_len, EVP_PKEY *priv_key,
         unsigned char **signature, size_t *sig_len) {
    EVP_MD_CTX *mdctx = NULL;
    int ret = 0;

    *signature = NULL;

    /* Create the message digest context (equivalent to SHA256.new()) */
    if (!(mdctx = EVP_MD_CTX_new())) {
        goto cleanup;
    }

    /* Initialize signing context with SHA256.
     * By default, EVP_DigestSignInit uses PKCS1 v1.5 padding for RSA keys,
     * equivalent to PKCS1_v1_5.new(priv_key). */
    if (EVP_DigestSignInit(mdctx, NULL, EVP_sha256(), NULL, priv_key) != 1) {
        goto cleanup;
    }

    /* Update digest with message (equivalent to digest.update(msg)) */
    if (EVP_DigestSignUpdate(mdctx, msg, msg_len) != 1) {
        goto cleanup;
    }

    /* First call determines required signature buffer size */
    if (EVP_DigestSignFinal(mdctx, NULL, sig_len) != 1) {
        goto cleanup;
    }

    /* Allocate buffer for signature */
    *signature = (unsigned char *)OPENSSL_malloc(*sig_len);
    if (*signature == NULL) {
        goto cleanup;
    }

    /* Compute the signature (equivalent to signer.sign(digest)) */
    if (EVP_DigestSignFinal(mdctx, *signature, sig_len) != 1) {
        OPENSSL_free(*signature);
        *signature = NULL;
        goto cleanup;
    }

    ret = 1;

cleanup:
    if (mdctx) EVP_MD_CTX_free(mdctx);
    return ret;
}