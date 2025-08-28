#include <stdlib.h>   /* malloc / free   */
#include <string.h>   /* strlen          */

/*
 * NOTE:
 *  –  The functions allocate a new buffer large enough to hold the
 *     result plus a terminating NUL byte.  The caller owns the buffer
 *     and must free it when finished.
 *  –  They return NULL if allocation fails.
 *  –  Because the algorithm may generate any value in the range
 *     0-255, the result is handled as unsigned bytes internally,
 *     but the outward type is plain ‘char *’ for convenience.
 */

char *encrypt(const char *key, const char *text)
{
    size_t text_len = strlen(text);
    size_t key_len  = strlen(key);

    char *out = (char *)malloc(text_len + 1);   /* +1 for terminating 0 */
    if (!out) return NULL;

    for (size_t i = 0; i < text_len; ++i)
    {
        unsigned char t  = (unsigned char)text[i];
        unsigned char kc = (unsigned char)key[i % key_len];

        out[i] = (char)((t + kc) & 0xFF);       /* modulo 256 */
    }

    out[text_len] = '\0';
    return out;
}

char *decrypt(const char *key, const char *text)
{
    size_t text_len = strlen(text);
    size_t key_len  = strlen(key);

    char *out = (char *)malloc(text_len + 1);
    if (!out) return NULL;

    for (size_t i = 0; i < text_len; ++i)
    {
        unsigned char t  = (unsigned char)text[i];
        unsigned char kc = (unsigned char)key[i % key_len];

        out[i] = (char)((t - kc + 256) & 0xFF); /* modulo 256 */
    }

    out[text_len] = '\0';
    return out;
}