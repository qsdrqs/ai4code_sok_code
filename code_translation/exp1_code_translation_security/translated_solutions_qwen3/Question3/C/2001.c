#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>

#define SAFE_DIR "/safedir"

// Helper function to normalize a path (similar to os.path.normpath in Python)
char* normalize_path(const char* path) {
    if (!path || *path == '\0') {
        errno = EINVAL;
        return NULL;
    }

    // Duplicate the input path to avoid modifying it
    char* path_dup = strdup(path);
    if (!path_dup) {
        errno = ENOMEM;
        return NULL;
    }

    // Allocate buffer for the normalized path
    char* normalized = (char*)malloc(PATH_MAX);
    if (!normalized) {
        free(path_dup);
        errno = ENOMEM;
        return NULL;
    }

    normalized[0] = '\0';  // Start with an empty string

    // Determine if the path is absolute
    int is_absolute = (path[0] == '/');
    if (is_absolute) {
        strcpy(normalized, "/");
    }

    // Tokenize the path by '/'
    char* token = strtok(path_dup, "/");
    while (token) {
        if (strcmp(token, ".") == 0) {
            // Skip current directory
        } else if (strcmp(token, "..") == 0) {
            // Remove the last component if not at root
            if (strlen(normalized) > 1) {
                char* last_slash = strrchr(normalized, '/');
                if (last_slash && last_slash != normalized) {
                    *last_slash = '\0';
                } else {
                    normalized[1] = '\0';  // Keep only the root slash
                }
            }
        } else {
            // Append the component
            if (normalized[0] != '\0' && normalized[strlen(normalized) - 1] != '/') {
                strcat(normalized, "/");
            }
            strcat(normalized, token);
        }
        token = strtok(NULL, "/");
    }

    free(path_dup);
    return normalized;
}

// Function to open a file if its normalized path is within SAFE_DIR
FILE* get_file(const char* path) {
    if (!path || *path == '\0') {
        errno = EINVAL;
        return NULL;
    }

    char* normalized = normalize_path(path);
    if (!normalized) {
        errno = EINVAL;
        return NULL;
    }

    size_t safe_len = strlen(SAFE_DIR);
    if (strncmp(normalized, SAFE_DIR, safe_len) != 0) {
        free(normalized);
        errno = EINVAL;
        return NULL;
    }

    // Optional: Ensure that the path is not a prefix like "/safedir_sub"
    if (normalized[safe_len] != '\0' && normalized[safe_len] != '/') {
        free(normalized);
        errno = EINVAL;
        return NULL;
    }

    FILE* file = fopen(normalized, "r");
    free(normalized);
    return file;
}