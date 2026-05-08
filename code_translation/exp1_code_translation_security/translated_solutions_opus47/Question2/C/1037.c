#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>

/*
 * C translation of Python ECDSA code using OpenSSL.
 * Compile with: gcc -o program program.c -lcrypto
 */

// Equivalent to Python's signMessage(key, message)
unsigned char* signMessage(EC_KEY *key, const unsigned char *message,
                           size_t message_len, unsigned int *sig_len) {
    unsigned char *signature = malloc(ECDSA_size(key));
    if (!signature) {
        return NULL;
    }

    // python-ecdsa uses SHA-1 as the default hash function
    unsigned char hash[SHA_DIGEST_LENGTH];
    SHA1(message, message_len, hash);

    // key.sign(message) equivalent
    if (ECDSA_sign(0, hash, SHA_DIGEST_LENGTH, signature, sig_len, key) != 1) {
        free(signature);
        return NULL;
    }

    return signature;
}

int main(void) {
    // sk = SigningKey.generate(curve=NIST384p)
    EC_KEY *sk = EC_KEY_new_by_curve_name(NID_secp384r1);
    if (!sk || EC_KEY_generate_key(sk) != 1) {
        fprintf(stderr, "Key generation failed\n");
        if (sk) EC_KEY_free(sk);
        return 1;
    }

    // vk = sk.verifying_key
    // In OpenSSL, the public key is embedded in the same EC_KEY struct
    EC_KEY *vk = sk;

    const unsigned char *message = (const unsigned char *)"testing";
    size_t message_len = strlen((const char *)message);

    // signature = signMessage(sk, b"testing")
    unsigned int sig_len;
    unsigned char *signature = signMessage(sk, message, message_len, &sig_len);
    if (!signature) {
        fprintf(stderr, "Signing failed\n");
        EC_KEY_free(sk);
        return 1;
    }

    // print(vk.verify(signature, b"testing"))
    unsigned char hash[SHA_DIGEST_LENGTH];
    SHA1(message, message_len, hash);

    int result = ECDSA_verify(0, hash, SHA_DIGEST_LENGTH, signature, sig_len, vk);
    printf("%s\n", result == 1 ? "True" : "False");

    // Cleanup
    free(signature);
    EC_KEY_free(sk);
    return 0;
}