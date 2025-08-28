#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <limits.h>
#include <errno.h>

#define SAFE_DIR "/safedir"

// Helper function to normalize path (similar to os.path.normpath)
char* normpath(const char* path) {
    if (!path) return NULL;
    
    char* resolved = malloc(PATH_MAX);
    if (!resolved) return NULL;
    
    // Use realpath to resolve and normalize the path
    // If realpath fails, fall back to simple normalization
    if (realpath(path, resolved) == NULL) {
        // Simple normalization - remove redundant separators and handle basic cases
        strcpy(resolved, path);
        
        // Remove duplicate slashes
        char* src = resolved;
        char* dst = resolved;
        int prev_slash = 0;
        
        while (*src) {
            if (*src == '/') {
                if (!prev_slash) {
                    *dst++ = *src;
                    prev_slash = 1;
                }
            } else {
                *dst++ = *src;
                prev_slash = 0;
            }
            src++;
        }
        *dst = '\0';
        
        // Remove trailing slash if not root
        int len = strlen(resolved);
        if (len > 1 && resolved[len-1] == '/') {
            resolved[len-1] = '\0';
        }
    }
    
    return resolved;
}

// Function to check if string starts with prefix
int startswith(const char* str, const char* prefix) {
    if (!str || !prefix) return 0;
    return strncmp(str, prefix, strlen(prefix)) == 0;
}

FILE* get_file(const char* path) {
    if (!path) {
        fprintf(stderr, "Error: Path must be in SAFE_DIR\n");
        return NULL;
    }
    
    char* normalized_path = normpath(path);
    if (!normalized_path) {
        fprintf(stderr, "Error: Failed to normalize path\n");
        return NULL;
    }
    
    if (!startswith(normalized_path, SAFE_DIR)) {
        fprintf(stderr, "Error: Path must be in SAFE_DIR\n");
        free(normalized_path);
        return NULL;
    }
    
    FILE* file = fopen(path, "r");
    free(normalized_path);
    
    if (!file) {
        fprintf(stderr, "Error: Failed to open file: %s\n", strerror(errno));
        return NULL;
    }
    
    return file;
}