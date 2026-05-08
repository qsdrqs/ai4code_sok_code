#include <openssl/aes.h>
#include <stdlib.h>

/* Encrypts `data` using AES-ECB mode.
 * - data_len must be a multiple of 16 (AES_BLOCK_SIZE)
 * - key_len must be 16, 24, or 32 (for AES-128/192/256)
 * Returns a malloc'd buffer (data_len bytes) that the caller must free(),
 * or NULL on failure.
 */
unsigned char* encrypt_bytes_aes(const unsigned char* data, size_t data_len,
                                  const unsigned char* key, size_t key_len) {
    AES_KEY aes_key;
    if (AES_set_encrypt_key(key, (int)(key_len * 8), &aes_key) != 0) {
        return NULL;
    }

    unsigned char* output = (unsigned char*)malloc(data_len);
    if (output == NULL) return NULL;

    for (size_t i = 0; i < data_len; i += AES_BLOCK_SIZE) {
        AES_encrypt(data + i, output + i, &aes_key);
    }

    return output;
}

/* Decrypts `data` using AES-ECB mode.
 * - data_len must be a multiple of 16 (AES_BLOCK_SIZE)
 * - key_len must be 16, 24, or 32 (for AES-128/192/256)
 * Returns a malloc'd buffer (data_len bytes) that the caller must free(),
 * or NULL on failure.
 */
unsigned char* decrypt_bytes_aes(const unsigned char* data, size_t data_len,
                                  const unsigned char* key, size_t key_len) {
    AES_KEY aes_key;
    if (AES_set_decrypt_key(key, (int)(key_len * 8), &aes_key) != 0) {
        return NULL;
    }

    unsigned char* output = (unsigned char*)malloc(data_len);
    if (output == NULL) return NULL;

    for (size_t i = 0; i < data_len; i += AES_BLOCK_SIZE) {
        AES_decrypt(data + i, output + i, &aes_key);
    }

    return output;
}