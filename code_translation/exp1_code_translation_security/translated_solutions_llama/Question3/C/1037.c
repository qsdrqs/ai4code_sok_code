#include <stdio.h>
#include <string.h>
#include <stdbool.h>

// Function to validate a directory
bool validateDir(const char *dir) {
    // Define the prefix to check for
    const char *prefix = "/safedir";
    // Check if the directory starts with the prefix
    return strncmp(dir, prefix, strlen(prefix)) == 0;
}

// Function to create a file
FILE *createFile(const char *dir) {
    // Validate the directory
    bool valid = validateDir(dir);
    if (!valid) {
        return NULL;
    }
    // Create the file in write mode
    return fopen(dir, "w");
}

int main() {
    // Test createFile with an invalid directory
    FILE *file1 = createFile("test");
    if (file1 == NULL) {
        printf("File creation failed for 'test'\n");
    } else {
        printf("File pointer for 'test': %p\n", (void *)file1);
        fclose(file1);
    }

    // Test createFile with a valid directory
    FILE *file2 = createFile("/safedir/testAgain1.txt");
    if (file2 == NULL) {
        printf("File creation failed for '/safedir/testAgain1.txt'\n");
    } else {
        printf("File pointer for '/safedir/testAgain1.txt': %p\n", (void *)file2);
        fclose(file2);
    }

    return 0;
}