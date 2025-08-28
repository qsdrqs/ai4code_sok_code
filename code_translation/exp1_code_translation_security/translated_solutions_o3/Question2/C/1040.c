/*
 * sign_message.c
 *
 * C translation of:
 *     sk = SigningKey.from_string(private_key, curve=SECP256k1)
 *     signature = sk.sign(message)
 */

#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/bn.h>
#include <string.h>
#include <stdlib.h>

/*
 * Return value:
 *   On success  : pointer to DER-encoded signature (malloc'ed)
 *   On failure  : NULL
 * Out parameter:
 *   *sig_len is set to the number of bytes placed in the signature buffer
 */
unsigned char *sign_message(const unsigned char  *message,
                            size_t                message_len,
                            const unsigned char  *private_key,   /* 32-byte raw */
                            size_t                private_key_len,
                            size_t               *sig_len)
{
    EC_KEY  *ec_key  = NULL;
    BIGNUM  *priv_bn = NULL;
    unsigned char *sig = NULL;
    int ok = 0;         /* becomes 1 on success */

    if (sig_len == NULL || private_key_len == 0)
        return NULL;

    *sig_len = 0;

    /* 1. Create a secp256k1 key object */
    ec_key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!ec_key) goto cleanup;

    /* 2. Load the raw 32-byte private key */
    priv_bn = BN_bin2bn(private_key, (int)private_key_len, NULL);
    if (!priv_bn) goto cleanup;

    if (!EC_KEY_set_private_key(ec_key, priv_bn))
        goto cleanup;

    /* 3. Derive and set the corresponding public key (some OpenSSL versions
          require it before signing).                                       */
    {
        const EC_GROUP *group = EC_KEY_get0_group(ec_key);
        EC_POINT *pub = EC_POINT_new(group);
        if (!pub) goto cleanup;

        if (!EC_POINT_mul(group, pub, priv_bn, NULL, NULL, NULL)) {
            EC_POINT_free(pub);
            goto cleanup;
        }
        if (!EC_KEY_set_public_key(ec_key, pub)) {
            EC_POINT_free(pub);
            goto cleanup;
        }
        EC_POINT_free(pub);
    }

    /* 4. Allocate buffer large enough for the signature */
    *sig_len = (size_t)ECDSA_size(ec_key);
    sig = (unsigned char *)malloc(*sig_len);
    if (!sig) goto cleanup;

    /* 5. Sign */
    if (!ECDSA_sign(0,                     /* type (unused)            */
                    message, (int)message_len,
                    sig, (unsigned int *)sig_len,
                    ec_key))
        goto cleanup;

    ok = 1;    /* success */

cleanup:
    if (!ok) {
        free(sig);
        sig = NULL;
        *sig_len = 0;
    }
    BN_free(priv_bn);
    EC_KEY_free(ec_key);

    return sig;
}

/* ------------------------------------------------------------------------ */
/* Optional demo / test harness                                             */
/* (Remove main() if only the library function is needed.)                  */

#ifdef TEST_SIGN_MESSAGE

#include <stdio.h>

int main(void)
{
    /* Example 32-byte private key (DON’T use in production!) */
    const unsigned char priv[32] =
        { 0x1f,0x8b,0xd4,0xaf,0xa3,0xc1,0x29,0x56,
          0x34,0xee,0x88,0x55,0xda,0x6b,0x7d,0x36,
          0xa4,0xa1,0x30,0x5d,0xe3,0xf1,0x8a,0xee,
          0x95,0x3e,0xc1,0x75,0x23,0xb3,0x4d,0x9c };

    const char *msg = "hello world";
    size_t sig_len;
    unsigned char *sig = sign_message((const unsigned char *)msg,
                                      strlen(msg),
                                      priv, sizeof(priv),
                                      &sig_len);

    if (!sig) {
        fprintf(stderr, "Signing failed\n");
        return 1;
    }

    printf("Signature (%zu bytes):\n", sig_len);
    for (size_t i = 0; i < sig_len; ++i)
        printf("%02x", sig[i]);
    printf("\n");

    free(sig);
    return 0;
}

#endif /* TEST_SIGN_MESSAGE */