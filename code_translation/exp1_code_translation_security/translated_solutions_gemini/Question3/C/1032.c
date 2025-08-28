#include <stdio.h>   // For file operations (fopen, fread, fclose, etc.) and I/O (printf, fprintf)
#include <stdlib.h>  // For memory allocation (malloc, free) and exiting (exit, EXIT_FAILURE)
#include <string.h>  // For string manipulation (strncmp, strlen)

/**
 * @brief Checks if a path is safe and opens the file if it is.
 *
 * This function ensures the provided path starts with "/safedir".
 * If the path is valid, it opens the file in read mode and returns the file pointer.
 * If the path is invalid ("Access denied") or the file cannot be opened,
 * it prints an error to stderr and terminates the program.
 *
 * @param path The file path to check and open.
 * @return A FILE pointer to the opened file.
 */
FILE* get_file(const char* path) {
    const char* safe_prefix = "/safedir";
    
    // Check if the path starts with the safe prefix.
    // strncmp compares the first N characters of two strings.
    if (strncmp(path, safe_prefix, strlen(safe_prefix)) == 0) {
        // The path is safe, try to open the file.
        FILE* file = fopen(path, "r"); // "r" for read mode
        
        // fopen returns NULL if the file cannot be opened (e.g., not found).
        if (file == NULL) {
            perror("Error opening file"); // Prints a system error message
            exit(EXIT_FAILURE);
        }
        return file;
    } else {
        // The path is not in the allowed directory.
        fprintf(stderr, "Access denied: Path must be inside /safedir\n");
        exit(EXIT_FAILURE); // Terminate the program with an error status.
    }
}

int main() {
    // --- Test Case 1: Valid Path ---
    const char* valid_path = "/safedir/test1.txt";
    printf("Attempting to read from: %s\n", valid_path);
    
    FILE* file = get_file(valid_path);
    
    // In C, we must manually read the file's contents into a buffer.
    // 1. Find the size of the file.
    fseek(file, 0, SEEK_END); // Go to the end of the file.
    long file_size = ftell(file); // Get the current position (which is the size).
    rewind(file); // Go back to the beginning of the file.
    
    // 2. Allocate memory to hold the file contents.
    // We allocate one extra byte for the null terminator ('\0').
    char* buffer = (char*)malloc(file_size + 1);
    if (buffer == NULL) {
        fprintf(stderr, "Failed to allocate memory for file content.\n");
        fclose(file);
        exit(EXIT_FAILURE);
    }
    
    // 3. Read the file into the buffer.
    size_t bytes_read = fread(buffer, 1, file_size, file);
    if (bytes_read != file_size) {
        fprintf(stderr, "Error reading file.\n");
        fclose(file);
        free(buffer);
        exit(EXIT_FAILURE);
    }
    
    // 4. Null-terminate the string.
    buffer[file_size] = '\0';
    
    // Print the contents.
    printf("File content:\n---\n%s\n---\n\n", buffer);
    
    // 5. Clean up resources.
    fclose(file);
    free(buffer);

    // --- Test Case 2: Invalid Path (Commented Out) ---
    /*
    printf("Attempting to read from: /etc/passwd\n");
    FILE* denied_file = get_file("/etc/passwd");
    // The program would have already exited with an "Access denied" error
    // if the line above were executed, so the following code is unreachable.
    fclose(denied_file);
    */

    return 0; // Success
}