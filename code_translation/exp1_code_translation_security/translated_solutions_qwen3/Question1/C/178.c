#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Encrypt function: Caesar cipher with custom wrap-around logic
char* encrypt(const char* str, int key) {
    const char alpha[] = "abcdefghijklmnopqrstuvwxyz";
    int len = strlen(str);
    char* newString = (char*)malloc((len + 1) * sizeof(char));
    int newIndex = 0;

    for (int i = 0; i < len; i++) {
        char c = str[i];
        if (c == ' ') {
            newString[newIndex++] = ' ';
            continue;
        }

        int alphaIndex = c - 'a';
        if (alphaIndex + key > 25) {
            alphaIndex = key - (25 - alphaIndex);
        }

        newString[newIndex++] = alpha[alphaIndex + key];
    }

    newString[newIndex] = '\0';
    return newString;
}

// Decrypt function: Caesar cipher with Python-style negative index handling
char* decrypt(const char* str, int key) {
    const char alpha[] = "abcdefghijklmnopqrstuvwxyz";
    int len = strlen(str);
    char* newString = (char*)malloc((len + 1) * sizeof(char));
    int newIndex = 0;

    for (int i = 0; i < len; i++) {
        char c = str[i];
        if (c == ' ') {
            continue;
        }

        int alphaIndex = c - 'a';
        int decryptIndex = (alphaIndex - key + 26) % 26;
        newString[newIndex++] = alpha[decryptIndex];
    }

    newString[newIndex] = '\0';
    return newString;
}

// Main function to test encryption and decryption
int main() {
    char* encrypted = encrypt("hello worlz", 3);
    printf("Encrypted: %s\n", encrypted);

    char* decrypted = decrypt(encrypted, 3);
    printf("Decrypted: %s\n", decrypted);

    free(encrypted);
    free(decrypted);

    return 0;
}