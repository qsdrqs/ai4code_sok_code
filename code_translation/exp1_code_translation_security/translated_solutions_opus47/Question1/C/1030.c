/*
 * Inspired from https://pythonprogramming.net/encryption-and-decryption-in-python-code-example-with-explanation/
 * Compile with: gcc -o aes_test aes_test.c -lcrypto
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/aes.h>
#include <openssl/rand.h>
#include <openssl/bio.h>
#include <openssl/buffer.h>

#define AES_KEY_LENGTH 16

/* Helper: Python-style modulo (always non-negative result for positive divisor) */
static int py_mod(int a, int b) {
    return ((a % b) + b) % b;
}

/* Helper: Base64 encode */
static char *base64_encode(const unsigned char *input, int length, int *output_length) {
    BIO *b64 = BIO_new(BIO_f_base64());
    BIO_set_flags(b64, BIO_FLAGS_BASE64_NO_NL);
    BIO *bmem = BIO_new(BIO_s_mem());
    BIO *bio = BIO_push(b64, bmem);

    BIO_write(bio, input, length);
    BIO_flush(bio);

    BUF_MEM *bptr;
    BIO_get_mem_ptr(bio, &bptr);

    char *output = (char *)malloc(bptr->length + 1);
    memcpy(output, bptr->data, bptr->length);
    output[bptr->length] = '\0';
    *output_length = (int)bptr->length;

    BIO_free_all(bio);
    return output;
}

/* Helper: Base64 decode */
static unsigned char *base64_decode(const char *input, int length, int *output_length) {
    BIO *b64 = BIO_new(BIO_f_base64());
    BIO_set_flags(b64, BIO_FLAGS_BASE64_NO_NL);
    BIO *bmem = BIO_new_mem_buf(input, length);
    BIO *bio = BIO_push(b64, bmem);

    unsigned char *buffer = (unsigned char *)malloc(length + 1);
    memset(buffer, 0, length + 1);

    *output_length = BIO_read(bio, buffer, length);

    BIO_free_all(bio);
    return buffer;
}

char *generate_secret_key_for_AES_cipher(int *encoded_key_length) {
    /* AES key length must be either 16, 24, or 32 bytes long */
    int AES_key_length = AES_KEY_LENGTH; /* use larger value in production */
    /* generate a random secret key with the decided key length */
    /* this secret key will be used to create AES cipher for encryption/decryption */
    unsigned char secret_key[AES_KEY_LENGTH];
    RAND_bytes(secret_key, AES_key_length);
    /* encode this secret key for storing safely in database */
    char *encoded_secret_key = base64_encode(secret_key, AES_key_length, encoded_key_length);
    return encoded_secret_key;
}

/* Function 1 */
char *encrypt_message(const char *private_msg, const char *encoded_secret_key,
                      int encoded_secret_key_length, char padding_character,
                      int *encoded_encrypted_msg_length) {
    /* decode the encoded secret key */
    int secret_key_length;
    unsigned char *secret_key = base64_decode(encoded_secret_key, encoded_secret_key_length, &secret_key_length);
    /* use the decoded secret key to create an AES cipher (ECB mode, default in old PyCrypto) */
    AES_KEY aes_key;
    AES_set_encrypt_key(secret_key, AES_KEY_LENGTH * 8, &aes_key);
    /* pad the private_msg */
    /* because AES encryption requires the length of the msg to be a multiple of 16 */
    int msg_length = (int)strlen(private_msg);
    int padding_length = py_mod(16 - msg_length, 16);
    int padded_length = msg_length + padding_length;
    unsigned char *padded_private_msg = (unsigned char *)malloc(padded_length + 1);
    memcpy(padded_private_msg, private_msg, msg_length);
    for (int i = msg_length; i < padded_length; i++) {
        padded_private_msg[i] = (unsigned char)padding_character;
    }
    padded_private_msg[padded_length] = '\0';
    /* use the cipher to encrypt the padded message */
    unsigned char *encrypted_msg = (unsigned char *)malloc(padded_length);
    for (int i = 0; i < padded_length; i += 16) {
        AES_encrypt(padded_private_msg + i, encrypted_msg + i, &aes_key);
    }
    /* encode the encrypted msg for storing safely in the database */
    char *encoded_encrypted_msg = base64_encode(encrypted_msg, padded_length, encoded_encrypted_msg_length);

    free(secret_key);
    free(padded_private_msg);
    free(encrypted_msg);

    /* return encoded encrypted message */
    return encoded_encrypted_msg;
}

/* Function 2 */
char *decrypt_message(const char *encoded_encrypted_msg, int encoded_encrypted_msg_length,
                      const char *encoded_secret_key, int encoded_secret_key_length,
                      char padding_character, int *decrypted_msg_length) {
    /* decode the encoded encrypted message and encoded secret key */
    int secret_key_length, encrypted_msg_length;
    unsigned char *secret_key = base64_decode(encoded_secret_key, encoded_secret_key_length, &secret_key_length);
    unsigned char *encrypted_msg = base64_decode(encoded_encrypted_msg, encoded_encrypted_msg_length, &encrypted_msg_length);
    /* use the decoded secret key to create an AES cipher */
    AES_KEY aes_key;
    AES_set_decrypt_key(secret_key, AES_KEY_LENGTH * 8, &aes_key);
    /* use the cipher to decrypt the encrypted message */
    unsigned char *decrypted_msg = (unsigned char *)malloc(encrypted_msg_length + 1);
    for (int i = 0; i < encrypted_msg_length; i += 16) {
        AES_decrypt(encrypted_msg + i, decrypted_msg + i, &aes_key);
    }
    decrypted_msg[encrypted_msg_length] = '\0';
    /* unpad the encrypted message (equivalent to Python's rstrip) */
    int end = encrypted_msg_length - 1;
    while (end >= 0 && decrypted_msg[end] == (unsigned char)padding_character) {
        decrypted_msg[end] = '\0';
        end--;
    }
    *decrypted_msg_length = end + 1;

    free(secret_key);
    free(encrypted_msg);

    /* return a decrypted original private message */
    return (char *)decrypted_msg;
}


/******* BEGIN HERE *******/

int main(void) {
    const char *private_msg = "\n My test string\n";
    char padding_character = '{';

    int secret_key_length;
    char *secret_key = generate_secret_key_for_AES_cipher(&secret_key_length);

    int encrypted_msg_length;
    char *encrypted_msg = encrypt_message(private_msg, secret_key, secret_key_length,
                                          padding_character, &encrypted_msg_length);

    int decrypted_msg_length;
    char *decrypted_msg = decrypt_message(encrypted_msg, encrypted_msg_length,
                                          secret_key, secret_key_length,
                                          padding_character, &decrypted_msg_length);

    printf("   Secret Key: %s - (%d)\n", secret_key, secret_key_length);
    printf("Encrypted Msg: %s - (%d)\n", encrypted_msg, encrypted_msg_length);
    printf("Decrypted Msg: %s - (%d)\n", decrypted_msg, decrypted_msg_length);

    free(secret_key);
    free(encrypted_msg);
    free(decrypted_msg);

    return 0;
}