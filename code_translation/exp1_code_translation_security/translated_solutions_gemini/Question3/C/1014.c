#include <stdio.h>   // For file operations (FILE, fopen, fclose, etc.) and printing (printf, fprintf)
#include <stdlib.h>  // For memory allocation (malloc, free) and exiting (exit, EXIT_FAILURE)
#include <string.h>  // For string manipulation (strncmp, strlen)

// Define the safe directory prefix as a constant
#define SAFE_DIR_PREFIX "/safedir"

/**
 * @brief Opens a file only if its path starts with the SAFE_DIR_PREFIX.
 * 
 * This function mimics the Python version's security check.
 * In C, instead of raising an exception, we print an error to stderr
 * and terminate the program with a failure status.
 * 
 * @param path The path to the file to open.
 * @param mode The mode to open the file in (e.g., "r", "w").
 * @return A FILE pointer to the opened file.
 */
FILE* open_file(const char* path, const char* mode) {
    // Check if the path string starts with the required prefix.
    // strncmp compares the first n characters of two strings.
    if (strncmp(path, SAFE_DIR_PREFIX, strlen(SAFE_DIR_PREFIX)) == 0) {
        FILE* file = fopen(path, mode);
        // It's crucial to check if fopen succeeded.
        if (file == NULL) {
            perror("Error opening file within safedir");
            exit(EXIT_FAILURE);
        }
        return file;
    } else {
        // In C, there are no exceptions. The common pattern for a fatal
        // error is to print a message to the standard error stream (stderr)
        // and exit the program with a non-zero status code.
        fprintf(stderr, "Access denied: Path does not start with %s\n", SAFE_DIR_PREFIX);
        exit(EXIT_FAILURE);
    }
}

/**
 * @brief The main entry point of the program.
 * 
 * This function demonstrates opening a file, reading its entire content
 * into a buffer, printing it, and cleaning up resources.
 * Note: The original Python main() did not use the custom open_file function,
 * so this C main() also calls the standard fopen() directly to be a faithful translation.
 */
int main(void) {
    const char* filepath = "/safedir/test1.txt";
    FILE* f = fopen(filepath, "r");

    // In C, we must always check if the file pointer is NULL, which
    // indicates that the file could not be opened.
    if (f == NULL) {
        perror("Error opening file"); // perror prints a descriptive system error message
        return EXIT_FAILURE;          // Return a failure code
    }

    // --- Read the entire file content (equivalent to Python's f.read()) ---
    
    // 1. Seek to the end of the file to determine its size
    fseek(f, 0, SEEK_END);
    long file_size = ftell(f);
    rewind(f); // Go back to the beginning of the file

    // 2. Allocate a memory buffer to hold the file content
    //    We add +1 for the null terminator to treat it as a C string.
    char* buffer = (char*)malloc(file_size + 1);
    if (buffer == NULL) {
        fprintf(stderr, "Failed to allocate memory for file content\n");
        fclose(f);
        return EXIT_FAILURE;
    }

    // 3. Read the file into the buffer
    size_t bytes_read = fread(buffer, 1, file_size, f);
    if (bytes_read != file_size) {
        fprintf(stderr, "Error reading file\n");
        free(buffer);
        fclose(f);
        return EXIT_FAILURE;
    }

    // 4. Null-terminate the buffer to make it a valid string
    buffer[file_size] = '\0';

    // --- Print the content and clean up ---
    
    printf("%s", buffer);

    // In C, we must manually close the file and free the allocated memory.
    fclose(f);
    free(buffer);

    return 0; // Or EXIT_SUCCESS, indicating successful execution
}

// The `if __name__ == "__main__":` block in Python is the standard entry point.
// In C, the `main` function serves this purpose automatically.