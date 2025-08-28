#include <stdio.h>
#include <stdlib.h>
#include <string.h>

FILE* returnFile(const char* path) {
    // Check if path starts with '/safedir'
    if (strncmp(path, "/safedir", 8) != 0) {
        printf("File cannot be accessed\n");
        return NULL;
    }
    
    // Open and return the file
    return fopen(path, "r");
}