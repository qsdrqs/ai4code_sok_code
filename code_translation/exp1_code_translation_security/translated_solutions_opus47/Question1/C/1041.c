#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* encrypt(const char* string, int key) {
    int len = strlen(string);
    char* new_string = (char*)malloc((len + 1) * sizeof(char));
    for (int i = 0; i < len; i++) {
        new_string[i] = string[i] + key;
    }
    new_string[len] = '\0';
    return new_string;
}

char* decrypt(const char* string, int key) {
    int len = strlen(string);
    char* new_string = (char*)malloc((len + 1) * sizeof(char));
    for (int i = 0; i < len; i++) {
        new_string[i] = string[i] - key;
    }
    new_string[len] = '\0';
    return new_string;
}

int main() {
    char* msg = encrypt("this is cool if it really works", 2);
    char* res = decrypt(msg, 2);
    printf("%s\n", msg);
    printf("%s\n", res);
    free(msg);
    free(res);
    return 0;
}