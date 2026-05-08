/* Compile with: gcc -o fernet fernet.c -lcrypto */

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

/* -------- URL-safe Base64 helpers -------- */
static char *base64_url_encode(const unsigned char *input, size_t length) {
    BIO *b64 = BIO_new(BIO_f_base64());
    BIO *bio = BIO_new(BIO_s_mem());
    BUF_MEM *bufferPtr;
    bio = BIO_push(b64, bio);
    BIO_set_flags(bio, BIO_FLAGS_BASE64_NO_NL);
    BIO_write(bio, input, length);
    BIO_flush(bio);
    BIO_get_mem_ptr(bio, &bufferPtr);

    char *result = (char *)malloc(bufferPtr->length + 1);
    memcpy(result, bufferPtr->data, bufferPtr->length);
    result[bufferPtr->length] = '\0';
    for (size_t i = 0; i < bufferPtr->length; i++) {
        if (result[i] == '+') result[i] = '-';
        else if (result[i] == '/') result[i] = '_';
    }
    BIO_free_all(bio);
    return result;
}

static unsigned char *base64_url_decode(const char *input, size_t *out_length) {
    size_t in_len = strlen(input);
    char *temp = (char *)malloc(in_len + 1);
    memcpy(temp, input, in_len);
    temp[in_len] = '\0';
    for (size_t i = 0; i < in_len; i++) {
        if (temp[i] == '-') temp[i] = '+';
        else if (temp[i] == '_') temp[i] = '/';
    }
    unsigned char *buffer = (unsigned char *)malloc(in_len);
    BIO *bio = BIO_new_mem_buf(temp, -1);
    BIO *b64 = BIO_new(BIO_f_base64());
    bio = BIO_push(b64, bio);
    BIO_set_flags(bio, BIO_FLAGS_BASE64_NO_NL);

    int n = BIO_read(bio, buffer, in_len);
    *out_length = (n > 0) ? (size_t)n : 0;
    BIO_free_all(bio);
    free(temp);
    return buffer;
}

/* -------- Fernet key generation -------- */
char *generate_key(void) {
    unsigned char key[32];
    RAND_bytes(key, 32);
    return base64_url_encode(key, 32);
}

/* -------- Encrypt (Fernet) -------- */
char *encrypt(const unsigned char *data, size_t data_len, const char *key_b64) {
    size_t key_len;
    unsigned char *key = base64_url_decode(key_b64, &key_len);
    if (key_len != 32) { free(key); return NULL; }

    unsigned char *signing_key    = key;       /* first 16 bytes */
    unsigned char *encryption_key = key + 16;  /* last  16 bytes */

    unsigned char iv[16];
    RAND_bytes(iv, 16);
    uint64_t timestamp = (uint64_t)time(NULL);

    /* PKCS#7 pad */
    size_t pad = 16 - (data_len % 16);
    size_t padded_len = data_len + pad;
    unsigned char *padded = (unsigned char *)malloc(padded_len);
    memcpy(padded, data, data_len);
    memset(padded + data_len, (int)pad, pad);

    /* AES-128-CBC */
    unsigned char *ciphertext = (unsigned char *)malloc(padded_len);
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, encryption_key, iv);
    EVP_CIPHER_CTX_set_padding(ctx, 0);
    int len = 0, ct_len = 0;
    EVP_EncryptUpdate(ctx, ciphertext, &len, padded, padded_len);
    ct_len = len;
    EVP_EncryptFinal_ex(ctx, ciphertext + len, &len);
    ct_len += len;
    EVP_CIPHER_CTX_free(ctx);

    /* Assemble token: 0x80 || timestamp(8 BE) || IV(16) || ciphertext || HMAC(32) */
    size_t body_len = 1 + 8 + 16 + ct_len;
    unsigned char *token = (unsigned char *)malloc(body_len + 32);
    token[0] = 0x80;
    for (int i = 0; i < 8; i++)
        token[1 + i] = (timestamp >> (56 - i * 8)) & 0xFF;
    memcpy(token + 9,  iv, 16);
    memcpy(token + 25, ciphertext, ct_len);

    unsigned char mac[32];
    unsigned int mac_len;
    HMAC(EVP_sha256(), signing_key, 16, token, body_len, mac, &mac_len);
    memcpy(token + body_len, mac, 32);

    char *result = base64_url_encode(token, body_len + 32);

    free(padded); free(ciphertext); free(token); free(key);
    return result;
}

/* -------- Decrypt (Fernet) -------- */
unsigned char *decrypt(const char *token_b64, const char *key_b64, size_t *out_len) {
    size_t key_len;
    unsigned char *key = base64_url_decode(key_b64, &key_len);
    if (key_len != 32) { free(key); return NULL; }

    unsigned char *signing_key    = key;
    unsigned char *encryption_key = key + 16;

    size_t token_len;
    unsigned char *token = base64_url_decode(token_b64, &token_len);
    if (token_len < 57 || token[0] != 0x80) { free(key); free(token); return NULL; }

    unsigned char mac[32];
    unsigned int mac_len;
    HMAC(EVP_sha256(), signing_key, 16, token, token_len - 32, mac, &mac_len);
    if (memcmp(mac, token + token_len - 32, 32) != 0) {
        free(key); free(token); return NULL;
    }

    unsigned char *iv = token + 9;
    unsigned char *ct = token + 25;
    size_t ct_len     = token_len - 25 - 32;

    unsigned char *plaintext = (unsigned char *)malloc(ct_len + 1);
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, encryption_key, iv);
    EVP_CIPHER_CTX_set_padding(ctx, 0);
    int len = 0, pt_len = 0;
    EVP_DecryptUpdate(ctx, plaintext, &len, ct, ct_len);
    pt_len = len;
    EVP_DecryptFinal_ex(ctx, plaintext + len, &len);
    pt_len += len;
    EVP_CIPHER_CTX_free(ctx);

    /* strip PKCS#7 padding */
    int pad = plaintext[pt_len - 1];
    pt_len -= pad;
    plaintext[pt_len] = '\0';
    *out_len = pt_len;

    free(key); free(token);
    return plaintext;
}

/* -------- main -------- */
int main(void) {
    char *key = generate_key();
    const char *data = "/safedir/test.txt";
    char *encrypted  = encrypt((const unsigned char *)data, strlen(data), key);

    printf("Key:       %s\n", key);
    printf("Encrypted: %s\n", encrypted);

    free(key);
    free(encrypted);
    return 0;
}