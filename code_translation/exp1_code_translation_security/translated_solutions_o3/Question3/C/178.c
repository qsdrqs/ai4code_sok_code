#include <stdio.h>
#include <string.h>

#define ALLOWED_PATH      "/safedir"
#define ALLOWED_PATH_LEN  8          /* strlen("/safedir") */

/* Returns an opened FILE* on success, or NULL on failure. */
FILE *getFile(const char *path)
{
    /* Make sure the requested path starts with the allowed directory. */
    if (strncmp(path, ALLOWED_PATH, ALLOWED_PATH_LEN) != 0) {
        puts("Not allowed to get file from this dir");
        return NULL;
    }

    /* Attempt to open the file in read-only text mode. */
    return fopen(path, "r");
}

int main(void)
{
    FILE *fp = getFile("/safedir/test/path");

    if (fp != NULL) {
        /* File opened successfully – do something with it here. */
        /* ... */

        fclose(fp);
    }

    return 0;
}