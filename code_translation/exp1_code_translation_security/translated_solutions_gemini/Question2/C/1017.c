#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// OpenSSL headers - these are the dependencies
#include <openssl/ec.h>      // For Elliptic Curve functions (EC_KEY)
#include <openssl/ecdsa.h>   // For ECDSA specific functions
#include <openssl/obj_mac.h> // For NID_secp256k1
#include <openssl/evp.h>     // For the high-level "Envelope" API (EVP_PKEY)
#include <openssl/err.h>     // For error reporting

/**
 * @brief Signs a given message using a given ECDSA signing key.
 *
 * This function takes a message and an EVP_PKEY containing an EC private key,
 * and produces a DER-encoded ECDSA signature. It uses SHA-256 as the hash algorithm.
 *
 * @param message Pointer to the message data to be signed.
 * @param message_len The length of the message data in bytes.
 * @param signing_key A pointer to an OpenSSL EVP_PKEY object containing the private key.
 * @param signature_len A pointer to a size_t variable where the length of the output signature will be stored.
 *
 * @return A pointer to a newly allocated buffer containing the DER-encoded signature, or NULL on failure.
 *         The caller is responsible for freeing this buffer using free().
 */
unsigned char* sign_message(const unsigned char* message, size_t message_len, EVP_PKEY* signing_key, size_t* signature_len) {
    // 1. Create a context for the signing operation.
    EVP_PKEY_CTX* ctx = EVP_PKEY_CTX_new(signing_key, NULL);
    if (!ctx) {
        fprintf(stderr, "Error: EVP_PKEY_CTX_new failed.\n");
        return NULL;
    }

    // 2. Initialize the signing operation. We'll use SHA-256 as the hash.
    if (EVP_PKEY_sign_init(ctx) <= 0) {
        fprintf(stderr, "Error: EVP_PKEY_sign_init failed.\n");
        EVP_PKEY_CTX_free(ctx);
        return NULL;
    }

    // 3. Set the hash algorithm to be used in the signature.
    if (EVP_PKEY_CTX_set_signature_md(ctx, EVP_sha256()) <= 0) {
        fprintf(stderr, "Error: EVP_PKEY_CTX_set_signature_md failed.\n");
        EVP_PKEY_CTX_free(ctx);
        return NULL;
    }

    // 4. Determine the required buffer size for the signature.
    size_t required_len;
    if (EVP_PKEY_sign(ctx, NULL, &required_len, message, message_len) <= 0) {
        fprintf(stderr, "Error: Failed to determine signature length.\n");
        EVP_PKEY_CTX_free(ctx);
        return NULL;
    }

    // 5. Allocate memory for the signature.
    unsigned char* signature = (unsigned char*)malloc(required_len);
    if (!signature) {
        fprintf(stderr, "Error: malloc failed for signature.\n");
        EVP_PKEY_CTX_free(ctx);
        return NULL;
    }

    // 6. Perform the signing.
    *signature_len = required_len; // The size might be adjusted by the function
    if (EVP_PKEY_sign(ctx, signature, signature_len, message, message_len) <= 0) {
        fprintf(stderr, "Error: EVP_PKEY_sign failed.\n");
        free(signature);
        EVP_PKEY_CTX_free(ctx);
        return NULL;
    }

    // 7. Clean up the context and return the signature.
    EVP_PKEY_CTX_free(ctx);
    return signature;
}

// --- Example Usage ---

// Helper function to print byte arrays in hex format
void print_hex(const char* label, const unsigned char* data, size_t len) {
    printf("%s: ", label);
    for (size_t i = 0; i < len; ++i) {
        printf("%02x", data[i]);
    }
    printf("\n");
}

int main() {
    // --- Setup: Create a sample message and an ECDSA key ---
    const unsigned char message[] = "This is a test message for ECDSA signing.";
    size_t message_len = strlen((const char*)message);

    // Generate a new EC key pair on the secp256k1 curve
    EC_KEY* ec_key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!ec_key) {
        fprintf(stderr, "Failed to create new EC key.\n");
        return 1;
    }
    if (EC_KEY_generate_key(ec_key) != 1) {
        fprintf(stderr, "Failed to generate EC key.\n");
        EC_KEY_free(ec_key);
        return 1;
    }

    // Wrap the EC_KEY in a generic EVP_PKEY structure. This is the modern way.
    EVP_PKEY* signing_key = EVP_PKEY_new();
    if (!signing_key || EVP_PKEY_assign_EC_KEY(signing_key, ec_key) != 1) {
        fprintf(stderr, "Failed to assign EC key to EVP_PKEY.\n");
        EC_KEY_free(ec_key); // EVP_PKEY_assign_EC_KEY takes ownership on success
        if (signing_key) EVP_PKEY_free(signing_key);
        return 1;
    }
    // Note: From now on, signing_key owns ec_key. Freeing signing_key will also free ec_key.

    printf("Successfully generated a temporary ECDSA key.\n");
    print_hex("Message", message, message_len);
    printf("\n");

    // --- Call the translated function ---
    size_t signature_len;
    unsigned char* signature = sign_message(message, message_len, signing_key, &signature_len);

    // --- Process the result ---
    if (signature) {
        printf("Message signed successfully!\n");
        print_hex("Signature (DER format)", signature, signature_len);
        printf("Signature Length: %zu bytes\n", signature_len);

        // IMPORTANT: Free the memory allocated by sign_message
        free(signature);
    } else {
        fprintf(stderr, "Failed to sign the message.\n");
        ERR_print_errors_fp(stderr);
    }

    // --- Cleanup ---
    EVP_PKEY_free(signing_key); // This also frees the underlying EC_KEY

    return 0;
}