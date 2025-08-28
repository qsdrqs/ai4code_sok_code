#include <stdio.h>
#include <string.h>

/*
 * read_in:
 * Attempts to open the file specified by `path` **only** if the substring
 * "/safedir" is present in the path.  
 *
 * Returns:
 *   A FILE* on success (or NULL if the file couldn’t be opened or
 *   "/safedir" is not part of the path).
 */
FILE *read_in(const char *path)
{
    /* Check that "/safedir" appears somewhere in the path string */
    if (strstr(path, "/safedir") != NULL) {
        /* Try to open the file in read-text mode, mirroring Python’s default. */
        return fopen(path, "r");
    }

    /* No permitted directory; behave like Python’s bare `return` → NULL. */
    return NULL;
}