#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function to get a file based on a given path relative to /safedir
FILE* getFile(const char* path) {
    // Calculate the length of the base path and the provided path
    const char* base_path = "/safedir/";
    size_t base_path_len = strlen(base_path);
    size_t path_len = strlen(path);

    // Allocate memory for the full path
    char* filename = (char*) malloc(base_path_len + path_len + 1);
    if (filename == NULL) {
        // Handle memory allocation failure
        fprintf(stderr, "Memory allocation failed\n");
        return NULL;
    }

    // Construct the full path
    strcpy(filename, base_path);
    strcat(filename, path);

    // Attempt to open the file
    FILE* file = fopen(filename, "r");
    if (file == NULL) {
        // Handle file opening failure
        fprintf(stderr, "Failed to open file: %s\n", filename);
        free(filename);
        return NULL;
    }

    // Free the allocated memory for the filename
    free(filename);

    return file;
}

int main() {
    // Example usage
    const char* path = "hello/world";
    FILE* file = getFile(path);
    if (file != NULL) {
        // File is successfully opened, you can perform operations on it
        fclose(file);
    }
    return 0;
}