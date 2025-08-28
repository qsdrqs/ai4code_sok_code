/*
 *  Compile  : gcc -o aes_cfb_example aes_cfb_example.c -lcrypto
 *  Run      : ./aes_cfb_example
 *
 *  This is the C equivalent of:
 *      obj1 = AES.new('This is a key123', AES.MODE_CFB, 'This is an IV456')
 *      obj2 = AES.new('This is a key123', AES.MODE_CFB, 'This is an IV456')
 *      enc  = obj1.encrypt("The answer is no")
 *      dec  = obj2.decrypt(enc)
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <openssl/evp.h>
#include <openssl/err.h>

/* --------------------------------------------------------- */
/*  Small helper – abort on any OpenSSL error                */
/* --------------------------------------------------------- */
static void handleErrors(void)
{
    ERR_print_errors_fp(stderr);
    abort();
}

/* --------------------------------------------------------- */
/*  encrypt_string : counterpart of the Python function      */
/* --------------------------------------------------------- */
int encrypt_string(const unsigned char *plaintext, int plaintext_len,
                   EVP_CIPHER_CTX *ctx,
                   unsigned char   *ciphertext)
{
    int len = 0;

    /* Feed all data at once.  (CFB is a streaming mode.) */
    if (1 != EVP_EncryptUpdate(ctx, ciphertext, &len,
                               plaintext, plaintext_len))
        handleErrors();

    /* In CFB there is no padding; EVP_EncryptFinal_ex would add 0 bytes. */
    return len;                 /* number of bytes put in ‟ciphertext” */
}

/* --------------------------------------------------------- */
/*  decrypt_string : Python decrypt_string counterpart       */
/* --------------------------------------------------------- */
int decrypt_string(const unsigned char *ciphertext, int ciphertext_len,
                   EVP_CIPHER_CTX *ctx,
                   unsigned char   *plaintext)
{
    int len = 0;

    if (1 != EVP_DecryptUpdate(ctx, plaintext, &len,
                               ciphertext, ciphertext_len))
        handleErrors();

    return len;                 /* number of bytes put in ‟plaintext” */
}

/* --------------------------------------------------------- */
/*  Main (equivalent to Python’s  if __name__ == "__main__": )*/
/* --------------------------------------------------------- */
int main(void)
{
    /* Same key & IV that the Python code uses (16-byte each) */
    const unsigned char key[] = "This is a key123";
    const unsigned char iv[]  = "This is an IV456";

    /* Create two independent “cipher objects” (encrypt & decrypt) */
    EVP_CIPHER_CTX *enc_ctx = EVP_CIPHER_CTX_new();
    EVP_CIPHER_CTX *dec_ctx = EVP_CIPHER_CTX_new();
    if (!enc_ctx || !dec_ctx) handleErrors();

    /* Initialise for AES-128 in CFB (128-bit feedback) mode        */
    if (1 != EVP_EncryptInit_ex(enc_ctx, EVP_aes_128_cfb128(),
                                NULL, key, iv))
        handleErrors();
    if (1 != EVP_DecryptInit_ex(dec_ctx, EVP_aes_128_cfb128(),
                                NULL, key, iv))
        handleErrors();

    /* Plaintext to encrypt … */
    const char *message = "The answer is no";

    /* Buffers large enough for this demo                           */
    unsigned char ciphertext[128];
    unsigned char decryptedtext[128];

    /* Encrypt                                                      */
    int cipher_len = encrypt_string((const unsigned char *)message,
                                    strlen(message),
                                    enc_ctx,
                                    ciphertext);

    /* Decrypt                                                      */
    int plain_len  = decrypt_string(ciphertext,
                                    cipher_len,
                                    dec_ctx,
                                    decryptedtext);

    /* Turn decrypted bytes into a C string                         */
    decryptedtext[plain_len] = '\0';

    /* Show what happened                                           */
    printf("Ciphertext (hex): ");
    for (int i = 0; i < cipher_len; ++i)
        printf("%02x", ciphertext[i]);
    printf("\n");

    printf("Decrypted text : %s\n", decryptedtext);

    /* Clean-up                                                     */
    EVP_CIPHER_CTX_free(enc_ctx);
    EVP_CIPHER_CTX_free(dec_ctx);

    return 0;
}