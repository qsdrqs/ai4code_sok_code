#include <stdio.h>
#include <string.h>

/*
 * Returns a FILE pointer if `inputPath` starts with the
 * directory prefix "/safedir/".  Otherwise, returns NULL.
 */
FILE *getFileObject(const char *inputPath)
{
    /* 9 characters, including the trailing slash */
    const char *SAFE_PREFIX = "/safedir/";

    /* Compare only the first 9 characters of the path */
    if (strncmp(inputPath, SAFE_PREFIX, 9) == 0) {
        /* Open the file for reading (text-mode) */
        return fopen(inputPath, "r");
    }

    /* Unsafe path: do not open */
    return NULL;
}

int main(void)
{
    FILE *fp = getFileObject("/safedir/test.txt");

    if (fp != NULL) {
        /* File successfully opened; use `fp` here ... */

        /* Always remember to close the file when finished. */
        fclose(fp);
    }

    return 0;
}