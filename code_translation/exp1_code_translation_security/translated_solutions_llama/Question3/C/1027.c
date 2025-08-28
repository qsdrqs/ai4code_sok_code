#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>

// Function to check if a string starts with a given prefix
int starts_with(const char *str, const char *prefix) {
    return strncmp(str, prefix, strlen(prefix)) == 0;
}

// Function to open a file and perform safety checks
FILE* open_file(const char *file_path) {
    // Safety check: Ensure file path starts with '/safedir'
    if (!starts_with(file_path, "/safedir")) {
        fprintf(stderr, "Error: File is outside of /safedir\n");
        exit(EXIT_FAILURE); // Exit with failure status
    }

    // Attempt to open the file
    FILE *file_obj = fopen(file_path, "r");
    if (file_obj == NULL) {
        // Handle file not found or other IO errors
        if (errno == ENOENT) {
            fprintf(stderr, "File not found\n");
        } else {
            perror("Error opening file");
        }
        return NULL;
    }

    return file_obj;
}

int main() {
    const char *path = "/safedir/Jim";
    FILE *file_ptr = open_file(path);

    // Print the file pointer (not directly printable, but can be used for further operations)
    if (file_ptr != NULL) {
        printf("File opened successfully. File pointer: %p\n", (void*)file_ptr);
    } else {
        printf("Failed to open file.\n");
    }

    return 0;
}