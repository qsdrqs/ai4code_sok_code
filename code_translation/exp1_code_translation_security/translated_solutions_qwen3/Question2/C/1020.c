#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <openssl/bn.h>
#include <stdio.h>
#include <stdlib.h>

int sign_message(EC_KEY *key, const char *message, size_t message_len) {
    // Step 1: Compute SHA-1 hash of the message
    unsigned char hash[SHA_DIGEST_LENGTH];
    SHA1((const unsigned char *)message, message_len, hash);

    // Step 2: Sign the hash using the private key
    ECDSA_SIG *sig = ECDSA_do_sign(hash, SHA_DIGEST_LENGTH, key);
    if (!sig) {
        return 0;
    }

    // Step 3: Convert the signature to 64-byte format (r || s)
    unsigned char *sig_bytes = OPENSSL_malloc(64);
    if (!sig_bytes) {
        ECDSA_SIG_free(sig);
        return 0;
    }

    const BIGNUM *r, *s;
    ECDSA_SIG_get0(sig, &r, &s);

    // Convert r and s to 32-byte big-endian format
    BN_bn2binpad(r, sig_bytes, 32);
    BN_bn2binpad(s, sig_bytes + 32, 32);

    ECDSA_SIG_free(sig);

    // Step 4: Reconstruct ECDSA_SIG from the 64-byte signature
    ECDSA_SIG *sig_parsed = ECDSA_SIG_new();
    BIGNUM *r_parsed = BN_new();
    BIGNUM *s_parsed = BN_new();

    BN_bin2bn(sig_bytes, 32, r_parsed);
    BN_bin2bn(sig_bytes + 32, 32, s_parsed);
    ECDSA_SIG_set0(sig_parsed, r_parsed, s_parsed);

    OPENSSL_free(sig_bytes);

    // Step 5: Verify the signature
    int result = ECDSA_do_verify(hash, SHA_DIGEST_LENGTH, sig_parsed, key);

    // Clean up
    ECDSA_SIG_free(sig_parsed);
    BN_free(r_parsed);
    BN_free(s_parsed);

    return (result == 1) ? 1 : 0;
}

int main() {
    // Step 1: Initialize OpenSSL context
    EC_KEY *key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!key) {
        fprintf(stderr, "Failed to create EC key\n");
        return 1;
    }

    // Step 2: Generate the key pair
    if (!EC_KEY_generate_key(key)) {
        fprintf(stderr, "Failed to generate EC key\n");
        EC_KEY_free(key);
        return 1;
    }

    // Step 3: Sign and verify the message ".."
    int result = sign_message(key, "..", 2);
    printf("%d\n", result);  // Should print 1 (True)

    // Step 4: Clean up
    EC_KEY_free(key);
    return 0;
}