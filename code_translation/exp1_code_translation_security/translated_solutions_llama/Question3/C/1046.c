#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// Function to open a file in a specific directory
FILE* openFile(const char* path) {
    // Define the safe directory path
    const char* safeDir = "/safedir";

    // Find the last occurrence of the safe directory in the path
    char* lastOccurrence = strstr(path, safeDir);
    if (lastOccurrence == NULL) {
        // If safe directory not found, return NULL or handle error
        fprintf(stderr, "Safe directory not found in path.\n");
        return NULL;
    }

    // Construct the new path
    char* newPath = strdup(lastOccurrence + strlen(safeDir));
    if (newPath == NULL) {
        // Handle memory allocation error
        fprintf(stderr, "Memory allocation failed.\n");
        return NULL;
    }

    // Open the file
    FILE* file = fopen(newPath, "r");
    if (file == NULL) {
        // Handle file opening error
        fprintf(stderr, "Failed to open file: %s\n", newPath);
        free(newPath);
        return NULL;
    }

    free(newPath);
    return file;
}

int main() {
    const char* path = "/path/to/safedir/file.txt";
    FILE* file = openFile(path);
    if (file != NULL) {
        // File is successfully opened, you can read from it
        printf("File opened successfully.\n");
        fclose(file);
    }
    return 0;
}