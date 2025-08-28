/**
 * python_to_c_fernet_sample.c
 *
 * Straightforward translation of:
 *   import cryptography
 *   from cryptography.fernet import Fernet
 *
 *   def encrypt(data, key):
 *       fernet = Fernet(key)
 *       encrypted = fernet.encrypt(data)
 *       return encrypted
 *
 *   def decrypt(data, key):
 *       fernet = Fernet(key)
 *       decrypted = fernet.decrypt(data)
 *       return decrypted
 *
 *   def main():
 *       encrypt("/safedir/test.txt", Fernet.generate_key())
 *
 *   if __name__ == "__main__":
 *       main()
 *
 * ---------------------------------------------------------------------------
 * NOTE 1:  All “fernet_*” symbols used below are assumed to be supplied by
 *          the dependencies you said would be provided.
 * NOTE 2:  Error-handling is kept minimal, mirroring the simplicity of the
 *          original Python.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "fernet.h"     /* <— Must expose the functions used below              */

/* -------------------------------------------------------------------------- */
/* Direct equivalents of the two Python helpers                               */
unsigned char *encrypt(const unsigned char *data,
                       size_t               data_len,
                       const char          *base64_key,
                       size_t              *out_len)
{
    /* Construct a Fernet context from the key */
    FernetContext *ctx = fernet_new(base64_key);      /* provided helper       */
    if (!ctx) return NULL;

    /* Encrypt the buffer */
    unsigned char *ct = fernet_encrypt(ctx, data, data_len, out_len);

    /* Clean up */
    fernet_free(ctx);
    return ct;
}

unsigned char *decrypt(const unsigned char *token,
                       size_t               token_len,
                       const char          *base64_key,
                       size_t              *out_len)
{
    FernetContext *ctx = fernet_new(base64_key);      /* provided helper       */
    if (!ctx) return NULL;

    unsigned char *pt = fernet_decrypt(ctx, token, token_len, out_len);

    fernet_free(ctx);
    return pt;
}

/* -------------------------------------------------------------------------- */
/* Main reproduces `encrypt("/safedir/test.txt", Fernet.generate_key())`      */
int main(void)
{
    /* Generate a fresh key (Base-64 encoded, 44-byte printable string) */
    char key[FERNET_KEY_SIZE_B64 + 1]; /* size declared in <fernet.h> */
    if (fernet_generate_key(key) != 0) {
        fprintf(stderr, "Key generation failed\n");
        return EXIT_FAILURE;
    }

    /* What we are “encrypting” – same literal string as the Python sample */
    const char *plaintext = "/safedir/test.txt";
    size_t      ct_len     = 0;

    /* Encrypt */
    unsigned char *ciphertext = encrypt((const unsigned char *)plaintext,
                                        strlen(plaintext),
                                        key,
                                        &ct_len);

    if (!ciphertext) {
        fprintf(stderr, "Encryption failed\n");
        return EXIT_FAILURE;
    }

    /* Display result just to show something happened */
    printf("Key:  %s\n", key);
    printf("Ciphertext (%zu bytes):\n", ct_len);
    for (size_t i = 0; i < ct_len; ++i) {
        putchar(ciphertext[i]);
    }
    putchar('\n');

    /* Clean up */
    free(ciphertext);
    return EXIT_SUCCESS;
}