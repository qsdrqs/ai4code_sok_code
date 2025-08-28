#include <stdio.h>
#include <stdlib.h>
#include <openssl/evp.h>
#include <openssl/ec.h>
#include <openssl/err.h>

/*
 * This C code provides equivalent functionality to the Python ECDSA functions
 * using the OpenSSL library.
 *
 * DEPENDENCIES:
 * This code requires the OpenSSL library to be installed.
 * When compiling, you must link against libcrypto.
 * Example: gcc your_code.c -o your_app -lcrypto
 */

/**
 * @brief Signs a given message using an ECDSA signing key.
 *
 * This function corresponds to the Python `sign` function. It takes a message
 * and an EVP_PKEY containing a private key, and produces a digital signature.
 * The signature is allocated on the heap and must be freed by the caller
 * using OPENSSL_free().
 *
 * @param pkey The EVP_PKEY object containing the ECDSA private key.
 * @param message A pointer to the message data to be signed.
 * @param message_len The length of the message data.
 * @param signature An output pointer that will be set to the newly allocated signature buffer.
 *                  The caller is responsible for freeing this buffer with OPENSSL_free().
 * @param signature_len An output pointer that will be set to the length of the signature.
 * @return 1 on success, 0 on failure.
 */
int ecdsa_sign(EVP_PKEY *pkey, const unsigned char *message, size_t message_len, unsigned char **signature, size_t *signature_len) {
    EVP_MD_CTX *md_ctx = NULL;
    int result = 0;

    if (!pkey || !message || !signature || !signature_len) {
        fprintf(stderr, "Error: Invalid input parameters.\n");
        return 0;
    }

    // Create a message digest context
    md_ctx = EVP_MD_CTX_new();
    if (md_ctx == NULL) {
        fprintf(stderr, "Error: EVP_MD_CTX_new failed.\n");
        ERR_print_errors_fp(stderr);
        return 0;
    }

    // Initialize the signing operation with SHA-256
    if (EVP_DigestSignInit(md_ctx, NULL, EVP_sha256(), NULL, pkey) != 1) {
        fprintf(stderr, "Error: EVP_DigestSignInit failed.\n");
        ERR_print_errors_fp(stderr);
        goto cleanup;
    }

    // Provide the message to be signed
    if (EVP_DigestSignUpdate(md_ctx, message, message_len) != 1) {
        fprintf(stderr, "Error: EVP_DigestSignUpdate failed.\n");
        ERR_print_errors_fp(stderr);
        goto cleanup;
    }

    // First, get the required size for the signature
    if (EVP_DigestSignFinal(md_ctx, NULL, signature_len) != 1) {
        fprintf(stderr, "Error: EVP_DigestSignFinal failed (to get size).\n");
        ERR_print_errors_fp(stderr);
        goto cleanup;
    }

    // Allocate memory for the signature
    *signature = OPENSSL_malloc(*signature_len);
    if (*signature == NULL) {
        fprintf(stderr, "Error: OPENSSL_malloc failed.\n");
        ERR_print_errors_fp(stderr);
        goto cleanup;
    }

    // Now, get the signature
    if (EVP_DigestSignFinal(md_ctx, *signature, signature_len) != 1) {
        fprintf(stderr, "Error: EVP_DigestSignFinal failed (to get signature).\n");
        ERR_print_errors_fp(stderr);
        OPENSSL_free(*signature);
        *signature = NULL;
        goto cleanup;
    }

    // Success
    result = 1;

cleanup:
    if (md_ctx) {
        EVP_MD_CTX_free(md_ctx);
    }
    return result;
}

/**
 * @brief Verifies a given signature using an ECDSA verification key.
 *
 * This function corresponds to the Python `verify` function. It checks if a
 * given signature is valid for a message and a public key.
 *
 * @param pkey The EVP_PKEY object containing the ECDSA public key.
 * @param message The message that was signed.
 * @param message_len The length of the message.
 * @param signature The signature to be verified.
 * @param signature_len The length of the signature.
 * @return 1 if the signature is valid (True), 0 if it is invalid (False), and -1 on error.
 */
int ecdsa_verify(EVP_PKEY *pkey, const unsigned char *message, size_t message_len, const unsigned char *signature, size_t signature_len) {
    EVP_MD_CTX *md_ctx = NULL;
    int result = -1; // Default to error

    if (!pkey || !message || !signature) {
        fprintf(stderr, "Error: Invalid input parameters.\n");
        return -1;
    }

    // Create a message digest context
    md_ctx = EVP_MD_CTX_new();
    if (md_ctx == NULL) {
        fprintf(stderr, "Error: EVP_MD_CTX_new failed.\n");
        ERR_print_errors_fp(stderr);
        return -1;
    }

    // Initialize the verification operation with SHA-256
    if (EVP_DigestVerifyInit(md_ctx, NULL, EVP_sha256(), NULL, pkey) != 1) {
        fprintf(stderr, "Error: EVP_DigestVerifyInit failed.\n");
        ERR_print_errors_fp(stderr);
        goto cleanup;
    }

    // Provide the message to be verified
    if (EVP_DigestVerifyUpdate(md_ctx, message, message_len) != 1) {
        fprintf(stderr, "Error: EVP_DigestVerifyUpdate failed.\n");
        ERR_print_errors_fp(stderr);
        goto cleanup;
    }

    // Verify the signature
    // EVP_DigestVerifyFinal returns 1 for a valid signature, 0 for an invalid one,
    // and a negative value for other errors.
    result = EVP_DigestVerifyFinal(md_ctx, signature, signature_len);

cleanup:
    if (md_ctx) {
        EVP_MD_CTX_free(md_ctx);
    }
    return result;
}