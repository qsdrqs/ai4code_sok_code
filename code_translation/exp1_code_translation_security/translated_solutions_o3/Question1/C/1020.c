/*  aes_example.c
 *
 *  Build (Linux / macOS):
 *      gcc aes_example.c -o aes_example -lcrypto
 *
 *  NOTE
 *      – key_size must be 16, 24 or 32 (AES-128/192/256)
 *      – All memory returned from encrypt_AES_CBC_base64() /
 *        decrypt_AES_CBC_base64() must be free()’d by the caller.
 */

#include <openssl/evp.h>
#include <openssl/rand.h>
#include <openssl/bio.h>
#include <openssl/buffer.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>

#define AES_BLK 16               /* AES block size in bytes                */

/* --------------------------------------------------------------------- */
/* PKCS#7 padding helpers                                                */
/* --------------------------------------------------------------------- */
static size_t pkcs7_pad(const unsigned char *in,
                        size_t in_len,
                        unsigned char **out)
{
    size_t pad     = AES_BLK - (in_len % AES_BLK);
    size_t out_len = in_len + pad;

    *out = (unsigned char *)malloc(out_len);
    memcpy(*out, in, in_len);
    memset(*out + in_len, (int)pad, pad);

    return out_len;
}

static size_t pkcs7_unpad(unsigned char *buf, size_t buf_len)
{
    if (buf_len == 0) return 0;

    unsigned char pad = buf[buf_len - 1];

    if (pad == 0 || pad > AES_BLK) return 0;          /* malformed */
    for (size_t i = buf_len - pad; i < buf_len; ++i)  /* integrity */
        if (buf[i] != pad) return 0;

    return buf_len - pad;
}

/* --------------------------------------------------------------------- */
/* Base-64 helpers using BIO                                              */
/* --------------------------------------------------------------------- */
static char *b64_encode(const unsigned char *in, size_t in_len,
                        size_t *out_len)
{
    BIO *b64 = BIO_new(BIO_f_base64());
    BIO *mem = BIO_new(BIO_s_mem());
    BIO_set_flags(b64, BIO_FLAGS_BASE64_NO_NL);   /* no \n */
    b64 = BIO_push(b64, mem);

    BIO_write(b64, in, (int)in_len);
    BIO_flush(b64);

    BUF_MEM *bptr;
    BIO_get_mem_ptr(b64, &bptr);

    char *buff = (char *)malloc(bptr->length + 1);
    memcpy(buff, bptr->data, bptr->length);
    buff[bptr->length] = '\0';

    if (out_len) *out_len = bptr->length;

    BIO_free_all(b64);
    return buff;
}

static unsigned char *b64_decode(const char *in, size_t in_len,
                                 size_t *out_len)
{
    BIO *b64 = BIO_new(BIO_f_base64());
    BIO *mem = BIO_new_mem_buf((void *)in, (int)in_len);
    BIO_set_flags(b64, BIO_FLAGS_BASE64_NO_NL);
    mem = BIO_push(b64, mem);

    /* Worst-case: decoded output ≤ input length */
    unsigned char *out = (unsigned char *)malloc(in_len);
    int len           = BIO_read(mem, out, (int)in_len);

    if (len <= 0) {                 /* decode error */
        free(out);
        *out_len = 0;
        BIO_free_all(mem);
        return NULL;
    }
    *out_len = (size_t)len;

    BIO_free_all(mem);
    return out;
}

