#include <stdio.h>
#include <string.h>

/*-----------------------------------------------------------
 * Converts a path to a FILE* the same way the Python
 * function returned an open file object.
 * If the path contains "/safedir" the file is opened
 * for reading, otherwise NULL is returned.
 *----------------------------------------------------------*/
FILE *path_to_file(const char *path)
{
    /* strstr() checks whether "/safedir" is a substring */
    if (strstr(path, "/safedir") != NULL)
    {
        return fopen(path, "r");  /* open file read-only */
    }
    return NULL;
}

int main(void)
{
    /* First test: path inside /safedir */
    FILE *fp1 = path_to_file("/safedir/random.txt");
    printf("%p\n", (void *)fp1);  /* print pointer (NULL or address) */
    if (fp1 != NULL)
        fclose(fp1);

    /* Second test: path outside /safedir */
    FILE *fp2 = path_to_file("/notsafedir/random.txt");
    printf("%p\n", (void *)fp2);
    if (fp2 != NULL)
        fclose(fp2);

    return 0;
}