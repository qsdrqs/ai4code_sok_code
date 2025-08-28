/*
 * get_file.c
 *
 * Translation of the Python example to C.
 * ----------------------------------------
 * 1. get_file() reproduces the “only open files inside /safedir” rule.
 * 2. main() creates /safedir/test.txt, writes “Hello\n”, then demonstrates
 *    the success and failure code paths exactly like the Python version.
 */

#include <stdio.h>      // FILE, fopen, fgets, printf
#include <string.h>     // strlen, strncmp
#include <stdlib.h>     // EXIT_SUCCESS / EXIT_FAILURE
#include <sys/stat.h>   // mkdir
#include <errno.h>      // errno

/*------------------------------------------------------------*/
/*  Equivalent of Python's get_file()                         */
/*------------------------------------------------------------*/
FILE *get_file(const char *file_path)
{
    const char *SAFE_DIR = "/safedir";
    const size_t SAFE_DIR_LEN = strlen(SAFE_DIR);

    /* 1. Check that the path begins with "/safedir"            */
    if (strncmp(file_path, SAFE_DIR, SAFE_DIR_LEN) != 0)
    {
        printf("File must be inside /safedir\n");
        return NULL;
    }

    /* 2. Try to open the file for reading                      */
    FILE *fp = fopen(file_path, "r");
    if (fp == NULL)
    {
        printf("File not found\n");
        return NULL;
    }
    return fp;
}

/*------------------------------------------------------------*/
/*  Main test harness (mirrors the Python demo)               */
/*------------------------------------------------------------*/
int main(void)
{
    /* Create /safedir if it is not already present */
    /* (Ignore errors if it already exists.)        */
    mkdir("/safedir", 0755);

    /* Create /safedir/test.txt and write "Hello" */
    FILE *f = fopen("/safedir/test.txt", "w");
    if (f == NULL)
    {
        perror("Unable to create /safedir/test.txt");
        return EXIT_FAILURE;
    }
    fprintf(f, "Hello\n");
    fclose(f);

    /* --- First call: should succeed --- */
    FILE *test_f = get_file("/safedir/test.txt");
    if (test_f)
    {
        char buffer[256];
        if (fgets(buffer, sizeof(buffer), test_f) != NULL)
        {
            /* Equivalent to Python's print(test_f.readline()) */
            printf("%s", buffer);
        }
        fclose(test_f);
    }

    /* --- Second call: should fail (outside /safedir) --- */
    test_f = get_file("/test.txt");       /* Should print error and return NULL */
    printf("%p\n", (void *)test_f);       /* Matches Python's 'print(test_f)'   */

    return EXIT_SUCCESS;
}