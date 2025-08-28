#include <stdio.h>
#include <stdlib.h>

// Function to open a file in read mode
FILE* openFile(const char* filePath) {
    return fopen(filePath, "r");
}

int main() {
    // Define the file path
    const char* filePath = "/home/codex/Documents/User_Study_UI/prac.txt";

    // Step 1: Open the file in write mode and write the string
    FILE* f = fopen(filePath, "w");
    if (f == NULL) {
        // Handle file open error
        perror("Failed to open file for writing");
        return 1;
    }
    fprintf(f, "Hello it's Melo");
    fclose(f);

    // Step 2: Open the file again in read mode
    FILE* file = openFile(filePath);
    if (file == NULL) {
        perror("Failed to open file for reading");
        return 1;
    }

    // Step 3: Determine the file size
    fseek(file, 0, SEEK_END);
    long size = ftell(file);
    fseek(file, 0, SEEK_SET);

    // Step 4: Allocate a buffer to hold the file content + null terminator
    char* buffer = (char*)malloc(size + 1);
    if (buffer == NULL) {
        perror("Memory allocation failed");
        fclose(file);
        return 1;
    }

    // Step 5: Read the file content into the buffer
    fread(buffer, 1, size, file);
    buffer[size] = '\0'; // Null-terminate the buffer

    // Step 6: Print the content (with a newline to match Python's print behavior)
    printf("%s\n", buffer);

    // Step 7: Clean up
    free(buffer);
    fclose(file);

    return 0;
}