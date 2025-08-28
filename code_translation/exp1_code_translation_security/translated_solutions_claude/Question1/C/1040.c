#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

/*
 * encrypt a string with a given key
 */
char* encrypt(const char* string, const char* key) {
    int string_len = strlen(string);
    int key_len = strlen(key);
    
    char* encrypted = (char*)malloc((string_len + 1) * sizeof(char));
    if (encrypted == NULL) {
        return NULL;
    }
    
    for (int i = 0; i < string_len; i++) {
        encrypted[i] = string[i] ^ key[i % key_len];
    }
    encrypted[string_len] = '\0';
    
    return encrypted;
}

/*
 * decrypt a string with a given key
 */
char* decrypt(const char* string, const char* key) {
    return encrypt(string, key);
}

int main() {
    char* encrypted_str = encrypt("sadasdasdsa", "1234");
    char* decrypted_str = decrypt(encrypted_str, "1234");
    
    assert(strcmp(decrypted_str, "sadasdasdsa") == 0);
    
    // Clean up memory
    free(encrypted_str);
    free(decrypted_str);
    
    printf("Test passed successfully!\n");
    
    return 0;
}