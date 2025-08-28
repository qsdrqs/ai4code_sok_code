#include "sign.h"

#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <openssl/err.h>

#include <stdlib.h>
#include <string.h>

unsigned char *sign_message(const uint8_t *message,
                            size_t          message_len,
                            const uint8_t  *private_key,
                            size_t         *signature_len)
{
    EC_KEY   *key        = NULL;
    BIGNUM   *priv_bn    = NULL;
    EC_POINT *pub_point  = NULL;
    unsigned char digest[SHA_DIGEST_LENGTH];
    unsigned char *signature = NULL;
    unsigned int   sig_len   = 0;

    /* -------------------------------------------------------------
     * 1. Build an EC_KEY that holds the secp256k1 private key
     * ---------------------------------------------------------- */
    key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!key) goto err;

    /* import raw 32-byte private key */
    priv_bn = BN_bin2bn(private_key, 32, NULL);
    if (!priv_bn) goto err;
    if (!EC_KEY_set_private_key(key, priv_bn)) goto err;

    /* OpenSSL wants a public key inside EC_KEY before it can sign */
    const EC_GROUP *group = EC_KEY_get0_group(key);
    pub_point = EC_POINT_new(group);
    if (!pub_point) goto err;
    if (!EC_POINT_mul(group, pub_point, priv_bn, NULL, NULL, NULL)) goto err;
    if (!EC_KEY_set_public_key(key, pub_point)) goto err;

    /* -------------------------------------------------------------
     * 2. Hash the message           (same default as python-ecdsa)
     *    The ecdsa.SigningKey.sign() default is SHA-1.
     * ---------------------------------------------------------- */
    SHA1(message, message_len, digest);

    /* -------------------------------------------------------------
     * 3. Sign the hash
     * ---------------------------------------------------------- */
    signature = malloc(ECDSA_size(key));          /* big enough buffer */
    if (!signature) goto err;

    if (!ECDSA_sign(0,                       /* hash “type” – 0 is fine */
                    digest,
                    SHA_DIGEST_LENGTH,
                    signature,
                    &sig_len,
                    key))
        goto err;

    /* optionally shrink the buffer to the actual signature size */
    unsigned char *tmp = realloc(signature, sig_len);
    if (tmp) signature = tmp;

    if (signature_len) *signature_len = sig_len;

    /* ---- clean up & success ---- */
    EC_KEY_free(key);
    BN_clear_free(priv_bn);
    EC_POINT_free(pub_point);
    return signature;

err:
    free(signature);
    EC_KEY_free(key);
    BN_clear_free(priv_bn);
    EC_POINT_free(pub_point);
    return NULL;
}