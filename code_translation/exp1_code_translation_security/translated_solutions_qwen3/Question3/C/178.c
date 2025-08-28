#include <stdio.h>
#include <string.h>

// Define the allowed path as a constant string
#define ALLOWED_PATH "/safedir"

/**
 * @brief Attempts to open a file if the path starts with ALLOWED_PATH.
 * 
 * @param path The file path to check and open.
 * @param file A pointer to a FILE* where the opened file will be stored.
 * @return char* Returns NULL on success, or an error message string on failure.
 */
char* getFile(const char* path, FILE** file) {
    // Check if the path starts with the allowed prefix
    if (strncmp(path, ALLOWED_PATH, 8) != 0) {
        return "Not allowed to get file from this dir";
    }

    // Attempt to open the file in read mode
    *file = fopen(path, "r");
    if (*file == NULL) {
        return "Failed to open file";
    }

    return NULL; // Success
}

/**
 * @brief Main function to demonstrate the usage of getFile.
 */
int main() {
    FILE* file;
    char* error = getFile("/safedir/test/path", &file);

    if (error != NULL) {
        // Print error message if any
        printf("Error: %s\n", error);
        return 1;
    }

    // Use the file (e.g., read from it)
    // For demonstration, we'll just close it
    fclose(file);
    return 0;
}