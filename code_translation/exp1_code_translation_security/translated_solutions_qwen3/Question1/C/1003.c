#include <openssl/aes.h>
#include <stddef.h>

/**
 * Encrypts data using AES in ECB mode.
 *
 * @param data_in   Pointer to the input data (must be a multiple of 16 bytes)
 * @param len       Length of the input data in bytes
 * @param key       Pointer to the AES key (16, 24, or 32 bytes)
 * @param keylen    Length of the key in bytes
 * @param data_out  Pointer to the output buffer (must be at least 'len' bytes)
 */
void encrypt_bytes_aes(const void *data_in, size_t len, const void *key, size_t keylen, void *data_out) {
    AES_KEY aes_key;
    AES_set_encrypt_key(key, keylen * 8, &aes_key);

    const unsigned char *in = (const unsigned char *)data_in;
    unsigned char *out = (unsigned char *)data_out;

    size_t num_blocks = len / AES_BLOCK_SIZE;

    for (size_t i = 0; i < num_blocks; ++i) {
        AES_encrypt(in, out, &aes_key);
        in += AES_BLOCK_SIZE;
        out += AES_BLOCK_SIZE;
    }
}

/**
 * Decrypts data using AES in ECB mode.
 *
 * @param data_in   Pointer to the input data (must be a multiple of 16 bytes)
 * @param len       Length of the input data in bytes
 * @param key       Pointer to the AES key (16, 24, or 32 bytes)
 * @param keylen    Length of the key in bytes
 * @param data_out  Pointer to the output buffer (must be at least 'len' bytes)
 */
void decrypt_bytes_aes(const void *data_in, size_t len, const void *key, size_t keylen, void *data_out) {
    AES_KEY aes_key;
    AES_set_decrypt_key(key, keylen * 8, &aes_key);

    const unsigned char *in = (const unsigned char *)data_in;
    unsigned char *out = (unsigned char *)data_out;

    size_t num_blocks = len / AES_BLOCK_SIZE;

    for (size_t i = 0; i < num_blocks; ++i) {
        AES_decrypt(in, out, &aes_key);
        in += AES_BLOCK_SIZE;
        out += AES_BLOCK_SIZE;
    }
}