#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define ALLOWED_PATH "/safedir"

FILE* getFile(const char* path) {
    if (strncmp(path, ALLOWED_PATH, 8) != 0) {
        printf("Not allowed to get file from this dir\n");
        return NULL;
    }
    return fopen(path, "r");
}

int main(void) {
    FILE* file = getFile("/safedir/test/path");
    if (file != NULL) {
        fclose(file);
    }
    return 0;
}