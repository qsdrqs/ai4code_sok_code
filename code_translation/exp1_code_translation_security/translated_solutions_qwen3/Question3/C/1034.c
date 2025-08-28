#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function to check if the path starts with "/safedir"
int check_dir(char* path) {
    return strncmp(path, "/safedir", 8) == 0;
}

// Function to get a file object (FILE pointer)
FILE* get_file_object(char* path) {
    if (check_dir(path)) {
        FILE* file_object = fopen(path, "r");
        if (file_object == NULL) {
            printf("Error\n");
            exit(0);
        }
        return file_object;
    } else {
        exit(0);
    }
}

// Main function to simulate the Python print statement
int main() {
    FILE* f = get_file_object("/safedir/test1.txt");

    // Avoid unused variable warning
    if (f == NULL) {}

    return 0;
}