/*
 * Translation of the Python snippet to C.
 * All standard-library headers that are required are pulled in below.
 * The code keeps the same run-time behaviour:
 *   • Any file that is not beneath “/safedir/” is rejected.
 *   • Missing files are reported as “file-not-found”.
 */

#include <stdio.h>
#include <string.h>
#include <errno.h>

/* Constant that defines the only directory tree we are willing to serve. */
#define SAFE_PREFIX "/safedir/"

/* ------------------------------------------------------------------------- */
/*  A very small “exception” mechanism (error codes)                         */
/* ------------------------------------------------------------------------- */
typedef enum {
    ERR_NONE = 0,
    ERR_PATH_NOT_ALLOWED,   /* equivalent to Python’s PathNotAllowed         */
    ERR_FILE_NOT_FOUND      /* equivalent to Python’s FileNotFoundError      */
} PathError;

/*
 * getFilePath
 * ----------
 * Tries to open a file for reading, provided the path is inside SAFE_PREFIX.
 *
 * On success:
 *      *err = ERR_NONE      – returns a valid FILE * (opened with "r")
 *
 * Failure cases:
 *      1) Path outside SAFE_PREFIX   → *err = ERR_PATH_NOT_ALLOWED,  NULL
 *      2) fopen() fails (no file, permission, …)
 *                                     *err = ERR_FILE_NOT_FOUND,    NULL
 */
static FILE *getFilePath(const char *path, PathError *err)
{
    size_t prefix_len = strlen(SAFE_PREFIX);

    /* Reject any path that does not start with “/safedir/”.  */
    if (strncmp(path, SAFE_PREFIX, prefix_len) != 0) {
        if (err) *err = ERR_PATH_NOT_ALLOWED;
        return NULL;
    }

    /* Attempt to open the file. */
    FILE *fp = fopen(path, "r");
    if (fp == NULL) {
        if (err) *err = ERR_FILE_NOT_FOUND;
        return NULL;
    }

    if (err) *err = ERR_NONE;
    return fp;
}

/* ------------------------------------------------------------------------- */
/*  Main – mirrors the flow of the original Python script                    */
/* ------------------------------------------------------------------------- */
int main(void)
{
    const char *goodPaths[] = {
        SAFE_PREFIX "test1.txt",
        SAFE_PREFIX "test.txt"
    };

    PathError err;
    FILE *f;

    /* ------------------------------------------------------------------ */
    /* Paths that should open without problems                             */
    /* ------------------------------------------------------------------ */
    for (size_t i = 0; i < sizeof(goodPaths) / sizeof(goodPaths[0]); ++i) {
        f = getFilePath(goodPaths[i], &err);
        if (!f) {
            /* In the Python version this would raise;
               here we just print a diagnostic and continue.               */
            fprintf(stderr,
                    "Unexpected failure opening %s (err = %d)\n",
                    goodPaths[i], err);
            continue;
        }
        fclose(f);
    }

    /* ------------------------------------------------------------------ */
    /* A file that does not exist – expect “file-not-found”                */
    /* ------------------------------------------------------------------ */
    f = getFilePath("/safedir/noexist.txt", &err);
    if (f) {
        /* Should not happen; the file is expected to be missing. */
        fclose(f);
    } else if (err == ERR_FILE_NOT_FOUND) {
        /* Expected route — silently swallow like the Python ‘except’. */
    }

    /* ------------------------------------------------------------------ */
    /* A path outside the allowed directory – expect “path not allowed”.   */
    /* ------------------------------------------------------------------ */
    f = getFilePath("not/a/good/path.txt", &err);
    if (f) {
        /* Again, should never happen. */
        fclose(f);
    } else if (err == ERR_PATH_NOT_ALLOWED) {
        /* Expected – matches Python’s PathNotAllowed handling. */
    }

    return 0;
}