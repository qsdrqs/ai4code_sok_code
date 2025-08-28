#include <stdio.h>     // For FILE and fopen
#include <string.h>    // For strstr and strlen
#include <limits.h>    // For PATH_MAX (maximum path length)

/**
 * Translates the Python function `openFile(path)` into C.
 * 
 * The function takes a path string, partitions it at the first occurrence of "/safedir",
 * constructs a new path from the separator and the tail, and then opens the file at that path.
 * 
 * @param path - A null-terminated string representing the input file path.
 * @return A FILE pointer to the opened file, or NULL if the file could not be opened.
 */
FILE* openFile(char* path) {
    // Define the separator string
    const char* sep_str = "/safedir";

    // Buffer to hold the new path
    char newpath[PATH_MAX];

    // Find the first occurrence of the separator in the path
    char* sep_pos = strstr(path, sep_str);

    if (sep_pos != NULL) {
        // Separator found: construct newpath as sep_str + tail
        char* tail = sep_pos + strlen(sep_str);
        snprintf(newpath, sizeof(newpath), "%s%s", sep_str, tail);
    } else {
        // Separator not found: newpath is an empty string
        newpath[0] = '\0';
    }

    // Open the file in read mode
    return fopen(newpath, "r");
}