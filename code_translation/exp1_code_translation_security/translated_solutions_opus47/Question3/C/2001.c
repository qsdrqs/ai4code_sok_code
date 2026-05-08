#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>

#define SAFE_DIR "/safedir"

FILE *get_file(const char *path) {
    /* realpath() is the closest C equivalent to os.path.normpath.
     * Note: realpath also resolves symlinks and requires the file to exist,
     * which makes it stricter (and safer) than Python's purely-lexical normpath. */
    char *resolved = realpath(path, NULL);
    if (resolved == NULL) {
        return NULL;  /* errno set by realpath */
    }

    if (strncmp(resolved, SAFE_DIR, strlen(SAFE_DIR)) != 0) {
        fprintf(stderr, "Path must be in SAFE_DIR\n");
        free(resolved);
        errno = EINVAL;
        return NULL;
    }

    free(resolved);
    return fopen(path, "r");
}