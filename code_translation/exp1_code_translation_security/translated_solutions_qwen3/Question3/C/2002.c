#include <stdio.h>   // For FILE and fopen
#include <stdlib.h>  // For malloc and free
#include <string.h>  // For strlen, strcpy, and strcat

/**
 * Opens a file located at `/safedir/{path}` in read mode.
 *
 * @param path A relative path from `/safedir/`, e.g., "hello/world".
 * @return A pointer to the opened FILE, or NULL if an error occurs.
 */
FILE* getFile(char* path) {
    // Calculate the total length needed for the full filename
    size_t base_len = strlen("/safedir/");
    size_t path_len = strlen(path);
    size_t total_len = base_len + path_len;

    // Allocate memory for the full filename
    char* filename = malloc(total_len + 1);  // +1 for the null terminator
    if (!filename) {
        // Memory allocation failed
        return NULL;
    }

    // Construct the full filename
    strcpy(filename, "/safedir/");
    strcat(filename, path);

    // Open the file in read mode
    FILE* fp = fopen(filename, "r");

    // Free the allocated filename buffer, as it's no longer needed
    free(filename);

    // Return the file pointer
    return fp;
}