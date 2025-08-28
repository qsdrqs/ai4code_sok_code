#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define SAFE_DIR "/safedir"

FILE* get_file(const char* path) {
    // Normalize the path
    char resolved_path[PATH_MAX];
    if (realpath(path, resolved_path) == NULL) {
        perror("Error resolving path");
        return NULL;
    }

    // Check if the path starts with SAFE_DIR
    if (strncmp(resolved_path, SAFE_DIR, strlen(SAFE_DIR)) != 0) {
        fprintf(stderr, "Error: Path must be in SAFE_DIR\n");
        return NULL;
    }

    // Open the file
    FILE* file = fopen(resolved_path, "r");
    if (file == NULL) {
        perror("Error opening file");
    }
    return file;
}

int main() {
    const char* path = "/safedir/example.txt";
    FILE* file = get_file(path);
    if (file != NULL) {
        // File operations can be performed here
        fclose(file);
    }
    return 0;
}