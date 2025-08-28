#include <stdio.h>      // For file operations (fopen, printf, etc.) and stderr
#include <stdlib.h>     // For realpath(), exit(), EXIT_FAILURE
#include <string.h>     // For strncmp(), strlen()
#include <limits.h>     // For PATH_MAX constant

/**
 * @brief Opens a file only if it resides within the '/safedir' directory.
 * 
 * This function securely checks if the requested file path is located inside
 * the designated safe directory. It resolves both the input path and the safe
 * directory path to their canonical, absolute forms to prevent path traversal
 * attacks.
 * 
 * @param path The path to the file to open.
 * @return A FILE pointer to the opened file in read mode.
 * @note If access is denied or an error occurs, the function prints an error
 *       message to stderr and terminates the program.
 */
FILE* open_file(const char* path) {
    // Define the safe directory
    const char* safe_dir_str = "/safedir";

    // Buffers to hold the canonical absolute paths
    char resolved_path[PATH_MAX];
    char resolved_safe_dir[PATH_MAX];

    // 1. Resolve the safe directory path to its absolute, canonical form.
    // This is the C equivalent of `os.path.abspath('/safedir')`.
    if (realpath(safe_dir_str, resolved_safe_dir) == NULL) {
        // This is a critical configuration error (e.g., /safedir doesn't exist)
        perror("Error resolving safe directory path");
        exit(EXIT_FAILURE);
    }

    // 2. Resolve the user-provided path to its absolute, canonical form.
    // This is the C equivalent of `os.path.abspath(path)`.
    if (realpath(path, resolved_path) == NULL) {
        // This can happen if the file doesn't exist, which we treat as access denied.
        fprintf(stderr, "Access Denied: Path does not exist or cannot be resolved.\n");
        exit(EXIT_FAILURE);
    }

    // 3. Check if the resolved path starts with the resolved safe directory path.
    // This is the C equivalent of `.startswith()`.
    size_t safe_dir_len = strlen(resolved_safe_dir);
    if (strncmp(resolved_path, resolved_safe_dir, safe_dir_len) == 0) {
        // To prevent matching '/safedir-other', we must also check that the
        // character after the match is either a path separator '/' or the
        // end of the string '\0'.
        if (resolved_path[safe_dir_len] == '/' || resolved_path[safe_dir_len] == '\0') {
            // Path is safe, open the file.
            FILE* file_handle = fopen(path, "r");
            if (file_handle == NULL) {
                perror("Error opening file after security check");
                exit(EXIT_FAILURE);
            }
            return file_handle;
        }
    }

    // 4. If the check fails, raise an exception.
    // The C equivalent is to print to stderr and exit with a failure code.
    fprintf(stderr, "Access Denied\n");
    exit(EXIT_FAILURE);
}

int main() {
    // To make this example runnable, you must first create the directory and file:
    // $ mkdir -p /safedir
    // $ echo "Hello from the safe directory!" > /safedir/test.txt

    const char* file_path = "/safedir/test.txt";
    printf("Attempting to open: %s\n", file_path);

    FILE* file_handle = open_file(file_path);

    // In the Python code, `print()` on a file object shows its representation.
    // In C, we can demonstrate success by printing the file's contents.
    printf("File opened successfully. Contents:\n---\n");
    
    int c;
    while ((c = fgetc(file_handle)) != EOF) {
        putchar(c);
    }
    printf("---\n");

    // Always close the file when you're done with it.
    fclose(file_handle);

    // Example of a denied access attempt (uncomment to test)
    /*
    printf("\nAttempting to open forbidden file: /etc/passwd\n");
    open_file("/etc/passwd"); 
    */

    return 0;
}