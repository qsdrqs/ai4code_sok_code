#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <string.h>
#include <stdio.h>

int main() {
    // Generate EC key pair using the NIST P-192 curve
    EC_KEY *ec_key = EC_KEY_new_by_curve_name(NID_X9_62_prime192v1);
    if (ec_key == NULL) {
        fprintf(stderr, "Error creating EC key\n");
        return 1;
    }

    if (EC_KEY_generate_key(ec_key) != 1) {
        fprintf(stderr, "Error generating EC key\n");
        EC_KEY_free(ec_key);
        return 1;
    }

    // Message to be signed
    const char *message = "message";
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256((unsigned char*)message, strlen(message), hash);

    // Sign the message
    unsigned int sig_len;
    unsigned char *signature = NULL;
    sig_len = ECDSA_size(ec_key);
    signature = OPENSSL_malloc(sig_len);
    if (signature == NULL) {
        fprintf(stderr, "Error allocating memory for signature\n");
        EC_KEY_free(ec_key);
        return 1;
    }

    if (ECDSA_sign(0, hash, SHA256_DIGEST_LENGTH, signature, &sig_len, ec_key) != 1) {
        fprintf(stderr, "Error signing message\n");
        OPENSSL_free(signature);
        EC_KEY_free(ec_key);
        return 1;
    }

    // Verify the signature
    int verify_status = ECDSA_verify(0, hash, SHA256_DIGEST_LENGTH, signature, sig_len, ec_key);
    if (verify_status != 1) {
        fprintf(stderr, "Signature verification failed\n");
    } else {
        printf("Signature verified successfully\n");
    }

    // Clean up
    OPENSSL_free(signature);
    EC_KEY_free(ec_key);

    return 0;
}