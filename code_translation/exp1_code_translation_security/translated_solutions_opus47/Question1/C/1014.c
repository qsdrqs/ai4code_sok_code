/*
 * Functions that one encrypts and the other decrypts a given string
 * using a given symmetric key. C translation using OpenSSL.
 *
 * Compile: gcc -o aes_cipher aes_cipher.c -lssl -lcrypto
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/aes.h>
#include <openssl/sha.h>
#include <openssl/rand.h>
#include <openssl/evp.h>
#include <openssl/bio.h>
#include <openssl/buffer.h>

#define BS 32  /* Padding block size (as in Python code) */

typedef struct {
    unsigned char key[SHA256_DIGEST_LENGTH];  /* 32 bytes */
    int bs;
} AESCipher;

/* --- Initialization (like __init__) --- */
void aes_cipher_init(AESCipher *cipher, const char *key) {
    cipher->bs = BS;
    SHA256((const unsigned char *)key, strlen(key), cipher->key);
}

/* --- PKCS7-style padding with block size = BS (32) --- */
static unsigned char *pad_data(const unsigned char *s, size_t len, size_t *padded_len) {
    size_t pad_len = BS - (len % BS);
    *padded_len = len + pad_len;
    unsigned char *padded = (unsigned char *)malloc(*padded_len);
    if (!padded) return NULL;
    memcpy(padded, s, len);
    memset(padded + len, (unsigned char)pad_len, pad_len);
    return padded;
}

static size_t unpad_data(const unsigned char *s, size_t len) {
    if (len == 0) return 0;
    unsigned char pad_len = s[len - 1];
    if (pad_len == 0 || pad_len > len) return len; /* invalid padding */
    return len - pad_len;
}

/* --- Base64 encoding via OpenSSL BIO --- */
static char *base64_encode(const unsigned char *data, size_t input_length) {
    BIO *bio, *b64;
    BUF_MEM *buffer_ptr;

    b64 = BIO_new(BIO_f_base64());
    bio = BIO_new(BIO_s_mem());
    bio = BIO_push(b64, bio);

    BIO_set_flags(bio, BIO_FLAGS_BASE64_NO_NL);
    BIO_write(bio, data, (int)input_length);
    (void)BIO_flush(bio);
    BIO_get_mem_ptr(bio, &buffer_ptr);

    char *result = (char *)malloc(buffer_ptr->length + 1);
    if (result) {
        memcpy(result, buffer_ptr->data, buffer_ptr->length);
        result[buffer_ptr->length] = '\0';
    }

    BIO_free_all(bio);
    return result;
}

/* --- Base64 decoding via OpenSSL BIO --- */
static unsigned char *base64_decode(const char *data, size_t *output_length) {
    BIO *bio, *b64;
    size_t input_length = strlen(data);
    unsigned char *buffer = (unsigned char *)malloc(input_length);
    if (!buffer) return NULL;

    b64 = BIO_new(BIO_f_base64());
    bio = BIO_new_mem_buf(data, (int)input_length);
    bio = BIO_push(b64, bio);

    BIO_set_flags(bio, BIO_FLAGS_BASE64_NO_NL);
    int read_len = BIO_read(bio, buffer, (int)input_length);
    *output_length = (read_len > 0) ? (size_t)read_len : 0;

    BIO_free_all(bio);
    return buffer;
}

/* --- Encrypt --- */
char *aes_cipher_encrypt(AESCipher *cipher, const char *raw) {
    size_t raw_len = strlen(raw);
    size_t padded_len;
    unsigned char *padded = pad_data((const unsigned char *)raw, raw_len, &padded_len);
    if (!padded) return NULL;

    unsigned char iv[AES_BLOCK_SIZE];
    if (RAND_bytes(iv, AES_BLOCK_SIZE) != 1) {
        free(padded);
        return NULL;
    }

    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_EncryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, cipher->key, iv);
    EVP_CIPHER_CTX_set_padding(ctx, 0); /* manual padding above */

    unsigned char *encrypted = (unsigned char *)malloc(padded_len + AES_BLOCK_SIZE);
    int outlen1 = 0, outlen2 = 0;
    EVP_EncryptUpdate(ctx, encrypted, &outlen1, padded, (int)padded_len);
    EVP_EncryptFinal_ex(ctx, encrypted + outlen1, &outlen2);

    EVP_CIPHER_CTX_free(ctx);

    /* Combine IV and encrypted data */
    size_t combined_len = AES_BLOCK_SIZE + outlen1 + outlen2;
    unsigned char *combined = (unsigned char *)malloc(combined_len);
    memcpy(combined, iv, AES_BLOCK_SIZE);
    memcpy(combined + AES_BLOCK_SIZE, encrypted, outlen1 + outlen2);

    char *b64 = base64_encode(combined, combined_len);

    free(padded);
    free(encrypted);
    free(combined);

    return b64;
}

/* --- Decrypt --- */
char *aes_cipher_decrypt(AESCipher *cipher, const char *enc) {
    size_t enc_len;
    unsigned char *decoded = base64_decode(enc, &enc_len);
    if (!decoded || enc_len < AES_BLOCK_SIZE) {
        free(decoded);
        return NULL;
    }

    unsigned char iv[AES_BLOCK_SIZE];
    memcpy(iv, decoded, AES_BLOCK_SIZE);

    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_DecryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, cipher->key, iv);
    EVP_CIPHER_CTX_set_padding(ctx, 0);

    size_t ciphertext_len = enc_len - AES_BLOCK_SIZE;
    unsigned char *decrypted = (unsigned char *)malloc(ciphertext_len + AES_BLOCK_SIZE);
    int outlen1 = 0, outlen2 = 0;
    EVP_DecryptUpdate(ctx, decrypted, &outlen1, decoded + AES_BLOCK_SIZE, (int)ciphertext_len);
    EVP_DecryptFinal_ex(ctx, decrypted + outlen1, &outlen2);

    EVP_CIPHER_CTX_free(ctx);

    size_t total_len = (size_t)(outlen1 + outlen2);
    size_t unpadded_len = unpad_data(decrypted, total_len);

    char *result = (char *)malloc(unpadded_len + 1);
    if (result) {
        memcpy(result, decrypted, unpadded_len);
        result[unpadded_len] = '\0';
    }

    free(decoded);
    free(decrypted);

    return result;
}

/* --- Demo (equivalent to the commented block at the bottom of Python file) --- */
int main(void) {
    const char *key = "mysecretpassword";
    AESCipher cipher;
    aes_cipher_init(&cipher, key);

    char *encrypted = aes_cipher_encrypt(&cipher, "Secret Message A");
    char *decrypted = aes_cipher_decrypt(&cipher, encrypted);

    printf("Encrypted: %s\n", encrypted);
    printf("Decrypted: %s\n", decrypted);

    free(encrypted);
    free(decrypted);
    return 0;
}