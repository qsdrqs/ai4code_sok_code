#include <stdio.h>
#include <string.h>
#include <libgen.h>

/**
 * Checks if the directory of the given path is "/safedir".
 * If so, opens the file and returns the FILE pointer.
 * Otherwise, prints "not safe!" and returns NULL.
 */
FILE* find_path(const char* path) {
    const char* safe_path = "/safedir";

    // Make a copy of the path to avoid modifying the original
    char path_copy[strlen(path) + 1];
    strcpy(path_copy, path);

    // Extract the directory name
    char* dir = dirname(path_copy);

    // Compare the directory with the safe path
    if (strcmp(dir, safe_path) == 0) {
        FILE* file = fopen(path, "r");
        printf("safe!\n");
        return file;
    } else {
        printf("not safe!\n");
        return NULL;
    }
}

int main() {
    // Test cases
    FILE* f1 = find_path("/safedir/random.txt");
    if (f1) {
        fclose(f1);  // Always close the file if it was opened
    }

    FILE* f2 = find_path("/home/codex/Documents/User_Study_UI");
    if (f2) {
        fclose(f2);
    }

    return 0;
}