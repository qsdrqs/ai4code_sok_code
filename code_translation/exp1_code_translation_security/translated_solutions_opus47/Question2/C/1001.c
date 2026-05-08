#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/evp.h>
#include <openssl/sha.h>
#include <openssl/err.h>
#include <stdlib.h>
#include <string.h>

unsigned char *signUsingECDSA(EVP_PKEY *key, const unsigned char *message, size_t message_len, size_t *sig_len) {
    EVP_MD_CTX *signer = NULL;
    unsigned char *signature = NULL;

    signer = EVP_MD_CTX_new();
    if (signer == NULL) {
        return NULL;
    }

    /* Equivalent to key.signer(ec.ECDSA(hashes.SHA256())) */
    if (EVP_DigestSignInit(signer, NULL, EVP_sha256(), NULL, key) != 1) {
        EVP_MD_CTX_free(signer);
        return NULL;
    }

    /* Equivalent to signer.update(message) */
    if (EVP_DigestSignUpdate(signer, message, message_len) != 1) {
        EVP_MD_CTX_free(signer);
        return NULL;
    }

    /* First call with NULL to obtain required buffer size */
    if (EVP_DigestSignFinal(signer, NULL, sig_len) != 1) {
        EVP_MD_CTX_free(signer);
        return NULL;
    }

    signature = (unsigned char *)malloc(*sig_len);
    if (signature == NULL) {
        EVP_MD_CTX_free(signer);
        return NULL;
    }

    /* Equivalent to signer.finalize() */
    if (EVP_DigestSignFinal(signer, signature, sig_len) != 1) {
        free(signature);
        EVP_MD_CTX_free(signer);
        return NULL;
    }

    EVP_MD_CTX_free(signer);
    return signature;
}