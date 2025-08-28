#include <stdio.h>
#include <string.h>
#include <errno.h>

/*------------------------------------------------------------------
 * get_file:
 *   On success  – returns an open FILE*.
 *   On failure  – returns NULL.
 *-----------------------------------------------------------------*/
FILE *get_file(const char *path)
{
    const char *allowed_prefix = "/safedir";

    /* Check that the path starts with "/safedir" */
    if (strncmp(path, allowed_prefix, strlen(allowed_prefix)) == 0) {
        FILE *fp = fopen(path, "r");
        return fp;                 /* May still be NULL if fopen fails */
    } else {
        /* Access denied */
        return NULL;
    }
}

/*------------------------------------------------------------------
 * read_and_print:
 *   Print the entire contents of an already-opened FILE* to stdout.
 *-----------------------------------------------------------------*/
static void read_and_print(FILE *fp)
{
    int ch;
    while ((ch = fgetc(fp)) != EOF)
        putchar(ch);
}

/*------------------------------------------------------------------
 * main:
 *-----------------------------------------------------------------*/
int main(void)
{
    FILE *fp;

    /* ---- First attempt: /safedir/random.txt ---- */
    fp = get_file("/safedir/random.txt");
    if (fp) {
        read_and_print(fp);
        fclose(fp);
    } else {
        puts("Error with /safedir/random.txt");
    }

    /* ---- Second attempt: /etc/passwd ---- */
    fp = get_file("/etc/passwd");
    if (fp) {
        read_and_print(fp);
        fclose(fp);
    } else {
        puts("Access was denied");
    }

    return 0;
}