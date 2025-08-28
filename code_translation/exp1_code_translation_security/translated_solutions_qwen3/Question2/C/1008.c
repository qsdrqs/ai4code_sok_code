#include <secp256k1.h>
#include <secp256k1_ecdsa.h>
#include <openssl/sha.h>  // For SHA1
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

/**
 * Signs a message using ECDSA with the SECP256k1 curve.
 *
 * @param message The message to sign (UTF-8 encoded string).
 * @param private_key_str A 32-byte private key string.
 * @return A 64-byte signature (r and s concatenated), or NULL on failure.
 */
unsigned char* sign_message(const char* message, const char* private_key_str) {
    // Create a context for signing
    secp256k1_context* ctx = secp256k1_context_create(SECP256K1_CONTEXT_SIGN);
    if (!ctx) {
        return NULL;
    }

    // Convert the private key string to a 32-byte array
    unsigned char private_key[32];
    memcpy(private_key, private_key_str, 32);

    // Validate the private key
    if (!secp256k1_ec_seckey_verify(ctx, private_key)) {
        secp256k1_context_destroy(ctx);
        return NULL;
    }

    // Compute SHA1 hash of the message
    unsigned char msg32[32] = {0};  // Initialize to zero
    SHA1((const unsigned char*)message, strlen(message), msg32 + 12);  // Copy SHA1 hash to the last 20 bytes

    // Sign the message
    secp256k1_ecdsa_signature signature;
    if (!secp256k1_ecdsa_sign(ctx, &signature, msg32, private_key, NULL, NULL)) {
        secp256k1_context_destroy(ctx);
        return NULL;
    }

    // Serialize the signature in compact format (64 bytes)
    unsigned char* sig_serial = malloc(64);
    if (!sig_serial) {
        secp256k1_context_destroy(ctx);
        return NULL;
    }

    secp256k1_ecdsa_signature_serialize_compact(ctx, sig_serial, &signature);

    secp256k1_context_destroy(ctx);
    return sig_serial;
}

/**
 * Main function to test the sign_message function.
 */
int main() {
    const char* message = "hello world";
    const char* private_key_str = "12345678901234567890123456789012";

    unsigned char* signature = sign_message(message, private_key_str);
    if (signature) {
        // Print the signature in hexadecimal format
        for (int i = 0; i < 64; i++) {
            printf("%02x", signature[i]);
        }
        printf("\n");
        free(signature);
    } else {
        printf("Failed to sign message.\n");
    }

    return 0;
}