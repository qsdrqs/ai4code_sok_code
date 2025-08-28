/*
 *  Example that reproduces the behaviour of the Python snippet
 *
 *  Dependencies:
 *      OpenSSL (libcrypto)
 *
 *  Build example (Linux/macOS):
 *      cc -o ecdsa_example ecdsa_example.c -lcrypto
 */

#include <stdio.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>

int main(void)
{
    const char *message = "My test message";

    /* ------------------------------------------------------------ *
     * 1. Generate a new secp256k1 private key                      *
     * ------------------------------------------------------------ */
    EC_KEY *private_key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!private_key) {
        fprintf(stderr, "Could not create EC_KEY object\n");
        return 1;
    }
    if (EC_KEY_generate_key(private_key) != 1) {
        fprintf(stderr, "Key-pair generation failed\n");
        EC_KEY_free(private_key);
        return 1;
    }

    /* ------------------------------------------------------------ *
     * 2. Extract/public key object (only contains public part)     *
     * ------------------------------------------------------------ */
    const EC_POINT *pub_point = EC_KEY_get0_public_key(private_key);

    EC_KEY *public_key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!public_key || EC_KEY_set_public_key(public_key, pub_point) != 1) {
        fprintf(stderr, "Could not create public-key object\n");
        EC_KEY_free(private_key);
        EC_KEY_free(public_key);
        return 1;
    }

    /* ------------------------------------------------------------ *
     * 3. SHA-256 hash of the message                               *
     * ------------------------------------------------------------ */
    unsigned char digest[SHA256_DIGEST_LENGTH];
    SHA256((const unsigned char *)message, strlen(message), digest);

    /* ------------------------------------------------------------ *
     * 4. Sign the digest                                           *
     * ------------------------------------------------------------ */
    unsigned char signature[72];                  /* max DER size */
    unsigned int sig_len = 0;

    if (ECDSA_sign(0, digest, SHA256_DIGEST_LENGTH,
                   signature, &sig_len, private_key) != 1)
    {
        fprintf(stderr, "Signing failed\n");
        EC_KEY_free(private_key);
        EC_KEY_free(public_key);
        return 1;
    }

    /* ------------------------------------------------------------ *
     * 5. Verify the signature using the public key                 *
     * ------------------------------------------------------------ */
    int ok = ECDSA_verify(0, digest, SHA256_DIGEST_LENGTH,
                          signature, sig_len, public_key);

    printf("%s\n", ok == 1 ? "True" : "False");

    /* ------------------------------------------------------------ *
     * 6. Clean up                                                  *
     * ------------------------------------------------------------ */
    EC_KEY_free(private_key);
    EC_KEY_free(public_key);

    return 0;
}