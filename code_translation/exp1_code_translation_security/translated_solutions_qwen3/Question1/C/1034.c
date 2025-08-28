#include <openssl/evp.h>
#include <openssl/bio.h>
#include <openssl/buffer.h>
#include <openssl/rand.h>
#include <openssl/err.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>

// Base64 encoding function
char *base64encode(const unsigned char *data, int len) {
    BIO *b64 = BIO_new(BIO_f_base64());
    BIO *bio = BIO_new(BIO_s_mem());
    bio = BIO_push(b64, bio);
    BIO_set_flags(bio, BIO_FLAGS_BASE64_NO_NL); // No newlines
    BIO_write(bio, data, len);
    BIO_flush(bio);
    BUF_MEM *bptr;
    BIO_get_mem_ptr(bio, &bptr);
    char *buff = (char *)malloc(bptr->length + 1);
    memcpy(buff, bptr->data, bptr->length);
    buff[bptr->length] = '\0';
    BIO_free_all(bio);
    return buff;
}

// Base64 decoding function
char *base64decode(const char *data, int len, int *out_len) {
    BIO *b64 = BIO_new(BIO_f_base64());
    BIO *bio = BIO_new(BIO_s_mem());
    bio = BIO_push(b64, bio);
    BIO_set_flags(bio, BIO_FLAGS_BASE64_NO_NL); // No newlines
    BIO_write(bio, data, len);
    BIO_flush(bio);
    char *bptr;
    int decoded_len = BIO_get_mem_data(bio, (void**)&bptr);
    char *buff = (char *)malloc(decoded_len);
    memcpy(buff, bptr, decoded_len);
    *out_len = decoded_len;
    BIO_free_all(bio);
    return buff;
}

// Encrypt a string using a password
char *encode_str(const char *plaintext, const char *key) {
    const unsigned char *salt = (const unsigned char *)"ThIsIsAsAlTfOrThEc RyPtOcOdElIb";
    int salt_len = strlen((char *)salt);
    int iterations = 1000;
    unsigned char key_iv[32]; // 16 bytes key + 16 bytes IV

    // Derive key and IV using PBKDF2
    if (PKCS5_PBKDF2_HMAC(key, strlen(key), salt, salt_len, iterations, EVP_sha1(), 32, key_iv) != 1) {
        fprintf(stderr, "Key derivation failed\n");
        exit(EXIT_FAILURE);
    }

    unsigned char *aes_key = key_iv;
    unsigned char *aes_iv = key_iv + 16;

    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    int len;
    int ciphertext_len = 0;
    int plaintext_len = strlen(plaintext);
    unsigned char *ciphertext = (unsigned char *)malloc(plaintext_len + AES_BLOCK_SIZE);

    // Initialize encryption
    EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, aes_key, aes_iv);

    // Encrypt the plaintext
    EVP_EncryptUpdate(ctx, ciphertext, &len, (const unsigned char *)plaintext, plaintext_len);
    ciphertext_len += len;

    // Finalize encryption
    EVP_EncryptFinal_ex(ctx, ciphertext + ciphertext_len, &len);
    ciphertext_len += len;

    EVP_CIPHER_CTX_free(ctx);

    // Base64 encode the result
    char *encoded = base64encode(ciphertext, ciphertext_len);
    free(ciphertext);
    return encoded;
}

// Decrypt a Base64-encoded string using a password
char *decode_str(const char *ciphertext_base64, const char *key) {
    const unsigned char *salt = (const unsigned char *)"ThIsIsAsAlTfOrThEc RyPtOcOdElIb";
    int salt_len = strlen((char *)salt);
    int iterations = 1000;
    unsigned char key_iv[32];

    // Derive key and IV using PBKDF2
    if (PKCS5_PBKDF2_HMAC(key, strlen(key), salt, salt_len, iterations, EVP_sha1(), 32, key_iv) != 1) {
        fprintf(stderr, "Key derivation failed\n");
        exit(EXIT_FAILURE);
    }

    unsigned char *aes_key = key_iv;
    unsigned char *aes_iv = key_iv + 16;

    int decoded_len;
    unsigned char *ciphertext = (unsigned char *)base64decode(ciphertext_base64, strlen(ciphertext_base64), &decoded_len);

    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    int len;
    int plaintext_len = 0;
    unsigned char *plaintext = (unsigned char *)malloc(decoded_len + AES_BLOCK_SIZE);

    // Initialize decryption
    EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, aes_key, aes_iv);

    // Decrypt the ciphertext
    EVP_DecryptUpdate(ctx, plaintext, &len, ciphertext, decoded_len);
    plaintext_len += len;

    // Finalize decryption
    EVP_DecryptFinal_ex(ctx, plaintext + plaintext_len, &len);
    plaintext_len += len;

    EVP_CIPHER_CTX_free(ctx);
    free(ciphertext);

    // Null-terminate the result
    plaintext[plaintext_len] = '\0';
    char *result = strdup((char *)plaintext);
    free(plaintext);
    return result;
}

// Main function to test the encryption and decryption
int main() {
    char *encoded = encode_str("I am okay", "wow");
    printf("Decrypted: %s\n", decode_str(encoded, "wow"));

    free(encoded);
    return 0;
}