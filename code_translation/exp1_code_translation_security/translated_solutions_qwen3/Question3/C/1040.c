#include <stdio.h>
#include <string.h>

/**
 * Creates a file object for the given path if it starts with "/safedir".
 * Returns a FILE pointer if successful, or NULL otherwise.
 */
FILE* create_file_object(char* path) {
    // Check if the path starts with "/safedir" (8 characters)
    if (strncmp(path, "/safedir", 8) == 0) {
        return fopen(path, "r");
    } else {
        return NULL;
    }
}

int main() {
    // Call the function with the given path
    FILE* result = create_file_object("/etc/passwd");

    // Print the result (FILE pointer or NULL)
    printf("%p\n", (void*)result);

    return 0;
}