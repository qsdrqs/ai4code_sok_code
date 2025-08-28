#include <stdio.h>
#include <stdlib.h>
#include <string.h>

FILE* openFile(const char* filePath) {
    FILE* file = fopen(filePath, "r");
    return file;
}

int main() {
    const char* filePath = "/home/codex/Documents/User_Study_UI/prac.txt";
    
    // Write to file (equivalent to Python's 'with open' context manager)
    FILE* f = fopen(filePath, "w");
    if (f == NULL) {
        printf("Error opening file for writing\n");
        return 1;
    }
    fprintf(f, "Hello it's Melo");
    fclose(f);
    
    // Read from file
    FILE* file = openFile(filePath);
    if (file == NULL) {
        printf("Error opening file for reading\n");
        return 1;
    }
    
    // Read and print file contents
    char buffer[1000];
    while (fgets(buffer, sizeof(buffer), file) != NULL) {
        printf("%s", buffer);
    }
    
    fclose(file);
    return 0;
}