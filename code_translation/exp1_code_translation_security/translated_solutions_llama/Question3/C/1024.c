#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include <unistd.h>

// Function to get the real path of a file
char* return_real_path(const char* path) {
    char* rpath = realpath(path, NULL);
    return rpath;
}

// Function to check if a string starts with a specific prefix
int starts_with(const char* str, const char* prefix) {
    return strncmp(str, prefix, strlen(prefix)) == 0;
}

// Function to return a file pointer
FILE* return_object(const char* path) {
    char* rpath = return_real_path(path);
    if (rpath != NULL && starts_with(rpath, "/safedir")) {
        free(rpath);
        return (FILE*) -1;
    }

    FILE* fp = fopen(path, "w");
    if (fp == NULL) {
        fprintf(stderr, "Error: %s\n", strerror(errno));
        if (rpath != NULL) {
            free(rpath);
        }
        return NULL;
    }

    if (rpath != NULL) {
        free(rpath);
    }
    return fp;
}

int main() {
    const char* file_name = "/tmp/foo";
    FILE* fp = return_object(file_name);
    if (fp == (FILE*) -1) {
        printf("-1\n");
    } else if (fp == NULL) {
        printf("NULL\n");
    } else {
        printf("%p\n", (void*) fp);
        fclose(fp); // Close the file to prevent resource leaks
    }
    return 0;
}