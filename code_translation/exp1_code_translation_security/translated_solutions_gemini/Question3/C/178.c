#include <stdio.h>
#include <string.h> // Required for string functions like strncmp and strlen

// Define the allowed path as a constant, similar to the Python version.
#define ALLOWED_PATH "/safedir"

/**
 * @brief Attempts to open a file if it resides within the ALLOWED_PATH.
 *
 * @param path The full path to the file to be opened.
 * @return A FILE pointer on success, or NULL on failure (either due to
 *         security restrictions or if the file cannot be opened).
 */
FILE* getFile(const char* path) {
    // In C, we use strncmp to compare the beginning of a string.
    // We get the length of ALLOWED_PATH to know how many characters to compare.
    const size_t allowed_len = strlen(ALLOWED_PATH);

    // First, ensure the path is long enough to even contain the prefix.
    // This prevents reading out of bounds on short path strings.
    if (strlen(path) < allowed_len) {
        fprintf(stderr, "Error: Not allowed to get file from this dir (path too short).\n");
        return NULL;
    }

    // Compare the first 'allowed_len' characters of the path with ALLOWED_PATH.
    // strncmp returns 0 if they are equal.
    if (strncmp(path, ALLOWED_PATH, allowed_len) != 0) {
        fprintf(stderr, "Error: Not allowed to get file from this dir.\n");
        return NULL; // Return NULL to indicate failure.
    }

    // If the path is valid, attempt to open the file in read mode ("r").
    FILE* file_handle = fopen(path, "r");
    if (file_handle == NULL) {
        // If fopen fails (e.g., file not found), print an error.
        // perror is useful as it also prints the system error message.
        perror("Error opening file");
        return NULL;
    }

    return file_handle;
}

int main() {
    printf("Attempting to open a valid file...\n");
    // This call corresponds to getFile("/safedir/test/path")
    // We assume a file exists at this path for the example to succeed.
    // For demonstration, let's create one.
    system("mkdir -p /safedir/test");
    system("echo 'hello world' > /safedir/test/path");

    FILE* f1 = getFile("/safedir/test/path");

    if (f1 != NULL) {
        printf("Successfully opened file. Closing it now.\n");
        fclose(f1); // Always close a file when you are done with it.
    } else {
        printf("Failed to get file handle.\n");
    }

    printf("\nAttempting to open an invalid file...\n");
    FILE* f2 = getFile("/etc/passwd");
    if (f2 != NULL) {
        printf("Successfully opened file. This should not have happened!\n");
        fclose(f2);
    } else {
        printf("Correctly blocked access to the file.\n");
    }

    return 0;
}