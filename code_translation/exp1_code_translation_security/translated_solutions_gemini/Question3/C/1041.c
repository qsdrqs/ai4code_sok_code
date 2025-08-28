#include <stdio.h>   // For FILE, fopen, printf, fprintf, stderr, fclose
#include <stdlib.h>  // For exit, EXIT_FAILURE, EXIT_SUCCESS
#include <string.h>  // For strncmp, strlen
#include <sys/stat.h> // For mkdir

/**
 * @brief Opens a file if its path is within the "/safedir" directory.
 *
 * This function mimics the Python version's behavior. It checks if the provided
 * path starts with "/safedir".
 *
 * @param path The path to the file to open.
 * @return A FILE pointer to the opened file on success.
 *
 * @note On failure (if the path is invalid), this function prints an error
 * message to stderr and terminates the program, simulating an uncaught exception.
 */
FILE* open_file(const char* path) {
    const char* prefix = "/safedir";
    // In C, we can check if a string starts with a prefix using strncmp.
    // We compare the first `strlen(prefix)` characters of both strings.
    if (strncmp(path, prefix, strlen(prefix)) == 0) {
        // The 'r' stands for "read mode", which is the default for Python's open().
        FILE* file_handle = fopen(path, "r");
        if (file_handle == NULL) {
            // This handles cases where the file doesn't exist, permissions are wrong, etc.
            perror("Error opening file");
            exit(EXIT_FAILURE);
        }
        return file_handle;
    } else {
        // This mimics Python's `raise ValueError`. We print to the standard error
        // stream and exit the program with a failure status.
        fprintf(stderr, "ValueError: Path must start with /safedir\n");
        exit(EXIT_FAILURE);
    }
}

int main() {
    // --- Setup: Create a directory and file for the successful test case ---
    // The 0777 is the permission mode. This call might fail if the directory
    // already exists, but we can ignore that for this example.
    mkdir("/safedir", 0777);

    // Create a dummy file to ensure fopen succeeds.
    FILE* temp_file = fopen("/safedir/random.txt", "w");
    if (temp_file != NULL) {
        fprintf(temp_file, "hello world");
        fclose(temp_file);
    }
    // --- End Setup ---


    // Test Case 1: Successful call
    printf("Attempting to open '/safedir/random.txt'...\n");
    FILE* res1 = open_file("/safedir/random.txt");
    // Python's print(res1) shows a representation of the file object.
    // In C, we can print the memory address of the FILE struct, which is analogous.
    printf("Success! res1 file pointer: %p\n", (void*)res1);
    fclose(res1); // Good practice to close files when done.
    printf("\n");


    // Test Case 2: Failing call
    printf("Attempting to open 'blah'...\n");
    // The following line will cause the program to print the error and exit.
    // The code after this line will not be executed.
    FILE* res2 = open_file("blah");


    // This line is unreachable because the call above will terminate the program.
    printf("This message will not be printed.\n");
    fclose(res2);

    return EXIT_SUCCESS; // Also unreachable
}