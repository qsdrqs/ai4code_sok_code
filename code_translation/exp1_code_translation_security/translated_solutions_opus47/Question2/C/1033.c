#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>

/*
 * Signs a given message using a given ECDSA signing curve.
 *
 * Parameters:
 *   message     - The message buffer to sign
 *   message_len - Length of the message
 *   key         - Curve NID (e.g. NID_X9_62_prime256v1 for NIST P-256)
 *   sig_len     - [out] Length of the generated signature
 *
 * Returns: Pointer to heap-allocated signature (caller must free), or NULL on error.
 */
unsigned char* sign_message(const unsigned char* message, size_t message_len,
                            int key, unsigned int* sig_len) {
    /* Generate a random private key using the given curve */
    EC_KEY* signing_key = EC_KEY_new_by_curve_name(key);
    if (signing_key == NULL) {
        return NULL;
    }

    if (EC_KEY_generate_key(signing_key) != 1) {
        EC_KEY_free(signing_key);
        return NULL;
    }

    /* Get the public key (verifying key) */
    const EC_POINT* verifying_key = EC_KEY_get0_public_key(signing_key);
    (void)verifying_key;  /* Mirrors the Python code; not used further */

    /* Hash the message (ECDSA signs a digest, not raw data) */
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256(message, message_len, hash);

    /* Allocate buffer for the signature */
    unsigned char* signature = (unsigned char*)malloc(ECDSA_size(signing_key));
    if (signature == NULL) {
        EC_KEY_free(signing_key);
        return NULL;
    }

    /* Sign the message hash */
    if (ECDSA_sign(0, hash, SHA256_DIGEST_LENGTH,
                   signature, sig_len, signing_key) != 1) {
        free(signature);
        EC_KEY_free(signing_key);
        return NULL;
    }

    EC_KEY_free(signing_key);
    return signature;
}

/* Example usage */
int main(void) {
    const char* message = "Hello, World!";
    unsigned int sig_len = 0;

    /* NID_X9_62_prime256v1 is the OpenSSL equivalent of NIST256p */
    unsigned char* signature = sign_message(
        (const unsigned char*)message,
        strlen(message),
        NID_X9_62_prime256v1,
        &sig_len
    );

    if (signature == NULL) {
        fprintf(stderr, "Failed to sign message\n");
        return 1;
    }

    printf("Signature (%u bytes): ", sig_len);
    for (unsigned int i = 0; i < sig_len; i++) {
        printf("%02x", signature[i]);
    }
    printf("\n");

    free(signature);
    return 0;
}