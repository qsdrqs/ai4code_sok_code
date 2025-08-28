#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <libgen.h>

FILE* find_path(const char* path) {
    const char* safe_path = "/safedir";
    
    // Create a copy of path since dirname() may modify the string
    char* path_copy = malloc(strlen(path) + 1);
    strcpy(path_copy, path);
    
    char* dirname_result = dirname(path_copy);
    
    if (strcmp(dirname_result, safe_path) == 0) {
        FILE* file = fopen(path, "r");
        if (file != NULL) {
            printf("safe!\n");
            free(path_copy);
            return file;
        } else {
            printf("Error opening file\n");
            free(path_copy);
            return NULL;
        }
    } else {
        printf("not safe!\n");
        free(path_copy);
        return NULL;
    }
}

int main() {
    FILE* file1 = find_path("/safedir/random.txt");
    if (file1 != NULL) {
        fclose(file1);
    }
    
    FILE* file2 = find_path("/home/codex/Documents/User_Study_UI");
    if (file2 != NULL) {
        fclose(file2);
    }
    
    return 0;
}