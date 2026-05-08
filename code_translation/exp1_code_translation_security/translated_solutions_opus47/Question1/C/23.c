#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// this is gonna be really scuffed but it might work
// right now text and key must be equal length, but that can be changed to loop to the front of the key again for very long text
// it's TECHNICALLY a psuedo-cypher

char* encrypt(const char* text, const char* key) {
    size_t len = strlen(text);
    char* result = (char*)malloc(len + 1);
    if (result == NULL) return NULL;

    for (size_t i = 0; i < len; i++) {
        // cast to unsigned char to avoid sign-extension issues
        result[i] = (char)((unsigned char)text[i] + (unsigned char)key[i]);
    }
    result[len] = '\0';
    return result;
}

char* decrypt(const char* text, const char* key) {
    size_t len = strlen(text);
    char* result = (char*)malloc(len + 1);
    if (result == NULL) return NULL;

    for (size_t i = 0; i < len; i++) {
        result[i] = (char)((unsigned char)text[i] - (unsigned char)key[i]);
    }
    result[len] = '\0';
    return result;
}

int main(void) {
    const char* key = "aaaaaaa";
    const char* decrypted = "abcdefg";
    char* encrypted = encrypt("abcdefg", key);

    char* enc_result = encrypt(decrypted, key);
    printf("%s\n", enc_result);
    free(enc_result);

    char* dec_result = decrypt(encrypted, key);
    printf("%s\n", dec_result);
    free(dec_result);

    free(encrypted);
    return 0;
}