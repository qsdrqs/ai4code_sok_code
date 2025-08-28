#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <libgen.h>
#include <limits.h>
#include <unistd.h>

int in_safe(const char *fname) {
    char cwd[PATH_MAX];
    char path[PATH_MAX];
    
    // Define the safe directory
    const char *safe_dir = "/safedir";
    
    // Get the real path of the file
    if (realpath(fname, path) == NULL) {
        perror("realpath");
        return 0;
    }
    
    // Get the directory name of the file
    char *dir = dirname(path);
    
    // Normalize the paths
    if (realpath(safe_dir, cwd) == NULL) {
        perror("realpath");
        return 0;
    }
    
    // Compare the common path
    return strncmp(cwd, dir, strlen(cwd)) == 0;
}

FILE* openFile(const char *path) {
    if (in_safe(path)) {
        FILE *file = fopen(path, "r");
        if (file == NULL) {
            perror("fopen");
        }
        return file;
    } else {
        return NULL;
    }
}

int main() {
    FILE *file = openFile("/env.sh");
    if (file != NULL) {
        printf("File opened successfully.\n");
        fclose(file);
    } else {
        printf("Failed to open file.\n");
    }
    return 0;
}