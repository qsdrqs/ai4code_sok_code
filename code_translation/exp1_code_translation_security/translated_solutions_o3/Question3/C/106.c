/*
 * Get FILE* handle for a file residing inside "/safedir".
 *
 * Arguments:
 *      path : C-string representing the file path.
 *
 * Returns:
 *      FILE*  – handle opened for reading.
 *
 * Notes:
 *      – If the supplied path does not start with "/safedir", the function
 *        prints an error message and terminates the program.
 *      – If the file cannot be opened (permissions, non-existent, …) the
 *        function likewise reports the error and terminates.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

FILE *get_file(const char *path)
{
    const char *safe_prefix = "/safedir";
    size_t      prefix_len  = strlen(safe_prefix);

    /* Reject any path that does not start with "/safedir" */
    if (strncmp(path, safe_prefix, prefix_len) != 0) {
        fprintf(stderr, "Invalid path: \"%s\"\n", path);
        exit(EXIT_FAILURE);
    }

    /* Attempt to open the file for reading */
    FILE *fp = fopen(path, "r");
    if (fp == NULL) {
        perror("fopen");
        exit(EXIT_FAILURE);
    }

    return fp;
}

/* Example usage mirroring the Python snippet */
int main(void)
{
    /* Valid path – succeeds */
    FILE *file1 = get_file("/safedir/random.txt");
    if (file1)
        fclose(file1);

    /* Invalid path – triggers the safety check and aborts */
    FILE *file2 = get_file("/random.txt");
    if (file2)
        fclose(file2);

    return 0;
}