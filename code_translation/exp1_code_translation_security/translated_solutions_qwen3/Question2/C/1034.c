#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main() {
    // Step 1: Generate a private key using the secp256k1 curve
    EC_KEY *key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!key) {
        fprintf(stderr, "Failed to create EC_KEY\n");
        return 1;
    }

    if (!EC_KEY_generate_key(key)) {
        fprintf(stderr, "Failed to generate EC key\n");
        EC_KEY_free(key);
        return 1;
    }

    // Step 2: Define the message to be signed
    const char *message = "Test message";
    size_t message_len = strlen(message);

    // Step 3: Compute SHA-256 hash of the message
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256_CTX sha256;
    SHA256_Init(&sha256);
    SHA256_Update(&sha256, message, message_len);
    SHA256_Final(hash, &sha256);

    // Step 4: Determine the required signature length
    unsigned int signature_len = ECDSA_sign(0, hash, SHA256_DIGEST_LENGTH, NULL, NULL, key);
    if (signature_len == 0) {
        fprintf(stderr, "Failed to determine signature length\n");
        EC_KEY_free(key);
        return 1;
    }

    // Step 5: Allocate memory for the signature
    unsigned char *signature = malloc(signature_len);
    if (!signature) {
        fprintf(stderr, "Failed to allocate memory for signature\n");
        EC_KEY_free(key);
        return 1;
    }

    // Step 6: Sign the hash
    if (ECDSA_sign(0, hash, SHA256_DIGEST_LENGTH, signature, &signature_len, key) <= 0) {
        fprintf(stderr, "Failed to sign message\n");
        free(signature);
        EC_KEY_free(key);
        return 1;
    }

    // Step 7: Verify the signature
    int verify_result = ECDSA_verify(0, hash, SHA256_DIGEST_LENGTH, signature, signature_len, key);
    printf("%d\n", verify_result);  // Should print 1 (True) if verification succeeds

    // Step 8: Clean up
    free(signature);
    EC_KEY_free(key);

    return 0;
}