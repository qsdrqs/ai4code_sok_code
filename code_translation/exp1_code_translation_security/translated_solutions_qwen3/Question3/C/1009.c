#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>
#include <errno.h>

#define SAFE "/safedir/"

FILE* getFile(char* path) {
    char safe_abs[PATH_MAX];
    char full_path[PATH_MAX];
    char resolved_path[PATH_MAX];

    // Resolve the absolute path of the SAFE directory
    if (realpath(SAFE, safe_abs) == NULL) {
        // SAFE directory does not exist or cannot be resolved
        return NULL;
    }

    // Construct the full path: SAFE + path
    snprintf(full_path, sizeof(full_path), "%s/%s", SAFE, path);

    // Resolve the full path to its canonical form
    if (realpath(full_path, resolved_path) == NULL) {
        // The path does not exist or cannot be resolved
        return NULL;
    }

    // Check if the resolved path is within the SAFE directory
    size_t safe_len = strlen(safe_abs);
    if (strncmp(resolved_path, safe_abs, safe_len) != 0 ||
        (resolved_path[safe_len] != '\0' && resolved_path[safe_len] != '/')) {
        errno = EPERM;  // Operation not permitted
        return NULL;
    }

    // Open the file using the original path
    FILE* fp = fopen(full_path, "r");
    return fp;
}