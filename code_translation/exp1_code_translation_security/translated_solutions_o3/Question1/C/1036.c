/*
 * Simple Caesar-like encryption/decryption example.
 * Mirrors the behaviour of the given Python script.
 */

#include <stdio.h>
#include <string.h>

/* Encrypts a C-string with a fixed integer key. */
void encrypt(const char *input, char *output, int key)
{
    size_t len = strlen(input);

    for (size_t i = 0; i < len; ++i)
        output[i] = (char)(input[i] + key);

    output[len] = '\0';          /* NUL-terminate the result */
}

/* Decrypts an encrypted C-string with the same key. */
void decrypt(const char *input, char *output, int key)
{
    size_t len = strlen(input);

    for (size_t i = 0; i < len; ++i)
        output[i] = (char)(input[i] - key);

    output[len] = '\0';
}

/* Entry point */
int main(void)
{
    const char *string = "Hello World!";
    const int key = 5;

    char encrypted[256];   /* Buffers large enough for demonstration */
    char decrypted[256];

    encrypt(string, encrypted, key);
    decrypt(encrypted, decrypted, key);

    printf("Encrypted: %s\n", encrypted);
    printf("Decrypted: %s\n", decrypted);

    return 0;
}