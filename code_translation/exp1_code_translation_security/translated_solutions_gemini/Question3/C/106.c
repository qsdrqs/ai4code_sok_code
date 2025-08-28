#include <stdio.h>
#include <string.h>
#include <stdlib.h>

/**
 * @brief Get a FILE pointer for a given path, ensuring it's inside "/safedir".
 * 
 * This function checks if the requested path starts with the "/safedir" prefix.
 * If the check fails, it prints an error and returns NULL.
 * If the check passes, it attempts to open the file in read mode.
 * 
 * @param path A constant character string representing the file path.
 * @return A FILE pointer on success, or NULL on failure (either due to security
 *         check or if the file cannot be opened).
 */
FILE* get_file(const char* path) {
    const char* safe_prefix = "/safedir";
    
    // Check if the path starts with the safe directory prefix.
    // strncmp returns 0 if the first n characters are equal.
    if (strncmp(path, safe_prefix, strlen(safe_prefix)) != 0) {
        fprintf(stderr, "Error: Invalid path. Access is restricted to '%s'.\n", safe_prefix);
        return NULL;
    }
    
    // Attempt to open the file in read mode ("r").
    FILE* file_ptr = fopen(path, "r");
    
    // fopen returns NULL if the file cannot be opened (e.g., not found, no permission).
    if (file_ptr == NULL) {
        // perror is useful here as it prints a descriptive system error message.
        perror("Error opening file");
    }
    
    return file_ptr;
}

// Main function to demonstrate the usage of get_file, similar to the Python script.
int main() {
    // To make this example fully runnable, we'll create the required directory and file.
    // In a real environment, you would assume these exist.
    system("mkdir -p /safedir");
    FILE* temp_file = fopen("/safedir/random.txt", "w");
    if (temp_file != NULL) {
        fprintf(temp_file, "This is a test file.\n");
        fclose(temp_file);
    }

    printf("--- 1. Attempting to open a valid file ---\n");
    const char* valid_path = "/safedir/random.txt";
    FILE* file1 = get_file(valid_path);
    
    if (file1 != NULL) {
        printf("Successfully opened: %s\n", valid_path);
        // IMPORTANT: Always close a file when you are done with it in C.
        fclose(file1);
    } else {
        printf("Failed to open: %s\n", valid_path);
    }

    printf("\n--- 2. Attempting to open a file outside the safe directory ---\n");
    const char* invalid_path = "/random.txt";
    FILE* file2 = get_file(invalid_path);
    
    if (file2 == NULL) {
        printf("Correctly blocked access to: %s\n", invalid_path);
    } else {
        // This block should not be reached.
        printf("ERROR: Security check FAILED! Opened an invalid path: %s\n", invalid_path);
        fclose(file2);
    }

    return 0;
}