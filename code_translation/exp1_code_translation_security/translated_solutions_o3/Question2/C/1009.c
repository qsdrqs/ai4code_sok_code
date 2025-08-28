/*
 * Compile (Linux / macOS):
 *     gcc -Wall -Wextra -O2 ecdsa_example.c -o ecdsa_example -lcrypto
 *
 * Run:
 *     ./ecdsa_example
 *
 * Expected output:
 *     1
 */

#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <openssl/err.h>

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

/* Helper: print any OpenSSL error and abort */
static void die_openssl(const char *msg)
{
    fprintf(stderr, "%s\n", msg);
    ERR_print_errors_fp(stderr);
    exit(EXIT_FAILURE);
}

/*
 * Equivalent to the Python `sign()` wrapper.
 * On success returns the signature buffer (malloc'ed) and sets *sig_len.
 * Caller must free() the returned buffer.
 */
static unsigned char *sign_message(EC_KEY *sk,
                                   const unsigned char *msg,
                                   size_t msg_len,
                                   unsigned int *sig_len)
{
    /* The Python library hashes internally with SHA-1 by default.
       We mimic that here. */
    unsigned char digest[SHA_DIGEST_LENGTH];

    if (!SHA1(msg, msg_len, digest))
        die_openssl("SHA1() failed");

    unsigned char *sig = malloc(ECDSA_size(sk));
    if (!sig)
    {
        perror("malloc");
        exit(EXIT_FAILURE);
    }

    if (ECDSA_sign(0,                /* type (unused)              */
                   digest,
                   SHA_DIGEST_LENGTH,
                   sig,
                   sig_len,
                   sk) != 1)
        die_openssl("ECDSA_sign() failed");

    return sig;
}

int main(void)
{
    const unsigned char message[] = "hehehe";
    const size_t        msg_len   = sizeof(message) - 1; /* exclude '\0' */

    /* 1. Create a new EC key on curve NIST P-384 (== secp384r1). */
    EC_KEY *sk = EC_KEY_new_by_curve_name(NID_secp384r1);
    if (!sk)
        die_openssl("EC_KEY_new_by_curve_name() failed");

    /* 2. Generate key material (private + public) */
    if (EC_KEY_generate_key(sk) != 1)
        die_openssl("EC_KEY_generate_key() failed");

    /* 3. Sign */
    unsigned int  sig_len = 0;
    unsigned char *sig = sign_message(sk, message, msg_len, &sig_len);

    /* 4. Verify (same digest procedure) */
    unsigned char digest[SHA_DIGEST_LENGTH];
    SHA1(message, msg_len, digest);

    int ok = ECDSA_verify(0,
                          digest,
                          SHA_DIGEST_LENGTH,
                          sig,
                          sig_len,
                          sk);

    /* Print result exactly like Python version: True(1)/False(0) */
    printf("%d\n", ok);

    /* 5. Cleanup */
    free(sig);
    EC_KEY_free(sk);

    return ok == 1 ? EXIT_SUCCESS : EXIT_FAILURE;
}