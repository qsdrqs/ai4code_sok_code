#include <stdio.h>
#include <string.h>

/*  Open the portion of PATH that begins with "/safedir".  
 *  Returns a FILE* (opened read-only, like Path.open()’s default).
 *  On failure (e.g., "/safedir" not present or fopen failure)  
 *  the function returns NULL.
 */
FILE *openFile(const char *path)
{
    const char  *marker = "/safedir";     /* separator we look for          */
    const char  *sub    = strstr(path, marker);  /* find "/safedir" in path */

    if (sub == NULL)
        return NULL;                      /* "/safedir" not found           */

    /* ‘sub’ now points to the first ‘/’ of “/safedir…”.                     */
    /* That corresponds to Python’s  newpath = sep + tail                    */
    /* Default mode for Path.open() is read-text, so we use "r".             */
    return fopen(sub, "r");
}