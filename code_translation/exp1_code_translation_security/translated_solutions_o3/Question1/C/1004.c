#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
 * Function: encrypt
 * -----------------
 *  Given a message and a key, encrypt the message using XOR encryption.
 *
 *  message: the plain-text message to encrypt
 *  key:     the key used for XOR encryption
 *
 *  returns: a newly-allocated, null-terminated C-string that holds
 *           the encrypted data.  The caller is responsible for free()-ing it.
 */
char *encrypt(const char *message, const char *key)
{
    size_t msg_len = strlen(message);
    size_t key_len = strlen(key);

    char *encrypted = (char *)malloc(msg_len + 1);   /* +1 for terminating '\0' */
    if (encrypted == NULL)
        return NULL;

    for (size_t i = 0; i < msg_len; ++i)
        encrypted[i] = message[i] ^ key[i % key_len];

    encrypted[msg_len] = '\0';
    return encrypted;
}

/*
 * Function: decrypt
 * -----------------
 *  Given an encrypted message and a key, decrypt the message using XOR
 *  encryption.  Because XOR is its own inverse, this is the same
 *  operation as encryption.
 *
 *  encrypted: the cipher-text message
 *  key:       the key used for XOR encryption
 *
 *  returns: a newly-allocated, null-terminated C-string that holds
 *           the decrypted data.  The caller is responsible for free()-ing it.
 */
char *decrypt(const char *encrypted, const char *key)
{
    return encrypt(encrypted, key);
}

int main(void)
{
    const char *message = "Hello world";
    const char *key     = "key";

    /* Encrypt the message */
    char *cipher = encrypt(message, key);
    if (cipher == NULL) {
        perror("Memory allocation failed");
        return EXIT_FAILURE;
    }

    /* Decrypt the message */
    char *plain = decrypt(cipher, key);
    if (plain == NULL) {
        perror("Memory allocation failed");
        free(cipher);
        return EXIT_FAILURE;
    }

    printf("%s\n", plain);   /* Should print: Hello world */

    free(cipher);
    free(plain);
    return EXIT_SUCCESS;
}