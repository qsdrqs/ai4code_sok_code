#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

// Structure to represent a path
typedef struct {
    char* path;
} Path;

// Function to create a new Path from a string
Path* path_from_str(const char* p) {
    // Check if the path starts with "/safedir"
    if (strncmp(p, "/safedir", 9) != 0) {
        return NULL;
    }

    // Allocate memory for the Path structure
    Path* path = (Path*) malloc(sizeof(Path));
    if (path == NULL) {
        return NULL;
    }

    // Allocate memory for the path string
    path->path = (char*) malloc(strlen(p) + 1);
    if (path->path == NULL) {
        free(path);
        return NULL;
    }

    // Copy the path string
    strcpy(path->path, p);

    return path;
}

// Function to print a Path
void print_path(Path* path) {
    if (path != NULL) {
        printf("%s\n", path->path);
    } else {
        printf("NULL\n");
    }
}

// Function to free a Path
void free_path(Path* path) {
    if (path != NULL) {
        free(path->path);
        free(path);
    }
}

int main() {
    // Test the path_from_str function
    Path* path1 = path_from_str("/safedir/junk");
    Path* path2 = path_from_str("/bin");
    Path* path3 = path_from_str("/safedir/doesntexist");

    print_path(path1);
    print_path(path2);
    print_path(path3);

    // Free the allocated memory
    free_path(path1);
    free_path(path2);
    free_path(path3);

    return 0;
}