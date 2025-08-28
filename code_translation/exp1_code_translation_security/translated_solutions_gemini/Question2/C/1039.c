#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// OpenSSL headers
#include <openssl/evp.h>
#include <openssl/ec.h>
#include <openssl/pem.h>
#include <openssl/err.h>

/**
 * @brief Signs a message using a given ECDSA private key.
 *
 * This function directly translates the Python behavior, which signs the hardcoded
 * byte string "message". It uses the EVP (Envelope) API from OpenSSL for the
 * signing process with the SHA-256 hash algorithm.
 *
 * @param pkey The private key (EVP_PKEY*) to sign with.
 * @param sig_len A pointer to a size_t variable where the length of the
 *                output signature will be stored.
 * @return A dynamically allocated buffer containing the DER-encoded signature.
 *         The caller is responsible for freeing this buffer with free().
 *         Returns NULL on failure.
 */
unsigned char* signMessage(EVP_PKEY* pkey, size_t* sig_len) {
    EVP_MD_CTX* md_ctx = NULL;
    const char* message = "message"; // The hardcoded message from the Python code
    size_t message_len = strlen(message);
    unsigned char* signature = NULL;

    // 1. Create a message digest context
    if (!(md_ctx = EVP_MD_CTX_new())) {
        fprintf(stderr, "Error: EVP_MD_CTX_new failed.\n");
        return NULL;
    }

    // 2. Initialize the signing operation.
    // We use SHA-256 as the hash function. The Python ecdsa library often defaults
    // to SHA-1, but SHA-256 is the modern standard.
    if (EVP_DigestSignInit(md_ctx, NULL, EVP_sha256(), NULL, pkey) != 1) {
        fprintf(stderr, "Error: EVP_DigestSignInit failed.\n");
        EVP_MD_CTX_free(md_ctx);
        return NULL;
    }

    // 3. Provide the message to be signed
    if (EVP_DigestSignUpdate(md_ctx, message, message_len) != 1) {
        fprintf(stderr, "Error: EVP_DigestSignUpdate failed.\n");
        EVP_MD_CTX_free(md_ctx);
        return NULL;
    }

    // 4. Finalize the signature.
    // First, get the required buffer size for the signature.
    if (EVP_DigestSignFinal(md_ctx, NULL, sig_len) != 1) {
        fprintf(stderr, "Error: EVP_DigestSignFinal (for size) failed.\n");
        EVP_MD_CTX_free(md_ctx);
        return NULL;
    }

    // Allocate memory for the signature
    if (!(signature = malloc(*sig_len))) {
        fprintf(stderr, "Error: malloc failed for signature.\n");
        EVP_MD_CTX_free(md_ctx);
        return NULL;
    }

    // Actually get the signature
    if (EVP_DigestSignFinal(md_ctx, signature, sig_len) != 1) {
        fprintf(stderr, "Error: EVP_DigestSignFinal (for signature) failed.\n");
        free(signature);
        EVP_MD_CTX_free(md_d_ctx);
        return NULL;
    }

    // 5. Clean up and return
    EVP_MD_CTX_free(md_ctx);
    return signature;
}

// Helper function to print bytes in hex format
void print_hex(const char* label, const unsigned char* data, size_t len) {
    printf("%s (%zu bytes):\n", label, len);
    for (size_t i = 0; i < len; ++i) {
        printf("%02x", data[i]);
    }
    printf("\n\n");
}

// A main function to demonstrate the usage of signMessage
int main() {
    EVP_PKEY* pkey = NULL;
    EVP_PKEY_CTX* pctx = NULL;

    printf("Generating a new ECDSA private key (using secp256k1 curve)...\n");

    // 1. Create a context for the key generation
    pctx = EVP_PKEY_CTX_new_id(EVP_PKEY_EC, NULL);
    if (!pctx) {
        ERR_print_errors_fp(stderr);
        return 1;
    }

    // 2. Initialize the key generation
    if (EVP_PKEY_keygen_init(pctx) <= 0) {
        ERR_print_errors_fp(stderr);
        EVP_PKEY_CTX_free(pctx);
        return 1;
    }

    // 3. Set the curve for the key (secp256k1 is a common choice)
    if (EVP_PKEY_CTX_set_ec_paramgen_curve_nid(pctx, NID_secp256k1) <= 0) {
        ERR_print_errors_fp(stderr);
        EVP_PKEY_CTX_free(pctx);
        return 1;
    }

    // 4. Generate the key
    if (EVP_PKEY_keygen(pctx, &pkey) <= 0) {
        ERR_print_errors_fp(stderr);
        EVP_PKEY_CTX_free(pctx);
        return 1;
    }

    printf("Key generation successful.\n\n");
    EVP_PKEY_CTX_free(pctx); // No longer need the context

    // Now, use the generated key to sign the message
    size_t signature_len = 0;
    unsigned char* signature = signMessage(pkey, &signature_len);

    if (signature) {
        printf("--- Signing Succeeded ---\n");
        print_hex("Message", (const unsigned char*)"message", 7);
        print_hex("Signature", signature, signature_len);

        // Clean up the signature buffer
        free(signature);
    } else {
        printf("--- Signing Failed ---\n");
        ERR_print_errors_fp(stderr);
    }

    // Clean up the key
    EVP_PKEY_free(pkey);

    return 0;
}