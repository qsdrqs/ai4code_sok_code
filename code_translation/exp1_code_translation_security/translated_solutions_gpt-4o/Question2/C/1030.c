#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/sha.h>
#include <secp256k1.h>
#include <secp256k1_recovery.h>

// Function to compute SHA3-256 hash
void sha3_256Hash(const char *msg, unsigned char *hash) {
    SHA3_256((unsigned char *)msg, strlen(msg), hash);
}

// Function to sign a message using ECDSA with secp256k1
int signECDSAsecp256k1(const char *msg, const unsigned char *privKey, secp256k1_ecdsa_signature *signature, secp256k1_context *ctx) {
    unsigned char hash[32];
    sha3_256Hash(msg, hash);

    if (!secp256k1_ecdsa_sign(ctx, signature, hash, privKey, NULL, NULL)) {
        return 0; // Signing failed
    }
    return 1; // Signing succeeded
}

// Function to verify a signature using ECDSA with secp256k1
int verifyECDSAsecp256k1(const char *msg, const secp256k1_ecdsa_signature *signature, const secp256k1_pubkey *pubKey, secp256k1_context *ctx) {
    unsigned char hash[32];
    sha3_256Hash(msg, hash);

    if (!secp256k1_ecdsa_verify(ctx, signature, hash, pubKey)) {
        return 0; // Verification failed
    }
    return 1; // Verification succeeded
}

int main() {
    const char *msg = "Message for ECDSA signing";
    unsigned char privKey[32];
    secp256k1_ecdsa_signature signature;
    secp256k1_context *ctx;

    // Create a context for signing and verification
    ctx = secp256k1_context_create(SECP256K1_CONTEXT_SIGN | SECP256K1_CONTEXT_VERIFY);

    // Generate a random private key
    FILE *fp = fopen("/dev/urandom", "r");
    fread(privKey, 1, 32, fp);
    fclose(fp);

    // Sign the message
    if (!signECDSAsecp256k1(msg, privKey, &signature, ctx)) {
        printf("Failed to sign message\n");
        return 1;
    }

    // Print the message, private key, and signature
    printf("Message: %s\n", msg);
    printf("Private key: ");
    for (int i = 0; i < 32; i++) {
        printf("%02x", privKey[i]);
    }
    printf("\nSignature: ");
    for (int i = 0; i < 64; i++) {
        printf("%02x", signature.data[i]);
    }
    printf("\n");

    // Clean up
    secp256k1_context_destroy(ctx);

    return 0;
}