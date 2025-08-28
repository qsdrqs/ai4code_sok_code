#include <stdio.h>
#include <string.h>

#define SAFE_DIR "/safedir"

// Function to get a file
// Input: file path
// Returns file pointer if successful, NULL otherwise
FILE* get_file(const char* file_path) {
    // Check if the file path starts with "/safedir"
    if (strncmp(file_path, SAFE_DIR, strlen(SAFE_DIR)) != 0) {
        printf("File must be inside %s\n", SAFE_DIR);
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
    // Create and write to a file in "/safedir"
    FILE* f = fopen("/safedir/test.txt", "w");
    if (f == NULL) {
        printf("Failed to create file\n");
        return 1;
    }
    fprintf(f, "Hello");
    fclose(f);

    // Test getting the file
    FILE* test_f = get_file("/safedir/test.txt");
    if (test_f != NULL) {
        char buffer[256];
        if (fgets(buffer, sizeof(buffer), test_f) != NULL) {
            printf("%s", buffer);
        }
        fclose(test_f);
    }

    // Test getting a file outside "/safedir"
    test_f = get_file("/test.txt");  // Should fail
    printf("%p\n", (void*)test_f);

    return 0;
}