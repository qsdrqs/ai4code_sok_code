/*
 *  aes_cipher.c
 *
 *  Exact C counterpart of the Python AESCipher class shown in the prompt.
 *  Dependencies: OpenSSL (libcrypto)    -> -lcrypto
 *
 *  gcc -Wall -O2 -o aes_cipher aes_cipher.c -lcrypto
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdint.h>

#include <openssl/sha.h>
#include <openssl/evp.h>
#include <openssl/rand.h>
#include <openssl/bio.h>
#include <openssl/buffer.h>

/* ------------------------------------------------------------------ */
/*                       Base-64 helpers (OpenSSL)                    */
/* ------------------------------------------------------------------ */

static char *b64_encode(const uint8_t *buf, size_t len, size_t *out_len)
{
    BIO *b64  = BIO_new(BIO_f_base64());
    BIO *mem  = BIO_new(BIO_s_mem());
    BIO_set_flags(b64, BIO_FLAGS_BASE64_NO_NL);   /* no new-lines */
    b64 = BIO_push(b64, mem);

    BIO_write(b64, buf, (int)len);
    BIO_flush(b64);

    BUF_MEM *bptr;
    BIO_get_mem_ptr(b64, &bptr);

    char *ret = malloc(bptr->length + 1);
    memcpy(ret, bptr->data, bptr->length);
    ret[bptr->length] = '\0';

    if (out_len) *out_len = bptr->length;

    BIO_free_all(b64);
    return ret;
}

static uint8_t *b64_decode(const char *txt, size_t txt_len, size_t *out_len)
{
    BIO *b64 = BIO_new(BIO_f_base64());
    BIO *mem = BIO_new_mem_buf((void*)txt, (int)txt_len);
    BIO_set_flags(b64, BIO_FLAGS_BASE64_NO_NL);
    mem = BIO_push(b64, mem);

    size_t buff_sz = txt_len * 3 / 4 + 4;         /* upper bound */
    uint8_t *buf  = malloc(buff_sz);

    int n = BIO_read(mem, buf, (int)buff_sz);
    if (n < 0) n = 0;

    if (out_len) *out_len = (size_t)n;
    BIO_free_all(mem);
    return buf;
}

/* ------------------------------------------------------------------ */
/*                         PKCS#7 helpers                              */
/* ------------------------------------------------------------------ */

static void pkcs7_pad(uint8_t **data, size_t *len, size_t block)
{
    size_t pad = block - (*len % block);
    *data = realloc(*data, *len + pad);
    memset(*data + *len, (int)pad, pad);
    *len += pad;
}

static int pkcs7_unpad(uint8_t *data, size_t *len)
{
    if (*len == 0) return 0;
    uint8_t pad = data[*len - 1];
    if (pad == 0 || pad > *len) return 0;         /* invalid */
    for (size_t i = 0; i < pad; ++i) {
        if (data[*len - 1 - i] != pad) return 0;  /* invalid */
    }
    *len -= pad;
    return 1;
}

/* ------------------------------------------------------------------ */
/*                        AESCipher object                            */
/* ------------------------------------------------------------------ */

#define AES_BLOCK 16
#define AES_KEYSZ 32     /* 256-bit key, like Python code (SHA-256) */

typedef struct {
    uint8_t key[AES_KEYSZ];
    size_t  bs;          /* block size (32 in original, but we keep 32 to match Python) */
} AESCipher;

/* Constructor: derive key with SHA-256 */
AESCipher *aes_new(const char *key_str)
{
    AESCipher *c = calloc(1, sizeof(*c));
    c->bs = 32;
    SHA256((const uint8_t*)key_str, strlen(key_str), c->key);
    return c;
}

/* Free */
void aes_free(AESCipher *c)
{
    if (!c) return;
    OPENSSL_cleanse(c->key, sizeof(c->key));
    free(c);
}

/* ------------------------------------------------------------------ */
/*                        Encrypt / Decrypt                           */
/* ------------------------------------------------------------------ */

char *aes_encrypt(AESCipher *c, const char *plain)
{
    size_t p_len = strlen(plain);
    uint8_t *pbuf = malloc(p_len);
    memcpy(pbuf, plain, p_len);
    pkcs7_pad(&pbuf, &p_len, c->bs);      /* same padding rule */

    /* Allocate iv + ciphertext (ciphertext <= p_len + block) */
    uint8_t iv[AES_BLOCK];
    if (RAND_bytes(iv, AES_BLOCK) != 1) { free(pbuf); return NULL; }

    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    int outl1 = 0, outl2 = 0;
    uint8_t *ct = malloc(p_len + AES_BLOCK);

    EVP_EncryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, c->key, iv);
    EVP_EncryptUpdate(ctx, ct, &outl1, pbuf, (int)p_len);
    EVP_EncryptFinal_ex(ctx, ct + outl1, &outl2);
    EVP_CIPHER_CTX_free(ctx);

    size_t ct_len = (size_t)(outl1 + outl2);

    /* prepend IV */
    uint8_t *iv_ct = malloc(AES_BLOCK + ct_len);
    memcpy(iv_ct,           iv, AES_BLOCK);
    memcpy(iv_ct + AES_BLOCK, ct, ct_len);

    /* Base-64 encode */
    char *b64 = b64_encode(iv_ct, AES_BLOCK + ct_len, NULL);

    /* Cleanup */
    free(pbuf);
    free(ct);
    free(iv_ct);

    return b64;      /* caller frees */
}

char *aes_decrypt(AESCipher *c, const char *b64_cipher)
{
    size_t b64_len = strlen(b64_cipher);
    size_t bin_len;
    uint8_t *bin = b64_decode(b64_cipher, b64_len, &bin_len);
    if (!bin || bin_len < AES_BLOCK) { free(bin); return NULL; }

    uint8_t iv[AES_BLOCK];
    memcpy(iv, bin, AES_BLOCK);
    uint8_t *ct = bin + AES_BLOCK;
    int ct_len = (int)(bin_len - AES_BLOCK);

    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    uint8_t *pt = malloc(ct_len + AES_BLOCK);
    int outl1 = 0, outl2 = 0;

    EVP_DecryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, c->key, iv);
    EVP_DecryptUpdate(ctx, pt, &outl1, ct, ct_len);
    if (EVP_DecryptFinal_ex(ctx, pt + outl1, &outl2) != 1) {
        /* Wrong key / tampered data */
        EVP_CIPHER_CTX_free(ctx);
        free(bin); free(pt);
        return NULL;
    }
    EVP_CIPHER_CTX_free(ctx);

    size_t pt_len = (size_t)(outl1 + outl2);
    if (!pkcs7_unpad(pt, &pt_len)) {   /* bad padding */
        free(bin); free(pt);
        return NULL;
    }

    char *plain = malloc(pt_len + 1);
    memcpy(plain, pt, pt_len);
    plain[pt_len] = '\0';

    free(bin);
    free(pt);
    return plain;      /* caller frees */
}

/* ------------------------------------------------------------------ */
/*                              Demo                                  */
/* ------------------------------------------------------------------ */

#ifdef TEST_AES_CIPHER
int main(void)
{
    const char *key  = "mysecretpassword";
    const char *text = "Secret Message A";

    AESCipher *c = aes_new(key);

    char *enc = aes_encrypt(c, text);
    char *dec = aes_decrypt(c, enc);

    printf("Encrypted: %s\n", enc);
    printf("Decrypted: %s\n", dec ? dec : "(decryption failed)");

    free(enc);
    free(dec);
    aes_free(c);
    return 0;
}
#endif