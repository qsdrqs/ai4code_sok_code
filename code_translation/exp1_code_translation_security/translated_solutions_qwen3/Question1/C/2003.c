#include <openssl/aes.h>     // OpenSSL AES functions
#include <assert.h>          // For assert()
#include <string.h>          // For memcmp()

/**
 * Encrypts a 16-byte block using AES-128 in ECB mode.
 *
 * @param inp  Input 16-byte block to encrypt
 * @param key  16-byte AES key
 * @param out  Output buffer for encrypted data
 */
void encode(const unsigned char *inp, const unsigned char *key, unsigned char *out) {
    AES_KEY aes_key;
    AES_set_encrypt_key(key, 128, &aes_key);  // Set up encryption key
    AES_encrypt(inp, out, &aes_key);          // Encrypt one block
}

/**
 * Decrypts a 16-byte block using AES-128 in ECB mode.
 *
 * @param inp  Input 16-byte block to decrypt
 * @param key  16-byte AES key
 * @param out  Output buffer for decrypted data
 */
void decode(const unsigned char *inp, const unsigned char *key, unsigned char *out) {
    AES_KEY aes_key;
    AES_set_decrypt_key(key, 128, &aes_key);  // Set up decryption key
    AES_decrypt(inp, out, &aes_key);          // Decrypt one block
}

int main() {
    // Initialize input and key as 16-byte arrays of '1's
    unsigned char inp[16] = {
        '1','1','1','1','1','1','1','1',
        '1','1','1','1','1','1','1','1'
    };
    unsigned char key[16] = {
        '1','1','1','1','1','1','1','1',
        '1','1','1','1','1','1','1','1'
    };

    unsigned char encrypted[16];
    unsigned char decrypted[16];

    // Encrypt and decrypt
    encode(inp, key, encrypted);
    decode(encrypted, key, decrypted);

    // Assert that the decrypted data matches the original input
    assert(memcmp(inp, decrypted, 16) == 0);

    return 0;
}