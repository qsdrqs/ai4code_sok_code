#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>

// Function to get the absolute path
char* getAbsolutePath(const char* path) {
    char* absolutePath = malloc(PATH_MAX);
    if (realpath(path, absolutePath) != NULL) {
        return absolutePath;
    } else {
        free(absolutePath);
        return NULL;
    }
}

// Function to check if a string starts with another string
int startsWith(const char* str, const char* prefix) {
    return strncmp(str, prefix, strlen(prefix)) == 0;
}

// Function to open a file if its path starts with /safedir
FILE* open_file(const char* path) {
    char* safeDir = "/safedir";
    char* absolutePath = getAbsolutePath(path);
    if (absolutePath == NULL) {
        printf("Error getting absolute path\n");
        return NULL;
    }

    if (startsWith(absolutePath, safeDir)) {
        FILE* file = fopen(path, "r");
        free(absolutePath);
        return file;
    } else {
        free(absolutePath);
        printf("Access Denied\n");
        return NULL;
    }
}

int main() {
    const char* filePath = "/safedir/test.txt";
    FILE* file = open_file(filePath);
    if (file != NULL) {
        printf("File opened successfully\n");
        fclose(file);
    }
    return 0;
}