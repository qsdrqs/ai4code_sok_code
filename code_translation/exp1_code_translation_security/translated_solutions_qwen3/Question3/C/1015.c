#include <stdio.h>
#include <string.h>

// Function to check if the input path starts with "/safedir/" and open the file
FILE* getFileObject(char* inputPath) {
    // Compare the first 9 characters of inputPath with "/safedir/"
    if (strncmp(inputPath, "/safedir/", 9) == 0) {
        // Open the file in read mode
        return fopen(inputPath, "r");
    }
    // Return NULL if the path does not start with "/safedir/"
    return NULL;
}

// Main function, which is the entry point in C
int main() {
    // Call the function with the test file path
    getFileObject("/safedir/test.txt");
    return 0; // Indicate successful execution
}