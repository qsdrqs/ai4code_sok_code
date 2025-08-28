#include <secp256k1.h>
#include <secp256k1_ecdsa.h>
#include <openssl/evp.h>
#include <openssl/bn.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Computes SHA3-256 hash of the given message
unsigned char* sha3_256_hash(const char* msg, size_t len) {
    unsigned char* hash = malloc(32);
    if (!hash) return NULL;

    EVP_MD_CTX* ctx = EVP_MD_CTX_new();
    if (!ctx) {
        free(hash);
        return NULL;
    }

    if (EVP_DigestInit_ex(ctx, EVP_sha3_256(), NULL) != 1) {
        EVP_MD_CTX_free(ctx);
        free(hash);
        return NULL;
    }

    EVP_DigestUpdate(ctx, msg, len);
    if (EVP_DigestFinal_ex(ctx, hash, NULL) != 1) {
        EVP_MD_CTX_free(ctx);
        free(hash);
        return NULL;
    }

    EVP_MD_CTX_free(ctx);
    return hash;
}

// Signs a message hash using ECDSA with secp256k1 and returns r and s
int sign_ecdsa(secp256k1_context* ctx, const unsigned char* msg_hash, const unsigned char* private_key, unsigned char* r, unsigned char* s) {
    secp256k1_ecdsa_signature sig;
    if (!secp256k1_ecdsa_sign(ctx, &sig, msg_hash, private_key, NULL, NULL)) {
        return 0;
    }

    unsigned char compact_sig[64];
    size_t compact_len = 64;
    if (!secp256k1_ecdsa_signature_serialize_compact(ctx, compact_sig, &sig, &compact_len)) {
        return 0;
    }

    memcpy(r, compact_sig, 32);
    memcpy(s, compact_sig + 32, 32);
    return 1;
}

// Verifies an ECDSA signature
int verify_ecdsa(secp256k1_context* ctx, const unsigned char* msg_hash, const unsigned char* sig_r, const unsigned char* sig_s, const unsigned char* public_key, size_t pubkey_len) {
    secp256k1_pubkey pubkey;
    if (!secp256k1_pubkey_parse(ctx, &pubkey, public_key, pubkey_len)) {
        return 0;
    }

    unsigned char compact_sig[64];
    memcpy(compact_sig, sig_r, 32);
    memcpy(compact_sig + 32, sig_s, 32);

    secp256k1_ecdsa_signature sig;
    if (!secp256k1_ecdsa_signature_parse_compact(ctx, &sig, compact_sig)) {
        return 0;
    }

    return secp256k1_ecdsa_verify(ctx, &sig, msg_hash, &pubkey);
}

int main() {
    const char* msg = "Message for ECDSA signing";
    size_t msg_len = strlen(msg);

    // Generate private key using OpenSSL BIGNUM
    BIGNUM* order = BN_new();
    BN_hex2bn(&order, "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEBAAEDCE6AF48A03BBFD25E8CD0364141");
    BIGNUM* priv_bn = BN_new();
    BN_rand_range(priv_bn, order);
    if (BN_is_zero(priv_bn)) {
        BN_add_word(priv_bn, 1); // Ensure private key is not zero
    }

    // Convert private key to 32-byte array (big-endian)
    unsigned char priv_key[32];
    BN_bn2binpad(priv_bn, priv_key, 32);
    BN_free(order);
    BN_free(priv_bn);

    // Create secp256k1 context
    secp256k1_context* ctx = secp256k1_context_create(SECP256K1_CONTEXT_SIGN | SECP256K1_CONTEXT_VERIFY);

    // Generate public key
    secp256k1_pubkey pubkey;
    if (!secp256k1_ec_pubkey_create(ctx, &pubkey, priv_key)) {
        fprintf(stderr, "Failed to create public key\n");
        secp256k1_context_destroy(ctx);
        return 1;
    }

    unsigned char pub_key[65];
    size_t pub_key_len = 65;
    secp256k1_pubkey_serialize(ctx, pub_key, &pub_key_len, &pubkey, SECP256K1_EC_UNCOMPRESSED);

    // Compute SHA3-256 hash
    unsigned char* hash = sha3_256_hash(msg, msg_len);
    if (!hash) {
        fprintf(stderr, "Failed to compute hash\n");
        secp256k1_context_destroy(ctx);
        return 1;
    }

    // Sign
    unsigned char r[32], s[32];
    if (!sign_ecdsa(ctx, hash, priv_key, r, s)) {
        fprintf(stderr, "Signing failed\n");
        free(hash);
        secp256k1_context_destroy(ctx);
        return 1;
    }

    // Optional: Verify
    if (!verify_ecdsa(ctx, hash, r, s, pub_key, pub_key_len)) {
        fprintf(stderr, "Verification failed\n");
    }

    // Print results
    printf("Message: %s\n", msg);
    printf("Private key: ");
    for (int i = 0; i < 32; i++) {
        printf("%02x", priv_key[i]);
    }
    printf("\nSignature: r=");
    for (int i = 0; i < 32; i++) {
        printf("%02x", r[i]);
    }
    printf(", s=");
    for (int i = 0; i < 32; i++) {
        printf("%02x", s[i]);
    }
    printf("\n");

    // Cleanup
    free(hash);
    secp256k1_context_destroy(ctx);
    return 0;
}