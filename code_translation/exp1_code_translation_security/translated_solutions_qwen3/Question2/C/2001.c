#include <openssl/evp.h>
#include <openssl/ec.h>
#include <openssl/sha.h>
#include <openssl/openssl_malloc.h>

/**
 * Signs a message using ECDSA with SHA-256.
 *
 * @param message     Pointer to the message data to be signed.
 * @param message_len Length of the message in bytes.
 * @param private_key ECDSA private key as an EVP_PKEY*.
 * @param sig_len     Output parameter to store the length of the signature.
 * @return            Pointer to the signature buffer (must be freed with OPENSSL_free),
 *                    or NULL on failure.
 */
unsigned char* sign(const unsigned char* message, size_t message_len, EVP_PKEY* private_key, size_t* sig_len) {
    EVP_MD_CTX* ctx = EVP_MD_CTX_new();
    if (!ctx) {
        return NULL;
    }

    if (EVP_DigestSignInit(ctx, NULL, EVP_sha256(), NULL, private_key) != 1) {
        EVP_MD_CTX_free(ctx);
        return NULL;
    }

    if (EVP_DigestSignUpdate(ctx, message, message_len) != 1) {
        EVP_MD_CTX_free(ctx);
        return NULL;
    }

    // First call to get the required signature length
    if (EVP_DigestSignFinal(ctx, NULL, sig_len) != 1) {
        EVP_MD_CTX_free(ctx);
        return NULL;
    }

    unsigned char* signature = OPENSSL_malloc(*sig_len);
    if (!signature) {
        EVP_MD_CTX_free(ctx);
        return NULL;
    }

    // Second call to actually generate the signature
    if (EVP_DigestSignFinal(ctx, signature, sig_len) != 1) {
        OPENSSL_free(signature);
        EVP_MD_CTX_free(ctx);
        return NULL;
    }

    EVP_MD_CTX_free(ctx);
    return signature;
}