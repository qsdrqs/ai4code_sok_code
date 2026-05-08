#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/evp.h>
#include <openssl/rand.h>
#include <openssl/bio.h>
#include <openssl/buffer.h>

#define AES_BLOCK_SIZE 16

/* PKCS7 padding */
static unsigned char *pad(const unsigned char *plaintext, size_t plaintext_len,
                          size_t *padded_len) {
    size_t pad_len = AES_BLOCK_SIZE - (plaintext_len % AES_BLOCK_SIZE);
    *padded_len = plaintext_len + pad_len;
    unsigned char *padded = malloc(*padded_len);
    if (!padded) return NULL;
    memcpy(padded, plaintext, plaintext_len);
    memset(padded + plaintext_len, (unsigned char)pad_len, pad_len);
    return padded;
}

/* PKCS7 unpadding */
static unsigned char *unpad(const unsigned char *plaintext, size_t plaintext_len,
                            size_t *unpadded_len) {
    if (plaintext_len == 0) return NULL;
    unsigned char pad_len = plaintext[plaintext_len - 1];
    if (pad_len == 0 || pad_len > AES_BLOCK_SIZE || pad_len > plaintext_len)
        return NULL;
    *unpadded_len = plaintext_len - pad_len;
    unsigned char *unpadded = malloc(*unpadded_len + 1);
    if (!unpadded) return NULL;
    memcpy(unpadded, plaintext, *unpadded_len);
    unpadded[*unpadded_len] = '\0';
    return unpadded;
}

/* Base64 encode */
static char *b64_encode(const unsigned char *input, size_t length) {
    BIO *b64 = BIO_new(BIO_f_base64());
    BIO *bmem = BIO_new(BIO_s_mem());
    BIO_set_flags(b64, BIO_FLAGS_BASE64_NO_NL);
    b64 = BIO_push(b64, bmem);
    BIO_write(b64, input, (int)length);
    BIO_flush(b64);

    BUF_MEM *bptr;
    BIO_get_mem_ptr(b64, &bptr);
    char *output = malloc(bptr->length + 1);
    memcpy(output, bptr->data, bptr->length);
    output[bptr->length] = '\0';

    BIO_free_all(b64);
    return output;
}

/* Base64 decode */
static unsigned char *b64_decode(const char *input, size_t *output_len) {
    size_t input_len = strlen(input);
    unsigned char *output = malloc(input_len);
    if (!output) return NULL;

    BIO *b64 = BIO_new(BIO_f_base64());
    BIO *bmem = BIO_new_mem_buf(input, (int)input_len);
    BIO_set_flags(b64, BIO_FLAGS_BASE64_NO_NL);
    bmem = BIO_push(b64, bmem);

    int n = BIO_read(bmem, output, (int)input_len);
    *output_len = (n > 0) ? (size_t)n : 0;
    BIO_free_all(bmem);
    return output;
}

/* Select the AES-CBC cipher based on key size (in bytes) */
static const EVP_CIPHER *select_cipher(int key_size) {
    switch (key_size) {
        case 16: return EVP_aes_128_cbc();
        case 24: return EVP_aes_192_cbc();
        case 32: return EVP_aes_256_cbc();
        default: return NULL;
    }
}

/* Encrypt plaintext using symmetric key (returns base64-encoded string) */
char *encrypt(const unsigned char *key, int key_size,
              const unsigned char *plaintext, size_t plaintext_len) {
    unsigned char iv[AES_BLOCK_SIZE];
    if (RAND_bytes(iv, AES_BLOCK_SIZE) != 1) return NULL;

    const EVP_CIPHER *cipher = select_cipher(key_size);
    if (!cipher) return NULL;

    size_t padded_len;
    unsigned char *padded_txt = pad(plaintext, plaintext_len, &padded_len);
    if (!padded_txt) return NULL;

    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    unsigned char *cipher_txt = malloc(padded_len);
    int len = 0, cipher_len = 0;

    EVP_EncryptInit_ex(ctx, cipher, NULL, key, iv);
    EVP_CIPHER_CTX_set_padding(ctx, 0);  /* we pad manually */
    EVP_EncryptUpdate(ctx, cipher_txt, &len, padded_txt, (int)padded_len);
    cipher_len = len;
    EVP_EncryptFinal_ex(ctx, cipher_txt + len, &len);
    cipher_len += len;
    EVP_CIPHER_CTX_free(ctx);

    /* Prepend IV to ciphertext */
    unsigned char *iv_cipher = malloc(AES_BLOCK_SIZE + cipher_len);
    memcpy(iv_cipher, iv, AES_BLOCK_SIZE);
    memcpy(iv_cipher + AES_BLOCK_SIZE, cipher_txt, cipher_len);

    char *result = b64_encode(iv_cipher, AES_BLOCK_SIZE + cipher_len);

    free(padded_txt);
    free(cipher_txt);
    free(iv_cipher);
    return result;
}

/* Decrypt cipher text using symmetric key */
unsigned char *decrypt(const unsigned char *key, int key_size,
                       const char *ciphertext, size_t *plaintext_len) {
    const EVP_CIPHER *cipher = select_cipher(key_size);
    if (!cipher) return NULL;

    size_t decoded_len;
    unsigned char *decoded = b64_decode(ciphertext, &decoded_len);
    if (!decoded || decoded_len < AES_BLOCK_SIZE) {
        free(decoded);
        return NULL;
    }

    unsigned char iv[AES_BLOCK_SIZE];
    memcpy(iv, decoded, AES_BLOCK_SIZE);

    size_t ct_len = decoded_len - AES_BLOCK_SIZE;
    unsigned char *plaintext = malloc(ct_len);
    int len = 0, pt_len = 0;

    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_DecryptInit_ex(ctx, cipher, NULL, key, iv);
    EVP_CIPHER_CTX_set_padding(ctx, 0);  /* we unpad manually */
    EVP_DecryptUpdate(ctx, plaintext, &len, decoded + AES_BLOCK_SIZE, (int)ct_len);
    pt_len = len;
    EVP_DecryptFinal_ex(ctx, plaintext + len, &len);
    pt_len += len;
    EVP_CIPHER_CTX_free(ctx);
    free(decoded);

    size_t unpadded_len;
    unsigned char *unpadded = unpad(plaintext, pt_len, &unpadded_len);
    free(plaintext);

    if (plaintext_len) *plaintext_len = unpadded_len;
    return unpadded;
}

/* Example usage */
int main(void) {
    unsigned char key[32] = "01234567890123456789012345678901";
    const char *msg = "Hello, World! This is a secret message.";

    printf("Plaintext:  %s\n", msg);

    char *ct = encrypt(key, 32, (const unsigned char *)msg, strlen(msg));
    printf("Ciphertext: %s\n", ct);

    size_t pt_len;
    unsigned char *pt = decrypt(key, 32, ct, &pt_len);
    printf("Decrypted:  %s\n", pt);

    free(ct);
    free(pt);
    return 0;
}