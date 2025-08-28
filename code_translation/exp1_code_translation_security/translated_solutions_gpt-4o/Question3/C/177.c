#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <libgen.h> // For dirname and basename
#include <unistd.h> // For access

FILE* path2obj(const char* filePath) {
    // Open the file
    FILE* f = fopen(filePath, "r");
    if (!f) {
        return NULL; // Return NULL if the file cannot be opened
    }

    // Make a copy of the filePath to manipulate
    char* pathCopy = strdup(filePath);
    if (!pathCopy) {
        fclose(f);
        return NULL; // Return NULL if memory allocation fails
    }

    // Get the directory name
    char* directory = dirname(pathCopy);

    // Traverse up the directory tree
    while (directory && strcmp(directory, "/") != 0) {
        // Get the base name of the current directory
        char* baseName = basename(directory);

        // Check if the base name is "safedir"
        if (strcmp(baseName, "safedir") == 0) {
            free(pathCopy); // Free the allocated memory
            return f;       // Return the file pointer
        }

        // Move up one directory
        directory = dirname(directory);
    }

    // If "safedir" is not found, clean up and return NULL
    free(pathCopy);
    fclose(f);
    return NULL;
}