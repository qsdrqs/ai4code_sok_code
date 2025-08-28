#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

// Dependency: libsecp256k1 for ECDSA operations
#include <secp256k1.h>

// Dependency: OpenSSL's libcrypto for SHA256 hashing
#include <openssl/sha.h>

/**
 * @brief A structure to hold the result of the signing operation.
 *
 * In C, we cannot directly return a dynamically sized array like in Python.
 * This struct allows us to return both a pointer to the signature data
 * and its length, along with an error status.
 */
typedef struct {
    unsigned char* data; // Pointer to the signature bytes
    size_t len;          // Length of the signature data (usually 64 bytes)
    int error;           // 0 on success, 1 on failure
} SignatureResult;

/**
 * @brief Signs a given message using an ECDSA private key (SECP256k1).
 *
 * This function replicates the behavior of the Python `sign_message` function.
 * It takes a message and a 32-byte private key, computes the SHA256 hash of the
 * message, and then signs the hash using the private key.
 *
 * @param message A pointer to the message data to be signed.
 * @param message_len The length of the message data in bytes.
 * @param private_key A pointer to the 32-byte private key.
 * @return A SignatureResult struct. The caller is responsible for freeing
 *         the `data` member of the struct if the operation was successful.
 */
SignatureResult sign_message(const unsigned char* message, size_t message_len, const unsigned char* private_key) {
    SignatureResult result = { .data = NULL, .len = 0, .error = 1 }; // Initialize to failure state

    // 1. Create a secp256k1 context object.
    // This object is required for all library operations. We create it for signing.
    secp256k1_context* ctx = secp256k1_context_create(SECP256K1_CONTEXT_SIGN);
    if (ctx == NULL) {
        fprintf(stderr, "Failed to create secp256k1 context.\n");
        return result;
    }

    // 2. Verify the private key is valid.
    // This is a crucial security step. A valid private key is a 32-byte non-zero
    // scalar that is less than the group order.
    if (!secp256k1_ec_seckey_verify(ctx, private_key)) {
        fprintf(stderr, "Invalid private key.\n");
        secp256k1_context_destroy(ctx);
        return result;
    }

    // 3. Hash the message.
    // ECDSA signs a 32-byte hash, not the raw message. We use SHA256.
    unsigned char msg_hash[SHA256_DIGEST_LENGTH];
    SHA256(message, message_len, msg_hash);

    // 4. Create the ECDSA signature.
    secp256k1_ecdsa_signature sig;
    if (!secp256k1_ecdsa_sign(ctx, &sig, msg_hash, private_key, NULL, NULL)) {
        fprintf(stderr, "Failed to create signature.\n");
        secp256k1_context_destroy(ctx);
        return result;
    }

    // 5. Serialize the signature to a compact 64-byte format (r, s).
    // This is the format most commonly used and expected.
    unsigned char* serialized_signature = (unsigned char*)malloc(64);
    if (serialized_signature == NULL) {
        fprintf(stderr, "Memory allocation failed for signature.\n");
        secp256k1_context_destroy(ctx);
        return result;
    }
    secp256k1_ecdsa_signature_serialize_compact(ctx, serialized_signature, &sig);

    // 6. Populate the result struct for a successful operation.
    result.data = serialized_signature;
    result.len = 64;
    result.error = 0; // Success

    // 7. Clean up the context.
    secp256k1_context_destroy(ctx);

    return result;
}

// --- Example Usage ---
int main() {
    // A sample 32-byte private key (DO NOT USE FOR REAL APPLICATIONS)
    unsigned char private_key[32] = {
        0x11, 0x11, 0x11, 0x11, 0x11, 0x11, 0x11, 0x11,
        0x11, 0x11, 0x11, 0x11, 0x11, 0x11, 0x11, 0x11,
        0x11, 0x11, 0x11, 0x11, 0x11, 0x11, 0x11, 0x11,
        0x11, 0x11, 0x11, 0x11, 0x11, 0x11, 0x11, 0x11
    };

    // The message to be signed
    const char* message_str = "This is a test message for ECDSA signing.";
    const unsigned char* message = (const unsigned char*)message_str;
    size_t message_len = strlen(message_str);

    printf("Message: %s\n", message_str);
    printf("Private Key: ");
    for(int i = 0; i < 32; ++i) printf("%02x", private_key[i]);
    printf("\n\n");

    // Call the signing function
    SignatureResult signature_result = sign_message(message, message_len, private_key);

    if (signature_result.error) {
        fprintf(stderr, "Signing failed.\n");
        return 1;
    }

    // Print the resulting signature
    printf("Signature (64 bytes):\n");
    for (size_t i = 0; i < signature_result.len; ++i) {
        printf("%02x", signature_result.data[i]);
    }
    printf("\n");

    // IMPORTANT: Free the memory allocated by the sign_message function
    free(signature_result.data);

    return 0;
}