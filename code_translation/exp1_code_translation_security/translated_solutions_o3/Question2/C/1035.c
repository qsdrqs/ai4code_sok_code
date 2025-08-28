/*  compile with:  gcc ecdsa_example.c -o ecdsa_example -lcrypto           */
/*  (All headers / libraries requested in the exercise are assumed to be   */
/*  available in the build environment.)                                   */

#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

static void die(const char *why)
{
    fprintf(stderr, "%s\n", why);
    exit(EXIT_FAILURE);
}

/* ---------- 1. generate signing / verifying key ----------------------- */

static EC_KEY *generate_key(void)
{
    /* NIST-192p  ==  prime192v1  ==  NID_X9_62_prime192v1 */
    EC_KEY *key = EC_KEY_new_by_curve_name(NID_X9_62_prime192v1);
    if (!key) die("EC_KEY_new_by_curve_name() failed");

    if (EC_KEY_generate_key(key) != 1)
        die("EC_KEY_generate_key() failed");

    return key;          /* contains both private (signing) & public (verify) */
}

/* ---------- 2. sign a message ----------------------------------------- */

static int sign(const unsigned char *msg,
                size_t                 msg_len,
                EC_KEY               *sk,
                unsigned char        **sig,     /* malloc’ed buffer  */
                unsigned int          *sig_len) /* length returned   */
{
    /* determine max possible signature size and allocate */
    int max_len = ECDSA_size(sk);
    *sig = (unsigned char *)malloc(max_len);
    if (!*sig) die("malloc() failed");

    if (ECDSA_sign(0, msg, (int)msg_len, *sig, sig_len, sk) != 1)
        return 0;   /* failure */

    return 1;       /* success */
}

/* ---------- 3. verify a signature ------------------------------------- */

static int verify(const unsigned char *msg,
                  size_t               msg_len,
                  const unsigned char *sig,
                  unsigned int         sig_len,
                  EC_KEY              *vk)      /* verifying key (= public) */
{
    /* returns 1 on success, 0 on failure, -1 on error */
    return ECDSA_verify(0, msg, (int)msg_len, sig, sig_len, vk);
}

/* ---------- 4. demonstration (equivalent to the Python snippet) ------- */

int main(void)
{
    const unsigned char *msg     = (const unsigned char *)"message";
    const size_t         msg_len = strlen((const char *)msg);

    EC_KEY *sk = generate_key();        /* signing key  */
    EC_KEY *vk = sk;                    /* same object → public part used */

    unsigned char *signature = NULL;
    unsigned int   sig_len   = 0;

    if (!sign(msg, msg_len, sk, &signature, &sig_len))
        die("sign() failed");

    int ok = verify(msg, msg_len, signature, sig_len, vk);
    if (ok != 1)
        die("verify() failed");

    puts("Signature verified – test passed!");

    /* cleanup */
    free(signature);
    EC_KEY_free(sk);
    return 0;
}