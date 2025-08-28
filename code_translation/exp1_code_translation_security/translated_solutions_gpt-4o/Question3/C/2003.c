#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <libgen.h>
#include <limits.h>
#include <errno.h>

FILE* load(const char* path_str) {
    char resolved_path[PATH_MAX];
    
    // Resolve the absolute path
    if (realpath(path_str, resolved_path) == NULL) {
        perror("Error resolving path");
        exit(EXIT_FAILURE);
    }

    // Check if the path is safe
    char* token = strtok(resolved_path, "/");
    if (token == NULL || strcmp(token, "safedir") != 0) {
        fprintf(stderr, "Cannot access unsafe file\n");
        exit(EXIT_FAILURE);
    }

    // Open the file
    FILE* file = fopen(path_str, "r");
    if (file == NULL) {
        perror("Error opening file");
        exit(EXIT_FAILURE);
    }

    return file;
}

int main() {
    FILE* file1 = load("/safedir/foo.txt");
    char buffer[1024];
    size_t n;

    // Read and print the contents of the first file
    while ((n = fread(buffer, 1, sizeof(buffer), file1)) > 0) {
        fwrite(buffer, 1, n, stdout);
    }
    fclose(file1);

    // Attempt to load the second file
    FILE* file2 = load("/tmp/foo.txt");
    // If the program reaches here, it means the file was loaded successfully
    // Read and print the contents of the second file
    while ((n = fread(buffer, 1, sizeof(buffer), file2)) > 0) {
        fwrite(buffer, 1, n, stdout);
    }
    fclose(file2);

    return 0;
}