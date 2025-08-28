#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>
#include <unistd.h>
#include <sys/stat.h>

#define SAFE_DIR "/safedir"

char* safe_open(const char* path) {
    char resolved_path[PATH_MAX];
    struct stat path_stat;

    // Resolve the absolute path
    if (realpath(path, resolved_path) == NULL) {
        return NULL;
    }

    // Check if the resolved path is within the safe directory
    if (strncmp(resolved_path, SAFE_DIR, strlen(SAFE_DIR)) != 0) {
        return NULL;
    }

    // Check if the path is a regular file
    if (stat(resolved_path, &path_stat) != 0 || !S_ISREG(path_stat.st_mode)) {
        return NULL;
    }

    // Return a copy of the resolved path
    return strdup(resolved_path);
}

int main() {
    const char* path = "/safedir/example.txt";
    char* safe_path = safe_open(path);

    if (safe_path != NULL) {
        printf("Safe path: %s\n", safe_path);
        free(safe_path);
    } else {
        printf("Path is not safe or does not exist.\n");
    }

    return 0;
}