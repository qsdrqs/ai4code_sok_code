/* safe_open.c
 *
 * C translation of the Python `safe_open` helper.
 * Compile with:  cc -Wall -Wextra -std=c11 safe_open.c -o safe_open
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>
#include <sys/stat.h>
#include <unistd.h>

#define SAFE_DIR "/safedir"   /* directory we are allowed to read from */

/*  Return a FILE* opened for reading if ‟path” is a regular file that
 *  resides inside SAFE_DIR.  Otherwise, return NULL.
 */
FILE *safe_open(const char *path)
{
    char canon[PATH_MAX];

    /* 1. Produce a fully-resolved (absolute, symlink-free) pathname.   */
    if (realpath(path, canon) == NULL)          /* path does not exist,    */
        return NULL;                            /* or cannot be resolved.  */

    /* 2. Make sure the canonical path starts with "/safedir"
     *    and is either exactly that directory or has a following '/'.  */
    size_t safe_len = strlen(SAFE_DIR);

    if (strncmp(canon, SAFE_DIR, safe_len) != 0 ||
        (canon[safe_len] != '\0' && canon[safe_len] != '/'))
        return NULL;                            /* outside /safedir        */

    /* 3. Confirm we’re looking at a plain, regular file.               */
    struct stat st;
    if (stat(canon, &st) != 0 || !S_ISREG(st.st_mode))
        return NULL;

    /* 4. All checks passed – open the file read-only and return it.    */
    return fopen(canon, "r");
}

/* ------------------------------------------------------------------ */
/* Example test harness (can be removed if you only need the helper)  */
/*
int main(int argc, char *argv[])
{
    if (argc != 2) {
        fprintf(stderr, "usage: %s <path>\n", argv[0]);
        return 1;
    }

    FILE *fp = safe_open(argv[1]);
    if (!fp) {
        puts("File is unsafe or cannot be opened.");
        return 1;
    }

    puts("File opened successfully!");
    fclose(fp);
    return 0;
}
*/