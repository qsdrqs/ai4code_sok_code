#include <stdio.h>  // For file operations (fopen, printf, FILE) and stderr
#include <stdlib.h> // For exit() and EXIT_FAILURE
#include <string.h> // For string manipulation (strncmp, strlen)

/**
 * @brief Opens a file, but only if it's within the "/safedir/" directory.
 * 
 * @param file_path The full path to the file to open.
 * @return A FILE pointer to the opened file on success, or NULL on failure.
 */
FILE* open_file(const char* file_path) {
    // In C, we use strncmp to check if a string starts with a specific prefix.
    // We check for "/safedir/" to ensure we are in the correct directory.
    const char* prefix = "/safedir/";
    if (strncmp(file_path, prefix, strlen(prefix)) != 0) {
        // C doesn't have exceptions like Python. The standard way to handle a
        // fatal error is to print to the standard error stream (stderr)
        // and exit the program with a non-zero status code.
        fprintf(stderr, "Error: File is outside of /safedir\n");
        exit(EXIT_FAILURE);
    }

    // The 'try...except' block in Python is replaced by checking the return
    // value of the function that might fail. fopen() returns NULL on error.
    FILE* file_obj = fopen(file_path, "r");

    if (file_obj == NULL) {
        // This block is equivalent to Python's 'except IOError'.
        // We print a message to standard output to match the Python example.
        printf("File not found\n");
        return NULL; // 'None' in Python is 'NULL' for pointers in C.
    }

    // This is the successful 'try' path.
    return file_obj;
}

int main() {
    // Call the function with a valid path
    printf("Attempting to open /safedir/Jim...\n");
    FILE* path = open_file("/safedir/Jim");

    // In C, we must check if the returned pointer is NULL to see if the
    // function succeeded.
    if (path != NULL) {
        // Python's print(file_obj) shows a representation of the object.
        // In C, we can show that we received a valid pointer.
        printf("File opened successfully. File pointer: %p\n", (void*)path);
        
        // CRITICAL: In C, you must manually close any file you open.
        // This is a major difference from Python's automatic garbage collection.
        fclose(path);
    } else {
        // The open_file function already printed "File not found",
        // but we can confirm here that we received NULL.
        printf("open_file returned NULL.\n");
    }

    printf("\n");

    // Example of a call that will trigger the fatal error
    printf("Attempting to open /etc/passwd...\n");
    // The following line will cause the program to exit, so it's commented out.
    // open_file("/etc/passwd"); 

    return 0;
}