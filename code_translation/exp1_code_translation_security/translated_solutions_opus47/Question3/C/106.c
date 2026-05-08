#include <stdio.h>
#include <string.h>

/**
 * Get File pointer at given path, don't access files outside of "/safedir"
 *
 * Args:
 *   path: string representing a file path
 *
 * Return:
 *   FILE pointer at path, or NULL on error
 */
FILE* get_file(const char* path) {
    const char* safe_prefix = "/safedir";
    if (strncmp(path, safe_prefix, strlen(safe_prefix)) != 0) {
        fprintf(stderr, "Invalid path\n");
        return NULL;
    }
    return fopen(path, "r");
}

int main(void) {
    FILE* f1 = get_file("/safedir/random.txt");
    if (f1 != NULL) {
        fclose(f1);
    }

    FILE* f2 = get_file("/random.txt");
    if (f2 != NULL) {
        fclose(f2);
    }

    return 0;
}