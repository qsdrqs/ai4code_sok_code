/*
 * C translation of the Python example that used
 *   cryptography.hazmat.primitives.ciphers.Cipher
 * with AES-256-CBC (no padding).
 *
 * Dependencies: OpenSSL (>= 1.1.0 for the simplified API),
 *               libc (malloc/printf/strcmp/…).
 */

#include <openssl/evp.h>
#include <openssl/rand.h>

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*----------------------------------------------------------*/
/* Helper: print a buffer in hex – handy while debugging.   */
static void dump_hex(const char *label,
                     const unsigned char *buf,
                     size_t len)
{
    size_t i;
    printf("%s (%zu bytes): ", label, len);
    for (i = 0; i < len; i++) {
        printf("%02x", buf[i]);
    }
    putchar('\n');
}

/*----------------------------------------------------------*/
/* encrypt(): mirrors the Python encrypt() function.        */
/* Produces malloc()ed ciphertext – caller must free().     */
static int encrypt_buf(const unsigned char *plaintext,
                       size_t                plaintext_len,
                       const unsigned char  *key,
                       const unsigned char  *iv,
                       unsigned char       **ciphertext,   /* out – malloc()ed */
                       size_t               *ciphertext_len)
{
    int            len   = 0;
    int            outl  = 0;
    int            ret   = 0;          /* 0 ==> error, 1 ==> success */
    EVP_CIPHER_CTX *ctx  = NULL;
    unsigned char  *out  = NULL;

    /* Create and initialise the context */
    if (!(ctx = EVP_CIPHER_CTX_new()))
        goto cleanup;

    /* Initialise the encryption operation. */
    if (EVP_EncryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv) != 1)
        goto cleanup;

    /* Disable padding so we behave like the Python code (raw CBC). */
    EVP_CIPHER_CTX_set_padding(ctx, 0);

    /* Allocate output buffer: input length + one block is always safe. */
    out = malloc(plaintext_len + EVP_CIPHER_block_size(EVP_aes_256_cbc()));
    if (!out)
        goto cleanup;

    /* Provide the message to be encrypted, obtain the encrypted output. */
    if (EVP_EncryptUpdate(ctx, out, &len, plaintext, (int)plaintext_len) != 1)
        goto cleanup;
    outl = len;

    /* Finalise the encryption. */
    if (EVP_EncryptFinal_ex(ctx, out + outl, &len) != 1)
        goto cleanup;
    outl += len;

    /* Success. */
    *ciphertext     = out;
    *ciphertext_len = (size_t)outl;
    out  = NULL;          /* so we don't free() it below */
    ret  = 1;

cleanup:
    EVP_CIPHER_CTX_free(ctx);
    free(out);
    return ret;
}

/*----------------------------------------------------------*/
/* decrypt(): mirrors the Python decrypt() function.        */
/* Produces malloc()ed plaintext – caller must free().      */
static int decrypt_buf(const unsigned char *ciphertext,
                       size_t                ciphertext_len,
                       const unsigned char  *key,
                       const unsigned char  *iv,
                       unsigned char       **plaintext,    /* out – malloc()ed */
                       size_t               *plaintext_len)
{
    int            len  = 0;
    int            outl = 0;
    int            ret  = 0;
    EVP_CIPHER_CTX *ctx = NULL;
    unsigned char  *out = NULL;

    if (!(ctx = EVP_CIPHER_CTX_new()))
        goto cleanup;

    if (EVP_DecryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv) != 1)
        goto cleanup;

    EVP_CIPHER_CTX_set_padding(ctx, 0);

    out = malloc(ciphertext_len);  /* ciphertext_len is an upper bound here */
    if (!out)
        goto cleanup;

    if (EVP_DecryptUpdate(ctx, out, &len, ciphertext, (int)ciphertext_len) != 1)
        goto cleanup;
    outl = len;

    if (EVP_DecryptFinal_ex(ctx, out + outl, &len) != 1)
        goto cleanup;
    outl += len;

    *plaintext     = out;
    *plaintext_len = (size_t)outl;
    out  = NULL;
    ret  = 1;

cleanup:
    EVP_CIPHER_CTX_free(ctx);
    free(out);
    return ret;
}

/*----------------------------------------------------------*/
static void test(void)
{
    const unsigned char data[] = "a secret message";  /* 16 bytes */
    const size_t        data_len = sizeof(data) - 1;  /* strip NUL */

    unsigned char key[32];  /* 256 bit */
    unsigned char iv[16];   /* 128 bit */

    /* Generate random key and IV (os.urandom() equivalent). */
    if (RAND_bytes(key, sizeof(key)) != 1 ||
        RAND_bytes(iv,  sizeof(iv))  != 1) {
        fprintf(stderr, "RAND_bytes() failed\n");
        exit(EXIT_FAILURE);
    }

    unsigned char *enc = NULL;
    size_t         enc_len = 0;

    unsigned char *dec = NULL;
    size_t         dec_len = 0;

    if (!encrypt_buf(data, data_len, key, iv, &enc, &enc_len)) {
        fprintf(stderr, "encryption failed\n");
        exit(EXIT_FAILURE);
    }

    if (!decrypt_buf(enc, enc_len, key, iv, &dec, &dec_len)) {
        fprintf(stderr, "decryption failed\n");
        free(enc);
        exit(EXIT_FAILURE);
    }

    /* Check round-trip */
    assert(dec_len == data_len);
    assert(memcmp(dec, data, data_len) == 0);

    puts("Encryption / decryption successful!");
    dump_hex("ciphertext", enc, enc_len);

    free(enc);
    free(dec);
}

/*----------------------------------------------------------*/
int main(void)
{
    /* OpenSSL 1.1.x/3.x automatically initialises the library. */
    test();
    return 0;
}