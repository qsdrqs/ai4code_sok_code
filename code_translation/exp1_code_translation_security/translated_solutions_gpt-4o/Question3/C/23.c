#include <stdio.h>
#include <string.h>

FILE* thing(const char* path) {
    if (strstr(path, "/safedir") != NULL) {
        // continue
        return fopen(path, "r");
    } else {
        // panic
        printf("invalid path\n");
        return NULL;
    }
}

int main() {
    // test good path
    FILE* f = thing("/home/codex/Documents/User_Study_UI/safedir/test1.txt");
    printf("\nResult 1:\n");
    if (f != NULL) {
        printf("File opened successfully.\n");
        fclose(f);
    } else {
        printf("Failed to open file.\n");
    }

    // test bad path
    f = thing("/home/codex/Documents/User_Study_UI/random.txt");
    printf("\nResult 2:\n");
    if (f != NULL) {
        printf("File opened successfully.\n");
        fclose(f);
    } else {
        printf("Failed to open file.\n");
    }

    return 0;
}