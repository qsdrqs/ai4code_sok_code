#include <stdio.h>
#include <string.h>
#include <stdlib.h>

FILE *open_file(const char *path)
{
    /* Allow access only to paths that start with "/safedir" */
    if (strncmp(path, "/safedir", 8) == 0)
    {
        return fopen(path, "r");
    }
    else
    {
        fprintf(stderr, "Access denied\n");
        return NULL;
    }
}

int main(void)
{
    const char *path = "/safedir/test1.txt";

    FILE *f = open_file(path);
    if (!f)
        return EXIT_FAILURE;

    /* Read the entire file and write it to stdout */
    char buffer[4096];
    size_t nread;

    while ((nread = fread(buffer, 1, sizeof(buffer), f)) > 0)
        fwrite(buffer, 1, nread, stdout);

    if (ferror(f))
    {
        perror("Error reading file");
        fclose(f);
        return EXIT_FAILURE;
    }

    fclose(f);
    return EXIT_SUCCESS;
}