#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>

// Function to check if a path is safe
int is_safe_path(const char *path_str) {
    // In C, we don't have a direct equivalent to Python's Path class.
    // For simplicity, we'll assume a safe path starts with "/safedir/".
    return strncmp(path_str, "/safedir/", 10) == 0;
}

// Function to load a file
FILE *load(const char *path_str) {
    // Check if the path is safe
    if (!is_safe_path(path_str)) {
        fprintf(stderr, "Cannot access unsafe file\n");
        exit(EXIT_FAILURE);
    }

    // Open the file
    FILE *file = fopen(path_str, "r");
    if (file == NULL) {
        fprintf(stderr, "Failed to open file: %s\n", path_str);
        exit(EXIT_FAILURE);
    }

    return file;
}

int main() {
    // Load and print the contents of a safe file
    FILE *safe_file = load("/safedir/foo.txt");
    char buffer[1024];
    size_t bytes_read = fread(buffer, 1, sizeof(buffer) - 1, safe_file);
    buffer[bytes_read] = '\0'; // Null-terminate the buffer
    printf("%s", buffer);
    fclose(safe_file);

    // Attempt to load an unsafe file
    load("/tmp/foo.txt");
    // Note: The above call will exit the program if the file is not safe.

    return 0;
}