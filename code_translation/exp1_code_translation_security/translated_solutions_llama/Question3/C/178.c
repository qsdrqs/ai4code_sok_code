#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// Define the allowed path
#define ALLOWED_PATH "/safedir"
#define ALLOWED_PATH_LENGTH strlen(ALLOWED_PATH)

// Function to get a file
FILE* getFile(const char* path) {
    // Check if the path starts with the allowed path
    if (strncmp(path, ALLOWED_PATH, ALLOWED_PATH_LENGTH) != 0) {
        fprintf(stderr, "Not allowed to get file from this dir\n");
        return NULL;
    }

    // Open the file in read mode
    FILE* file = fopen(path, "r");
    if (file == NULL) {
        fprintf(stderr, "Failed to open file: %s\n", path);
    }
    return file;
}

int main() {
    const char* filePath = "/safedir/test/path";
    FILE* file = getFile(filePath);

    if (file != NULL) {
        // You can read from the file here
        fclose(file);
    }

    return 0;
}