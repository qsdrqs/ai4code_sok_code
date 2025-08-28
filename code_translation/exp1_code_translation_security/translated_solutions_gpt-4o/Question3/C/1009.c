#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <libgen.h>
#include <limits.h>
#include <unistd.h>

#define SAFE "/safedir/"

FILE* getFile(const char* path) {
    char resolved_path[PATH_MAX];
    char safe_resolved[PATH_MAX];

    // Resolve the SAFE directory path
    if (realpath(SAFE, safe_resolved) == NULL) {
        perror("Error resolving SAFE directory");
        exit(EXIT_FAILURE);
    }

    // Construct the full path and resolve it
    char full_path[PATH_MAX];
    snprintf(full_path, sizeof(full_path), "%s%s", SAFE, path);

    if (realpath(full_path, resolved_path) == NULL) {
        perror("Error resolving file path");
        exit(EXIT_FAILURE);
    }

    // Check if the resolved path is within the SAFE directory
    if (strstr(resolved_path, safe_resolved) != resolved_path) {
        fprintf(stderr, "Filename %s is not in %s directory\n", resolved_path, SAFE);
        exit(EXIT_FAILURE);
    }

    // Open the file for reading
    FILE* file = fopen(full_path, "r");
    if (file == NULL) {
        perror("Error opening file");
        exit(EXIT_FAILURE);
    }

    return file;
}

int main() {
    // Example usage
    FILE* file = getFile("example.txt");
    if (file) {
        // Do something with the file
        fclose(file);
    }
    return 0;
}