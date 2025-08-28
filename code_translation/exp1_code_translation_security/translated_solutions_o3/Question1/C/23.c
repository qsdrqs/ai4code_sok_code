/* A direct translation of the original Python snippet.             */
/* The “encryption” is just a per-character addition (or subtraction)*/
/* of the code-points in text and key.  Text and key must have the  */
/* same length, exactly like the Python version shown.              */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* ----------------------------------------------------------------- */
/*  encrypt:  returns a newly-allocated C-string                     */
/* ----------------------------------------------------------------- */
char *encrypt(const char *text, const char *key)
{
    size_t len = strlen(text);          /* assumes key has same len  */
    char *result = (char *)malloc(len + 1);   /* +1 for terminating NUL */

    if (!result)                        /* allocation check          */
        return NULL;

    for (size_t i = 0; i < len; ++i)
        result[i] = (char)(text[i] + key[i]);

    result[len] = '\0';
    return result;
}

/* ----------------------------------------------------------------- */
/*  decrypt:  inverse of encrypt                                     */
/* ----------------------------------------------------------------- */
char *decrypt(const char *text, const char *key)
{
    size_t len = strlen(text);          /* assumes key has same len  */
    char *result = (char *)malloc(len + 1);

    if (!result)
        return NULL;

    for (size_t i = 0; i < len; ++i)
        result[i] = (char)(text[i] - key[i]);

    result[len] = '\0';
    return result;
}

/* ----------------------------------------------------------------- */
int main(void)
{
    const char *key       = "aaaaaaa";
    const char *decrypted = "abcdefg";

    /* encrypt */
    char *encrypted = encrypt(decrypted, key);
    if (!encrypted) {
        fprintf(stderr, "Memory allocation failed\n");
        return EXIT_FAILURE;
    }

    /* print encrypted text */
    printf("%s\n", encrypted);

    /* decrypt back */
    char *restored = decrypt(encrypted, key);
    if (!restored) {
        fprintf(stderr, "Memory allocation failed\n");
        free(encrypted);
        return EXIT_FAILURE;
    }

    /* print decrypted (restored) text */
    printf("%s\n", restored);

    /* cleanup */
    free(encrypted);
    free(restored);

    return EXIT_SUCCESS;
}