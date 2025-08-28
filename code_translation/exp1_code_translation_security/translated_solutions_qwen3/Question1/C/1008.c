#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Encrypt function: adds key characters (cycled) to plaintext modulo 256
char* encrypt(char* key, char* plaintext) {
    int len_plain = strlen(plaintext);
    int len_key = strlen(key);
    char* ciphertext = malloc(len_plain + 1);  // +1 for null terminator
    if (!ciphertext) return NULL;

    for (int i = 0; i < len_plain; i++) {
        char key_c = key[i % len_key];
        ciphertext[i] = ( (unsigned char)plaintext[i] + (unsigned char)key_c ) % 256;
    }
    ciphertext[len_plain] = '\0';  // Null-terminate the result
    return ciphertext;
}

// Decrypt function: subtracts key characters (cycled) from ciphertext modulo 256
char* decrypt(char* key, char* ciphertext) {
    int len_cipher = strlen(ciphertext);
    int len_key = strlen(key);
    char* plaintext = malloc(len_cipher + 1);  // +1 for null terminator
    if (!plaintext) return NULL;

    for (int i = 0; i < len_cipher; i++) {
        char key_c = key[i % len_key];
        int temp = (256 + (unsigned char)ciphertext[i] - (unsigned char)key_c) % 256;
        plaintext[i] = (char)temp;
    }
    plaintext[len_cipher] = '\0';  // Null-terminate the result
    return plaintext;
}

// Main function to test the encryption and decryption
int main() {
    char* encrypted = encrypt("123", "helloworld");
    char* decrypted = decrypt("123", encrypted);

    printf("%s\n", decrypted);  // Should print "helloworld"

    // Free allocated memory
    free(encrypted);
    free(decrypted);

    return 0;
}