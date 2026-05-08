// Compile with: gcc -o fernet fernet.c -lcrypto

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

// Base64url encoding (URL-safe base64)
char *base64url_encode(const unsigned char *input, size_t length) {
    BIO *bio, *b64;
    BUF_MEM *buffer_ptr;

    b64 = BIO_new(BIO_f_base64());
    bio = BIO_new(BIO_s_mem());
    bio = BIO_push(b64, bio);
    BIO_set_flags(bio, BIO_FLAGS_BASE64_NO_NL);

    BIO_write(bio, input, length);
    BIO_flush(bio);
    BIO_get_mem_ptr(bio, &buffer_ptr);

    char *output = malloc(buffer_ptr->length + 1);
    memcpy(output, buffer_ptr->data, buffer_ptr->length);
    output[buffer_ptr->length] = '\0';

    BIO_free_all(bio);

    // Convert to URL-safe alphabet
    for (size_t i = 0; output[i] != '\0'; i++) {
        if (output[i] == '+') output[i] = '-';
        else if (output[i] == '/') output[i] = '_';
    }
    return output;
}

// Base64url decoding
unsigned char *base64url_decode(const char *input, size_t *output_length) {
    size_t input_length = strlen(input);
    size_t padding = (4 - (input_length % 4)) % 4;
    size_t total_length = input_length + padding;

    char *modified = malloc(total_length + 1);
    memcpy(modified, input, input_length);

    for (size_t i = 0; i < input_length; i++) {
        if (modified[i] == '-') modified[i] = '+';
        else if (modified[i] == '_') modified[i] = '/';
    }
    for (size_t i = 0; i < padding; i++) {
        modified[input_length + i] = '=';
    }
    modified[total_length] = '\0';

    size_t decode_length = total_length * 3 / 4;
    unsigned char *output = malloc(decode_length);

    BIO *bio = BIO_new_mem_buf(modified, total_length);
    BIO *b64 = BIO_new(BIO_f_base64());
    bio = BIO_push(b64, bio);
    BIO_set_flags(bio, BIO_FLAGS_BASE64_NO_NL);

    int bytes_read = BIO_read(bio, output, decode_length);
    *output_length = (bytes_read > 0) ? (size_t)bytes_read : 0;

    BIO_free_all(bio);
    free(modified);
    return output;
}

// Generate a Fernet key (32 random bytes, base64url encoded)
char *generate_key() {
    unsigned char key[32];
    RAND_bytes(key, 32);
    return base64url_encode(key, 32);
}

char *encrypt(const unsigned char *input, size_t input_length, const char *key_str) {
    size_t key_length;
    unsigned char *key = base64url_decode(key_str, &key_length);
    if (key_length != 32) { free(key); return NULL; }

    unsigned char signing_key[16], encryption_key[16];
    memcpy(signing_key, key, 16);
    memcpy(encryption_key, key + 16, 16);
    free(key);

    unsigned char iv[16];
    RAND_bytes(iv, 16);

    uint64_t timestamp = (uint64_t)time(NULL);

    // AES-128-CBC with PKCS7 padding (default in EVP)
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, encryption_key, iv);

    unsigned char *ciphertext = malloc(input_length + 16);
    int len, ciphertext_len;

    EVP_EncryptUpdate(ctx, ciphertext, &len, input, input_length);
    ciphertext_len = len;
    EVP_EncryptFinal_ex(ctx, ciphertext + len, &len);
    ciphertext_len += len;
    EVP_CIPHER_CTX_free(ctx);

    // Build token: version(1) + timestamp(8) + iv(16) + ciphertext + hmac(32)
    size_t data_length = 1 + 8 + 16 + ciphertext_len;
    unsigned char *token = malloc(data_length + 32);

    token[0] = 0x80; // version
    for (int i = 0; i < 8; i++) {
        token[1 + i] = (timestamp >> ((7 - i) * 8)) & 0xFF;
    }
    memcpy(token + 9, iv, 16);
    memcpy(token + 25, ciphertext, ciphertext_len);

    unsigned int hmac_length;
    HMAC(EVP_sha256(), signing_key, 16, token, data_length,
         token + data_length, &hmac_length);

    char *result = base64url_encode(token, data_length + 32);

    free(ciphertext);
    free(token);
    return result;
}

unsigned char *decrypt(const char *input, const char *key_str, size_t *output_length) {
    size_t key_length;
    unsigned char *key = base64url_decode(key_str, &key_length);
    if (key_length != 32) { free(key); return NULL; }

    unsigned char signing_key[16], encryption_key[16];
    memcpy(signing_key, key, 16);
    memcpy(encryption_key, key + 16, 16);
    free(key);

    size_t token_length;
    unsigned char *token = base64url_decode(input, &token_length);

    if (token_length < 1 + 8 + 16 + 32 || token[0] != 0x80) {
        free(token);
        return NULL;
    }

    // Verify HMAC
    unsigned char expected_hmac[32];
    unsigned int hmac_length;
    HMAC(EVP_sha256(), signing_key, 16, token, token_length - 32,
         expected_hmac, &hmac_length);

    if (memcmp(expected_hmac, token + token_length - 32, 32) != 0) {
        free(token);
        return NULL;
    }

    unsigned char *iv = token + 9;
    unsigned char *ciphertext = token + 25;
    size_t ciphertext_length = token_length - 25 - 32;

    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, encryption_key, iv);

    unsigned char *plaintext = malloc(ciphertext_length + 1);
    int len, plaintext_len;

    EVP_DecryptUpdate(ctx, plaintext, &len, ciphertext, ciphertext_length);
    plaintext_len = len;
    EVP_DecryptFinal_ex(ctx, plaintext + len, &len);
    plaintext_len += len;
    plaintext[plaintext_len] = '\0';

    EVP_CIPHER_CTX_free(ctx);

    *output_length = plaintext_len;
    free(token);
    return plaintext;
}

int main() {
    char *key = generate_key();
    const char *input = "super secret";
    size_t input_length = strlen(input);

    char *encryptedInput = encrypt((const unsigned char *)input, input_length, key);

    size_t decrypted_length;
    unsigned char *decryptedInput = decrypt(encryptedInput, key, &decrypted_length);

    printf("b'%s'\n", input);
    printf("b'%.*s'\n", (int)decrypted_length, decryptedInput);

    free(key);
    free(encryptedInput);
    free(decryptedInput);
    return 0;
}