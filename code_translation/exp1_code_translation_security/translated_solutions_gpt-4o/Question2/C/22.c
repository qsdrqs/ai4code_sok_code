#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>

void myfunction(const unsigned char *message, size_t message_len) {
    // Create a new EC_KEY object for the SECP256k1 curve
    EC_KEY *ec_key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (ec_key == NULL) {
        fprintf(stderr, "Error creating EC_KEY object\n");
        return;
    }

    // Generate a new private key
    if (EC_KEY_generate_key(ec_key) != 1) {
        fprintf(stderr, "Error generating EC key pair\n");
        EC_KEY_free(ec_key);
        return;
    }

    // Sign the message
    unsigned int sig_len = 0;
    unsigned char *signature = NULL;

    // Allocate memory for the signature
    sig_len = ECDSA_size(ec_key);
    signature = (unsigned char *)malloc(sig_len);
    if (signature == NULL) {
        fprintf(stderr, "Error allocating memory for signature\n");
        EC_KEY_free(ec_key);
        return;
    }

    if (ECDSA_sign(0, message, message_len, signature, &sig_len, ec_key) != 1) {
        fprintf(stderr, "Error signing the message\n");
        free(signature);
        EC_KEY_free(ec_key);
        return;
    }

    printf("Message signed successfully. Signature length: %u\n", sig_len);

    // Clean up
    free(signature);
    EC_KEY_free(ec_key);
}