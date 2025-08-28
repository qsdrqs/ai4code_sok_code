#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/**
 * Checks if the given path starts with "/safedir".
 * Returns a dynamically allocated copy of the path if valid, or NULL otherwise.
 */
char* path_from_str(const char* p) {
    if (p == NULL || p[0] != '/') {
        return NULL; // Not an absolute path
    }

    // Make a copy of the path to tokenize
    char* str_copy = strdup(p);
    if (!str_copy) {
        return NULL; // Memory allocation failed
    }

    char* saveptr;
    char* first_token = strtok_r(str_copy, "/", &saveptr);
    char* second_token = strtok_r(NULL, "/", &saveptr);

    free(str_copy); // Done with the copy

    // Check if the second token is "safedir"
    if (second_token == NULL || strcmp(second_token, "safedir") != 0) {
        return NULL;
    }

    // Return a copy of the original path
    return strdup(p);
}

int main() {
    char* result;

    result = path_from_str("/safedir/junk");
    printf("%s\n", result ? result : "None");
    free(result);

    result = path_from_str("/bin");
    printf("%s\n", result ? result : "None");
    free(result);

    result = path_from_str("/safedir/doesntexist");
    printf("%s\n", result ? result : "None");
    free(result);

    return 0;
}