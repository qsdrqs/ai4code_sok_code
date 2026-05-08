/*
 * Compile with: gcc -o program program.c -lssl -lcrypto
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/evp.h>
#include <openssl/rand.h>
#include <openssl/bio.h>
#include <openssl/buffer.h>

#define AES_KEY_SIZE    32   /* AES-256                                */
#define GCM_TAG_SIZE    16   /* GCM authentication tag                 */
#define GCM_NONCE_SIZE  16   /* pycryptodome default GCM nonce size    */
#define SALT_SIZE       16   /* AES.block_size in cryptocode           */

/* --- Base64 helpers --------------------------------------------------- */

static char *base64_encode(const unsigned char *data, size_t length) {
    BIO *b64 = BIO_new(BIO_f_base64());
    BIO *bio = BIO_new(BIO_s_mem());
    bio = BIO_push(b64, bio);
    BIO_set_flags(bio, BIO_FLAGS_BASE64_NO_NL);

    BIO_write(bio, data, (int)length);
    BIO_flush(bio);

    BUF_MEM *buffer_ptr;
    BIO_get_mem_ptr(bio, &buffer_ptr);

    char *result = (char *)malloc(buffer_ptr->length + 1);
    memcpy(result, buffer_ptr->data, buffer_ptr->length);
    result[buffer_ptr->length] = '\0';

    BIO_free_all(bio);
    return result;
}

static unsigned char *base64_decode(const char *data, size_t *out_length) {
    size_t input_len = strlen(data);
    unsigned char *buffer = (unsigned char *)malloc(input_len + 1);

    BIO *bio = BIO_new_mem_buf(data, -1);
    BIO *b64 = BIO_new(BIO_f_base64());
    bio = BIO_push(b64, bio);
    BIO_set_flags(bio, BIO_FLAGS_BASE64_NO_NL);

    int decoded_len = BIO_read(bio, buffer, (int)input_len);
    *out_length = (decoded_len > 0) ? (size_t)decoded_len : 0;

    BIO_free_all(bio);
    return buffer;
}

/* --- Encrypt / Decrypt ------------------------------------------------- */

char *encode_str(const char *plain_text, const char *key) {
    unsigned char salt[SALT_SIZE];
    unsigned char nonce[GCM_NONCE_SIZE];
    unsigned char private_key[AES_KEY_SIZE];
    unsigned char tag[GCM_TAG_SIZE];

    RAND_bytes(salt, SALT_SIZE);
    RAND_bytes(nonce, GCM_NONCE_SIZE);

    /* Scrypt KDF: n=2^14, r=8, p=1, dklen=32 (same as cryptocode) */
    if (EVP_PBE_scrypt(key, strlen(key), salt, SALT_SIZE,
                       1 << 14, 8, 1, 32 * 1024 * 1024,
                       private_key, AES_KEY_SIZE) != 1) {
        fprintf(stderr, "Scrypt KDF failed\n");
        return NULL;
    }

    size_t plain_len = strlen(plain_text);
    unsigned char *cipher_text = (unsigned char *)malloc(plain_len + 16);
    int len, ciphertext_len;

    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_EncryptInit_ex(ctx, EVP_aes_256_gcm(), NULL, NULL, NULL);
    EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_SET_IVLEN, GCM_NONCE_SIZE, NULL);
    EVP_EncryptInit_ex(ctx, NULL, NULL, private_key, nonce);
    EVP_EncryptUpdate(ctx, cipher_text, &len,
                      (const unsigned char *)plain_text, (int)plain_len);
    ciphertext_len = len;
    EVP_EncryptFinal_ex(ctx, cipher_text + len, &len);
    ciphertext_len += len;
    EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_GET_TAG, GCM_TAG_SIZE, tag);
    EVP_CIPHER_CTX_free(ctx);

    char *b64_cipher = base64_encode(cipher_text, ciphertext_len);
    char *b64_tag    = base64_encode(tag,         GCM_TAG_SIZE);
    char *b64_salt   = base64_encode(salt,        SALT_SIZE);
    char *b64_nonce  = base64_encode(nonce,       GCM_NONCE_SIZE);

    /* Format: cipher_text*tag*salt*nonce (same as cryptocode) */
    size_t total_len = strlen(b64_cipher) + strlen(b64_tag) +
                       strlen(b64_salt)   + strlen(b64_nonce) + 4;
    char *result = (char *)malloc(total_len);
    snprintf(result, total_len, "%s*%s*%s*%s",
             b64_cipher, b64_tag, b64_salt, b64_nonce);

    free(cipher_text);
    free(b64_cipher);
    free(b64_tag);
    free(b64_salt);
    free(b64_nonce);
    return result;
}

char *decode_str(const char *str_encoded, const char *key) {
    char *copy       = strdup(str_encoded);
    char *cipher_b64 = strtok(copy, "*");
    char *tag_b64    = strtok(NULL, "*");
    char *salt_b64   = strtok(NULL, "*");
    char *nonce_b64  = strtok(NULL, "*");

    if (!cipher_b64 || !tag_b64 || !salt_b64 || !nonce_b64) {
        free(copy);
        return NULL;
    }

    size_t cipher_len, tag_len, salt_len, nonce_len;
    unsigned char *cipher_text = base64_decode(cipher_b64, &cipher_len);
    unsigned char *tag         = base64_decode(tag_b64,    &tag_len);
    unsigned char *salt        = base64_decode(salt_b64,   &salt_len);
    unsigned char *nonce       = base64_decode(nonce_b64,  &nonce_len);

    unsigned char private_key[AES_KEY_SIZE];
    EVP_PBE_scrypt(key, strlen(key), salt, salt_len,
                   1 << 14, 8, 1, 32 * 1024 * 1024,
                   private_key, AES_KEY_SIZE);

    unsigned char *plaintext = (unsigned char *)malloc(cipher_len + 1);
    int len, plaintext_len;

    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_DecryptInit_ex(ctx, EVP_aes_256_gcm(), NULL, NULL, NULL);
    EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_SET_IVLEN, (int)nonce_len, NULL);
    EVP_DecryptInit_ex(ctx, NULL, NULL, private_key, nonce);
    EVP_DecryptUpdate(ctx, plaintext, &len, cipher_text, (int)cipher_len);
    plaintext_len = len;
    EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_SET_TAG, GCM_TAG_SIZE, tag);
    int ret = EVP_DecryptFinal_ex(ctx, plaintext + len, &len);
    EVP_CIPHER_CTX_free(ctx);

    char *result = NULL;
    if (ret > 0) {                  /* tag verified */
        plaintext_len += len;
        plaintext[plaintext_len] = '\0';
        result = (char *)plaintext;
    } else {
        free(plaintext);            /* decryption failed (like returning False) */
    }

    free(copy);
    free(cipher_text);
    free(tag);
    free(salt);
    free(nonce);
    return result;
}

/* --- Main ------------------------------------------------------------- */

int main(void) {
    char *str_encoded1 = encode_str("I am okay", "wow");
    char *decoded      = decode_str(str_encoded1, "wow");

    if (decoded) printf("%s\n", decoded);

    free(str_encoded1);
    free(decoded);
    return 0;
}