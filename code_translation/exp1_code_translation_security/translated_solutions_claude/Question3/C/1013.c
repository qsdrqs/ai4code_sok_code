#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <limits.h>
#include <libgen.h>
#include <sys/stat.h>

// Function to normalize case (convert to lowercase on case-insensitive systems)
void normcase(char *path) {
    // On Unix-like systems, paths are case-sensitive, so no change needed
    // On Windows, you would convert to lowercase here
    // For this example, we'll keep it as-is for Unix-like systems
}

// Function to get the real path (equivalent to os.path.realpath)
char* get_realpath(const char *path) {
    char *resolved_path = malloc(PATH_MAX);
    if (realpath(path, resolved_path) == NULL) {
        free(resolved_path);
        return NULL;
    }
    return resolved_path;
}

// Function to get directory name (equivalent to os.path.dirname)
char* get_dirname(const char *path) {
    char *path_copy = strdup(path);
    char *dir = dirname(path_copy);
    char *result = strdup(dir);
    free(path_copy);
    return result;
}

// Function to find common path prefix
char* commonpath(const char *path1, const char *path2) {
    int i = 0;
    int last_slash = -1;
    
    while (path1[i] && path2[i] && path1[i] == path2[i]) {
        if (path1[i] == '/') {
            last_slash = i;
        }
        i++;
    }
    
    // If one path is a prefix of another and ends with '/'
    if ((!path1[i] && path2[i] == '/') || (!path2[i] && path1[i] == '/') || (!path1[i] && !path2[i])) {
        last_slash = i - 1;
    }
    
    if (last_slash == -1) {
        return strdup("/");
    }
    
    char *result = malloc(last_slash + 2);
    strncpy(result, path1, last_slash + 1);
    result[last_slash + 1] = '\0';
    
    // Remove trailing slash unless it's root
    if (last_slash > 0 && result[last_slash] == '/') {
        result[last_slash] = '\0';
    }
    
    return result;
}

// Function to check if file is in safe directory
int in_safe(const char *fname) {
    char cwd[] = "/safedir";
    normcase(cwd);
    
    char *real_path = get_realpath(fname);
    if (real_path == NULL) {
        return 0;
    }
    
    char *dir_path = get_dirname(real_path);
    normcase(dir_path);
    
    char *common = commonpath(dir_path, cwd);
    int result = (strcmp(common, cwd) == 0);
    
    free(real_path);
    free(dir_path);
    free(common);
    
    return result;
}

// Function to open file safely
FILE* openFile(const char *path) {
    if (in_safe(path)) {
        FILE *file = fopen(path, "r");
        return file;
    } else {
        return NULL;
    }
}

int main() {
    FILE *file = openFile("/env.sh");
    if (file != NULL) {
        printf("File opened successfully\n");
        fclose(file);
    } else {
        printf("NULL\n");
    }
    
    return 0;
}