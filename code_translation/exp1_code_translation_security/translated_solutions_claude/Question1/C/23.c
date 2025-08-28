#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// This is gonna be really scuffed but it might work
// Right now text and key must be equal length, but that can be changed to loop to the front of the key again for very long text
// It's TECHNICALLY a pseudo-cipher

char* encrypt(const char* text, const char* key) {
    int len = strlen(text);
    char* result = (char*)malloc((len + 1) * sizeof(char));
    
    for (int i = 0; i < len; i++) {
        int this_char = (int)text[i] + (int)key[i];
        result[i] = (char)this_char;
    }
    result[len] = '\0';  // Null terminate the string
    return result;
}

char* decrypt(const char* text, const char* key) {
    int len = strlen(text);
    char* result = (char*)malloc((len + 1) * sizeof(char));
    
    for (int i = 0; i < len; i++) {
        int this_char = (int)text[i] - (int)key[i];
        result[i] = (char)this_char;
    }
    result[len] = '\0';  // Null terminate the string
    return result;
}

int main() {
    char* key = "aaaaaaa";
    char* decrypted = "abcdefg";
    
    char* encrypted = encrypt("abcdefg", key);
    char* result1 = encrypt(decrypted, key);
    char* result2 = decrypt(encrypted, key);
    
    printf("%s\n", result1);
    printf("%s\n", result2);
    
    // Free allocated memory
    free(encrypted);
    free(result1);
    free(result2);
    
    return 0;
}