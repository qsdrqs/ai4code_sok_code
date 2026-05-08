#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include <limits.h>

FILE* return_object(const char *path) {
    char rpath[PATH_MAX];
    
    if (realpath(path, rpath) == NULL) {
        fprintf(stderr, "Error: %s\n", strerror(errno));
        return NULL;
    }
    
    if (strncmp(rpath, "/safedir", 8) == 0) {
        return NULL;  // equivalent to returning -1 in Python
    }
    
    FILE *fp = fopen(path, "w");
    if (fp == NULL) {
        fprintf(stderr, "Error: %s\n", strerror(errno));
        return NULL;
    }
    return fp;
}

int main(void) {
    const char *file_name = "/tmp/foo";
    FILE *fp = return_object(file_name);
    printf("%p\n", (void*)fp);
    if (fp != NULL) {
        fclose(fp);
    }
    return 0;
}