/*  sign_secp256k1.c
 *
 *  Compile:
 *      gcc -o sign_secp256k1 sign_secp256k1.c \
 *          -lcrypto                           \
 *          -Wall -Wextra -pedantic
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <openssl/sha.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/bn.h>

/* -------- utility : hex ↔ bytes ----------------------------------------- */

static int hexchar2int(char c)
{
    if ('0' <= c && c <= '9') return c - '0';
    if ('a' <= c && c <= 'f') return c - 'a' + 10;
    if ('A' <= c && c <= 'F') return c - 'A' + 10;
    return -1;
}

static size_t hex2bin(const char *hex, unsigned char **out)
{
    size_t hexlen = strlen(hex);
    if (hexlen & 1) return 0;                    /* must be even length */

    size_t binlen = hexlen / 2;
    *out = (unsigned char *)malloc(binlen);
    if (!*out) return 0;

    for (size_t i = 0; i < binlen; ++i) {
        int hi = hexchar2int(hex[2 * i]);
        int lo = hexchar2int(hex[2 * i + 1]);
        if (hi < 0 || lo < 0) { free(*out); return 0; }
        (*out)[i] = (unsigned char)((hi << 4) | lo);
    }
    return binlen;
}

static char *bin2hex(const unsigned char *bin, size_t len)
{
    static const char hexdigits[] = "0123456789abcdef";
    char *hex = (char *)malloc(len * 2 + 1);
    if (!hex) return NULL;

    for (size_t i = 0; i < len; ++i) {
        hex[2 * i    ] = hexdigits[(bin[i] >> 4) & 0x0F];
        hex[2 * i + 1] = hexdigits[ bin[i]       & 0x0F];
    }
    hex[len * 2] = '\0';
    return hex;
}

/* -------- sign_message -------------------------------------------------- */

char *sign_message(const char *msg,
                   const unsigned char *priv_bytes,
                   size_t priv_len,
                   size_t *out_sig_len)                 /* returns hex string */
{
    /* 1. Build an EC_KEY object from the raw 32-byte private key */
    EC_KEY *key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!key) goto error;

    BIGNUM *priv_bn = BN_bin2bn(priv_bytes, (int)priv_len, NULL);
    if (!priv_bn) goto error;

    if (!EC_KEY_set_private_key(key, priv_bn)) goto error;

    /* Derive public key from the private component (OpenSSL needs it) */
    const EC_GROUP *grp = EC_KEY_get0_group(key);
    EC_POINT *pub_pt   = EC_POINT_new(grp);
    if (!pub_pt) goto error;

    if (!EC_POINT_mul(grp, pub_pt, priv_bn, NULL, NULL, NULL)) goto error;
    if (!EC_KEY_set_public_key(key, pub_pt)) goto error;

    /* 2. SHA-256 hash of the message */
    unsigned char digest[SHA256_DIGEST_LENGTH];
    SHA256((const unsigned char *)msg, strlen(msg), digest);

    /* 3. Sign the digest -> DER encoded signature */
    unsigned int  der_len = ECDSA_size(key);
    unsigned char *der    = (unsigned char *)OPENSSL_malloc(der_len);
    if (!der) goto error;

    if (!ECDSA_sign(0, digest, SHA256_DIGEST_LENGTH, der, &der_len, key))
        goto error;

    /* 4. Hex-encode output */
    char *hex_sig = bin2hex(der, der_len);
    *out_sig_len  = der_len;

    /* ---- cleanup ---- */
    BN_clear_free(priv_bn);
    EC_POINT_free(pub_pt);
    EC_KEY_free(key);
    OPENSSL_free(der);

    return hex_sig;

error:
    if (priv_bn)  BN_clear_free(priv_bn);
    if (pub_pt)   EC_POINT_free(pub_pt);
    if (key)      EC_KEY_free(key);
    return NULL;
}

/* ----------------------------------------------------------------------- */

int main(void)
{
    const char *priv_hex =
        "e331b6d69882b4cb4ea581d88e0b604039a3de5967688d3dcffdd2270c0fd109";
    const char *message  = "Hello";

    /* convert hex private key to raw bytes */
    unsigned char *priv_bytes = NULL;
    size_t priv_len = hex2bin(priv_hex, &priv_bytes);
    if (priv_len == 0 || !priv_bytes) {
        fprintf(stderr, "Bad private key hex.\n");
        return EXIT_FAILURE;
    }

    /* sign */
    size_t sig_len = 0;
    char *signature_hex = sign_message(message, priv_bytes, priv_len, &sig_len);
    free(priv_bytes);

    if (!signature_hex) {
        fprintf(stderr, "Signing failed.\n");
        return EXIT_FAILURE;
    }

    printf("%s\n", signature_hex);
    free(signature_hex);
    return EXIT_SUCCESS;
}