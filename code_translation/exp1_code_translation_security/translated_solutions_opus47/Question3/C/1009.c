#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>

#define SAFE "/safedir/"

FILE *getFile(const char *path) {
    char full_path[PATH_MAX];
    char resolved_path[PATH_MAX];
    char resolved_safe[PATH_MAX];
    size_t safe_len;

    // Build the full path: SAFE + path
    if (snprintf(full_path, sizeof(full_path), "%s%s", SAFE, path) >= (int)sizeof(full_path)) {
        fprintf(stderr, "Path too long\n");
        return NULL;
    }

    // Resolve the safe directory (equivalent to Path(SAFE).resolve())
    if (realpath(SAFE, resolved_safe) == NULL) {
        fprintf(stderr, "Failed to resolve safe directory: %s\n", SAFE);
        return NULL;
    }

    // Resolve the full path (equivalent to (Path(SAFE) / path).resolve())
    if (realpath(full_path, resolved_path) == NULL) {
        fprintf(stderr, "Failed to resolve path: %s\n", full_path);
        return NULL;
    }

    // Check if resolved_safe is a strict parent of resolved_path
    // (equivalent to Path(SAFE).resolve() in test_path.resolve().parents)
    safe_len = strlen(resolved_safe);
    if (strncmp(resolved_path, resolved_safe, safe_len) != 0 ||
        resolved_path[safe_len] != '/') {
        fprintf(stderr, "Filename %s is not in %s directory\n", resolved_path, SAFE);
        return NULL;
    }

    // Open the file (equivalent to open(SAFE + path, 'r'))
    return fopen(full_path, "r");
}