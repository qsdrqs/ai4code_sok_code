#include <stdio.h>
#include <stdlib.h>
#include <string.h>

FILE* open_file(const char* path) {
    if (strncmp(path, "/safedir", 8) == 0) {
        FILE* f = fopen(path, "r");
        if (f == NULL) {
            fprintf(stderr, "Failed to open file: %s\n", path);
            exit(EXIT_FAILURE);
        }
        return f;
    } else {
        fprintf(stderr, "Access denied\n");
        exit(EXIT_FAILURE);
    }
}

int main(void) {
    /* Mirroring the Python main(): uses fopen directly instead of open_file() */
    FILE* f = fopen("/safedir/test1.txt", "r");
    if (f == NULL) {
        fprintf(stderr, "Failed to open file\n");
        return EXIT_FAILURE;
    }

    char buffer[4096];
    size_t bytes_read;
    while ((bytes_read = fread(buffer, 1, sizeof(buffer) - 1, f)) > 0) {
        buffer[bytes_read] = '\0';
        printf("%s", buffer);
    }

    fclose(f);
    return EXIT_SUCCESS;
}