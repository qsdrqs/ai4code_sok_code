/*
 * ECDSA signing and verification example using OpenSSL
 * 
 * Compile with: gcc ecdsa_example.c -lssl -lcrypto -o ecdsa_example
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>

/*
 * Signs a given message using a given ECDSA signing key
 *
 * Args:
 *   message: input message
 *   message_len: length of the input message
 *   key: ECDSA key used to sign the message
 *   sig_len: output parameter to store the signature length
 *
 * Return:
 *   pointer to dynamically allocated signed message (caller must free)
 *   NULL on failure
 */
unsigned char *sign(const unsigned char *message, size_t message_len,
                    EC_KEY *key, unsigned int *sig_len) {
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256(message, message_len, hash);

    *sig_len = ECDSA_size(key);
    unsigned char *signature = (unsigned char *)malloc(*sig_len);
    if (signature == NULL) {
        return NULL;
    }

    if (ECDSA_sign(0, hash, SHA256_DIGEST_LENGTH, signature, sig_len, key) != 1) {
        free(signature);
        return NULL;
    }

    return signature;
}

int main(void) {
    /* Generate an ECDSA key pair on the SECP256k1 curve (equivalent to
       ecdsa.SigningKey.generate(curve=ecdsa.SECP256k1)) */
    EC_KEY *sk = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (sk == NULL || EC_KEY_generate_key(sk) != 1) {
        fprintf(stderr, "Error generating EC key\n");
        if (sk) EC_KEY_free(sk);
        return 1;
    }

    /* In OpenSSL, the EC_KEY structure already contains both the private
       (signing) and public (verifying) keys, so we can reuse it. */
    EC_KEY *vk = sk;

    /* Sign the message */
    const unsigned char message[] = "helloworld";
    size_t message_len = strlen((const char *)message);
    unsigned int sig_len;
    unsigned char *signed_message = sign(message, message_len, sk, &sig_len);

    if (signed_message == NULL) {
        fprintf(stderr, "Error signing message\n");
        EC_KEY_free(sk);
        return 1;
    }

    /* Verify the signature (hash the message first, then verify) */
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256(message, message_len, hash);
    int verify_status = ECDSA_verify(0, hash, SHA256_DIGEST_LENGTH,
                                      signed_message, sig_len, vk);

    /* Print "True" if verification succeeded, else "False"
       (to match Python's boolean print output) */
    printf("%s\n", verify_status == 1 ? "True" : "False");

    /* Cleanup */
    free(signed_message);
    EC_KEY_free(sk);

    return 0;
}