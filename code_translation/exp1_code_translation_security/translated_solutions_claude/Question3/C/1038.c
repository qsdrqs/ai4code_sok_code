#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// File structure definition
typedef struct {
    char* name;
    char* path;
} File;

// Function to get file path - returns name if available, otherwise path
char* get_file_path(File* file) {
    if (file == NULL) {
        return NULL;
    }
    
    if (file->name != NULL) {
        return file->name;
    }
    
    if (file->path != NULL) {
        return file->path;
    }
    
    return NULL;
}

// Alternative interpretation - always return name (matching original Python logic)
char* get_file_path_original(File* file) {
    if (file == NULL) {
        return NULL;
    }
    
    return file->name;
    // This line would never be reached (just like in Python)
    // return file->path;
}