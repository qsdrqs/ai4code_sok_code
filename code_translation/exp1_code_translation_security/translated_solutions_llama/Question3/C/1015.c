#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// Function to get a file object
FILE* getFileObject(const char* inputPath) {
    // Define the safe directory prefix
    const char* safeDirPrefix = "/safedir/";
    const int prefixLength = strlen(safeDirPrefix);

    // Check if the input path starts with the safe directory prefix
    if (strncmp(inputPath, safeDirPrefix, prefixLength) == 0) {
        // Open the file in read mode
        return fopen(inputPath, "r");
    }

    // If the path does not start with the safe directory prefix, return NULL
    return NULL;
}

int main() {
    const char* filePath = "/safedir/test.txt";
    FILE* fileObject = getFileObject(filePath);

    if (fileObject != NULL) {
        printf("File opened successfully.\n");
        // Don't forget to close the file when you're done
        fclose(fileObject);
    } else {
        printf("Failed to open file.\n");
    }

    return 0;
}