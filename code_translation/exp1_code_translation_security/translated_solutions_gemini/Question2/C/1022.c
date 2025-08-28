#include <stdio.h>
#include <string.h>
#include <assert.h>

// For SHA256 hashing
#include <openssl/sha.h>

// For secp256k1 ECDSA operations
#include <secp256k1.h>

// A helper function to print byte arrays in hex format
void print_hex(const unsigned char* data, size_t size, const char* label) {
    printf("%s: ", label);
    for (size_t i = 0; i < size; ++i) {
        printf("%02x", data[i]);
    }
    printf("\n");
}

// Fills a buffer with cryptographically secure random bytes.
// Uses /dev/urandom on Unix-like systems.
// Returns 1 on success, 0 on failure.
int fill_random(unsigned char* data, size_t size) {
    FILE* fp = fopen("/dev/urandom", "rb");
    if (!fp) {
        fprintf(stderr, "Error: Could not open /dev/urandom for random data.\n");
        return 0;
    }
    if (fread(data, 1, size, fp) != size) {
        fprintf(stderr, "Error: Could not read %zu bytes from /dev/urandom.\n");
        fclose(fp);
        return 0;
    }
    fclose(fp);
    return 1;
}

int main() {
    // ------------------------------------------------------------------------
    // 1. Setup: Create a secp256k1 context object.
    // This is required for all library operations.
    // We create it with flags for both signing and verification.
    // ------------------------------------------------------------------------
    secp256k1_context* ctx = secp256k1_context_create(SECP256K1_CONTEXT_SIGN | SECP256K1_CONTEXT_VERIFY);
    if (ctx == NULL) {
        fprintf(stderr, "Failed to create secp256k1 context.\n");
        return 1;
    }

    // ------------------------------------------------------------------------
    // 2. Generate Keys (PrivateKey and PublicKey)
    // ------------------------------------------------------------------------
    unsigned char seckey[32];
    secp256k1_pubkey pubkey;

    // A private key is just a 32-byte random number.
    // We loop until we find a valid key. The probability of generating an
    // invalid key (0 or >= curve order) is astronomically low.
    while (1) {
        if (!fill_random(seckey, sizeof(seckey))) {
            fprintf(stderr, "Failed to generate random data for private key.\n");
            secp256k1_context_destroy(ctx);
            return 1;
        }
        // Verify that the secret key is valid.
        if (secp256k1_ec_seckey_verify(ctx, seckey)) {
            break;
        }
    }

    // Derive the public key from the secret key.
    if (!secp256k1_ec_pubkey_create(ctx, &pubkey, seckey)) {
        fprintf(stderr, "Failed to create public key.\n");
        secp256k1_context_destroy(ctx);
        return 1;
    }

    // For demonstration, let's serialize and print the keys.
    // Public keys can be serialized in compressed (33 bytes) or uncompressed (65 bytes) format.
    unsigned char serialized_pubkey[65];
    size_t pubkey_len = sizeof(serialized_pubkey);
    secp256k1_ec_pubkey_serialize(ctx, serialized_pubkey, &pubkey_len, &pubkey, SECP256K1_EC_UNCOMPRESSED);

    print_hex(seckey, sizeof(seckey), "Private Key");
    print_hex(serialized_pubkey, pubkey_len, "Public Key (Uncompressed)");


    // ------------------------------------------------------------------------
    // 3. Prepare the message and its hash
    // The Python library automatically hashes the message with SHA-256.
    // We must do this explicitly in C.
    // ------------------------------------------------------------------------
    const char *message = "My test message";
    unsigned char msg_hash[SHA256_DIGEST_LENGTH];

    SHA256((const unsigned char*)message, strlen(message), msg_hash);
    
    printf("\nMessage: \"%s\"\n", message);
    print_hex(msg_hash, sizeof(msg_hash), "Message Hash (SHA-256)");


    // ------------------------------------------------------------------------
    // 4. Generate Signature
    // ------------------------------------------------------------------------
    secp256k1_ecdsa_signature sig;

    if (!secp256k1_ecdsa_sign(ctx, &sig, msg_hash, seckey, NULL, NULL)) {
        fprintf(stderr, "Failed to sign message.\n");
        secp256k1_context_destroy(ctx);
        return 1;
    }

    // Serialize the signature to the standard DER format.
    unsigned char der_sig[72];
    size_t der_sig_len = sizeof(der_sig);
    if (!secp256k1_ecdsa_signature_serialize_der(ctx, der_sig, &der_sig_len, &sig)) {
        fprintf(stderr, "Failed to serialize signature.\n");
        secp256k1_context_destroy(ctx);
        return 1;
    }

    print_hex(der_sig, der_sig_len, "Signature (DER)");


    // ------------------------------------------------------------------------
    // 5. Verify if signature is valid
    // ------------------------------------------------------------------------
    // The verify function returns 1 for a valid signature, 0 for an invalid one.
    int is_signature_valid = secp256k1_ecdsa_verify(ctx, &sig, msg_hash, &pubkey);

    printf("\nVerifying signature...\n");
    printf("Signature is valid: %s\n", is_signature_valid ? "true" : "false");


    // ------------------------------------------------------------------------
    // 6. Cleanup
    // ------------------------------------------------------------------------
    secp256k1_context_destroy(ctx);

    return 0;
}