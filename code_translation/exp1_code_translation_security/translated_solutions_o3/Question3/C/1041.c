#include <stdio.h>
#include <stdlib.h>
#include <string.h>

FILE *open_file(const char *path)
{
    const char *allowed_prefix = "/safedir";
    size_t prefix_len          = strlen(allowed_prefix);

    /* Make sure the path starts with "/safedir" */
    if (strncmp(path, allowed_prefix, prefix_len) == 0)
    {
        /* Open the file in read-only mode.  Adjust mode flags if needed. */
        FILE *fp = fopen(path, "r");
        if (!fp)
        {
            perror("Failed to open file");
            exit(EXIT_FAILURE);
        }
        return fp;
    }
    else
    {
        fprintf(stderr, "ValueError: Path must start with /safedir\n");
        exit(EXIT_FAILURE);
    }
}

int main(void)
{
    /* First call: succeeds (assuming file exists). */
    FILE *res1 = open_file("/safedir/random.txt");
    printf("%p\n", (void *)res1);   /* Just prints the pointer, like Python’s repr. */

    /* Second call: fails, mimicking the raised ValueError. */
    FILE *res2 = open_file("blah");
    (void)res2; /* Prevent unused-variable warning; code never reaches here. */

    return 0;
}