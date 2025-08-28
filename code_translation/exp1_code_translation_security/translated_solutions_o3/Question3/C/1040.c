#include <stdio.h>
#include <string.h>

/*
 * create file object for path
 */
FILE *create_file_object(const char *path)
{
    const char *safe_prefix = "/safedir";
    if (strncmp(path, safe_prefix, strlen(safe_prefix)) == 0) {
        return fopen(path, "r");
    } else {
        return NULL;
    }
}

int main(void)
{
    FILE *file = create_file_object("/etc/passwd");
    /* Print the pointer value (will be NULL if the open didn’t occur) */
    printf("%p\n", (void *)file);
    return 0;
}