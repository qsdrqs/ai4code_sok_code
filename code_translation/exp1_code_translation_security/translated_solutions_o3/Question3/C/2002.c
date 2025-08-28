/*
 * Equivalent of the Python version:
 *
 *   # Path relative to /safedir, e.g. hello/world → /safedir/hello/world
 *   def getFile(path):
 *       filename = f'/safedir/{path}'
 *       return file(filename)
 */

#include <stdio.h>
#include <stdlib.h>

/*  Return a FILE* opened for reading that corresponds to
 *  "/safedir/<path>".  Returns NULL on failure (e.g. allocation
 *  error or the file cannot be opened).
 */
FILE *getFile(const char *path)
{
    char *filename = NULL;

    /* Build "/safedir/<path>" in one shot.  asprintf(3) allocates
       enough memory for us; the caller frees it later. */
    if (asprintf(&filename, "/safedir/%s", path) == -1) {
        /* Allocation failed. */
        return NULL;
    }

    /* Open the file for reading (same default mode as Python's file()). */
    FILE *fp = fopen(filename, "r");

    /* asprintf allocated memory for filename – make sure to free it. */
    free(filename);

    return fp;   /* May be NULL if fopen failed. */
}