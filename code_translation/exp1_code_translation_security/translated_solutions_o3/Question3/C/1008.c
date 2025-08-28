#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* Rough C equivalent of the Python test() function */
FILE *test(const char *path)
{
    /* Check whether "/safedir" appears anywhere in the path */
    if (strstr(path, "/safedir") != NULL)
    {
        /* The Python version calls open(path);                             *
         * in C we use fopen(path,"r") and return the resulting FILE*.      */
        return fopen(path, "r");
    }

    /* Imitate the Python exception by printing a message and terminating. */
    fprintf(stderr, "no access\n");
    exit(EXIT_FAILURE);   /* never returns */
    return NULL;          /* keeps the compiler quiet */
}

int main(void)
{
    /* FILE *j = test("/junk");  // same as the commented-out Python line */
    FILE *k = test("/safedir/junk");

    if (k != NULL)
    {
        /* ‑- Do whatever you need with the file here ‑- */
        fclose(k);
    }

    return 0;
}