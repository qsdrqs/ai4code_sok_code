/*  AES / Base-64 demo – translation of the supplied Python code
 *
 *  Build :  gcc aes_demo.c -o aes_demo -lcrypto
 *
 *  Note  :  Uses AES-128-ECB (same default as PyCrypto’s AES.new()).
 *           Padding is done with a user supplied character exactly
 *           the way the Python version does it.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <openssl/aes.h>
#include <openssl/rand.h>
#include <openssl/bio.h>
#include <openssl/evp.h>
#include <openssl/buffer.h>

/* ----------------------------------------------------------------------- */
/* ------------------------------  helpers  ------------------------------ */
/* ----------------------------------------------------------------------- */

static char *base64_encode(const unsigned char *input,
                           size_t length,
                           size_t *out_len)          /* optional, may be NULL */
{
    BIO *bmem = NULL, *b64 = NULL;
    BUF_MEM *bptr = NULL;

    b64  = BIO_new(BIO_f_base64());
    BIO_set_flags(b64, BIO_FLAGS_BASE64_NO_NL);       /* no new-lines */
    bmem = BIO_new(BIO_s_mem());
    b64  = BIO_push(b64, bmem);

    BIO_write(b64, input, (int)length);
    BIO_flush(b64);
    BIO_get_mem_ptr(b64, &bptr);

    char *buff = (char *)malloc(bptr->length + 1);
    memcpy(buff, bptr->data, bptr->length);
    buff[bptr->length] = '\0';

    if (out_len) *out_len = bptr->length;
    BIO_free_all(b64);
    return buff;
}

static unsigned char *base64_decode(const char *input,
                                    size_t *out_len)  /* must NOT be NULL */
{
    BIO *b64 = NULL, *bmem = NULL;
    size_t in_len = strlen(input);
    size_t max_len = 3 * in_len / 4 + 3;              /* worst case */
    unsigned char *buffer = (unsigned char *)malloc(max_len);

    b64  = BIO_new(BIO_f_base64());
    BIO_set_flags(b64, BIO_FLAGS_BASE64_NO_NL);
    bmem = BIO_new_mem_buf((char *)input, (int)in_len);
    bmem = BIO_push(b64, bmem);

    *out_len = BIO_read(bmem, buffer, (int)in_len);
    BIO_free_all(bmem);
    return buffer;
}

/* ----------------------------------------------------------------------- */
/* --------------------  Python-equivalent functions  -------------------- */
/* ----------------------------------------------------------------------- */

char *generate_secret_key_for_AES_cipher(void)
/* Returns the key ALREADY Base-64 encoded (caller must free). */
{
    const size_t AES_KEY_LEN = 16;                    /* 16, 24 or 32 */
    unsigned char raw_key[AES_KEY_LEN];

    if (RAND_bytes(raw_key, (int)AES_KEY_LEN) != 1) {
        fprintf(stderr, "Random generation failed.\n");
        exit(EXIT_FAILURE);
    }
    return base64_encode(raw_key, AES_KEY_LEN, NULL); /* malloc'ed */
}

char *encrypt_message(const char        *plain,
                      const char        *encoded_secret_key,
                      char               padding_character)
/* Returns encrypted message Base-64 encoded (caller must free).          */
{
    /* ---- decode secret key ---- */
    size_t key_len = 0;
    unsigned char *key =
        base64_decode(encoded_secret_key, &key_len);  /* key_len == 16 */

    /* ---- build AES key schedule ---- */
    AES_KEY enc_key;
    AES_set_encrypt_key(key, (int)key_len * 8, &enc_key);

    /* ---- pad plain text ---- */
    size_t plain_len = strlen(plain);
    size_t pad_len   = (16 - (plain_len % 16)) % 16;
    size_t padded_len = plain_len + pad_len;

    unsigned char *padded =
        (unsigned char *)malloc(padded_len);
    memcpy(padded, plain, plain_len);
    memset(padded + plain_len, padding_character, pad_len);

    /* ---- encrypt block-by-block ---- */
    unsigned char *cipher =
        (unsigned char *)malloc(padded_len);

    for (size_t i = 0; i < padded_len; i += 16)
        AES_encrypt(padded + i, cipher + i, &enc_key);

    /* ---- Base-64 encode result ---- */
    char *cipher_b64 = base64_encode(cipher, padded_len, NULL);

    /* ---- tidy up ---- */
    free(key);
    free(padded);
    free(cipher);
    return cipher_b64;
}

char *decrypt_message(const char  *encoded_cipher_text,
                      const char  *encoded_secret_key,
                      char         padding_character)
/* Returns the original message (caller must free).                       */
{
    /* ---- decode secret key ---- */
    size_t key_len = 0;
    unsigned char *key = base64_decode(encoded_secret_key, &key_len);

    /* ---- decode cipher text ---- */
    size_t cipher_len = 0;
    unsigned char *cipher =
        base64_decode(encoded_cipher_text, &cipher_len);

    /* ---- build AES key schedule ---- */
    AES_KEY dec_key;
    AES_set_decrypt_key(key, (int)key_len * 8, &dec_key);

    /* ---- decrypt ---- */
    unsigned char *plain =
        (unsigned char *)malloc(cipher_len + 1);      /* +1 for '\0' */

    for (size_t i = 0; i < cipher_len; i += 16)
        AES_decrypt(cipher + i, plain + i, &dec_key);

    plain[cipher_len] = '\0';                         /* temp 0-term */

    /* ---- remove padding ---- */
    size_t real_len = cipher_len;
    while (real_len > 0 && plain[real_len - 1] == padding_character)
        --real_len;

    char *out = (char *)malloc(real_len + 1);
    memcpy(out, plain, real_len);
    out[real_len] = '\0';

    /* ---- tidy up ---- */
    free(key);
    free(cipher);
    free(plain);
    return out;
}

/* ----------------------------------------------------------------------- */
/* -------------------------------- main --------------------------------- */
/* ----------------------------------------------------------------------- */

int main(void)
{
    const char *private_msg =
        "\n My test string\n";
    const char  padding_character = '{';

    char *secret_key   = generate_secret_key_for_AES_cipher();
    char *encrypted_msg = encrypt_message(private_msg,
                                          secret_key,
                                          padding_character);
    char *decrypted_msg = decrypt_message(encrypted_msg,
                                          secret_key,
                                          padding_character);

    printf("   Secret Key: %s - (%zu)\n",
           secret_key, strlen(secret_key));
    printf("Encrypted Msg: %s - (%zu)\n",
           encrypted_msg, strlen(encrypted_msg));
    printf("Decrypted Msg: %s - (%zu)\n",
           decrypted_msg, strlen(decrypted_msg));

    /* ---- free what we allocated ---- */
    free(secret_key);
    free(encrypted_msg);
    free(decrypted_msg);
    return 0;
}