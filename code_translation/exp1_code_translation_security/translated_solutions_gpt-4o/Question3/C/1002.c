#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <libgen.h>
#include <limits.h>
#include <unistd.h>

FILE* open_file(const char* filename) {
    char resolved_path[PATH_MAX];
    
    // Resolve the absolute path
    if (realpath(filename, resolved_path) == NULL) {
        return NULL;
    }

    // Tokenize the path to check the components
    char* path_copy = strdup(resolved_path);
    char* token = strtok(path_copy, "/");
    int part_count = 0;
    int is_safe = 0;

    while (token != NULL) {
        part_count++;
        if (part_count == 2 && strcmp(token, "safedir") == 0) {
            is_safe = 1;
            break;
        }
        token = strtok(NULL, "/");
    }

    free(path_copy);

    if (is_safe) {
        return fopen(resolved_path, "r");
    }
    return NULL;
}

void test() {
    // Test case 1
    if (open_file("/safedir/../a/b") != NULL) {
        printf("Test 1 failed\n");
    } else {
        printf("Test 1 passed\n");
    }

    // Test case 2
    FILE* file = open_file("/safedir/a");
    if (file != NULL) {
        printf("Test 2 failed\n");
        fclose(file);
    } else {
        printf("Test 2 passed\n");
    }
}

int main() {
    test();
    return 0;
}