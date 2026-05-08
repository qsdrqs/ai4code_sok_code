#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/aes.h>

typedef struct {
    unsigned char *key;
    int key_len;      // Key length in bytes (16, 24, or 32 for AES-128/192/256)
    int blk_sz;
} AEScipher;

// Constructor: equivalent to __init__
AEScipher* AEScipher_new(unsigned char *key, int key_len, int blk_sz) {
    AEScipher *self = (AEScipher*)malloc(sizeof(AEScipher));
    self->key = key;
    self->key_len = key_len;
    self->blk_sz = blk_sz;
    return self;
}

// Destructor
void AEScipher_free(AEScipher *self) {
    free(self);
}

// Encrypt method
// Returns heap-allocated ciphertext; caller must free(). *out_len holds its length.
unsigned char* AEScipher_encrypt(AEScipher *self, const unsigned char *msg,
                                 int msg_len, int *out_len) {
    // Padding (PKCS#7)
    int pad_len = self->blk_sz - (msg_len % self->blk_sz);
    int padded_len = msg_len + pad_len;
    
    unsigned char *padded_msg = (unsigned char*)malloc(padded_len);
    memcpy(padded_msg, msg, msg_len);
    memset(padded_msg + msg_len, (unsigned char)pad_len, pad_len);
    
    // Encryption
    AES_KEY aes_key;
    AES_set_encrypt_key(self->key, self->key_len * 8, &aes_key);
    
    unsigned char *ciphertext = (unsigned char*)malloc(padded_len);
    for (int i = 0; i < padded_len; i += self->blk_sz) {
        AES_ecb_encrypt(padded_msg + i, ciphertext + i, &aes_key, AES_ENCRYPT);
    }
    
    free(padded_msg);
    *out_len = padded_len;
    return ciphertext;
}

// Decrypt method
// Returns heap-allocated plaintext; caller must free(). *out_len holds its length.
unsigned char* AEScipher_decrypt(AEScipher *self, const unsigned char *ciphertext,
                                 int ct_len, int *out_len) {
    // Decryption
    AES_KEY aes_key;
    AES_set_decrypt_key(self->key, self->key_len * 8, &aes_key);
    
    unsigned char *msg = (unsigned char*)malloc(ct_len);
    for (int i = 0; i < ct_len; i += self->blk_sz) {
        AES_ecb_encrypt(ciphertext + i, msg + i, &aes_key, AES_DECRYPT);
    }
    
    // Remove padding
    int pad_len = msg[ct_len - 1];
    *out_len = ct_len - pad_len;
    
    return msg;
}