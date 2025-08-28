#include <stdio.h>
#include <sys/stat.h>
#include <unistd.h>

/**
 * function that takes as input a string path representing a file path
 * Returns a FILE pointer if the file exists and can be opened, NULL otherwise
 */
FILE* get_file_path(const char* path) {
    struct stat st;
    
    // Check if path exists and is a regular file
    if (stat(path, &st) == 0 && S_ISREG(st.st_mode)) {
        return fopen(path, "r");
    } else {
        return NULL;
    }
}