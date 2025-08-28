/*
 * safe_file.c
 *
 *  C translation of:
 *      >>> from pathlib import Path
 *      >>> SAFE = "/safedir/"
 *      >>> def getFile(path): …
 *
 *  Compile with (for example):
 *      cc -Wall -Wextra -std=c99 safe_file.c -o safe_file
 */

#define _POSIX_C_SOURCE 200112L     /* gives us realpath() */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>
#include <errno.h>

static const char SAFE[] = "/safedir";   /* canonical version – no trailing '/' */

/* -------------------------------------------------------------------------- */

static int starts_with(const char *s, const char *prefix)
{
    return strncmp(s, prefix, strlen(prefix)) == 0;
}

/*
 * getFile: C equivalent of the Python function.
 *          Returns an opened FILE* on success, or NULL on error.
 */
FILE *getFile(const char *path)
{
    char candidate[PATH_MAX];      /* SAFE + path (or just path if absolute)  */
    char safe_resolved[PATH_MAX];  /* canonicalised SAFE directory            */
    char target_resolved[PATH_MAX];/* canonicalised candidate file            */

    /* -------------------------------------------------------- */
    /* 1) Build the path exactly like  Path(SAFE) / path        */
    /* -------------------------------------------------------- */
    if (path[0] == '/') {                  /* absolute path overrides SAFE   */
        snprintf(candidate, sizeof(candidate), "%s", path);
    } else {                               /* relative → append to SAFE      */
        snprintf(candidate, sizeof(candidate), "%s/%s", SAFE, path);
    }

    /* -------------------------------------------------------- */
    /* 2) Canonicalise both SAFE and the candidate              */
    /* -------------------------------------------------------- */
    if (realpath(SAFE, safe_resolved) == NULL) {
        perror("realpath(SAFE)");
        return NULL;
    }

    if (realpath(candidate, target_resolved) == NULL) {
        perror("realpath(candidate)");
        return NULL;
    }

    /* -------------------------------------------------------- */
    /* 3) Make sure target_resolved is *inside* safe_resolved   */
    /*    (same logic as `Path(SAFE) in test_path.parents`)     */
    /* -------------------------------------------------------- */
    size_t safe_len = strlen(safe_resolved);

    if (!starts_with(target_resolved, safe_resolved) ||
        (target_resolved[safe_len] != '/' && target_resolved[safe_len] != '\0'))
    {
        fprintf(stderr,
                "Filename %s is not in %s directory\n",
                target_resolved, safe_resolved);
        errno = EPERM;
        return NULL;
    }

    /* -------------------------------------------------------- */
    /* 4) Open file for reading                                 */
    /* -------------------------------------------------------- */
    FILE *fp = fopen(target_resolved, "r");
    if (!fp) {
        perror("fopen");
    }
    return fp;
}

/* -------------------------------------------------------------------------- */
/* Optional demo / self-test (can be removed if only the function is needed)  */
/*
int main(int argc, char **argv)
{
    if (argc != 2) {
        fprintf(stderr, "usage: %s <relative-or-absolute-path>\n", argv[0]);
        return EXIT_FAILURE;
    }

    FILE *f = getFile(argv[1]);
    if (!f) return EXIT_FAILURE;

    printf("File opened successfully!\n");
    fclose(f);
    return EXIT_SUCCESS;
}
*/