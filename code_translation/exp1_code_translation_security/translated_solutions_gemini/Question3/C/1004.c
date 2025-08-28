#include <stdio.h>   // For file operations (fopen, fclose, printf, etc.)
#include <string.h>  // For string manipulation (strncmp, strlen)
#include <stdlib.h>  // For NULL
#include <sys/stat.h> // For mkdir (used to create the /safedir directory)
#include <sys/types.h> // For mkdir

/**
 * @brief Function get_file
 * 
 * @param file_path The path to the file.
 * @return A FILE pointer to the opened file, or NULL on failure.
 * 
 * If the file is outside of the "/safedir" directory, the function
 * prints an error and returns NULL.
 */
FILE* get_file(const char* file_path) {
    const char* safe_prefix = "/safedir";
    
    // Check if file_path starts with "/safedir"
    // strncmp returns 0 if the first N characters are equal.
    if (strncmp(file_path, safe_prefix, strlen(safe_prefix)) != 0) {
        printf("File must be inside /safedir\n");
        return NULL;
    }

    // In C, error handling for file opening is done by checking the return value.
    // fopen returns NULL if it fails, which replaces the try/except block.
    FILE* file_handle = fopen(file_path, "r"); // "r" for read mode
    if (file_handle == NULL) {
        printf("File not found\n");
        return NULL;
    }

    return file_handle;
}

int main() {
    // --- Setup: Create the directory and the test file ---
    // This part corresponds to the Python 'import os' and 'with open(...)'.
    // We create the directory first. The second argument is permissions.
    // This might fail if the directory already exists, but we can ignore the error for this example.
    mkdir("/safedir", 0755); 

    FILE* f = fopen("/safedir/test.txt", "w"); // "w" for write mode
    if (f == NULL) {
        perror("Error creating test file");
        return 1; // Exit with an error code
    }
    fprintf(f, "Hello");
    fclose(f);

    // --- Test Case 1: Successful file access ---
    printf("--- Testing valid path ---\n");
    FILE* test_f = get_file("/safedir/test.txt");
    
    // In C, we must check if the pointer is not NULL before using it.
    if (test_f != NULL) {
        char line_buffer[256]; // Buffer to hold the line read from the file
        
        // fgets reads one line from the file
        if (fgets(line_buffer, sizeof(line_buffer), test_f) != NULL) {
            // Python's print() adds a newline, so we do the same for equivalent output.
            printf("%s\n", line_buffer);
        }
        fclose(test_f); // Corresponds to test_f.close()
    }

    printf("\n");

    // --- Test Case 2: Failed file access (path traversal attempt) ---
    printf("--- Testing invalid path ---\n");
    test_f = get_file("/test.txt"); // Should fail
    
    // This corresponds to 'print(test_f)' where test_f is None
    if (test_f == NULL) {
        printf("Returned value: NULL (as expected)\n");
    } else {
        // This block should not be reached
        printf("Test failed: function returned a valid file pointer.\n");
        fclose(test_f);
    }

    return 0; // Successful execution
}