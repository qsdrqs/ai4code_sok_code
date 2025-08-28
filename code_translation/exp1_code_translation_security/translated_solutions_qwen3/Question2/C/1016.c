#include <openssl/sha.h>
#include <secp256k1.h>
#include <secp256k1_ecdsa.h>

/**
 * Signs a message using ECDSA with the SECP256k1 curve.
 *
 * @param message       Pointer to the message data to be signed.
 * @param message_len   Length of the message in bytes.
 * @param private_key   32-byte private key (big-endian, scalar value).
 * @param signature     Output buffer to store the 64-byte signature (r || s).
 *
 * @return              1 on success, 0 on failure.
 */
int sign_message(const unsigned char *message, size_t message_len,
                 const unsigned char *private_key, unsigned char *signature) {
    // Create a context for signing operations
    secp256k1_context *ctx = secp256k1_context_create(SECP256K1_CONTEXT_SIGN);
    if (ctx == NULL) {
        return 0;
    }

    // Step 1: Hash the message using SHA-256
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256(message, message_len, hash);

    // Step 2: Sign the hash using the private key
    secp256k1_ecdsa_signature sig;
    if (!secp256k1_ecdsa_sign(ctx, &sig, hash, private_key, NULL, NULL)) {
        secp256k1_context_destroy(ctx);
        return 0;
    }

    // Step 3: Serialize the signature into compact 64-byte format (r || s)
    if (!secp256k1_ecdsa_signature_serialize_compact(ctx, signature, &sig)) {
        secp256k1_context_destroy(ctx);
        return 0;
    }

    secp256k1_context_destroy(ctx);
    return 1;
}