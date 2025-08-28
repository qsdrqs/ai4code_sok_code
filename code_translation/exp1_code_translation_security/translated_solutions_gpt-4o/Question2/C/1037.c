#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>

void handleErrors() {
    fprintf(stderr, "An error occurred.\n");
    exit(1);
}

unsigned char* signMessage(EC_KEY *key, const unsigned char *message, size_t message_len, unsigned int *sig_len) {
    unsigned char *signature = NULL;

    // Allocate memory for the signature
    signature = (unsigned char *)malloc(ECDSA_size(key));
    if (signature == NULL) {
        handleErrors();
    }

    // Sign the message
    if (!ECDSA_sign(0, message, message_len, signature, sig_len, key)) {
        free(signature);
        handleErrors();
    }

    return signature;
}

int main() {
    // Generate a new EC key pair using the NIST P-384 curve
    EC_KEY *key = EC_KEY_new_by_curve_name(NID_secp384r1);
    if (key == NULL) {
        handleErrors();
    }

    if (!EC_KEY_generate_key(key)) {
        EC_KEY_free(key);
        handleErrors();
    }

    // Get the public key (verifying key)
    const EC_GROUP *group = EC_KEY_get0_group(key);
    const EC_POINT *public_key = EC_KEY_get0_public_key(key);

    // Message to be signed
    const unsigned char *message = (unsigned char *)"testing";
    size_t message_len = strlen((const char *)message);

    // Sign the message
    unsigned int sig_len;
    unsigned char *signature = signMessage(key, message, message_len, &sig_len);

    // Verify the signature
    int verify_status = ECDSA_verify(0, message, message_len, signature, sig_len, key);
    if (verify_status == 1) {
        printf("Signature is valid.\n");
    } else if (verify_status == 0) {
        printf("Signature is invalid.\n");
    } else {
        printf("Error verifying the signature.\n");
    }

    // Clean up
    free(signature);
    EC_KEY_free(key);

    return 0;
}