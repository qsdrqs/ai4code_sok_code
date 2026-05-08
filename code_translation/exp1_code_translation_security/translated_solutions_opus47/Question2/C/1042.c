#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/bn.h>
#include <openssl/sha.h>

/**
 * Signs a given message using ECDSA signing key (SECP256k1 curve)
 *
 * @param message           message to be signed
 * @param message_len       length of the message in bytes
 * @param private_key       raw private key bytes (32 bytes for SECP256k1)
 * @param private_key_len   length of the private key in bytes
 * @param signature_out     output buffer for signature (must be at least 64 bytes)
 * @param signature_len_out pointer to store the actual signature length
 * @return 1 on success, 0 on failure
 */
int sign_message(const unsigned char *message, size_t message_len,
                 const unsigned char *private_key, size_t private_key_len,
                 unsigned char *signature_out, size_t *signature_len_out) {
    int ret = 0;
    EC_KEY *ec_key = NULL;
    BIGNUM *priv_bn = NULL;
    ECDSA_SIG *sig = NULL;
    const BIGNUM *r = NULL, *s = NULL;
    unsigned char hash[SHA_DIGEST_LENGTH];
    int r_len, s_len;

    /* Create EC_KEY object with SECP256k1 curve (curve=ecdsa.SECP256k1) */
    ec_key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!ec_key) goto cleanup;

    /* Convert raw private key bytes to BIGNUM
     * (ecdsa.SigningKey.from_string(private_key, curve=ecdsa.SECP256k1)) */
    priv_bn = BN_bin2bn(private_key, (int)private_key_len, NULL);
    if (!priv_bn) goto cleanup;

    /* Set the private key on the EC_KEY object */
    if (!EC_KEY_set_private_key(ec_key, priv_bn)) goto cleanup;

    /* Hash the message with SHA-1 (default hash used by python-ecdsa) */
    SHA1(message, message_len, hash);

    /* Sign the hash using ECDSA (signer.sign(message)) */
    sig = ECDSA_do_sign(hash, SHA_DIGEST_LENGTH, ec_key);
    if (!sig) goto cleanup;

    /* Extract r and s components from the signature */
    ECDSA_SIG_get0(sig, &r, &s);

    /* Convert signature to raw 64-byte format (r || s),
     * matching python-ecdsa's default output */
    memset(signature_out, 0, 64);
    r_len = BN_num_bytes(r);
    s_len = BN_num_bytes(s);
    BN_bn2bin(r, signature_out + (32 - r_len));
    BN_bn2bin(s, signature_out + 32 + (32 - s_len));
    *signature_len_out = 64;

    ret = 1;

cleanup:
    if (priv_bn) BN_free(priv_bn);
    if (sig) ECDSA_SIG_free(sig);
    if (ec_key) EC_KEY_free(ec_key);
    return ret;
}