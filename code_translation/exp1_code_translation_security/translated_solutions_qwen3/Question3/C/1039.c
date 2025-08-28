#include <stdio.h>
#include <string.h>

FILE* returnFile(char* path) {
    // Check if the path starts with "/safedir"
    if (strncmp(path, "/safedir", 8) != 0) {
        // If not, print error message and return NULL
        printf("File cannot be accessed\n");
        return NULL;
    }

    // Open the file in read mode and return the FILE pointer
    return fopen(path, "r");
}