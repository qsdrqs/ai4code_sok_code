#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>
#include <errno.h>

// Function to check if the resolved path is under /safedir
int is_safe_path(const char *resolved_path) {
    char *temp = strdup(resolved_path);
    if (!temp) {
        perror("strdup");
        exit(EXIT_FAILURE);
    }

    char *saveptr;
    char *token = strtok_r(temp, "/", &saveptr);  // First token is empty (root)
    token = strtok_r(NULL, "/", &saveptr);         // First directory component

    int valid = 0;
    if (token && strcmp(token, "safedir") == 0) {
        valid = 1;
    }

    free(temp);
    return valid;
}

// Function to load a file from a given path, ensuring it's under /safedir
FILE *load(const char *path_str) {
    char resolved_path[PATH_MAX];
    if (realpath(path_str, resolved_path) == NULL) {
        // If realpath fails, try with dynamic allocation
        char *rpath = realpath(path_str, NULL);
        if (!rpath) {
            fprintf(stderr, "Error resolving path for %s: %s\n", path_str, strerror(errno));
            exit(EXIT_FAILURE);
        }
        strncpy(resolved_path, rpath, PATH_MAX - 1);
        resolved_path[PATH_MAX - 1] = '\0';
        free(rpath);
    }

    if (!is_safe_path(resolved_path)) {
        fprintf(stderr, "Cannot access unsafe file\n");
        exit(EXIT_FAILURE);
    }

    FILE *fp = fopen(resolved_path, "r");
    if (!fp) {
        perror("Error opening file");
        exit(EXIT_FAILURE);
    }

    return fp;
}

// Main function to simulate the Python code's behavior
int main() {
    // First call: should succeed
    FILE *fp1 = load("/safedir/foo.txt");
    char buffer[4096];
    while (fgets(buffer, sizeof(buffer), fp1)) {
        printf("%s", buffer);
    }
    fclose(fp1);

    // Second call: should fail and exit
    FILE *fp2 = load("/tmp/foo.txt");
    while (fgets(buffer, sizeof(buffer), fp2)) {
        printf("%s", buffer);
    }
    fclose(fp2);

    return 0;
}