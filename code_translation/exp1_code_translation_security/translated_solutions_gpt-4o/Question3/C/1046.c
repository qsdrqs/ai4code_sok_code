#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// Function to open a file
FILE* openFile(const char* path) {
    const char* keyword = "/safedir";
    char* position = strstr(path, keyword); // Find the position of "/safedir" in the path

    if (position == NULL) {
        // If "/safedir" is not found, return NULL or handle the error
        fprintf(stderr, "Error: '/safedir' not found in the path.\n");
        return NULL;
    }

    // Create a new path starting from "/safedir"
    char* newpath = strdup(position); // Duplicate the substring starting from "/safedir"
    if (newpath == NULL) {
        fprintf(stderr, "Error: Memory allocation failed.\n");
        return NULL;
    }

    // Open the file
    FILE* file = fopen(newpath, "r");
    if (file == NULL) {
        fprintf(stderr, "Error: Could not open file at path: %s\n", newpath);
    }

    free(newpath); // Free the allocated memory for the new path
    return file;
}