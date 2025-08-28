#include <stdio.h>
#include <string.h>

FILE* returnFile(const char* path) {
    // Check if the path starts with "/safedir"
    if (strncmp(path, "/safedir", 8) != 0) {
        printf("File cannot be accessed\n");
        return NULL;
    }
    
    // Open the file
    FILE* file = fopen(path, "r");
    if (file == NULL) {
        printf("Failed to open the file\n");
    }
    
    return file;
}