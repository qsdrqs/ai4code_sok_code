/*----------------------------------------------------------
 *  Caesar-style “encrypt” and Vigenère-style “decrypt”
 *  (direct transliteration of the supplied Python version)
 *---------------------------------------------------------*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

/*----------------------------------------------------------
 *  encrypt
 *----------------------------------------------------------
 *  key    : non-negative shift value
 *  string : text to be enciphered
 *
 *  Returned value
 *      Newly-allocated, NUL-terminated ciphertext or NULL on
 *      any failure/invalid input.  Caller must free().
 *---------------------------------------------------------*/
char *encrypt(int key, const char *string)
{
    /* --- basic argument checks ---------------------------------- */
    if (string == NULL)          return NULL;
    if (key     <  0)            return NULL;

    size_t len = strlen(string);
    if (len == 0)                return NULL;

    /* --- normalise key to range 0-25 ----------------------------- */
    if (key > 26)
        key %= 26;

    /* --- allocate output buffer --------------------------------- */
    char *out = (char *)malloc(len + 1);
    if (!out)
        return NULL;             /* malloc failed */

    /* --- perform character-wise shift --------------------------- */
    for (size_t i = 0; i < len; ++i) {
        unsigned char ch = (unsigned char)string[i];

        if (isalpha(ch)) {
            if (islower(ch))
                out[i] = (char)(((ch - 'a' + key) % 26) + 'a');
            else  /* is upper-case */
                out[i] = (char)(((ch - 'A' + key) % 26) + 'A');
        } else {
            out[i] = (char)ch;   /* non-alphabetic: copy verbatim */
        }
    }
    out[len] = '\0';
    return out;
}

/*----------------------------------------------------------
 *  decrypt   (Vigenère-like, mirroring the given Python)
 *----------------------------------------------------------
 *  ciphertext : text to be deciphered (expected upper-case)
 *  key        : symmetric key (expected upper-case)
 *
 *  Returned value
 *      Newly-allocated, NUL-terminated plaintext or NULL on
 *      any failure/invalid input.  Caller must free().
 *---------------------------------------------------------*/
char *decrypt(const char *ciphertext, const char *key)
{
    /* --- basic argument checks ---------------------------------- */
    if (ciphertext == NULL || key == NULL)  return NULL;

    size_t c_len = strlen(ciphertext);
    size_t k_len = strlen(key);

    if (c_len == 0 || k_len == 0)           return NULL;
    if (k_len == 1)                         return strdup(ciphertext);
    if (k_len >  c_len)                     return NULL;

    /* --- allocate output buffer --------------------------------- */
    char *plain = (char *)malloc(c_len + 1);
    if (!plain)
        return NULL;

    /* --- classical Vigenère decryption -------------------------- */
    for (size_t i = 0; i < c_len; ++i) {
        int diff = ((int)(unsigned char)ciphertext[i] -
                    (int)(unsigned char)key[i % k_len]) % 26;
        if (diff < 0) diff += 26;           /* keep result positive */

        plain[i] = (char)(diff + 'A');      /* store as upper-case */
    }
    plain[c_len] = '\0';
    return plain;
}

/*----------------------------------------------------------
 *  (Optional) Small demo for local testing
 *  Uncomment main() if you wish to compile & run directly.
 *---------------------------------------------------------*/
/*
int main(void)
{
    char *enc = encrypt(3, "Hello, World!");
    if (enc) {
        printf("Encrypted: %s\n", enc);
        free(enc);
    }

    char *dec = decrypt("RIJVSUYVJN", "KEY");
    if (dec) {
        printf("Decrypted: %s\n", dec);
        free(dec);
    }
    return 0;
}
*/