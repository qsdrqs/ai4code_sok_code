#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/evp.h>
#include <openssl/rand.h>

/* -----------------------------------------------------------------
 * Some simple, Python-like bookkeeping types
 * -----------------------------------------------------------------*/
#define AES_KEY_LEN   16          /* 16 bytes = 128-bit key          */
#define NONCE_LEN     16          /* 16 bytes nonce (like PyCryptodome) */

typedef struct {
    unsigned char *cipher;        /* pointer to ciphertext buffer    */
    int            cipher_len;    /* ciphertext length               */
    unsigned char  nonce[NONCE_LEN];
} Ciphertext;


/* -----------------------------------------------------------------
 * encrypt()  – roughly equivalent to Python encrypt(m, sk)
 * -----------------------------------------------------------------*/
Ciphertext encrypt(const unsigned char *msg, int msg_len,
                   const unsigned char *sk)
{
    Ciphertext out = {0};

    /* allocate space for the ciphertext (CTR keeps same size)       */
    out.cipher      = (unsigned char *)malloc(msg_len);
    out.cipher_len  = msg_len;

    /* --- generate a 16-byte random nonce ------------------------- */
    if (RAND_bytes(out.nonce, NONCE_LEN) != 1) {
        fprintf(stderr, "RAND_bytes() failed\n");
        exit(EXIT_FAILURE);
    }

    /* --- OpenSSL EVP: AES-128-CTR -------------------------------- */
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    if (!ctx) { perror("EVP_CIPHER_CTX_new"); exit(EXIT_FAILURE); }

    if (EVP_EncryptInit_ex(ctx, EVP_aes_128_ctr(), NULL, sk, out.nonce) != 1) {
        fprintf(stderr, "EVP_EncryptInit_ex() failed\n");
        exit(EXIT_FAILURE);
    }

    int len = 0, total = 0;
    if (EVP_EncryptUpdate(ctx, out.cipher, &len, msg, msg_len) != 1) {
        fprintf(stderr, "EVP_EncryptUpdate() failed\n");
        exit(EXIT_FAILURE);
    }
    total += len;

    /* CTR mode does not pad, but call Final for completeness        */
    if (EVP_EncryptFinal_ex(ctx, out.cipher + total, &len) != 1) {
        fprintf(stderr, "EVP_EncryptFinal_ex() failed\n");
        exit(EXIT_FAILURE);
    }

    EVP_CIPHER_CTX_free(ctx);
    return out;          /* (cipher, nonce) bundled in struct        */
}


/* -----------------------------------------------------------------
 * decrypt()  – roughly equivalent to Python decrypt(cn, sk)
 * -----------------------------------------------------------------*/
unsigned char *decrypt(const Ciphertext *cn,
                       const unsigned char *sk,
                       int *plain_len_out)        /* returns plaintext length */
{
    unsigned char *plain = (unsigned char *)malloc(cn->cipher_len);
    if (!plain) { perror("malloc"); exit(EXIT_FAILURE); }
    *plain_len_out = cn->cipher_len;

    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    if (!ctx) { perror("EVP_CIPHER_CTX_new"); exit(EXIT_FAILURE); }

    if (EVP_DecryptInit_ex(ctx, EVP_aes_128_ctr(), NULL, sk, cn->nonce) != 1) {
        fprintf(stderr, "EVP_DecryptInit_ex() failed\n");
        exit(EXIT_FAILURE);
    }

    int len = 0, total = 0;
    if (EVP_DecryptUpdate(ctx, plain, &len, cn->cipher, cn->cipher_len) != 1) {
        fprintf(stderr, "EVP_DecryptUpdate() failed\n");
        exit(EXIT_FAILURE);
    }
    total += len;

    if (EVP_DecryptFinal_ex(ctx, plain + total, &len) != 1) {
        fprintf(stderr, "EVP_DecryptFinal_ex() failed\n");
        exit(EXIT_FAILURE);
    }

    EVP_CIPHER_CTX_free(ctx);
    return plain;                        /* caller frees this buffer */
}

/* -----------------------------------------------------------------
 * Tiny self-test / demo
 * -----------------------------------------------------------------*/
int main(void)
{
    /* message and 128-bit secret key (16 bytes)                     */
    const unsigned char msg[] = "Sphinx of black quartz, judge my vow";
    const unsigned char key[AES_KEY_LEN] = "sixteen byte key";

    puts("Original message :");
    puts((const char *)msg);

    /* --- encryption --------------------------------------------- */
    Ciphertext ct = encrypt(msg, (int)strlen((const char *)msg), key);

    printf("\nCiphertext (hex) :\n");
    for (int i = 0; i < ct.cipher_len; ++i) printf("%02x", ct.cipher[i]);
    printf("\nNonce      (hex) :\n");
    for (int i = 0; i < NONCE_LEN; ++i)      printf("%02x", ct.nonce[i]);
    putchar('\n');

    /* --- decryption --------------------------------------------- */
    int plain_len = 0;
    unsigned char *plain = decrypt(&ct, key, &plain_len);

    printf("\nDecrypted text   :\n");
    fwrite(plain, 1, plain_len, stdout);
    putchar('\n');

    /* --- tidy up ------------------------------------------------- */
    free(plain);
    free(ct.cipher);
    return 0;
}