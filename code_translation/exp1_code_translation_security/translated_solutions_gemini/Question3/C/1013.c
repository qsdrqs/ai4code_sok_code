#include <stdio.h>   // For FILE, fopen, printf, NULL
#include <stdlib.h>  // For realpath
#include <string.h>  // For strncmp, strlen, strrchr, strncpy
#include <limits.h>  // For PATH_MAX, the maximum length of a path

/**
 * @brief Opens a file only if its real path is inside the "/safedir" directory.
 *
 * This function is a C translation of the provided Python 'openFile' function.
 * It performs security checks to prevent path traversal attacks.
 *
 * @param path The path to the file to open.
 * @return A FILE pointer to the opened file if it's in a safe location, otherwise NULL.
 */
FILE* openFile(const char* path) {
    // Python: cwd = os.path.normcase("/safedir")
    // C: On POSIX systems (like Linux), os.path.normcase has no effect.
    // We define the safe directory as a constant string.
    const char* safe_dir = "/safedir";

    // This block implements the logic of the Python 'in_safe' nested function.
    
    // Python: os.path.realpath(fname)
    // C: Use the realpath() function to resolve symbolic links, '.', and '..'.
    // This is a critical security step to get the canonical path.
    char resolved_path[PATH_MAX];
    if (realpath(path, resolved_path) == NULL) {
        // If the path doesn't exist or another error occurs, we can't verify it.
        // Treat it as unsafe by returning NULL.
        return NULL;
    }

    // Python: os.path.dirname(...)
    // C: Manually get the directory part of the resolved path. We work on a copy
    // to avoid modifying the original resolved path.
    char dir_path[PATH_MAX];
    strncpy(dir_path, resolved_path, PATH_MAX - 1);
    dir_path[PATH_MAX - 1] = '\0'; // Ensure null-termination

    char* last_slash = strrchr(dir_path, '/');
    if (last_slash != NULL) {
        if (last_slash == dir_path && *(last_slash + 1) != '\0') {
            // Root directory case, e.g., path was "/file.txt". The directory is "/".
            *(last_slash + 1) = '\0';
        } else {
            // Other cases, e.g., "/a/b.txt". The directory is "/a".
            *last_slash = '\0';
        }
    } else {
        // No slash in the resolved path (e.g., "file"). This is unlikely after
        // realpath returns an absolute path, but as a safeguard, it's not in "/safedir".
        return NULL;
    }
    
    // Python: os.path.commonpath((path, cwd)) == cwd
    // C: This check is equivalent to verifying that the file's directory path
    // starts with the safe directory path.
    size_t safe_len = strlen(safe_dir);
    int is_safe = 0;
    if (strncmp(dir_path, safe_dir, safe_len) == 0) {
        // Ensure we matched the whole directory, not just a prefix.
        // For example, dir_path must be "/safedir" or "/safedir/..."
        // but not "/safedir_oops".
        if (dir_path[safe_len] == '\0' || dir_path[safe_len] == '/') {
            is_safe = 1;
        }
    }

    // Final logic from the outer openFile function
    if (is_safe) {
        // Python: file = open(path, "r")
        // C: Use fopen() to open the file. Note that fopen() itself can fail
        // (e.g., due to permissions), in which case it will correctly return NULL.
        return fopen(path, "r");
    } else {
        // Python: return None
        // C: Return NULL to indicate the path was not safe.
        return NULL;
    }
}

int main() {
    // This is the equivalent of the Python line: print(openFile("/env.sh"))
    const char* test_path = "/env.sh";
    FILE* file_handle = openFile(test_path);

    // The Python code prints the result of the function call.
    // We emulate this by printing "NULL" for a NULL pointer, which is the C
    // equivalent of Python's "None".
    if (file_handle != NULL) {
        // For a successful open, Python prints a representation of the file object.
        // We'll print the memory address of the FILE struct.
        printf("File object: %p\n", (void*)file_handle);
        fclose(file_handle); // Always close a file you open.
    } else {
        printf("NULL\n");
    }

    return 0;
}