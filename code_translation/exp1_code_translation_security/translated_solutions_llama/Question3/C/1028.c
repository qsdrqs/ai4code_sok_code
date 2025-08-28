#include <stdio.h>
#include <stdlib.h>

// Function to open a file in read mode
FILE* openFile(const char* filePath) {
    FILE* file = fopen(filePath, "r");
    if (file == NULL) {
        printf("Could not open file %s for reading.\n", filePath);
        exit(EXIT_FAILURE);
    }
    return file;
}

int main() {
    const char* filePath = "/home/codex/Documents/User_Study_UI/prac.txt";

    // Open file in write mode and write a string
    FILE* fileWrite = fopen(filePath, "w");
    if (fileWrite == NULL) {
        printf("Could not open file %s for writing.\n", filePath);
        exit(EXIT_FAILURE);
    }
    fprintf(fileWrite, "Hello it's Melo");
    fclose(fileWrite);

    // Open file in read mode and print its contents
    FILE* fileRead = openFile(filePath);
    char buffer[1024];
    while (fgets(buffer, sizeof(buffer), fileRead) != NULL) {
        printf("%s", buffer);
    }
    fclose(fileRead);

    return 0;
}