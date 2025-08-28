/*
 * Simple XOR-based symmetric cipher.
 *
 *   encrypt() – encrypts a NUL-terminated string with a given key.
 *   decrypt() – decrypts a NUL-terminated string with the same key.
 *
 * Both functions return a newly-allocated, NUL-terminated byte
 * sequence containing the result.  The caller is responsible for
 * freeing the returned buffer with free().
 */

#include <stdlib.h>   /* malloc, free */
#include <string.h>   /* strlen */

/* ------------------------------------------------------------------ */
/* Internal helper: performs the actual XOR transformation.           */
/* ------------------------------------------------------------------ */
static char *xor_crypt(const char *input, const char *key)
{
    if (!input || !key)                 /* basic guard */
        return NULL;

    size_t in_len  = strlen(input);
    size_t key_len = strlen(key);

    /* +1 for the terminating '\0' */
    char *out = (char *)malloc(in_len + 1);
    if (!out)
        return NULL;                    /* allocation failed */

    for (size_t i = 0; i < in_len; ++i)
        out[i] = input[i] ^ key[i % key_len];

    out[in_len] = '\0';                 /* keep it a C-string */
    return out;
}

/* ------------------------------------------------------------------ */
/* Public wrappers – mirror the original Python interface.            */
/* ------------------------------------------------------------------ */
char *encrypt(const char *string, const char *key)
{
    return xor_crypt(string, key);
}

char *decrypt(const char *string, const char *key)
{
    return xor_crypt(string, key);
}