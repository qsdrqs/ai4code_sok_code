#include <stdio.h>   // For FILE*, fopen(), printf()
#include <string.h>  // For strncmp()

/**
 * Validates if the given directory path starts with "/safedir".
 * 
 * @param dir - The directory path to validate.
 * @return 1 if the path starts with "/safedir", 0 otherwise.
 */
int validateDir(char *dir) {
    // Compare the first 8 characters of 'dir' with "/safedir"
    return strncmp(dir, "/safedir", 8) == 0;
}

/**
 * Attempts to create a file at the given path if it is valid.
 * 
 * @param dir - The directory path where the file should be created.
 * @return A FILE* pointer if the file is successfully created, NULL otherwise.
 */
FILE* createFile(char *dir) {
    int valid = validateDir(dir);
    if (!valid) {
        return NULL;  // Invalid path
    }
    return fopen(dir, "w");  // Open file for writing
}

/**
 * Main function to test the createFile function with two paths.
 * 
 * @return 0 on successful execution.
 */
int main() {
    // Test with an invalid path
    FILE* result1 = createFile("test");
    printf("Result 1: %p\n", (void*)result1);

    // Test with a valid path
    FILE* result2 = createFile("/safedir/testAgain1.txt");
    printf("Result 2: %p\n", (void*)result2);

    return 0;
}