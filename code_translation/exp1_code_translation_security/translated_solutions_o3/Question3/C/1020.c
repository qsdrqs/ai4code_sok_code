#include <stdio.h>
#include <string.h>

/*  funcPath():
 *  Tries to open a file only if the path contains "/safedir".
 *  On success, returns a FILE* (opened for reading).
 *  On failure/unsafe path, returns NULL.
 */
FILE *funcPath(const char *path)
{
    /* Check if "/safedir" appears anywhere in the path */
    if (strstr(path, "/safedir") != NULL)
    {
        /* Attempt to open the file read-only */
        return fopen(path, "r");
    }
    else
    {
        /* Unsafe path => no file handle returned */
        return NULL;
    }
}

int main(void)
{
    const char *testPath = "/safedir/Jim";

    FILE *fp = funcPath(testPath);

    if (fp != NULL)
    {
        /* Successfully opened */
        printf("File opened successfully: %s\n", testPath);
        /* Normally you would read/use the file here … */

        fclose(fp);  /* Always close when done */
    }
    else
    {
        printf("file not found or unsafe for access.\n");
    }

    return 0;
}