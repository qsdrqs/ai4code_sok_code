/**
 * @file sign_message.c
 * @brief C implementation for signing a message with ECDSA on the secp256k1 curve.
 *
 * This code provides a C equivalent to the Python ecdsa signing function.
 * It uses libsecp256k1 for the core cryptographic operations.
 *
 * Dependencies:
 * - libsecp256k1: For ECDSA signing. (https://github.com/bitcoin-core/secp256k1)
 * - OpenSSL (libcrypto): For SHA-256 hashing.
 *
 * Compilation command:
 * gcc sign_message.c -o sign_message -lsecp256k1 -lcrypto
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

// Dependency: OpenSSL for SHA256 hashing
#include <openssl/sha.h>

// Dependency: libsecp256k1 for ECDSA operations
#include "secp256k1.h"

/**
 * @brief Signs a 32-byte message hash using a 32-byte private key.
 *
 * This function performs an ECDSA signature on the secp256k1 curve. It is the C
 * equivalent of the Python function `ecdsa.SigningKey.from_string(...).sign_digest(...)`.
 *
 * @param[in]  message_hash   A pointer to the 32-byte hash of the message to be signed.
 * @param[in]  private_key    A pointer to the 32-byte private key.
 * @param[out] signature      A pointer to a buffer where the resulting 64-byte compact
 *                            signature (r || s) will be stored. The buffer must be at
 *                            least 64 bytes long.
 * @param[out] signature_len  A pointer to a size_t variable that will be filled with the
 *                            length of the output signature (always 64 for compact format).
 * @return 0 on success, non-zero on failure.
 *         -1: Failed to create secp256k1 context.
 *         -2: The provided private key is invalid.
 *         -3: The signing operation failed.
 */
int sign_message(const unsigned char *message_hash, const unsigned char *private_key, unsigned char *signature, size_t *signature_len) {
    secp256k1_context* ctx;
    secp256k1_ecdsa_signature sig;
    int result;

    // Create a secp256k1 context for signing.
    // SECP256K1_CONTEXT_SIGN is a flag that precomputes tables for signing efficiency.
    ctx = secp256k1_context_create(SECP256K1_CONTEXT_SIGN);
    if (ctx == NULL) {
        fprintf(stderr, "Error: Failed to create secp256k1 context.\n");
        return -1;
    }

    // Verify the private key is valid before use. A valid key is a non-zero scalar
    // between 1 and the curve order.
    if (!secp256k1_ec_seckey_verify(ctx, private_key)) {
        fprintf(stderr, "Error: The provided private key is invalid.\n");
        secp256k1_context_destroy(ctx);
        return -2;
    }

    // Sign the 32-byte message hash with the private key.
    // The nonce function is set to NULL to use the deterministic default (RFC6979).
    result = secp256k1_ecdsa_sign(ctx, &sig, message_hash, private_key, NULL, NULL);
    if (result != 1) {
        // This is highly unlikely to fail with a valid key and message hash.
        fprintf(stderr, "Error: The signing operation failed.\n");
        secp256k1_context_destroy(ctx);
        return -3;
    }

    // Serialize the signature in compact format (64 bytes, R||S).
    secp256k1_ecdsa_signature_serialize_compact(ctx, signature, &sig);
    *signature_len = 64;

    // Clean up the context object to free memory.
    secp256k1_context_destroy(ctx);

    return 0; // Success
}

// Helper function to print a byte array in hex format.
void print_hex(const char* label, const unsigned char* data, size_t len) {
    printf("%s", label);
    for (size_t i = 0; i < len; ++i) {
        printf("%02x", data[i]);
    }
    printf("\n");
}

// --- Main function to demonstrate usage ---
int main() {
    // 1. Define a sample message and a sample private key.
    // IMPORTANT: This is a sample private key. NEVER use it for real applications.
    const char* message = "This is a test message for ECDSA signing.";
    const unsigned char private_key[32] = {
        0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, 0x88,
        0x99, 0xaa, 0xbb, 0xcc, 0xdd, 0xee, 0xff, 0x00,
        0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, 0x88,
        0x99, 0xaa, 0xbb, 0xcc, 0xdd, 0xee, 0xff, 0x00
    };

    // 2. Hash the message using SHA-256. ECDSA signs the hash, not the raw message.
    unsigned char message_hash[SHA256_DIGEST_LENGTH];
    SHA256((const unsigned char*)message, strlen(message), message_hash);

    // 3. Prepare output buffers for the signature.
    unsigned char signature[64];
    size_t signature_len;

    printf("--- ECDSA Signing Demo ---\n");
    print_hex("Private Key:  ", private_key, sizeof(private_key));
    printf("Message:      \"%s\"\n", message);
    print_hex("Message Hash: ", message_hash, sizeof(message_hash));
    printf("\n");

    // 4. Call the signing function.
    int ret = sign_message(message_hash, private_key, signature, &signature_len);

    // 5. Check the result and print the signature.
    if (ret == 0) {
        printf("Signing successful!\n");
        assert(signature_len == 64); // Compact signatures are always 64 bytes.
        print_hex("Signature (r||s): ", signature, signature_len);
    } else {
        fprintf(stderr, "Signing failed with error code %d\n", ret);
        return 1; // Indicate failure
    }

    return 0; // Indicate success
}