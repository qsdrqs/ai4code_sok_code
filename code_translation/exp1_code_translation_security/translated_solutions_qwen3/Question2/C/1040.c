/**
 * Signs a message using ECDSA with the SECP256k1 curve.
 *
 * @param message       Pointer to the message data to be signed.
 * @param message_len   Length of the message in bytes.
 * @param private_key   32-byte private key.
 * @param signature     Output buffer for the 64-byte signature.
 * @return              0 on success, -1 on failure.
 */
int sign_message(const unsigned char *message, size_t message_len,
                 const unsigned char *private_key, unsigned char *signature) {
    // Create a context for signing
    secp256k1_context* ctx = secp256k1_context_create(SECP256K1_CONTEXT_SIGN);
    if (!ctx) {
        return -1; // Context creation failed
    }

    // Compute SHA-256 hash of the message
    unsigned char hash[32];
    SHA256(message, message_len, hash);

    // Validate the private key
    if (!secp256k1_ec_seckey_verify(ctx, private_key)) {
        secp256k1_context_destroy(ctx);
        return -1; // Invalid private key
    }

    // Create a signature object
    secp256k1_ecdsa_signature sig;
    if (!secp256k1_ecdsa_sign(ctx, &sig, hash, private_key, NULL, NULL)) {
        secp256k1_context_destroy(ctx);
        return -1; // Signing failed
    }

    // Serialize the signature in compact format (64 bytes: r + s)
    if (!secp256k1_ecdsa_signature_serialize_compact(ctx, signature, &sig)) {
        secp256k1_context_destroy(ctx);
        return -1; // Serialization failed
    }

    secp256k1_context_destroy(ctx);
    return 0; // Success
}