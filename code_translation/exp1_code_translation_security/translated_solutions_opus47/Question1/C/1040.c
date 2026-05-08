#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

/*
 * encrypt a string with a given key
 */
char* encrypt(const char* string, const char* key) {
    size_t str_len = strlen(string);
    size_t key_len = strlen(key);
    char* encrypted = (char*)malloc(str_len + 1);
    if (encrypted == NULL) {
        return NULL;
    }
    for (size_t i = 0; i < str_len; i++) {
        encrypted[i] = string[i] ^ key[i % key_len];
    }
    encrypted[str_len] = '\0';
    return encrypted;
}

/*
 * decrypt a string with a given key
 */
char* decrypt(const char* string, const char* key) {
    return encrypt(string, key);
}

int main(void) {
    char* encrypted = encrypt("sadasdasdsa", "1234");
    char* decrypted = decrypt(encrypted, "1234");
    assert(strcmp(decrypted, "sadasdasdsa") == 0);
    free(encrypted);
    free(decrypted);
    return 0;
}