/* aes_ecb_example.c */

#include <stdio.h>
#include <string.h>
#include <assert.h>
#include <openssl/aes.h>

/* ────────────────────────────────────────────────────────── */
/* Encode (encrypt) one or more 16-byte blocks with AES-128.  */
/* inp_len MUST be a multiple of 16 (block size).            */
static void encode(const unsigned char *inp,
                   size_t              inp_len,
                   const unsigned char *key,
                   unsigned char       *out)
{
    AES_KEY enc_key;
    AES_set_encrypt_key(key, 128, &enc_key);     /* 128-bit key */

    /* ECB encrypt, block-by-block */
    for (size_t i = 0; i < inp_len; i += AES_BLOCK_SIZE)
        AES_ecb_encrypt(inp + i, out + i, &enc_key, AES_ENCRYPT);
}

/* ────────────────────────────────────────────────────────── */
/* Decode (decrypt) one or more 16-byte blocks with AES-128.  */
static void decode(const unsigned char *inp,
                   size_t              inp_len,
                   const unsigned char *key,
                   unsigned char       *out)
{
    AES_KEY dec_key;
    AES_set_decrypt_key(key, 128, &dec_key);

    for (size_t i = 0; i < inp_len; i += AES_BLOCK_SIZE)
        AES_ecb_encrypt(inp + i, out + i, &dec_key, AES_DECRYPT);
}

/* ────────────────────────────────────────────────────────── */
int main(void)
{
    /* Same 16-byte ASCII buffer the Python code uses */
    const unsigned char inp[] = "1111111111111111";
    const unsigned char key[] = "1111111111111111";

    const size_t len = sizeof(inp) - 1;          /* 16, no NUL */

    /* Output buffers (must be ≥ input length) */
    unsigned char enc[len];
    unsigned char dec[len];

    /* Perform encode → decode round-trip */
    encode(inp, len, key, enc);
    decode(enc, len, key, dec);

    /* Make sure we got the original plaintext back */
    assert(memcmp(dec, inp, len) == 0);

    puts("Round-trip AES-ECB encode/decode successful!");
    return 0;
}