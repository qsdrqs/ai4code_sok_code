/*
 * return file at file location
 */

#include <stdio.h>
#include <string.h>
#include <ctype.h>

#define PATH_BUF_SIZE 1024   /* plenty of room for a demo */

/* Convert a string to lower-case. */
static void to_lower(char *dst, const char *src)
{
    while (*src)
    {
        *dst++ = (char)tolower((unsigned char)*src++);
    }
    *dst = '\0';
}

/* Trim leading and trailing white-space (in-place). */
static void strip(char *s)
{
    char *start = s;
    char *end;

    /* leading */
    while (isspace((unsigned char)*start))
        ++start;

    /* trailing */
    end = start + strlen(start);
    while (end > start && isspace((unsigned char)*(end - 1)))
        --end;

    /* shift + terminate */
    memmove(s, start, (size_t)(end - start));
    s[end - start] = '\0';
}

/*
 * Return a FILE* corresponding to `file_location` if "/safedir"
 * appears in the path (case-insensitive, leading/trailing spaces removed).
 * Otherwise print an error and return NULL.
 */
FILE *get_file(const char *file_location)
{
    char tmp[PATH_BUF_SIZE];

    if (!file_location)
        return NULL;

    if (strlen(file_location) >= sizeof(tmp))
    {
        fprintf(stderr, "Path too long\n");
        return NULL;
    }

    /* Case-fold + strip (Python’s `casefold().strip()`) */
    to_lower(tmp, file_location);
    strip(tmp);

    if (strstr(tmp, "/safedir") != NULL)
        return fopen(file_location, "r");

    puts("Provided File location is inaccessible");
    return NULL;
}

int main(void)
{
    FILE *fp = get_file("/safedir/test1.txt");
    printf("%p\n", (void *)fp);   /* mimic Python’s `print()` */

    if (fp != NULL)
        fclose(fp);

    return 0;
}