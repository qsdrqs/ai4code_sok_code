#include <stdio.h>
#include <string.h>

/*  Rough C equivalent of the Python routine above.
 *  ‑ If the supplied path does not start with "/safedir"
 *    it prints the same error message and returns NULL.
 *  ‑ Otherwise it tries to open the file read-only and
 *    returns the resulting FILE* (or NULL if fopen fails).
 */
FILE *returnFile(const char *path)
{
    const char *PREFIX = "/safedir";
    size_t prefix_len  = strlen(PREFIX);

    if (path == NULL || strncmp(path, PREFIX, prefix_len) != 0) {
        puts("File cannot be accessed");
        return NULL;
    }

    /* Open read-only; adjust mode if something else is required. */
    return fopen(path, "r");
}