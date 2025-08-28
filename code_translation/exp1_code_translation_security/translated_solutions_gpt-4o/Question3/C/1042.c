#include <stdio.h>
#include <stdlib.h>
#include <sys/stat.h>

// Function to check if a file exists and is a regular file
int is_file(const char *path) {
    struct stat path_stat;
    if (stat(path, &path_stat) != 0) {
        return 0; // File does not exist
    }
    return S_ISREG(path_stat.st_mode); // Check if it's a regular file
}

// Function to get the file pointer if the file exists
FILE* get_file_path(const char *path) {
    if (is_file(path)) {
        return fopen(path, "r"); // Open the file in read mode
    } else {
        return NULL; // File does not exist or is not a regular file
    }
}