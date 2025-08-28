#include <stdio.h>
#include <string.h>
#include <assert.h>

// For SHA256 hashing
#include <openssl/sha.h>

// The primary library for ECDSA operations on the secp256k1 curve
#include <secp256k1.h>

// A helper function to print byte arrays in hex format
void print_hex(const unsigned char* data, size_t size) {
    for (size_t i = 0; i < size; i++) {
        printf("%02x", data[i]);
    }
    printf("\n");
}

/**
 * Fills a buffer with cryptographically secure random bytes.
 * This is crucial for generating a secure private key.
 * On Linux/macOS, we can read from /dev/urandom.
 * Returns 1 on success, 0 on failure.
 */
int fill_random(unsigned char* data, size_t size) {
    FILE* frand = fopen("/dev/urandom", "r");
    if (frand == NULL) {
        return 0;
    }
    size_t read_bytes = fread(data, 1, size, frand);
    fclose(frand);
    return read_bytes == size;
}

/**
 * Signs a given message hash using a given ECDSA signing key.
 *
 * Args:
 *   ctx: A pointer to a secp256k1 context object, initialized for signing.
 *   msg_hash: A 32-byte hash of the message to be signed.
 *   seckey: A 32-byte secret key.
 *   sig_out: A pointer to a signature structure where the output will be stored.
 *
 * Return:
 *   1 if the signature was created, 0 if the nonce generation failed.
 */
int sign(secp256k1_context* ctx, const unsigned char* msg_hash, const unsigned char* seckey, secp256k1_ecdsa_signature* sig_out) {
    // The secp256k1_ecdsa_sign function creates a non-deterministic signature
    // using a CSPRNG. It is the recommended way to sign.
    return secp256k1_ecdsa_sign(ctx, sig_out, msg_hash, seckey, NULL, NULL);
}


int main() {
    // =========================================================================
    // 1. Initialization
    // =========================================================================

    // secp256k1 context object, used to hold precomputed tables for performance.
    // We need to initialize it for both signing and verification.
    secp256k1_context* ctx = secp256k1_context_create(SECP256K1_CONTEXT_SIGN | SECP256K1_CONTEXT_VERIFY);
    if (ctx == NULL) {
        printf("Failed to create secp256k1 context\n");
        return 1;
    }

    // =========================================================================
    // 2. Key Generation (Equivalent to: sk = ecdsa.SigningKey.generate(...))
    // =========================================================================
    unsigned char seckey[32]; // This will hold our private key
    
    // It's essential to use a cryptographically secure random number generator.
    // We loop until we find a valid key. The probability of generating an
    // invalid key is astronomically low, but it's best practice to check.
    while (1) {
        if (!fill_random(seckey, sizeof(seckey))) {
            printf("Failed to generate random data for private key\n");
            secp256k1_context_destroy(ctx);
            return 1;
        }
        // secp256k1_ec_seckey_verify checks if the secret key is valid.
        if (secp256k1_ec_seckey_verify(ctx, seckey)) {
            break; // Valid key found
        }
    }

    printf("Generated Secret Key (sk): ");
    print_hex(seckey, sizeof(seckey));

    // =========================================================================
    // 3. Public Key Derivation (Equivalent to: vk = sk.get_verifying_key())
    // =========================================================================
    secp256k1_pubkey pubkey; // This will hold the public key

    // Derive the public key from the secret key.
    int return_val = secp256k1_ec_pubkey_create(ctx, &pubkey, seckey);
    assert(return_val); // This should never fail with a valid seckey

    // Serialize the public key into a compressed format (33 bytes).
    unsigned char serialized_pubkey[33];
    size_t pubkey_len = sizeof(serialized_pubkey);
    secp256k1_ec_pubkey_serialize(ctx, serialized_pubkey, &pubkey_len, &pubkey, SECP256K1_EC_COMPRESSED);
    
    printf("Derived Public Key (vk):  ");
    print_hex(serialized_pubkey, pubkey_len);

    // =========================================================================
    // 4. Signing (Equivalent to: signed_message = sign(b"helloworld", sk))
    // =========================================================================
    const char* message = "helloworld";
    unsigned char msg_hash[SHA256_DIGEST_LENGTH];

    // First, we must hash the message. ECDSA signs the hash, not the raw message.
    SHA256((const unsigned char*)message, strlen(message), msg_hash);
    
    printf("Message: %s\n", message);
    printf("Message Hash (SHA256):    ");
    print_hex(msg_hash, sizeof(msg_hash));

    // Create a signature structure
    secp256k1_ecdsa_signature sig;

    // Call our sign function
    return_val = sign(ctx, msg_hash, seckey, &sig);
    assert(return_val); // Should not fail

    // Serialize the signature to a compact 64-byte format (R and S values)
    unsigned char serialized_signature[64];
    secp256k1_ecdsa_signature_serialize_compact(ctx, serialized_signature, &sig);

    printf("Signature (compact):      ");
    print_hex(serialized_signature, sizeof(serialized_signature));

    // =========================================================================
    // 5. Verification (Equivalent to: vk.verify(signed_message, b"helloworld"))
    // =================================_========================================
    
    // The verification function takes the message hash and the public key.
    // Note: We use the non-serialized `sig` and `pubkey` objects here.
    // If you were receiving a signature from an external source, you would first
    // need to parse it from its serialized format.
    return_val = secp256k1_ecdsa_verify(ctx, &sig, msg_hash, &pubkey);

    if (return_val == 1) {
        printf("Verification Result: True\n");
    } else {
        printf("Verification Result: False\n");
    }

    // =========================================================================
    // 6. Cleanup
    // =========================================================================
    secp256k1_context_destroy(ctx);

    return 0;
}