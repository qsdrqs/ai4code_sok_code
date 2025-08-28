/* encrypt.c  */
#include <stdlib.h>
#include <string.h>
#include "aes.h"        /* ↱  an AES-CBC implementation               */
#include "padding.h"    /* ↳  PKCS#7 pad / unpad helpers (see bottom) */

#define AES_BLOCK_SIZE 16   /* PyCryptodome’s AES.block_size */

/*
 *  encrypt()
 *  ---------
 *  message         – zero-terminated ASCII/UTF-8 text
 *  key             – 16, 24 or 32 byte AES key
 *  out_len (OUT)   – receives ciphertext length (in bytes)
 *
 *  RETURN: freshly allocated ciphertext buffer (must be free()’d),
 *          or NULL on failure.
 */
unsigned char *encrypt(const char *message,
                       const unsigned char *key,
                       size_t *out_len)
{
    size_t msg_len      = strlen(message);
    size_t padded_len   = padded_size(msg_len, AES_BLOCK_SIZE);
    unsigned char iv[AES_BLOCK_SIZE] = {0};          /* same idea as
                                                        AES.new(key, MODE_CBC)
                                                        without an explicit IV */
    /* 1.  allocate & pad --------------------------------------------------- */
    unsigned char *buffer = malloc(padded_len);
    if (!buffer) return NULL;

    memcpy(buffer, message, msg_len);
    apply_pkcs7_padding(buffer + msg_len,
                        AES_BLOCK_SIZE - (msg_len % AES_BLOCK_SIZE));

    /* 2.  AES-CBC encrypt -------------------------------------------------- */
    struct AES_ctx ctx;
    AES_init_ctx_iv(&ctx, key, iv);                  /* library call */
    AES_CBC_encrypt_buffer(&ctx, buffer, padded_len);

    *out_len = padded_len;
    return buffer;                                   /* caller owns it */
}

/*
 *  decrypt()
 *  ---------
 *  ciphertext      – bytes produced by encrypt()
 *  cipher_len      – its length
 *  key             – same key that was used for encryption
 *
 *  RETURN: freshly allocated, zero-terminated plaintext string,
 *          or NULL on failure.
 */
char *decrypt(const unsigned char *ciphertext,
              size_t cipher_len,
              const unsigned char *key)
{
    unsigned char iv[AES_BLOCK_SIZE] = {0};
    struct AES_ctx ctx;

    /* 1. make a working copy ---------------------------------------------- */
    unsigned char *buffer = malloc(cipher_len);
    if (!buffer) return NULL;
    memcpy(buffer, ciphertext, cipher_len);

    /* 2. AES-CBC decrypt --------------------------------------------------- */
    AES_init_ctx_iv(&ctx, key, iv);
    AES_CBC_decrypt_buffer(&ctx, buffer, cipher_len);

    /* 3. remove PKCS#7 padding -------------------------------------------- */
    size_t plain_len = strip_pkcs7_padding(buffer, cipher_len, AES_BLOCK_SIZE);
    if (plain_len == (size_t)-1) {              /* padding error */
        free(buffer);
        return NULL;
    }

    /* 4. copy into a C-string --------------------------------------------- */
    char *message = malloc(plain_len + 1);
    if (!message) {
        free(buffer);
        return NULL;
    }
    memcpy(message, buffer, plain_len);
    message[plain_len] = '\0';

    free(buffer);
    return message;
}

/* ------------------------------------------------------------------------- */
/*                    Simple PKCS#7 padding helpers                          */
/* ------------------------------------------------------------------------- */
size_t padded_size(size_t data_len, size_t block_sz)
{
    size_t pad = block_sz - (data_len % block_sz);
    return data_len + pad;
}

void apply_pkcs7_padding(unsigned char *dst, size_t pad_len)
{
    memset(dst, (unsigned char)pad_len, pad_len);
}

size_t strip_pkcs7_padding(unsigned char *buf, size_t buf_len, size_t block_sz)
{
    if (buf_len == 0 || buf_len % block_sz) return (size_t)-1;

    unsigned char pad = buf[buf_len - 1];
    if (pad == 0 || pad > block_sz)            /* malformed */
        return (size_t)-1;

    /* verify all padding bytes ------------------------------------------- */
    for (size_t i = 0; i < pad; ++i)
        if (buf[buf_len - 1 - i] != pad)
            return (size_t)-1;

    return buf_len - pad;                      /* new length sans padding */
}