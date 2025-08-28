#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_COMPONENTS 64

/**
 * @brief Normalize a file path, mimicking Python's os.path.normpath.
 * 
 * This function:
 * - Removes redundant slashes.
 * - Handles '.' and '..' components.
 * - Preserves absolute/relative nature of the path.
 * 
 * @param path The input path to normalize.
 * @return char* The normalized path (must be freed by the caller).
 */
char* normalize_path(const char* path) {
    // Make a copy for tokenization
    char* p = strdup(path);
    if (!p) return NULL;

    int absolute = (path[0] == '/');
    char* saveptr;
    char* token = strtok_r(p, "/", &saveptr);

    char* components[MAX_COMPONENTS];
    int count = 0;

    while (token != NULL) {
        if (strcmp(token, ".") == 0) {
            // Skip current directory
        } else if (strcmp(token, "..") == 0) {
            if (count > 0) {
                // Pop last component
                free(components[count - 1]);
                count--;
            } else {
                // Add ".." if no previous component
                if (count < MAX_COMPONENTS) {
                    components[count] = strdup("..");
                    count++;
                }
            }
        } else {
            // Add normal component
            if (count < MAX_COMPONENTS) {
                components[count] = strdup(token);
                count++;
            }
        }
        token = strtok_r(NULL, "/", &saveptr);
    }

    free(p);

    // Compute length of the final normalized path
    int len = 0;
    if (absolute) len++;

    for (int i = 0; i < count; i++) {
        len += strlen(components[i]);
        if (i < count - 1) len++; // for slash
    }

    if (len == 0) {
        // All components were removed
        return absolute ? strdup("/") : strdup(".");
    }

    char* normalized = malloc(len + 1);
    if (!normalized) return NULL;

    normalized[0] = '\0';

    if (absolute) strcat(normalized, "/");

    for (int i = 0; i < count; i++) {
        strcat(normalized, components[i]);
        if (i < count - 1) strcat(normalized, "/");
        free(components[i]);
    }

    return normalized;
}

/**
 * @brief Get a file handle based on the normalized path.
 * 
 * @param path The input file path.
 * @return FILE* A file handle.
 */
FILE* get_file(const char* path) {
    char* normpath = normalize_path(path);
    printf("%s\n", normpath);

    if (strncmp(normpath, "/safedir", 8) != 0) {
        printf("out of bounds\n");
        FILE* f = fopen("/dev/null", "r");
        free(normpath);
        return f;
    } else {
        printf("opening file %s\n", normpath);
        FILE* f = fopen(normpath, "r+");
        free(normpath);
        return f;
    }
}

/**
 * @brief Main function to demonstrate the behavior of get_file.
 */
int main() {
    FILE* f = get_file("/safedir/test1.txt");
    if (f != NULL) {
        int c;
        while ((c = fgetc(f)) != EOF) {
            putchar(c);
        }
        fclose(f);
    }

    FILE* g = get_file("/dev/rand0");
    if (g != NULL) {
        int c;
        while ((c = fgetc(g)) != EOF) {
            putchar(c);
        }
        fclose(g);
    }

    return 0;
}