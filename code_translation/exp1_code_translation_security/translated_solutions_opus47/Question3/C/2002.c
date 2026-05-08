#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <limits.h>

/* Path relative to /safedir, e.g. hello/world to refer to /safedir/hello/world */
FILE *getFile(const char *path) {
    char filename[PATH_MAX];
    char resolved[PATH_MAX];
    const char *safedir = "/safedir/";
    size_t safedir_len = strlen(safedir);

    /* Build the full path: /safedir/<path> */
    int n = snprintf(filename, sizeof(filename), "%s%s", safedir, path);
    if (n < 0 || (size_t)n >= sizeof(filename)) {
        return NULL;  /* path too long */
    }

    /* Resolve to canonical absolute path (collapses "..", "." and symlinks) */
    if (realpath(filename, resolved) == NULL) {
        return NULL;  /* file doesn't exist or is inaccessible */
    }

    /* Verify the resolved path is still inside /safedir/ */
    if (strncmp(resolved, safedir, safedir_len) != 0) {
        return NULL;  /* reject: path traversal outside safedir */
    }

    return fopen(resolved, "r");
}