#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <libgen.h>
#include <limits.h>
#include <unistd.h>

FILE* get_file(const char* path) {
    char normpath[PATH_MAX];
    
    // Normalize the path
    if (realpath(path, normpath) == NULL) {
        perror("realpath");
        return fopen("/dev/null", "r");
    }
    
    printf("%s\n", normpath);
    
    // Check if the path starts with "/safedir"
    if (strncmp(normpath, "/safedir", 8) != 0) {
        printf("out of bounds\n");
        return fopen("/dev/null", "r");
    } else {
        printf("opening file %s\n", normpath);
        return fopen(normpath, "r+");
    }
}

int main() {
    FILE* f = get_file("/safedir/test1.txt");
    if (f != NULL) {
        char buffer[1024];
        size_t bytesRead = fread(buffer, 1, sizeof(buffer) - 1, f);
        buffer[bytesRead] = '\0'; // Null-terminate the string
        printf("%s", buffer);
        fclose(f);
    }

    FILE* g = get_file("/dev/rand0");
    if (g != NULL) {
        char buffer[1024];
        size_t bytesRead = fread(buffer, 1, sizeof(buffer) - 1, g);
        buffer[bytesRead] = '\0'; // Null-terminate the string
        printf("%s", buffer);
        fclose(g);
    }

    return 0;
}