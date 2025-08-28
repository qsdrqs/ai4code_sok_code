#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/**
 * @brief Validates a path string to ensure it starts with "/safedir".
 *
 * This function is a C translation of the Python `pathlib` logic.
 * It checks if the path is absolute (starts with '/') and if the first
 * component is "safedir".
 *
 * In Python, the function returns a Path object or None. In C, we simulate this:
 * - On success, it returns a new, heap-allocated string containing the path.
 *   The caller is responsible for freeing this memory with free().
 * - On failure (invalid path), it returns NULL.
 *
 * @param p The input path string to validate.
 * @return A new string with the path if valid, otherwise NULL.
 */
char* path_from_str(const char* p) {
    // In Python, Path(p).parts for "/safedir/junk" is ('/', 'safedir', 'junk').
    // The check `path.parts[:2] != ("/", "safedir")` ensures two things:
    // 1. The path is absolute (starts with '/').
    // 2. The first directory component is "safedir".

    // 1. Check if the path is absolute. It must start with '/'.
    if (p == NULL || p[0] != '/') {
        return NULL;
    }

    // 2. Check if the first component is "safedir".
    // We can use strncmp to check for the prefix "/safedir".
    const char* required_prefix = "/safedir";
    size_t prefix_len = strlen(required_prefix); // Length of "/safedir" is 8

    if (strncmp(p, required_prefix, prefix_len) == 0) {
        // The prefix matches. We also need to ensure it's a full component.
        // For example, "/safedirectory" should not match.
        // The character after "/safedir" must be either the end of the string '\0'
        // or another path separator '/'.
        char next_char = p[prefix_len];
        if (next_char == '\0' || next_char == '/') {
            // The path is valid. Return a new copy that the caller must manage.
            char* valid_path = strdup(p);
            if (valid_path == NULL) {
                // In case memory allocation fails
                perror("Failed to allocate memory for path");
                return NULL;
            }
            return valid_path;
        }
    }

    // If any check fails, the path is invalid.
    return NULL;
}

// Helper function to test the path_from_str function and print the result.
void run_test(const char* input_str) {
    char* result = path_from_str(input_str);
    if (result != NULL) {
        printf("%s\n", result);
        free(result); // Clean up the allocated memory
    } else {
        // Python's `print(None)` outputs the string "None".
        printf("None\n");
    }
}

int main() {
    // The following calls replicate the Python `print` statements.
    run_test("/safedir/junk");
    run_test("/bin");
    run_test("/safedir/doesntexist");

    return 0;
}