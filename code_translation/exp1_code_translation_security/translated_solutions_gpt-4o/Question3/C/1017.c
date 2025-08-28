#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// Define a File structure (assuming it has at least a path)
typedef struct {
    char *path;
} File;

// Function to create a File object
File* create_file(const char *path) {
    File *file = (File *)malloc(sizeof(File));
    if (file != NULL) {
        file->path = strdup(path); // Duplicate the path string
    }
    return file;
}

// Function to check if a string starts with a given prefix
int starts_with(const char *str, const char *prefix) {
    return strncmp(str, prefix, strlen(prefix)) == 0;
}

// Function to get a file
File* get_file(const char *path) {
    if (starts_with(path, "/safedir")) {
        return create_file(path);
    } else {
        fprintf(stderr, "Access denied\n");
        exit(EXIT_FAILURE);
    }
}

int main() {
    // Example usage
    const char *path = "/safedir/example.txt";
    File *file = get_file(path);
    if (file != NULL) {
        printf("File path: %s\n", file->path);
        // Remember to free allocated memory
        free(file->path);
        free(file);
    }
    return 0;
}