#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// OpenSSL headers
#include <openssl/evp.h>
#include <openssl/ec.h>
#include <openssl/pem.h>
#include <openssl/err.h>

/**
 * @brief Generates a new ECDSA (P-256) private key.
 *
 * In a real application, you would load a key from a file or secure storage.
 * This function is for demonstration purposes.
 *
 * @return A pointer to an EVP_PKEY structure, or NULL on failure.
 *         The caller is responsible for freeing the key with EVP_PKEY_free().
 */
EVP_PKEY* generate_signing_key() {
    EVP_PKEY *pkey = NULL;
    EVP_PKEY_CTX *pctx = EVP_PKEY_CTX_new_id(EVP_PKEY_EC, NULL);
    if (!pctx) {
        fprintf(stderr, "Failed to create PKEY context.\n");
        return NULL;
    }

    if (EVP_PKEY_keygen_init(pctx) <= 0) {
        fprintf(stderr, "Failed to initialize keygen.\n");
        EVP_PKEY_CTX_free(pctx);
        return NULL;
    }

    // Set the curve to P-256 (a.k.a. prime256v1 or secp256r1)
    if (EVP_PKEY_CTX_set_ec_paramgen_curve_nid(pctx, NID_X9_62_prime256v1) <= 0) {
        fprintf(stderr, "Failed to set EC curve.\n");
        EVP_PKEY_CTX_free(pctx);
        return NULL;
    }

    if (EVP_PKEY_keygen(pctx, &pkey) <= 0) {
        fprintf(stderr, "Failed to generate key.\n");
        EVP_PKEY_CTX_free(pctx);
        return NULL;
    }

    EVP_PKEY_CTX_free(pctx);
    return pkey;
}


/**
 * @brief Signs a message using a private key (EVP_PKEY).
 *
 * This function is the C equivalent of `signing_key.sign(message)`.
 * It allocates memory for the signature, which the caller must free.
 *
 * @param message The message data to sign.
 * @param message_len The length of the message data.
 * @param signing_key The private key to use for signing.
 * @param signature A pointer to a buffer that will be allocated to hold the signature.
 * @param signature_len A pointer to a size_t variable that will hold the signature length.
 * @return 0 on success, -1 on failure.
 */
int sign_message(
    const unsigned char *message,
    size_t message_len,
    EVP_PKEY *signing_key,
    unsigned char **signature,
    size_t *signature_len
) {
    EVP_MD_CTX *md_ctx = NULL;
    int result = -1;

    // 1. Create a message digest context
    md_ctx = EVP_MD_CTX_new();
    if (md_ctx == NULL) {
        fprintf(stderr, "EVP_MD_CTX_new failed.\n");
        goto cleanup;
    }

    // 2. Initialize the signing operation with the key and digest (SHA-256)
    //    The digest algorithm is often determined by the security policy or key type.
    if (EVP_DigestSignInit(md_ctx, NULL, EVP_sha256(), NULL, signing_key) != 1) {
        fprintf(stderr, "EVP_DigestSignInit failed.\n");
        goto cleanup;
    }

    // 3. Provide the message to be signed
    if (EVP_DigestSignUpdate(md_ctx, message, message_len) != 1) {
        fprintf(stderr, "EVP_DigestSignUpdate failed.\n");
        goto cleanup;
    }

    // 4. Finalize the signature
    // First, get the required size for the signature buffer
    if (EVP_DigestSignFinal(md_ctx, NULL, signature_len) != 1) {
        fprintf(stderr, "EVP_DigestSignFinal (get size) failed.\n");
        goto cleanup;
    }

    // Allocate memory for the signature
    *signature = malloc(*signature_len);
    if (*signature == NULL) {
        fprintf(stderr, "malloc failed for signature.\n");
        goto cleanup;
    }

    // Now, get the actual signature
    if (EVP_DigestSignFinal(md_ctx, *signature, signature_len) != 1) {
        fprintf(stderr, "EVP_DigestSignFinal (get signature) failed.\n");
        free(*signature);
        *signature = NULL; // Avoid dangling pointer
        goto cleanup;
    }

    // Success
    result = 0;

cleanup:
    if (md_ctx != NULL) {
        EVP_MD_CTX_free(md_ctx);
    }
    if (result != 0) {
        ERR_print_errors_fp(stderr);
    }
    return result;
}

// Helper function to print bytes in hex format
void print_hex(const char* label, const unsigned char* data, size_t len) {
    printf("%s (%zu bytes):\n", label, len);
    for (size_t i = 0; i < len; ++i) {
        printf("%02x", data[i]);
    }
    printf("\n\n");
}

int main() {
    // The message to be signed
    const char *message_str = "This is the message to be signed.";
    const unsigned char *message = (const unsigned char *)message_str;
    size_t message_len = strlen(message_str);

    // The signature will be stored here
    unsigned char *signature = NULL;
    size_t signature_len = 0;

    // 1. Generate a signing key (in a real app, you'd load this)
    EVP_PKEY *signing_key = generate_signing_key();
    if (signing_key == NULL) {
        fprintf(stderr, "Could not generate signing key.\n");
        return 1;
    }
    printf("Successfully generated a new ECDSA P-256 key.\n\n");

    // 2. Call the sign_message function
    printf("Signing message: \"%s\"\n\n", message_str);
    if (sign_message(message, message_len, signing_key, &signature, &signature_len) != 0) {
        fprintf(stderr, "Failed to sign the message.\n");
        EVP_PKEY_free(signing_key);
        return 1;
    }

    // 3. Print the resulting signature
    printf("Successfully signed the message.\n");
    print_hex("Signature", signature, signature_len);

    // 4. Clean up allocated memory
    free(signature);
    EVP_PKEY_free(signing_key);

    return 0;
}