#include <stdio.h>
#include <string.h>

/**
 * Function: thing
 * ----------------
 * Checks if the given path contains the substring "/safedir".
 * If it does, opens the file in read mode and returns the FILE pointer.
 * Otherwise, prints "invalid path" and returns NULL.
 *
 * Parameters:
 *   path - A null-terminated string representing the file path.
 *
 * Returns:
 *   FILE* - A pointer to the opened file, or NULL if the path is invalid.
 */
FILE* thing(char* path) {
    // Check if the path contains "/safedir"
    if (strstr(path, "/safedir") != NULL) {
        // Open the file in read mode
        return fopen(path, "r");
    } else {
        // Print error message and return NULL
        printf("invalid path\n");
        return NULL;
    }
}

/**
 * Function: main
 * ----------------
 * Tests the `thing` function with two different file paths:
 *   - A valid path containing "/safedir"
 *   - An invalid path without "/safedir"
 *
 * Prints the result of each test to the console.
 */
int main() {
    FILE* f;

    // Test with a valid path
    f = thing("/home/codex/Documents/User_Study_UI/safedir/test1.txt");
    printf("\nResult 1:\n");
    if (f != NULL) {
        printf("File opened successfully\n");
        fclose(f);  // Always close the file after use
    } else {
        printf("NULL\n");
    }

    // Test with an invalid path
    f = thing("/home/codex/Documents/User_Study_UI/random.txt");
    printf("\nResult 2:\n");
    if (f != NULL) {
        printf("File opened successfully\n");
        fclose(f);  // Always close the file after use
    } else {
        printf("NULL\n");
    }

    return 0;
}