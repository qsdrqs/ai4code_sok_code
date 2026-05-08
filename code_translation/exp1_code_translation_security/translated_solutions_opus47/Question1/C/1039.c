/* 
 * Compile with: gcc -o fernet fernet.c -lcrypto
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <stdint.h>
#include <openssl/evp.h>
#include <openssl/hmac.h>
#include <openssl/rand.h>
#include <openssl/bio.h>
#include <openssl/buffer.h>
#include <openssl/crypto.h>

#define AES_BLOCK_SIZE 16

/* URL-safe base64 encode */
static char* base64_url_encode(const unsigned char *input, int length) {
    BIO *bmem, *b64;
    BUF_MEM *bptr;

    b64 = BIO_new(BIO_f_base64());
    BIO_set_flags(b64, BIO_FLAGS_BASE64_NO_NL);
    bmem = BIO_new(BIO_s_mem());
    b64 = BIO_push(b64, bmem);
    BIO_write(b64, input, length);
    BIO_flush(b64);
    BIO_get_mem_ptr(b64, &bptr);

    char *buff = (char*)malloc(bptr->length + 1);
    memcpy(buff, bptr->data, bptr->length);
    buff[bptr->length] = '\0';

    for (size_t i = 0; i < bptr->length; i++) {
        if (buff[i] == '+') buff[i] = '-';
        else if (buff[i] == '/') buff[i] = '_';
    }

    BIO_free_all(b64);
    return buff;
}

/* URL-safe base64 decode */
static unsigned char* base64_url_decode(const char *input, int *output_length) {
    size_t input_length = strlen(input);
    char *modified = (char*)malloc(input_length + 5);
    memcpy(modified, input, input_length);

    for (size_t i = 0; i < input_length; i++) {
        if (modified[i] == '-') modified[i] = '+';
        else if (modified[i] == '_') modified[i] = '/';
    }
    while (input_length % 4 != 0) modified[input_length++] = '=';
    modified[input_length] = '\0';

    unsigned char *buffer = (unsigned char*)malloc(input_length);

    BIO *b64 = BIO_new(BIO_f_base64());
    BIO_set_flags(b64, BIO_FLAGS_BASE64_NO_NL);
    BIO *bmem = BIO_new_mem_buf(modified, (int)input_length);
    bmem = BIO_push(b64, bmem);
    *output_length = BIO_read(bmem, buffer, (int)input_length);

    BIO_free_all(bmem);
    free(modified);
    return buffer;
}

/* Returns malloc'd Fernet token (URL-safe base64). Caller must free. */
char* encrypt(const char *message, const char *key) {
    int key_length;
    unsigned char *decoded_key = base64_url_decode(key, &key_length);
    if (key_length != 32) { free(decoded_key); return NULL; }

    unsigned char *signing_key    = decoded_key;       /* first 16 bytes */
    unsigned char *encryption_key = decoded_key + 16;  /* last  16 bytes */

    unsigned char iv[16];
    if (RAND_bytes(iv, 16) != 1) { free(decoded_key); return NULL; }

    uint64_t timestamp = (uint64_t)time(NULL);

    int message_length = (int)strlen(message);
    unsigned char *ciphertext = (unsigned char*)malloc(message_length + AES_BLOCK_SIZE);

    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, encryption_key, iv);
    int len;
    EVP_EncryptUpdate(ctx, ciphertext, &len, (const unsigned char*)message, message_length);
    int ciphertext_length = len;
    EVP_EncryptFinal_ex(ctx, ciphertext + len, &len);
    ciphertext_length += len;
    EVP_CIPHER_CTX_free(ctx);

    /* Build: version(1) | timestamp(8) | iv(16) | ciphertext | hmac(32) */
    int token_length = 1 + 8 + 16 + ciphertext_length;
    unsigned char *token = (unsigned char*)malloc(token_length + 32);

    token[0] = 0x80;
    for (int i = 0; i < 8; i++)
        token[1 + i] = (timestamp >> (56 - 8 * i)) & 0xff;
    memcpy(token + 9,  iv, 16);
    memcpy(token + 25, ciphertext, ciphertext_length);

    unsigned int hmac_length;
    HMAC(EVP_sha256(), signing_key, 16, token, token_length,
         token + token_length, &hmac_length);
    token_length += 32;

    char *encoded = base64_url_encode(token, token_length);

    free(ciphertext); free(token); free(decoded_key);
    return encoded;
}

/* Returns malloc'd plaintext. Caller must free. NULL on failure. */
char* decrypt(const char *encrypted_message, const char *key) {
    int key_length;
    unsigned char *decoded_key = base64_url_decode(key, &key_length);
    if (key_length != 32) { free(decoded_key); return NULL; }

    unsigned char *signing_key    = decoded_key;
    unsigned char *encryption_key = decoded_key + 16;

    int token_length;
    unsigned char *token = base64_url_decode(encrypted_message, &token_length);

    if (token_length < 57 || token[0] != 0x80) {
        free(token); free(decoded_key); return NULL;
    }

    /* Verify HMAC (constant-time compare) */
    unsigned char hmac[32];
    unsigned int hmac_length;
    HMAC(EVP_sha256(), signing_key, 16, token, token_length - 32, hmac, &hmac_length);
    if (CRYPTO_memcmp(hmac, token + token_length - 32, 32) != 0) {
        free(token); free(decoded_key); return NULL;
    }

    unsigned char *iv         = token + 9;
    unsigned char *ciphertext = token + 25;
    int ciphertext_length = token_length - 25 - 32;

    unsigned char *plaintext = (unsigned char*)malloc(ciphertext_length + 1);

    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, encryption_key, iv);
    int len;
    EVP_DecryptUpdate(ctx, plaintext, &len, ciphertext, ciphertext_length);
    int plaintext_length = len;
    if (EVP_DecryptFinal_ex(ctx, plaintext + len, &len) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(plaintext); free(token); free(decoded_key);
        return NULL;
    }
    plaintext_length += len;
    EVP_CIPHER_CTX_free(ctx);
    plaintext[plaintext_length] = '\0';

    free(token); free(decoded_key);
    return (char*)plaintext;
}

/* Demonstration */
int main(void) {
    unsigned char key_bytes[32];
    RAND_bytes(key_bytes, 32);
    char *key = base64_url_encode(key_bytes, 32);
    printf("Key:       %s\n", key);

    const char *message = "Hello, World!";
    char *encrypted = encrypt(message, key);
    printf("Encrypted: %s\n", encrypted);

    char *decrypted = decrypt(encrypted, key);
    printf("Decrypted: %s\n", decrypted);

    free(key); free(encrypted); free(decrypted);
    return 0;
}