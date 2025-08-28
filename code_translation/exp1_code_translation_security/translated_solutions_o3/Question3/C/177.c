/*  path2obj.c  */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*  Return a pointer to the last path separator contained in s,
    or NULL if no separator can be found.  Both POSIX ‘/’ and
    Windows ‘\’ separators are recognised.                                       */
static char *find_last_sep(char *s)
{
    char *fwd  = strrchr(s, '/');
    char *back = strrchr(s, '\\');
    return (fwd > back) ? fwd : back;
}

/*  Remove one trailing path component (the ‘basename’) from buf,
    leaving the parent directory in place.  buf is modified in-place
    and will never be NULL on return (it will at least contain an
    empty string).                                                               */
static void chop_basename(char *buf)
{
    char *sep = find_last_sep(buf);
    if (sep)
        *sep = '\0';
    else
        buf[0] = '\0';          /* No separator ⇒ we were already at the top */
}

/*  Drop redundant trailing separators (/// etc.) from buf.                     */
static void rstrip_separators(char *buf)
{
    size_t len = strlen(buf);
    while (len > 1 && (buf[len-1] == '/' || buf[len-1] == '\\'))
        buf[--len] = '\0';
}

/*  Return a FILE* if the path sits underneath a directory called “safedir”,
    otherwise return NULL.                                                       */
FILE *path2obj(const char *filePath)
{
    /* 1. Try to open the file immediately, just like the Python code does.  */
    FILE *fp = fopen(filePath, "r");
    if (!fp)
        return NULL;                             /* could not open file        */

    /* 2. Work on a mutable duplicate of filePath.                            */
    char *work = strdup(filePath);
    if (!work) {                                /* out of memory              */
        fclose(fp);
        return NULL;
    }

    /* 3. Strip the actual filename, keeping only its directory.              */
    chop_basename(work);                        /* dirname(filePath)          */
    rstrip_separators(work);

    /* 4. Ascend the directory hierarchy looking for “safedir”.              */
    while (work[0] != '\0') {
        /* Obtain basename(work).                                             */
        char *base = find_last_sep(work);
        base = base ? base + 1 : work;

        if (strcmp(base, "safedir") == 0) {      /* found it!                 */
            free(work);
            return fp;                          /* hand back the open FILE*   */
        }

        /* Move up one level.                                                 */
        chop_basename(work);
        rstrip_separators(work);
    }

    /* No “safedir” in the path — clean up.                                   */
    free(work);
    fclose(fp);
    return NULL;
}