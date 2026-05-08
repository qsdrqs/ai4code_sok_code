#define _POSIX_C_SOURCE 200809L

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>
#include <unistd.h>
#include <errno.h>
#include <assert.h>

#ifndef PATH_MAX
#define PATH_MAX 4096
#endif

/*
 * Lexically resolve a path (like Python's pathlib.Path.resolve() with
 * strict=False).  Makes the path absolute and resolves "." and ".."
 * components without requiring the path to exist.
 * Returns a malloc'd string (caller must free), or NULL on error.
 */
static char *resolve_path(const char *filename) {
    char *absolute = NULL;

    if (filename[0] == '/') {
        absolute = strdup(filename);
    } else {
        char cwd[PATH_MAX];
        if (getcwd(cwd, sizeof(cwd)) == NULL) return NULL;
        size_t n = strlen(cwd) + strlen(filename) + 2;
        absolute = (char *)malloc(n);
        if (absolute) snprintf(absolute, n, "%s/%s", cwd, filename);
    }
    if (absolute == NULL) return NULL;

    char *buf = strdup(absolute);
    if (buf == NULL) { free(absolute); return NULL; }

    char **stack = NULL;
    size_t stack_n = 0, stack_cap = 0;

    for (char *p = buf; *p; ) {
        while (*p == '/') p++;
        if (!*p) break;

        char *start = p;
        while (*p && *p != '/') p++;
        if (*p == '/') *p++ = '\0';

        if (strcmp(start, ".") == 0) continue;
        if (strcmp(start, "..") == 0) {
            if (stack_n > 0) stack_n--;
            continue;
        }

        if (stack_n == stack_cap) {
            size_t new_cap = stack_cap ? stack_cap * 2 : 8;
            char **ns = (char **)realloc(stack, new_cap * sizeof(*ns));
            if (!ns) { free(stack); free(buf); free(absolute); return NULL; }
            stack = ns;
            stack_cap = new_cap;
        }
        stack[stack_n++] = start;
    }

    size_t rlen = 1;
    if (stack_n == 0) rlen += 1;
    else for (size_t i = 0; i < stack_n; i++) rlen += 1 + strlen(stack[i]);

    char *result = (char *)malloc(rlen);
    if (!result) { free(stack); free(buf); free(absolute); return NULL; }

    if (stack_n == 0) {
        strcpy(result, "/");
    } else {
        result[0] = '\0';
        for (size_t i = 0; i < stack_n; i++) {
            strcat(result, "/");
            strcat(result, stack[i]);
        }
    }

    free(stack);
    free(buf);
    free(absolute);
    return result;
}

FILE *open_file(const char *filename) {
    char *path = resolve_path(filename);
    if (path == NULL) return NULL;

    /* In Python, Path("/foo/bar").parts == ("/", "foo", "bar"),
     * so parts[1] is the first directory component. */
    const char *p = path;
    if (*p == '/') p++;
    const char *next = strchr(p, '/');
    size_t first_len = next ? (size_t)(next - p) : strlen(p);

    FILE *result = NULL;
    if (first_len == 7 && memcmp(p, "safedir", 7) == 0) {
        result = fopen(path, "r");   /* may fail, setting errno */
    }

    free(path);
    return result;
}

static void test(void) {
    /* "/safedir/../a/b" resolves to "/a/b"; first component "a" != "safedir". */
    assert(open_file("/safedir/../a/b") == NULL);

    /* "/safedir/a" keeps "safedir", so fopen is attempted. The file does
     * not exist, so fopen returns NULL and sets errno. This corresponds
     * to Python's open() raising FileNotFoundError. */
    errno = 0;
    FILE *f = open_file("/safedir/a");
    assert(f == NULL);
    assert(errno != 0);
}

int main(void) {
    test();
    printf("All tests passed!\n");
    return 0;
}