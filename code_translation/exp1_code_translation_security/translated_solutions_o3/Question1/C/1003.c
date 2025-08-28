/*
 *  AES ECB ‑ mode byte-array encryption / decryption.
 *  ==================================================
 *  This is a direct translation of the Python helpers
 *
 *      encrypt_bytes_aes(data, key)
 *      decrypt_bytes_aes(data, key)
 *
 *  that were implemented with PyCryptodome’s
 *  `Crypto.Cipher.AES` in ECB mode.
 *
 *  The C version below relies on OpenSSL’s AES API.
 *  (Any project that links against OpenSSL or LibreSSL
 *  already has these symbols available.)
 *
 *  NOTE:
 *    • The caller must make sure that
 *          – `data_len` is a multiple of 16 bytes
 *            (AES block size) – exactly as required
 *            by ECB mode.
 *          – `key` is 16 bytes (128-bit AES). If you
 *            need 192- or 256-bit keys, adjust the
 *            constant in AES_set_*_key().
 *
 *    • Both helpers allocate a fresh output buffer
 *      (of equal length to the input) and return it.
 *      The caller is responsible for `free()`-ing the
 *      returned pointer.
 */

#include <openssl/aes.h>
#include <stdlib.h>
#include <string.h>

/*----------------------------------------------------*/
/*  Encrypt                                            */
/*----------------------------------------------------*/
unsigned char *
encrypt_bytes_aes(const unsigned char *data,
                  size_t               data_len,
                  const unsigned char *key)
{
    if (data_len % AES_BLOCK_SIZE != 0)
        return NULL;                           /* length error */

    AES_KEY enc_key;
    if (AES_set_encrypt_key(key, 128, &enc_key) != 0)
        return NULL;                           /* key setup error */

    unsigned char *out = (unsigned char *)malloc(data_len);
    if (!out)
        return NULL;                           /* allocation failure */

    /* ECB processes one 16-byte block at a time */
    for (size_t i = 0; i < data_len; i += AES_BLOCK_SIZE)
        AES_ecb_encrypt(data + i, out + i, &enc_key, AES_ENCRYPT);

    return out;
}


/*----------------------------------------------------*/
/*  Decrypt                                            */
/*----------------------------------------------------*/
unsigned char *
decrypt_bytes_aes(const unsigned char *data,
                  size_t               data_len,
                  const unsigned char *key)
{
    if (data_len % AES_BLOCK_SIZE != 0)
        return NULL;

    AES_KEY dec_key;
    if (AES_set_decrypt_key(key, 128, &dec_key) != 0)
        return NULL;

    unsigned char *out = (unsigned char *)malloc(data_len);
    if (!out)
        return NULL;

    for (size_t i = 0; i < data_len; i += AES_BLOCK_SIZE)
        AES_ecb_encrypt(data + i, out + i, &dec_key, AES_DECRYPT);

    return out;
}

/*----------------------------------------------------*/
/*  Example usage (optional)                           */
/*----------------------------------------------------*/
/*
#include <stdio.h>

int main(void)
{
    const unsigned char key[16]  = "0123456789ABCDEF";
    const unsigned char plain[16] = "plain-text-block";

    unsigned char *enc = encrypt_bytes_aes(plain, 16, key);
    unsigned char *dec = decrypt_bytes_aes(enc,   16, key);

    fwrite(enc, 1, 16, stdout); putchar('\n');
    fwrite(dec, 1, 16, stdout); putchar('\n');

    free(enc);
    free(dec);
    return 0;
}
*/