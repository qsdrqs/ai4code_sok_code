#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdint.h>

/*
 ============================================================================
 ===                 DEPENDENCIES (Stubs / Headers)                     ===
 ============================================================================
  This section simulates the necessary libraries. In a real project, you
  would include the actual headers for libsecp256k1 and a SHA3 library.
*/

// --- Dependency 1: libsecp256k1 (https://github.com/bitcoin-core/secp256k1) ---
// Opaque data structures
typedef struct secp256k1_context_struct secp256k1_context;
typedef struct { unsigned char data[64]; } secp256k1_pubkey;
typedef struct { unsigned char data[64]; } secp256k1_ecdsa_signature;

// Context creation flags
#define SECP256K1_CONTEXT_SIGN   (1 << 0)
#define SECP256K1_CONTEXT_VERIFY (1 << 1)

// Function prototypes from libsecp256k1
secp256k1_context* secp256k1_context_create(unsigned int flags);
void secp256k1_context_destroy(secp256k1_context* ctx);
int secp256k1_ec_seckey_verify(const secp256k1_context* ctx, const unsigned char *seckey);
int secp256k1_ec_pubkey_create(const secp256k1_context* ctx, secp256k1_pubkey *pubkey, const unsigned char *seckey);
int secp256k1_ecdsa_sign(const secp256k1_context* ctx, secp256k1_ecdsa_signature *sig, const unsigned char *msghash32, const unsigned char *seckey, secp256k1_nonce_function noncefp, const void *ndata);
int secp256k1_ecdsa_verify(const secp256k1_context* ctx, const secp256k1_ecdsa_signature *sig, const unsigned char *msghash32, const secp256k1_pubkey *pubkey);
int secp256k1_ecdsa_signature_serialize_compact(const secp256k1_context* ctx, unsigned char *output64, const secp256k1_ecdsa_signature *sig);
int secp256k1_ecdsa_signature_parse_compact(const secp256k1_context* ctx, secp256k1_ecdsa_signature* sig, const unsigned char *input64);

// --- Dependency 2: SHA3-256 Hashing Library ---
// A typical function signature for a SHA3-256 implementation.
void sha3_256(const uint8_t *message, size_t message_len, uint8_t *digest);

// --- Dependency 3: Cryptographically Secure Random Number Generator ---
// A function to generate secure random bytes (e.g., by reading from /dev/urandom on Linux).
// Returns 1 on success, 0 on failure.
int fill_random(unsigned char *data, size_t len);


/*
 ============================================================================
 ===                  HELPER FUNCTIONS                                    ===
 ============================================================================
*/

// Helper to print a byte array in hexadecimal format.
void print_hex(const char* label, const unsigned char* data, size_t len) {
    printf("%s", label);
    for (size_t i = 0; i < len; ++i) {
        printf("%02x", data[i]);
    }
    printf("\n");
}

// Helper to print the r and s components of a signature.
void print_signature(const unsigned char* sig64) {
    printf("Signature: r=0x");
    for (int i = 0; i < 32; ++i) printf("%02x", sig64[i]);
    printf(", s=0x");
    for (int i = 32; i < 64; ++i) printf("%02x", sig64[i]);
    printf("\n");
}


/*
 ============================================================================
 ===                  PYTHON-TO-C TRANSLATED FUNCTIONS                    ===
 ============================================================================
*/

/**
 * @brief Hashes a message using SHA3-256.
 * Corresponds to Python's `sha3_256Hash`.
 * @param msg The input message string.
 * @param hash_out A 32-byte buffer to store the resulting hash.
 */
void sha3_256Hash(const char* msg, unsigned char* hash_out) {
    // In C, strings are already byte arrays. We use strlen for the length.
    sha3_256((const uint8_t*)msg, strlen(msg), hash_out);
}

/**
 * @brief Signs a message using ECDSA with secp256k1.
 * Corresponds to Python's `signECDSAsecp256k1`.
 * @param ctx A valid secp256k1 context.
 * @param msg The message string to sign.
 * @param privKey A 32-byte private key.
 * @param signature_out A 64-byte buffer to store the compact signature (r, s).
 * @return 1 on success, 0 on failure.
 */
int signECDSAsecp256k1(secp256k1_context* ctx, const char* msg, const unsigned char* privKey, unsigned char* signature_out) {
    unsigned char msgHash[32];
    sha3_256Hash(msg, msgHash);

    secp256k1_ecdsa_signature sig;
    if (!secp256k1_ecdsa_sign(ctx, &sig, msgHash, privKey, NULL, NULL)) {
        return 0; // Signing failed
    }

    secp256k1_ecdsa_signature_serialize_compact(ctx, signature_out, &sig);
    return 1;
}

/**
 * @brief Verifies an ECDSA secp256k1 signature.
 * Corresponds to Python's `verifyECDSAsecp256k1`.
 * @param ctx A valid secp256k1 context.
 * @param msg The message string that was signed.
 * @param signature_in A 64-byte compact signature (r, s).
 * @param pubKey The public key to use for verification.
 * @return 1 if the signature is valid, 0 otherwise.
 */
