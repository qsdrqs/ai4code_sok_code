#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function to check if a path contains "/safedir"
int contains_safedir(const char *path) {
    return strstr(path, "/safedir") != NULL;
}

// Function to open a file and return the file pointer
FILE *thing(const char *path) {
    if (contains_safedir(path)) {
        // Open the file in read mode
        return fopen(path, "r");
    } else {
        // Print an error message
        fprintf(stderr, "invalid path\n");
        return NULL;
    }
}

int main() {
    // Test good path
    const char *good_path = "/home/codex/Documents/User_Study_UI/safedir/test1.txt";
    FILE *f = thing(good_path);
    printf("\nResult 1:\n");
    if (f != NULL) {
        printf("File opened successfully.\n");
        fclose(f); // Close the file
    } else {
        printf("Failed to open file.\n");
    }

    // Test bad path
    const char *bad_path = "/home/codex/Documents/User_Study_UI/random.txt";
    f = thing(bad_path);
    printf("\nResult 2:\n");
    if (f == NULL) {
        printf("File not opened (as expected).\n");
    } else {
        printf("Unexpectedly opened file.\n");
        fclose(f); // Close the file
    }

    return 0;
}