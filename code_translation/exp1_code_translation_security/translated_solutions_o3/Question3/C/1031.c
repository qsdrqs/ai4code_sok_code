/*
 * Translation of the given Python snippet into C
 * (standard POSIX headers are assumed to be available).
 */

#include <stdio.h>    // printf, fopen, FILE, perror
#include <string.h>   // strcmp, strdup
#include <libgen.h>   // dirname (POSIX)
#include <stdlib.h>   // free, NULL

/* --------------------------------------------------------------------------
 * find_path:
 *   Checks whether `path` is located directly in the directory "/safedir".
 *   If so, opens the file for reading, prints "safe!" and returns the FILE*.
 *   Otherwise, prints "not safe!" and returns NULL.
 * -------------------------------------------------------------------------- */
FILE *find_path(const char *path)
{
    const char *safe_path = "/safedir";
    FILE       *file      = NULL;

    /* dirname() may modify its argument, so work on a writable copy. */
    char *path_copy = strdup(path);          /* allocate duplicate string */
    if (path_copy == NULL) {                 /* allocation failed */
        perror("strdup");
        return NULL;
    }

    char *dir = dirname(path_copy);          /* obtain directory component */

    if (strcmp(dir, safe_path) == 0) {       /* directory is exactly /safedir */
        file = fopen(path, "r");             /* open for reading */
        if (file == NULL)
            perror("fopen");
        printf("safe!\n");
    } else {
        printf("not safe!\n");
    }

    free(path_copy);                         /* cleanup temporary buffer */
    return file;                             /* may be NULL if not safe/failed */
}

/* -------------------------------------------------------------------------- */
int main(void)
{
    find_path("/safedir/random.txt");
    find_path("/home/codex/Documents/User_Study_UI");
    return 0;
}