#include <stdio.h>
#include <sys/stat.h> // Required for stat() and S_ISREG()

/**
 * @brief Takes a string path representing a file path and attempts to open it.
 *
 * This function checks if the given path exists and corresponds to a regular file.
 * If it is, the function opens the file in read mode ('r') and returns a file pointer.
 *
 * @param path A constant character pointer to the null-terminated string
 *             representing the file path.
 *
 * @return A pointer to a FILE object if the file exists and was successfully opened.
 *         NULL if the path does not exist, is not a regular file, or could not be opened.
 */
FILE* get_file_path(const char* path) {
    // Create a stat buffer to hold file information
    struct stat buffer;

    // stat() returns 0 on success.
    // We check if the path exists AND if it's a regular file.
    if (stat(path, &buffer) == 0 && S_ISREG(buffer.st_mode)) {
        // If it is a regular file, open it in read mode
        return fopen(path, "r");
    } else {
        // If the path doesn't exist, is not a regular file (e.g., a directory),
        // or another error occurred with stat(), return NULL.
        return NULL;
    }
}

// --- Example Usage ---
// The following main function demonstrates how to use the get_file_path function.
// It is not part of the direct translation but is included for completeness.

#include <unistd.h> // For rmdir()

int main() {
    // --- Setup for demonstration ---
    // 1. Create a dummy file that exists
    FILE* temp_file = fopen("exists.txt", "w");
    if (temp_file) {
        fprintf(temp_file, "This is a test file.\n");
        fclose(temp_file);
    }

    // 2. Create a dummy directory
    mkdir("a_directory", 0755);


    // --- Test Cases ---

    // Case 1: File exists
    printf("Testing with 'exists.txt'...\n");
    FILE* file_ptr1 = get_file_path("exists.txt");
    if (file_ptr1 != NULL) {
        printf("  Success: 'exists.txt' opened.\n");
        // Remember to close the file when you are done with it!
        fclose(file_ptr1);
    } else {
        printf("  Failure: Could not open 'exists.txt'.\n");
    }
    printf("\n");


    // Case 2: File does not exist
    printf("Testing with 'non_existent_file.txt'...\n");
    FILE* file_ptr2 = get_file_path("non_existent_file.txt");
    if (file_ptr2 != NULL) {
        printf("  Success: 'non_existent_file.txt' opened.\n");
        fclose(file_ptr2);
    } else {
        printf("  Failure: 'non_existent_file.txt' returned NULL as expected.\n");
    }
    printf("\n");


    // Case 3: Path is a directory, not a file
    printf("Testing with 'a_directory'...\n");
    FILE* file_ptr3 = get_file_path("a_directory");
    if (file_ptr3 != NULL) {
        printf("  Success: 'a_directory' opened.\n");
        fclose(file_ptr3);
    } else {
        printf("  Failure: 'a_directory' returned NULL as it's not a regular file.\n");
    }
    printf("\n");


    // --- Cleanup ---
    remove("exists.txt");
    rmdir("a_directory");

    return 0;
}