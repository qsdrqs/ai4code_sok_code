/*
 * ECDSA Signing Example - C translation
 *
 * Dependencies: OpenSSL (libssl, libcrypto)
 *
 * Compile with: gcc -o ecdsa_sign ecdsa_sign.c -lssl -lcrypto
 *
 * Note: The starkbank-ecdsa library (Python) uses the secp256k1 curve by
 *       default, which is the same curve we use here.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <openssl/bn.h>

/* Equivalent to Python's generate_ecdsa_signed_msg function */
ECDSA_SIG *generate_ecdsa_signed_msg(const char *message, EC_KEY *privateKey) {
    unsigned char digest[SHA256_DIGEST_LENGTH];
    /* Hash the message with SHA-256 (default for ECDSA) */
    SHA256((const unsigned char *)message, strlen(message), digest);
    /* Sign the digest */
    return ECDSA_do_sign(digest, SHA256_DIGEST_LENGTH, privateKey);
}

int main(void) {
    /* Generate Keys: equivalent to PrivateKey() in Python (secp256k1) */
    EC_KEY *privateKey = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (privateKey == NULL) {
        fprintf(stderr, "Failed to create EC_KEY\n");
        return 1;
    }

    if (!EC_KEY_generate_key(privateKey)) {
        fprintf(stderr, "Failed to generate key\n");
        EC_KEY_free(privateKey);
        return 1;
    }

    /* Get the public key: equivalent to privateKey.publicKey() */
    const EC_POINT *publicKey = EC_KEY_get0_public_key(privateKey);
    (void)publicKey;  /* unused, mirrors the Python code */

    const char *message = "My test message";
    ECDSA_SIG *signature = generate_ecdsa_signed_msg(message, privateKey);

    if (signature == NULL) {
        fprintf(stderr, "Failed to sign message\n");
        EC_KEY_free(privateKey);
        return 1;
    }

    /* Print the signature (r, s components in hex) */
    const BIGNUM *r, *s;
    ECDSA_SIG_get0(signature, &r, &s);

    char *r_hex = BN_bn2hex(r);
    char *s_hex = BN_bn2hex(s);

    printf("Signature(r=%s, s=%s)\n", r_hex, s_hex);

    /* Cleanup */
    OPENSSL_free(r_hex);
    OPENSSL_free(s_hex);
    ECDSA_SIG_free(signature);
    EC_KEY_free(privateKey);

    return 0;
}