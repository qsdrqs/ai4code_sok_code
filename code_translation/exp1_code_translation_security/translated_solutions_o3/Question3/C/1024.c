#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include <limits.h>

/*
 * Rough C equivalent of the Python function `return_object`.
 *  – Blocks paths that start with "/safedir".
 *  – Tries to open the file in write-mode.
 *  – On success returns a FILE*.
 *  – On the “/safedir” case returns (void *)-1.
 *  – On any other error returns NULL and prints the reason.
 */
void *return_object(const char *path)
{
    char  resolved[PATH_MAX];
    const char *rpath = NULL;

    /* realpath() fails if the file does not yet exist; in that case fall
       back to the original path to mimic Python’s behaviour more closely. */
    if (realpath(path, resolved) != NULL)
        rpath = resolved;
    else
        rpath = path;

    /* Reject anything that begins with "/safedir". */
    if (strncmp(rpath, "/safedir", 8) == 0)
        return (void *)-1;

    /* Try to open the file for writing. */
    FILE *fp = fopen(path, "w");
    if (!fp) {
        fprintf(stderr, "Error: %s\n", strerror(errno));
        return NULL;
    }

    return (void *)fp;
}

int main(void)
{
    const char *file_name = "/tmp/foo";

    void *result = return_object(file_name);
    printf("%p\n", result);

    /* Close the file again if we succeeded. */
    if (result != (void *)-1 && result != NULL)
        fclose((FILE *)result);

    return 0;
}