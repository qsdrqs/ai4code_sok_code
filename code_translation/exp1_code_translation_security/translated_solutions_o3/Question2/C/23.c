/*
 *  gcc -Wall -Wextra -O2 ecdsa_example.c -lcrypto -o ecdsa_example
 *
 *  Same behaviour as the original Python snippet that used the `ecdsa`
 *  PyPI package:
 *
 *      >>> from ecdsa import SigningKey
 *      >>> print(SigningKey.generate().sign(b"i am a message"))
 */

#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/err.h>

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* --------------------------------------------------------------------------
 * Sign `message` with key `eckey`.
 * The function allocates a buffer large enough to hold the signature;
 * its address is returned through the normal function value,
 * and the actual signature length through *sig_len.
 * Caller must free() the returned buffer.
 * -------------------------------------------------------------------------- */
static unsigned char *sign_message(const unsigned char  *message,
                                   size_t                message_len,
                                   EC_KEY               *eckey,
                                   unsigned int         *sig_len)
{
    if (!eckey) {
        return NULL;
    }

    /* Maximum size the signature can take for this key */
    int max_len = ECDSA_size(eckey);
    if (max_len <= 0) {
        return NULL;
    }

    unsigned char *sig = malloc((size_t)max_len);
    if (!sig) {
        return NULL;
    }

    if (ECDSA_sign(0, message, (int)message_len, sig, sig_len, eckey) == 0) {
        free(sig);
        return NULL;
    }

    return sig;  /* success */
}

int main(void)
{
    /* ---- 1. Generate a fresh EC key on the NIST-192p curve ------------ */
    EC_KEY *eckey = EC_KEY_new_by_curve_name(NID_X9_62_prime192v1);  /* NIST192p */
    if (!eckey) {
        fprintf(stderr, "EC_KEY_new_by_curve_name failed\n");
        return EXIT_FAILURE;
    }
    if (EC_KEY_generate_key(eckey) == 0) {
        fprintf(stderr, "EC_KEY_generate_key failed\n");
        EC_KEY_free(eckey);
        return EXIT_FAILURE;
    }

    /* ---- 2. Sign the message ----------------------------------------- */
    const char *msg = "i am a message";
    unsigned int sig_len = 0;

    unsigned char *sig = sign_message((const unsigned char *)msg,
                                      strlen(msg),
                                      eckey,
                                      &sig_len);
    if (!sig) {
        fprintf(stderr, "Signing failed\n");
        EC_KEY_free(eckey);
        return EXIT_FAILURE;
    }

    /* ---- 3. Print the result in hex ---------------------------------- */
    printf("Signature (%u bytes): ", sig_len);
    for (unsigned int i = 0; i < sig_len; ++i) {
        printf("%02x", sig[i]);
    }
    putchar('\n');

    /* ---- 4. Clean up -------------------------------------------------- */
    free(sig);
    EC_KEY_free(eckey);

    return EXIT_SUCCESS;
}