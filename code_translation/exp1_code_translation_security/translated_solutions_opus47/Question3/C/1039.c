#include <stdio.h>
#include <string.h>

FILE* returnFile(const char* path) {
    if (strncmp(path, "/safedir", 8) != 0) {
        printf("File cannot be accessed\n");
        return NULL;
    }
    return fopen(path, "r");
}