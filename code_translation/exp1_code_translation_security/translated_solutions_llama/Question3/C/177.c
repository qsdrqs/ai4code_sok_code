#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <dirent.h>
#include <limits.h>

// Function to get the directory path from a full file path
char* get_dir_path(const char* file_path) {
    char* dir_path = strdup(file_path);
    char* last_slash = strrchr(dir_path, '/');
    if (last_slash != NULL) {
        *last_slash = '\0';
    }
    return dir_path;
}

// Function to get the base name from a full file path
char* get_base_name(const char* file_path) {
    char* base_name = strdup(file_path);
    char* last_slash = strrchr(base_name, '/');
    if (last_slash != NULL) {
        base_name = last_slash + 1;
    }
    return base_name;
}

// Function to check if a directory exists
int dir_exists(const char* dir_path) {
    DIR* dir = opendir(dir_path);
    if (dir != NULL) {
        closedir(dir);
        return 1;
    }
    return 0;
}

// Function to open a file and check if it's within a "safedir"
FILE* path2obj(const char* file_path) {
    FILE* file = fopen(file_path, "r");
    if (file == NULL) {
        return NULL;
    }

    char* dir_path = get_dir_path(file_path);
    while (dir_path != NULL && strcmp(dir_path, "") != 0) {
        char* base_name = get_base_name(dir_path);
        if (strcmp(base_name, "safedir") == 0) {
            free(base_name);
            free(dir_path);
            return file;
        }
        free(base_name);
        char* parent_dir = get_dir_path(dir_path);
        free(dir_path);
        dir_path = parent_dir;
    }
    free(dir_path);
    fclose(file);
    return NULL;
}

int main() {
    const char* file_path = "path_to_your_file.txt"; // replace with your file path
    FILE* file = path2obj(file_path);
    if (file != NULL) {
        printf("File found within safedir\n");
        fclose(file);
    } else {
        printf("File not found within safedir\n");
    }
    return 0;
}