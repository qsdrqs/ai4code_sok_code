#include <openssl/rand.h>
#include <openssl/evp.h>
#include <openssl/err.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// Simple error handler
void handleErrors() {
    ERR_print_errors_fp(stderr);
    fprintf(stderr, "Error in EVP operation\n");
    exit(EXIT_FAILURE);
}

// Encrypts the input using AES-128-CBC with PKCS#7 padding
// Returns a dynamically allocated buffer containing IV + ciphertext
unsigned char* encrypt(const unsigned char *input, int input_len, const unsigned char *key, int *enc_len) {
    unsigned char iv[16];
    if (!RAND_bytes(iv, 16)) {
        handleErrors();
    }

    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    if (!ctx) {
        handleErrors();
    }

    if (1 != EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, key, iv)) {
        handleErrors();
    }
    EVP_CIPHER_CTX_set_padding(ctx, 1); // Enable PKCS#7 padding

    // Allocate buffer for encrypted data
    int encrypted_len_max = input_len + 16; // Max padding
    unsigned char *encrypted_data = malloc(encrypted_len_max);
    if (!encrypted_data) {
        EVP_CIPHER_CTX_free(ctx);
        handleErrors();
    }

    int encrypted_len = 0;
    int len;

    if (1 != EVP_EncryptUpdate(ctx, encrypted_data, &len, input, input_len)) {
        free(encrypted_data);
        EVP_CIPHER_CTX_free(ctx);
        handleErrors();
    }
    encrypted_len += len;

    if (1 != EVP_EncryptFinal_ex(ctx, encrypted_data + encrypted_len, &len)) {
        free(encrypted_data);
        EVP_CIPHER_CTX_free(ctx);
        handleErrors();
    }
    encrypted_len += len;

    // Allocate result buffer: IV (16) + encrypted data
    unsigned char *result = malloc(16 + encrypted_len);
    if (!result) {
        free(encrypted_data);
        EVP_CIPHER_CTX_free(ctx);
        handleErrors();
    }

    memcpy(result, iv, 16);
    memcpy(result + 16, encrypted_data, encrypted_len);

    *enc_len = 16 + encrypted_len;

    free(encrypted_data);
    EVP_CIPHER_CTX_free(ctx);
    return result;
}

// Decrypts the input using AES-128-CBC with PKCS#7 padding
// Assumes the input starts with a 16-byte IV
unsigned char* decrypt(const unsigned char *ciphertext, int ciphertext_len, const unsigned char *key, int *dec_len) {
    if (ciphertext_len <= 16) {
        handleErrors();
    }

    const unsigned char *iv = ciphertext;
    const unsigned char *encrypted_data = ciphertext + 16;
    int encrypted_len = ciphertext_len - 16;

    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    if (!ctx) {
        handleErrors();
    }

    if (1 != EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, key, iv)) {
        EVP_CIPHER_CTX_free(ctx);
        handleErrors();
    }
    EVP_CIPHER_CTX_set_padding(ctx, 1); // Enable PKCS#7 padding

    // Allocate buffer for decrypted data
    unsigned char *decrypted = malloc(encrypted_len);
    if (!decrypted) {
        EVP_CIPHER_CTX_free(ctx);
        handleErrors();
    }

    int decrypted_len = 0;
    int len;

    if (1 != EVP_DecryptUpdate(ctx, decrypted, &len, encrypted_data, encrypted_len)) {
        free(decrypted);
        EVP_CIPHER_CTX_free(ctx);
        handleErrors();
    }
    decrypted_len += len;

    if (1 != EVP_DecryptFinal_ex(ctx, decrypted + decrypted_len, &len)) {
        free(decrypted);
        EVP_CIPHER_CTX_free(ctx);
        handleErrors();
    }
    decrypted_len += len;

    *dec_len = decrypted_len;

    EVP_CIPHER_CTX_free(ctx);
    return decrypted;
}

int main() {
    // Generate a 32-byte key (matches Fernet's key size)
    unsigned char key[32];
    if (!RAND_bytes(key, sizeof(key))) {
        handleErrors();
    }

    const char *input = "super secret";
    int input_len = strlen(input);
    int enc_len;
    unsigned char *encrypted = encrypt((const unsigned char *)input, input_len, key, &enc_len);
    if (!encrypted) {
        return -1;
    }

    int dec_len;
    unsigned char *decrypted = decrypt(encrypted, enc_len, key, &dec_len);
    if (!decrypted) {
        free(encrypted);
        return -1;
    }

    printf("Original: %s\n", input);
    printf("Decrypted: %.*s\n", dec_len, decrypted);

    free(encrypted);
    free(decrypted);
    return 0;
}