#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/bn.h>
#include <openssl/sha.h>
#include <openssl/obj_mac.h>

/**
 * Sign a message using a given ECDSA signing key (SECP256k1).
 *
 * Mirrors the behavior of the Python `ecdsa` library:
 *   - Curve: SECP256k1
 *   - Hash:  SHA-1 (the `ecdsa` library default)
 *   - Encoding: raw r || s, 32 bytes each (64 bytes total)
 *
 * @param message         Pointer to the message bytes.
 * @param message_len     Length of the message in bytes.
 * @param private_key     Pointer to a 32-byte raw private key.
 * @param signed_message  Output buffer of at least 64 bytes.
 * @return 1 on success, 0 on failure.
 */
int sign(const unsigned char *message, size_t message_len,
         const unsigned char *private_key,
         unsigned char *signed_message)
{
    EC_KEY        *ec_key  = NULL;
    BIGNUM        *priv_bn = NULL;
    ECDSA_SIG     *sig     = NULL;
    const BIGNUM  *r       = NULL;
    const BIGNUM  *s       = NULL;
    unsigned char  hash[SHA_DIGEST_LENGTH];   /* SHA-1 = 20 bytes */
    int r_len, s_len;
    int ret = 0;

    /* Create a new EC_KEY for SECP256k1 */
    ec_key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!ec_key) goto cleanup;

    /* Load the 32-byte raw private key (equivalent of SigningKey.from_string) */
    priv_bn = BN_bin2bn(private_key, 32, NULL);
    if (!priv_bn) goto cleanup;

    if (EC_KEY_set_private_key(ec_key, priv_bn) != 1) goto cleanup;

    /* Hash the message with SHA-1 (Python ecdsa library default) */
    SHA1(message, message_len, hash);

    /* Sign the digest */
    sig = ECDSA_do_sign(hash, sizeof(hash), ec_key);
    if (!sig) goto cleanup;

    /* Serialize signature as r || s, each 32 bytes big-endian (sigencode_string) */
    ECDSA_SIG_get0(sig, &r, &s);
    memset(signed_message, 0, 64);
    r_len = BN_num_bytes(r);
    s_len = BN_num_bytes(s);
    BN_bn2bin(r, signed_message + (32 - r_len));
    BN_bn2bin(s, signed_message + 32 + (32 - s_len));

    ret = 1;

cleanup:
    if (sig)     ECDSA_SIG_free(sig);
    if (priv_bn) BN_free(priv_bn);
    if (ec_key)  EC_KEY_free(ec_key);
    return ret;
}

/* -------------------- Example usage -------------------- */
int main(void)
{
    /* Demo 32-byte private key – DO NOT use in production */
    unsigned char private_key[32] = {
        0x1e, 0x99, 0x42, 0x3a, 0x4e, 0xd2, 0x76, 0x08,
        0xa1, 0x5a, 0x26, 0x16, 0xa2, 0xb0, 0xe9, 0xe5,
        0x2c, 0xed, 0x33, 0x0a, 0xc5, 0x30, 0xed, 0xcc,
        0x32, 0xc8, 0xff, 0xc6, 0xa5, 0x26, 0xae, 0xdd
    };
    const char   *message = "Hello, ECDSA!";
    unsigned char signature[64];

    if (!sign((const unsigned char *)message, strlen(message),
              private_key, signature)) {
        fprintf(stderr, "Signing failed\n");
        return 1;
    }

    printf("Signature (r || s):\n");
    for (int i = 0; i < 64; i++) printf("%02x", signature[i]);
    printf("\n");
    return 0;
}