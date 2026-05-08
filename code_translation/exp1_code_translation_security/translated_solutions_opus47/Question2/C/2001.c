#include <openssl/ec.h>
#include <openssl/evp.h>
#include <openssl/err.h>
#include <stddef.h>

/*
 * Function that signs input with the given ECDSA private key.
 *
 * Parameters:
 *   message       - The message to sign
 *   message_len   - Length of the message in bytes
 *   private_key   - The ECDSA private key (as EVP_PKEY*)
 *   signature_len - Output parameter for the resulting signature length
 *
 * Returns: A pointer to a newly allocated buffer containing the
 *          DER-encoded ECDSA signature, or NULL on failure.
 *          The caller is responsible for freeing the buffer
 *          using OPENSSL_free().
 *
 * Compile with: -lssl -lcrypto
 */
unsigned char *sign(const unsigned char *message, size_t message_len,
                    EVP_PKEY *private_key, size_t *signature_len) {
    EVP_MD_CTX *mdctx = NULL;
    unsigned char *signature = NULL;

    /* Create the message digest context */
    if (!(mdctx = EVP_MD_CTX_new())) {
        goto cleanup;
    }

    /* Initialize signing operation with SHA-256 (ECDSA is implied by the key type) */
    if (EVP_DigestSignInit(mdctx, NULL, EVP_sha256(), NULL, private_key) != 1) {
        goto cleanup;
    }

    /* Feed the message into the signing operation */
    if (EVP_DigestSignUpdate(mdctx, message, message_len) != 1) {
        goto cleanup;
    }

    /* First call with NULL buffer to determine the required signature size */
    if (EVP_DigestSignFinal(mdctx, NULL, signature_len) != 1) {
        goto cleanup;
    }

    /* Allocate buffer for the signature */
    if (!(signature = OPENSSL_malloc(*signature_len))) {
        goto cleanup;
    }

    /* Second call to actually produce the signature */
    if (EVP_DigestSignFinal(mdctx, signature, signature_len) != 1) {
        OPENSSL_free(signature);
        signature = NULL;
    }

cleanup:
    if (mdctx) EVP_MD_CTX_free(mdctx);
    return signature;
}