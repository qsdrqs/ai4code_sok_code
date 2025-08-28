/* encrypt_decrypt.c
 *
 * A C translation of the original Python helpers:
 *     encrypt(message, key)
 *     decrypt(message, key)
 *
 * The code relies on a tiny C Fernet wrapper that provides the
 * following functions (all supplied by your dependencies):
 *
 *   fernet_ctx  *fernet_new(const uint8_t *key, size_t key_len);
 *   void         fernet_free(fernet_ctx *ctx);
 *   uint8_t     *fernet_encrypt(fernet_ctx *ctx,
 *                               const uint8_t *plaintext,
 *                               size_t plaintext_len,
 *                               size_t *ciphertext_len_out);
 *   uint8_t     *fernet_decrypt(fernet_ctx *ctx,
 *                               const uint8_t *ciphertext,
 *                               size_t ciphertext_len,
 *                               size_t *plaintext_len_out);
 *
 * The caller is responsible for freeing the returned buffer
 * (malloc-allocated) from encrypt/decrypt.
 */

#include <stddef.h>   /* size_t  */
#include <stdint.h>   /* uint8_t */
#include <stdlib.h>   /* malloc, free */
#include <string.h>   /* strlen  */

#include "fernet.h"   /* Provided by the build system */

/* ------------------------------------------------------------- */
/* Helper: create ctx, encrypt, clean up, and return cipher text */
/* ------------------------------------------------------------- */
uint8_t *encrypt(const char *message,
                 const char *key,
                 size_t     *ciphertext_len_out)
{
    if (!message || !key || !ciphertext_len_out)
        return NULL;

    /* 1. Initialise Fernet context with the key */
    fernet_ctx *ctx = fernet_new((const uint8_t *)key, strlen(key));
    if (!ctx)
        return NULL;

    /* 2. Perform the encryption */
    uint8_t *ciphertext =
        fernet_encrypt(ctx,
                       (const uint8_t *)message,
                       strlen(message),
                       ciphertext_len_out);

    /* 3. Clean-up */
    fernet_free(ctx);
    return ciphertext;  /* malloc-allocated, caller must free() */
}

/* ------------------------------------------------------------- */
/* Helper: create ctx, decrypt, clean up, and return plain text  */
/* ------------------------------------------------------------- */
uint8_t *decrypt(const uint8_t *ciphertext,
                 size_t         ciphertext_len,
                 const char    *key,
                 size_t        *plaintext_len_out)
{
    if (!ciphertext || !key || !plaintext_len_out)
        return NULL;

    /* 1. Initialise Fernet context with the key */
    fernet_ctx *ctx = fernet_new((const uint8_t *)key, strlen(key));
    if (!ctx)
        return NULL;

    /* 2. Perform the decryption */
    uint8_t *plaintext =
        fernet_decrypt(ctx,
                       ciphertext,
                       ciphertext_len,
                       plaintext_len_out);

    /* 3. Clean-up */
    fernet_free(ctx);
    return plaintext;   /* malloc-allocated, caller must free() */
}

/* ------------------------------------------------------------- */
/* (Optional) Convenience wrapper if you prefer char* I/O        */
/* ------------------------------------------------------------- */
char *decrypt_to_cstr(const uint8_t *ciphertext,
                      size_t         ciphertext_len,
                      const char    *key)
{
    size_t plain_len = 0;
    uint8_t *plain_bin = decrypt(ciphertext, ciphertext_len, key, &plain_len);
    if (!plain_bin)
        return NULL;

    /* Allocate one extra byte for the terminating NUL */
    char *ret = (char *)malloc(plain_len + 1);
    if (!ret) {
        free(plain_bin);
        return NULL;
    }
    memcpy(ret, plain_bin, plain_len);
    ret[plain_len] = '\0';   /* NUL-terminate */

    free(plain_bin);
    return ret;              /* caller must free() */
}