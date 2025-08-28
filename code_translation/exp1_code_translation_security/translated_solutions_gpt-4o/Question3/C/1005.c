#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>
#include <unistd.h>

#define SAFE_DIR "/safedir"

FILE* safe_load_file(const char* fname) {
    char fpath[PATH_MAX];
    
    // Resolve the real path
    if (realpath(fname, fpath) == NULL) {
        perror("realpath");
        return NULL;
    }
    
    // Check if the resolved path starts with SAFE_DIR
    if (strncmp(fpath, SAFE_DIR, strlen(SAFE_DIR)) != 0) {
        printf("FAILED\n");
        return NULL;
    }
    
    // Open the file
    FILE* file = fopen(fpath, "r");
    if (file == NULL) {
        perror("fopen");
    }
    return file;
}

int main() {
    FILE* f = safe_load_file("/safedir/test.txt");
    if (f != NULL) {
        // Read and print the file content
        char buffer[1024];
        while (fgets(buffer, sizeof(buffer), f) != NULL) {
            printf("%s", buffer);
        }
        fclose(f);
    }
    return 0;
}