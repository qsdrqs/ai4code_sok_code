#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function to check if a path is safe and open the file
FILE* funcPath(const char* path) {
    // Check if "/safedir" is in the path
    if (strstr(path, "/safedir") != NULL) {
        // Open the file in read mode
        return fopen(path, "r");
    } else {
        // If path is not safe, print an error message and return NULL
        fprintf(stderr, "file not found or unsafe for access.\n");
        return NULL;
    }
}

int main() {
    const char* path = "/safedir/Jim";
    FILE* file = funcPath(path);

    if (file != NULL) {
        // If file is opened successfully, you can read from it
        // For demonstration, just close the file
        fclose(file);
    }

    return 0;
}