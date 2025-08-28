#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* Equivalent of Python’s get_file() */
FILE *get_file(const char *path)
{
    const char *allowed_prefix = "/safedir";
    size_t prefix_len = strlen(allowed_prefix);

    /* Check that path starts with "/safedir" */
    if (strncmp(path, allowed_prefix, prefix_len) == 0)
    {
        FILE *fp = fopen(path, "r");
        if (!fp)
        {
            perror("fopen failed");
            exit(EXIT_FAILURE);
        }
        return fp;
    }
    else
    {
        fprintf(stderr, "Access denied\n");
        exit(EXIT_FAILURE);
    }
}

int main(void)
{
    char buf[4096];
    size_t n;

    /* First file: "/safedir/test1.txt" */
    FILE *f1 = get_file("/safedir/test1.txt");
    n = fread(buf, 1, sizeof(buf) - 1, f1);
    buf[n] = '\0';
    printf("%s\n", buf);
    fclose(f1);

    /* Second file: "/etc/passwd" (will trigger “Access denied”) */
    FILE *f2 = get_file("/etc/passwd");
    n = fread(buf, 1, sizeof(buf) - 1, f2);
    buf[n] = '\0';
    printf("%s\n", buf);
    fclose(f2);

    return 0;
}