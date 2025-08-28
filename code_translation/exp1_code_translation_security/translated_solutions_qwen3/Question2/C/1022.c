#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main() {
    // Step 1: Generate EC key pair using secp256k1 curve
    EC_KEY *key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!key) {
        fprintf(stderr, "Failed to create EC key\n");
        return 1;
    }

    if (!EC_KEY_generate_key(key)) {
        fprintf(stderr, "Failed to generate EC key\n");
        EC_KEY_free(key);
        return 1;
    }

    // Step 2: Define the message to be signed
    const char *message = "My test message";
    size_t message_len = strlen(message);

    // Step 3: Hash the message using SHA-256
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256((const unsigned char *)message, message_len, hash);

    // Step 4: Sign the hash
    unsigned int signature_len = ECDSA_size(key);
    unsigned char *signature = malloc(signature_len);
    if (!signature) {
        fprintf(stderr, "Failed to allocate memory for signature\n");
        EC_KEY_free(key);
        return 1;
    }

    if (ECDSA_sign(NID_sha256, hash, SHA256_DIGEST_LENGTH, signature, &signature_len, key) != 1) {
        fprintf(stderr, "Failed to sign message\n");
        free(signature);
        EC_KEY_free(key);
        return 1;
    }

    // Step 5: Verify the signature
    int verify_result = ECDSA_verify(NID_sha256, hash, SHA256_DIGEST_LENGTH, signature, signature_len, key);
    printf("%d\n", verify_result == 1 ? 1 : 0);

    // Step 6: Clean up
    free(signature);
    EC_KEY_free(key);

    return 0;
}