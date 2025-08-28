#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/**
 * @brief Opens a file if the path starts with "/safedir".
 *        Otherwise, prints "Access denied" and exits.
 *
 * @param path The file path to check and open.
 * @return FILE* A pointer to the opened file.
 */
FILE* get_file(char* path) {
    // Check if the path starts with "/safedir"
    if (strncmp(path, "/safedir", 8) == 0) {
        FILE* fp = fopen(path, "r");
        if (!fp) {
            perror("Error opening file");
            exit(EXIT_FAILURE);
        }
        return fp;
    } else {
        fprintf(stderr, "Access denied\n");
        exit(EXIT_FAILURE);
    }
}

/**
 * @brief Reads the entire content of a file into a dynamically allocated buffer.
 *
 * @param fp A pointer to the opened file.
 * @return char* A pointer to the buffer containing the file content.
 */
char* read_all(FILE* fp) {
    // Determine the file size
    fseek(fp, 0, SEEK_END);
    long size = ftell(fp);
    fseek(fp, 0, SEEK_SET);

    // Allocate memory for the file content + null terminator
    char* buffer = (char*)malloc(size + 1);
    if (!buffer) {
        perror("Memory allocation failed");
        exit(EXIT_FAILURE);
    }

    // Read the file content
    size_t read_size = fread(buffer, 1, size, fp);
    buffer[read_size] = '\0'; // Null-terminate the buffer

    return buffer;
}

/**
 * @brief Main function to demonstrate the behavior of the Python code.
 */
int main() {
    // First file: should be allowed
    FILE* f1 = get_file("/safedir/test1.txt");
    char* content1 = read_all(f1);
    printf("%s", content1);
    free(content1);
    fclose(f1);

    // Second file: should be denied
    FILE* f2 = get_file("/etc/passwd");
    char* content2 = read_all(f2);
    printf("%s", content2);
    free(content2);
    fclose(f2);

    return 0;
}