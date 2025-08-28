#include <stdio.h>
#include <string.h>
#include <errno.h>

// Simulate the Python exceptions using errno values.
// PathNotAllowed maps to EACCES.
// FileNotFoundError maps to ENOENT.

/**
 * @brief Opens a file if the path starts with "/safedir".
 * 
 * @param path The file path to check and open.
 * @return FILE* A pointer to the opened file, or NULL on error.
 */
FILE* getFilePath(const char* path) {
    // Check if the path starts with "/safedir"
    if (strncmp(path, "/safedir", 8) != 0) {
        errno = EACCES;  // Simulate PathNotAllowed exception
        return NULL;
    }

    // Attempt to open the file
    FILE* file = fopen(path, "r");
    return file;
}

int main() {
    // Define the list of valid file paths
    const char* goodPaths[] = {
        "/safedir/test1.txt",
        "/safedir/test.txt"
    };

    // Test 1: Open and close all good paths
    for (int i = 0; i < 2; ++i) {
        FILE* f = getFilePath(goodPaths[i]);
        if (f != NULL) {
            fclose(f);
        }
    }

    // Test 2: Try to open a file that does not exist
    FILE* f = getFilePath("/safedir/noexist.txt");
    if (f == NULL && errno == ENOENT) {
        // Expected FileNotFoundError, ignore
    }
    // Finally block: close the file if it was opened
    if (f != NULL) {
        fclose(f);
    }

    // Test 3: Try to open a file outside the allowed path
    f = getFilePath("not/a/good/path.txt");
    if (f == NULL && errno == EACCES) {
        // Expected PathNotAllowed, ignore
    }
    // Finally block: close the file if it was opened
    if (f != NULL) {
        fclose(f);
    }

    return 0;
}