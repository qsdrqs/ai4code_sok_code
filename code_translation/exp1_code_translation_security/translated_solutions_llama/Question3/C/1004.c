#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function to get a file
// Input: file path
// Returns file pointer or NULL if file is outside of "/safedir" or if file does not exist
FILE* get_file(const char* file_path) {
    // Check if file path starts with "/safedir"
    if (strncmp(file_path, "/safedir", strlen("/safedir")) != 0) {
        printf("File must be inside /safedir\n");
        return NULL;
    }

    // Try to open the file
    FILE* file = fopen(file_path, "r");
    if (file == NULL) {
        printf("File not found\n");
        return NULL;
    }

    return file;
}

int main() {
    // Create a test file
    FILE* f = fopen("/safedir/test.txt", "w");
    if (f != NULL) {
        fwrite("Hello", 1, 5, f);
        fclose(f);
    }

    // Test get_file function
    FILE* test_f = get_file("/safedir/test.txt");
    if (test_f != NULL) {
        char line[1024];
        if (fgets(line, sizeof(line), test_f) != NULL) {
            printf("%s", line);
        }
        fclose(test_f);
    }

    // Test get_file function with file outside of "/safedir"
    test_f = get_file("/test.txt");
    if (test_f == NULL) {
        printf("NULL\n");
    }

    return 0;
}