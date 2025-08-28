#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
 * encrypt with key
 */
char* encrypt(const char* key, const char* plaintext) {
    int plaintext_len = strlen(plaintext);
    int key_len = strlen(key);
    
    // Allocate memory for ciphertext (including null terminator)
    char* ciphertext = (char*)malloc((plaintext_len + 1) * sizeof(char));
    if (ciphertext == NULL) {
        return NULL; // Memory allocation failed
    }
    
    for (int i = 0; i < plaintext_len; i++) {
        char key_c = key[i % key_len];
        char cipher_c = (char)((plaintext[i] + key_c) % 256);
        ciphertext[i] = cipher_c;
    }
    
    ciphertext[plaintext_len] = '\0'; // Null terminate the string
    return ciphertext;
}

/*
 * decrypt with key
 */
char* decrypt(const char* key, const char* ciphertext) {
    int ciphertext_len = strlen(ciphertext);
    int key_len = strlen(key);
    
    // Allocate memory for plaintext (including null terminator)
    char* plaintext = (char*)malloc((ciphertext_len + 1) * sizeof(char));
    if (plaintext == NULL) {
        return NULL; // Memory allocation failed
    }
    
    for (int i = 0; i < ciphertext_len; i++) {
        char key_c = key[i % key_len];
        char plain_c = (char)((256 + ciphertext[i] - key_c) % 256);
        plaintext[i] = plain_c;
    }
    
    plaintext[ciphertext_len] = '\0'; // Null terminate the string
    return plaintext;
}

int main() {
    const char* key = "123";
    const char* original = "helloworld";
    
    // Encrypt the message
    char* encrypted = encrypt(key, original);
    if (encrypted == NULL) {
        printf("Encryption failed\n");
        return 1;
    }
    
    // Decrypt the message
    char* decrypted = decrypt(key, encrypted);
    if (decrypted == NULL) {
        printf("Decryption failed\n");
        free(encrypted);
        return 1;
    }
    
    // Print the result
    printf("%s\n", decrypted);
    
    // Free allocated memory
    free(encrypted);
    free(decrypted);
    
    return 0;
}