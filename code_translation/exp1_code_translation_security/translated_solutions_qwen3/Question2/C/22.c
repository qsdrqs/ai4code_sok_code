#include <secp256k1.h>
#include <openssl/sha.h>
#include <string.h>
#include <stdio.h>

/**
 * @brief Signs a message using ECDSA with the SECP256k1 curve.
 *
 * This function:
 * 1. Generates a random private key.
 * 2. Computes the SHA-1 hash of the message.
 * 3. Pads the hash to 32 bytes with leading zeros.
 * 4. Signs the padded hash using the private key.
 * 5. Serializes the signature in DER format.
 *
 * @param message Pointer to the message data.
 * @param message_len Length of the message in bytes.
 */
void myfunction(const unsigned char *message, size_t message_len) {
    // Create a context for signing operations
    secp256k1_context* ctx = secp256k1_context_create(SECP256K1_CONTEXT_SIGN);
    if (!ctx) {
        // Handle context creation failure
        return;
    }

    // Generate a valid private key
    unsigned char seckey[32];
    do {
        // Generate 32 random bytes from /dev/urandom
        FILE *rand = fopen("/dev/urandom", "rb");
        if (!rand) {
            secp256k1_context_destroy(ctx);
            return;
        }
        fread(seckey, 1, 32, rand);
        fclose(rand);
    } while (secp256k1_ec_seckey_verify(ctx, seckey) != 1);

    // Compute SHA-1 hash of the message
    unsigned char hash[SHA_DIGEST_LENGTH]; // 20 bytes
    SHA1(message, message_len, hash);

    // Pad the hash to 32 bytes with leading zeros
    unsigned char padded_hash[32];
    memset(padded_hash, 0, sizeof(padded_hash));
    memcpy(padded_hash + 12, hash, SHA_DIGEST_LENGTH); // 32 - 20 = 12

    // Sign the padded hash
    secp256k1_ecdsa_signature sig;
    if (!secp256k1_ecdsa_sign(ctx, &sig, padded_hash, seckey, NULL, NULL)) {
        // Handle signing failure
        secp256k1_context_destroy(ctx);
        return;
    }

    // Serialize the signature to DER format
    unsigned char der_signature[72]; // Max DER size for secp256k1
    size_t der_sig_len = sizeof(der_signature);
    if (!secp256k1_ecdsa_signature_serialize_der(ctx, der_signature, &der_sig_len, &sig)) {
        // Handle serialization failure
        secp256k1_context_destroy(ctx);
        return;
    }

    // At this point, der_signature contains the DER-encoded signature
    // You can return it, print it, or store it as needed

    // Clean up
    secp256k1_context_destroy(ctx);
}