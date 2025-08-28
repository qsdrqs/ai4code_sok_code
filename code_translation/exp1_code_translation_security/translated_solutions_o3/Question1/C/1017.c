/* ------------------------------------------------------------------------
 *  aes_eax_helpers.c
 *
 *  C translation of
 *
 *      ciphertext_tuple = encrypt(key, plaintext)
 *      plaintext        = decrypt(key, ciphertext_tuple)
 *
 *  Dependencies (NOT included here):
 *      – A random–bytes generator      :  random_bytes()
 *      – AES/EAX primitives            :  AES_EAX_CTX,
 *                                         AES_EAX_init(),
 *                                         AES_EAX_encrypt(),
 *                                         AES_EAX_decrypt(),
 *                                         AES_EAX_final()
 * -----------------------------------------------------------------------*/

#include <stdlib.h>
#include <stdint.h>
#include <string.h>

/* ------------------------- Helper data struct ------------------------- */

typedef struct
{
    /* Returned by encrypt(), consumed by decrypt()            */
    uint8_t  *nonce;          /* buffer of nonce_len bytes     */
    size_t    nonce_len;

    uint8_t  *tag;            /* buffer of tag_len   bytes     */
    size_t    tag_len;

    uint8_t  *ciphertext;     /* buffer of ciphertext_len bytes*/
    size_t    ciphertext_len;
} encrypted_blob_t;


/* ---------------------------  encrypt()  ----------------------------- */
/*   key/key_len      : secret symmetric key (16/24/32 B for AES)       */
/*   plaintext/len    : message to be encrypted                         */
/*   returns          : encrypted_blob_t holding nonce, tag, ciphertext */
encrypted_blob_t encrypt(const uint8_t *key, size_t key_len,
                         const uint8_t *plaintext, size_t plaintext_len)
{
    encrypted_blob_t ret  = {0};

    /* ---- allocate buffers ------------------------------------------ */
    ret.nonce_len      = 16;                      /* 128-bit nonce */
    ret.tag_len        = 16;                      /* 128-bit tag   */
    ret.ciphertext_len = plaintext_len;

    ret.nonce      = malloc(ret.nonce_len);
    ret.tag        = malloc(ret.tag_len);
    ret.ciphertext = malloc(ret.ciphertext_len);

    if (!ret.nonce || !ret.tag || !ret.ciphertext)       /* allocation check */
        goto error;

    /* ---- fill nonce with fresh randomness -------------------------- */
    random_bytes(ret.nonce, ret.nonce_len);

    /* ---- run AES/EAX                                                */
    AES_EAX_CTX ctx;
    AES_EAX_init(&ctx, key, key_len, ret.nonce, ret.nonce_len);

    AES_EAX_encrypt(&ctx,
                    plaintext,
                    ret.ciphertext,
                    plaintext_len);

    AES_EAX_final(&ctx, ret.tag, &ret.tag_len);
    return ret;

error:
    /* In case of failure, free anything that was allocated */
    free(ret.nonce);
    free(ret.tag);
    free(ret.ciphertext);
    memset(&ret, 0, sizeof(ret));
    return ret;
}


/* ---------------------------  decrypt()  ----------------------------- */
/*   key/key_len   : same symmetric key                                 */
/*   blob          : triplet produced by encrypt()                      */
/*   *out          : gets malloc'ed plaintext (caller must free)        */
/*   *out_len      : length of plaintext                                */
/*   returns 0 on success, –1 on auth/tag failure                       */
int decrypt(const uint8_t *key, size_t key_len,
            const encrypted_blob_t *blob,
            uint8_t **out, size_t *out_len)
{
    *out     = NULL;
    *out_len = 0;

    if (!blob || !blob->ciphertext)               /* basic sanity checks */
        return -1;

    /* ---- prepare output buffer ------------------------------------- */
    uint8_t *pt = malloc(blob->ciphertext_len);
    if (!pt) return -1;

    /* ---- run AES/EAX ----------------------------------------------- */
    AES_EAX_CTX ctx;
    AES_EAX_init(&ctx, key, key_len, blob->nonce, blob->nonce_len);

    AES_EAX_decrypt(&ctx,
                    blob->ciphertext,
                    pt,
                    blob->ciphertext_len);

    uint8_t computed_tag[32];     /* large enough for any sane tag size */
    size_t  computed_tag_len = 0;
    AES_EAX_final(&ctx, computed_tag, &computed_tag_len);

    /* ---- constant-time tag comparison ------------------------------ */
    if (computed_tag_len != blob->tag_len ||
        memcmp(computed_tag, blob->tag, blob->tag_len) != 0)
    {
        /* authentication failed */
        free(pt);
        return -1;
    }

    /* ---- success ---------------------------------------------------- */
    *out     = pt;
    *out_len = blob->ciphertext_len;
    return 0;
}