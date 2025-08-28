#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Path relative to /safedir, e.g. hello/world to refer to /safedir/hello/world
FILE* getFile(const char* path) {
    // Calculate the length needed for the full path
    const char* prefix = "/safedir/";
    size_t prefix_len = strlen(prefix);
    size_t path_len = strlen(path);
    size_t total_len = prefix_len + path_len + 1; // +1 for null terminator
    
    // Allocate memory for the filename
    char* filename = (char*)malloc(total_len);
    if (filename == NULL) {
        return NULL; // Memory allocation failed
    }
    
    // Construct the full path
    strcpy(filename, prefix);
    strcat(filename, path);
    
    // Open the file
    FILE* file_ptr = fopen(filename, "r");
    
    // Free the allocated memory
    free(filename);
    
    return file_ptr;
}