#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>
#include <assert.h>
#include <unistd.h>

/*  Return a FILE* if ‟filename” resolves to a path whose second
    path-component is “safedir”; otherwise return NULL.            */
FILE *open_file(const char *filename)
{
    char resolved[PATH_MAX];

    /*  realpath() gives us the canonical absolute path, removing any
        “..” or “.” elements — the closest C equivalent to
        pathlib.Path(filename).resolve() in Python.                 */
    if (realpath(filename, resolved) == NULL)
        return NULL;                 /* cannot be resolved */

    /*  After realpath() an absolute path always starts with ‘/’.
        We want    /safedir/…                                         */
    if (resolved[0] != '/')
        return NULL;

    const char *second = resolved + 1;          /* skip leading “/”     */
    const char *slash  = strchr(second, '/');
    size_t len = slash ? (size_t)(slash - second) : strlen(second);

    if (len == 7 && strncmp(second, "safedir", 7) == 0)
        /*  Inside /safedir → allow the open, read-only like Python’s open()  */
        return fopen(resolved, "r");

    /*  Any other top-level directory is forbidden.  */
    return NULL;
}


/* ────────────────── simple self-test ────────────────── */
static void test(void)
{
    /*  “..” is resolved away → /a/b, which is *not* under /safedir.  */
    assert(open_file("/safedir/../a/b") == NULL);

    /*  Even if the file existed, top-level must still be ‘safedir’.
        We expect open_file() to fail (return NULL).                 */
    assert(open_file("/safedir/a") == NULL);
}

int main(void)
{
    test();
    return 0;
}