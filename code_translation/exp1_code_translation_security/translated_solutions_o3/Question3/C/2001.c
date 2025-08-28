#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>
#include <errno.h>

#define SAFE_DIR "/safedir"

/*
 * Returns an already–opened FILE* if the requested path resolves
 * inside SAFE_DIR; otherwise returns NULL and sets errno.
 */
FILE *get_file(const char *path)
{
    char resolved_path[PATH_MAX];

    /* Resolve the path to its canonical form (handles ../, symlinks, etc.). */
    if (realpath(path, resolved_path) == NULL) {
        /* errno is set by realpath (e.g., ENOENT, EACCES). */
        return NULL;
    }

    /* Ensure the resolved path has SAFE_DIR as its prefix. */
    size_t safe_len = strlen(SAFE_DIR);
    if (strncmp(resolved_path, SAFE_DIR, safe_len) != 0 ||
        (resolved_path[safe_len] != '\0' && resolved_path[safe_len] != '/')) {
        /* Not inside SAFE_DIR → treat as an error. */
        errno = EINVAL;
        fprintf(stderr, "Path must be in SAFE_DIR\n");
        return NULL;
    }

    /* Open the file for reading. */
    return fopen(resolved_path, "r");
}