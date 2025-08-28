#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

/* ---------------------------------------------------------
   Mimics Python's  check_dir(path)  -----------------------
   ---------------------------------------------------------*/
bool check_dir(const char *path)
{
    /* exact prefix we want: "/safedir" (8 characters) */
    const char *prefix = "/safedir";

    /* Compare the first 8 chars of path with the prefix. */
    if (strncmp(path, prefix, strlen(prefix)) == 0)
        return true;
    else
        return false;
}

/* ---------------------------------------------------------
   Mimics Python's  get_file_object(path)  -----------------
   ---------------------------------------------------------*/
FILE *get_file_object(const char *path)
{
    FILE *file_object = NULL;

    if (check_dir(path))
    {
        file_object = fopen(path, "r");     /* open for reading */

        if (file_object == NULL)            /* fopen failed */
        {
            fprintf(stderr, "Error\n");
            exit(EXIT_FAILURE);
        }
    }
    else
    {
        /* Directory check failed */
        exit(EXIT_FAILURE);
    }

    return file_object;
}

/* ---------------------------------------------------------
   Main test driver (equivalent to the last print statement)
   ---------------------------------------------------------*/
int main(void)
{
    FILE *fp = get_file_object("/safedir/test1.txt");

    /* Print the FILE* pointer value (closest equivalent
       to Python’s printing of the file object itself).    */
    printf("%p\n", (void *)fp);

    /* Clean up */
    if (fp != NULL)
        fclose(fp);

    return 0;
}