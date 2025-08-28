#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>
#include <unistd.h>

static FILE *load(const char *path_str)
{
    char resolved[PATH_MAX];

    /* Convert to an absolute, canonical path (resolves symlinks, “..”, etc.) */
    if (realpath(path_str, resolved) == NULL) {
        perror("realpath");
        exit(EXIT_FAILURE);
    }

    /* Verify that the path is “/safedir/...”. */
    if (resolved[0] != '/') {
        fprintf(stderr, "Cannot access unsafe file\n");
        exit(EXIT_FAILURE);
    }

    const char *first_component = resolved + 1;          /* skip leading '/' */
    const char *slash          = strchr(first_component, '/');
    if (slash == NULL) {                                 /* path is just "/foo" */
        fprintf(stderr, "Cannot access unsafe file\n");
        exit(EXIT_FAILURE);
    }

    size_t component_len = (size_t)(slash - first_component);
    if (component_len != strlen("safedir") ||
        strncmp(first_component, "safedir", component_len) != 0) {
        fprintf(stderr, "Cannot access unsafe file\n");
        exit(EXIT_FAILURE);
    }

    FILE *fp = fopen(resolved, "r");
    if (!fp) {
        perror("fopen");
        exit(EXIT_FAILURE);
    }
    return fp;
}

static void print_file(FILE *fp)
{
    int ch;
    while ((ch = fgetc(fp)) != EOF)
        putchar(ch);
}

int main(void)
{
    FILE *fp1 = load("/safedir/foo.txt");
    print_file(fp1);
    fclose(fp1);

    putchar('\n');

    /* This call will terminate the program with an error. */
    FILE *fp2 = load("/tmp/foo.txt");
    print_file(fp2);
    fclose(fp2);

    return 0;
}