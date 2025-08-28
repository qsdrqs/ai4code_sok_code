/*
 *  Compile :  gcc -Wall -O2 ecdsa_sample.c -o ecdsa_sample -lcrypto
 *
 *  OpenSSL 1.1.x or later is expected.  (If you are on 3.x it works too.)
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/evp.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/err.h>

/* ------------------------------------------------------------ *
 *  Small helper to abort on OpenSSL errors                     *
 * ------------------------------------------------------------ */
static void die(const char *where)
{
    fprintf(stderr, "Failure in %s\n", where);
    ERR_print_errors_fp(stderr);
    exit(EXIT_FAILURE);
}

/* ============================================================ *
 *               Python-like helper wrappers                    *
 * ============================================================ */

/* Generate a signing key (sk) and the verifying key (vk)        *
 * In OpenSSL the same EC_KEY structure contains both parts,     *
 * so we simply return one EC_KEY* and – if the caller wants a   *
 * “public-only” object – we duplicate the key and clear the     *
 * private component.                                            */
static void ecdsa_genkey(EC_KEY **sk, EC_KEY **vk)
{
    int nid = NID_secp384r1;                  /* NIST-384p      */
    EC_KEY *key  = EC_KEY_new_by_curve_name(nid);
    if (!key) die("EC_KEY_new_by_curve_name");

    if (!EC_KEY_generate_key(key))
        die("EC_KEY_generate_key");

    /*  sk  -> full key (private + public) */
    *sk = key;

    /*  vk  -> public only (verifier)      */
    *vk = EC_KEY_dup(key);                   /* duplicate key first          */
    if (!*vk) die("EC_KEY_dup");

    /* Strip the private part from vk */
#if OPENSSL_VERSION_NUMBER >= 0x30000000L
    /* OpenSSL 3.x */
    if (!EVP_PKEY_set_int_param((EVP_PKEY *)*vk, "priv", 0))
        die("Clearing private key");
#else
    /* OpenSSL 1.1.x : set the private BIGNUM to NULL */
    EC_KEY_set_private_key(*vk, NULL);
#endif
}

/* Sign arbitrary bytes.  DER encoded signature is returned.     *
 * Caller owns the returned buffer and must free() it.           */
static unsigned char *ecdsa_sign(EC_KEY *sk,
                                 const unsigned char *msg,
                                 size_t msglen,
                                 unsigned int *siglen)
{
    unsigned char *sig = NULL;

    /* ECDSA_size tells how many bytes max are needed */
    *siglen = ECDSA_size(sk);
    sig = malloc(*siglen);
    if (!sig) die("malloc(sig)");

    if (!ECDSA_sign(0, msg, (int)msglen, sig, siglen, sk))
        die("ECDSA_sign");

    return sig;   /* DER encoded (r,s) pair */
}

/* Verify a signature against a message with the public key      */
static int ecdsa_verify_key(EC_KEY *vk,
                            const unsigned char *sig,
                            unsigned int siglen,
                            const unsigned char *msg,
                            size_t msglen)
{
    int ok = ECDSA_verify(0, msg, (int)msglen, sig, siglen, vk);
    /* ok == 1  => signature valid
     * ok == 0  => invalid
     * ok == -1 => error                              */
    if (ok == -1) die("ECDSA_verify");
    return ok;
}

/* ============================================================ *
 *                       Utility printers                       *
 * ============================================================ */
static void print_hex(const unsigned char *buf, size_t len)
{
    for (size_t i = 0; i < len; ++i)
        printf("%02x", buf[i]);
    putchar('\n');
}

static void print_keys(EC_KEY *sk, EC_KEY *vk)
{
    const BIGNUM     *priv = EC_KEY_get0_private_key(sk);
    const EC_POINT   *pub  = EC_KEY_get0_public_key(sk);
    const EC_GROUP   *grp  = EC_KEY_get0_group(sk);

    /* Print private scalar */
    if (priv)
    {
        char *hex = BN_bn2hex(priv);
        printf("Private key (hex) : %s\n", hex);
        OPENSSL_free(hex);
    }

    /* Print compressed public key bytes */
    int len = EC_POINT_point2oct(grp, pub,
                                 POINT_CONVERSION_COMPRESSED,
                                 NULL, 0, NULL);
    unsigned char *tmp = malloc(len);
    EC_POINT_point2oct(grp, pub, POINT_CONVERSION_COMPRESSED,
                       tmp, len, NULL);
    printf("Public key (hex)  : ");
    print_hex(tmp, len);
    free(tmp);
}

/* ============================================================ *
 *                           main()                             *
 * ============================================================ */
int main(void)
{
    /* Needed on some OpenSSL versions for EVP operations */
    ERR_load_crypto_strings();
    OpenSSL_add_all_algorithms();

    const unsigned char message[] = "This is a test message";
    size_t               msglen   = sizeof(message) - 1;   /* do not include '\0' */

    EC_KEY *sk = NULL, *vk = NULL;
    ecdsa_genkey(&sk, &vk);

    print_keys(sk, vk);

    unsigned int  siglen = 0;
    unsigned char *signature = ecdsa_sign(sk, message, msglen, &siglen);

    printf("Signature (DER, hex): ");
    print_hex(signature, siglen);

    int valid = ecdsa_verify_key(vk, signature, siglen, message, msglen);
    printf("Verification result : %s\n", valid ? "True" : "False");

    /* ---------------------------------------------- *
     *             cleanup                             *
     * ---------------------------------------------- */
    free(signature);
    EC_KEY_free(sk);
    EC_KEY_free(vk);
    EVP_cleanup();
    ERR_free_strings();

    return 0;
}