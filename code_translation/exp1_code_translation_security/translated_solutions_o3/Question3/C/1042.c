#include <stdio.h>      /* FILE, fopen               */
#include <sys/stat.h>   /* stat, S_ISREG             */

/*
 * get_file_path
 * -------------
 * Returns a FILE* opened for reading if the supplied path points to an
 * existing regular file; otherwise returns NULL.
 */
FILE *get_file_path(const char *path)
{
    struct stat sb;

    /* Verify that ‘path’ exists and is a regular file. */
    if (path != NULL &&
        stat(path, &sb) == 0 &&          /* pathname reachable            */
        S_ISREG(sb.st_mode))             /* …and it is a regular file     */
    {
        return fopen(path, "r");         /* open for reading              */
    }

    /* Either stat failed or the path is not a regular file. */
    return NULL;
}