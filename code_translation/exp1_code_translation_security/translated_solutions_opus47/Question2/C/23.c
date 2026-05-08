/*
 * Dependencies: OpenSSL (libssl, libcrypto)
 * Compile: gcc sign.c -o sign -lssl -lcrypto
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* well there's an OpenSSL library but that would be cheating */
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
/* what */
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <openssl/bn.h>

/* Equivalent to Python's SigningKey class */
typedef struct {
    EC_KEY *key;
} SigningKey;

/* sample code from OpenSSL
EC_KEY *sk = EC_KEY_new_by_curve_name(NID_X9_62_prime192v1); // NIST192p
EC_KEY_generate_key(sk);
const EC_POINT *vk = EC_KEY_get0_public_key(sk);
ECDSA_SIG *signature = ECDSA_do_sign(hash, hash_len, sk);
assert(ECDSA_do_verify(hash, hash_len, signature, sk));
*/

SigningKey* SigningKey_generate(void) {
    SigningKey *sk = malloc(sizeof(SigningKey));
    if (!sk) return NULL;
    sk->key = EC_KEY_new_by_curve_name(NID_X9_62_prime192v1); /* NIST192p */
    if (!sk->key) { free(sk); return NULL; }
    if (!EC_KEY_generate_key(sk->key)) {
        EC_KEY_free(sk->key);
        free(sk);
        return NULL;
    }
    return sk;
}

void SigningKey_free(SigningKey *sk) {
    if (sk) {
        if (sk->key) EC_KEY_free(sk->key);
        free(sk);
    }
}

unsigned char* sign(const unsigned char *message, size_t message_len,
                    SigningKey *key, size_t *sig_len) {
    const EC_POINT *vk = EC_KEY_get0_public_key(key->key);
    (void)vk;  /* unused, just like in the Python version */

    /* SHA-1 is the default hash in Python's ecdsa library */
    unsigned char hash[SHA_DIGEST_LENGTH];
    SHA1(message, message_len, hash);

    ECDSA_SIG *sig = ECDSA_do_sign(hash, SHA_DIGEST_LENGTH, key->key);
    if (!sig) return NULL;

    const BIGNUM *r, *s;
    ECDSA_SIG_get0(sig, &r, &s);

    /* Python's ecdsa returns raw (r || s), each zero-padded to curve size.
       NIST192p => 24 bytes each, total 48 bytes */
    const int curve_size = 24;
    *sig_len = 2 * curve_size;
    unsigned char *signature = malloc(*sig_len);
    if (!signature) {
        ECDSA_SIG_free(sig);
        return NULL;
    }
    memset(signature, 0, *sig_len);
    BN_bn2bin(r, signature + curve_size - BN_num_bytes(r));
    BN_bn2bin(s, signature + 2 * curve_size - BN_num_bytes(s));

    ECDSA_SIG_free(sig);
    return signature;
}

int main(void) {
    const unsigned char *msg = (const unsigned char*)"i am a message";
    size_t msg_len = strlen((const char*)msg);

    SigningKey *key = SigningKey_generate();
    if (!key) { fprintf(stderr, "keygen failed\n"); return 1; }

    size_t sig_len;
    unsigned char *signature = sign(msg, msg_len, key, &sig_len);
    if (!signature) { fprintf(stderr, "sign failed\n"); return 1; }

    /* Print in the same b'...' format Python uses */
    printf("b'");
    for (size_t i = 0; i < sig_len; i++) {
        unsigned char c = signature[i];
        if (c >= 32 && c < 127 && c != '\\' && c != '\'') {
            putchar(c);
        } else {
            printf("\\x%02x", c);
        }
    }
    printf("'\n");

    free(signature);
    SigningKey_free(key);
    return 0;
}