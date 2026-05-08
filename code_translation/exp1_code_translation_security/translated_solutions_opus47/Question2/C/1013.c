#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <openssl/bn.h>

/* Compile with:  gcc -o ecdsa_sign ecdsa_sign.c -lssl -lcrypto */

/* Returns a malloc'd buffer holding the raw (r || s) signature.
 * Caller must free() the result. */
unsigned char *sign(const char *message, EC_KEY *key, size_t *sig_len) {
    /* Python ecdsa library hashes with SHA-1 by default */
    unsigned char hash[SHA_DIGEST_LENGTH];
    SHA1((const unsigned char *)message, strlen(message), hash);

    /* Produce an ECDSA signature */
    ECDSA_SIG *sig = ECDSA_do_sign(hash, SHA_DIGEST_LENGTH, key);
    if (!sig) return NULL;

    /* Extract r and s */
    const BIGNUM *r, *s;
    ECDSA_SIG_get0(sig, &r, &s);

    /* Determine fixed width from the curve order (24 bytes for NIST192p) */
    const EC_GROUP *group = EC_KEY_get0_group(key);
    BIGNUM *order = BN_new();
    EC_GROUP_get_order(group, order, NULL);
    int n = BN_num_bytes(order);
    BN_free(order);

    /* Write zero-padded r || s — this matches Python ecdsa's default sigencode_string */
    *sig_len = 2 * n;
    unsigned char *out = (unsigned char *)malloc(*sig_len);
    BN_bn2binpad(r, out,     n);
    BN_bn2binpad(s, out + n, n);

    ECDSA_SIG_free(sig);
    return out;
}

int main(void) {
    /* SigningKey.generate() defaults to NIST192p  (a.k.a. secp192r1 / prime192v1) */
    EC_KEY *key = EC_KEY_new_by_curve_name(NID_X9_62_prime192v1);
    if (!key || !EC_KEY_generate_key(key)) {
        fprintf(stderr, "Key generation failed\n");
        if (key) EC_KEY_free(key);
        return 1;
    }

    size_t sig_len = 0;
    unsigned char *signature = sign("something", key, &sig_len);
    if (!signature) {
        fprintf(stderr, "Signing failed\n");
        EC_KEY_free(key);
        return 1;
    }

    /* Print like Python would print a bytes object */
    printf("b'");
    for (size_t i = 0; i < sig_len; i++) {
        unsigned char c = signature[i];
        if (c >= 0x20 && c < 0x7f && c != '\\' && c != '\'')
            putchar(c);
        else
            printf("\\x%02x", c);
    }
    printf("'\n");

    free(signature);
    EC_KEY_free(key);
    return 0;
}