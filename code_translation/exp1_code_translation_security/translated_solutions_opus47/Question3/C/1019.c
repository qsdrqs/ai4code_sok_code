#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>

#ifndef PATH_MAX
#define PATH_MAX 4096
#endif

/* Normalize a path similar to Python's os.path.normpath */
static void normpath(const char *path, char *result) {
    char buffer[PATH_MAX];
    strncpy(buffer, path, PATH_MAX - 1);
    buffer[PATH_MAX - 1] = '\0';

    if (buffer[0] == '\0') {
        strcpy(result, ".");
        return;
    }

    int is_absolute = (buffer[0] == '/');

    char *components[PATH_MAX];
    int num_components = 0;

    char *token = strtok(buffer, "/");
    while (token != NULL) {
        if (strcmp(token, ".") == 0) {
            /* skip "." components */
        } else if (strcmp(token, "..") == 0) {
            if (num_components > 0 &&
                strcmp(components[num_components - 1], "..") != 0) {
                num_components--;
            } else if (!is_absolute) {
                components[num_components++] = token;
            }
            /* For absolute paths, ".." at the root is dropped */
        } else {
            components[num_components++] = token;
        }
        token = strtok(NULL, "/");
    }

    char *p = result;
    if (is_absolute) {
        *p++ = '/';
    }

    for (int i = 0; i < num_components; i++) {
        if (i > 0) {
            *p++ = '/';
        }
        size_t len = strlen(components[i]);
        memcpy(p, components[i], len);
        p += len;
    }
    *p = '\0';

    if (result[0] == '\0') {
        strcpy(result, ".");
    }
}

FILE *get_file(const char *path) {
    char normalized[PATH_MAX];
    normpath(path, normalized);
    printf("%s\n", normalized);

    if (strncmp(normalized, "/safedir", 8) != 0) {
        printf("out of bounds\n");
        return fopen("/dev/null", "r");
    } else {
        printf("opening file %s\n", normalized);
        return fopen(normalized, "r+");
    }
}

static void print_and_close(FILE *f) {
    if (f) {
        int c;
        while ((c = fgetc(f)) != EOF) {
            putchar(c);
        }
        fclose(f);
    }
    putchar('\n');
}

int main(void) {
    FILE *f = get_file("/safedir/test1.txt");
    print_and_close(f);

    FILE *g = get_file("/dev/rand0");
    print_and_close(g);

    return 0;
}