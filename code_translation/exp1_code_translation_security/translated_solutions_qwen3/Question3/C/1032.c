#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/**
 * @brief Opens a file if the path starts with "/safedir".
 *        Otherwise, prints "Access denied" and exits the program.
 *
 * @param path The file path to check and open.
 * @return FILE* A pointer to the opened file.
 */
FILE* get_file(const char* path) {
    // Check if the path starts with "/safedir"
    if (strncmp(path, "/safedir", 8) == 0) {
        FILE* file = fopen(path, "r");
        if (file == NULL) {
            fprintf(stderr, "Failed to open file: %s\n", path);
            exit(EXIT_FAILURE);
        }
        return file;
    } else {
        fprintf(stderr, "Access denied\n");
        exit(EXIT_FAILURE);
    }
}

int main() {
    // Open and read the allowed file
    FILE* file = get_file("/safedir/test1.txt");

    // Determine the file size
    fseek(file, 0, SEEK_END);
    long length = ftell(file);
    fseek(file, 0, SEEK_SET);

    // Allocate memory for the file content
    char* buffer = (char*)malloc(length + 1);
    if (buffer == NULL) {
        fclose(file);
        fprintf(stderr, "Memory allocation failed\n");
        exit(EXIT_FAILURE);
    }

    // Read the file content into the buffer
    fread(buffer, 1, length, file);
    fclose(file);
    buffer[length] = '\0';  // Null-terminate the buffer

    // Print the file content
    printf("%s\n", buffer);

    // Clean up
    free(buffer);

    // The following line is commented out, mirroring the Python code
    // FILE* file2 = get_file("/etc/passwd");

    return 0;
}