#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* encrypt(const char* key, const char* string) {
    size_t string_len = strlen(string);
    size_t key_len = strlen(key);
    char* encrypted_text = (char*)malloc(string_len + 1);
    if (encrypted_text == NULL) {
        return NULL;
    }
    for (size_t i = 0; i < string_len; i++) {
        unsigned char key_c = (unsigned char)key[i % key_len];
        encrypted_text[i] = (char)(((unsigned char)string[i] + key_c) % 256);
    }
    encrypted_text[string_len] = '\0';
    return encrypted_text;
}

char* decrypt(const char* key, const char* string) {
    size_t string_len = strlen(string);
    size_t key_len = strlen(key);
    char* decrypted_text = (char*)malloc(string_len + 1);
    if (decrypted_text == NULL) {
        return NULL;
    }
    for (size_t i = 0; i < string_len; i++) {
        unsigned char key_c = (unsigned char)key[i % key_len];
        decrypted_text[i] = (char)(((unsigned char)string[i] - key_c + 256) % 256);
    }
    decrypted_text[string_len] = '\0';
    return decrypted_text;
}