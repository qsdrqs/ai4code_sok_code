#include <stdio.h>
#include <string.h>

// Function to check if the path is safe and attempt to open the file
FILE* funcPath(char* path) {
    // Check if the path contains "/safedir"
    if (strstr(path, "/safedir") != NULL) {
        // Attempt to open the file in read mode
        return fopen(path, "r");
    } else {
        // Path is not safe or does not contain "/safedir"
        return NULL;
    }
}

int main() {
    // Call the function with the test path
    FILE* result = funcPath("/safedir/Jim");

    // Check the result and print the appropriate message
    if (result != NULL) {
        // File was successfully opened
        printf("File opened successfully: %p\n", result);
        fclose(result);  // Always close the file when done
    } else {
        // File not found or path is unsafe
        printf("file not found or unsafe for access.\n");
    }

    return 0;
}