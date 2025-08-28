/* ----------  translated_code.c  ---------- */
#include <stdio.h>
#include <string.h>
#include <stdbool.h>

/* Returns true if dir starts with "/safedir" */
bool validateDir(const char *dir)
{
    const char *prefix = "/safedir";
    return strncmp(dir, prefix, strlen(prefix)) == 0;
}

/* Opens a file for writing only if the path is inside "/safedir" */
FILE *createFile(const char *dir)
{
    if (!validateDir(dir))
        return NULL;            /* Same idea as Python’s “return None” */

    return fopen(dir, "w");     /* Caller must close the file if non-NULL */
}

int main(void)
{
    /* Print the pointer returned (NULL prints as "(nil)" on most libc’s). */
    printf("%p\n", (void *)createFile("test"));
    printf("%p\n", (void *)createFile("/safedir/testAgain1.txt"));

    return 0;
}