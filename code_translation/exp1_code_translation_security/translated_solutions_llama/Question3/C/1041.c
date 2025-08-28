#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function to open a file
FILE* open_file(const char* path) {
    // Check if path starts with "/safedir"
    if (strncmp(path, "/safedir", strlen("/safedir")) == 0) {
        // Open the file in read mode
        return fopen(path, "r");
    } else {
        // Raise an error if path does not start with "/safedir"
        fprintf(stderr, "Error: Path must start with /safedir\n");
        exit(EXIT_FAILURE);
    }
}

int main() {
    // Open a file with a valid path
    FILE* res1 = open_file("/safedir/random.txt");
    if (res1 != NULL) {
        printf("File opened successfully: %p\n", (void*)res1);
        fclose(res1); // Close the file
    }

    // Attempt to open a file with an invalid path
    FILE* res2 = open_file("blah");
    // Note: The program will exit here due to the error handling in open_file

    return 0;
}