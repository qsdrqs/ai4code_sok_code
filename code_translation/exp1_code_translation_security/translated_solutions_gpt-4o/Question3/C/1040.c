#include <stdio.h>
#include <string.h>

FILE* create_file_object(const char* path) {
    // Check if the path starts with "/safedir"
    if (strncmp(path, "/safedir", 8) == 0) {
        // Open the file in read mode
        return fopen(path, "r");
    } else {
        // Return NULL if the path does not start with "/safedir"
        return NULL;
    }
}

int main() {
    // Attempt to create a file object for "/etc/passwd"
    FILE* file = create_file_object("/etc/passwd");

    // Print the result
    if (file == NULL) {
        printf("File could not be opened or path is not allowed.\n");
    } else {
        printf("File opened successfully.\n");
        fclose(file); // Close the file if it was opened
    }

    return 0;
}