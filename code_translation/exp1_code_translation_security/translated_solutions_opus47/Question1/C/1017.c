/*
 * C translation of the Python AES-EAX encryption/decryption functions.
 *
 * Dependency: libtomcrypt (https://github.com/libtom/libtomcrypt)
 * Compile with: gcc -o prog prog.c -ltomcrypt
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <tomcrypt.h>

#define NONCE_SIZE 16
#define TAG_SIZE   16

/* Structure equivalent to Python's tuple (nonce, tag, ciphertext) */
typedef struct {
    unsigned char  nonce[NONCE_SIZE];
    unsigned char  tag[TAG_SIZE];
    unsigned long  tag_len;
    unsigned char *ciphertext;
    unsigned long  ciphertext_len;
} EncryptedData;

/* Function in C where one encrypts a given string using a given symmetric key. */
EncryptedData* encrypt(const unsigned char *key, unsigned long key_len,
                       const unsigned char *plaintext, unsigned long plaintext_len) {
    int err;
    int cipher_idx;

    EncryptedData *result = (EncryptedData*)malloc(sizeof(EncryptedData));
    if (!result) return NULL;

    result->ciphertext = (unsigned char*)malloc(plaintext_len);
    if (!result->ciphertext) {
        free(result);
        return NULL;
    }
    result->ciphertext_len = plaintext_len;
    result->tag_len        = TAG_SIZE;

    /* Register AES cipher and a system PRNG for nonce generation */
    register_cipher(&aes_desc);
    register_prng(&sprng_desc);
    cipher_idx = find_cipher("aes");

    /* Generate random nonce (equivalent to PyCryptodome's auto-generated nonce) */
    if (sprng_read(result->nonce, NONCE_SIZE, NULL) != NONCE_SIZE) {
        free(result->ciphertext);
        free(result);
        return NULL;
    }

    /* Equivalent of cipher.encrypt_and_digest(plaintext) */
    err = eax_encrypt_authenticate_memory(cipher_idx, key, key_len,
                                          result->nonce, NONCE_SIZE,
                                          NULL, 0,            /* no associated data */
                                          plaintext, plaintext_len,
                                          result->ciphertext,
                                          result->tag, &result->tag_len);
    if (err != CRYPT_OK) {
        free(result->ciphertext);
        free(result);
        return NULL;
    }

    return result;  /* returns (nonce, tag, ciphertext) */
}

/* Function in C where one decrypts a given string using a given symmetric key. */
unsigned char* decrypt(const unsigned char *key, unsigned long key_len,
                       const EncryptedData *ciphertext, unsigned long *out_len) {
    int err;
    int cipher_idx;
    int stat;

    /* Unpacking (nonce, tag, ciphertext) is done via struct fields */
    unsigned char *data = (unsigned char*)malloc(ciphertext->ciphertext_len);
    if (!data) return NULL;

    register_cipher(&aes_desc);
    cipher_idx = find_cipher("aes");

    /* Equivalent of cipher.decrypt_and_verify(ciphertext, tag) */
    err = eax_decrypt_verify_memory(cipher_idx, key, key_len,
                                    ciphertext->nonce, NONCE_SIZE,
                                    NULL, 0,             /* no associated data */
                                    ciphertext->ciphertext, ciphertext->ciphertext_len,
                                    data,
                                    ciphertext->tag, ciphertext->tag_len,
                                    &stat);

    /* stat != 1 indicates tag verification failure (like a MAC mismatch exception) */
    if (err != CRYPT_OK || stat != 1) {
        free(data);
        return NULL;
    }

    *out_len = ciphertext->ciphertext_len;
    return data;
}

/* Helper to free EncryptedData returned by encrypt() */
void free_encrypted_data(EncryptedData *data) {
    if (data) {
        if (data->ciphertext) free(data->ciphertext);
        free(data);
    }
}