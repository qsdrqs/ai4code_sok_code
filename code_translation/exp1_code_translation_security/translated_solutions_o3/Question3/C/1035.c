#include <stdio.h>
#include <string.h>
#include <stdlib.h>

/*
 * Replicates the behaviour of the Python function
 *     def path_from_str(p):
 *         path = Path(p)
 *         if path.parts[:2] != ("/", "safedir"):
 *             return None
 *         return path
 *
 * In C we simply check that the input string
 *   1. starts with "/safedir"
 *   2. is followed either by ‘/’ or the end of the string
 * If the check succeeds we duplicate the string and return it,
 * otherwise we return NULL.
 */
char *path_from_str(const char *p)
{
    const char  prefix[] = "/safedir";          /* what we expect up front   */
    size_t      prefix_len = sizeof(prefix) - 1;/* strlen("/safedir") == 8  */

    if (p == NULL)                    /* defensive: NULL pointer passed in */
        return NULL;

    if (strncmp(p, prefix, prefix_len) != 0)    /* does not start with prefix */
        return NULL;

    /* Make sure the prefix is a complete path component.                    */
    char next = p[prefix_len];                  /* char right after "/safedir" */
    if (next != '/' && next != '\0')            /* “…/safedirX” is not ok     */
        return NULL;

    /* Everything is fine – return a heap-allocated copy of the path.        */
    return strdup(p);   /* caller must free() the result (see main) */
}

int main(void)
{
    char *res;

    res = path_from_str("/safedir/junk");
    printf("%s\n", res ? res : "None");
    free(res);

    res = path_from_str("/bin");
    printf("%s\n", res ? res : "None");
    free(res);

    res = path_from_str("/safedir/doesntexist");
    printf("%s\n", res ? res : "None");
    free(res);

    return 0;
}