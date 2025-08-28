#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define ALPHA_LEN 26

// Function to find the index of a character in the alphabet array
int findIndex(char c, char alpha[]) {
    for (int i = 0; i < ALPHA_LEN; i++) {
        if (alpha[i] == c) {
            return i;
        }
    }
    return -1; // Return -1 if character is not found
}

// Encrypt function
char* encrypt(const char* str, int key) {
    char alpha[ALPHA_LEN] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    int strLen = strlen(str);
    char* newString = (char*)malloc((strLen + 1) * sizeof(char)); // Allocate memory for the new string
    int newIndex;

    for (int i = 0; i < strLen; i++) {
        char c = str[i];
        if (c == ' ') {
            newString[i] = ' '; // Preserve spaces
            continue;
        }
        int alphaIndex = findIndex(c, alpha);
        if (alphaIndex == -1) {
            printf("Error: Character not found in alphabet.\n");
            free(newString);
            return NULL;
        }
        if (alphaIndex + key > ALPHA_LEN - 1) {
            newIndex = key - (ALPHA_LEN - 1 - alphaIndex);
        } else {
            newIndex = alphaIndex + key;
        }
        newString[i] = alpha[newIndex];
    }
    newString[strLen] = '\0'; // Null-terminate the string
    return newString;
}

// Decrypt function
char* decrypt(const char* str, int key) {
    char alpha[ALPHA_LEN] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    int strLen = strlen(str);
    char* newString = (char*)malloc((strLen + 1) * sizeof(char)); // Allocate memory for the new string

    for (int i = 0; i < strLen; i++) {
        char c = str[i];
        if (c == ' ') {
            newString[i] = ' '; // Preserve spaces
            continue;
        }
        int alphaIndex = findIndex(c, alpha);
        if (alphaIndex == -1) {
            printf("Error: Character not found in alphabet.\n");
            free(newString);
            return NULL;
        }
        newString[i] = alpha[alphaIndex - key];
    }
    newString[strLen] = '\0'; // Null-terminate the string
    return newString;
}

int main() {
    char input[] = "hello worlz";
    int key = 3;

    // Encrypt the input string
    char* encrypted = encrypt(input, key);
    if (encrypted != NULL) {
        printf("Encrypted: %s\n", encrypted);
    }

    // Decrypt the encrypted string
    char* decrypted = decrypt(encrypted, key);
    if (decrypted != NULL) {
        printf("Decrypted: %s\n", decrypted);
    }

    // Free allocated memory
    free(encrypted);
    free(decrypted);

    return 0;
}