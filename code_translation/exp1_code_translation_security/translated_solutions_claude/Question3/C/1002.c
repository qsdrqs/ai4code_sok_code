#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <limits.h>
#include <assert.h>

// Function to resolve path (similar to pathlib.Path.resolve())
char* resolve_path(const char* filename) {
    char* resolved = malloc(PATH_MAX);
    if (resolved == NULL) {
        return NULL;
    }
    
    if (realpath(filename, resolved) == NULL) {
        free(resolved);
        return NULL;
    }
    
    return resolved;
}

// Function to split path into parts and check if second part is "safedir"
int check_safedir_path(const char* path) {
    if (path == NULL || strlen(path) == 0) {
        return 0;
    }
    
    // Make a copy of the path to tokenize
    char* path_copy = malloc(strlen(path) + 1);
    if (path_copy == NULL) {
        return 0;
    }
    strcpy(path_copy, path);
    
    // Split path by '/'
    char* parts[256]; // Assuming max 256 path components
    int part_count = 0;
    
    char* token = strtok(path_copy, "/");
    while (token != NULL && part_count < 256) {
        if (strlen(token) > 0) { // Skip empty tokens (from leading /)
            parts[part_count] = token;
            part_count++;
        }
        token = strtok(NULL, "/");
    }
    
    // Check if we have at least 1 part and first part is "safedir"
    // (Note: In Unix paths starting with /, the first meaningful part after root is index 0)
    int result = (part_count >= 1 && strcmp(parts[0], "safedir") == 0);
    
    free(path_copy);
    return result;
}

FILE* open_file(const char* filename) {
    char* resolved_path = resolve_path(filename);
    if (resolved_path == NULL) {
        return NULL;
    }
    
    if (check_safedir_path(resolved_path)) {
        FILE* file = fopen(resolved_path, "r");
        free(resolved_path);
        return file;
    }
    
    free(resolved_path);
    return NULL;
}

void test() {
    // Test 1: open_file("/safedir/../a/b") should return NULL
    FILE* result1 = open_file("/safedir/../a/b");
    assert(result1 == NULL);
    
    // Test 2: open_file("/safedir/a") should fail (file doesn't exist)
    // This will return NULL because the file doesn't exist, which matches
    // the Python behavior where it would raise an exception
    FILE* result2 = open_file("/safedir/a");
    // In the original Python, this would raise an exception if file doesn't exist
    // In C, fopen returns NULL if file doesn't exist, so we expect NULL
    assert(result2 == NULL);
    
    printf("All tests passed!\n");
}

int main() {
    test();
    return 0;
}