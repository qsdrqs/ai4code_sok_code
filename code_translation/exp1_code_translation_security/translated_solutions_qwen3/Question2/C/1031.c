#include <openssl/evp.h>
#include <openssl/pkey.h>
#include <openssl/rsa.h>
#include <openssl/sha.h>
#include <openssl/err.h>
#include <stdio.h>
#include <stdlib.h>

/**
 * Signs a message using an RSA private key with PKCS#1 v1.5 padding and SHA-256.
 *
 * @param msg     Pointer to the message data to be signed.
 * @param msg_len Length of the message in bytes.
 * @param pkey    Pointer to the RSA private key (EVP_PKEY structure).
 * @param sig_len Pointer to a size_t variable to store the length of the signature.
 * @return        Pointer to the signature (must be freed with OPENSSL_free), or NULL on failure.
 */
unsigned char *sign(const unsigned char *msg, size_t msg_len, EVP_PKEY *pkey, size_t *sig_len) {
    // Step 1: Compute SHA-256 hash of the message
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256(msg, msg_len, hash);

    // Step 2: Create and initialize the signing context
    EVP_PKEY_CTX *ctx = EVP_PKEY_CTX_new(pkey, NULL);
    if (!ctx) {
        fprintf(stderr, "Failed to create EVP_PKEY_CTX\n");
        return NULL;
    }

    if (EVP_PKEY_sign_init(ctx) <= 0) {
        fprintf(stderr, "Failed to initialize signing context\n");
        EVP_PKEY_CTX_free(ctx);
        return NULL;
    }

    // Step 3: Set RSA padding and message digest
    if (EVP_PKEY_CTX_set_rsa_padding(ctx, RSA_PKCS1_PADDING) <= 0 ||
        EVP_PKEY_CTX_set_signature_md(ctx, EVP_sha256()) <= 0) {
        fprintf(stderr, "Failed to set RSA padding or digest\n");
        EVP_PKEY_CTX_free(ctx);
        return NULL;
    }

    // Step 4: Determine the required signature length
    if (EVP_PKEY_sign(ctx, NULL, sig_len, hash, SHA256_DIGEST_LENGTH) <= 0) {
        fprintf(stderr, "Failed to determine signature length\n");
        EVP_PKEY_CTX_free(ctx);
        return NULL;
    }

    // Step 5: Allocate memory for the signature
    unsigned char *signature = OPENSSL_malloc(*sig_len);
    if (!signature) {
        fprintf(stderr, "Failed to allocate memory for signature\n");
        EVP_PKEY_CTX_free(ctx);
        return NULL;
    }

    // Step 6: Perform the signing operation
    if (EVP_PKEY_sign(ctx, signature, sig_len, hash, SHA256_DIGEST_LENGTH) <= 0) {
        fprintf(stderr, "Failed to sign message\n");
        OPENSSL_free(signature);
        EVP_PKEY_CTX_free(ctx);
        return NULL;
    }

    EVP_PKEY_CTX_free(ctx);
    return signature;
}