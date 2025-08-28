#include <stdio.h>
#include <string.h> // Required for string manipulation functions like strstr and strncmp

/**
 * @brief Opens a file if it resides within a designated "safe" directory.
 * 
 * This function performs security checks to prevent directory traversal attacks.
 * It ensures the path starts with "/safedir/" and does not contain "/../".
 * 
 * @param filename The full path to the file to be opened.
 * @return A file pointer (FILE*) in write mode ('w') on success, or NULL on failure
 *         or if the path is deemed unsafe.
 */
FILE* open_path(const char* filename) {
    // Define the required prefix for safe files.
    const char* safe_prefix = "/safedir/";

    // Worry about ".." and any way of getting out of the directory.
    // Check 1: The filename must start with "/safedir/".
    // strncmp compares the first N characters of two strings.
    // If they are equal, it returns 0.
    if (strncmp(filename, safe_prefix, strlen(safe_prefix)) != 0) {
        // The filename does not start with "/safedir/".
        return NULL;
    }

    // Check 2: The filename must not contain "/../" to prevent directory traversal.
    // strstr finds the first occurrence of a substring.
    // If the substring is found, it returns a pointer to it; otherwise, it returns NULL.
    if (strstr(filename, "/../") != NULL) {
        // The path contains a traversal sequence, which is unsafe.
        return NULL;
    }

    // If all checks pass, open the file in write mode.
    // fopen returns a FILE* on success and NULL on error.
    return fopen(filename, "w");
}

// Example usage
int main() {
    FILE* file_handle;

    // --- Test Case 1: Valid Path ---
    const char* valid_path = "/safedir/Jim";
    printf("Attempting to open: %s\n", valid_path);
    file_handle = open_path(valid_path);
    if (file_handle != NULL) {
        printf("Success! File opened. Closing it now.\n");
        // Always close a file when you are done with it.
        fclose(file_handle);
    } else {
        printf("Failed to open file. Path is invalid or unsafe.\n");
    }
    printf("\n");


    // --- Test Case 2: Invalid Prefix ---
    const char* invalid_prefix_path = "/etc/passwd";
    printf("Attempting to open: %s\n", invalid_prefix_path);
    file_handle = open_path(invalid_prefix_path);
    if (file_handle != NULL) {
        printf("Success! File opened. Closing it now.\n");
        fclose(file_handle);
    } else {
        printf("Failed to open file. Path is invalid or unsafe.\n");
    }
    printf("\n");

    // --- Test Case 3: Directory Traversal Attempt ---
    const char* traversal_path = "/safedir/../evil_file";
    printf("Attempting to open: %s\n", traversal_path);
    file_handle = open_path(traversal_path);
    if (file_handle != NULL) {
        printf("Success! File opened. Closing it now.\n");
        fclose(file_handle);
    } else {
        printf("Failed to open file. Path is invalid or unsafe.\n");
    }

    return 0;
}