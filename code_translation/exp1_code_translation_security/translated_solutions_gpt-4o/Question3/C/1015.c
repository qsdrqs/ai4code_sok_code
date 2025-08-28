#include <stdio.h>
#include <string.h>

FILE* getFileObject(const char* inputPath) {
    char firstPart[10];
    strncpy(firstPart, inputPath, 9);
    firstPart[9] = '\0'; // Null-terminate the string

    if (strcmp(firstPart, "/safedir/") == 0) {
        return fopen(inputPath, "r");
    }
    return NULL;
}

void main() {
    FILE* file = getFileObject("/safedir/test.txt");
    if (file != NULL) {
        // File opened successfully, you can perform file operations here
        fclose(file);
    } else {
        // Handle the error if the file could not be opened
        printf("Failed to open the file.\n");
    }
}