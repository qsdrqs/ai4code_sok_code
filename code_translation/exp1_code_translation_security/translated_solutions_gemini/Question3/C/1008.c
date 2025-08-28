#include <stdio.h>  // For file operations (fopen, FILE*, fclose) and I/O (printf, fprintf)
#include <string.h> // For string manipulation (strstr)
#include <stdlib.h> // For exit codes (EXIT_SUCCESS, EXIT_FAILURE)

/**
 * @brief Attempts to open a file if its path is within the "/safedir" directory.
 *
 * This function mimics the Python version's logic. It checks if the substring
 * "/safedir" exists in the provided path.
 *
 * @param path The full path to the file.
 * @return A FILE pointer on success, or NULL on failure (either due to an
 *         invalid path or if fopen itself fails).
 */
FILE* test(const char* path) {
    // In C, we use strstr() to check if a string contains a substring.
    // strstr returns a pointer to the beginning of the substring, or NULL if not found.
    if (strstr(path, "/safedir") != NULL) {
        // The equivalent of Python's open() is fopen().
        // We open in "r" (read) mode, which is the default for Python's open().
        // Note: fopen will return NULL if the file doesn't exist or can't be opened.
        // The original Python code would also raise an exception in that case.
        return fopen(path, "r");
    }

    // C does not have exceptions. The standard way to signal an error is to
    // return a special value (like NULL) and print an error message.
    fprintf(stderr, "Error: no access to path \"%s\"\n", path);
    return NULL;
}

int main(void) {
    // To run this example successfully, you would need a file at "/safedir/junk".
    // We will proceed assuming it exists for the demonstration.
    // If it doesn't, fopen() inside test() will return NULL, and our error
    // handling below will catch it.

    // The commented-out Python line:
    // FILE* j = test("/junk"); // This call would fail and print "Error: no access..."

    // The active Python line:
    FILE* k = test("/safedir/junk");

    // In C, it is crucial to check the return value of functions that can fail.
    if (k == NULL) {
        // This block will execute if test() returned NULL, either because of our
        // "no access" rule or because fopen() itself failed.
        fprintf(stderr, "main: Failed to get a valid file handle.\n");
        return EXIT_FAILURE; // Exit with an error code.
    }

    // If we get here, the file was opened successfully.
    printf("Successfully opened file: /safedir/junk\n");

    // In C, you must manually close any file you open to free system resources.
    // Python does this automatically when the file object is garbage collected.
    fclose(k);

    return EXIT_SUCCESS; // Exit successfully.
}