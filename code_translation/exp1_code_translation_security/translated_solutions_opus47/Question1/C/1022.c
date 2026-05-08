/*
 * Fernet symmetric encryption in C (OpenSSL).
 * Compile with: gcc -o fernet fernet.c -lssl -lcrypto
 *
 * Token = base64url( 0x80 || timestamp(8) || IV(16) || ciphertext || HMAC-SHA256(32) )
 * Key   = base64url( 32 random bytes );  first 16 = signing key, last 16 = AES key
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

static char *base64_urlsafe_encode(const unsigned char *input, int length) {
    BIO *b64  = BIO_new(BIO_f_base64());
    BIO *bmem = BIO_new(BIO_s_mem());
    b64 = BIO_push(b64, bmem);
    BIO_set_flags(b64, BIO_FLAGS_BASE64_NO_NL);
    BIO_write(b64, input, length);
    BIO_flush(b64);

    BUF_MEM *bptr;
    BIO_get_mem_ptr(b64, &bptr);

    char *output = malloc(bptr->length + 1);
    memcpy(output, bptr->data, bptr->length);
    output[bptr->length] = '\0';

    for (size_t i = 0; i < bptr->length; i++) {
        if (output[i] == '+')      output[i] = '-';
        else if (output[i] == '/') output[i] = '_';
    }

    BIO_free_all(b64);
    return output;
}

static int base64_urlsafe_decode(const char *input, unsigned char *output, int max_length) {
    int input_length = (int)strlen(input);
    int padding = (4 - input_length % 4) % 4;
    char *temp = malloc(input_length + padding + 1);
    memcpy(temp, input, input_length);

    for (int i = 0; i < input_length; i++) {
        if (temp[i] == '-')      temp[i] = '+';
        else if (temp[i] == '_') temp[i] = '/';
    }
    for (int i = 0; i < padding; i++) temp[input_length + i] = '=';
    temp[input_length + padding] = '\0';

    BIO *b64  = BIO_new(BIO_f_base64());
    BIO *bmem = BIO_new_mem_buf(temp, -1);
    bmem = BIO_push(b64, bmem);
    BIO_set_flags(bmem, BIO_FLAGS_BASE64_NO_NL);

    int length = BIO_read(bmem, output, max_length);
    BIO_free_all(bmem);
    free(temp);
    return length;
}

char *generate_key(void) {
    unsigned char key[32];
    RAND_bytes(key, sizeof(key));
    return base64_urlsafe_encode(key, sizeof(key));
}

char *encrypt(const char *inp, const char *key) {
    unsigned char decoded_key[32];
    base64_urlsafe_decode(key, decoded_key, sizeof(decoded_key));
    unsigned char *signing_key    = decoded_key;
    unsigned char *encryption_key = decoded_key + 16;

    int message_len = (int)strlen(inp);
    int padded_len  = ((message_len / 16) + 1) * 16;      /* PKCS7 always adds padding */
    int total_len   = 1 + 8 + 16 + padded_len + 32;
    unsigned char *token = malloc(total_len);

    /* Version */
    token[0] = 0x80;

    /* Timestamp: big-endian */
    uint64_t timestamp = (uint64_t)time(NULL);
    for (int i = 0; i < 8; i++)
        token[1 + i] = (timestamp >> (56 - i * 8)) & 0xff;

    /* IV */
    unsigned char *iv = token + 9;
    RAND_bytes(iv, 16);

    /* AES-128-CBC encrypt */
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, encryption_key, iv);
    int out_len = 0, final_len = 0;
    EVP_EncryptUpdate(ctx, token + 25, &out_len, (const unsigned char *)inp, message_len);
    EVP_EncryptFinal_ex(ctx, token + 25 + out_len, &final_len);
    EVP_CIPHER_CTX_free(ctx);

    /* HMAC-SHA256 over everything above */
    unsigned int hmac_len;
    HMAC(EVP_sha256(), signing_key, 16,
         token, 1 + 8 + 16 + padded_len,
         token + 1 + 8 + 16 + padded_len, &hmac_len);

    char *encoded = base64_urlsafe_encode(token, total_len);
    free(token);
    return encoded;
}

char *decrypt(const char *inp, const char *key) {
    unsigned char decoded_key[32];
    base64_urlsafe_decode(key, decoded_key, sizeof(decoded_key));
    unsigned char *signing_key    = decoded_key;
    unsigned char *encryption_key = decoded_key + 16;

    int encrypted_len = (int)strlen(inp);
    unsigned char *token = malloc(encrypted_len);
    int token_len = base64_urlsafe_decode(inp, token, encrypted_len);

    /* Verify HMAC */
    unsigned char hmac[32];
    unsigned int hmac_len;
    HMAC(EVP_sha256(), signing_key, 16, token, token_len - 32, hmac, &hmac_len);
    if (memcmp(hmac, token + token_len - 32, 32) != 0) {
        free(token);
        return NULL;
    }

    unsigned char *iv         = token + 9;
    unsigned char *ciphertext = token + 25;
    int ciphertext_len        = token_len - 25 - 32;

    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, encryption_key, iv);

    unsigned char *plaintext = malloc(ciphertext_len + 1);
    int out_len = 0, final_len = 0;
    EVP_DecryptUpdate(ctx, plaintext, &out_len, ciphertext, ciphertext_len);
    EVP_DecryptFinal_ex(ctx, plaintext + out_len, &final_len);
    EVP_CIPHER_CTX_free(ctx);

    plaintext[out_len + final_len] = '\0';
    free(token);
    return (char *)plaintext;
}

int main(void) {
    char *ex_key = generate_key();

    char *encrypted = encrypt("Hello World!", ex_key);
    printf("%s\n", encrypted);

    char *decrypted = decrypt(encrypted, ex_key);
    printf("%s\n", decrypted);

    free(ex_key);
    free(encrypted);
    free(decrypted);
    return 0;
}