/*
 *  Translation of the given Python snippet to C.
 *  ------------------------------------------------
 *  NOTE:
 *    1.  It is assumed that the “cryptocode”-equivalent C API
 *        (i.e. the functions encrypt() and decrypt()) is available
 *        at compile / link time, exactly as shown below:
 *
 *            char *encrypt (const char *plain_text, const char *key);
 *            char *decrypt (const char *cipher_text, const char *key);
 *
 *    2.  Both encrypt() and decrypt() are assumed to return a
 *        heap-allocated, NUL-terminated char-buffer that must be
 *        free()’d by the caller when no longer needed.
 *
 *    3.  Because all external dependencies are guaranteed to be
 *        provided, the source below focuses only on the functional
 *        translation of the Python code.
 */

#include <stdio.h>
#include <stdlib.h>   /* malloc / free */

/*  <--   External dependency header goes here   -->  */
/*  #include "cryptocode.h"                          */

/* ------------------------------------------------- */
/*  Helper wrappers mimicking the original Python    */
/*  functions.                                       */
/* ------------------------------------------------- */
char *encode_str(const char *plain_text, const char *key)
{
    /* calls the provided C implementation of encrypt() */
    char *str_encoded = encrypt(plain_text, key);
    return str_encoded;       /* caller must free()   */
}

char *decode_str(const char *str_encoded, const char *key)
{
    /* calls the provided C implementation of decrypt() */
    char *str_decoded = decrypt(str_encoded, key);
    return str_decoded;       /* caller must free()   */
}

/* ------------------------------------------------- */
/*                      main()                       */
/* ------------------------------------------------- */
int main(void)
{
    /* 1) Encrypt */
    char *cipher = encode_str("I am okay", "wow");

    /* 2) Decrypt and print result */
    char *plain  = decode_str(cipher, "wow");
    printf("%s\n", plain);

    /* 3) Release heap memory returned by encrypt/decrypt */
    free(cipher);
    free(plain);

    return 0;
}