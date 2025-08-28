#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>
#include <unistd.h>

// Function to get the canonical path
char* getCanonicalPath(const char* path) {
    char* canonicalPath = realpath(path, NULL);
    return canonicalPath;
}

// Function to normalize a path (to lowercase)
char* normalizePath(const char* path) {
    char* normalizedPath = strdup(path);
    for (int i = 0; i < strlen(normalizedPath); i++) {
        normalizedPath[i] = tolower(normalizedPath[i]);
    }
    return normalizedPath;
}

// Function to compare two paths
int comparePaths(const char* path1, const char* path2) {
    int len1 = strlen(path1);
    int len2 = strlen(path2);
    int minLen = (len1 < len2) ? len1 : len2;

    for (int i = 0; i < minLen; i++) {
        if (path1[i] != path2[i]) {
            return 0;
        }
    }

    // Check if path1 is a subdirectory of path2
    return len1 >= len2 && (len1 == minLen || path1[minLen] == '/');
}

// Function to check if a file is in a safe directory
int inSafeDirectory(const char* filePath, const char* safeDir) {
    char* fileDir = dirname(strdup(filePath));
    char* canonicalFileDir = getCanonicalPath(fileDir);
    char* normalizedSafeDir = normalizePath(safeDir);
    char* normalizedCanonicalFileDir = normalizePath(canonicalFileDir);

    int isSafe = comparePaths(normalizedCanonicalFileDir, normalizedSafeDir);

    free(fileDir);
    free(canonicalFileDir);
    free(normalizedSafeDir);
    free(normalizedCanonicalFileDir);

    return isSafe;
}

// Function to open a file if it's in a safe directory
FILE* openFile(const char* path) {
    const char* safeDir = "/safedir";
    if (inSafeDirectory(path, safeDir)) {
        return fopen(path, "r");
    } else {
        return NULL;
    }
}

int main() {
    FILE* file = openFile("/env.sh");
    if (file != NULL) {
        printf("File opened successfully.\n");
        fclose(file);
    } else {
        printf("File not opened.\n");
    }
    return 0;
}