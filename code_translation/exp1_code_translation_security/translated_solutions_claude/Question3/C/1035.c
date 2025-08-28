#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    char** parts;
    int count;
    char* original;
} Path;

Path* create_path(const char* p) {
    if (!p) return NULL;
    
    Path* path = malloc(sizeof(Path));
    if (!path) return NULL;
    
    // Store original path
    path->original = malloc(strlen(p) + 1);
    strcpy(path->original, p);
    
    // Count parts by counting '/' characters
    int part_count = 0;
    const char* temp = p;
    
    // Handle leading slash
    if (*temp == '/') {
        part_count++;
        temp++;
    }
    
    // Count remaining parts
    while (*temp) {
        if (*temp == '/') {
            part_count++;
        }
        temp++;
    }
    
    // If path ends with content after last '/', count it
    if (strlen(p) > 1 && p[strlen(p)-1] != '/') {
        part_count++;
    }
    
    // Allocate parts array
    path->parts = malloc(part_count * sizeof(char*));
    path->count = part_count;
    
    // Fill parts array
    int index = 0;
    char* path_copy = malloc(strlen(p) + 1);
    strcpy(path_copy, p);
    
    // Handle root "/"
    if (path_copy[0] == '/') {
        path->parts[index] = malloc(2);
        strcpy(path->parts[index], "/");
        index++;
        
        // Parse remaining parts
        char* token = strtok(path_copy + 1, "/");
        while (token != NULL && index < part_count) {
            path->parts[index] = malloc(strlen(token) + 1);
            strcpy(path->parts[index], token);
            index++;
            token = strtok(NULL, "/");
        }
    }
    
    free(path_copy);
    return path;
}

void free_path(Path* path) {
    if (!path) return;
    
    for (int i = 0; i < path->count; i++) {
        free(path->parts[i]);
    }
    free(path->parts);
    free(path->original);
    free(path);
}

Path* path_from_str(const char* p) {
    Path* path = create_path(p);
    if (!path) return NULL;
    
    // Check if path has at least 2 parts and they are "/" and "safedir"
    if (path->count < 2 || 
        strcmp(path->parts[0], "/") != 0 || 
        strcmp(path->parts[1], "safedir") != 0) {
        free_path(path);
        return NULL;
    }
    
    return path;
}

void print_path_result(const char* input) {
    Path* result = path_from_str(input);
    if (result) {
        printf("%s\n", result->original);
        free_path(result);
    } else {
        printf("None\n");
    }
}

int main() {
    print_path_result("/safedir/junk");
    print_path_result("/bin");
    print_path_result("/safedir/doesntexist");
    
    return 0;
}