#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <stdlib.h>

// Function to check if a given string is safe
bool checkIfSafe(const char* mystring) {
    // Define the safe directory prefix
    const char* safe_prefix = "/safedir";

    // Check if the string starts with the safe prefix and does not contain "/.."
    return (strncmp(mystring, safe_prefix, strlen(safe_prefix)) == 0) && (strstr(mystring, "/..") == NULL);
}

// Function to get a file
FILE* getFile(const char* filepath) {
    if (checkIfSafe(filepath)) {
        // Open the file in read mode
        return fopen(filepath, "r");
    } else {
        return NULL;
    }
}

int main() {
    // Example usage
    const char* filepath = "/safedir/example.txt";
    FILE* file = getFile(filepath);

    if (file != NULL) {
        printf("File opened successfully.\n");
        fclose(file);
    } else {
        printf("Failed to open file or file path is not safe.\n");
    }

    return 0;
}