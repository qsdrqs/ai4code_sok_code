#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function prototypes
char* dirname_copy(const char* path);
char* basename_copy(const char* path);

/**
 * @brief Returns a FILE pointer if the file is in a directory tree that contains a "safedir".
 *        Returns NULL otherwise, and closes the file if it was opened.
 */
FILE* path2obj(const char* filePath) {
    FILE* f = fopen(filePath, "r");
    if (!f) {
        return NULL;
    }

    char* directory = dirname_copy(filePath);
    if (!directory) {
        fclose(f);
        return NULL;
    }

    int found = 0;

    while (directory[0] != '\0') {
        char* base = basename_copy(directory);
        if (!base) {
            free(directory);
            fclose(f);
            return NULL;
        }

        if (strcmp(base, "safedir") == 0) {
            free(base);
            found = 1;
            break;
        }

        free(base);

        char* new_dir = dirname_copy(directory);
        free(directory);
        directory = new_dir;

        if (!directory) {
            fclose(f);
            return NULL;
        }
    }

    free(directory);

    if (found) {
        return f;
    } else {
        fclose(f);
        return NULL;
    }
}

/**
 * @brief Returns a dynamically allocated copy of the directory name of the given path.
 *        Trims trailing slashes before processing.
 */
char* dirname_copy(const char* path) {
    size_t len = strlen(path);
    char* pathcpy = malloc(len + 1);
    if (!pathcpy) return NULL;
    strcpy(pathcpy, path);

    // Trim trailing slashes
    while (len > 0 && (pathcpy[len - 1] == '/' || pathcpy[len - 1] == '\\')) {
        pathcpy[--len] = '\0';
    }

    if (len == 0) {
        free(pathcpy);
        return strdup("");
    }

    // Find last slash
    char* last_slash = strrchr(pathcpy, '/');
    #ifdef _WIN32
    char* last_backslash = strrchr(pathcpy, '\\');
    if (last_backslash && (!last_slash || last_backslash > last_slash)) {
        last_slash = last_backslash;
    }
    #endif

    if (!last_slash) {
        free(pathcpy);
        return strdup(".");
    } else {
        *last_slash = '\0';
        return pathcpy;
    }
}

/**
 * @brief Returns a dynamically allocated copy of the base name of the given path.
 *        Trims trailing slashes before extracting the base name.
 */
char* basename_copy(const char* path) {
    size_t len = strlen(path);
    char* pathcpy = malloc(len + 1);
    if (!pathcpy) return NULL;
    strcpy(pathcpy, path);

    // Trim trailing slashes
    while (len > 0 && (pathcpy[len - 1] == '/' || pathcpy[len - 1] == '\\')) {
        pathcpy[--len] = '\0';
    }

    if (len == 0) {
        free(pathcpy);
        return strdup("");
    }

    // Find last slash
    char* last_slash = strrchr(pathcpy, '/');
    #ifdef _WIN32
    char* last_backslash = strrchr(pathcpy, '\\');
    if (last_backslash && (!last_slash || last_backslash > last_slash)) {
        last_slash = last_backslash;
    }
    #endif

    if (!last_slash) {
        return pathcpy;
    } else {
        char* res = strdup(last_slash + 1);
        free(pathcpy);
        return res;
    }
}