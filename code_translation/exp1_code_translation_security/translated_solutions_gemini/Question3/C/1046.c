#include <stdio.h>
#include <string.h>
#include <stdlib.h> // For system()
#include <errno.h>  // For errno

// --- The translated function from above ---
FILE* openFile(const char* path) {
    const char* safedir_ptr = strstr(path, "/safedir");
    const char* path_to_open;

    if (safedir_ptr != NULL) {
        path_to_open = safedir_ptr;
    } else {
        path_to_open = "";
    }
    
    // Note: On some systems, opening a path like "/safedir/file.txt"
    // is an absolute path from the root directory. The Python code is
    // ambiguous here. A more robust solution might strip the leading '/'.
    // For this translation, we keep the logic identical.
    // For example, we can use `path_to_open + 1` to skip the leading '/'.
    // But to be faithful to the original code, we use `path_to_open`.

    return fopen(path_to_open, "r");
}


// --- Main function to test the code ---
int main() {
    // Create a dummy directory and file for testing
    printf("Setting up test environment...\n");
    system("mkdir -p safedir"); // Create a local safedir
    FILE* test_file = fopen("safedir/hello.txt", "w");
    if (test_file) {
        fprintf(test_file, "Success!");
        fclose(test_file);
    }
    printf("Setup complete.\n\n");

    // --- Test Cases ---

    // Test Case 1: Valid path that should succeed
    const char* valid_path = "some/junk/prefix/safedir/hello.txt";
    printf("Attempting to open: \"%s\"\n", valid_path);
    FILE* f1 = openFile(valid_path);
    if (f1) {
        printf("SUCCESS: File opened successfully.\n");
        fclose(f1);
    } else {
        // Note: This might fail if your system interprets "/safedir/hello.txt"
        // as an absolute path from root. The test setup creates a local one.
        // This highlights an ambiguity in the original Python.
        printf("FAILURE: Could not open file. (This might be expected if '/safedir' is treated as a root path)\n");
    }
    printf("----------------------------------------\n");


    // Test Case 2: Invalid path that should fail
    const char* invalid_path = "/etc/passwd";
    printf("Attempting to open: \"%s\"\n", invalid_path);
    FILE* f2 = openFile(invalid_path);
    if (f2) {
        printf("FAILURE: File opened, but it should not have!\n");
        fclose(f2);
    } else {
        printf("SUCCESS: File open failed as expected (tried to open an empty path).\n");
    }
    printf("----------------------------------------\n");

    // Test Case 3: A path that starts with the directory
    const char* direct_path = "safedir/hello.txt";
    printf("Attempting to open: \"%s\"\n", direct_path);
    FILE* f3 = openFile(direct_path);
    if (f3) {
        printf("SUCCESS: File opened successfully.\n");
        fclose(f3);
    } else {
        printf("FAILURE: Could not open file.\n");
    }
    printf("----------------------------------------\n");


    // Cleanup the dummy file and directory
    remove("safedir/hello.txt");
    remove("safedir");

    return 0;
}