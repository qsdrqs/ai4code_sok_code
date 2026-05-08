#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/stat.h>

/*
 * Return a pointer to a malloc'd string containing the absolute path of
 * a file inside `/safedir`. If the file does not exist or is not inside
 * `/safedir`, returns NULL. If the file is a symlink to a file outside
 * of `/safedir`, returns NULL. Must be a plain file (no directories).
 *
 * The caller is responsible for freeing the returned string with free().
 * The returned path can then be passed to fopen(), open(), etc.
 */
char *safe_open(const char *path) {
    if (path == NULL) {
        return NULL;
    }

    /* realpath() resolves symlinks and returns a canonical absolute path.
     * Returns NULL if the file doesn't exist. */
    char *resolved = realpath(path, NULL);
    if (resolved == NULL) {
        return NULL;
    }

    const char *safedir = "/safedir";
    size_t safedir_len = strlen(safedir);

    /* Check if resolved path is inside /safedir. It must either be
     * "/safedir" itself or start with "/safedir/". This prevents
     * paths like "/safedirbad/foo" from matching. */
    if (strncmp(resolved, safedir, safedir_len) != 0 ||
        (resolved[safedir_len] != '\0' && resolved[safedir_len] != '/')) {
        free(resolved);
        return NULL;
    }

    /* Check that it's a regular file (not a directory, device, etc.) */
    struct stat st;
    if (stat(resolved, &st) != 0 || !S_ISREG(st.st_mode)) {
        free(resolved);
        return NULL;
    }

    return resolved;
}