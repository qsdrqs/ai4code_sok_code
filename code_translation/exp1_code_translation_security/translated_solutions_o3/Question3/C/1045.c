/*
 * Translation of the Python snippet:
 *
 *     import os
 *
 *     def open_file(path):
 *         if os.path.abspath(path).startswith(os.path.abspath('/safedir')):
 *             return open(path, 'r')
 *         else:
 *             raise Exception('Access Denied')
 *
 *     print(open_file("/safedir/test.txt"))
 */

#include <stdio.h>      /* FILE, fopen, printf           */
#include <stdlib.h>     /* EXIT_SUCCESS / EXIT_FAILURE   */
#include <string.h>     /* strlen, strncmp               */
#include <limits.h>     /* PATH_MAX                      */
#include <errno.h>      /* errno, perror                 >
#include <unistd.h>     /* realpath                      */

#define SAFE_DIR "/safedir"

/* ------------------------------------------------------------ */
/* open_file: returns an opened FILE* on success or NULL on fail */
/* ------------------------------------------------------------ */
FILE *open_file(const char *path)
{
    char resolved_path[PATH_MAX];
    char resolved_safe[PATH_MAX];

    /* Canonicalize the user-supplied path. */
    if (realpath(path, resolved_path) == NULL) {
        perror("realpath (user path)");
        return NULL;
    }

    /* Canonicalize the safe directory once as well. */
    if (realpath(SAFE_DIR, resolved_safe) == NULL) {
        perror("realpath (SAFE_DIR)");
        return NULL;
    }

    /* Check that resolved_path starts with resolved_safe.          */
    /* Note: make sure the match happens on a directory boundary    */
    /*       so that "/safedir123" is NOT accepted.                 */
    size_t safe_len = strlen(resolved_safe);
    if (strncmp(resolved_path, resolved_safe, safe_len) == 0 &&
        (resolved_path[safe_len] == '/' || resolved_path[safe_len] == '\0'))
    {
        /* Path is inside the safe directory: open the file read-only. */
        return fopen(resolved_path, "r");
    }

    /* Otherwise, deny access. */
    fprintf(stderr, "Access Denied\n");
    errno = EACCES;
    return NULL;
}

/* ----------------------------- */
/* Example usage (like Python):  */
/* ----------------------------- */
int main(void)
{
    const char *test_path = "/safedir/test.txt";

    FILE *fp = open_file(test_path);
    if (!fp) {
        /* open_file already printed reason; just exit with failure. */
        return EXIT_FAILURE;
    }

    /* Rough equivalent of Python's 'print(<file object>)'. */
    printf("Opened file successfully: %p\n", (void *)fp);

    fclose(fp);
    return EXIT_SUCCESS;
}