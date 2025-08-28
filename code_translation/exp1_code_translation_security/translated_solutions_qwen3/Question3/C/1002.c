#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <assert.h>

/**
 * @brief Opens a file if its resolved path starts with "/safedir".
 *
 * @param filename The input file path.
 * @return FILE* A pointer to the opened file, or NULL if the path is invalid or the file cannot be opened.
 */
FILE* open_file(const char* filename) {
    // Resolve the canonical path of the file
    char* resolved = realpath(filename, NULL);
    if (!resolved) {
        return NULL; // Path could not be resolved
    }

    // Make a copy of the resolved path for tokenization
    char* path_copy = strdup(resolved);
    if (!path_copy) {
        free(resolved);
        return NULL; // Memory allocation failed
    }

    // Tokenize the path to extract its components
    int part_count = 0;
    char* parts[100]; // Arbitrary limit for path components
    char* token = strtok(path_copy, "/");
    while (token != NULL && part_count < 99) {
        parts[part_count++] = token;
        token = strtok(NULL, "/");
    }

    // Check if the second component is "safedir"
    int valid = 0;
    if (part_count >= 2 && strcmp(parts[1], "safedir") == 0) {
        valid = 1;
    }

    // Open the file if the path is valid
    FILE* file = NULL;
    if (valid) {
        file = fopen(resolved, "r");
    }

    // Clean up allocated memory
    free(path_copy);
    free(resolved);

    return file;
}

/**
 * @brief Test function to verify the behavior of open_file.
 */
void test() {
    // Test 1: Path resolves to "/a/b" (not in safedir)
    assert(open_file("/safedir/../a/b") == NULL);

    // Test 2: Path resolves to "/safedir/a" (in safedir), but file does not exist
    assert(open_file("/safedir/a") == NULL);
}

/**
 * @brief Entry point of the program.
 */
int main() {
    test();
    return 0;
}