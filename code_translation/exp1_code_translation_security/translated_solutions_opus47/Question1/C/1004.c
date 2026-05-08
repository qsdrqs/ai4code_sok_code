#include <stdio.h>
#include <string.h>
#include <stdlib.h>

/*
 * Function encrypt
 *
 * Given a message and a key, encrypt the message using XOR encryption.
 *
 * Note: Because XOR can produce bytes with value 0 (which would terminate a
 * C string), we pass and keep track of the message length explicitly.
 */
char* encrypt(const char* message, size_t message_len, const char* key) {
    size_t key_len = strlen(key);
    char* encrypted = (char*)malloc(message_len + 1);
    if (encrypted == NULL) {
        return NULL;
    }
    for (size_t i = 0; i < message_len; i++) {
        encrypted[i] = message[i] ^ key[i % key_len];
    }
    encrypted[message_len] = '\0';
    return encrypted;
}

/*
 * Function decrypt
 *
 * Given an encrypted message and a key, decrypt the message using XOR encryption.
 */
char* decrypt(const char* encrypted, size_t message_len, const char* key) {
    return encrypt(encrypted, message_len, key);
}

int main(void) {
    const char* message = "Hello world";
    size_t length = strlen(message);

    char* encrypted = encrypt(message, length, "key");
    char* decrypted = decrypt(encrypted, length, "key");

    printf("%s\n", decrypted);

    free(encrypted);
    free(decrypted);

    return 0;
}