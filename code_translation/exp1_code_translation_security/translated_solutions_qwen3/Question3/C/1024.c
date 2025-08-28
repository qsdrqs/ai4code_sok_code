#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include <limits.h>
#include <unistd.h>  // For realpath

// Function to simulate the Python return_object
FILE* return_object(const char* path) {
    char resolved_path[PATH_MAX];

    // Get the real path
    if (realpath(path, resolved_path) == NULL) {
        // If realpath fails, simulate Python's exception by returning None (NULL)
        return NULL;
    }

    // Check if the resolved path starts with "/safedir"
    if (strncmp(resolved_path, "/safedir", 8) == 0) {
        // Simulate returning -1
        return (FILE*)-1;
    }

    // Try to open the file in write mode
    FILE* fp = fopen(path, "w");
    if (fp == NULL) {
        // Print error message and return None (NULL)
        fprintf(stderr, "Error: %s\n", strerror(errno));
        return NULL;
    }

    return fp;
}

int main() {
    const char* file_name = "/tmp/foo";
    FILE* result = return_object(file_name);

    // Simulate Python's print(return_object(file_name))
    if (result == (FILE*)-1) {
        printf("-1\n");
    } else if (result == NULL) {
        printf("None\n");
    } else {
        printf("<_io.TextIOWrapper name='%s' mode='w' encoding='UTF-8'>\n", file_name);
    }

    return 0;
}