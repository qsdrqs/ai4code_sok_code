/*
 * search file in a path
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <dirent.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <regex.h>
#include <unistd.h>
#include <limits.h>

/*
 * recursive helper: walks a directory tree and returns the first file
 * whose name matches the (already compiled) regex.
 * Caller must free() the returned string.
 */
static char* search_helper(const char* dir_path, regex_t* regex) {
    DIR* dir;
    struct dirent* entry;
    struct stat statbuf;
    char path[PATH_MAX];
    char* result = NULL;
    regmatch_t match;

    if ((dir = opendir(dir_path)) == NULL) {
        return NULL;
    }

    while ((entry = readdir(dir)) != NULL) {
        /* skip "." and ".." */
        if (strcmp(entry->d_name, ".") == 0 || strcmp(entry->d_name, "..") == 0) {
            continue;
        }

        snprintf(path, sizeof(path), "%s/%s", dir_path, entry->d_name);

        if (stat(path, &statbuf) == -1) {
            continue;
        }

        if (S_ISDIR(statbuf.st_mode)) {
            /* recurse into subdirectory */
            result = search_helper(path, regex);
            if (result != NULL) {
                break;
            }
        } else {
            /*
             * Mimic Python's re.match (match at beginning of string):
             * regexec returns 0 on match; we also require rm_so == 0
             * so the match starts at position 0.
             */
            if (regexec(regex, entry->d_name, 1, &match, 0) == 0 && match.rm_so == 0) {
                result = strdup(path);
                break;
            }
        }
    }

    closedir(dir);
    return result;
}

/*
 * search file in a path
 * Caller must free() the returned string (if not NULL).
 */
char* search_file_re(const char* file_path) {
    regex_t regex;
    char* result;

    if (regcomp(&regex, file_path, REG_EXTENDED) != 0) {
        return NULL;
    }

    result = search_helper("/safedir", &regex);
    regfree(&regex);
    return result;
}

/*
 * main function
 */
int main(void) {
    char* file_path = search_file_re("test12.txt");
    if (file_path != NULL) {
        printf("%s\n", file_path);
        free(file_path);
    } else {
        printf("None\n");
    }
    return 0;
}