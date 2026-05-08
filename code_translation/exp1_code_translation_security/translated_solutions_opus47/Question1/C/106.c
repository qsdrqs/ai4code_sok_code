#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <assert.h>

char *encrypt(const char *plaintext, const char *key) {
    int plaintext_len = strlen(plaintext);
    int key_len = strlen(key);
    char *ciphertext = (char *)malloc(plaintext_len + 1);
    for (int i = 0; i < plaintext_len; i++) {
        ciphertext[i] = ((plaintext[i] + key[i % key_len] - 2 * 'a') % 26) + 'a';
    }
    ciphertext[plaintext_len] = '\0';
    return ciphertext;
}

char *decrypt(const char *ciphertext, const char *key) {
    int ciphertext_len = strlen(ciphertext);
    int key_len = strlen(key);
    char *plaintext = (char *)malloc(ciphertext_len + 1);
    for (int i = 0; i < ciphertext_len; i++) {
        plaintext[i] = ((ciphertext[i] - key[i % key_len] + 26) % 26) + 'a';
    }
    plaintext[ciphertext_len] = '\0';
    return plaintext;
}

int main() {
    const char *plaintext = "helloworld";
    const char *key = "python";
    
    char *ciphertext = encrypt(plaintext, key);
    printf("Ciphertext: %s\n", ciphertext);
    
    char *decrypted = decrypt(ciphertext, key);
    printf("%s\n", decrypted);
    
    assert(strcmp(plaintext, decrypted) == 0);
    
    free(ciphertext);
    free(decrypted);
    
    return 0;
}