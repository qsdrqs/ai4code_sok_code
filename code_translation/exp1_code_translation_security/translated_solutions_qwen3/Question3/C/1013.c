#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <libgen.h>  // For dirname()
#include <errno.h>   // For error handling (optional)

/**
 * Checks if the directory of the given file path is within the safe directory "/safedir".
 *
 * @param fname The file path to check.
 * @return 1 if the file's directory is within "/safedir", 0 otherwise.
 */
int in_safe(char* fname) {
    // Resolve the real path of the file
    char *resolved_fname = realpath(fname, NULL);
    if (!resolved_fname) {
        // Failed to resolve the path (file may not exist or other error)
        return 0;
    }

    // Duplicate the resolved path to use with dirname (which may modify the string)
    char *dir_copy = strdup(resolved_fname);
    if (!dir_copy) {
        free(resolved_fname);
        return 0;
    }

    // Get the directory of the resolved path
    char *dir = dirname(dir_copy);

    // Define the safe directory
    const char *safe_dir = "/safedir";
    size_t safe_len = strlen(safe_dir);

    int result = 0;

    // Check if the directory starts with the safe directory
    if (strncmp(dir, safe_dir, safe_len) == 0) {
        // Ensure the next character is either '/' or the end of the string
        if (dir[safe_len] == '/' || dir[safe_len] == '\0') {
            result = 1;
        }
    }

    // Clean up allocated memory
    free(dir_copy);
    free(resolved_fname);

    return result;
}

/**
 * Opens the file if its directory is within the safe directory.
 *
 * @param path The file path to open.
 * @return A FILE pointer if the file is safe and can be opened, NULL otherwise.
 */
FILE* openFile(char* path) {
    if (in_safe(path)) {
        return fopen(path, "r");
    } else {
        return NULL;
    }
}

/**
 * Main function to test the openFile function.
 */
int main() {
    FILE* f = openFile("/env.sh");
    printf("File pointer: %p\n", (void*)f);
    if (f) {
        fclose(f);
    }
    return 0;
}