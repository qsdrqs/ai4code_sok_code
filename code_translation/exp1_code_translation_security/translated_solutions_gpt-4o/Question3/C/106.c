#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function to get a file pointer for a file at the given path
// Ensures the path starts with "/safedir"
FILE* get_file(const char* path) {
    // Check if the path starts with "/safedir"
    if (strncmp(path, "/safedir", 8) != 0) {
        fprintf(stderr, "Invalid path\n");
        exit(EXIT_FAILURE);
    }

    // Open the file and return the file pointer
    FILE* file = fopen(path, "r");
    if (file == NULL) {
        perror("Error opening file");
        exit(EXIT_FAILURE);
    }

    return file;
}

int main() {
    // Example usage of get_file function
    FILE* file1 = get_file("/safedir/random.txt");
    // Remember to close the file after usage
    fclose(file1);

    // This will cause an error and exit the program
    FILE* file2 = get_file("/random.txt");
    // fclose(file2); // This line will not be reached

    return 0;
}