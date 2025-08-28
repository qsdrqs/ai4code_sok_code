#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>
#include <unistd.h>

#define SAFE_DIR "/safedir"

/*  safe_load_file -------------------------------------------------
 *  Tries to open ‟fname” for reading, but only if the canonical     *
 *  (real) path begins with SAFE_DIR.                                *
 *  Returns:  FILE *  on success, NULL on failure.                   *
 * ----------------------------------------------------------------*/
static FILE *safe_load_file(const char *fname)
{
    char resolved_path[PATH_MAX];

    /* Resolve symbolic links, “..”, etc. */
    if (realpath(fname, resolved_path) == NULL) {
        perror("realpath");
        return NULL;
    }

    /* Check that the resolved path is inside SAFE_DIR. */
    size_t safe_len = strlen(SAFE_DIR);
    if (strncmp(resolved_path, SAFE_DIR, safe_len) != 0) {
        puts("FAILED");
        return NULL;
    }

    /* Open the file read-only (text mode). */
    return fopen(resolved_path, "r");
}

int main(void)
{
    FILE *f = safe_load_file("/safedir/test.txt");
    if (f == NULL)
        return EXIT_FAILURE;

    /* Print entire file to stdout. */
    int ch;
    while ((ch = fgetc(f)) != EOF)
        putchar(ch);

    fclose(f);
    return EXIT_SUCCESS;
}