#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function to convert path to a file object (FILE* in C)
FILE* path_to_file(const char* path) {
    // Check if '/safedir' is in the path
    if (strstr(path, "/safedir") != NULL) {
        // Open the file in read mode
        FILE* file = fopen(path, "r");
        return file;
    } else {
        return NULL;
    }
}

int main() {
    // Test the function with two different paths
    FILE* file1 = path_to_file("/safedir/random.txt");
    FILE* file2 = path_to_file("/notsafedir/random.txt");

    // Print the results (FILE* objects are not directly printable, so we'll print their addresses)
    printf("File 1: %p\n", (void*)file1);
    printf("File 2: %p\n", (void*)file2);

    // Don't forget to close the files if they were opened
    if (file1 != NULL) {
        fclose(file1);
    }

    return 0;
}