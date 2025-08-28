/*
 *  sign_message.c
 *
 *  Equivalent of the Python helper
 *
 *      signature = signer.sign(message)
 *
 *  Compiles with:   cc -std=c11 -Wall -Wextra sign_message.c -lcrypto
 */

#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <stdlib.h>
#include <string.h>

/*
 *  sign_message
 *  ------------
 *  Signs an arbitrary byte-buffer with an EC private key (secp256k1).
 *
 *  Parameters
 *      message       : pointer to data to be signed
 *      message_len   : length of the data
 *      privkey_bytes : raw 32-byte secp256k1 private key
 *      signature     : (out) buffer that receives the DER-encoded signature
 *                      → memory is allocated inside the function and
 *                        must be freed by the caller with OPENSSL_free().
 *      sig_len       : (out) length of the resulting signature in bytes
 *
 *  Returns
 *      1 on success, 0 on failure
 */
int sign_message(const unsigned char *message,
                 size_t              message_len,
                 const unsigned char privkey_bytes[32],
                 unsigned char     **signature,
                 unsigned int       *sig_len)
{
    int        ok        = 0;          /* pessimistic */
    EC_KEY    *ec_key    = NULL;
    BIGNUM    *priv_bn   = NULL;
    ECDSA_SIG *ecdsa_sig = NULL;
    unsigned char digest[SHA256_DIGEST_LENGTH];

    /* --- 1) Build an EC_KEY object on secp256k1 -------------------------- */
    ec_key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!ec_key) goto cleanup;

    /* Load raw 32-byte secret into a BIGNUM and attach to EC_KEY */
    priv_bn = BN_bin2bn(privkey_bytes, 32, NULL);
    if (!priv_bn || !EC_KEY_set_private_key(ec_key, priv_bn)) goto cleanup;

    /* --- 2) Hash the message (ECDSA requires a fixed-length digest) ------ *
     * The original Python ecdsa.SigningKey signs the *raw* message with
     * a default SHA-1 hash.  In modern code we strongly prefer SHA-256.
     * If an exact SHA-1 clone is required, just swap SHA256() with SHA1().
     */
    SHA256(message, message_len, digest);

    /* --- 3) Produce the signature (ECDSA_do_sign) ------------------------ */
    ecdsa_sig = ECDSA_do_sign(digest, sizeof(digest), ec_key);
    if (!ecdsa_sig) goto cleanup;

    /* --- 4) DER-encode the ECDSA_SIG structure --------------------------- */
    *sig_len = i2d_ECDSA_SIG(ecdsa_sig, NULL);       /* size first          */
    *signature = OPENSSL_malloc(*sig_len);           /* caller frees later  */
    if (!*signature) goto cleanup;

    unsigned char *tmp = *signature;                 /* i2d() mutates ptr   */
    i2d_ECDSA_SIG(ecdsa_sig, &tmp);

    ok = 1;                                          /* success */

cleanup:
    if (!ok && *signature) { OPENSSL_free(*signature); *signature = NULL; }
    EC_KEY_free(ec_key);
    BN_free(priv_bn);
    ECDSA_SIG_free(ecdsa_sig);
    return ok;
}

/* ----------------------------------------------------------------------- *
 *  Small self-test (build only when compiled as a stand-alone translation)
 * ----------------------------------------------------------------------- */
#ifdef TEST_SIGN_MESSAGE
#include <stdio.h>

int main(void)
{
    /* 32-byte dummy private key (DON’T use in production!) */
    const unsigned char priv[32] = {
        0x1f,0x2e,0x3d,0x4c,0x5b,0x6a,0x79,0x88,
        0x97,0xa6,0xb5,0xc4,0xd3,0xe2,0xf1,0x00,
        0x11,0x22,0x33,0x44,0x55,0x66,0x77,0x88,
        0x99,0xaa,0xbb,0xcc,0xdd,0xee,0xff,0x01
    };

    const char *msg = "Hello ECDSA in C!";
    unsigned char *sig = NULL;
    unsigned int   sig_len = 0;

    if (!sign_message((const unsigned char *)msg, strlen(msg),
                      priv, &sig, &sig_len))
    {
        fprintf(stderr, "Signing failed\n");
        return 1;
    }

    printf("Signature (%u bytes):\n", sig_len);
    for (unsigned int i = 0; i < sig_len; ++i)
        printf("%02x", sig[i]);
    puts("");

    OPENSSL_free(sig);
    return 0;
}
#endif