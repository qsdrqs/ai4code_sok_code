#include <stdio.h>   // For FILE and fopen
#include <string.h>  // For strstr

/**
 * Reads a file if the path contains "/safedir".
 *
 * @param path The file path as a null-terminated string.
 * @return A pointer to the opened file, or NULL if "/safedir" is not in the path.
 */
FILE* read_in(const char* path) {
    if (strstr(path, "/safedir") != NULL) {
        return fopen(path, "r");
    } else {
        return NULL;
    }
}