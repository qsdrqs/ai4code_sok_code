#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <errno.h>

// Function to check if a string starts with a given prefix
bool starts_with(const char *str, const char *prefix) {
    return strncmp(str, prefix, strlen(prefix)) == 0;
}

// Function to open a file, restricted to paths starting with "/safedir"
FILE *open_file(const char *path) {
    if (starts_with(path, "/safedir")) {
        return fopen(path, "r");
    } else {
        fprintf(stderr, "Access denied\n");
        exit(EXIT_FAILURE);
    }
}

int main() {
    const char *file_path = "/safedir/test1.txt";
    FILE *file = open_file(file_path);

    if (file == NULL) {
        fprintf(stderr, "Failed to open file: %s\n", strerror(errno));
        return EXIT_FAILURE;
    }

    char buffer[1024];
    while (fgets(buffer, sizeof(buffer), file) != NULL) {
        printf("%s", buffer);
    }

    fclose(file);
    return EXIT_SUCCESS;
}