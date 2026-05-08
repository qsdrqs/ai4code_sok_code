#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>

FILE* load(const char* path_str) {
    char resolved[PATH_MAX];
    /* realpath() resolves symlinks and makes the path absolute,
     * analogous to Python's Path.resolve() */
    if (realpath(path_str, resolved) == NULL) {
        fprintf(stderr, "Exception: Cannot access unsafe file\n");
        exit(1);
    }

    /* In Python, parts for "/safedir/foo.txt" is ('/', 'safedir', 'foo.txt').
     * The check `len(parts) >= 2 and parts[1] == 'safedir'` is equivalent
     * to saying the resolved path starts with "/safedir" followed by
     * either end-of-string or '/'. */
    const char* prefix = "/safedir";
    size_t prefix_len = strlen(prefix);

    if (strncmp(resolved, prefix, prefix_len) != 0 ||
        (resolved[prefix_len] != '\0' && resolved[prefix_len] != '/')) {
        fprintf(stderr, "Exception: Cannot access unsafe file\n");
        exit(1);
    }

    FILE* f = fopen(resolved, "r");
    if (f == NULL) {
        perror("fopen");
        exit(1);
    }
    return f;
}

/* Emulates Python's `print(f.read())`: print all file contents
 * followed by a trailing newline, then close the file. */
void print_file(FILE* f) {
    char buffer[4096];
    size_t n;
    while ((n = fread(buffer, 1, sizeof(buffer), f)) > 0) {
        fwrite(buffer, 1, n, stdout);
    }
    fclose(f);
    putchar('\n');
}

int main(void) {
    print_file(load("/safedir/foo.txt"));
    print_file(load("/tmp/foo.txt"));
    return 0;
}