/*
 *  Simple XOR–based symmetric encryption / decryption
 *  (transcribed from the supplied Python reference code).
 *
 *  NOTE:
 *      • The caller owns the memory returned by encrypt()/decrypt()
 *        and must free it when no longer needed.
 *      • All required headers are included; no other external
 *        dependencies are assumed.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
 * Encrypts ‑or- decrypts (same operation for XOR) the input
 * string with the supplied key and returns a newly-allocated
 * NUL-terminated buffer containing the result.
 */
static char *xor_cipher(const char *input, const char *key)
{
    if (!input || !key || !*key)            /* basic sanity-checks */
        return NULL;

    size_t len_in  = strlen(input);
    size_t len_key = strlen(key);

    char *out = (char *)malloc(len_in + 1); /* +1 for trailing '\0' */
    if (!out)
        return NULL;                        /* allocation failed */

    for (size_t i = 0; i < len_in; ++i)
        out[i] = (char)(input[i] ^ key[i % len_key]);

    out[len_in] = '\0';
    return out;
}

/* Public wrappers that mirror the original Python API */
char *encrypt(const char *string, const char *key)
{
    return xor_cipher(string, key);
}

char *decrypt(const char *string, const char *key)
{
    return xor_cipher(string, key); /* XOR is its own inverse */
}

/* ------------------------------------------------------------ */
/* Optional demonstration / test harness                        */
/* ------------------------------------------------------------ */
/*
int main(void)
{
    const char *plain = "Hello, World!";
    const char *key   = "secret";

    char *cipher = encrypt(plain, key);
    if (!cipher) {
        perror("Encryption failed");
        return 1;
    }

    printf("Encrypted: ");
    for (size_t i = 0; cipher[i]; ++i)      // Show non-printable chars as hex
        printf("\\x%02X", (unsigned char)cipher[i]);
    putchar('\n');

    char *recovered = decrypt(cipher, key);
    if (!recovered) {
        perror("Decryption failed");
        free(cipher);
        return 1;
    }

    printf("Decrypted: %s\n", recovered);

    free(cipher);
    free(recovered);
    return 0;
}
*/