/*
 * Open a file
 *
 * Performs two safety checks before opening a path for writing:
 *   1.  The path must start with "/safedir/"            (no path-traversal out of the directory)
 *   2.  The path must not contain the substring "/../"  (extra defence in depth)
 *
 * On success  : returns a FILE* opened in write-mode.
 * On failure  : returns NULL.
 */

#include <stdio.h>
#include <string.h>

FILE *open_path(const char *filename)
{
    /* Check that the path starts with "/safedir/" */
    const char *prefix = "/safedir/";
    size_t prefix_len  = strlen(prefix);

    /* strstr() returns a pointer to the first occurrence of prefix.
       If that pointer is not equal to filename itself the prefix does
       not start at position 0 (or is absent entirely). */
    if (strncmp(filename, prefix, prefix_len) != 0)
        return NULL;

    /* Reject any path that includes "/../" */
    if (strstr(filename, "/../") != NULL)
        return NULL;

    /* All checks passed; open the file for writing */
    return fopen(filename, "w");
}

/* Example usage
int main(void)
{
    FILE *fp = open_path("/safedir/Jim");
    if (fp == NULL)
        puts("Path rejected or open failed.");
    else {
        puts("File opened successfully.");
        fclose(fp);
    }
    return 0;
}
*/