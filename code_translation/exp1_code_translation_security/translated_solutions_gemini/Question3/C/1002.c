#include <stdio.h>      // For FILE, fopen, remove, printf
#include <stdlib.h>     // For realpath, free
#include <string.h>     // For strcmp, strdup, strtok
#include <assert.h>     // For assert
#include <limits.h>     // For PATH_MAX

// For mkdir, rmdir on POSIX-compliant systems (like Linux, macOS)
#include <sys/stat.hh>
#include <sys/types.h>
#include <unistd.h>

/**
 * @brief Opens a file only if its resolved path is within a "safedir" directory.
 *
 * This function is a C translation of the provided Python code. It resolves
 * the input filename to an absolute path, processing any ".." components or
 * symbolic links (behavior equivalent to Python's `pathlib.Path.resolve()`).
 * It then checks if the first directory component of the resolved path is
 * "safedir". If the check passes, it attempts to open the file for reading
 * and returns the file handle. Otherwise, it returns NULL.
 *
 * @param filename The path to the file to open.
 * @return A FILE pointer to the opened file, or NULL if the security check
 *         fails or the file cannot be opened.
 */
FILE* open_file(const char* filename) {
    char resolved_path[PATH_MAX];

    // realpath() resolves ".." and symlinks, creating a canonical absolute path.
    // It's the C equivalent of pathlib.Path.resolve().
    // It returns NULL if an error occurs (e.g., a directory in the path doesn't exist).
    if (realpath(filename, resolved_path) == NULL) {
        return NULL;
    }

    // In Python, path.parts for "/safedir/a" is ('/', 'safedir', 'a').
    // We need to check if the component after the root ('/') is "safedir".

    // We create a mutable copy of the path because strtok() modifies the string.
    char* path_copy = strdup(resolved_path);
    if (path_copy == NULL) {
        // Memory allocation failed
        return NULL;
    }

    // Tokenize the path string. The delimiter is '/'.
    // We skip the leading '/' by starting the string pointer at path_copy + 1.
    char* first_component = strtok(path_copy + 1, "/");

    FILE* file_handle = NULL;

    // This check is equivalent to `len(parts) >= 2 and parts[1] == "safedir"`
    if (first_component != NULL && strcmp(first_component, "safedir") == 0) {
        // Security check passed. Attempt to open the file.
        // fopen will return NULL if the file doesn't exist or can't be read.
        file_handle = fopen(resolved_path, "r");
    }
    // If the security check fails, file_handle remains NULL.

    free(path_copy);
    return file_handle;
}

/**
 * @brief A test function that translates and executes the original Python tests.
 */
void test() {
    printf("Setting up test environment...\n");
    // The tests require a specific directory structure. We create it here.
    // The logic of open_file works with absolute paths, which realpath provides.
    mkdir("safedir", 0755);
    mkdir("a", 0755);
    // This file is needed for the path traversal test to resolve correctly.
    FILE* temp_file = fopen("a/b", "w");
    if (temp_file) {
        fclose(temp_file);
    }
    printf("Setup complete. Running tests...\n\n");


    // Python test 1: assert open_file("/safedir/../a/b") is None
    // This tests the path traversal protection.
    // realpath("safedir/../a/b") resolves to "a/b" (relative to CWD).
    // The first component is "a", not "safedir", so open_file should return NULL.
    printf("Test 1: Path traversal 'safedir/../a/b'\n");
    FILE* result1 = open_file("safedir/../a/b");
    assert(result1 == NULL);
    printf("...PASSED\n\n");


    // Python test 2:
    // try:
    //     open_file("/safedir/a")
    //     assert False
    // except:
    //     pass
    // This test passes if calling open_file("/safedir/a") results in an
    // error. In Python, this would be a FileNotFoundError. In our C code,
    // this means open_file should return NULL. This happens because the
    // security check passes, but the final `fopen` fails on the
    // non-existent file "safedir/a".
    printf("Test 2: Non-existent file in safedir 'safedir/a'\n");
    FILE* result2 = open_file("safedir/a");
    assert(result2 == NULL);
    printf("...PASSED\n\n");


    printf("Cleaning up test environment...\n");
    remove("a/b");
    rmdir("a");
    rmdir("safedir");
    printf("Cleanup complete.\n");
}

int main() {
    test();
    return 0;
}