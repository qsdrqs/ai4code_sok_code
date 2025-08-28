#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>
#include "aes.h" // Include your AES library header file

typedef struct {
    uint8_t *key;
    size_t blk_sz;
} AEScipher;

// Function to initialize the AEScipher struct
AEScipher* AEScipher_init(uint8_t *key, size_t blk_sz) {
    AEScipher *cipher = (AEScipher *)malloc(sizeof(AEScipher));
    cipher->key = key;
    cipher->blk_sz = blk_sz;
    return cipher;
}

// Function to encrypt a message
uint8_t* AEScipher_encrypt(AEScipher *cipher, const uint8_t *msg, size_t msg_len, size_t *out_len) {
    // Padding
    size_t pad_len = cipher->blk_sz - (msg_len % cipher->blk_sz);
    size_t padded_len = msg_len + pad_len;
    uint8_t *padded_msg = (uint8_t *)malloc(padded_len);
    memcpy(padded_msg, msg, msg_len);
    memset(padded_msg + msg_len, pad_len, pad_len);

    // Encryption
    uint8_t *encrypted_msg = (uint8_t *)malloc(padded_len);
    struct AES_ctx ctx;
    AES_init_ctx(&ctx, cipher->key);

    for (size_t i = 0; i < padded_len; i += cipher->blk_sz) {
        AES_ECB_encrypt(&ctx, padded_msg + i);
    }

    *out_len = padded_len;
    free(padded_msg);
    return encrypted_msg;
}

// Function to decrypt a ciphertext
uint8_t* AEScipher_decrypt(AEScipher *cipher, const uint8_t *ciphertext, size_t ciphertext_len, size_t *out_len) {
    // Decryption
    uint8_t *decrypted_msg = (uint8_t *)malloc(ciphertext_len);
    struct AES_ctx ctx;
    AES_init_ctx(&ctx, cipher->key);

    for (size_t i = 0; i < ciphertext_len; i += cipher->blk_sz) {
        AES_ECB_decrypt(&ctx, decrypted_msg + i);
    }

    // Remove padding
    uint8_t pad_len = decrypted_msg[ciphertext_len - 1];
    size_t msg_len = ciphertext_len - pad_len;
    uint8_t *unpadded_msg = (uint8_t *)malloc(msg_len);
    memcpy(unpadded_msg, decrypted_msg, msg_len);

    *out_len = msg_len;
    free(decrypted_msg);
    return unpadded_msg;
}

// Function to free the AEScipher struct
void AEScipher_free(AEScipher *cipher) {
    free(cipher);
}

// Example usage
int main() {
    uint8_t key[16] = {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F};
    size_t blk_sz = 16;

    AEScipher *cipher = AEScipher_init(key, blk_sz);

    const char *message = "Hello, World!";
    size_t msg_len = strlen(message);

    size_t encrypted_len;
    uint8_t *encrypted_msg = AEScipher_encrypt(cipher, (const uint8_t *)message, msg_len, &encrypted_len);

    printf("Encrypted message: ");
    for (size_t i = 0; i < encrypted_len; i++) {
        printf("%02X ", encrypted_msg[i]);
    }
    printf("\n");

    size_t decrypted_len;
    uint8_t *decrypted_msg = AEScipher_decrypt(cipher, encrypted_msg, encrypted_len, &decrypted_len);

    printf("Decrypted message: %.*s\n", (int)decrypted_len, decrypted_msg);

    free(encrypted_msg);
    free(decrypted_msg);
    AEScipher_free(cipher);

    return 0;
}