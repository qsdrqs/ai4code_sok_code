/*
 * demo.c – translation of the given Python code.
 *
 * The program
 *   • normalises a pathname (roughly like os.path.normpath),
 *   • prints that normalised path,
 *   • checks whether it begins with “/safedir”,
 *   • opens either the requested file (read-/write) or /dev/null (read-only),
 *   • shows the file contents on stdout.
 *
 * Only the standard C / POSIX headers are required.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define SAFE_PREFIX  "/safedir"
#define NULL_DEVICE  "/dev/null"

/* ------------------------------------------------------------------ */
/* Very small, self-contained replacement for Python’s os.path.normpath.
 * Works well enough for the demonstration – it
 *   – keeps or removes the leading ‘/’
 *   – collapses “//”                → “/”
 *   – removes “.”                   components
 *   – honours “..”                  (one level up)
 *
 * The returned string is heap-allocated; caller must free().
 */
static char *normpath(const char *path)
{
    size_t len = strlen(path);
    char  *out  = malloc(len + 2);         /* result buffer             */
    char  *tmp  = strdup(path);            /* strtok() scratch-copy     */
    size_t outlen = 0;

    if (!out || !tmp) {
        perror("malloc");
        exit(EXIT_FAILURE);
    }

    /* Remember whether the path is absolute. */
    int absolute = (path[0] == '/');

    /* Tokenise on '/' and process component by component. */
    char *token;
    char *saveptr;
    for (token = strtok_r(tmp, "/", &saveptr);
         token;
         token = strtok_r(NULL, "/", &saveptr))
    {
        if (strcmp(token, "")  == 0 ||          /* empty (caused by //) */
            strcmp(token, ".") == 0)            /* current directory     */
            continue;                           /* ignore                */

        if (strcmp(token, "..") == 0) {         /* go one level up       */
            /* wipe the previous component from out[].                  */
            while (outlen && out[outlen-1] != '/')
                --outlen;
            if (outlen && out[outlen-1] == '/')
                --outlen;                       /* remove the '/' itself */
            while (outlen && out[outlen-1] != '/')
                --outlen;
            continue;
        }

        if (outlen && out[outlen-1] != '/')
            out[outlen++] = '/';

        strcpy(out + outlen, token);
        outlen += strlen(token);
    }

    /* Prepend the leading slash for absolute paths. */
    if (absolute) {
        memmove(out + 1, out, outlen + 1);   /* +1 to copy the '\0'      */
        out[0] = '/';
        ++outlen;
    }

    /* Result must never be empty. */
    if (outlen == 0) {
        strcpy(out, absolute ? "/" : ".");
        outlen = strlen(out);
    }

    /* Remove a trailing slash (except for "/"). */
    if (outlen > 1 && out[outlen-1] == '/')
        out[outlen-1] = '\0';

    free(tmp);
    return out;
}
/* ------------------------------------------------------------------ */

static FILE *get_file(const char *path)
{
    char *normalized = normpath(path);

    printf("%s\n", normalized);

    FILE *fp;
    if (strncmp(normalized, SAFE_PREFIX, strlen(SAFE_PREFIX)) != 0) {
        puts("out of bounds");
        fp = fopen(NULL_DEVICE, "r");
    } else {
        printf("opening file %s\n", normalized);
        fp = fopen(normalized, "r+");
    }

    free(normalized);
    return fp;
}

/* Read the full file and echo its content to stdout. */
static void dump_file(FILE *fp)
{
    if (!fp) {
        puts("(could not open file)");
        return;
    }

    fseek(fp, 0, SEEK_SET);        /* read from the beginning         */

    char  buffer[4096];
    size_t nread;

    while ((nread = fread(buffer, 1, sizeof buffer, fp)) > 0)
        fwrite(buffer, 1, nread, stdout);

    putchar('\n');
    fclose(fp);
}

/* ------------------------------------------------------------------ */

int main(void)
{
    FILE *f = get_file("/safedir/test1.txt");
    dump_file(f);

    FILE *g = get_file("/dev/rand0");
    dump_file(g);

    return 0;
}