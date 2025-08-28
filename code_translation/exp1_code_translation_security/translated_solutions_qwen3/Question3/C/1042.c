#include <sys/stat.h>
#include <stdio.h>

/**
 * Function: get_file_path
 * ------------------------
 * Checks if the given path is a regular file and opens it in read mode.
 *
 * Parameters:
 *   path - A null-terminated string representing the file path.
 *
 * Returns:
 *   A FILE pointer if the path is a regular file and can be opened in read mode.
 *   NULL otherwise (including if the file exists but cannot be opened).
 */
FILE* get_file_path(const char* path) {
    struct stat st;

    // Check if the path exists and is a regular file
    if (stat(path, &st) == 0 && S_ISREG(st.st_mode)) {
        // Attempt to open the file in read mode
        return fopen(path, "r");
    }

    // Return NULL if the path is not a regular file or if stat failed
    return NULL;
}