/*
 * Simple XOR-based encryption / decryption
 * (exact same logic as the Python snippet)
 *
 * NOTE:
 *   Because XOR can generate NUL (0x00) bytes, the implementation works
 *   with explicit lengths instead of relying on C’s zero-terminated
 *   “strings”.  As long as you keep the original length around, the data
 *   can be treated as an arbitrary byte array.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

/*------------------------------------------------------------
 *  encrypt : XOR every byte of “data” with the corresponding
 *            byte of “key” (cycled), return freshly allocated
 *            buffer that must be free()d by the caller.
 *-----------------------------------------------------------*/
unsigned char *encrypt(const unsigned char *data,
                       size_t               data_len,
                       const unsigned char *key,
                       size_t               key_len)
{
    unsigned char *out = (unsigned char *)malloc(data_len);
    if (!out) {
        perror("malloc");
        exit(EXIT_FAILURE);
    }

    for (size_t i = 0; i < data_len; ++i)
        out[i] = data[i] ^ key[i % key_len];

    return out;
}

/* XOR is its own inverse, so decrypt == encrypt */
static inline unsigned char *decrypt(const unsigned char *data,
                                     size_t               data_len,
                                     const unsigned char *key,
                                     size_t               key_len)
{
    return encrypt(data, data_len, key, key_len);
}

/*------------------------------------------------------------*/
int main(void)
{
    const char *plain = "sadasdasdsa";
    const char *key   = "1234";

    size_t plain_len = strlen(plain);
    size_t key_len   = strlen(key);

    /* encrypt */
    unsigned char *cipher = encrypt((const unsigned char *)plain, plain_len,
                                    (const unsigned char *)key,   key_len);

    /* decrypt */
    unsigned char *decoded = decrypt(cipher, plain_len,
                                     (const unsigned char *)key, key_len);

    /* ensure round-trip correctness (same as Python assert) */
    assert(memcmp(decoded, plain, plain_len) == 0);
    puts("Assertion succeeded – encryption and decryption match!");

    /* --- (optional) show encrypted bytes in hex --- */
    printf("Encrypted data: ");
    for (size_t i = 0; i < plain_len; ++i)
        printf("%02X ", cipher[i]);
    putchar('\n');

    /* clean-up */
    free(cipher);
    free(decoded);
    return 0;
}