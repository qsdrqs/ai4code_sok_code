#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/sha.h>
#include <openssl/bn.h>
#include <openssl/obj_mac.h>
#include <stdio.h>
#include <stdlib.h>

int main() {
    // Initialize OpenSSL (optional in newer versions, but safe to include)
    OPENSSL_init_crypto(0, NULL);

    // Generate ECDSA key pair using SECP256k1 curve
    EC_KEY *key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!key) {
        fprintf(stderr, "Failed to create EC_KEY\n");
        return 1;
    }

    if (!EC_KEY_generate_key(key)) {
        fprintf(stderr, "Failed to generate ECDSA key\n");
        EC_KEY_free(key);
        return 1;
    }

    // Message to sign
    const char *message = "helloworld";
    size_t message_len = 10;

    // Compute SHA-256 hash of the message
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256_CTX sha256;
    SHA256_Init(&sha256);
    SHA256_Update(&sha256, message, message_len);
    SHA256_Final(hash, &sha256);

    // Sign the hash
    ECDSA_SIG *sig = ECDSA_do_sign(hash, SHA256_DIGEST_LENGTH, key);
    if (!sig) {
        fprintf(stderr, "Failed to sign message\n");
        EC_KEY_free(key);
        return 1;
    }

    // Convert ECDSA_SIG to 64-byte raw format (r || s)
    unsigned char sig_bytes[64];
    BN_bn2binpad(sig->r, sig_bytes, 32);
    BN_bn2binpad(sig->s, sig_bytes + 32, 32);

    // Reconstruct ECDSA_SIG from raw bytes for verification
    BIGNUM *r = BN_bin2bn(sig_bytes, 32, NULL);
    BIGNUM *s = BN_bin2bn(sig_bytes + 32, 32, NULL);
    ECDSA_SIG *sig2 = ECDSA_SIG_new();
    ECDSA_SIG_set0(sig2, r, s);

    // Verify the signature
    int verified = ECDSA_do_verify(hash, SHA256_DIGEST_LENGTH, sig2, key);
    printf("%d\n", verified); // Should print 1 (success)

    // Clean up
    ECDSA_SIG_free(sig);
    ECDSA_SIG_free(sig2);
    EC_KEY_free(key);

    return 0;
}