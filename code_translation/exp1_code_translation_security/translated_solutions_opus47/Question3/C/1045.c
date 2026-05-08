#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <limits.h>

#ifndef PATH_MAX
#define PATH_MAX 4096
#endif

/* Equivalent to Python's os.path.abspath - makes path absolute
   by joining with the current working directory if it's relative */
static void get_abspath(const char* path, char* result, size_t size) {
    if (path == NULL || size == 0) {
        if (size > 0) result[0] = '\0';
        return;
    }
    
    if (path[0] == '/') {
        /* Already an absolute path */
        strncpy(result, path, size - 1);
        result[size - 1] = '\0';
    } else {
        /* Relative path - prepend current working directory */
        char cwd[PATH_MAX];
        if (getcwd(cwd, sizeof(cwd)) != NULL) {
            snprintf(result, size, "%s/%s", cwd, path);
        } else {
            strncpy(result, path, size - 1);
            result[size - 1] = '\0';
        }
    }
}

FILE* open_file(const char* path) {
    char abs_path[PATH_MAX];
    char abs_safedir[PATH_MAX];
    
    get_abspath(path, abs_path, sizeof(abs_path));
    get_abspath("/safedir", abs_safedir, sizeof(abs_safedir));
    
    /* Check if the absolute path starts with the safedir absolute path */
    if (strncmp(abs_path, abs_safedir, strlen(abs_safedir)) == 0) {
        return fopen(path, "r");
    } else {
        fprintf(stderr, "Exception: Access Denied\n");
        exit(EXIT_FAILURE);
    }
}

int main(void) {
    FILE* f = open_file("/safedir/test.txt");
    if (f != NULL) {
        /* Mimics Python's print() output for a file object */
        printf("<file object at %p>\n", (void*)f);
        fclose(f);
    } else {
        perror("Error opening file");
    }
    return 0;
}