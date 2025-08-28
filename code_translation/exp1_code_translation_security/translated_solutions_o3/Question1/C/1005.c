/*
 * Example C translation of the Python/Fernet demo.
 *
 * Build (example):  clang -Os -lsodium fernet_demo.c -o fernet_demo
 * Run:              ./fernet_demo
 *
 * The code uses libsodium’s crypto_secretbox_* primitives to achieve roughly
 * the same “sym­metric encryption with authentication” behaviour that
 * Python-Fernet provides.  The surrounding API (encrypt / decrypt) mirrors the
 * Python functions in spirit.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sodium.h>        /* <- provided dependency */

/* ------------------------------------------------------------------------- */
/* Helper: encrypt                                                           */
/* ------------------------------------------------------------------------- */
static void
encrypt(const unsigned char *msg,
        unsigned long long  msg_len,
        const unsigned char key[crypto_secretbox_KEYBYTES],
        unsigned char     **out_ct,          /* ciphertext+nonce  (malloc’d) */
        unsigned long long *out_len)         /* total length      */
{
    unsigned char nonce[crypto_secretbox_NONCEBYTES];
    randombytes_buf(nonce, sizeof nonce);                     /* random IV  */

    *out_len = crypto_secretbox_NONCEBYTES           /* store nonce first */
             + crypto_secretbox_MACBYTES
             + msg_len;

    *out_ct  = malloc(*out_len);
    if (!*out_ct) {
        fprintf(stderr, "malloc failed\n");
        exit(EXIT_FAILURE);
    }

    /* Layout:  [nonce | MAC+ciphertext] */
    memcpy(*out_ct, nonce, crypto_secretbox_NONCEBYTES);

    if (crypto_secretbox_easy(*out_ct + crypto_secretbox_NONCEBYTES,
                              msg,
                              msg_len,
                              nonce,
                              key) != 0) {
        fprintf(stderr, "crypto_secretbox_easy failed\n");
        exit(EXIT_FAILURE);
    }
}

/* ------------------------------------------------------------------------- */
/* Helper: decrypt                                                           */
/* Returns 0 on success, ‑1 on auth failure.                                 */
/* ------------------------------------------------------------------------- */
static int
decrypt(const unsigned char *ct,
        unsigned long long   ct_len,
        const unsigned char  key[crypto_secretbox_KEYBYTES],
        unsigned char      **out_msg,        /* plaintext (malloc’d) */
        unsigned long long  *out_len)
{
    if (ct_len < crypto_secretbox_NONCEBYTES + crypto_secretbox_MACBYTES)
        return -1;  /* clearly invalid */

    const unsigned char *nonce      = ct;
    const unsigned char *boxed_part = ct + crypto_secretbox_NONCEBYTES;
    const unsigned long long boxed_len =
        ct_len - crypto_secretbox_NONCEBYTES;

    *out_len = boxed_len - crypto_secretbox_MACBYTES;
    *out_msg = malloc(*out_len);
    if (!*out_msg) return -1;

    if (crypto_secretbox_open_easy(*out_msg,
                                   boxed_part,
                                   boxed_len,
                                   nonce,
                                   key) != 0) {
        free(*out_msg);                   /* authentication failed */
        return -1;
    }
    return 0;
}

/* ------------------------------------------------------------------------- */
/* Demo driver (mirrors the original Python __main__)                        */
/* ------------------------------------------------------------------------- */
int
main(void)
{
    if (sodium_init() == -1) {            /* initialise libsodium */
        fprintf(stderr, "sodium_init() failed\n");
        return EXIT_FAILURE;
    }

    /* Equivalent to Fernet.generate_key() */
    unsigned char key[crypto_secretbox_KEYBYTES];
    randombytes_buf(key, sizeof key);

    const char *msg_str =
        "a much longer message with punctuation!";
    const unsigned long long msg_len = strlen(msg_str);

    /* ---- encrypt ---- */
    unsigned char *cipher        = NULL;
    unsigned long long cipherlen = 0;

    encrypt((const unsigned char *)msg_str, msg_len,
            key, &cipher, &cipherlen);

    /* ---- decrypt ---- */
    unsigned char *plain        = NULL;
    unsigned long long plainlen = 0;

    if (decrypt(cipher, cipherlen, key, &plain, &plainlen) != 0) {
        fprintf(stderr, "Decryption / authentication failed\n");
        free(cipher);
        return EXIT_FAILURE;
    }

    /* ---- show results ---- */
    /* Print ciphertext as hex */
    printf("Ciphertext (%llu bytes):\n", cipherlen);
    for (unsigned long long i = 0; i < cipherlen; ++i)
        printf("%02x", cipher[i]);
    printf("\n");

    /* Print recovered plaintext */
    printf("Plaintext:\n%.*s\n", (int)plainlen, plain);

    /* clean up */
    sodium_memzero(key, sizeof key);  /* scrub key from memory */
    free(cipher);
    free(plain);

    return EXIT_SUCCESS;
}