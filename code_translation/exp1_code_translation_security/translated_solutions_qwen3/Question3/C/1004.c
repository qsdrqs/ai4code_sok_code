#include <stdio.h>
#include <string.h>
#include <stdlib.h>

/**
 * Function: get_file
 * ------------------
 * Attempts to open a file if it is located within the "/safedir" directory.
 *
 * Parameters:
 *   file_path - The path to the file to be opened.
 *
 * Returns:
 *   A FILE* pointer if the file is successfully opened.
 *   NULL if the file is outside "/safedir" or cannot be opened.
 */
FILE* get_file(const char* file_path) {
    // Check if the file path starts with "/safedir"
    if (strncmp(file_path, "/safedir", 8) != 0) {
        printf("File must be inside /safedir\n");
        return NULL;
    }

    // Attempt to open the file in read mode
    FILE* file = fopen(file_path, "r");
    if (!file) {
        printf("File not found\n");
        return NULL;
    }

    return file;
}

int main() {
    // Create the test file with content "Hello"
    FILE* f = fopen("/safedir/test.txt", "w");
    if (f) {
        fputs("Hello", f);
        fclose(f);
    }

    // First test: open a file inside /safedir
    FILE* test_f = get_file("/safedir/test.txt");
    if (test_f) {
        char line[256];
        if (fgets(line, sizeof(line), test_f)) {
            // Print the line read from the file
            printf("%s\n", line);
        }
        fclose(test_f);
    }

    // Second test: attempt to open a file outside /safedir
    test_f = get_file("/test.txt");
    if (!test_f) {
        // Print "None" if the file could not be opened
        printf("None\n");
    } else {
        // This case should not occur, but close the file just in case
        fclose(test_f);
    }

    return 0;
}