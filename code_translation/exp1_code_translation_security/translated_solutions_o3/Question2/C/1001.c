/*  sign_using_ECDSA.c
 *
 *  C equivalent of:
 *      signer = key.signer(ec.ECDSA(hashes.SHA256()))
 *      signer.update(message)
 *      return signer.finalize()
 *
 *  All required OpenSSL headers/libraries are assumed to be available.
 */

#include <openssl/evp.h>
#include <openssl/err.h>
#include <stdlib.h>
#include <string.h>

/*  sign_using_ECDSA
 *  -----------------
 *  key            : an initialised private EC key wrapped in an EVP_PKEY
 *  message        : data to be signed
 *  message_len    : size of 'message' in bytes
 *  sig            : (out) pointer that will receive a newly-allocated buffer
 *                   containing the signature
 *  sig_len        : (out) size of the signature in bytes
 *
 *  returns 1 on success, 0 on failure.
 *
 *  NOTE: *sig is allocated with OPENSSL_malloc(); the caller must free it
 *        with OPENSSL_free() when finished.
 */
int sign_using_ECDSA(EVP_PKEY       *key,
                     const uint8_t  *message,
                     size_t          message_len,
                     uint8_t       **sig,
                     size_t         *sig_len)
{
    EVP_MD_CTX *mdctx = NULL;
    int ok = 0;               /* will be set to 1 on success */

    if (!key || !message || !sig || !sig_len)
        return 0;

    /* 1. Create and initialise the context */
    mdctx = EVP_MD_CTX_new();
    if (mdctx == NULL)
        goto cleanup;

    /* 2. Initialise the DigestSign operation with SHA-256 + the EC key */
    if (EVP_DigestSignInit(mdctx, NULL, EVP_sha256(), NULL, key) != 1)
        goto cleanup;

    /* 3. Add data to be signed */
    if (EVP_DigestSignUpdate(mdctx, message, message_len) != 1)
        goto cleanup;

    /* 4. First call: obtain required signature length */
    if (EVP_DigestSignFinal(mdctx, NULL, sig_len) != 1)
        goto cleanup;

    *sig = OPENSSL_malloc(*sig_len);
    if (*sig == NULL)
        goto cleanup;

    /* 5. Second call: actually obtain the signature */
    if (EVP_DigestSignFinal(mdctx, *sig, sig_len) != 1)
        goto cleanup;

    ok = 1;   /* success */

cleanup:
    if (!ok) {
        /* On failure, free any partial allocation */
        OPENSSL_free(*sig);
        *sig = NULL;
        *sig_len = 0;
    }

    EVP_MD_CTX_free(mdctx);
    return ok;
}