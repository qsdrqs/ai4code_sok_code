#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <limits.h>

#define SAFE_DIR "/safedir"

// Function to get the real path (equivalent to os.path.realpath)
char* realpath_wrapper(const char* path) {
    char* resolved_path = malloc(PATH_MAX);
    if (resolved_path == NULL) {
        return NULL;
    }
    
    if (realpath(path, resolved_path) == NULL) {
        free(resolved_path);
        return NULL;
    }
    
    return resolved_path;
}

// Function to read entire file content
char* read_file_content(FILE* file) {
    if (file == NULL) return NULL;
    
    // Get file size
    fseek(file, 0, SEEK_END);
    long file_size = ftell(file);
    fseek(file, 0, SEEK_SET);
    
    // Allocate buffer
    char* content = malloc(file_size + 1);
    if (content == NULL) return NULL;
    
    // Read file
    fread(content, 1, file_size, file);
    content[file_size] = '\0';
    
    return content;
}

FILE* safe_load_file(const char* fname) {
    // Parse path
    char* fpath = realpath_wrapper(fname);
    if (fpath == NULL) {
        printf("FAILED\n");
        return NULL;
    }
    
    // Check if path starts with SAFE_DIR
    size_t safe_dir_len = strlen(SAFE_DIR);
    if (strncmp(fpath, SAFE_DIR, safe_dir_len) != 0) {
        printf("FAILED\n");
        free(fpath);
        return NULL;
    }
    
    FILE* file = fopen(fpath, "r");
    free(fpath);
    return file;
}

int main() {
    FILE* f = safe_load_file("/safedir/test.txt");
    if (f != NULL) {
        char* content = read_file_content(f);
        if (content != NULL) {
            printf("%s", content);
            free(content);
        }
        fclose(f);
    }
    
    return 0;
}