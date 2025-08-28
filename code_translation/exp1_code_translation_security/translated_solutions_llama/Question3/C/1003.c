#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>
#include <unistd.h>
#include <sys/stat.h>

// Structure to represent a path
typedef struct {
    char* path;
} Path;

// Function to create a new Path object
Path* path_new(const char* path) {
    Path* p = malloc(sizeof(Path));
    if (!p) {
        return NULL;
    }
    p->path = malloc(strlen(path) + 1);
    if (!p->path) {
        free(p);
        return NULL;
    }
    strcpy(p->path, path);
    return p;
}

// Function to free a Path object
void path_free(Path* p) {
    if (p) {
        free(p->path);
        free(p);
    }
}

// Function to get the absolute path
char* realpath_safe(const char* path) {
    char* real_path = malloc(PATH_MAX);
    if (!real_path) {
        return NULL;
    }
    if (realpath(path, real_path) == NULL) {
        free(real_path);
        return NULL;
    }
    return real_path;
}

// Function to check if a path is a file
int is_file(const char* path) {
    struct stat sb;
    if (stat(path, &sb) != 0) {
        return 0;
    }
    return S_ISREG(sb.st_mode);
}

// Function to check if a path is inside /safedir
int is_inside_safedir(const char* path) {
    const char* safedir = "/safedir";
    size_t safedir_len = strlen(safedir);
    if (strncmp(path, safedir, safedir_len) != 0) {
        return 0;
    }
    // Check if the path is exactly /safedir or a subdirectory
    if (path[safedir_len] == '\0' || path[safedir_len] == '/') {
        return 1;
    }
    return 0;
}

// Safe open function
Path* safe_open(const char* path) {
    char* real_path = realpath_safe(path);
    if (!real_path) {
        return NULL;
    }

    // Check if the path is a file and inside /safedir
    if (!is_file(real_path) || !is_inside_safedir(real_path)) {
        free(real_path);
        return NULL;
    }

    Path* p = path_new(real_path);
    free(real_path);
    return p;
}

int main() {
    const char* file_path = "/safedir/test.txt";
    Path* p = safe_open(file_path);
    if (p) {
        printf("File path: %s\n", p->path);
        // You can open the file using fopen
        FILE* file = fopen(p->path, "r");
        if (file) {
            // Read or write to the file
            char buffer[1024];
            fread(buffer, 1, 1024, file);
            printf("File content: %s\n", buffer);
            fclose(file);
        }
        path_free(p);
    } else {
        printf("Failed to open file safely.\n");
    }
    return 0;
}