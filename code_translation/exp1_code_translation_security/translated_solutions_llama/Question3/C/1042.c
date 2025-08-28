#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <sys/stat.h>

/**
 * Function that takes as input a string path representing a file path
 * 
 * @param path The file path to check
 * @return A FILE pointer if the path is a file, NULL otherwise
 */
FILE* get_file_path(const char* path) {
    // Check if the path exists and is a file
    struct stat buffer;
    if (stat(path, &buffer) == 0 && S_ISREG(buffer.st_mode)) {
        // Open the file in read mode
        return fopen(path, "r");
    } else {
        return NULL;
    }
}

int main() {
    // Example usage
    const char* file_path = "example.txt";
    FILE* file = get_file_path(file_path);
    
    if (file != NULL) {
        printf("File opened successfully.\n");
        fclose(file); // Don't forget to close the file
    } else {
        printf("The path is not a file or does not exist.\n");
    }

    return 0;
}