/*
 * Simple Caesar-style encrypt / decrypt working on wide (Unicode) characters
 * Build example (GCC / Clang):  gcc -std=c99 -Wall -Wextra -o demo demo.c
 */

#include <stdio.h>
#include <stdlib.h>
#include <wchar.h>
#include <locale.h>

/* ------------------------------------------------------------------------ */
/* Encrypt a Unicode (wide-char) string by adding ‘key’ to every character. */
wchar_t *encrypt(const wchar_t *text, int key)
{
    size_t len = wcslen(text);
    wchar_t *out = malloc((len + 1) * sizeof *out);
    if (!out) {
        fwprintf(stderr, L"Allocation failure\n");
        return NULL;
    }

    for (size_t i = 0; i < len; ++i)
        out[i] = text[i] + key;

    out[len] = L'\0';
    return out;
}

/* ------------------------------------------------------------------------ */
/* Decrypt a Unicode (wide-char) string by subtracting ‘key’.               */
wchar_t *decrypt(const wchar_t *text, int key)
{
    size_t len = wcslen(text);
    wchar_t *out = malloc((len + 1) * sizeof *out);
    if (!out) {
        fwprintf(stderr, L"Allocation failure\n");
        return NULL;
    }

    for (size_t i = 0; i < len; ++i)
        out[i] = text[i] - key;

    out[len] = L'\0';
    return out;
}

/* ------------------------------------------------------------------------ */
int main(void)
{
    /* Enable the user’s locale so wide I/O understands UTF-8 etc. */
    setlocale(LC_CTYPE, "");

    /* Example usage (mirrors the original Python code) */
    const wchar_t input[] = L"name";
    const int     key    = 1234;

    wchar_t *enc = encrypt(input, key);
    if (!enc) return EXIT_FAILURE;

    wchar_t *dec = decrypt(L"ՀԳԿԷ", key);
    if (!dec) {
        free(enc);
        return EXIT_FAILURE;
    }

    /* Print results */
    wprintf(L"%ls\n", enc);   /* Output from encryption  */
    wprintf(L"%ls\n", dec);   /* Output from decryption  */

    /* Clean up */
    free(enc);
    free(dec);

    return EXIT_SUCCESS;
}