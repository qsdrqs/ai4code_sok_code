/*
 * Cryptographic functions translated from Python to C.
 * 
 * The original Python code uses PyCryptodome's AES-EAX mode.
 * Since OpenSSL doesn't natively support EAX mode, this C translation uses
 * AES-128-GCM, which provides similar authenticated encryption.
 * 
 * Compile with: gcc file.c -o file -lcrypto
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/evp.h>
#include <openssl/rand.h>
#include <openssl/err.h>

#define KEY_SIZE   16  /* AES-128 key size (128 bits) */
#define NONCE_SIZE 16  /* Nonce size in bytes */
#define TAG_SIZE   16  /* Authentication tag size */

/*
 * Function to encrypt a byte array using a symmetric key.
 *
 * @param key         Input: Symmetric key (KEY_SIZE bytes).
 * @param data        Input: Plaintext to encrypt.
 * @param data_len    Input: Length of plaintext.
 * @param ciphertext  Output: Ciphertext buffer (must be at least data_len bytes).
 * @param nonce       Output: Generated nonce (NONCE_SIZE bytes).
 * @param tag         Output: Authentication tag (TAG_SIZE bytes).
 * @return            Length of the ciphertext on success, -1 on error.
 */
int encrypt(const unsigned char *key,
            const unsigned char *data, int data_len,
            unsigned char *ciphertext,
            unsigned char *nonce,
            unsigned char *tag)
{
    EVP_CIPHER_CTX *ctx = NULL;
    int len;
    int ciphertext_len;

    /* Generate a random nonce */
    if (RAND_bytes(nonce, NONCE_SIZE) != 1) {
        return -1;
    }

    /* Create a cipher context */
    if (!(ctx = EVP_CIPHER_CTX_new())) {
        return -1;
    }

    /* Initialize the encryption operation with AES-128-GCM */
    if (EVP_EncryptInit_ex(ctx, EVP_aes_128_gcm(), NULL, NULL, NULL) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        return -1;
    }

    /* Set the nonce length */
    if (EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_SET_IVLEN, NONCE_SIZE, NULL) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        return -1;
    }

    /* Set the key and nonce */
    if (EVP_EncryptInit_ex(ctx, NULL, NULL, key, nonce) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        return -1;
    }

    /* Encrypt the data */
    if (EVP_EncryptUpdate(ctx, ciphertext, &len, data, data_len) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        return -1;
    }
    ciphertext_len = len;

    /* Finalize the encryption */
    if (EVP_EncryptFinal_ex(ctx, ciphertext + len, &len) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        return -1;
    }
    ciphertext_len += len;

    /* Get the authentication tag */
    if (EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_GET_TAG, TAG_SIZE, tag) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        return -1;
    }

    /* Clean up and return the ciphertext length and nonce (via out params) */
    EVP_CIPHER_CTX_free(ctx);
    return ciphertext_len;
}

/*
 * Function to decrypt a byte array.
 *
 * @param key             Input: Symmetric key (KEY_SIZE bytes).
 * @param nonce           Input: Nonce used during encryption (NONCE_SIZE bytes).
 * @param ciphertext      Input: Ciphertext to decrypt.
 * @param ciphertext_len  Input: Length of ciphertext.
 * @param plaintext       Output: Decrypted data buffer.
 * @return                Length of plaintext on success, -1 on error.
 *
 * Note: This function does not verify the authentication tag,
 * matching the behavior of the original Python code.
 */
int decrypt(const unsigned char *key,
            const unsigned char *nonce,
            const unsigned char *ciphertext, int ciphertext_len,
            unsigned char *plaintext)
{
    EVP_CIPHER_CTX *ctx = NULL;
    int len;
    int plaintext_len;

    /* Create a cipher context */
    if (!(ctx = EVP_CIPHER_CTX_new())) {
        return -1;
    }

    /* Initialize the decryption operation with AES-128-GCM */
    if (EVP_DecryptInit_ex(ctx, EVP_aes_128_gcm(), NULL, NULL, NULL) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        return -1;
    }

    /* Set the nonce length */
    if (EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_SET_IVLEN, NONCE_SIZE, NULL) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        return -1;
    }

    /* Set the key and nonce */
    if (EVP_DecryptInit_ex(ctx, NULL, NULL, key, nonce) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        return -1;
    }

    /* Decrypt the data */
    if (EVP_DecryptUpdate(ctx, plaintext, &len, ciphertext, ciphertext_len) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        return -1;
    }
    plaintext_len = len;

    /* Clean up and return the decrypted data (via out param) */
    EVP_CIPHER_CTX_free(ctx);
    return plaintext_len;
}

/*
 * Function to generate a random symmetric key.
 *
 * @param key  Output: Buffer to hold the generated key (KEY_SIZE bytes).
 * @return     0 on success, -1 on error.
 */
int generate_key(unsigned char *key)
{
    /* Generate a random key */
    if (RAND_bytes(key, KEY_SIZE) != 1) {
        return -1;
    }
    /* */
    return 0;
}

/*
// Example:
int main(void) {
    unsigned char key[KEY_SIZE];
    unsigned char nonce[NONCE_SIZE];
    unsigned char tag[TAG_SIZE];
    unsigned char encrypted[256];
    unsigned char restored_data[256];
    const char *test_data = "Jim's test";
    int data_len = strlen(test_data);

    generate_key(key);
    int enc_len = encrypt(key, (const unsigned char *)test_data, data_len,
                          encrypted, nonce, tag);
    int dec_len = decrypt(key, nonce, encrypted, enc_len, restored_data);
    restored_data[dec_len] = '\0';

    printf("%s\n", restored_data);
    return 0;
}
*/