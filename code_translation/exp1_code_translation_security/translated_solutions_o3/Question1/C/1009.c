/*
 *  aes_eax_demo.c   – Translation of the given Python snippet to C
 *
 *  Build example (with whatever library provides EAX support):
 *      cc aes_eax_demo.c -o aes_eax_demo -lcrypto   (OpenSSL-like)
 *      ./aes_eax_demo
 *
 *  All symbols that start with  ‘eax_…’  as well as
 *  get_random_bytes()  are assumed to be supplied by the crypto
 *  backend that is linked in, exactly as the instructions stated.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* ----------------  external (already-provided) interface  ----------- */
#include "aes_eax.h"     /* <- contains the declarations listed below  */

/*  Whatever library you link in must provide these functions/macros.
 *  Their prototypes are listed here only so that the compiler knows
 *  about them.  The real implementations come from the crypto backend
 *  that accompanies your project.
 */
typedef struct eax_ctx EAX_CTX;

void  get_random_bytes(unsigned char *dst, size_t len);

void  eax_init     (EAX_CTX *ctx,
                    const unsigned char *key,   size_t key_len,
                    const unsigned char *nonce, size_t nonce_len);

void  eax_encrypt  (EAX_CTX *ctx,
                    const unsigned char *in,  size_t in_len,
                    unsigned char *out);                  /* same len */

void  eax_decrypt  (EAX_CTX *ctx,
                    const unsigned char *in,  size_t in_len,
                    unsigned char *out);                  /* same len */

void  eax_get_tag      (EAX_CTX *ctx, unsigned char *tag, size_t tag_len);
int   eax_verify_tag   (EAX_CTX *ctx, const unsigned char *tag,
                                         size_t tag_len); /* 0 = bad */
void  eax_cleanup  (EAX_CTX *ctx);
/* -------------------------------------------------------------------- */


/* -------------------------------------------------------------------- */
/* A tiny struct that keeps ciphertext length + authentication tag.     */
typedef struct
{
    unsigned char *ct;     /* ciphertext bytes                     */
    size_t         ct_len; /* length of the ciphertext (== pt len) */
    unsigned char  tag[16];/* authentication tag (16 bytes here)   */
} cipher_blob;


/*
 *  encodeString  – equivalent of the Python encodeString()
 *  Returns  0   on success, non-zero on error.
 */
static int encodeString(const char      *message,
                        const uint8_t   *key,
                        const uint8_t   *nonce,
                        cipher_blob     *out)
{
    size_t       msg_len = strlen(message);
    unsigned char *ciphertext;

    ciphertext = malloc(msg_len);
    if (!ciphertext) {
        perror("malloc");
        return -1;
    }

    EAX_CTX ctx;
    eax_init(&ctx, key, 16, nonce, 16);
    eax_encrypt(&ctx,
                (const unsigned char *)message,
                msg_len,
                ciphertext);

    eax_get_tag(&ctx, out->tag, 16);
    eax_cleanup(&ctx);

    out->ct     = ciphertext;
    out->ct_len = msg_len;
    return 0;
}


/*
 *  decodeString  – equivalent of the Python decodeString()
 *  On success  *plaintext  will point to a zero-terminated string that
 *  must be freed by the caller.  Returns 0 on success, non-zero on error.
 */
static int decodeString(const cipher_blob *in,
                        const uint8_t     *key,
                        const uint8_t     *nonce,
                        char             **plaintext)
{
    unsigned char *plain = malloc(in->ct_len + 1);    /* +1 for '\0' */
    if (!plain) {
        perror("malloc");
        return -1;
    }

    EAX_CTX ctx;
    eax_init(&ctx, key, 16, nonce, 16);
    eax_decrypt(&ctx, in->ct, in->ct_len, plain);

    if (eax_verify_tag(&ctx, in->tag, 16) == 0) {
        /* authentication failed */
        fprintf(stderr, "ERROR: authentication tag mismatch!\n");
        eax_cleanup(&ctx);
        free(plain);
        return -2;
    }
    eax_cleanup(&ctx);

    plain[in->ct_len] = '\0';      /* turn it into a C string        */
    *plaintext        = (char *)plain;
    return 0;
}


/* -------------------------------------------------------------------- */
/*                             main()                                   */
int main(void)
{
    const char *originalMessage = "secret data";

    /* Generate key and nonce (16 bytes each, like in the Python code) */
    unsigned char key[16];
    unsigned char nonce[16];
    get_random_bytes(key,   sizeof key);
    get_random_bytes(nonce, sizeof nonce);

    /* --- print, encrypt, decrypt, print ----------------------------- */
    puts(originalMessage);

    cipher_blob blob;
    if (encodeString(originalMessage, key, nonce, &blob) != 0) {
        fprintf(stderr, "Encryption failed\n");
        return EXIT_FAILURE;
    }

    char *decoded = NULL;
    if (decodeString(&blob, key, nonce, &decoded) != 0) {
        /* decodeString already printed a reason                    */
        free(blob.ct);
        return EXIT_FAILURE;
    }

    puts(decoded);

    /* --- tidy up ---------------------------------------------------- */
    free(blob.ct);
    free(decoded);
    return EXIT_SUCCESS;
}