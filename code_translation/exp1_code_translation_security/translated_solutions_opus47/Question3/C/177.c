#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <libgen.h>
#include <limits.h>

FILE* path2obj(const char* filePath) {
    FILE* f = fopen(filePath, "r");
    if (f == NULL) {
        return NULL;
    }
    
    // dirname() and basename() may modify their input, so we use copies
    char pathCopy[PATH_MAX];
    strncpy(pathCopy, filePath, PATH_MAX - 1);
    pathCopy[PATH_MAX - 1] = '\0';
    
    // Get initial directory
    char directory[PATH_MAX];
    strncpy(directory, dirname(pathCopy), PATH_MAX - 1);
    directory[PATH_MAX - 1] = '\0';
    
    // Note: Python's dirname returns "" when exhausted; C's returns "."
    while (strlen(directory) > 0 && strcmp(directory, ".") != 0) {
        // Check if the basename of the current directory is "safedir"
        char buffer[PATH_MAX];
        strncpy(buffer, directory, PATH_MAX - 1);
        buffer[PATH_MAX - 1] = '\0';
        
        if (strcmp(basename(buffer), "safedir") == 0) {
            return f;
        }
        
        // Move to parent directory
        strncpy(buffer, directory, PATH_MAX - 1);
        buffer[PATH_MAX - 1] = '\0';
        char* parent = dirname(buffer);
        
        // Prevent infinite loop at root (dirname("/") returns "/")
        if (strcmp(parent, directory) == 0) {
            break;
        }
        
        strncpy(directory, parent, PATH_MAX - 1);
        directory[PATH_MAX - 1] = '\0';
    }
    
    fclose(f);
    return NULL;
}