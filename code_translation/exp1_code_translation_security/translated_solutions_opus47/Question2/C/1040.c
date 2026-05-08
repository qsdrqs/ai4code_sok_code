/**
 * sign message ecdsa
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/bn.h>
#include <openssl/sha.h>

/**
 * sign message
 *
 * Signs the given message using ECDSA with SECP256k1 curve.
 * The message is hashed with SHA1 before signing (matches Python ecdsa library default).
 *
 * @param message        The message to sign
 * @param message_len    Length of the message
 * @param private_key    The private key (32 bytes for SECP256k1)
 * @param signature      Output buffer for the signature (must be at least 64 bytes)
 *                       Format: r (32 bytes) || s (32 bytes)
 * @return 1 on success, 0 on failure
 */
int sign_message(const unsigned char *message, size_t message_len,
                 const unsigned char *private_key,
                 unsigned char *signature)
{
    EC_KEY *ec_key = NULL;
    BIGNUM *priv_bn = NULL;
    ECDSA_SIG *sig = NULL;
    const BIGNUM *r = NULL;
    const BIGNUM *s = NULL;
    int result = 0;
    unsigned char digest[SHA_DIGEST_LENGTH];
    int r_len, s_len;

    /* Create a new EC key with SECP256k1 curve */
    ec_key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (ec_key == NULL) {
        return 0;
    }

    /* Convert private key bytes to BIGNUM */
    priv_bn = BN_bin2bn(private_key, 32, NULL);
    if (priv_bn == NULL) {
        EC_KEY_free(ec_key);
        return 0;
    }

    /* Set the private key */
    if (EC_KEY_set_private_key(ec_key, priv_bn) != 1) {
        BN_free(priv_bn);
        EC_KEY_free(ec_key);
        return 0;
    }

    /* Hash the message (SHA1 - Python ecdsa library default) */
    SHA1(message, message_len, digest);

    /* Sign the hash */
    sig = ECDSA_do_sign(digest, SHA_DIGEST_LENGTH, ec_key);
    if (sig == NULL) {
        BN_free(priv_bn);
        EC_KEY_free(ec_key);
        return 0;
    }

    /* Extract r and s from signature */
    ECDSA_SIG_get0(sig, &r, &s);

    /* Convert r and s to bytes (padded to 32 bytes each) */
    memset(signature, 0, 64);
    r_len = BN_num_bytes(r);
    s_len = BN_num_bytes(s);
    BN_bn2bin(r, signature + (32 - r_len));
    BN_bn2bin(s, signature + 32 + (32 - s_len));

    result = 1;

    ECDSA_SIG_free(sig);
    BN_free(priv_bn);
    EC_KEY_free(ec_key);

    return result;
}