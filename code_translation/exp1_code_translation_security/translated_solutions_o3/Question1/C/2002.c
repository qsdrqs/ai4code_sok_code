/*
 *  python_port.c
 *
 *  Build (example):
 *      gcc -Wall -Wextra -pedantic -O2 python_port.c \
 *          -o python_port `pkg-config --libs --cflags openssl`
 *
 *  Run:
 *      ./python_port
 */

#include <openssl/evp.h>
#include <string.h>
#include <stdio.h>
#include <stdint.h>

/* ------------- encrypt / decrypt helpers ----------------- */

static int aes256_ctr_encrypt(const uint8_t *key,
                              const uint8_t *in,  size_t in_len,
                              uint8_t       *out, size_t *out_len)
{
    static const uint8_t IV[16] = {0};        /* pyaes default: all-zero IV   */
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    int len = 0, total = 0;

    if (!ctx) return 0;

    if (!EVP_EncryptInit_ex(ctx, EVP_aes_256_ctr(), NULL, key, IV))
        goto err;

    if (!EVP_EncryptUpdate(ctx, out, &len, in, (int)in_len))
        goto err;
    total += len;

    if (!EVP_EncryptFinal_ex(ctx, out + total, &len))
        goto err;
    total += len;

    *out_len = (size_t)total;
    EVP_CIPHER_CTX_free(ctx);
    return 1;

err:
    EVP_CIPHER_CTX_free(ctx);
    return 0;
}

static int aes256_ctr_decrypt(const uint8_t *key,
                              const uint8_t *in,  size_t in_len,
                              uint8_t       *out, size_t *out_len)
{
    static const uint8_t IV[16] = {0};        /* same all-zero IV             */
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    int len = 0, total = 0;

    if (!ctx) return 0;

    if (!EVP_DecryptInit_ex(ctx, EVP_aes_256_ctr(), NULL, key, IV))
        goto err;

    if (!EVP_DecryptUpdate(ctx, out, &len, in, (int)in_len))
        goto err;
    total += len;

    if (!EVP_DecryptFinal_ex(ctx, out + total, &len))
        goto err;
    total += len;

    *out_len = (size_t)total;
    EVP_CIPHER_CTX_free(ctx);
    return 1;

err:
    EVP_CIPHER_CTX_free(ctx);
    return 0;
}

/* ------------- "API" mirroring the Python functions ------- */

void encrypt_bytes(const uint8_t *k, const char *m,
                   uint8_t *ct,        size_t *ct_len)
{
    aes256_ctr_encrypt(k, (const uint8_t *)m, strlen(m), ct, ct_len);
}

void decrypt_bytes(const uint8_t *k, const uint8_t *ct, size_t ct_len,
                   char *out_plaintext)
{
    size_t pt_len = 0;
    aes256_ctr_decrypt(k, ct, ct_len, (uint8_t *)out_plaintext, &pt_len);
    out_plaintext[pt_len] = '\0';     /* Make it a C string */
}

/* ------------- round-trip test (equivalent to test() in python) */

static void round_trip_test(void)
{
    /* 32-byte / 256-bit key identical to the Python demo */
    const char key_literal[] = "This_key_for_demo_purposes_only!";
    uint8_t key[32];
    memcpy(key, key_literal, sizeof(key));

    const char msg[] =
        "Text may be any length you wish, no padding is required";

    uint8_t  ciphertext[512];
    size_t   ct_len = 0;

    encrypt_bytes(key, msg, ciphertext, &ct_len);

    char     recovered[512];
    decrypt_bytes(key, ciphertext, ct_len, recovered);

    puts(recovered);      /* should print the original message */
}

int main(void)
{
    /* OpenSSL 1.1+ handles init/cleanup itself, nothing to do */
    round_trip_test();
    return 0;
}