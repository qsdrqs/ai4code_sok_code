#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// Function to create a file object for a given path
FILE* create_file_object(const char* path) {
    // Check if path starts with "/safedir"
    if (strncmp(path, "/safedir", strlen("/safedir")) == 0) {
        // Open the file in read mode
        return fopen(path, "r");
    } else {
        // Return NULL if path does not start with "/safedir"
        return NULL;
    }
}

int main() {
    // Test the create_file_object function
    const char* path = "/etc/passwd";
    FILE* file_object = create_file_object(path);

    if (file_object != NULL) {
        printf("File object created successfully for path: %s\n", path);
        // Don't forget to close the file when you're done with it
        fclose(file_object);
    } else {
        printf("Failed to create file object for path: %s\n", path);
    }

    return 0;
}