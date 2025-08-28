#include <stdio.h>
#include <string.h>
#include <stdlib.h>

/**
 * Open a file in write mode if the path is within a safe directory.
 *
 * @param filename The path to the file to be opened.
 * @return A FILE pointer if the file can be opened, NULL otherwise.
 */
FILE* open_path(const char* filename) {
    // Define the safe directory
    const char* safe_dir = "/safedir/";

    // Check if the filename starts with the safe directory
    if (strncmp(filename, safe_dir, strlen(safe_dir)) != 0) {
        return NULL;
    }

    // Check for attempts to navigate out of the directory
    if (strstr(filename, "/../") != NULL) {
        return NULL;
    }

    // Open the file in write mode
    return fopen(filename, "w");
}

int main() {
    // Example usage
    FILE* file = open_path("/safedir/Jim");
    if (file != NULL) {
        printf("File opened successfully.\n");
        fclose(file);
    } else {
        printf("Failed to open file.\n");
    }
    return 0;
}