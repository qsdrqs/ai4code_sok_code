#include <stdio.h>
#include <string.h>
#include <stdbool.h>

// Function to check if the file path is safe
bool checkIfSafe(const char *mystring) {
    // Check if the string starts with "/safedir"
    if (strncmp(mystring, "/safedir", 8) == 0) {
        // Check if the string does not contain "/.."
        if (strstr(mystring, "/..") == NULL) {
            return true;
        }
    }
    return false;
}

// Function to get the file if the path is safe
FILE* getFile(const char *filepath) {
    if (checkIfSafe(filepath)) {
        return fopen(filepath, "r");
    } else {
        return NULL;
    }
}

int main() {
    const char *filepath = "/safedir/example.txt";
    FILE *file = getFile(filepath);

    if (file != NULL) {
        printf("File opened successfully.\n");
        // Remember to close the file after use
        fclose(file);
    } else {
        printf("File path is not safe or file could not be opened.\n");
    }

    return 0;
}