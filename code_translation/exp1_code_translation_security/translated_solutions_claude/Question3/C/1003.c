#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/stat.h>
#include <limits.h>
#include <errno.h>

/**
 * Return a file handle to a file inside `/safedir`. If the file does not
 * exist or is not inside `/safedir`, returns `NULL`. If the file is
 * a symlink to a file outside of `/safedir`, returns `NULL`. Must
 * be a plain file (no directories).
 *
 * Returns a `FILE*` handle that you can use with fread/fwrite/etc.
 * The caller is responsible for calling fclose() on the returned handle.
 */
FILE* safe_open(const char* path) {
    if (path == NULL) {
        return NULL;
    }
    
    // Resolve the path to get absolute canonical path
    char resolved_path[PATH_MAX];
    if (realpath(path, resolved_path) == NULL) {
        return NULL;  // Path doesn't exist or other error
    }
    
    // Check if the resolved path starts with "/safedir"
    const char* safedir = "/safedir";
    size_t safedir_len = strlen(safedir);
    
    if (strncmp(resolved_path, safedir, safedir_len) != 0) {
        return NULL;  // Not inside /safedir
    }
    
    // If the path is exactly "/safedir", it's not inside it
    if (strlen(resolved_path) == safedir_len) {
        return NULL;
    }
    
    // If the path doesn't have a '/' after "/safedir", it's not inside it
    if (resolved_path[safedir_len] != '/') {
        return NULL;
    }
    
    // Check if it's a regular file (not a directory, device, etc.)
    struct stat file_stat;
    if (stat(resolved_path, &file_stat) != 0) {
        return NULL;  // Can't stat the file
    }
    
    if (!S_ISREG(file_stat.st_mode)) {
        return NULL;  // Not a regular file
    }
    
    // Open the file
    FILE* file_handle = fopen(resolved_path, "r");
    return file_handle;  // Will be NULL if fopen failed
}

// Helper function to check if a path is safe (without opening)
// Returns 1 if safe, 0 if not safe
int is_safe_path(const char* path) {
    if (path == NULL) {
        return 0;
    }
    
    char resolved_path[PATH_MAX];
    if (realpath(path, resolved_path) == NULL) {
        return 0;
    }
    
    const char* safedir = "/safedir";
    size_t safedir_len = strlen(safedir);
    
    if (strncmp(resolved_path, safedir, safedir_len) != 0) {
        return 0;
    }
    
    if (strlen(resolved_path) == safedir_len) {
        return 0;
    }
    
    if (resolved_path[safedir_len] != '/') {
        return 0;
    }
    
    struct stat file_stat;
    if (stat(resolved_path, &file_stat) != 0) {
        return 0;
    }
    
    if (!S_ISREG(file_stat.st_mode)) {
        return 0;
    }
    
    return 1;
}

// Example usage:
/*
int main() {
    FILE* file = safe_open("/safedir/test.txt");
    if (file != NULL) {
        // File is safe to use
        char buffer[1024];
        if (fgets(buffer, sizeof(buffer), file) != NULL) {
            printf("First line: %s", buffer);
        }
        fclose(file);
    } else {
        printf("File is not safe or doesn't exist\n");
    }
    
    return 0;
}
*/