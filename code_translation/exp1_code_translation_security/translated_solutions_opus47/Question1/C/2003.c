#include <stdio.h>
#include <string.h>
#include <assert.h>
#include <openssl/aes.h>

/* Compile with: gcc program.c -lcrypto */

/**
 * Encodes a string using an AES key.
 */
void encode(const unsigned char *inp, size_t len, const unsigned char *key, unsigned char *out) {
    AES_KEY aes_key;
    AES_set_encrypt_key(key, 128, &aes_key);
    for (size_t i = 0; i < len; i += AES_BLOCK_SIZE) {
        AES_encrypt(inp + i, out + i, &aes_key);
    }
}

/**
 * Decodes a string using an AES key.
 */
void decode(const unsigned char *inp, size_t len, const unsigned char *key, unsigned char *out) {
    AES_KEY aes_key;
    AES_set_decrypt_key(key, 128, &aes_key);
    for (size_t i = 0; i < len; i += AES_BLOCK_SIZE) {
        AES_decrypt(inp + i, out + i, &aes_key);
    }
}

int main(void) {
    unsigned char inp[16] = "1111111111111111";
    unsigned char key[16] = "1111111111111111";
    unsigned char encrypted[16];
    unsigned char decrypted[16];

    encode(inp, 16, key, encrypted);
    decode(encrypted, 16, key, decrypted);

    assert(memcmp(decrypted, inp, 16) == 0);

    return 0;
}