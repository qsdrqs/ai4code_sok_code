/*  main.c  */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "fernet.h"          /*  << dependency supplied to you  */

/* ------------------------------------------------------------- */
/*  Python:  ex_key = Fernet.generate_key()                      */
/* ------------------------------------------------------------- */
static void generate_key(char key_out[FERNET_KEY_SIZE_BASE64 + 1])
{
    fernet_generate_key(key_out);      /* helper supplied by fernet.c */
}

/* ------------------------------------------------------------- */
/*  Python:  encrypt(inp, key)                                    */
/* ------------------------------------------------------------- */
static char *encrypt(const char *inp, const char *key_b64)
{
    /* The helper encrypts and returns a NUL-terminated base64 token.
       The caller must free() it.                                   */
    return fernet_encrypt_string(inp, key_b64);
}

/* ------------------------------------------------------------- */
/*  Python:  decrypt(inp, key)                                    */
/* ------------------------------------------------------------- */
static char *decrypt(const char *token_b64, const char *key_b64)
{
    /* Returns a malloc’ed, NUL-terminated UTF-8 string.           */
    return fernet_decrypt_string(token_b64, key_b64);
}

/* ------------------------------------------------------------- */
/*                    Driver identical to Python                  */
/* ------------------------------------------------------------- */
int main(void)
{
    /* 1. Generate a brand-new key (44 printable chars + '\0'). */
    char ex_key[FERNET_KEY_SIZE_BASE64 + 1];
    generate_key(ex_key);

    /* 2. Encrypt “Hello World!” */
    char *encrypted = encrypt("Hello World!", ex_key);
    if (!encrypted) {
        fprintf(stderr, "Encryption failed\n");
        return EXIT_FAILURE;
    }
    printf("%s\n", encrypted);

    /* 3. Decrypt it back. */
    char *decrypted = decrypt(encrypted, ex_key);
    if (!decrypted) {
        fprintf(stderr, "Decryption failed\n");
        free(encrypted);
        return EXIT_FAILURE;
    }
    printf("%s\n", decrypted);

    /* 4. Tidy-up. */
    free(encrypted);
    free(decrypted);
    return EXIT_SUCCESS;
}