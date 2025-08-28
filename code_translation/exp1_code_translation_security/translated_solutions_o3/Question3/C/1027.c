#include <stdio.h>
#include <string.h>
#include <stdlib.h>

FILE *open_file(const char *file_path)
{
    /* Ensure the path starts with "/safedir" */
    const char *SAFE_PREFIX = "/safedir";
    size_t prefix_len       = strlen(SAFE_PREFIX);

    if (strncmp(file_path, SAFE_PREFIX, prefix_len) != 0)
    {
        fprintf(stderr, "File is outside of /safedir\n");
        /* Simulate raising an exception by exiting */
        exit(EXIT_FAILURE);
    }

    /* Attempt to open the file */
    FILE *file_obj = fopen(file_path, "r");
    if (!file_obj)
    {
        printf("File not found\n");
        return NULL;
    }
    return file_obj;
}

int main(void)
{
    FILE *path = open_file("/safedir/Jim");
    printf("%p\n", (void *)path);

    /* Close the file if it was opened */
    if (path)
        fclose(path);
    return 0;
}