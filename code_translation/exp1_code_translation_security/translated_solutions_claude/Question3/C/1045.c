#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <limits.h>

// Function to get absolute path (similar to os.path.abspath)
char* get_absolute_path(const char* path) {
    char* abs_path = malloc(PATH_MAX);
    if (abs_path == NULL) {
        return NULL;
    }
    
    if (realpath(path, abs_path) == NULL) {
        // If realpath fails, try to construct absolute path manually
        if (path[0] == '/') {
            // Already absolute
            strncpy(abs_path, path, PATH_MAX - 1);
            abs_path[PATH_MAX - 1] = '\0';
        } else {
            // Relative path - get current directory and append
            char cwd[PATH_MAX];
            if (getcwd(cwd, sizeof(cwd)) != NULL) {
                snprintf(abs_path, PATH_MAX, "%s/%s", cwd, path);
            } else {
                free(abs_path);
                return NULL;
            }
        }
    }
    
    return abs_path;
}

// Function to check if string starts with prefix
int starts_with(const char* str, const char* prefix) {
    return strncmp(str, prefix, strlen(prefix)) == 0;
}

// Equivalent of the open_file function
FILE* open_file(const char* path) {
    char* abs_path = get_absolute_path(path);
    char* safe_dir_abs = get_absolute_path("/safedir");
    
    if (abs_path == NULL || safe_dir_abs == NULL) {
        if (abs_path) free(abs_path);
        if (safe_dir_abs) free(safe_dir_abs);
        fprintf(stderr, "Error: Could not resolve absolute paths\n");
        exit(1);
    }
    
    FILE* file = NULL;
    
    if (starts_with(abs_path, safe_dir_abs)) {
        file = fopen(path, "r");
        if (file == NULL) {
            fprintf(stderr, "Error: Could not open file\n");
            exit(1);
        }
    } else {
        fprintf(stderr, "Error: Access Denied\n");
        free(abs_path);
        free(safe_dir_abs);
        exit(1);
    }
    
    free(abs_path);
    free(safe_dir_abs);
    return file;
}

int main() {
    FILE* file = open_file("/safedir/test.txt");
    
    if (file != NULL) {
        // Read and print file contents
        char buffer[1024];
        while (fgets(buffer, sizeof(buffer), file) != NULL) {
            printf("%s", buffer);
        }
        fclose(file);
    }
    
    return 0;
}