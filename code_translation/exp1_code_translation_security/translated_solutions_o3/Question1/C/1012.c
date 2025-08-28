/* -------------------------------------------------------------------- *
 *  fernet_demo.c                                                       *
 * -------------------------------------------------------------------- *
 *  Translation of                                                     *
 *      from cryptography.fernet import Fernet                         *
 *                                                                    *
 *      key = Fernet.generate_key()                                    *
 *                                                                    *
 *      def encrypt_text(plain_text):                                  *
 *          f = Fernet(key)                                            *
 *          encrypted_text = f.encrypt(bytes(plain_text, "utf-8"))     *
 *          return encrypted_text.decode()                             *
 *                                                                    *
 *      def decrypt_text(encrypted_text):                              *
 *          f = Fernet(key)                                            *
 *          return f.decrypt(bytes(encrypted_text, "utf-8")).decode()  *
 * -------------------------------------------------------------------- */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "fernet.h"   /* <-- provided by the build system               */

/* -------------------------------------------------------------------- *
 *  1.  Global key – equivalent to the module-level `key` in Python     *
 * -------------------------------------------------------------------- */
static fernet_key g_key;                         /* 32-byte raw key     */
static char       g_key_b64[FERNET_KEY_STRING_SIZE]; /* 44 chars + NUL */

/* Generate the key once, exactly like `Fernet.generate_key()`          */
static void init_key(void)
{
    fernet_key_generate(&g_key);
    fernet_key_to_string(&g_key, g_key_b64);     /* just so we can print */
}

/* -------------------------------------------------------------------- *
 *  2.  Functional equivalents to the Python helpers                    *
 * -------------------------------------------------------------------- */

/*
 * Encrypt a UTF-8 string.
 * The function returns a *heap-allocated* NUL-terminated string that
 * the caller must free with `free()`.
 */
char *encrypt_text(const char *plain_text)
{
    fernet_ctx ctx;
    fernet_init(&ctx, &g_key);

    size_t cipher_len;
    unsigned char *cipher_bin =
        fernet_encrypt(&ctx,
                       (const unsigned char *)plain_text,
                       strlen(plain_text),
                       &cipher_len);

    /* Turn the binary token into printable ASCII (base64). */
    char *cipher_b64 = fernet_encode(cipher_bin, cipher_len);

    free(cipher_bin);            /* binary form no longer needed */
    return cipher_b64;           /* caller frees this string     */
}

/*
 * Decrypt an ASCII Fernet token.
 * Returns a heap-allocated, NUL-terminated string (UTF-8 text).
 */
char *decrypt_text(const char *encrypted_text)
{
    fernet_ctx ctx;
    fernet_init(&ctx, &g_key);

    size_t plain_len;
    unsigned char *plain_bin =
        fernet_decrypt(&ctx, encrypted_text, &plain_len);

    char *plain_str = malloc(plain_len + 1);
    if (!plain_str) {            /* out of memory … */
        free(plain_bin);
        return NULL;
    }
    memcpy(plain_str, plain_bin, plain_len);
    plain_str[plain_len] = '\0';

    free(plain_bin);
    return plain_str;            /* caller frees this string     */
}

/* -------------------------------------------------------------------- *
 *  3.  Tiny self-test – compile with  -DTEST_FERNET to enable          *
 * -------------------------------------------------------------------- */
#ifdef TEST_FERNET
int main(void)
{
    init_key();

    const char *msg = "Attack at dawn!";
    char *cipher  = encrypt_text(msg);
    char *plain   = decrypt_text(cipher);

    printf("Key         : %s\n", g_key_b64);
    printf("Plain text  : %s\n", msg);
    printf("Cipher text : %s\n", cipher);
    printf("Decrypted   : %s\n", plain);

    free(cipher);
    free(plain);
    return 0;
}
#endif