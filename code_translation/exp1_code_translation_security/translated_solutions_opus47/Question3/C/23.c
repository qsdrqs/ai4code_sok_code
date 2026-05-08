#include <stdio.h>
#include <string.h>
#include <stdlib.h>

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

int main(void) {
    // test good path
    FILE* f = thing("/home/codex/Documents/User_Study_UI/safedir/test1.txt");
    printf("\nResult 1:\n");
    if (f != NULL) {
        printf("FILE pointer: %p\n", (void*)f);
        fclose(f);
    } else {
        printf("None\n");
    }
    
    // test bad path
    f = thing("/home/codex/Documents/User_Study_UI/random.txt");
    printf("\nResult 2:\n");
    if (f != NULL) {
        printf("FILE pointer: %p\n", (void*)f);
        fclose(f);
    } else {
        printf("None\n");
    }
    
    return 0;
}