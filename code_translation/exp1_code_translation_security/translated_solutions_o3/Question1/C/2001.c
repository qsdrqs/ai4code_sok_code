/* fernet_util.c
 *
 * 1-to-1 translation of the Python helpers
 *     encrypt(msg,  key)  ->  encrypt_msg(...)
 *     decrypt(ct,   key)  ->  decrypt_msg(...)
 *
 * The underlying crypto is still Fernet
 * (AES-128-CBC + HMAC-SHA256, URL-safe-base64 payloads).
 *
 * Build example:  cc -Wall -O2 -c fernet_util.c
 *                 cc -o demo demo.c fernet_util.o -lfernet
 */

#include <stdlib.h>
#include <stdint.h>
#include "fernet.h"         /* provided with the project */

/* ------------------------------------------------------------------------
   Encrypt
   ------------------------------------------------------------------------ */
unsigned char *encrypt_msg(const unsigned char *msg,
                           size_t               msg_len,
                           const char          *key,      /* base64, 32-byte */
                           size_t              *out_len)  /* returned length */
{
    /* Create a Fernet context that owns the decoded key, HMAC ctx, etc. */
    FernetContext *ctx = fernet_init(key);        /* <-- provided helper */
    if (ctx == NULL)
        return NULL;

    /*
     * Ciphertext is always larger than plaintext by a fixed amount
     * (timestamp + IV + HMAC + some framing).  The helper macro below
     * is exported by `fernet.h`.
     */
    size_t max_ct = msg_len + FERNET_OVERHEAD;
    unsigned char *cipher = malloc(max_ct);
    if (cipher == NULL) {
        fernet_free(ctx);
        return NULL;
    }

    /* Do the real work; the helper returns the exact #bytes written. */
    *out_len = fernet_encrypt(ctx, msg, msg_len, cipher);

    fernet_free(ctx);
    return cipher;   /* caller must free() */
}

/* ------------------------------------------------------------------------
   Decrypt
   ------------------------------------------------------------------------ */
unsigned char *decrypt_msg(const unsigned char *cipher,
                           size_t               cipher_len,
                           const char          *key,
                           size_t              *out_len)
{
    FernetContext *ctx = fernet_init(key);
    if (ctx == NULL)
        return NULL;

    /* Plaintext will never be longer than ciphertext */
    unsigned char *plain = malloc(cipher_len);
    if (plain == NULL) {
        fernet_free(ctx);
        return NULL;
    }

    /*
     * fernet_decrypt() returns:
     *     – number of bytes written  on success
     *     – (size_t)-1              on authentication / parsing failure
     */
    *out_len = fernet_decrypt(ctx, cipher, cipher_len, plain);
    if (*out_len == (size_t)-1) {
        /* auth failed */
        free(plain);
        plain = NULL;
    }

    fernet_free(ctx);
    return plain;    /* caller must free() when not NULL */
}