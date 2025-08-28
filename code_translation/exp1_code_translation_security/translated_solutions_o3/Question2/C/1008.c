/*
 *  sign.c  – ECDSA secp256k1 “hello world” example
 *
 *  gcc sign.c -o sign -lcrypto
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/evp.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <openssl/bn.h>

/*
 * Sign a message with an ASCII “raw” 32-byte private key.
 * The function returns a DER-encoded signature buffer and its length.
 * Caller must free() the returned buffer.
 */
unsigned char *
sign_message(const char *message,
             const char *priv_ascii32,
             unsigned int *sig_len)
{
    /* ------ calculate SHA-1 of the message (Python ecdsa’s default hash) */
    unsigned char digest[SHA_DIGEST_LENGTH];
    SHA1((const unsigned char *)message, strlen(message), digest);

    /* ------ build EC key for curve secp256k1 --------------------------- */
    EC_KEY *ec_key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!ec_key) {
        fprintf(stderr, "EC_KEY_new_by_curve_name failed\n");
        return NULL;
    }

    /* convert the ASCII digits “1234…32” to the byte sequence 0x31 0x32 … */
    unsigned char priv_bin[32];
    memcpy(priv_bin, priv_ascii32, 32);

    BIGNUM *priv_bn = BN_bin2bn(priv_bin, 32, NULL);
    if (!priv_bn || !EC_KEY_set_private_key(ec_key, priv_bn)) {
        fprintf(stderr, "setting private key failed\n");
        EC_KEY_free(ec_key);
        BN_free(priv_bn);
        return NULL;
    }

    /* derive and set the public key (needed for OpenSSL < 3.0) */
    const EC_GROUP *group = EC_KEY_get0_group(ec_key);
    EC_POINT *pub_pt = EC_POINT_new(group);
    EC_POINT_mul(group, pub_pt, priv_bn, NULL, NULL, NULL);
    EC_KEY_set_public_key(ec_key, pub_pt);

    /* ------ do the actual ECDSA signing -------------------------------- */
    unsigned char *sig = malloc(ECDSA_size(ec_key));
    if (!sig) {
        perror("malloc");
        EC_POINT_free(pub_pt);
        EC_KEY_free(ec_key);
        BN_free(priv_bn);
        return NULL;
    }

    if (ECDSA_sign(0,              /* ignored */
                   digest, SHA_DIGEST_LENGTH,
                   sig, sig_len,
                   ec_key) != 1)
    {
        fprintf(stderr, "ECDSA_sign failed\n");
        free(sig);
        sig = NULL;
    }

    /* ------ clean-up --------------------------------------------------- */
    EC_POINT_free(pub_pt);
    EC_KEY_free(ec_key);
    BN_free(priv_bn);

    return sig;     /* caller frees */
}

/* ---------------------------------------------------------------------- */
int main(void)
{
    const char *msg  = "hello world";
    const char *priv = "12345678901234567890123456789012";   /* 32 chars */

    unsigned int sig_len = 0;
    unsigned char *sig = sign_message(msg, priv, &sig_len);
    if (!sig) return 1;

    /* print result in hex so it’s easy to see */
    printf("Signature (%u bytes): ", sig_len);
    for (unsigned int i = 0; i < sig_len; ++i)
        printf("%02x", sig[i]);
    puts("");

    free(sig);
    return 0;
}