#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h> // For PATH_MAX
#include <errno.h>  // For errno

// Global constant for the safe directory, equivalent to Python's SAFE_DIR
const char* SAFE_DIR = "/safedir";

/**
 * @brief Opens a file for reading, but only if it resides within SAFE_DIR.
 *
 * This function is a C translation of the provided Python snippet. It ensures
 * that the requested path, after normalization, is safely contained within
 * the SAFE_DIR directory to prevent directory traversal attacks.
 *
 * @param path The path to the file to open.
 * @return A FILE pointer on success, or NULL on failure.
 *         Failure can occur if the path is outside SAFE_DIR, if the path
 *         does not exist, or due to other file access errors.
 */
FILE* get_file(const char* path) {
    // Buffer to store the canonicalized absolute pathname
    char resolved_path[PATH_MAX];

    // realpath() resolves all symlinks, '..' and '.' path components,
    // and returns the absolute path. This is the C equivalent of
    // os.path.normpath() for security checks.
    // It returns NULL if the file does not exist or an error occurs.
    if (realpath(path, resolved_path) == NULL) {
        // We can use perror to print the system error, e.g., "No such file or directory"
        perror("Error resolving path");
        fprintf(stderr, "Failed to resolve path: %s\n", path);
        return NULL;
    }

    // Check if the resolved path starts with SAFE_DIR.
    // strncmp is used to compare the beginning of the string.
    size_t safe_dir_len = strlen(SAFE_DIR);
    if (strncmp(resolved_path, SAFE_DIR, safe_dir_len) != 0) {
        fprintf(stderr, "Error: Path '%s' is not in SAFE_DIR ('%s').\n", resolved_path, SAFE_DIR);
        return NULL;
    }

    // An additional check to prevent accessing paths like "/safedir_impostor".
    // The character after the SAFE_DIR part in the path must be a '/' or
    // the end of the string.
    // We skip this check if SAFE_DIR is just "/" to allow access to "/file".
    if (strcmp(SAFE_DIR, "/") != 0) {
        if (resolved_path[safe_dir_len] != '\0' && resolved_path[safe_dir_len] != '/') {
            fprintf(stderr, "Error: Path '%s' is not in SAFE_DIR ('%s').\n", resolved_path, SAFE_DIR);
            return NULL;
        }
    }

    // If the path is safe, open the file for reading.
    // Note: We open the original 'path' as the Python code does. For enhanced
    // security against TOCTTOU (Time-of-check to time-of-use) race conditions,
    // one might consider opening the 'resolved_path' instead.
    FILE* file = fopen(path, "r");
    if (file == NULL) {
        perror("Error opening file");
        return NULL;
    }

    return file;
}

// --- Example Usage ---
// To compile and run this example on a POSIX system (like Linux or macOS):
// 1. Create the necessary directory and files:
//    mkdir -p /safedir
//    echo "This is a safe file." > /safedir/safe_file.txt
//    echo "This is an unsafe file." > /tmp/unsafe_file.txt
//
// 2. Compile the C code:
//    gcc -o translator_example your_file_name.c
//
// 3. Run the executable:
//    ./translator_example

int main() {
    printf("--- Attempting to open a valid file ---\n");
    // This path is valid and should succeed.
    const char* safe_path = "/safedir/safe_file.txt";
    FILE* f1 = get_file(safe_path);
    if (f1) {
        printf("Successfully opened: %s\n", safe_path);
        fclose(f1);
    } else {
        printf("Failed to open: %s\n", safe_path);
    }

    printf("\n--- Attempting a directory traversal ---\n");
    // This path attempts to escape the safe directory and should fail.
    const char* unsafe_path = "/safedir/../tmp/unsafe_file.txt";
    FILE* f2 = get_file(unsafe_path);
    if (f2) {
        printf("Successfully opened: %s\n", unsafe_path);
        fclose(f2);
    } else {
        printf("Correctly failed to open: %s\n", unsafe_path);
    }
    
    printf("\n--- Attempting to open a non-existent file ---\n");
    // This path is inside the safe directory but doesn't exist.
    const char* non_existent_path = "/safedir/no_such_file.txt";
    FILE* f3 = get_file(non_existent_path);
    if (f3) {
        printf("Successfully opened: %s\n", non_existent_path);
        fclose(f3);
    } else {
        printf("Correctly failed to open: %s\n", non_existent_path);
    }

    return 0;
}