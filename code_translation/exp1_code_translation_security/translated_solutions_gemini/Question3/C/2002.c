#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/**
 * @brief Opens a file within a designated safe directory.
 *
 * This function takes a relative path, prepends it with a hardcoded safe
 * directory ("/safedir/"), and attempts to open the resulting file path.
 * It includes security checks to prevent directory traversal and the use of
 * absolute paths.
 *
 * Path relative to /safedir, e.g. "hello/world" to refer to "/safedir/hello/world".
 *
 * @param path The relative path to the file. Must not contain ".." or be an absolute path.
 * @return A file pointer (FILE*) on success, or NULL on failure.
 *         Failure can occur due to:
 *         - Invalid input (NULL path).
 *         - Security violation (path contains ".." or starts with '/').
 *         - Memory allocation failure.
 *         - File not found or permission errors.
 */
FILE* getFile(const char* path) {
    // --- Security and Input Validation ---

    if (path == NULL) {
        fprintf(stderr, "Error: Provided path is NULL.\n");
        return NULL;
    }

    // Prevent directory traversal attacks (e.g., path = "../../../etc/passwd")
    if (strstr(path, "..") != NULL) {
        fprintf(stderr, "Error: Invalid path. Directory traversal '..' is not allowed.\n");
        return NULL;
    }

    // Prevent absolute paths from being used
    if (path[0] == '/') {
        fprintf(stderr, "Error: Invalid path. Absolute paths are not allowed.\n");
        return NULL;
    }

    // --- Path Construction ---

    const char* base_dir = "/safedir/";
    // Allocate memory for the full path: len("/safedir/") + len(path) + 1 (for null terminator)
    size_t full_path_len = strlen(base_dir) + strlen(path) + 1;
    char* full_path = (char*)malloc(full_path_len);

    if (full_path == NULL) {
        fprintf(stderr, "Error: Memory allocation failed.\n");
        return NULL;
    }

    // Safely construct the full path string
    snprintf(full_path, full_path_len, "%s%s", base_dir, path);

    // --- File Opening ---

    // Open the file in read-only mode ("r")
    FILE* file_handle = fopen(full_path, "r");

    // The full_path buffer is no longer needed after fopen has been called,
    // so we can free it immediately.
    free(full_path);

    if (file_handle == NULL) {
        // Silently fail (or use perror("fopen") for debugging)
        // The caller is responsible for checking if the return value is NULL.
        return NULL;
    }

    return file_handle;
}