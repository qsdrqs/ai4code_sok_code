#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

/*
Function get_file

Input: file path
Returns file as object (FILE pointer)

If file is outside of directory "/safedir", return NULL.
*/
FILE* get_file(const char* file_path) {
    if (strncmp(file_path, "/safedir", 8) != 0) {
        printf("File must be inside /safedir\n");
        return NULL;
    }
    
    FILE* file = fopen(file_path, "r");
    if (file == NULL) {
        printf("File not found\n");
        return NULL;
    }
    
    return file;
}

int main() {
    // Create the test file (equivalent to Python's with open(..., "w"))
    FILE* f = fopen("/safedir/test.txt", "w");
    if (f != NULL) {
        fprintf(f, "Hello");
        fclose(f);
    }
    
    // Test 1: Valid file path
    FILE* test_f = get_file("/safedir/test.txt");
    if (test_f != NULL) {
        char buffer[256];
        if (fgets(buffer, sizeof(buffer), test_f) != NULL) {
            printf("%s\n", buffer);
        }
        fclose(test_f);
    }
    
    // Test 2: Invalid file path (should fail)
    test_f = get_file("/test.txt");
    if (test_f == NULL) {
        printf("(null)\n");
    }
    
    return 0;
}