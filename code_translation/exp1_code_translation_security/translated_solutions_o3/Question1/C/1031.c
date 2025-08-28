/*
 *  Example:  Encrypt / Decrypt with a symmetric Fernet key
 *
 *  NOTE:
 *  ----------------------------------------------------------------
 *  – The header  <fernet.h>  together with its implementation
 *    is expected to be provided in the same project.  
 *  – It must export at least the following symbols
 *        • fernet_generate_key          -> void fernet_generate_key(uint8_t key_out[32]);
 *        • fernet_encrypt               -> uint8_t *fernet_encrypt(const uint8_t *key,
 *                                                                  const uint8_t *msg,
 *                                                                  size_t msg_len,
 *                                                                  size_t *cipher_len);
 *        • fernet_decrypt               -> uint8_t *fernet_decrypt(const uint8_t *key,
 *                                                                  const uint8_t *cipher,
 *                                                                  size_t cipher_len,
 *                                                                  size_t *plain_len);
 *        • All returned buffers are heap-allocated; the caller must free().
 *  ----------------------------------------------------------------
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "fernet.h"        /* supplied by the build system */

/* ----------------------------------------------------------------
 * encrypt()  – thin wrapper that mimics the Python helper
 * ---------------------------------------------------------------- */
static uint8_t *encrypt(const uint8_t  key[32],
                        const char    *message,
                        size_t        *cipher_len_out)
{
    return fernet_encrypt(key,
                          (const uint8_t *)message,
                          strlen(message),
                          cipher_len_out);
}

/* ----------------------------------------------------------------
 * decrypt() – thin wrapper that mimics the Python helper
 * ---------------------------------------------------------------- */
static char *decrypt(const uint8_t  key[32],
                     const uint8_t *cipher,
                     size_t         cipher_len)
{
    size_t plain_len = 0;
    uint8_t *plain = fernet_decrypt(key, cipher, cipher_len, &plain_len);
    if (!plain) return NULL;                 /* decryption failed */

    /* Ensure zero-terminated string for easy printing */
    char *result = (char *)malloc(plain_len + 1);
    if (!result) {
        free(plain);
        return NULL;
    }

    memcpy(result, plain, plain_len);
    result[plain_len] = '\0';
    free(plain);
    return result;
}

/* ----------------------------------------------------------------
 * main() – driver that reproduces the behaviour of the Python script
 * ---------------------------------------------------------------- */
int main(void)
{
    const char *message = "the solutions are here somewhere";
    uint8_t     key[32];                     /* Fernet keys are 32 bytes */

    printf("Secret Message: %s\n", message);

    /* Generate (or load) a random symmetric key */
    fernet_generate_key(key);

    /* Encrypt ---------------------------------------------------- */
    size_t   cipher_len = 0;
    uint8_t *ciphertext = encrypt(key, message, &cipher_len);
    if (!ciphertext) {
        fprintf(stderr, "Encryption failed!\n");
        return EXIT_FAILURE;
    }

    printf("Encrypted Message (Base64): %s\n", ciphertext);

    /* Decrypt ---------------------------------------------------- */
    char *decrypted = decrypt(key, ciphertext, cipher_len);
    if (!decrypted) {
        fprintf(stderr, "Decryption failed!\n");
        free(ciphertext);
        return EXIT_FAILURE;
    }

    printf("Decrypted Message: %s\n", decrypted);

    /* Cleanup ---------------------------------------------------- */
    free(ciphertext);
    free(decrypted);

    return EXIT_SUCCESS;
}