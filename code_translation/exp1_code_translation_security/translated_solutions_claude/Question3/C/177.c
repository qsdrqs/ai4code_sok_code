#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Helper function to get the directory name (equivalent to os.path.dirname)
char* dirname_helper(const char* path) {
    if (!path || strlen(path) == 0) {
        return NULL;
    }
    
    char* path_copy = malloc(strlen(path) + 1);
    strcpy(path_copy, path);
    
    // Remove trailing slashes
    int len = strlen(path_copy);
    while (len > 1 && (path_copy[len-1] == '/' || path_copy[len-1] == '\\')) {
        path_copy[len-1] = '\0';
        len--;
    }
    
    // Find the last slash
    char* last_slash = strrchr(path_copy, '/');
    char* last_backslash = strrchr(path_copy, '\\');
    
    char* separator = (last_slash > last_backslash) ? last_slash : last_backslash;
    
    if (!separator) {
        free(path_copy);
        return NULL;
    }
    
    *separator = '\0';
    
    // Handle root directory case
    if (strlen(path_copy) == 0) {
        strcpy(path_copy, "/");
    }
    
    return path_copy;
}

// Helper function to get the base name (equivalent to os.path.basename)
char* basename_helper(const char* path) {
    if (!path || strlen(path) == 0) {
        return NULL;
    }
    
    char* path_copy = malloc(strlen(path) + 1);
    strcpy(path_copy, path);
    
    // Remove trailing slashes
    int len = strlen(path_copy);
    while (len > 1 && (path_copy[len-1] == '/' || path_copy[len-1] == '\\')) {
        path_copy[len-1] = '\0';
        len--;
    }
    
    // Find the last slash
    char* last_slash = strrchr(path_copy, '/');
    char* last_backslash = strrchr(path_copy, '\\');
    
    char* separator = (last_slash > last_backslash) ? last_slash : last_backslash;
    
    char* result;
    if (!separator) {
        result = malloc(strlen(path_copy) + 1);
        strcpy(result, path_copy);
    } else {
        result = malloc(strlen(separator + 1) + 1);
        strcpy(result, separator + 1);
    }
    
    free(path_copy);
    return result;
}

FILE* path2obj(const char* filePath) {
    FILE* f = fopen(filePath, "r");  // Fixed: use filePath instead of filepath
    
    char* directory = dirname_helper(filePath);
    
    while (directory && strlen(directory) > 0) {
        char* basename = basename_helper(directory);
        
        if (basename && strcmp(basename, "safedir") == 0) {
            free(basename);
            free(directory);
            return f;
        }
        
        free(basename);
        
        char* parent = dirname_helper(directory);
        free(directory);
        directory = parent;
    }
    
    if (directory) {
        free(directory);
    }
    
    if (f) {
        fclose(f);
    }
    
    return NULL;
}