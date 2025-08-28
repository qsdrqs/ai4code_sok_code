#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <limits.h>
#include <errno.h>

#define SAFE "/safedir/"
#define MAX_PATH_LEN 4096

// Custom exception structure
typedef struct {
    char message[512];
} Exception;

// Function to resolve path (similar to Path.resolve())
char* resolve_path(const char* path) {
    char* resolved = malloc(PATH_MAX);
    if (resolved == NULL) {
        return NULL;
    }
    
    if (realpath(path, resolved) == NULL) {
        free(resolved);
        return NULL;
    }
    
    return resolved;
}

// Function to check if parent_path is a parent of child_path
int is_parent_of(const char* parent_path, const char* child_path) {
    size_t parent_len = strlen(parent_path);
    size_t child_len = strlen(child_path);
    
    // Parent path must be shorter than child path
    if (parent_len >= child_len) {
        return 0;
    }
    
    // Check if child path starts with parent path
    if (strncmp(parent_path, child_path, parent_len) != 0) {
        return 0;
    }
    
    // Ensure the parent path ends with '/' or the next character in child is '/'
    if (parent_path[parent_len - 1] != '/' && child_path[parent_len] != '/') {
        return 0;
    }
    
    return 1;
}

// Function to concatenate paths
char* join_paths(const char* base, const char* path) {
    size_t base_len = strlen(base);
    size_t path_len = strlen(path);
    size_t total_len = base_len + path_len + 1;
    
    char* result = malloc(total_len);
    if (result == NULL) {
        return NULL;
    }
    
    strcpy(result, base);
    strcat(result, path);
    
    return result;
}

FILE* getFile(const char* path, Exception* ex) {
    // Clear exception
    memset(ex, 0, sizeof(Exception));
    
    // Join SAFE directory with the provided path
    char* full_path = join_paths(SAFE, path);
    if (full_path == NULL) {
        snprintf(ex->message, sizeof(ex->message), "Memory allocation failed");
        return NULL;
    }
    
    // Resolve the test path
    char* resolved_test_path = resolve_path(full_path);
    if (resolved_test_path == NULL) {
        snprintf(ex->message, sizeof(ex->message), "Failed to resolve path: %s", full_path);
        free(full_path);
        return NULL;
    }
    
    // Resolve the SAFE directory path
    char* resolved_safe_path = resolve_path(SAFE);
    if (resolved_safe_path == NULL) {
        snprintf(ex->message, sizeof(ex->message), "Failed to resolve safe directory: %s", SAFE);
        free(full_path);
        free(resolved_test_path);
        return NULL;
    }
    
    // Check if the resolved safe path is a parent of the resolved test path
    if (!is_parent_of(resolved_safe_path, resolved_test_path)) {
        snprintf(ex->message, sizeof(ex->message), 
                "Filename %s is not in %s directory", resolved_test_path, resolved_safe_path);
        free(full_path);
        free(resolved_test_path);
        free(resolved_safe_path);
        return NULL;
    }
    
    // Open the file
    FILE* file = fopen(full_path, "r");
    if (file == NULL) {
        snprintf(ex->message, sizeof(ex->message), 
                "Failed to open file %s: %s", full_path, strerror(errno));
    }
    
    // Clean up
    free(full_path);
    free(resolved_test_path);
    free(resolved_safe_path);
    
    return file;
}

// Example usage
int main() {
    Exception ex;
    FILE* file = getFile("test.txt", &ex);
    
    if (file == NULL) {
        printf("Error: %s\n", ex.message);
        return 1;
    }
    
    printf("File opened successfully\n");
    
    // Read and print file contents (example)
    char buffer[256];
    while (fgets(buffer, sizeof(buffer), file) != NULL) {
        printf("%s", buffer);
    }
    
    fclose(file);
    return 0;
}