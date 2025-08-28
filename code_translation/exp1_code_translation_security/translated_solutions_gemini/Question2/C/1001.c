#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// OpenSSL headers
#include <openssl/evp.h>
#include <openssl/ec.h>
#include <openssl/err.h>
#include <openssl/pem.h>

/**
 * @brief Signs a message using ECDSA with SHA-256.
 *
 * This function takes an EVP_PKEY containing an EC private key and a message,
 * and produces a digital signature.
 *
 * @param key A pointer to an EVP_PKEY structure holding the private key.
 * @param message A pointer to the message data to be signed.
 * @param message_len The length of the message data.
 * @param sig_len A pointer to a size_t variable where the length of the
 *                resulting signature will be stored.
 * @return A pointer to a dynamically allocated buffer containing the signature.
 *         The caller is responsible for freeing this buffer with free().
 *         Returns NULL on failure.
 */
unsigned char* signusingECDSA(EVP_PKEY *key, const unsigned char *message, size_t message_len, size_t *sig_len) {
    EVP_MD_CTX *md_ctx = NULL;
    unsigned char *signature = NULL;
    size_t signature_len;

    // 1. Create the signing context
    md_ctx = EVP_MD_CTX_new();
    if (md_ctx == NULL) {
        fprintf(stderr, "EVP_MD_CTX_new failed.\n");
        return NULL;
    }

    // 2. Initialize the signing operation with the key and hash function (SHA256)
    // This corresponds to: key.signer(ec.ECDSA(hashes.SHA256()))
    if (1 != EVP_DigestSignInit(md_ctx, NULL, EVP_sha256(), NULL, key)) {
        fprintf(stderr, "EVP_DigestSignInit failed.\n");
        ERR_print_errors_fp(stderr);
        EVP_MD_CTX_free(md_ctx);
        return NULL;
    }

    // 3. Provide the message to be signed
    // This corresponds to: signer.update(message)
    if (1 != EVP_DigestSignUpdate(md_ctx, message, message_len)) {
        fprintf(stderr, "EVP_DigestSignUpdate failed.\n");
        ERR_print_errors_fp(stderr);
        EVP_MD_CTX_free(md_ctx);
        return NULL;
    }

    // 4. Finalize the signature
    // This corresponds to: signer.finalize()

    // First, determine the required buffer size for the signature
    if (1 != EVP_DigestSignFinal(md_ctx, NULL, &signature_len)) {
        fprintf(stderr, "EVP_DigestSignFinal (for size) failed.\n");
        ERR_print_errors_fp(stderr);
        EVP_MD_CTX_free(md_ctx);
        return NULL;
    }

    // Allocate memory for the signature
    signature = (unsigned char *)malloc(signature_len);
    if (signature == NULL) {
        fprintf(stderr, "malloc failed.\n");
        EVP_MD_CTX_free(md_ctx);
        return NULL;
    }

    // Now, get the actual signature
    if (1 != EVP_DigestSignFinal(md_ctx, signature, &signature_len)) {
        fprintf(stderr, "EVP_DigestSignFinal (for signature) failed.\n");
        ERR_print_errors_fp(stderr);
        free(signature);
        EVP_MD_CTX_free(md_ctx);
        return NULL;
    }

    // Set the output length and clean up
    *sig_len = signature_len;
    EVP_MD_CTX_free(md_ctx);

    return signature;
}

// Helper function to generate a new EC key for demonstration
EVP_PKEY* generate_ec_key() {
    EVP_PKEY *pkey = NULL;
    EVP_PKEY_CTX *pctx = EVP_PKEY_CTX_new_id(EVP_PKEY_EC, NULL);
    if (!pctx) return NULL;
    if (EVP_PKEY_keygen_init(pctx) <= 0) {
        EVP_PKEY_CTX_free(pctx);
        return NULL;
    }
    // Use a standard curve, e.g., prime256v1 (NIST P-256)
    if (EVP_PKEY_CTX_set_ec_paramgen_curve_nid(pctx, NID_prime256v1) <= 0) {
        EVP_PKEY_CTX_free(pctx);
        return NULL;
    }
    if (EVP_PKEY_keygen(pctx, &pkey) <= 0) {
        EVP_PKEY_CTX_free(pctx);
        return NULL;
    }
    EVP_PKEY_CTX_free(pctx);
    return pkey;
}

// Helper function to print hex data
void print_hex(const char* label, const unsigned char* data, size_t len) {
    printf("%s (len=%zu): ", label, len);
    for (size_t i = 0; i < len; ++i) {
        printf("%02x", data[i]);
    }
    printf("\n");
}

// --- Main function to demonstrate usage ---
int main() {
    // Generate a sample EC private key
    EVP_PKEY *ec_key = generate_ec_key();
    if (!ec_key) {
        fprintf(stderr, "Failed to generate EC key.\n");
        return 1;
    }
    printf("Successfully generated EC key.\n");

    // Define a sample message
    const unsigned char *message = (const unsigned char *)"This is the message to be signed.";
    size_t message_len = strlen((const char *)message);
    print_hex("Message", message, message_len);

    // Call the signing function
    size_t signature_len;
    unsigned char *signature = signusingECDSA(ec_key, message, message_len, &signature_len);

    if (signature) {
        printf("Successfully created signature.\n");
        print_hex("Signature", signature, signature_len);

        // IMPORTANT: Free the memory allocated by the signing function
        free(signature);
    } else {
        fprintf(stderr, "Failed to sign the message.\n");
    }

    // Clean up the OpenSSL key object
    EVP_PKEY_free(ec_key);

    return 0;
}