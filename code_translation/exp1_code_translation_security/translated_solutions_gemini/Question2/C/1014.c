/**
 * @file sign_message.c
 * @brief Functions that sign a given message using a given ECDSA signing key.
 * This is a C translation of the provided Python code, using OpenSSL.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// OpenSSL headers for all necessary cryptographic operations
#include <openssl/ec.h>      // For Elliptic Curve functions (EC_KEY)
#include <openssl/ecdsa.h>   // For ECDSA signing functions (ECDSA_sign)
#include <openssl/sha.h>     // For SHA256 hashing
#include <openssl/obj_mac.h> // For NID_secp256k1
#include <openssl/err.h>     // For error reporting

/**
 * @brief Signs a given message using a given ECDSA signing key.
 *
 * @param message The raw message data to be signed.
 * @param message_len The length of the message data.
 * @param signing_key A pointer to an EC_KEY object containing the private key.
 * @param signature A pointer to a buffer that will be allocated to store the DER-encoded signature.
 *                  The caller is responsible for freeing this buffer using free().
 * @param signature_len A pointer to a size_t variable where the length of the signature will be stored.
 * @return 1 on success, 0 on failure.
 */
int sign_message(const unsigned char *message, size_t message_len, EC_KEY *signing_key, unsigned char **signature, size_t *signature_len) {
    // --- 1. Hash the message ---
    // In C, we need a buffer to store the hash result.
    // SHA256 always produces a 32-byte (256-bit) hash.
    unsigned char message_hash[SHA256_DIGEST_LENGTH];

    // The SHA256 function calculates the hash of the message and places it in message_hash.
    if (!SHA256(message, message_len, message_hash)) {
        fprintf(stderr, "Failed to hash the message.\n");
        return 0;
    }

    // --- 2. Sign the message hash ---
    // The signature is in DER format, which has a variable length.
    // We first determine the maximum possible size for the signature.
    int max_sig_len = ECDSA_size(signing_key);
    if (max_sig_len <= 0) {
        fprintf(stderr, "Failed to determine max signature size.\n");
        ERR_print_errors_fp(stderr);
        return 0;
    }

    // Allocate memory for the signature.
    *signature = (unsigned char *)malloc(max_sig_len);
    if (*signature == NULL) {
        fprintf(stderr, "Failed to allocate memory for signature.\n");
        return 0;
    }

    // The ECDSA_sign function performs the signing.
    // It takes the hash and produces a DER-encoded signature by default,
    // which matches the behavior of `sigencode=ecdsa.util.sigencode_der`.
    unsigned int sig_len_int; // ECDSA_sign uses an unsigned int for length
    if (ECDSA_sign(0, message_hash, SHA256_DIGEST_LENGTH, *signature, &sig_len_int, signing_key) != 1) {
        fprintf(stderr, "Failed to sign the message hash.\n");
        ERR_print_errors_fp(stderr);
        free(*signature); // Clean up allocated memory on failure
        *signature = NULL;
        return 0;
    }

    *signature_len = (size_t)sig_len_int;

    // Optional: Reallocate to the exact size to save memory.
    unsigned char* realloc_sig = realloc(*signature, *signature_len);
    if (realloc_sig == NULL) {
        fprintf(stderr, "Failed to reallocate memory for signature.\n");
        free(*signature);
        *signature = NULL;
        return 0;
    }
    *signature = realloc_sig;


    return 1; // Success
}

// Helper function to print byte arrays in hex format
void print_hex(const char* label, const unsigned char* data, size_t len) {
    printf("%s (%zu bytes): ", label, len);
    for (size_t i = 0; i < len; ++i) {
        printf("%02x", data[i]);
    }
    printf("\n");
}

// --- Main function to demonstrate usage ---
int main() {
    // --- Setup: Create a sample message and ECDSA key ---
    const char *message_str = "This is a test message for ECDSA signing.";
    const unsigned char *message = (const unsigned char *)message_str;
    size_t message_len = strlen(message_str);

    // Create a new EC_KEY object using the secp256k1 curve (commonly used in Bitcoin/Ethereum)
    EC_KEY *signing_key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (signing_key == NULL) {
        fprintf(stderr, "Failed to create EC_KEY object.\n");
        ERR_print_errors_fp(stderr);
        return 1;
    }

    // Generate the private and public key pair
    if (EC_KEY_generate_key(signing_key) != 1) {
        fprintf(stderr, "Failed to generate EC key pair.\n");
        ERR_print_errors_fp(stderr);
        EC_KEY_free(signing_key);
        return 1;
    }

    printf("Successfully generated a new ECDSA key.\n");
    print_hex("Message", message, message_len);

    // --- Call the sign_message function ---
    unsigned char *signature = NULL;
    size_t signature_len = 0;

    if (sign_message(message, message_len, signing_key, &signature, &signature_len)) {
        printf("Message signed successfully!\n");
        print_hex("Signature (DER)", signature, signature_len);

        // --- Cleanup ---
        free(signature); // Free the memory allocated by sign_message
    } else {
        fprintf(stderr, "Message signing failed.\n");
    }

    // Free the EC_KEY object
    EC_KEY_free(signing_key);

    return 0;
}