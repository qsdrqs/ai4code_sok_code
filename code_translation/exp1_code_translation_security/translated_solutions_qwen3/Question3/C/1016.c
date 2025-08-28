#include <stdio.h>
#include <string.h>

/**
 * Converts the path to a FILE pointer if the path contains "/safedir".
 * Returns NULL otherwise.
 */
FILE* path_to_file(const char* path) {
    if (strstr(path, "/safedir") != NULL) {
        return fopen(path, "r");
    } else {
        return NULL;
    }
}

int main() {
    // Print the result of path_to_file for two test paths
    printf("%p\n", (void*)path_to_file("/safedir/random.txt"));
    printf("%p\n", (void*)path_to_file("/notsafedir/random.txt"));

    return 0;
}