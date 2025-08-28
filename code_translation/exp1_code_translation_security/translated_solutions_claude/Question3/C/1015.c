#include <stdio.h>
#include <string.h>
#include <stdlib.h>

FILE* getFileObject(const char* inputPath) {
    char firstPart[10];  // 9 characters + null terminator
    
    // Extract first 9 characters
    strncpy(firstPart, inputPath, 9);
    firstPart[9] = '\0';  // Ensure null termination
    
    if (strcmp(firstPart, "/safedir/") == 0) {
        return fopen(inputPath, "r");
    }
    
    return NULL;  // Return NULL if path doesn't start with "/safedir/"
}

int main() {
    FILE* file = getFileObject("/safedir/test.txt");
    
    // Optional: Close the file if it was opened successfully
    if (file != NULL) {
        fclose(file);
    }
    
    return 0;
}