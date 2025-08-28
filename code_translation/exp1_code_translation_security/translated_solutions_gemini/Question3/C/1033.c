#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <sys/stat.h> // For mkdir
#include <sys/types.h> // For mkdir

/**
 * @brief Checks if a path is in a safe directory and opens it.
 * 
 * This function mimics the Python version's logic. It checks if the 'path'
 * string starts with "/safedir".
 * 
 * @param path The full path to the file.
 * @return A FILE pointer on success, or NULL on failure (either access
 *         denied or the file could not be opened). This use of NULL is the
 *         C equivalent of raising an exception.
 */
FILE* get_file(const char* path) {
    const char* prefix = "/safedir";
    // strncmp is the C equivalent of Python's startswith()
    if (strncmp(path, prefix, strlen(prefix)) == 0) {
        // Path is valid, attempt to open the file for reading ("r")
        return fopen(path, "r");
    } else {
        // Path is not in the safe directory, return NULL to signal an error.
        printf("Access denied\n"); // Explicitly print the error message from the function
        return NULL;
    }
}

/**
 * @brief Reads an entire file stream into a string and prints it.
 * 
 * @param file_handle A valid, open FILE pointer.
 */
void read_and_print_file(FILE* file_handle) {
    int c;
    // Read character by character until End-Of-File (EOF) is reached
    while ((c = fgetc(file_handle)) != EOF) {
        putchar(c); // Print the character to stdout
    }
    printf("\n"); // Print a final newline for clean output
}

// Helper function to set up the test environment
void setup_test_files() {
    // Create the /safedir directory. 0755 gives r/w/x to owner, r/x to others.
    mkdir("safedir", 0755); 
    
    // Create and write to the test file
    FILE* f = fopen("safedir/random.txt", "w");
    if (f != NULL) {
        fprintf(f, "This is a safe file.");
        fclose(f);
    }
}

int main() {
    // Set up the directory and file needed for the first test case
    setup_test_files();

    // --- First try...except block ---
    printf("Attempting to read /safedir/random.txt...\n");
    FILE* file1 = get_file("safedir/random.txt");
    
    // The 'if' statement is the C equivalent of a 'try' block
    if (file1 != NULL) {
        read_and_print_file(file1);
        fclose(file1); // Always close a file when you're done with it
    } 
    // The 'else' statement is the C equivalent of an 'except' block
    else {
        printf("Error with safedir/random.txt\n");
    }

    printf("\n"); // Separator for clarity

    // --- Second try...except block ---
    printf("Attempting to read /etc/passwd...\n");
    FILE* file2 = get_file("/etc/passwd");

    if (file2 != NULL) {
        // This block will not be executed
        read_and_print_file(file2);
        fclose(file2);
    }
    // The 'else' block will be executed because get_file returns NULL
    else {
        // The error message "Access denied" is now printed inside get_file
        // to more closely match the Python exception's behavior.
        // We print a context-specific message here.
        printf("Access was denied as expected.\n");
    }

    return 0;
}