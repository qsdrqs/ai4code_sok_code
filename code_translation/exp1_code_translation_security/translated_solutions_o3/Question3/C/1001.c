#include <stdio.h>
#include <string.h>
#include <stdbool.h>

bool checkIfSafe(const char *mystring) {
    const char *prefix = "/safedir";
    size_t prefix_len   = strlen(prefix);

    /* startswith("/safedir")? */
    if (strncmp(mystring, prefix, prefix_len) != 0)
        return false;

    /* contains("/..")? */
    if (strstr(mystring, "/..") != NULL)
        return false;

    return true;
}

FILE *getFile(const char *filepath) {
    return checkIfSafe(filepath) ? fopen(filepath, "r") : NULL;
}