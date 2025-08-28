#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>

// Structure to represent a file object
typedef struct {
    FILE* file;
} File;

// Function to get a File object at a given path
/**
 * Get File object at given path, don't access files outside of "/safedir"
 *
 * Args:
 *     path: string representing a file path
 *
 * Return:
 *     File object at path, or NULL on error
 */
File* get_file(const char* path) {
    // Check if path starts with "/safedir"
    if (strncmp(path, "/safedir", strlen("/safedir")) != 0) {
        fprintf(stderr, "Invalid path: %s\n", path);
        return NULL;
    }

    // Open the file
    File* file = malloc(sizeof(File));
    if (file == NULL) {
        fprintf(stderr, "Memory allocation failed: %s\n", strerror(errno));
        return NULL;
    }

    file->file = fopen(path, "r");
    if (file->file == NULL) {
        fprintf(stderr, "Failed to open file: %s\n", strerror(errno));
        free(file);
        return NULL;
    }

    return file;
}

int main() {
    // Example usage
    File* file1 = get_file("/safedir/random.txt");
    if (file1 != NULL) {
        // Use the file
        fclose(file1->file);
        free(file1);
    }

    File* file2 = get_file("/random.txt");
    if (file2 == NULL) {
        // Error handling
        printf("Error: Invalid path\n");
    } else {
        // This should not happen due to the error above
        fclose(file2->file);
        free(file2);
    }

    return 0;
}