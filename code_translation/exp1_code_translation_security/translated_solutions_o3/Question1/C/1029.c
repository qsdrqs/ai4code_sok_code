/*  aes_cipher.c  */
#include <stdlib.h>
#include <string.h>
#include <stdint.h>
#include <openssl/aes.h>      /* or any other provider that exports
                                 AES_set_encrypt_key / AES_encrypt /
                                 AES_decrypt … */

/* -------------- “Class” definition -------------------------------------- */

typedef struct
{
    uint8_t  key[32];      /* room for 128/192/256-bit keys              */
    int      key_len;      /* bytes in key                               */
    int      blk_sz;       /* AES block size – always 16, but we copy
                              the Python signature                       */
    AES_KEY  enc_ctx;      /* OpenSSL encryption context                 */
    AES_KEY  dec_ctx;      /* OpenSSL decryption context                 */
} AEScipher;

/* -------------- Constructor -------------------------------------------- */

AEScipher *AESCipher_new(const uint8_t *key, int key_len, int blk_sz)
{
    AEScipher *ctx = (AEScipher *)calloc(1, sizeof(*ctx));
    if (!ctx) return NULL;

    /* store key & parameters */
    memcpy(ctx->key, key, key_len);
    ctx->key_len = key_len;
    ctx->blk_sz  = blk_sz;

    /* prepare the AES key schedules */
    AES_set_encrypt_key(ctx->key, key_len * 8, &ctx->enc_ctx);
    AES_set_decrypt_key(ctx->key, key_len * 8, &ctx->dec_ctx);

    return ctx;
}

/* -------------- Helper : PKCS#7 padding -------------------------------- */

static uint8_t *pkcs7_pad(const uint8_t *in,
                          size_t in_len,
                          int blk_sz,
                          size_t *out_len)
{
    uint8_t pad_len  = blk_sz - (in_len % blk_sz);
    *out_len = in_len + pad_len;

    uint8_t *out = (uint8_t *)malloc(*out_len);
    if (!out) return NULL;

    memcpy(out, in, in_len);
    memset(out + in_len, pad_len, pad_len);

    return out;
}

static uint8_t *pkcs7_unpad(uint8_t *buf,
                            size_t *len_in_out)
{
    if (*len_in_out == 0) return NULL;

    uint8_t pad_len = buf[*len_in_out - 1];
    if (pad_len == 0 || pad_len > *len_in_out)
        return NULL;                /* invalid padding */

    *len_in_out -= pad_len;         /* shrink “logical” length */
    return buf;
}

/* -------------- Encryption --------------------------------------------- */

uint8_t *AESCipher_encrypt(AEScipher  *ctx,
                           const uint8_t *msg,
                           size_t  msg_len,
                           size_t *ct_len)
{
    /* 1) Pad ------------------------------------------------------------- */
    size_t padded_len;
    uint8_t *padded = pkcs7_pad(msg, msg_len, ctx->blk_sz, &padded_len);
    if (!padded) return NULL;

    /* 2) Allocate ciphertext buffer ------------------------------------- */
    uint8_t *cipher = (uint8_t *)malloc(padded_len);
    if (!cipher) { free(padded); return NULL; }

    /* 3) ECB encrypt block-by-block ------------------------------------- */
    for (size_t off = 0; off < padded_len; off += ctx->blk_sz)
        AES_encrypt(padded + off, cipher + off, &ctx->enc_ctx);

    free(padded);
    *ct_len = padded_len;
    return cipher;
}

/* -------------- Decryption --------------------------------------------- */

uint8_t *AESCipher_decrypt(AEScipher *ctx,
                           const uint8_t *ciphertext,
                           size_t ct_len,
                           size_t *pt_len)
{
    if (ct_len % ctx->blk_sz) return NULL;   /* malformed input */

    /* 1) Allocate plaintext buffer -------------------------------------- */
    uint8_t *plain = (uint8_t *)malloc(ct_len);
    if (!plain) return NULL;

    /* 2) ECB decrypt block-by-block ------------------------------------- */
    for (size_t off = 0; off < ct_len; off += ctx->blk_sz)
        AES_decrypt(ciphertext + off, plain + off, &ctx->dec_ctx);

    /* 3) Remove PKCS#7 padding ------------------------------------------ */
    size_t real_len = ct_len;
    if (!pkcs7_unpad(plain, &real_len)) {
        free(plain);
        return NULL;                         /* invalid padding */
    }

    *pt_len = real_len;
    return plain;
}

/* -------------- Destructor --------------------------------------------- */

void AESCipher_free(AEScipher *ctx)
{
    if (!ctx) return;
    /* clean key material from memory (optional) */
    OPENSSL_cleanse(ctx, sizeof(*ctx));
    free(ctx);
}

/* ----------------------------------------------------------------------- */
/* Example usage (can be removed during integration):

#include <stdio.h>

int main(void)
{
    const char key[16] = "this_is_aes_key!";
    const char msg[]   = "hello world";

    AEScipher *aes = AESCipher_new((const uint8_t *)key, 16, 16);

    size_t ct_len;
    uint8_t *ct = AESCipher_encrypt(aes,
                                    (const uint8_t *)msg,
                                    strlen(msg),
                                    &ct_len);

    size_t pt_len;
    uint8_t *pt = AESCipher_decrypt(aes, ct, ct_len, &pt_len);

    printf("Plain : %.*s\n", (int)pt_len, pt);

    free(ct);
    free(pt);
    AESCipher_free(aes);
    return 0;
}
*/