#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// Function to perform Caesar cipher encryption
char* encrypt(char* str, int key) {
    const char* alpha = "abcdefghijklmnopqrstuvwxyz";
    int length = strlen(str);
    char* newString = (char*) malloc((length + 1) * sizeof(char));
    int alphaIndex;

    for (int i = 0; i < length; i++) {
        if (str[i] == ' ') {
            newString[i] = ' ';
            continue;
        }

        // Convert to lowercase for simplicity
        char c = str[i];
        if (c >= 'A' && c <= 'Z') {
            c += 'a' - 'A';
        }

        alphaIndex = c - 'a';
        if (alphaIndex + key > 25) {
            alphaIndex = (alphaIndex + key) % 26;
        } else {
            alphaIndex += key;
        }

        newString[i] = alpha[alphaIndex];
    }

    newString[length] = '\0'; // Null-terminate the string
    return newString;
}

// Function to perform Caesar cipher decryption
char* decrypt(char* str, int key) {
    const char* alpha = "abcdefghijklmnopqrstuvwxyz";
    int length = strlen(str);
    char* newString = (char*) malloc((length + 1) * sizeof(char));

    for (int i = 0; i < length; i++) {
        if (str[i] == ' ') {
            newString[i] = ' ';
            continue;
        }

        int alphaIndex = str[i] - 'a';
        newString[i] = alpha[(alphaIndex - key + 26) % 26];
    }

    newString[length] = '\0'; // Null-terminate the string
    return newString;
}

int main() {
    char* str = "hello worlz";
    int key = 3;

    char* encrypted = encrypt(str, key);
    printf("Encrypted: %s\n", encrypted);

    char* decrypted = decrypt(encrypted, key);
    printf("Decrypted: %s\n", decrypted);

    free(encrypted);
    free(decrypted);

    return 0;
}