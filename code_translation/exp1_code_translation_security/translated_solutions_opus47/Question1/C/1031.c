/*
 * Fernet encryption implementation in C using OpenSSL
 * 
 * Compile with: gcc -o fernet fernet.c -lcrypto
 * 
 * Fernet specification:
 * - Key = 32 bytes, URL-safe base64 encoded
 *   - First 16 bytes: HMAC signing key
 *   - Last 16 bytes: AES-128 encryption key
 * - Token = base64(version(1) + timestamp(8) + IV(16) + ciphertext + HMAC(32))
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>
#include <time.h>
#include <openssl/evp.h>
#include <openssl/hmac.h>
#include <openssl/rand.h>
#include <openssl/bio.h>
#include <openssl/buffer.h>

/* Global key to mimic Python's global 'fernet' variable */
static char *fernet_key = NULL;

/* URL-safe base64 encoding */
char *b64_url_encode(const unsigned char *input, size_t length) {
    BIO *bmem, *b64;
    BUF_MEM *bptr;

    b64 = BIO_new(BIO_f_base64());
    BIO_set_flags(b64, BIO_FLAGS_BASE64_NO_NL);
    bmem = BIO_new(BIO_s_mem());
    b64 = BIO_push(b64, bmem);
    BIO_write(b64, input, length);
    BIO_flush(b64);
    BIO_get_mem_ptr(b64, &bptr);

    size_t out_len = bptr->length;
    char *output = (char *)malloc(out_len + 1);
    memcpy(output, bptr->data, out_len);
    output[out_len] = '\0';

    BIO_free_all(b64);

    /* Convert standard base64 to URL-safe base64 */
    for (size_t i = 0; i < out_len; i++) {
        if (output[i] == '+') output[i] = '-';
        else if (output[i] == '/') output[i] = '_';
    }
    return output;
}

/* URL-safe base64 decoding */
unsigned char *b64_url_decode(const char *input, size_t *output_len) {
    size_t input_len = strlen(input);
    char *temp = (char *)malloc(input_len + 1);
    memcpy(temp, input, input_len + 1);

    /* Convert URL-safe to standard base64 */
    for (size_t i = 0; i < input_len; i++) {
        if (temp[i] == '-') temp[i] = '+';
        else if (temp[i] == '_') temp[i] = '/';
    }

    BIO *bmem, *b64;
    b64 = BIO_new(BIO_f_base64());
    BIO_set_flags(b64, BIO_FLAGS_BASE64_NO_NL);
    bmem = BIO_new_mem_buf(temp, input_len);
    bmem = BIO_push(b64, bmem);

    unsigned char *output = (unsigned char *)malloc(input_len);
    *output_len = BIO_read(bmem, output, input_len);

    BIO_free_all(bmem);
    free(temp);
    return output;
}

/* Generate a Fernet key: 32 random bytes, URL-safe base64 encoded */
char *generate_fernet_key(void) {
    unsigned char key[32];
    RAND_bytes(key, 32);
    return b64_url_encode(key, 32);
}

/*
 * encrypt using a symmetric key
 */
char *encrypt(const char *message) {
    size_t key_len;
    unsigned char *raw_key = b64_url_decode(fernet_key, &key_len);

    unsigned char signing_key[16], encryption_key[16];
    memcpy(signing_key, raw_key, 16);
    memcpy(encryption_key, raw_key + 16, 16);

    unsigned char iv[16];
    RAND_bytes(iv, 16);

    uint64_t timestamp = (uint64_t)time(NULL);
    size_t message_len = strlen(message);
    unsigned char *ciphertext = (unsigned char *)malloc(message_len + 16);

    /* AES-128-CBC encryption with PKCS7 padding */
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, encryption_key, iv);

    int len, total_len = 0;
    EVP_EncryptUpdate(ctx, ciphertext, &len,
                      (const unsigned char *)message, message_len);
    total_len += len;
    EVP_EncryptFinal_ex(ctx, ciphertext + total_len, &len);
    total_len += len;
    EVP_CIPHER_CTX_free(ctx);

    /* Build token: version(1) + timestamp(8) + iv(16) + ciphertext */
    size_t token_data_len = 1 + 8 + 16 + total_len;
    unsigned char *token_data = (unsigned char *)malloc(token_data_len);
    token_data[0] = 0x80;
    for (int i = 0; i < 8; i++)
        token_data[1 + i] = (timestamp >> ((7 - i) * 8)) & 0xFF;
    memcpy(token_data + 9, iv, 16);
    memcpy(token_data + 25, ciphertext, total_len);

    /* Append HMAC-SHA256 */
    unsigned char hmac[32];
    unsigned int hmac_len;
    HMAC(EVP_sha256(), signing_key, 16, token_data,
         token_data_len, hmac, &hmac_len);

    size_t final_len = token_data_len + 32;
    unsigned char *final_token = (unsigned char *)malloc(final_len);
    memcpy(final_token, token_data, token_data_len);
    memcpy(final_token + token_data_len, hmac, 32);

    char *enc_message = b64_url_encode(final_token, final_len);

    free(raw_key);
    free(ciphertext);
    free(token_data);
    free(final_token);
    return enc_message;
}

/*
 * decrypt using a symmetric key
 */
char *decrypt(const char *enc_message) {
    size_t key_len;
    unsigned char *raw_key = b64_url_decode(fernet_key, &key_len);

    unsigned char signing_key[16], encryption_key[16];
    memcpy(signing_key, raw_key, 16);
    memcpy(encryption_key, raw_key + 16, 16);

    size_t token_len;
    unsigned char *token = b64_url_decode(enc_message, &token_len);

    /* Verify HMAC */
    unsigned char hmac[32];
    unsigned int hmac_len;
    HMAC(EVP_sha256(), signing_key, 16, token, token_len - 32, hmac, &hmac_len);
    if (memcmp(hmac, token + token_len - 32, 32) != 0) {
        free(raw_key);
        free(token);
        fprintf(stderr, "Invalid token (HMAC mismatch)\n");
        return NULL;
    }

    unsigned char *iv = token + 9;
    unsigned char *ciphertext = token + 25;
    size_t ciphertext_len = token_len - 25 - 32;

    unsigned char *plaintext = (unsigned char *)malloc(ciphertext_len + 1);

    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, encryption_key, iv);

    int len, total_len = 0;
    EVP_DecryptUpdate(ctx, plaintext, &len, ciphertext, ciphertext_len);
    total_len += len;
    EVP_DecryptFinal_ex(ctx, plaintext + total_len, &len);
    total_len += len;
    plaintext[total_len] = '\0';
    EVP_CIPHER_CTX_free(ctx);

    free(raw_key);
    free(token);
    return (char *)plaintext;
}

int main(void) {
    const char *message = "the solutions are here somewhere";
    printf("Secret Message: %s\n", message);

    fernet_key = generate_fernet_key();

    char *encMessage = encrypt(message);
    printf("%s\n", encMessage);

    char *decMessage = decrypt(encMessage);
    printf("decrypted Message: %s\n", decMessage);

    free(fernet_key);
    free(encMessage);
    free(decMessage);
    return 0;
}