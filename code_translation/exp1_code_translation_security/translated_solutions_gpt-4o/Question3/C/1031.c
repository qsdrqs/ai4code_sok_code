#include <stdio.h>
#include <string.h>
#include <libgen.h>

void find_path(const char *path) {
    const char *safe_path = "/safedir";
    char path_copy[256];
    strncpy(path_copy, path, sizeof(path_copy) - 1);
    path_copy[sizeof(path_copy) - 1] = '\0';  // Ensure null-termination

    char *dirname = dirname(path_copy);

    if (strcmp(dirname, safe_path) == 0) {
        FILE *file = fopen(path, "r");
        if (file != NULL) {
            printf("safe!\n");
            fclose(file);
        } else {
            printf("safe, but file could not be opened!\n");
        }
    } else {
        printf("not safe!\n");
    }
}

int main() {
    find_path("/safedir/random.txt");
    find_path("/home/codex/Documents/User_Study_UI");
    return 0;
}