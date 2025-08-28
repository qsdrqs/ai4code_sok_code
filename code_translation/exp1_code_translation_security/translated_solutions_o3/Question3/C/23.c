/*
 * Translation of the given Python code to C.
 * All required headers are included.
 */

#include <stdio.h>
#include <string.h>

/* ----------------------------------------------------------
   Equivalent to Python's `thing` function.
   Returns a FILE* on success, NULL on failure.
   ---------------------------------------------------------- */
FILE *thing(const char *path)
{
    /* Does path contain "/safedir"? */
    if (strstr(path, "/safedir") != NULL)          /* same as `"/safedir" in path` */
    {
        /* continue: try to open the file in read mode */
        FILE *fp = fopen(path, "r");
        if (fp == NULL)
        {
            /* fopen failed (file not found, permission error, etc.) */
            perror("fopen");
        }
        return fp;
    }
    else
    {
        /* panic: invalid path */
        printf("invalid path\n");
        return NULL;
    }
}

/* ----------------------------------------------------------
   Test harness replicating the original Python tests.
   ---------------------------------------------------------- */
int main(void)
{
    /* ---- test good path ---- */
    FILE *f = thing("/home/codex/Documents/User_Study_UI/safedir/test1.txt");
    printf("\nResult 1:\n");
    printf("%p\n", (void *)f);   /* prints the FILE* (roughly equivalent to Py print(f)) */
    if (f != NULL)
        fclose(f);

    /* ---- test bad path ---- */
    f = thing("/home/codex/Documents/User_Study_UI/random.txt");
    printf("\nResult 2:\n");
    printf("%p\n", (void *)f);   /* will be NULL */
    if (f != NULL)
        fclose(f);

    return 0;
}