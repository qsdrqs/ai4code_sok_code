#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/bn.h>
#include <openssl/sha.h>
#include <openssl/obj_mac.h>
#include <string.h>

/**
 * Sign a message using ECDSA with NIST P-256 curve and SHA-256.
 *
 * @param message         The message to sign.
 * @param message_len     The length of the message in bytes.
 * @param signing_key_raw The 32-byte raw private key.
 * @param signature_out   Output buffer for the 64-byte signature (r || s).
 * @return 1 on success, 0 on failure.
 */
int sign_nist256(const unsigned char *message, size_t message_len,
                 const unsigned char *signing_key_raw,
                 unsigned char *signature_out) {
    int ret = 0;
    EC_KEY *key = NULL;
    BIGNUM *priv_bn = NULL;
    ECDSA_SIG *sig = NULL;
    const BIGNUM *r = NULL, *s = NULL;
    unsigned char hash[SHA256_DIGEST_LENGTH];
    int r_len, s_len;

    /* Compute SHA-256 hash of the message */
    SHA256(message, message_len, hash);

    /* Create EC_KEY with the NIST P-256 curve */
    key = EC_KEY_new_by_curve_name(NID_X9_62_prime256v1);
    if (!key) goto cleanup;

    /* Convert raw private key (32 bytes, big-endian) to BIGNUM */
    priv_bn = BN_bin2bn(signing_key_raw, 32, NULL);
    if (!priv_bn) goto cleanup;

    if (!EC_KEY_set_private_key(key, priv_bn)) goto cleanup;

    /* Sign the hash */
    sig = ECDSA_do_sign(hash, SHA256_DIGEST_LENGTH, key);
    if (!sig) goto cleanup;

    /* Extract r and s, write them as 32-byte big-endian with leading zeros */
    ECDSA_SIG_get0(sig, &r, &s);
    r_len = BN_num_bytes(r);
    s_len = BN_num_bytes(s);

    memset(signature_out, 0, 64);
    BN_bn2bin(r, signature_out + (32 - r_len));
    BN_bn2bin(s, signature_out + 32 + (32 - s_len));

    ret = 1;

cleanup:
    if (sig)     ECDSA_SIG_free(sig);
    if (priv_bn) BN_clear_free(priv_bn);
    if (key)     EC_KEY_free(key);
    return ret;
}

/**
 * Verify an ECDSA signature using NIST P-256 curve and SHA-256.
 *
 * @param message           The message that was signed.
 * @param message_len       The length of the message in bytes.
 * @param signature         The 64-byte signature (r || s).
 * @param verifying_key_raw The 64-byte raw public key (x || y).
 * @return 1 if the signature is valid, 0 otherwise.
 */
int verify_nist256(const unsigned char *message, size_t message_len,
                   const unsigned char *signature,
                   const unsigned char *verifying_key_raw) {
    int ret = 0;
    int verify_result;
    EC_KEY *key = NULL;
    EC_POINT *pub_point = NULL;
    const EC_GROUP *group = NULL;
    BIGNUM *x = NULL, *y = NULL, *r = NULL, *s = NULL;
    ECDSA_SIG *sig = NULL;
    unsigned char hash[SHA256_DIGEST_LENGTH];

    /* Compute SHA-256 hash of the message */
    SHA256(message, message_len, hash);

    /* Create EC_KEY with the NIST P-256 curve */
    key = EC_KEY_new_by_curve_name(NID_X9_62_prime256v1);
    if (!key) goto cleanup;

    group = EC_KEY_get0_group(key);

    /* Parse raw public key (x || y, each 32 bytes big-endian) */
    x = BN_bin2bn(verifying_key_raw, 32, NULL);
    y = BN_bin2bn(verifying_key_raw + 32, 32, NULL);
    if (!x || !y) goto cleanup;

    pub_point = EC_POINT_new(group);
    if (!pub_point) goto cleanup;

    if (!EC_POINT_set_affine_coordinates(group, pub_point, x, y, NULL)) goto cleanup;
    if (!EC_KEY_set_public_key(key, pub_point)) goto cleanup;

    /* Parse the signature into an ECDSA_SIG */
    sig = ECDSA_SIG_new();
    if (!sig) goto cleanup;

    r = BN_bin2bn(signature, 32, NULL);
    s = BN_bin2bn(signature + 32, 32, NULL);
    if (!r || !s) goto cleanup;

    if (!ECDSA_SIG_set0(sig, r, s)) goto cleanup;
    r = NULL;  /* ownership transferred to sig */
    s = NULL;

    /* ECDSA_do_verify returns 1 (valid), 0 (invalid), or -1 (error).
       We map anything other than 1 to 0, mirroring the Python except-branch. */
    verify_result = ECDSA_do_verify(hash, SHA256_DIGEST_LENGTH, sig, key);
    ret = (verify_result == 1) ? 1 : 0;

cleanup:
    if (sig)       ECDSA_SIG_free(sig);
    if (r)         BN_free(r);
    if (s)         BN_free(s);
    if (x)         BN_free(x);
    if (y)         BN_free(y);
    if (pub_point) EC_POINT_free(pub_point);
    if (key)       EC_KEY_free(key);
    return ret;
}