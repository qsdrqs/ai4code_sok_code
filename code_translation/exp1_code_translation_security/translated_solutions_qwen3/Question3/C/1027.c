#include <stdio.h>   // For file operations and printf
#include <string.h>  // For strncmp

/**
 * @brief Opens a file if its path starts with "/safedir".
 *
 * This function mimics the behavior of the Python function `open_file`.
 * It first checks if the file path starts with "/safedir". If not, it prints
 * an error message to stderr and returns NULL. Then, it attempts to open
 * the file in read mode. If the file cannot be opened, it prints "File not found"
 * to stdout and returns NULL. Otherwise, it returns a pointer to the opened file.
 *
 * @param file_path The path to the file to be opened.
 * @return FILE* A pointer to the opened file, or NULL if an error occurred.
 */
FILE* open_file(const char* file_path) {
    // Check if the file path starts with "/safedir"
    if (strncmp(file_path, "/safedir", 8) != 0) {
        // If not, print an error message and return NULL
        fprintf(stderr, "Exception: File is outside of /safedir\n");
        return NULL;
    }

    // Attempt to open the file in read mode
    FILE* file = fopen(file_path, "r");

    if (!file) {
        // If the file could not be opened, print a message and return NULL
        printf("File not found\n");
        return NULL;
    }

    // Return the successfully opened file pointer
    return file;
}

/**
 * @brief Main function to demonstrate the behavior of `open_file`.
 *
 * This function calls `open_file` with the path "/safedir/Jim" and prints
 * the resulting file pointer. In C, printing a FILE* pointer shows its
 * memory address, which is analogous to printing a file object in Python.
 *
 * @return int Exit status of the program.
 */
int main() {
    // Call the open_file function with a sample path
    FILE* path = open_file("/safedir/Jim");

    // Print the file pointer (or NULL if the file could not be opened)
    printf("%p\n", (void*)path);

    return 0;
}