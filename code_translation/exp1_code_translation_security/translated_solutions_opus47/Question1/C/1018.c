// Compile with: gcc -o fernet fernet.c -lssl -lcrypto

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

// ---- Base64 URL-safe helpers ----
static char* base64_url_encode(const unsigned char* input, int length) {
    BIO *bmem, *b64;
    BUF_MEM *bptr;
    b64 = BIO_new(BIO_f_base64());
    BIO_set_flags(b64, BIO_FLAGS_BASE64_NO_NL);
    bmem = BIO_new(BIO_s_mem());
    b64 = BIO_push(b64, bmem);
    BIO_write(b64, input, length);
    BIO_flush(b64);
    BIO_get_mem_ptr(b64, &bptr);

    char *buff = (char *)malloc(bptr->length + 1);
    memcpy(buff, bptr->data, bptr->length);
    buff[bptr->length] = 0;
    BIO_free_all(b64);

    for (int i = 0; buff[i] != 0; i++) {
        if (buff[i] == '+') buff[i] = '-';
        else if (buff[i] == '/') buff[i] = '_';
    }
    return buff;
}

static unsigned char* base64_url_decode(const char* input, int* out_length) {
    int input_len = strlen(input);
    char* modified = (char*)malloc(input_len + 5);
    if (!modified) return NULL;
    strcpy(modified, input);

    for (int i = 0; modified[i] != 0; i++) {
        if (modified[i] == '-') modified[i] = '+';
        else if (modified[i] == '_') modified[i] = '/';
    }

    int mod = strlen(modified) % 4;
    if (mod > 0)
        for (int i = 0; i < 4 - mod; i++) strcat(modified, "=");

    int modified_len = strlen(modified);
    unsigned char* buffer = (unsigned char*)malloc(modified_len);
    if (!buffer) { free(modified); return NULL; }

    BIO *bmem, *b64;
    b64 = BIO_new(BIO_f_base64());
    BIO_set_flags(b64, BIO_FLAGS_BASE64_NO_NL);
    bmem = BIO_new_mem_buf(modified, -1);
    bmem = BIO_push(b64, bmem);

    *out_length = BIO_read(bmem, buffer, modified_len);
    BIO_free_all(bmem);
    free(modified);
    return buffer;
}

// ---- Fernet functions ----
char* encrypt(const char* input, const char* key) {
    int key_len;
    unsigned char* decoded_key = base64_url_decode(key, &key_len);
    if (!decoded_key || key_len != 32) {
        if (decoded_key) free(decoded_key);
        return NULL;
    }
    unsigned char* signing_key    = decoded_key;
    unsigned char* encryption_key = decoded_key + 16;

    unsigned char iv[16];
    if (RAND_bytes(iv, 16) != 1) { free(decoded_key); return NULL; }

    uint64_t timestamp = (uint64_t)time(NULL);

    int input_len = strlen(input);
    unsigned char* ciphertext = (unsigned char*)malloc(input_len + 16);
    int ciphertext_len, len;

    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, encryption_key, iv);
    EVP_EncryptUpdate(ctx, ciphertext, &len, (const unsigned char*)input, input_len);
    ciphertext_len = len;
    EVP_EncryptFinal_ex(ctx, ciphertext + len, &len);
    ciphertext_len += len;
    EVP_CIPHER_CTX_free(ctx);

    // Token: version(1) + timestamp(8) + IV(16) + ciphertext + HMAC(32)
    int token_len = 1 + 8 + 16 + ciphertext_len + 32;
    unsigned char* token = (unsigned char*)malloc(token_len);

    token[0] = 0x80;
    for (int i = 0; i < 8; i++)
        token[1 + i] = (timestamp >> (56 - 8*i)) & 0xFF;
    memcpy(token + 9, iv, 16);
    memcpy(token + 25, ciphertext, ciphertext_len);

    unsigned int hmac_len = 32;
    HMAC(EVP_sha256(), signing_key, 16, token,
         25 + ciphertext_len, token + 25 + ciphertext_len, &hmac_len);

    char* encoded = base64_url_encode(token, token_len);

    free(ciphertext); free(token); free(decoded_key);
    return encoded;
}

char* decrypt(const char* input, const char* key) {
    int key_len;
    unsigned char* decoded_key = base64_url_decode(key, &key_len);
    if (!decoded_key || key_len != 32) {
        if (decoded_key) free(decoded_key);
        return NULL;
    }
    unsigned char* signing_key    = decoded_key;
    unsigned char* encryption_key = decoded_key + 16;

    int token_len;
    unsigned char* token = base64_url_decode(input, &token_len);
    if (!token || token_len < 73 || token[0] != 0x80) {
        if (token) free(token);
        free(decoded_key);
        return NULL;
    }

    unsigned char computed_hmac[32];
    unsigned int hmac_len = 32;
    HMAC(EVP_sha256(), signing_key, 16, token, token_len - 32, computed_hmac, &hmac_len);
    if (CRYPTO_memcmp(computed_hmac, token + token_len - 32, 32) != 0) {
        free(token); free(decoded_key);
        return NULL;
    }

    unsigned char* iv = token + 9;
    unsigned char* ciphertext = token + 25;
    int ciphertext_len = token_len - 25 - 32;

    unsigned char* plaintext = (unsigned char*)malloc(ciphertext_len + 1);
    int plaintext_len, len;

    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, encryption_key, iv);
    EVP_DecryptUpdate(ctx, plaintext, &len, ciphertext, ciphertext_len);
    plaintext_len = len;
    if (EVP_DecryptFinal_ex(ctx, plaintext + len, &len) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(plaintext); free(token); free(decoded_key);
        return NULL;
    }
    plaintext_len += len;
    EVP_CIPHER_CTX_free(ctx);
    plaintext[plaintext_len] = 0;

    free(token); free(decoded_key);
    return (char*)plaintext;
}

// ---- Example usage ----
int main(void) {
    const char* key = "ZmDfcTF7_60GrrY167zsiPd67pEvs0aGOv2oasOM1Pg=";
    const char* message = "Hello, World!";

    char* encrypted = encrypt(message, key);
    if (encrypted) {
        printf("Encrypted: %s\n", encrypted);
        char* decrypted = decrypt(encrypted, key);
        if (decrypted) {
            printf("Decrypted: %s\n", decrypted);
            free(decrypted);
        }
        free(encrypted);
    }
    return 0;
}