#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* encrypt(const char* str, int key) {
    const char alpha[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                          'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                          'u', 'v', 'w', 'x', 'y', 'z'};
    const int alphaLen = 26;
    int strLen = strlen(str);
    char* newString = (char*)malloc((strLen + 1) * sizeof(char));

    for (int i = 0; i < strLen; i++) {
        char c = str[i];
        if (c == ' ') {
            newString[i] = ' ';
            continue;
        }
        /* find the index of c in alpha (equivalent to alpha.index(c)) */
        int alphaIndex = -1;
        for (int j = 0; j < alphaLen; j++) {
            if (alpha[j] == c) {
                alphaIndex = j;
                break;
            }
        }
        printf("%d\n", alphaIndex);
        if (alphaIndex + key > alphaLen - 1) {
            alphaIndex = key - (alphaLen - 1 - alphaIndex);
        }
        newString[i] = alpha[alphaIndex + key];
    }
    newString[strLen] = '\0';
    return newString;
}

void decrypt(const char* str, int key) {
    const char alpha[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                          'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                          'u', 'v', 'w', 'x', 'y', 'z'};
    const int alphaLen = 26;
    int strLen = strlen(str);
    char* newString = (char*)malloc((strLen + 1) * sizeof(char));
    int idx = 0;

    for (int i = 0; i < strLen; i++) {
        char c = str[i];
        if (c == ' ') {
            continue;
        }
        int alphaIndex = -1;
        for (int j = 0; j < alphaLen; j++) {
            if (alpha[j] == c) {
                alphaIndex = j;
                break;
            }
        }
        newString[idx++] = alpha[alphaIndex - key];
    }
    newString[idx] = '\0';
    /* Python version has no return statement (returns None).
       Free the buffer since it's unused. */
    free(newString);
}

int main(void) {
    char* encrypted = encrypt("hello worlz", 3);

    /* Mimic Python's print() of a list: ['a', 'b', ...] */
    printf("[");
    for (int i = 0; encrypted[i] != '\0'; i++) {
        if (i > 0) printf(", ");
        printf("'%c'", encrypted[i]);
    }
    printf("]\n");

    decrypt(encrypted, 3);
    /* Python's decrypt has no return, so print(decrypted) prints "None" */
    printf("None\n");

    free(encrypted);
    return 0;
}