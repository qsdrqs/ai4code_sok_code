#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>

// Function to get a file descriptor
FILE* get_file(const char* path) {
    // Check if the path starts with '/safedir'
    if (strncmp(path, "/safedir", 8) == 0) {
        // Open the file in read mode
        return fopen(path, "r");
    } else {
        // Print an error message and exit if access is denied
        fprintf(stderr, "Access denied\n");
        exit(EXIT_FAILURE);
    }
}

int main() {
    // Define the paths to test
    const char* path1 = "/safedir/test1.txt";
    const char* path2 = "/etc/passwd";

    // Get and print the contents of the first file
    FILE* file1 = get_file(path1);
    if (file1 != NULL) {
        char buffer[1024];
        while (fgets(buffer, sizeof(buffer), file1) != NULL) {
            printf("%s", buffer);
        }
        fclose(file1);
    }

    // Attempt to get and print the contents of the second file
    // This should trigger the access denied check
    FILE* file2 = get_file(path2);
    if (file2 != NULL) {
        char buffer[1024];
        while (fgets(buffer, sizeof(buffer), file2) != NULL) {
            printf("%s", buffer);
        }
        fclose(file2);
    }

    return 0;
}