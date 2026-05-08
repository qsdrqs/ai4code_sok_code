#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>

#define SAFE_DIR "/safedir"

FILE* safe_load_file(const char* fname) {
    char fpath[PATH_MAX];

    // Parse path
    if (realpath(fname, fpath) == NULL) {
        printf("FAILED\n");
        return NULL;
    }

    if (strncmp(fpath, SAFE_DIR, strlen(SAFE_DIR)) != 0) {
        printf("FAILED\n");
        return NULL;
    }

    return fopen(fpath, "r");
}

int main(void) {
    FILE* f = safe_load_file("/safedir/test.txt");
    if (f == NULL) {
        return 1;
    }

    char buffer[1024];
    size_t bytes_read;
    while ((bytes_read = fread(buffer, 1, sizeof(buffer) - 1, f)) > 0) {
        buffer[bytes_read] = '\0';
        printf("%s", buffer);
    }
    printf("\n");

    fclose(f);
    return 0;
}