#include <string.h>     /* For strncmp & strlen            */
#include "file.h"       /* Declares File      + File_create */
#include "exception.h"  /* Declares raise_exception(...)    */

/*
 * Return a File* if the supplied path begins with "/safedir".
 * Otherwise raise an exception and abort execution.
 */
File *get_file(const char *path)
{
    const char *SAFE_PREFIX = "/safedir";

    /* Check that path starts with the safe directory prefix. */
    if (strncmp(path, SAFE_PREFIX, strlen(SAFE_PREFIX)) == 0) {
        /* Path is allowed → create and return File object. */
        return File_create(path);  /* Provided elsewhere */
    }

    /* Unauthorized path → raise an exception. */
    raise_exception("Access denied");
    return NULL;   /* Never reached; satisfies the compiler. */
}