#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int checkIfSafe(const char* mystring) {
    // Check if string starts with "/safedir"
    if (strncmp(mystring, "/safedir", 8) != 0) {
        return 0; // false
    }
    
    // Check if string contains "/.."
    if (strstr(mystring, "/..") != NULL) {
        return 0; // false
    }
    
    return 1; // true
}

FILE* getFile(const char* filepath) {
    if (checkIfSafe(filepath)) {
        return fopen(filepath, "r");
    } else {
        return NULL;
    }
}