/*  demo_fernet.c  */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "fernet.h"           /*  <- supplied with the project  */


/* ------------------------------------------------------------ *
 *  Thin wrappers that mirror the Python version’s signatures   *
 * ------------------------------------------------------------ */

/* Returns a newly-allocated, NUL-terminated base-64 token.      */
static char *encrypt_msg(const unsigned char *in,
                         size_t                in_len,
                         const char           *b64key)
{
    return fernet_encrypt(in, in_len, b64key);
}

/* Returns newly-allocated plaintext; its length is stored in    *
 * *out_len.                                                     */
static unsigned char *decrypt_msg(const char *token,
                                  const char *b64key,
                                  size_t     *out_len)
{
    return (unsigned char *) fernet_decrypt(token, b64key, out_len);
}


/* ------------------------------------------------------------ *
 *  main – identical control flow to the original Python script *
 * ------------------------------------------------------------ */
int main(void)
{
    /* 1. Generate a 32-byte Fernet key and keep it in base-64   *
     *    (exactly 44 printable characters + NUL).               */
    char key[45];            /* 44 chars + '\0'                 */
    fernet_generate_key(key);

    /* 2. Message to protect                                     */
    const unsigned char  input[] = "super secret";
    const size_t         input_len = sizeof(input) - 1; /* no NUL */

    /* 3. Encrypt                                                */
    char *ciphertext = encrypt_msg(input, input_len, key);
    if (!ciphertext) {
        fprintf(stderr, "Encryption failed\n");
        return EXIT_FAILURE;
    }

    /* 4. Decrypt                                                */
    size_t         decrypted_len = 0;
    unsigned char *decrypted     = decrypt_msg(ciphertext, key, &decrypted_len);
    if (!decrypted) {
        fprintf(stderr, "Decryption failed\n");
        free(ciphertext);
        return EXIT_FAILURE;
    }

    /* 5. Show results                                           */
    printf("%s\n", input);                         /* original   */
    printf("%.*s\n", (int)decrypted_len, decrypted); /* recovered */

    /* 6. Clean up                                               */
    free(ciphertext);
    free(decrypted);

    return EXIT_SUCCESS;
}