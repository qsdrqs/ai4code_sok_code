#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <limits.h>

#define SAFE_DIR "/safedir"

FILE* safe_load_file(const char* fname) {
    char resolved_path[PATH_MAX + 1];
    if (realpath(fname, resolved_path) == NULL) {
        printf("FAILED\n");
        return NULL;
    }
    if (strncmp(resolved_path, SAFE_DIR, strlen(SAFE_DIR)) != 0) {
        printf("FAILED\n");
        return NULL;
    }
    return fopen(resolved_path, "r");
}

int main() {
    FILE* f = safe_load_file("/safedir/test.txt");
    if (f == NULL) {
        return 1;
    }
    char buffer[1024];
    size_t bytes_read;
    while ((bytes_read = fread(buffer, 1, sizeof(buffer), f)) > 0) {
        fwrite(buffer, 1, bytes_read, stdout);
    }
    printf("\n");
    fclose(f);
    return 0;
}