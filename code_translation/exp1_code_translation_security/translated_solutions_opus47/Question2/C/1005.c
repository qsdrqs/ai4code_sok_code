#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>

/*
 * Sign message, verify signature with the verifying key, and return it.
 * Caller must free the returned signature buffer.
 * sig_len is an out parameter for the signature length.
 */
unsigned char* sign(const unsigned char *msg, size_t msg_len,
                    EC_KEY *key, unsigned int *sig_len) {
    /* In OpenSSL, the EC_KEY holds both the private and public (verifying) key */
    EC_KEY *vk = key;

    /* Hash the message with SHA-1 (default digest for NIST192p in python ecdsa) */
    unsigned char hash[SHA_DIGEST_LENGTH];
    SHA1(msg, msg_len, hash);

    /* Allocate buffer and sign */
    unsigned char *signature = malloc(ECDSA_size(key));
    if (ECDSA_sign(0, hash, SHA_DIGEST_LENGTH, signature, sig_len, key) != 1) {
        fprintf(stderr, "ECDSA_sign failed\n");
        free(signature);
        return NULL;
    }

    /* Verify the signature */
    assert(ECDSA_verify(0, hash, SHA_DIGEST_LENGTH, signature, *sig_len, vk) == 1);

    return signature;
}

void test() {
    /* Generate a signing key with NIST192p curve (default in python ecdsa) */
    EC_KEY *sk = EC_KEY_new_by_curve_name(NID_X9_62_prime192v1);
    assert(sk != NULL);
    assert(EC_KEY_generate_key(sk) == 1);

    const unsigned char *msg = (const unsigned char *)"message";
    unsigned int sig_len = 0;
    unsigned char *signature = sign(msg, strlen((const char*)msg), sk, &sig_len);

    /* Print signature as a Python bytes-like string */
    printf("b'");
    for (unsigned int i = 0; i < sig_len; i++) {
        printf("\\x%02x", signature[i]);
    }
    printf("'\n");

    free(signature);
    EC_KEY_free(sk);
}

int main(void) {
    test();
    return 0;
}