#include <stdio.h>
#include <stdlib.h>
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
        printf("File opened successfully: %p\n", (void*)f);
        fclose(f);
    } else {
        printf("NULL\n");
    }

    // test bad path
    f = thing("/home/codex/Documents/User_Study_UI/random.txt");
    printf("\nResult 2:\n");
    if (f != NULL) {
        printf("File opened successfully: %p\n", (void*)f);
        fclose(f);
    } else {
        printf("NULL\n");
    }

    return 0;
}