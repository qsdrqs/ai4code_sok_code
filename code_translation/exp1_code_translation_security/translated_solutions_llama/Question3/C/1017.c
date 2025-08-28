#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <exception.h>

// Define a struct to represent a File
typedef struct {
    char* path;
} File;

// Function to create a new File
File* file_new(const char* path) {
    File* file = malloc(sizeof(File));
    file->path = malloc(strlen(path) + 1);
    strcpy(file->path, path);
    return file;
}

// Function to get a file based on the provided path
File* get_file(const char* path) {
    // Check if the path starts with '/safedir'
    if (strncmp(path, "/safedir", strlen("/safedir")) == 0) {
        // If it does, return a new File
        return file_new(path);
    } else {
        // If not, raise an exception
        fprintf(stderr, "Access denied\n");
        exit(EXIT_FAILURE);
    }
}

int main() {
    // Example usage
    const char* safe_path = "/safedir/example.txt";
    const char* unsafe_path = "/unsafe/example.txt";

    File* safe_file = get_file(safe_path);
    if (safe_file != NULL) {
        printf("File path: %s\n", safe_file->path);
        free(safe_file->path);
        free(safe_file);
    }

    // This will print "Access denied" and exit
    // get_file(unsafe_path);

    return 0;
}