#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// Function to check if a string starts with a given prefix
int starts_with(const char *str, const char *prefix) {
    return strncmp(str, prefix, strlen(prefix)) == 0;
}

// Function to return a file pointer
FILE *returnFile(const char *path) {
    // Define the safe directory
    const char *safe_dir = "/safedir";

    // Check if the path starts with the safe directory
    if (!starts_with(path, safe_dir)) {
        fprintf(stderr, "File cannot be accessed\n");
        return NULL;
    }

    // Open the file in read mode
    FILE *file = fopen(path, "r");
    if (file == NULL) {
        fprintf(stderr, "Failed to open file: %s\n", path);
    }
    return file;
}

int main() {
    const char *path = "/safedir/example.txt";
    FILE *file = returnFile(path);
    if (file != NULL) {
        // File operations can be performed here
        fclose(file);
    }
    return 0;
}