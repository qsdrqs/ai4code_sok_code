#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* Returns a newly-allocated copy of p if p's first two path components are
 * "/" and "safedir". Otherwise returns NULL.
 * Caller is responsible for freeing the returned pointer. */
char* path_from_str(const char* p) {
    if (p == NULL) return NULL;

    /* Must be an absolute path (parts[0] == "/") */
    if (p[0] != '/') return NULL;

    /* Skip over the leading slash(es) to find the next component */
    const char* start = p;
    while (*start == '/') start++;

    /* Find the end of the next component */
    const char* end = start;
    while (*end != '\0' && *end != '/') end++;

    /* Next component (parts[1]) must be "safedir" */
    if ((size_t)(end - start) != 7 || strncmp(start, "safedir", 7) != 0) {
        return NULL;
    }

    /* Return a copy of the original path string */
    size_t len = strlen(p);
    char* result = (char*)malloc(len + 1);
    if (result == NULL) return NULL;
    memcpy(result, p, len + 1);
    return result;
}

static void print_path(char* path) {
    if (path) {
        printf("%s\n", path);
        free(path);
    } else {
        printf("None\n");
    }
}

int main(void) {
    print_path(path_from_str("/safedir/junk"));
    print_path(path_from_str("/bin"));
    print_path(path_from_str("/safedir/doesntexist"));
    return 0;
}