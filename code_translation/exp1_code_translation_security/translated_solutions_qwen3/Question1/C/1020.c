#include <openssl/aes.h>
#include <openssl/rand.h>
#include <openssl/evp.h>
#include <string.h>
#include <stdlib.h>

/**
 * Encrypts a plaintext buffer using AES in CBC mode and returns the base64-encoded result.
 *
 * @param key           The encryption key (must be 16, 24, or 32 bytes for AES-128, 192, or 256).
 * @param key_size      The size of the key in bits (128, 192, or 256).
 * @param plaintext     The input data to encrypt.
 * @param plaintext_len The length of the plaintext in bytes.
 * @param encrypted_len Output parameter for the length of the returned base64 string.
 * @return              A base64-encoded string containing the IV and encrypted data.
 */
char* encrypt(const unsigned char* key, int key_size, const unsigned char* plaintext, int plaintext_len, int *encrypted_len) {
    // Generate a random 16-byte IV
    unsigned char iv[16];
    if (!RAND_bytes(iv, 16)) {
        return NULL; // Failed to generate IV
    }

    // Calculate padding length (PKCS#7)
    int padding_len = 16 - (plaintext_len % 16);
    if (padding_len == 0) {
        padding_len = 16; // If plaintext is a multiple of block size, add full block of padding
    }

    int padded_len = plaintext_len + padding_len;
    unsigned char *padded_txt = malloc(padded_len);
    if (!padded_txt) {
        return NULL; // Memory allocation failed
    }

    memcpy(padded_txt, plaintext, plaintext_len);
    memset(padded_txt + plaintext_len, padding_len, padding_len);

    // Initialize AES encryption key
    AES_KEY aes_key;
    if (AES_set_encrypt_key(key, key_size, &aes_key) < 0) {
        free(padded_txt);
        return NULL; // Failed to set encryption key
    }

    // Encrypt the padded plaintext
    unsigned char *cipher_txt = malloc(padded_len);
    if (!cipher_txt) {
        free(padded_txt);
        return NULL;
    }

    AES_cbc_encrypt(padded_txt, cipher_txt, padded_len, &aes_key, iv, AES_ENCRYPT);
    free(padded_txt);

    // Prepend IV to the ciphertext
    unsigned char *combined = malloc(16 + padded_len);
    if (!combined) {
        free(cipher_txt);
        return NULL;
    }

    memcpy(combined, iv, 16);
    memcpy(combined + 16, cipher_txt, padded_len);
    free(cipher_txt);

    // Base64 encode the combined IV + ciphertext
    int b64_len = ((16 + padded_len) + 2) / 3 * 4 + 1;
    char *b64_str = malloc(b64_len);
    if (!b64_str) {
        free(combined);
        return NULL;
    }

    EVP_EncodeBlock(b64_str, combined, 16 + padded_len);
    free(combined);

    *encrypted_len = strlen(b64_str);
    return b64_str;
}

/**
 * Decrypts a base64-encoded string using AES in CBC mode and returns the original plaintext.
 *
 * @param key           The decryption key (must be 16, 24, or 32 bytes for AES-128, 192, or 256).
 * @param key_size      The size of the key in bits (128, 192, or 256).
 * @param ciphertext_b64 The base64-encoded string containing the IV and encrypted data.
 * @param decrypted_len Output parameter for the length of the returned plaintext.
 * @return              The decrypted plaintext buffer.
 */
unsigned char* decrypt(const unsigned char* key, int key_size, const char* ciphertext_b64, int *decrypted_len) {
    // Decode base64 string
    int ciphertext_len = strlen(ciphertext_b64);
    int decoded_len = (ciphertext_len + 3) / 4 * 3; // Upper bound for decoded length
    unsigned char *ciphertext = malloc(decoded_len);
    if (!ciphertext) {
        return NULL;
    }

    decoded_len = EVP_DecodeBlock(ciphertext, ciphertext_b64, ciphertext_len);
    if (decoded_len < 16) {
        free(ciphertext);
        return NULL; // Invalid ciphertext (too short to contain IV)
    }

    // Extract IV and ciphertext
    unsigned char *iv = ciphertext;
    unsigned char *cipher_txt = ciphertext + 16;
    int cipher_txt_len = decoded_len - 16;

    if (cipher_txt_len < 1 || cipher_txt_len % 16 != 0) {
        free(ciphertext);
        return NULL; // Invalid ciphertext length
    }

    // Initialize AES decryption key
    AES_KEY aes_key;
    if (AES_set_decrypt_key(key, key_size, &aes_key) < 0) {
        free(ciphertext);
        return NULL;
    }

    // Decrypt the ciphertext
    unsigned char *padded_plaintext = malloc(cipher_txt_len);
    if (!padded_plaintext) {
        free(ciphertext);
        return NULL;
    }

    AES_cbc_encrypt(cipher_txt, padded_plaintext, cipher_txt_len, &aes_key, iv, AES_DECRYPT);
    free(ciphertext);

    // Validate and remove PKCS#7 padding
    int padding_len = padded_plaintext[cipher_txt_len - 1];
    if (padding_len < 1 || padding_len > 16) {
        free(padded_plaintext);
        return NULL; // Invalid padding
    }

    for (int i = 0; i < padding_len; i++) {
        if (padded_plaintext[cipher_txt_len - padding_len + i] != padding_len) {
            free(padded_plaintext);
            return NULL; // Invalid padding bytes
        }
    }

    int plaintext_len = cipher_txt_len - padding_len;
    unsigned char *plaintext = malloc(plaintext_len);
    if (!plaintext) {
        free(padded_plaintext);
        return NULL;
    }

    memcpy(plaintext, padded_plaintext, plaintext_len);
    free(padded_plaintext);

    *decrypted_len = plaintext_len;
    return plaintext;
}