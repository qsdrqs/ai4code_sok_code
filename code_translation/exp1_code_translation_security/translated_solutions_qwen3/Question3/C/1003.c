#include <sys/stat.h>
#include <unistd.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include <limits.h>
#include <errno.h>

/**
 * @brief Safely opens a file inside the /safedir directory.
 *
 * This function checks that the given path:
 * - Resolves to a canonical absolute path.
 * - Is located inside the /safedir directory.
 * - Points to a regular file (not a directory or other file type).
 *
 * @param path The input file path (can be relative or contain symlinks).
 * @return A dynamically allocated string of the resolved path if valid, or NULL otherwise.
 */
char* safe_open(const char* path) {
    // Step 1: Resolve the path to its canonical form (resolving symlinks)
    char resolved_path[PATH_MAX];
    if (realpath(path, resolved_path) == NULL) {
        // Path could not be resolved (e.g., file doesn't exist, invalid symlink)
        return NULL;
    }

    // Step 2: Ensure the resolved path is inside /safedir
    if (strncmp(resolved_path, "/safedir/", 9) != 0) {
        // Path is not under /safedir
        return NULL;
    }

    // Step 3: Check that the resolved path is a regular file
    struct stat st;
    if (stat(resolved_path, &st) != 0) {
        // File does not exist or an error occurred
        return NULL;
    }

    if (!S_ISREG(st.st_mode)) {
        // Not a regular file (e.g., directory, symlink, device, etc.)
        return NULL;
    }

    // Step 4: Return a dynamically allocated copy of the resolved path
    return strdup(resolved_path);
}