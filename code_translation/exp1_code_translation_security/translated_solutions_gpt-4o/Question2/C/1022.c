#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <stdio.h>
#include <string.h>

int main() {
    // Generate Keys
    EC_KEY *ec_key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (ec_key == NULL) {
        fprintf(stderr, "Error creating EC Key\n");
        return 1;
    }

    if (EC_KEY_generate_key(ec_key) == 0) {
        fprintf(stderr, "Error generating EC Key\n");
        EC_KEY_free(ec_key);
        return 1;
    }

    // Message to be signed
    const char *message = "My test message";
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256((unsigned char*)message, strlen(message), hash);

    // Generate Signature
    unsigned int sig_len;
    unsigned char *signature = NULL;
    sig_len = ECDSA_size(ec_key);
    signature = OPENSSL_malloc(sig_len);
    if (signature == NULL) {
        fprintf(stderr, "Error allocating memory for signature\n");
        EC_KEY_free(ec_key);
        return 1;
    }

    if (ECDSA_sign(0, hash, SHA256_DIGEST_LENGTH, signature, &sig_len, ec_key) == 0) {
        fprintf(stderr, "Error signing message\n");
        OPENSSL_free(signature);
        EC_KEY_free(ec_key);
        return 1;
    }

    // Verify if signature is valid
    int verify_status = ECDSA_verify(0, hash, SHA256_DIGEST_LENGTH, signature, sig_len, ec_key);
    if (verify_status == 1) {
        printf("Signature is valid\n");
    } else {
        printf("Signature is invalid\n");
    }

    // Clean up
    OPENSSL_free(signature);
    EC_KEY_free(ec_key);

    return 0;
}