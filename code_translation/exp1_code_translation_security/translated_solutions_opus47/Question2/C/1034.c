#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <openssl/err.h>

int main(void) {
    /* Create and generate a private key using secp256k1 (starkbank-ecdsa default) */
    EC_KEY *privateKey = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (privateKey == NULL) {
        fprintf(stderr, "Failed to create EC key\n");
        return 1;
    }

    if (EC_KEY_generate_key(privateKey) != 1) {
        fprintf(stderr, "Failed to generate EC key\n");
        EC_KEY_free(privateKey);
        return 1;
    }

    /* Derive the public key (separate EC_KEY with only the public point) */
    EC_KEY *publicKey = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (publicKey == NULL) {
        fprintf(stderr, "Failed to create public key\n");
        EC_KEY_free(privateKey);
        return 1;
    }
    EC_KEY_set_public_key(publicKey, EC_KEY_get0_public_key(privateKey));

    const char *message = "Test message";
    unsigned char hash[SHA256_DIGEST_LENGTH];

    /* Hash the message with SHA-256 */
    SHA256((const unsigned char *)message, strlen(message), hash);

    /* Sign the hash */
    ECDSA_SIG *signature = ECDSA_do_sign(hash, SHA256_DIGEST_LENGTH, privateKey);
    if (signature == NULL) {
        fprintf(stderr, "Failed to sign message\n");
        EC_KEY_free(privateKey);
        EC_KEY_free(publicKey);
        return 1;
    }

    /* Verify the signature using the public key */
    int verify_status = ECDSA_do_verify(hash, SHA256_DIGEST_LENGTH, signature, publicKey);

    /* Print True/False to match Python's boolean output */
    printf("%s\n", verify_status == 1 ? "True" : "False");

    /* Cleanup */
    ECDSA_SIG_free(signature);
    EC_KEY_free(privateKey);
    EC_KEY_free(publicKey);

    return 0;
}