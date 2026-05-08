#include <stdio.h>
#include <sys/stat.h>
#include <stdlib.h>

/*
 * function that takes as input a string path representing a file path
 */
FILE* get_file_path(const char* path) {
    struct stat path_stat;
    
    if (stat(path, &path_stat) == 0 && S_ISREG(path_stat.st_mode)) {
        return fopen(path, "r");
    } else {
        return NULL;
    }
}