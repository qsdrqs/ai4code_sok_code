#include <stdio.h>
#include <stdlib.h>
#include <string.h>

FILE *get_file(const char *path)
{
    /* Allow only files under "/safedir" */
    const char *prefix = "/safedir";
    size_t      plen   = strlen(prefix);

    if (strncmp(path, prefix, plen) == 0) {
        FILE *fp = fopen(path, "r");
        if (fp == NULL) {
            perror("fopen");
            exit(EXIT_FAILURE);
        }
        return fp;
    } else {
        fprintf(stderr, "Access denied\n");
        exit(EXIT_FAILURE);
    }
}

int main(void)
{
    /* Equivalent to: print(get_file('/safedir/test1.txt').read()) */
    FILE *fp = get_file("/safedir/test1.txt");

    int ch;
    while ((ch = fgetc(fp)) != EOF)
        putchar(ch);

    fclose(fp);

    /* Uncommenting the next two lines reproduces the denied-access case */
    /* fp = get_file("/etc/passwd"); */
    /* fclose(fp); */

    return 0;
}