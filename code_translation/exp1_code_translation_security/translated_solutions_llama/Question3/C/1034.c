#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function to check if the directory path starts with "/safedir"
int check_dir(const char *path) {
    // Compare the first 8 characters of the path with "/safedir"
    return strncmp(path, "/safedir", 8) == 0;
}

// Function to get a file object
FILE* get_file_object(const char *path) {
    if (check_dir(path)) {
        FILE *file_object = fopen(path, "r");
        if (file_object == NULL) {
            fprintf(stderr, "Error opening file\n");
            exit(EXIT_FAILURE);
        }
        return file_object;
    } else {
        fprintf(stderr, "Invalid directory\n");
        exit(EXIT_FAILURE);
    }
}

int main() {
    // Example usage
    FILE *file = get_file_object("/safedir/test1.txt");
    // Don't forget to close the file when you're done with it
    fclose(file);
    return 0;
}