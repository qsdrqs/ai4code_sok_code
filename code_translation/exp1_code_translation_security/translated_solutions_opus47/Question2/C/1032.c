/*
 * Sign a message using ECDSA (SECP256k1 curve).
 * Translated from Python.
 *
 * Dependencies: OpenSSL (libcrypto).
 * Compile:  gcc -o sign sign.c -lcrypto
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <openssl/sha.h>
#include <openssl/ecdsa.h>
#include <openssl/ec.h>
#include <openssl/obj_mac.h>
#include <openssl/bn.h>

/*
 * Sign a message using a given ECDSA private key (SECP256k1 curve).
 *
 * Parameters:
 *   message         - the message to sign (null-terminated string)
 *   private_key     - raw private key bytes
 *   private_key_len - length of the private key (must be 32 for SECP256k1)
 *   sig_len         - out: length of the returned signature buffer
 *
 * Returns a newly allocated buffer containing the 64-byte signature in
 * raw (r || s) form, matching the default output of Python's ecdsa
 * library.  The caller must free() this buffer.  Returns NULL on failure.
 */
unsigned char *sign_message(const char *message,
                            const unsigned char *private_key,
                            size_t private_key_len,
                            size_t *sig_len)
{
    unsigned char  message_hash[SHA256_DIGEST_LENGTH];
    EC_KEY        *key       = NULL;
    BIGNUM        *priv_bn   = NULL;
    ECDSA_SIG     *sig       = NULL;
    unsigned char *signature = NULL;
    const BIGNUM  *r;
    const BIGNUM  *s;
    int            r_len, s_len;

    /* Hash the message */
    SHA256((const unsigned char *)message, strlen(message), message_hash);

    /* Create a new ECDSA private key object on the SECP256k1 curve */
    key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!key) goto cleanup;

    priv_bn = BN_bin2bn(private_key, (int)private_key_len, NULL);
    if (!priv_bn) goto cleanup;

    if (!EC_KEY_set_private_key(key, priv_bn)) goto cleanup;

    /* Sign the message hash */
    sig = ECDSA_do_sign(message_hash, SHA256_DIGEST_LENGTH, key);
    if (!sig) goto cleanup;

    /*
     * Encode the signature as 64 raw bytes (r || s), each padded to 32
     * bytes big-endian - same format that python-ecdsa's sign() returns.
     */
    ECDSA_SIG_get0(sig, &r, &s);
    *sig_len  = 64;
    signature = (unsigned char *)calloc(64, 1);
    if (!signature) goto cleanup;

    r_len = BN_num_bytes(r);
    s_len = BN_num_bytes(s);

    BN_bn2bin(r, signature + (32 - r_len));
    BN_bn2bin(s, signature + 32 + (32 - s_len));

cleanup:
    if (sig)     ECDSA_SIG_free(sig);
    if (priv_bn) BN_free(priv_bn);
    if (key)     EC_KEY_free(key);
    return signature;
}

/* Helper: convert a hex string to raw bytes. Returns bytes written, or -1. */
static int hex_to_bytes(const char *hex, unsigned char *bytes, size_t max_len)
{
    size_t hex_len = strlen(hex);
    size_t i;

    if (hex_len % 2 != 0 || hex_len / 2 > max_len)
        return -1;

    for (i = 0; i < hex_len / 2; i++) {
        unsigned int byte;
        if (sscanf(hex + 2 * i, "%2x", &byte) != 1)
            return -1;
        bytes[i] = (unsigned char)byte;
    }
    return (int)(hex_len / 2);
}

/*
 * Test function
 */
int main(void)
{
    const char   *message         = "Hello, world!";
    const char   *private_key_hex =
        "18E14A7B6A307F426A94F8114701E7C8E774E7F9A47E2C2035DB29A206321725";
    unsigned char private_key[32];
    unsigned char *signature;
    size_t         sig_len = 0;
    size_t         i;

    if (hex_to_bytes(private_key_hex, private_key, sizeof(private_key)) != 32) {
        fprintf(stderr, "Invalid private key\n");
        return 1;
    }

    signature = sign_message(message, private_key, sizeof(private_key), &sig_len);
    if (!signature) {
        fprintf(stderr, "Signing failed\n");
        return 1;
    }

    /* Print the signature using a Python-like bytes representation */
    printf("b'");
    for (i = 0; i < sig_len; i++) {
        unsigned char c = signature[i];
        if (c >= 32 && c < 127 && c != '\\' && c != '\'')
            printf("%c", c);
        else
            printf("\\x%02x", c);
    }
    printf("'\n");

    free(signature);
    return 0;
}