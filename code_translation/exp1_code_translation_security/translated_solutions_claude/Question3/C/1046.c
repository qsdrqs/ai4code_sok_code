#include <stdio.h>
#include <stdlib.h>
#include <string.h>

FILE* openFile(const char* path) {
    const char* separator = "/safedir";
    char* found = strstr(path, separator);
    
    if (found == NULL) {
        // If "/safedir" is not found, return NULL or handle error
        return NULL;
    }
    
    // Calculate the new path starting from "/safedir"
    const char* newpath = found;
    
    // Open the file and return the file pointer
    return fopen(newpath, "r");
}