#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <limits.h>

// Function to normalize path (simplified version of os.path.normpath)
char* normpath(const char* path) {
    char* resolved = malloc(PATH_MAX);
    if (resolved == NULL) {
        return NULL;
    }
    
    // Use realpath to resolve the path, but handle non-existent files
    char* result = realpath(path, resolved);
    if (result == NULL) {
        // If realpath fails, do basic normalization
        strcpy(resolved, path);
        
        // Basic cleanup: remove redundant slashes
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

// Function to read entire file content
char* read_file_content(FILE* file) {
    if (file == NULL) return NULL;
    
    fseek(file, 0, SEEK_END);
    long length = ftell(file);
    fseek(file, 0, SEEK_SET);
    
    if (length <= 0) return strdup("");
    
    char* content = malloc(length + 1);
    if (content == NULL) return NULL;
    
    size_t read_size = fread(content, 1, length, file);
    content[read_size] = '\0';
    
    return content;
}

FILE* get_file(const char* path) {
    char* normalized = normpath(path);
    if (normalized == NULL) {
        printf("Error normalizing path\n");
        return fopen("/dev/null", "r");
    }
    
    printf("%s\n", normalized);
    
    if (strncmp(normalized, "/safedir", 8) != 0) {
        printf("out of bounds\n");
        free(normalized);
        return fopen("/dev/null", "r");
    } else {
        printf("opening file %s\n", normalized);
        FILE* file = fopen(normalized, "r+");
        free(normalized);
        return file;
    }
}

int main() {
    // First file operation
    FILE* f = get_file("/safedir/test1.txt");
    if (f != NULL) {
        char* content = read_file_content(f);
        if (content != NULL) {
            printf("%s", content);
            free(content);
        }
        fclose(f);
    }
    
    // Second file operation  
    FILE* g = get_file("/dev/rand0");
    if (g != NULL) {
        char* content = read_file_content(g);
        if (content != NULL) {
            printf("%s", content);
            free(content);
        }
        fclose(g);
    }
    
    return 0;
}