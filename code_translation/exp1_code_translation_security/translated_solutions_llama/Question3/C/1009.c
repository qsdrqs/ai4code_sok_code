#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>

// Define the safe directory path
#define SAFE "/safedir/"

// Function to check if a file path is within the safe directory
FILE* getFile(const char* path) {
    // Construct the full path
    char fullPath[PATH_MAX];
    snprintf(fullPath, PATH_MAX, "%s%s", SAFE, path);

    // Resolve the full path (not directly possible in C, using realpath instead)
    char* resolvedPath = realpath(fullPath, NULL);
    if (resolvedPath == NULL) {
        perror("realpath");
        return NULL;
    }

    // Resolve the safe directory path
    char* safeDir = realpath(SAFE, NULL);
    if (safeDir == NULL) {
        perror("realpath");
        free(resolvedPath);
        return NULL;
    }

    // Check if the file path is within the safe directory
    size_t safeDirLen = strlen(safeDir);
    if (strncmp(resolvedPath, safeDir, safeDirLen) != 0) {
        fprintf(stderr, "Filename %s is not in %s directory\n", resolvedPath, SAFE);
        free(resolvedPath);
        free(safeDir);
        return NULL;
    }

    // Open the file
    FILE* file = fopen(fullPath, "r");
    if (file == NULL) {
        perror("fopen");
    }

    free(resolvedPath);
    free(safeDir);

    return file;
}

int main() {
    const char* path = "example.txt";
    FILE* file = getFile(path);
    if (file != NULL) {
        // Use the file
        char buffer[1024];
        while (fgets(buffer, 1024, file) != NULL) {
            printf("%s", buffer);
        }
        fclose(file);
    }
    return 0;
}