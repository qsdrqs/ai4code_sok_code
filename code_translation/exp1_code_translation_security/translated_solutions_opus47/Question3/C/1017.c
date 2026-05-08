#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <limits.h>

FILE* get_file(const char* path) {
    char resolved[PATH_MAX];
    if (realpath(path, resolved) == NULL) {
        fprintf(stderr, "Invalid path\n");
        return NULL;
    }
    // Ensure resolved path is inside /safedir/ (note trailing slash)
    if (strncmp(resolved, "/safedir/", 9) != 0) {
        fprintf(stderr, "Access denied\n");
        return NULL;
    }
    return fopen(resolved, "r");
}