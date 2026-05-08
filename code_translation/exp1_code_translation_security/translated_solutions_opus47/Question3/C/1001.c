#include <stdio.h>
#include <string.h>
#include <stdbool.h>

bool checkIfSafe(const char *mystring) {
    const char *prefix = "/safedir";
    /* Check if mystring starts with "/safedir" */
    if (strncmp(mystring, prefix, strlen(prefix)) != 0) {
        return false;
    }
    /* Check that "/.." is not contained in mystring */
    if (strstr(mystring, "/..") != NULL) {
        return false;
    }
    return true;
}

FILE *getFile(const char *filepath) {
    if (checkIfSafe(filepath)) {
        return fopen(filepath, "r");
    } else {
        return NULL;
    }
}