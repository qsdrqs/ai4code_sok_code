#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <libgen.h>
#include <limits.h>
#include <errno.h>

FILE* open_file(const char* path) {
    char resolved_path[PATH_MAX];
    char safedir[PATH_MAX];

    // Resolve the absolute path of the input path
    if (realpath(path, resolved_path) == NULL) {
        perror("Error resolving path");
        return NULL;
    }

    // Resolve the absolute path of the safedir
    if (realpath("/safedir", safedir) == NULL) {
        perror("Error resolving safedir path");
        return NULL;
    }

    // Check if the resolved path starts with the safedir path
    if (strncmp(resolved_path, safedir, strlen(safedir)) == 0) {
        FILE* file = fopen(path, "r");
        if (file == NULL) {
            perror("Error opening file");
        }
        return file;
    } else {
        fprintf(stderr, "Access Denied\n");
        return NULL;
    }
}

int main() {
    FILE* file = open_file("/safedir/test.txt");
    if (file != NULL) {
        printf("File opened successfully\n");
        fclose(file);
    }
    return 0;
}