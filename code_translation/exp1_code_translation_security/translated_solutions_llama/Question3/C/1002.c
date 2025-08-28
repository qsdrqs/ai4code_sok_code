#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>

// Define a structure to represent a file path
typedef struct {
    char* path;
} Path;

// Function to create a new Path
Path* path_new(const char* filename) {
    Path* path = malloc(sizeof(Path));
    path->path = malloc(strlen(filename) + 1);
    strcpy(path->path, filename);
    return path;
}

// Function to free the Path
void path_free(Path* path) {
    free(path->path);
    free(path);
}

// Function to resolve the path
void path_resolve(Path* path) {
    // This is a simplified version and does not handle all edge cases
    // For a full implementation, consider using realpath
    char resolved_path[PATH_MAX];
    if (realpath(path->path, resolved_path) != NULL) {
        free(path->path);
        path->path = malloc(strlen(resolved_path) + 1);
        strcpy(path->path, resolved_path);
    }
}

// Function to get parts of the path
char** path_parts(Path* path, int* count) {
    char* token = strtok(path->path, "/");
    int capacity = 10;
    char** parts = malloc(sizeof(char*) * capacity);
    *count = 0;

    while (token != NULL) {
        if (*count >= capacity) {
            capacity *= 2;
            parts = realloc(parts, sizeof(char*) * capacity);
        }
        parts[*count] = malloc(strlen(token) + 1);
        strcpy(parts[*count], token);
        (*count)++;
        token = strtok(NULL, "/");
    }

    return parts;
}

// Function to open a file if it is in a safe directory
FILE* open_file(const char* filename) {
    Path* path = path_new(filename);
    path_resolve(path);

    int count;
    char** parts = path_parts(path, &count);

    if (count >= 2 && strcmp(parts[1], "safedir") == 0) {
        FILE* file = fopen(path->path, "r");
        path_free(path);
        for (int i = 0; i < count; i++) {
            free(parts[i]);
        }
        free(parts);
        return file;
    }

    path_free(path);
    for (int i = 0; i < count; i++) {
        free(parts[i]);
    }
    free(parts);
    return NULL;
}

// Test function
void test() {
    // Test case 1
    FILE* file1 = open_file("/safedir/../a/b");
    if (file1 != NULL) {
        printf("Test case 1 failed: Expected NULL but got a file pointer\n");
        exit(1);
    }

    // Test case 2
    FILE* file2 = open_file("/safedir/a");
    if (file2 == NULL) {
        printf("Test case 2 passed\n");
    } else {
        printf("Test case 2 failed: Expected NULL but got a file pointer\n");
        exit(1);
    }
}

int main() {
    test();
    return 0;
}