/* --------------------------------------------------------------------- */
/* AES-CBC + Base-64 wrapper (encrypt)                                    */
/* --------------------------------------------------------------------- */
char *encrypt_AES_CBC_base64(const unsigned char *key,
                             size_t               key_size,
                             const unsigned char *plain,
                             size_t               plain_len,
                             size_t              *b64_len_out)
{
    /* 1. Create IV */
    unsigned char iv[AES_BLK];
    if (RAND_bytes(iv, AES_BLK) != 1) return NULL;

    /* 2. Pad plaintext -> padded */
    unsigned char *padded      = NULL;
    size_t         padded_len  = pkcs7_pad(plain, plain_len, &padded);

    /* 3. Pick correct AES variant */
    const EVP_CIPHER *cipher = NULL;
    switch (key_size) {
        case 16: cipher = EVP_aes_128_cbc(); break;
        case 24: cipher = EVP_aes_192_cbc(); break;
        case 32: cipher = EVP_aes_256_cbc(); break;
        default: free(padded); return NULL;           /* invalid key size */
    }

    /* 4. Encrypt */
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_EncryptInit_ex(ctx, cipher, NULL, key, iv);

    unsigned char *cipher_buf =
        (unsigned char *)malloc(padded_len + AES_BLK);  /* room for padding */
    int len1 = 0, len2 = 0;

    EVP_EncryptUpdate(ctx, cipher_buf, &len1, padded, (int)padded_len);
    EVP_EncryptFinal_ex(ctx, cipher_buf + len1, &len2);

    size_t cipher_len = len1 + len2;
    EVP_CIPHER_CTX_free(ctx);
    free(padded);

    /* 5. Prepend IV */
    unsigned char *iv_plus_ct =
        (unsigned char *)malloc(AES_BLK + cipher_len);
    memcpy(iv_plus_ct,               iv,         AES_BLK);
    memcpy(iv_plus_ct + AES_BLK, cipher_buf, cipher_len);
    free(cipher_buf);

    /* 6. Base-64 encode */
    char *b64 = b64_encode(iv_plus_ct, AES_BLK + cipher_len, b64_len_out);
    free(iv_plus_ct);
    return b64;
}

/* --------------------------------------------------------------------- */
/* AES-CBC + Base-64 wrapper (decrypt)                                    */
/* --------------------------------------------------------------------- */
unsigned char *decrypt_AES_CBC_base64(const unsigned char *key,
                                      size_t               key_size,
                                      const char          *b64_in,
                                      size_t               b64_in_len,
                                      size_t              *plain_len_out)
{
    /* 1. Base-64 decode */
    size_t enc_len;
    unsigned char *enc = b64_decode(b64_in, b64_in_len, &enc_len);
    if (!enc || enc_len < AES_BLK) return NULL;        /* malformed */

    unsigned char *iv      = enc;                      /* first 16 bytes */
    unsigned char *cipher  = enc + AES_BLK;
    size_t         c_len   = enc_len - AES_BLK;

    /* 2. Pick AES variant */
    const EVP_CIPHER *cipher_spec = NULL;
    switch (key_size) {
        case 16: cipher_spec = EVP_aes_128_cbc(); break;
        case 24: cipher_spec = EVP_aes_192_cbc(); break;
        case 32: cipher_spec = EVP_aes_256_cbc(); break;
        default: free(enc); return NULL;
    }

    /* 3. Decrypt */
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_DecryptInit_ex(ctx, cipher_spec, NULL, key, iv);

    unsigned char *plain =
        (unsigned char *)malloc(c_len + AES_BLK);      /* extra space */
    int len1 = 0, len2 = 0;

    EVP_DecryptUpdate(ctx, plain, &len1, cipher, (int)c_len);
    if (EVP_DecryptFinal_ex(ctx, plain + len1, &len2) != 1) {
        /* Wrong key, tampering, … */
        EVP_CIPHER_CTX_free(ctx);
        free(enc); free(plain);
        return NULL;
    }
    size_t total_len = len1 + len2;
    EVP_CIPHER_CTX_free(ctx);
    free(enc);

    /* 4. Remove padding */
    size_t unpadded = pkcs7_unpad(plain, total_len);
    if (!unpadded) { free(plain); return NULL; }

    unsigned char *out = (unsigned char *)malloc(unpadded + 1);
    memcpy(out, plain, unpadded);
    out[unpadded] = '\0';

    free(plain);
    if (plain_len_out) *plain_len_out = unpadded;
    return out;
}

/* --------------------------------------------------------------------- */
/* Simple test / usage example                                           */
/* --------------------------------------------------------------------- */
#ifdef TEST_AES_MAIN
int main(void)
{
    const unsigned char key[] = "0123456789ABCDEF0123456789ABCDEF"; /* 32-byte key */
    const char *message        = "Hello from Python -> C AES CBC!";

    size_t b64_len;
    char *b64_cipher = encrypt_AES_CBC_base64(key, 32,
                                              (const unsigned char *)message,
                                              strlen(message),
                                              &b64_len);

    printf("Cipher (Base-64): %s\n", b64_cipher);

    size_t plain_len;
    unsigned char *plain = decrypt_AES_CBC_base64(key, 32,
                                                  b64_cipher, b64_len,
                                                  &plain_len);

    if (plain) {
        printf("Decrypted: %s\n", plain);
        free(plain);
    } else {
        printf("Decryption failed!\n");
    }

    free(b64_cipher);
    return 0;
}
#endif