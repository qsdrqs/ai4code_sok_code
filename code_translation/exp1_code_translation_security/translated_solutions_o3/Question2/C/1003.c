/*
 *  nist256_ecdsa.c
 *
 *  Drop-in replacement for the two Python helpers:
 *
 *      sign_nist256()   – returns r||s (64-byte) signature
 *      verify_nist256() – returns 1 (true) / 0 (false)
 *
 *  Build example (OpenSSL >= 1.0.2):
 *      gcc -Wall -O2 nist256_ecdsa.c -o nist256_ecdsa -lcrypto
 */

#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <string.h>
#include <stdlib.h>
#include <stdint.h>

/* ------------------------------------------------------------------ */
/* small utility: convert BIGNUM to fixed-size big-endian byte string  */
static int bn2bin_fixed(const BIGNUM *bn, unsigned char *out, int size)
{
    int bn_len = BN_num_bytes(bn);
    if (bn_len < 0 || bn_len > size)           /* shouldn’t ever happen   */
        return 0;

    memset(out, 0, size - bn_len);             /* left-pad with zeros     */
    BN_bn2bin(bn, out + (size - bn_len));
    return 1;
}
/* ------------------------------------------------------------------ */

int sign_nist256(const unsigned char *msg,
                 size_t                 msg_len,
                 const unsigned char   *priv_raw,   /* 32-byte scalar  */
                 unsigned char        **sig_out,    /* malloc’ed 64 B  */
                 size_t               *sig_len_out) /* always 64       */
{
    int          ok   = 0;
    EC_KEY      *key  = NULL;
    EC_GROUP    *grp  = NULL;
    EC_POINT    *pub  = NULL;
    BIGNUM      *priv = NULL;
    ECDSA_SIG   *sig  = NULL;
    unsigned char hash[SHA256_DIGEST_LENGTH];
    unsigned char *buf = NULL;

    /* -------- build EC_KEY from raw 32-byte private scalar -------- */
    grp = EC_GROUP_new_by_curve_name(NID_X9_62_prime256v1);
    if (!grp) goto done;

    key = EC_KEY_new();
    if (!key) goto done;
    if (!EC_KEY_set_group(key, grp)) goto done;

    priv = BN_bin2bn(priv_raw, 32, NULL);
    if (!priv) goto done;
    if (!EC_KEY_set_private_key(key, priv)) goto done;

    /* OpenSSL wants a public key as well – derive it (G * priv)      */
    pub = EC_POINT_new(grp);
    if (!pub) goto done;
    if (!EC_POINT_mul(grp, pub, priv, NULL, NULL, NULL)) goto done;
    if (!EC_KEY_set_public_key(key, pub)) goto done;

    /* ---------------------- hash and sign ------------------------- */
    SHA256(msg, msg_len, hash);

    sig = ECDSA_do_sign(hash, sizeof(hash), key);
    if (!sig) goto done;

    /* r||s concatenation, 32 bytes each                              */
    buf = (unsigned char *)malloc(64);
    if (!buf) goto done;

    const BIGNUM *r, *s;
#if OPENSSL_VERSION_NUMBER >= 0x10100000L
    ECDSA_SIG_get0(sig, &r, &s);
#else
    r = sig->r;  s = sig->s;
#endif
    if (!bn2bin_fixed(r, buf,      32)) goto done;
    if (!bn2bin_fixed(s, buf + 32, 32)) goto done;

    /* hand over the result to caller                                */
    *sig_out     = buf;
    *sig_len_out = 64;
    buf          = NULL;   /* ownership transferred                   */
    ok           = 1;

done:
    if (buf)   free(buf);
    if (sig)   ECDSA_SIG_free(sig);
    if (pub)   EC_POINT_free(pub);
    if (priv)  BN_free(priv);
    if (key)   EC_KEY_free(key);
    if (grp)   EC_GROUP_free(grp);
    return ok;
}

/* ------------------------------------------------------------------ */

int verify_nist256(const unsigned char *msg,
                   size_t               msg_len,
                   const unsigned char *sig,         /* r||s 64-bytes  */
                   size_t               sig_len,      /* must be 64     */
                   const unsigned char *pub_raw)      /* X||Y 64-bytes  */
{
    int          ret  = 0;
    EC_KEY      *key  = NULL;
    EC_GROUP    *grp  = NULL;
    EC_POINT    *pub  = NULL;
    BIGNUM      *x    = NULL;
    BIGNUM      *y    = NULL;
    ECDSA_SIG   *esig = NULL;
    unsigned char hash[SHA256_DIGEST_LENGTH];

    if (sig_len != 64)                 /* Python helper always uses 64 */
        return 0;

    /* ---------------- recreate public key ------------------------- */
    grp = EC_GROUP_new_by_curve_name(NID_X9_62_prime256v1);
    if (!grp) goto done;

    key = EC_KEY_new();
    if (!key) goto done;
    if (!EC_KEY_set_group(key, grp)) goto done;

    x   = BN_bin2bn(pub_raw,      32, NULL);
    y   = BN_bin2bn(pub_raw + 32, 32, NULL);
    if (!x || !y) goto done;

    pub = EC_POINT_new(grp);
    if (!pub) goto done;
    if (!EC_POINT_set_affine_coordinates_GFp(grp, pub, x, y, NULL)) goto done;
    if (!EC_KEY_set_public_key(key, pub)) goto done;

    /* ---------------- convert r||s to ECDSA_SIG ------------------- */
    BIGNUM *r = BN_bin2bn(sig,      32, NULL);
    BIGNUM *s = BN_bin2bn(sig + 32, 32, NULL);
    if (!r || !s) goto done;

    esig = ECDSA_SIG_new();
    if (!esig) goto done;
#if OPENSSL_VERSION_NUMBER >= 0x10100000L
    if (!ECDSA_SIG_set0(esig, r, s)) goto done;
#else
    esig->r = r;  esig->s = s;                /* transfer ownership      */
#endif

    /* -------------------- verify signature ------------------------ */
    SHA256(msg, msg_len, hash);

    ret = ECDSA_do_verify(hash, sizeof(hash), esig, key);
    ret = (ret == 1);                          /* 1 = valid, 0/-1 = bad  */

done:
    if (esig) ECDSA_SIG_free(esig);
    if (pub)  EC_POINT_free(pub);
    if (x)    BN_free(x);
    if (y)    BN_free(y);
    if (key)  EC_KEY_free(key);
    if (grp)  EC_GROUP_free(grp);
    return ret;
}