#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>
#include <errno.h>

FILE* return_object(const char* path) {
    char rpath[PATH_MAX];
    
    // Get the real path
    if (realpath(path, rpath) == NULL) {
        perror("Error resolving real path");
        return NULL;
    }

    // Check if the path starts with "/safedir"
    if (strncmp(rpath, "/safedir", 8) == 0) {
        return (FILE*)-1;
    }

    // Try to open the file
    FILE* fp = fopen(path, "w");
    if (fp == NULL) {
        perror("Error opening file");
        return NULL;
    }

    return fp;
}

int main() {
    const char* file_name = "/tmp/foo";
    FILE* file_pointer = return_object(file_name);

    if (file_pointer == (FILE*)-1) {
        printf("Path is not allowed.\n");
    } else if (file_pointer == NULL) {
        printf("Failed to open file.\n");
    } else {
        printf("File opened successfully.\n");
        fclose(file_pointer);
    }

    return 0;
}