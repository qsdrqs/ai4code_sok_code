#include <openssl/aes.h>
#include <string.h>
#include <stdlib.h>

typedef struct {
    AES_KEY enc_key;
    AES_KEY dec_key;
    size_t blk_sz;
} AESCipher;

int AESCipher_init(AESCipher *ctx, const unsigned char *key, size_t key_len, size_t blk_sz) {
    if (!ctx || !key || (key_len != 16 && key_len != 24 && key_len != 32)) {
        return -1; // Invalid key length
    }
    AES_set_encrypt_key(key, key_len * 8, &ctx->enc_key);
    AES_set_decrypt_key(key, key_len * 8, &ctx->dec_key);
    ctx->blk_sz = blk_sz;
    return 0;
}

unsigned char* AESCipher_encrypt(AESCipher *ctx, const unsigned char *msg, size_t len, size_t *out_len) {
    if (!ctx || !msg || !out_len) {
        return NULL;
    }

    // Calculate padding length
    size_t pad_len = ctx->blk_sz - (len % ctx->blk_sz);
    size_t padded_len = len + pad_len;

    // Allocate memory for padded message
    unsigned char *padded = (unsigned char *)malloc(padded_len);
    if (!padded) {
        return NULL;
    }

    // Copy message and add padding
    memcpy(padded, msg, len);
    memset(padded + len, pad_len, pad_len);

    // Allocate memory for encrypted data
    unsigned char *cipher_text = (unsigned char *)malloc(padded_len);
    if (!cipher_text) {
        free(padded);
        return NULL;
    }

    // Encrypt each block
    for (size_t i = 0; i < padded_len; i += AES_BLOCK_SIZE) {
        AES_encrypt(padded + i, cipher_text + i, &ctx->enc_key);
    }

    free(padded);
    *out_len = padded_len;
    return cipher_text;
}

unsigned char* AESCipher_decrypt(AESCipher *ctx, const unsigned char *cipher_text, size_t len, size_t *out_len) {
    if (!ctx || !cipher_text || !out_len || len % AES_BLOCK_SIZE != 0) {
        return NULL;
    }

    // Allocate memory for decrypted data
    unsigned char *decrypted = (unsigned char *)malloc(len);
    if (!decrypted) {
        return NULL;
    }

    // Decrypt each block
    for (size_t i = 0; i < len; i += AES_BLOCK_SIZE) {
        AES_decrypt(cipher_text + i, decrypted + i, &ctx->dec_key);
    }

    // Extract padding length
    size_t pad_len = decrypted[len - 1];

    // Validate padding length to prevent buffer overflows
    if (pad_len == 0 || pad_len > ctx->blk_sz || pad_len > len) {
        free(decrypted);
        return NULL;
    }

    // Calculate output length and allocate memory
    *out_len = len - pad_len;
    unsigned char *plain_text = (unsigned char *)malloc(*out_len);
    if (!plain_text) {
        free(decrypted);
        return NULL;
    }

    // Copy decrypted data without padding
    memcpy(plain_text, decrypted, *out_len);

    free(decrypted);
    return plain_text;
}