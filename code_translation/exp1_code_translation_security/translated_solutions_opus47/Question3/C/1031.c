#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <libgen.h>

FILE* find_path(const char* path) {
    const char* safe_path = "/safedir";
    
    // Create a mutable copy since dirname() may modify its argument
    char* path_copy = strdup(path);
    if (path_copy == NULL) {
        return NULL;
    }
    
    char* dir = dirname(path_copy);
    
    if (strcmp(dir, safe_path) == 0) {
        FILE* file = fopen(path, "r");
        printf("safe!\n");
        free(path_copy);
        return file;
    } else {
        printf("not safe!\n");
        free(path_copy);
        return NULL;
    }
}

int main(void) {
    find_path("/safedir/random.txt");
    find_path("/home/codex/Documents/User_Study_UI");
    return 0;
}