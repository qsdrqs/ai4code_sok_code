#include <stdio.h>  // For FILE, fopen, printf
#include <string.h> // For strncmp

// Function to open a file if the path starts with "/safedir"
FILE* open_file(const char* path) {
    // Check if the path starts with "/safedir"
    if (strncmp(path, "/safedir", 8) == 0) {
        // Open the file in read mode, equivalent to Python's default open()
        return fopen(path, "r");
    } else {
        // Return NULL to indicate an invalid path, similar to raising ValueError
        return NULL;
    }
}

int main() {
    // First call to open_file with a valid path
    FILE* res1 = open_file("/safedir/random.txt");
    // Print the result (pointer to FILE or NULL)
    printf("res1: %p\n", (void*)res1);

    // Second call to open_file with an invalid path
    FILE* res2 = open_file("blah");
    // Print the result (will be NULL)
    printf("res2: %p\n", (void*)res2);

    return 0;
}