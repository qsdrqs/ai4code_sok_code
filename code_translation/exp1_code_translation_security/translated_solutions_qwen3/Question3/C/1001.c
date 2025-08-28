#include <stdio.h>
#include <string.h>
#include <stdbool.h>

/**
 * Checks if the given string starts with "/safedir" and does not contain "/.."
 * @param mystring - The input string to check
 * @return true if the string is safe, false otherwise
 */
bool checkIfSafe(const char* mystring) {
    // Check if the string starts with "/safedir"
    if (strncmp(mystring, "/safedir", 8) != 0) {
        return false;
    }

    // Check if the string contains "/.."
    if (strstr(mystring, "/..") != NULL) {
        return false;
    }

    return true;
}

/**
 * Opens the file if the path is safe
 * @param filepath - The path to the file
 * @return FILE* if the file is opened successfully, NULL otherwise
 */
FILE* getFile(const char* filepath) {
    if (checkIfSafe(filepath)) {
        return fopen(filepath, "r");
    } else {
        return NULL;
    }
}