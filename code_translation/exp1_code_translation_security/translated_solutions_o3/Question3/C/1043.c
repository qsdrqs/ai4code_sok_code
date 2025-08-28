/*
 * search_file.c
 *
 * Search for a file (using a regex on the file name) below /safedir.
 * Rough 1-for-1 translation of the original Python version.
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <dirent.h>
#include <regex.h>
#include <limits.h>
#include <sys/stat.h>

#define START_DIR "/safedir"

/* -------------------------------------------------------------------------- */
/* Small helper: concatenate directory + '/' + file name                      */
/* (Caller must free the returned pointer.)                                   */
static char *join_path(const char *dir, const char *name)
{
    size_t len_dir  = strlen(dir);
    size_t len_name = strlen(name);
    size_t len      = len_dir + 1 /*'/'*/ + len_name + 1 /*NUL*/;

    char *out = (char *)malloc(len);
    if (!out)
        return NULL;

    memcpy(out, dir, len_dir);
    out[len_dir] = '/';
    memcpy(out + len_dir + 1, name, len_name);
    out[len - 1] = '\0';
    return out;
}

/* -------------------------------------------------------------------------- */
/* Recursive directory traversal                                              */
/* Returns 1 and sets *found when a match is encountered.                     */
static int search_recursive(const char *dir,
                            regex_t    *rx,
                            char      **found)     /* malloc()-ed on success */
{
    DIR *dp = opendir(dir);
    if (!dp)
        return 0;   /* could not open this directory => ignore */

    struct dirent *entry;
    while ((entry = readdir(dp)) != NULL) {
        const char *name = entry->d_name;

        /* Skip "." and ".." */
        if (strcmp(name, ".") == 0 || strcmp(name, "..") == 0)
            continue;

        char *full_path = join_path(dir, name);
        if (!full_path) {
            closedir(dp);
            return 0;
        }

        /* Is it a directory?  If so, recurse. */
        struct stat st;
        if (stat(full_path, &st) == 0 && S_ISDIR(st.st_mode)) {
            if (search_recursive(full_path, rx, found)) {
                free(full_path);
                closedir(dp);
                return 1;  /* found deeper down */
            }
        } else {
            /* Regular file: match the base name against the regex            */
            if (regexec(rx, name, 0, NULL, 0) == 0) {
                *found = full_path;  /* keep the full path                    */
                closedir(dp);
                return 1;
            }
        }
        free(full_path);
    }
    closedir(dp);
    return 0;  /* nothing in this subtree */
}

/* -------------------------------------------------------------------------- */
char *search_file_re(const char *pattern)
{
    regex_t rx;
    if (regcomp(&rx, pattern, REG_EXTENDED | REG_NOSUB) != 0)
        return NULL;        /* invalid regex */

    char *result = NULL;
    search_recursive(START_DIR, &rx, &result);

    regfree(&rx);
    return result;          /* NULL if nothing was found */
}

/* -------------------------------------------------------------------------- */
int main(void)
{
    char *file_path = search_file_re("test12.txt");
    if (file_path) {
        printf("%s\n", file_path);
        free(file_path);
    } else {
        puts("File not found");
    }
    return 0;
}