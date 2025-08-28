#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <limits.h>
#include <errno.h>

// Function to resolve path (similar to Path.resolve())
char* resolve_path(const char* path_str) {
    char* resolved = malloc(PATH_MAX);
    if (resolved == NULL) {
        return NULL;
    }
    
    if (realpath(path_str, resolved) == NULL) {
        free(resolved);
        return NULL;
    }
    
    return resolved;
}

// Function to split path into parts and count them
int count_path_parts(const char* path) {
    if (path == NULL || strlen(path) == 0) return 0;
    
    int count = 0;
    int len = strlen(path);
    
    // Skip leading slashes
    int i = 0;
    while (i < len && path[i] == '/') i++;
    
    // Count parts separated by '/'
    while (i < len) {
        count++;
        // Skip current part
        while (i < len && path[i] != '/') i++;
        // Skip slashes
        while (i < len && path[i] == '/') i++;
    }
    
    return count;
}

// Function to get the second part of the path
char* get_second_part(const char* path) {
    if (path == NULL || strlen(path) == 0) return NULL;
    
    int len = strlen(path);
    int i = 0;
    int part_count = 0;
    
    // Skip leading slashes
    while (i < len && path[i] == '/') i++;
    
    while (i < len && part_count < 2) {
        int start = i;
        // Find end of current part
        while (i < len && path[i] != '/') i++;
        
        part_count++;
        if (part_count == 2) {
            // Extract second part
            int part_len = i - start;
            char* part = malloc(part_len + 1);
            if (part == NULL) return NULL;
            strncpy(part, path + start, part_len);
            part[part_len] = '\0';
            return part;
        }
        
        // Skip slashes
        while (i < len && path[i] == '/') i++;
    }
    
    return NULL;
}

// Function to read entire file content
char* read_file_content(FILE* file) {
    if (file == NULL) return NULL;
    
    // Get file size
    fseek(file, 0, SEEK_END);
    long size = ftell(file);
    fseek(file, 0, SEEK_SET);
    
    // Allocate buffer
    char* content = malloc(size + 1);
    if (content == NULL) return NULL;
    
    // Read content
    size_t read_size = fread(content, 1, size, file);
    content[read_size] = '\0';
    
    return content;
}

// Main load function
FILE* load(const char* path_str) {
    char* resolved_path = resolve_path(path_str);
    if (resolved_path == NULL) {
        fprintf(stderr, "Error: Cannot resolve path\n");
        exit(1);
    }
    
    // Check if path has at least 2 parts
    int part_count = count_path_parts(resolved_path);
    if (part_count < 2) {
        free(resolved_path);
        fprintf(stderr, "Exception: Cannot access unsafe file\n");
        exit(1);
    }
    
    // Get second part and check if it's 'safedir'
    char* second_part = get_second_part(resolved_path);
    if (second_part == NULL || strcmp(second_part, "safedir") != 0) {
        free(resolved_path);
        if (second_part) free(second_part);
        fprintf(stderr, "Exception: Cannot access unsafe file\n");
        exit(1);
    }
    
    free(second_part);
    
    // Open file
    FILE* file = fopen(resolved_path, "r");
    free(resolved_path);
    
    if (file == NULL) {
        fprintf(stderr, "Error: Cannot open file\n");
        exit(1);
    }
    
    return file;
}

int main() {
    // First call: load("/safedir/foo.txt")
    FILE* file1 = load("/safedir/foo.txt");
    char* content1 = read_file_content(file1);
    if (content1) {
        printf("%s", content1);
        free(content1);
    }
    fclose(file1);
    
    // Second call: load("/tmp/foo.txt") - this will raise exception
    FILE* file2 = load("/tmp/foo.txt");
    char* content2 = read_file_content(file2);
    if (content2) {
        printf("%s", content2);
        free(content2);
    }
    fclose(file2);
    
    return 0;
}