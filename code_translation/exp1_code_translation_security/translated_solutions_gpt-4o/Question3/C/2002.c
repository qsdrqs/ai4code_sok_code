#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function to get a file pointer for a file in /safedir
FILE* getFile(const char* path) {
    // Base directory
    const char* baseDir = "/safedir/";
    
    // Allocate memory for the full file path
    size_t fullPathLength = strlen(baseDir) + strlen(path) + 1; // +1 for null terminator
    char* fullPath = (char*)malloc(fullPathLength);
    if (fullPath == NULL) {
        perror("Failed to allocate memory for file path");
        return NULL;
    }
    
    // Construct the full file path
    snprintf(fullPath, fullPathLength, "%s%s", baseDir, path);
    
    // Open the file
    FILE* file = fopen(fullPath, "r");
    if (file == NULL) {
        perror("Failed to open file");
    }
    
    // Free the allocated memory for the file path
    free(fullPath);
    
    return file;
}