#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <limits.h>

// Helper function to simulate Python's os.path.abspath
// Returns a dynamically allocated normalized absolute path
char* abspath(const char* path) {
    char fullpath[PATH_MAX];
    if (path[0] == '/') {
        // Already absolute
        strncpy(fullpath, path, sizeof(fullpath) - 1);
        fullpath[sizeof(fullpath) - 1] = '\0';
    } else {
        // Relative path: prepend current working directory
        char cwd[PATH_MAX];
        if (getcwd(cwd, sizeof(cwd)) == NULL) {
            return NULL;
        }
        snprintf(fullpath, sizeof(fullpath), "%s/%s", cwd, path);
    }

    // Tokenize the path and normalize it
    char* path_copy = strdup(fullpath);
    if (!path_copy) return NULL;

    char** components = NULL;
    int component_count = 0;

    char* token = strtok(path_copy, "/");
    while (token != NULL) {
        if (strcmp(token, ".") == 0) {
            // Skip current directory
            token = strtok(NULL, "/");
            continue;
        } else if (strcmp(token, "..") == 0) {
            // Go up one directory
            if (component_count > 0) {
                component_count--;
                components = realloc(components, component_count * sizeof(char*));
            }
        } else {
            // Add valid component
            components = realloc(components, (component_count + 1) * sizeof(char*));
            components[component_count] = strdup(token);
            component_count++;
        }
        token = strtok(NULL, "/");
    }

    // Build normalized path
    int len = component_count + 1; // for leading slash
    for (int i = 0; i < component_count; i++) {
        len += strlen(components[i]) + 1; // +1 for slashes
    }

    char* normalized = malloc(len);
    if (!normalized) {
        for (int i = 0; i < component_count; i++) free(components[i]);
        free(components);
        free(path_copy);
        return NULL;
    }

    normalized[0] = '\0';
    strcat(normalized, "/");

    for (int i = 0; i < component_count; i++) {
        if (i > 0) strcat(normalized, "/");
        strcat(normalized, components[i]);
    }

    // Clean up
    for (int i = 0; i < component_count; i++) free(components[i]);
    free(components);
    free(path_copy);

    return normalized;
}

// Main function to open a file if it's under the safe directory
FILE* open_file(const char* path) {
    char* safe_abs = abspath("/safedir");
    char* input_abs = abspath(path);

    if (!safe_abs || !input_abs) {
        free(safe_abs);
        free(input_abs);
        return NULL;
    }

    FILE* fp = NULL;
    if (strncmp(input_abs, safe_abs, strlen(safe_abs)) == 0) {
        fp = fopen(path, "r");
    }

    free(safe_abs);
    free(input_abs);
    return fp;
}

// Example usage
int main() {
    FILE* fp = open_file("/safedir/test.txt");
    printf("File pointer: %p\n", (void*)fp);
    if (fp) fclose(fp);
    return 0;
}