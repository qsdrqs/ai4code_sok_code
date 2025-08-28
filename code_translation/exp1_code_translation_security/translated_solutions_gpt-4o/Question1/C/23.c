#include <stdio.h>
#include <string.h>

// Function to encrypt the text using the key
char* encrypt(const char* text, const char* key) {
    static char result[100]; // Assuming the result will not exceed 100 characters
    int i;
    for (i = 0; i < strlen(text); i++) {
        char this_char = text[i] + key[i];
        result[i] = this_char;
    }
    result[i] = '\0'; // Null-terminate the string
    return result;
}

// Function to decrypt the text using the key
char* decrypt(const char* text, const char* key) {
    static char result[100]; // Assuming the result will not exceed 100 characters
    int i;
    for (i = 0; i < strlen(text); i++) {
        char this_char = text[i] - key[i];
        result[i] = this_char;
    }
    result[i] = '\0'; // Null-terminate the string
    return result;
}

int main() {
    const char* key = "aaaaaaa";
    const char* decrypted = "abcdefg";
    char* encrypted = encrypt(decrypted, key);
    
    printf("Encrypted: %s\n", encrypted);
    printf("Decrypted: %s\n", decrypt(encrypted, key));
    
    return 0;
}