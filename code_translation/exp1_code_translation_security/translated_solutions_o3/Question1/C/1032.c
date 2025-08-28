/*
 * Simple symmetric “encryption / de-cryption” demo.
 * (A Caesar-style shift by <key> positions.)
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*----------------------------------------------------------
 *  Encrypts the input string by adding <key> to each byte.
 *  A newly-allocated, NUL-terminated string is returned.
 *  The caller is responsible for free()-ing the memory.
 *---------------------------------------------------------*/
char *encrypt(const char *input, int key)
{
    size_t len = strlen(input);
    char  *out = (char *)malloc(len + 1);   /* +1 for '\0' */
    if (!out) return NULL;

    for (size_t i = 0; i < len; ++i)
        out[i] = (char)(input[i] + key);

    out[len] = '\0';
    return out;
}

/*----------------------------------------------------------
 *  Decrypts the input string by subtracting <key> from
 *  each byte.  Works symmetrically with encrypt().
 *---------------------------------------------------------*/
char *decrypt(const char *input, int key)
{
    size_t len = strlen(input);
    char  *out = (char *)malloc(len + 1);
    if (!out) return NULL;

    for (size_t i = 0; i < len; ++i)
        out[i] = (char)(input[i] - key);

    out[len] = '\0';
    return out;
}

int main(void)
{
    const char *plain = "Hello World!";
    int          key  = 5;

    char *cipher = encrypt(plain, key);
    if (!cipher) {
        fprintf(stderr, "Memory allocation failed\n");
        return EXIT_FAILURE;
    }

    char *decoded = decrypt(cipher, key);
    if (!decoded) {
        fprintf(stderr, "Memory allocation failed\n");
        free(cipher);
        return EXIT_FAILURE;
    }

    printf("Encrypted: %s\n", cipher);
    printf("Decrypted: %s\n", decoded);

    free(cipher);
    free(decoded);
    return EXIT_SUCCESS;
}