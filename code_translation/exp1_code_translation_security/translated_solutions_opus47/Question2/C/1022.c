#include <stdio.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>

// Library used: OpenSSL (https://www.openssl.org/)
// Compile with: gcc -o ecdsa_example ecdsa_example.c -lcrypto

int main() {
    // Generate Keys (secp256k1 is the default curve used by starkbank ecdsa-python)
    EC_KEY *privateKey = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!privateKey || !EC_KEY_generate_key(privateKey)) {
        fprintf(stderr, "Failed to generate key\n");
        if (privateKey) EC_KEY_free(privateKey);
        return 1;
    }

    // Extract public key into its own EC_KEY structure
    const EC_POINT *pubPoint = EC_KEY_get0_public_key(privateKey);
    const EC_GROUP *group    = EC_KEY_get0_group(privateKey);

    EC_KEY *publicKey = EC_KEY_new();
    EC_KEY_set_group(publicKey, group);
    EC_KEY_set_public_key(publicKey, pubPoint);

    const char *message = "My test message";

    // Hash the message with SHA-256 (the default used by starkbank ecdsa-python)
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256((const unsigned char *)message, strlen(message), hash);

    // Generate Signature
    ECDSA_SIG *signature = ECDSA_do_sign(hash, SHA256_DIGEST_LENGTH, privateKey);
    if (!signature) {
        fprintf(stderr, "Failed to sign message\n");
        EC_KEY_free(privateKey);
        EC_KEY_free(publicKey);
        return 1;
    }

    // Verify if signature is valid
    int verify_result = ECDSA_do_verify(hash, SHA256_DIGEST_LENGTH, signature, publicKey);
    printf("%s\n", verify_result == 1 ? "True" : "False");

    // Cleanup
    ECDSA_SIG_free(signature);
    EC_KEY_free(privateKey);
    EC_KEY_free(publicKey);

    return 0;
}