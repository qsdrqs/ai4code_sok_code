#include <stdio.h>
#include <stdlib.h>

// Function to open a file in read mode
FILE* openFile(const char* filePath) {
    FILE* file = fopen(filePath, "r");
    if (file == NULL) {
        perror("Error opening file");
        exit(EXIT_FAILURE);
    }
    return file;
}

int main() {
    const char* filePath = "/home/codex/Documents/User_Study_UI/prac.txt";

    // Open the file in write mode and write to it
    FILE* f = fopen(filePath, "w");
    if (f == NULL) {
        perror("Error opening file for writing");
        return EXIT_FAILURE;
    }
    fprintf(f, "Hello it's Melo");
    fclose(f);

    // Open the file in read mode and read its contents
    FILE* file = openFile(filePath);
    char ch;
    while ((ch = fgetc(file)) != EOF) {
        putchar(ch);
    }
    fclose(file);

    return 0;
}