int verifyECDSAsecp256k1(secp256k1_context* ctx, const char* msg, const unsigned char* signature_in, const secp256k1_pubkey* pubKey) {
    unsigned char msgHash[32];
    sha3_256Hash(msg, msgHash);

    secp256k1_ecdsa_signature sig;
    if (!secp256k1_ecdsa_signature_parse_compact(ctx, &sig, signature_in)) {
        return 0; // Failed to parse signature
    }

    return secp256k1_ecdsa_verify(ctx, &sig, msgHash, pubKey);
}


/*
 ============================================================================
 ===                  MAIN EXECUTION BLOCK                                ===
 ============================================================================
*/

int main() {
    // Create a secp256k1 context for signing and verification.
    secp256k1_context* ctx = secp256k1_context_create(SECP256K1_CONTEXT_SIGN | SECP256K1_CONTEXT_VERIFY);
    if (ctx == NULL) {
        fprintf(stderr, "Failed to create secp256k1 context.\n");
        return 1;
    }

    // --- ECDSA sign message (using the curve secp256k1 + SHA3-256) ---
    const char* msg = "Message for ECDSA signing";

    // --- Generate a valid private key ---
    // Corresponds to `privKey = secrets.randbelow(secp256k1_generator.order())`
    unsigned char privKey[32];
    do {
        if (!fill_random(privKey, sizeof(privKey))) {
            fprintf(stderr, "Failed to generate random data for private key.\n");
            secp256k1_context_destroy(ctx);
            return 1;
        }
    } while (!secp256k1_ec_seckey_verify(ctx, privKey)); // Loop until a valid key is found

    // --- Derive the public key from the private key ---
    // This is needed for verification.
    secp256k1_pubkey pubKey;
    if (!secp256k1_ec_pubkey_create(ctx, &pubKey, privKey)) {
        fprintf(stderr, "Failed to derive public key.\n");
        secp256k1_context_destroy(ctx);
        return 1;
    }

    // --- Sign the message ---
    unsigned char signature[64];
    if (!signECDSAsecp256k1(ctx, msg, privKey, signature)) {
        fprintf(stderr, "Failed to sign the message.\n");
        secp256k1_context_destroy(ctx);
        return 1;
    }

    // --- Print results ---
    printf("Message: %s\n", msg);
    print_hex("Private key: 0x", privKey, sizeof(privKey));
    print_signature(signature);

    // --- Verify the signature ---
    int isValid = verifyECDSAsecp256k1(ctx, msg, signature, &pubKey);
    printf("\nVerification: %s\n", isValid ? "SUCCESS" : "FAILED");

    // --- Clean up ---
    secp256k1_context_destroy(ctx);

    return 0;
}


/*
 ============================================================================
 ===          STUB IMPLEMENTATIONS OF DEPENDENCIES FOR TESTING            ===
 ============================================================================
  NOTE: These are NON-FUNCTIONAL and NON-SECURE stubs for demonstration.
  In a real application, you must link against the actual libraries.
*/

// Stub for libsecp256k1 functions (to allow compilation without the library)
// These stubs are simplified and do not represent the real library's logic.
secp256k1_context* secp256k1_context_create(unsigned int flags) { return (secp256k1_context*)malloc(1); }
void secp256k1_context_destroy(secp256k1_context* ctx) { free(ctx); }
int secp256k1_ec_seckey_verify(const secp256k1_context* ctx, const unsigned char *seckey) { return 1; /* Assume valid */ }
int secp256k1_ec_pubkey_create(const secp256k1_context* ctx, secp256k1_pubkey *pubkey, const unsigned char *seckey) { memset(pubkey->data, 0x02, sizeof(pubkey->data)); return 1; }
int secp256k1_ecdsa_sign(const secp256k1_context* ctx, secp256k1_ecdsa_signature *sig, const unsigned char *msghash32, const unsigned char *seckey, void* noncefp, const void *ndata) { memset(sig->data, 0xAA, sizeof(sig->data)); return 1; }
int secp256k1_ecdsa_verify(const secp256k1_context* ctx, const secp256k1_ecdsa_signature *sig, const unsigned char *msghash32, const secp256k1_pubkey *pubkey) { return 1; /* Assume valid */ }
int secp256k1_ecdsa_signature_serialize_compact(const secp256k1_context* ctx, unsigned char *output64, const secp256k1_ecdsa_signature *sig) { memcpy(output64, sig->data, 64); return 1; }
int secp256k1_ecdsa_signature_parse_compact(const secp256k1_context* ctx, secp256k1_ecdsa_signature* sig, const unsigned char *input64) { memcpy(sig->data, input64, 64); return 1; }

// Stub for SHA3-256
void sha3_256(const uint8_t *message, size_t message_len, uint8_t *digest) {
    // This is a dummy hash. A real implementation is required.
    for(int i=0; i<32; ++i) digest[i] = (uint8_t)(message[0] + i);
}

// Stub for random number generation
int fill_random(unsigned char *data, size_t len) {
    // WARNING: This is NOT cryptographically secure. For demonstration only.
    // Use /dev/urandom on Linux, CryptGenRandom on Windows, or a library like OpenSSL's RAND_bytes.
    FILE *f = fopen("/dev/urandom", "rb");
    if (f) {
        fread(data, 1, len, f);
        fclose(f);
        return 1;
    }
    // Fallback for systems without /dev/urandom (like basic Windows)
    for (size_t i = 0; i < len; ++i) data[i] = rand();
    return 1;
}