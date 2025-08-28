/*  sign.c
 *
 *  Equivalent of:
 *      from ecdsa import SigningKey
 *      def sign(inp, key):
 *          return key.sign(inp.encode('utf-8'))
 *      sk = SigningKey.generate()
 *      print(sign("Hello world", sk))
 *
 *  Compile  :  gcc -Wall -O2 sign.c -o sign -lcrypto
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* OpenSSL headers */
#include <openssl/ec.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <openssl/ecdsa.h>

/* -------------------------------------------------------------------------- */
/* helper: sign a UTF-8 string with an EC key, return malloc’ed DER blob      */
/* -------------------------------------------------------------------------- */
unsigned char *sign_message(const char *msg, EC_KEY *key,
                            unsigned int *sig_len_out)
{
    /* -------- 1. hash the message (SHA-256) ------------------------------- */
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256((const unsigned char *)msg, strlen(msg), hash);

    /* -------- 2. allocate buffer large enough for DER signature ----------- */
    unsigned int sig_len = ECDSA_size(key);      /* worst-case length */
    unsigned char *sig   = malloc(sig_len);
    if (!sig) return NULL;

    /* -------- 3. perform the signature ----------------------------------- */
    if (!ECDSA_sign(0, hash, SHA256_DIGEST_LENGTH, sig, &sig_len, key))
    {
        /* signing failed */
        free(sig);
        return NULL;
    }

    *sig_len_out = sig_len;      /* return the real length */
    return sig;
}

/* -------------------------------------------------------------------------- */
int main(void)
{
    const char *text = "Hello world";

    /* ------------------------------------------------------------------ */
    /* Generate a fresh private (signing) key on curve prime256v1.        */
    /* ------------------------------------------------------------------ */
    EC_KEY *sk = EC_KEY_new_by_curve_name(NID_X9_62_prime256v1);
    if (!sk)
    {
        fprintf(stderr, "Could not create EC key object\n");
        return EXIT_FAILURE;
    }
    if (!EC_KEY_generate_key(sk))
    {
        fprintf(stderr, "Key generation failed\n");
        EC_KEY_free(sk);
        return EXIT_FAILURE;
    }

    /* ------------------------------------------------------------------ */
    /* Sign the message                                                   */
    /* ------------------------------------------------------------------ */
    unsigned int sig_len = 0;
    unsigned char *signature = sign_message(text, sk, &sig_len);
    if (!signature)
    {
        fprintf(stderr, "Signing failed\n");
        EC_KEY_free(sk);
        return EXIT_FAILURE;
    }

    /* ------------------------------------------------------------------ */
    /* Print the DER-encoded signature in hexadecimal, like Python prints */
    /* a bytes object.                                                    */
    /* ------------------------------------------------------------------ */
    printf("Signature (%u bytes): ", sig_len);
    for (unsigned int i = 0; i < sig_len; ++i)
        printf("%02x", signature[i]);
    puts("");

    /* ------------------------------------------------------------------ */
    /* Clean up                                                           */
    /* ------------------------------------------------------------------ */
    free(signature);
    EC_KEY_free(sk);
    return EXIT_SUCCESS;
}