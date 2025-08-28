/*
 * Function: sign_message
 * ----------------------
 * Sign an arbitrary byte-buffer with a secp256k1 private key.
 *
 *  message      : pointer to the message bytes
 *  message_len  : length of the message
 *  priv_key     : 32-byte raw private-key buffer
 *  sig          : output buffer that will receive the DER-encoded signature
 *  sig_len      : pointer that will receive the signature length
 *
 *  returns 0 on success, -1 on failure.
 *
 *  NOTE:  ❑  The function hashes the message with SHA-1 before signing,
 *              mimicking ecdsa.SigningKey.sign(...)’s default behaviour.
 *         ❑  All memory allocation is handled inside the function; the caller
 *              must free the *sig buffer with OPENSSL_free().
 */

#include <stdio.h>
#include <string.h>
#include <openssl/ecdsa.h>
#include <openssl/evp.h>
#include <openssl/obj_mac.h>
#include <openssl/err.h>

int sign_message(const unsigned char *message,
                 size_t                message_len,
                 const unsigned char  *priv_key,      /* 32-byte raw key */
                 unsigned char       **sig,           /* out: DER blob  */
                 unsigned int         *sig_len)       /* out: size      */
{
    int ret = -1;                    /* pessimistic */
    EC_KEY       *ec_key   = NULL;
    const EC_GROUP *group  = NULL;
    BIGNUM       *bn_priv  = NULL;
    EC_POINT     *pub_key  = NULL;

    unsigned char hash[EVP_MAX_MD_SIZE];
    unsigned int  hash_len = 0;

    /* ---------------- SHA-1(message) ---------------- */
    if (!EVP_Digest(message, message_len,
                    hash, &hash_len, EVP_sha1(), NULL))
        goto cleanup;

    /* -------------- Build EC_KEY object ------------- */
    if (!(ec_key = EC_KEY_new_by_curve_name(NID_secp256k1)))
        goto cleanup;

    group = EC_KEY_get0_group(ec_key);

    /* import private scalar */
    if (!(bn_priv = BN_bin2bn(priv_key, 32, NULL)))
        goto cleanup;

    if (!EC_KEY_set_private_key(ec_key, bn_priv))
        goto cleanup;

    /* derive and set the corresponding public key */
    if (!(pub_key = EC_POINT_new(group)))
        goto cleanup;

    if (!EC_POINT_mul(group, pub_key, bn_priv, NULL, NULL, NULL))
        goto cleanup;

    if (!EC_KEY_set_public_key(ec_key, pub_key))
        goto cleanup;

    /* --------------- Actual ECDSA sign -------------- */
    /* sig will be allocated by OpenSSL (OPENSSL_malloc) */
    *sig = NULL;
    if (!ECDSA_sign(0, hash, hash_len, NULL, sig_len, ec_key))
        goto cleanup;                            /* just to get sig_len */

    if (!(*sig = OPENSSL_malloc(*sig_len)))
        goto cleanup;

    if (!ECDSA_sign(0, hash, hash_len, *sig, sig_len, ec_key))
        goto cleanup;

    /* Success! */
    ret = 0;

cleanup:
    if (ret != 0 && *sig) { OPENSSL_free(*sig); *sig = NULL; }
    EC_POINT_free(pub_key);
    BN_clear_free(bn_priv);
    EC_KEY_free(ec_key);
    return ret;
}

/* -------------------- Small driver -------------------- */

static void print_hex(const unsigned char *buf, unsigned int len)
{
    for (unsigned int i = 0; i < len; ++i)
        printf("%02x", buf[i]);
    putchar('\n');
}

int main(void)
{
    const unsigned char msg[] = "hello";
    const unsigned char raw_priv[32] =
        "qwertyuiopasdfghjklzxcvbnmqwerty";   /* exactly 32 bytes */

    unsigned char *signature = NULL;
    unsigned int   sig_len   = 0;

    if (sign_message(msg, sizeof(msg) - 1, raw_priv,
                     &signature, &sig_len) != 0)
    {
        fprintf(stderr, "Signing failed: %s\n",
                ERR_error_string(ERR_get_error(), NULL));
        return 1;
    }

    printf("Signature (DER, %u bytes):\n", sig_len);
    print_hex(signature, sig_len);

    OPENSSL_free(signature);
    return 0;
}