#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/stat.h> // For mkdir
#include <sys/types.h> // For mkdir

/**
 * @brief Checks if a path is within the allowed '/safedir' directory and opens it.
 *
 * This function mimics the Python version's security check.
 * In C, exceptions are not standard. The common pattern for a fatal error
 * like this is to print a message to stderr and exit the program with a
 * non-zero status code, which is what we do here for "Access denied".
 *
 * @param path The file path to open.
 * @return A FILE pointer to the opened file.
 * @note The program will terminate if the path is invalid.
 */
FILE* get_file(const char* path) {
    const char* prefix = "/safedir";
    // strncmp checks if the first N characters of `path` match `prefix`.
    // N is the length of `prefix`.
    if (strncmp(path, prefix, strlen(prefix)) == 0) {
        FILE* file = fopen(path, "r"); // "r" for read mode
        if (file == NULL) {
            // This handles cases where the file is in /safedir but doesn't exist
            // or can't be opened.
            perror("Error opening file");
            exit(EXIT_FAILURE);
        }
        return file;
    } else {
        // The path is outside the allowed directory.
        // Print to standard error and exit, simulating an unhandled exception.
        fprintf(stderr, "Exception: Access denied to path '%s'\n", path);
        exit(EXIT_FAILURE);
    }
}

/**
 * @brief Reads the entire content of an open file into a string.
 *
 * This function is equivalent to Python's `.read()` method on a file object.
 * It allocates memory for the content, which must be freed by the caller.
 *
 * @param file A valid, open FILE pointer.
 * @return A dynamically allocated string with the file's content.
 */
char* read_entire_file(FILE* file) {
    // Go to the end of the file to determine its size
    fseek(file, 0, SEEK_END);
    long size = ftell(file);
    // Go back to the beginning to read the content
    rewind(file);

    // Allocate enough memory for the entire file content + a null terminator
    char* buffer = (char*)malloc(size + 1);
    if (buffer == NULL) {
        fprintf(stderr, "Error: Could not allocate memory for file content.\n");
        exit(EXIT_FAILURE);
    }

    // Read the file into the buffer
    size_t bytes_read = fread(buffer, 1, size, file);
    if (bytes_read != size) {
        fprintf(stderr, "Error: Failed to read the entire file.\n");
        free(buffer); // Clean up memory before exiting
        exit(EXIT_FAILURE);
    }

    // Add the null terminator to make it a valid C-string
    buffer[size] = '\0';

    return buffer;
}

int main() {
    // --- Dependency Setup ---
    // The prompt states dependencies will be provided. In a self-contained
    // C example, we create them ourselves.
    // This mimics having a '/safedir/test1.txt' file.
    mkdir("/safedir", 0755); // Create the directory (permissions 755)
    FILE* test_file_setup = fopen("/safedir/test1.txt", "w");
    if (test_file_setup) {
        fprintf(test_file_setup, "This is a test file in the safe directory.");
        fclose(test_file_setup);
    } else {
        perror("Failed to set up test file");
        return EXIT_FAILURE;
    }
    // --- End of Setup ---


    // --- First Call (Successful) ---
    // Corresponds to: print(get_file('/safedir/test1.txt').read())
    printf("Attempting to read '/safedir/test1.txt'...\n");
    FILE* file1 = get_file("/safedir/test1.txt");
    char* content1 = read_entire_file(file1);

    printf("Content: %s\n\n", content1);

    // C requires manual resource management.
    free(content1);  // Free the memory allocated by read_entire_file
    fclose(file1);   // Close the file handle


    // --- Second Call (Failure) ---
    // Corresponds to: print(get_file('/etc/passwd').read())
    // This call will cause the program to exit from within get_file().
    // The lines after this will not be executed.
    printf("Attempting to read '/etc/passwd'...\n");
    FILE* file2 = get_file("/etc/passwd");

    // This code is unreachable because get_file will exit.
    char* content2 = read_entire_file(file2);
    printf("%s\n", content2);
    free(content2);
    fclose(file2);


    return EXIT_SUCCESS; // This line will not be reached.
}