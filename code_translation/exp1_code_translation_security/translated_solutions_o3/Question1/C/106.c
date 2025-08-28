#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

/* -------------------------------------------------
 * Vigenère-style helpers
 * -------------------------------------------------*/
char *encrypt(const char *plaintext, const char *key)
{
    size_t pt_len  = strlen(plaintext);
    size_t key_len = strlen(key);

    char *ciphertext = malloc(pt_len + 1);   /* +1 for '\0' */
    if (!ciphertext) {
        perror("malloc");
        exit(EXIT_FAILURE);
    }

    for (size_t i = 0; i < pt_len; ++i) {
        char p = plaintext[i];          /* current plaintext character */
        char k = key[i % key_len];      /* corresponding key character */

        ciphertext[i] = (char)((((p - 'a') + (k - 'a')) % 26) + 'a');
    }
    ciphertext[pt_len] = '\0';
    return ciphertext;
}

char *decrypt(const char *ciphertext, const char *key)
{
    size_t ct_len  = strlen(ciphertext);
    size_t key_len = strlen(key);

    char *plaintext = malloc(ct_len + 1);
    if (!plaintext) {
        perror("malloc");
        exit(EXIT_FAILURE);
    }

    for (size_t i = 0; i < ct_len; ++i) {
        char c = ciphertext[i];
        char k = key[i % key_len];

        plaintext[i] = (char)((((c - 'a') - (k - 'a') + 26) % 26) + 'a');
    }
    plaintext[ct_len] = '\0';
    return plaintext;
}

/* -------------------------------------------------
 * Demo / test
 * -------------------------------------------------*/
int main(void)
{
    const char *plaintext = "helloworld";
    const char *key       = "python";

    char *ciphertext = encrypt(plaintext, key);
    printf("Ciphertext: %s\n", ciphertext);

    char *decrypted = decrypt(ciphertext, key);
    printf("Decrypted:  %s\n", decrypted);

    /* sanity check (mimics the Python assert) */
    assert(strcmp(plaintext, decrypted) == 0);

    free(ciphertext);
    free(decrypted);
    return 0;
}