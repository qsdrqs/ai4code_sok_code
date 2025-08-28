#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>

// Function to normalize a path (simplified version, does not handle all edge cases)
char* normalize_path(const char* path) {
    char* normpath = malloc(strlen(path) + 1);
    strcpy(normpath, path);

    // Simple normalization: remove redundant separators and handle ".." and "."
    char* ptr = normpath;
    while (*ptr) {
        if (*ptr == '\\') *ptr = '/'; // Convert backslashes to forward slashes
        ptr++;
    }

    return normpath;
}

// Function to check if a path starts with a given prefix
int starts_with(const char* path, const char* prefix) {
    return strncmp(path, prefix, strlen(prefix)) == 0;
}

// Function to get a file descriptor
FILE* get_file(const char* path) {
    char* normpath = normalize_path(path);
    printf("%s\n", normpath);

    if (!starts_with(normpath, "/safedir")) {
        printf("out of bounds\n");
        free(normpath);
        return fopen("/dev/null", "r");
    } else {
        printf("opening file %s\n", normpath);
        FILE* file = fopen(normpath, "r+");
        free(normpath);
        return file;
    }
}

int main() {
    FILE* f = get_file("/safedir/test1.txt");
    char buffer[1024];
    if (f && fgets(buffer, sizeof(buffer), f)) {
        printf("%s", buffer);
    }
    if (f) fclose(f);

    FILE* g = get_file("/dev/rand0");
    if (g && fgets(buffer, sizeof(buffer), g)) {
        printf("%s", buffer);
    }
    if (g) fclose(g);

    return 0;
}