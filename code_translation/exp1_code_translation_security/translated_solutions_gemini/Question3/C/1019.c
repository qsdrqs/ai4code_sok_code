#include <stdio.h>
#include <stdlib.h> // For realpath(), system()
#include <string.h> // For strncmp(), strlen()
#include <limits.h> // For PATH_MAX

// A helper function to read the entire content of a file stream and print it.
// This mimics the behavior of `print(f.read())`.
void read_and_print_file(FILE* f) {
    if (f == NULL) {
        // fopen might fail, e.g., due to permissions.
        perror("Error opening file for reading");
        return;
    }

    int c;
    // Read character by character until the end of the file (EOF).
    while ((c = fgetc(f)) != EOF) {
        putchar(c); // Print the character to stdout.
    }
    printf("\n"); // Add a newline for clean output, similar to print().

    fclose(f); // Always close the file when done.
}

/**
 * @brief Gets a file handle, but only for paths inside "/safedir".
 *
 * This function normalizes the input path to prevent traversal attacks.
 * If the normalized path is not inside "/safedir", it returns a handle
 * to "/dev/null".
 *
 * @param path The file path to open.
 * @return A FILE pointer to the requested file or to "/dev/null".
 */
FILE* get_file(const char* path) {
    // Use realpath() to normalize the path. It resolves ".." and "."
    // and symbolic links, making it a strong defense against traversal.
    // Note: realpath requires the path to exist, unlike Python's os.path.normpath.
    // This makes the C version safer, as it won't attempt to open a malformed
    // path that passes the string check but is invalid.
    char resolved_path[PATH_MAX];
    char* normpath = realpath(path, resolved_path);

    // If realpath fails (e.g., file not found), normpath will be NULL.
    // We treat this as "out of bounds" for safety.
    if (normpath == NULL) {
        // We print the original path for debugging, as the normalized one is not available.
        printf("%s\n", path);
        printf("out of bounds\n");
        return fopen("/dev/null", "r");
    }

    printf("%s\n", normpath);

    // The security check: does the normalized path start with "/safedir"?
    // We use strncmp for a safe, bounded comparison.
    const char* safe_dir = "/safedir";
    if (strncmp(normpath, safe_dir, strlen(safe_dir)) != 0) {
        printf("out of bounds\n");
        return fopen("/dev/null", "r");
    } else {
        printf("opening file %s\n", normpath);
        // Python's "r+" mode translates to "r+" in C (read and write).
        return fopen(normpath, "r+");
    }
}

int main() {
    // --- Setup: Create a dummy directory and file for the test ---
    // This is necessary for realpath() to succeed on the valid path.
    system("mkdir -p /safedir");
    FILE* temp_file = fopen("/safedir/test1.txt", "w");
    if (temp_file != NULL) {
        fprintf(temp_file, "This is a safe file.");
        fclose(temp_file);
    }
    // --- End Setup ---

    printf("--- Test Case 1: Safe Path ---\n");
    FILE* f = get_file("/safedir/test1.txt");
    read_and_print_file(f);

    printf("\n--- Test Case 2: Unsafe Path ---\n");
    // This path is unsafe and does not exist. realpath() will fail.
    FILE* g = get_file("/dev/rand0");
    read_and_print_file(g);
    
    // --- Cleanup: Remove the dummy directory and file ---
    system("rm -rf /safedir");
    // --- End Cleanup ---

    return 0;
}