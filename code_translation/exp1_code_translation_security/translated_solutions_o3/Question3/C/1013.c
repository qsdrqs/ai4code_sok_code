#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <libgen.h>   // dirname()
#include <limits.h>   // PATH_MAX
#include <unistd.h>   // realpath()

/*  Equivalent to the nested in_safe() function in Python.
    Returns 1 (`true`) if the file pointed to by fname lives
    somewhere inside "/safedir", 0 (`false`) otherwise.
*/
static int in_safe(const char *fname)
{
    /* Canonicalize the reference directory and the candidate path. */
    const char *safe_root = "/safedir";
    char safe_real[PATH_MAX];
    char file_real[PATH_MAX];

    if (realpath(safe_root, safe_real) == NULL)        /* canonical "/safedir" */
        return 0;
    if (realpath(fname, file_real) == NULL)            /* canonical target     */
        return 0;

    /* Strip the filename to keep only its directory part. */
    char dirbuf[PATH_MAX];
    strncpy(dirbuf, file_real, sizeof(dirbuf));        /* dirname() works in-place */
    dirbuf[sizeof(dirbuf) - 1] = '\0';
    char *dirpath = dirname(dirbuf);

    /* Check if dirpath begins with safe_real and the boundary aligns on '/'. */
    size_t len = strlen(safe_real);
    if (strncmp(dirpath, safe_real, len) != 0)
        return 0;

    /* Either exact match or next char must be a slash to avoid partial prefix. */
    if (dirpath[len] == '\0' || dirpath[len] == '/')
        return 1;

    return 0;
}

/*  Mirrors the Python openFile().  Returns a FILE* on success or NULL on failure. */
FILE *openFile(const char *path)
{
    if (in_safe(path))
        return fopen(path, "r");
    return NULL;
}

/* Simple demo akin to `print(openFile("/env.sh"))` in the original code. */
int main(void)
{
    const char *test_path = "/env.sh";
    FILE *fp = openFile(test_path);

    if (fp)
    {
        printf("Successfully opened %s (FILE* = %p)\n", test_path, (void *)fp);
        fclose(fp);
    }
    else
    {
        printf("Could not open %s (outside safedir or error)\n", test_path);
    }
    return 0;
}