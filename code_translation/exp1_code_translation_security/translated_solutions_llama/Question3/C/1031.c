#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <dirent.h>
#include <limits.h>

// Function to find and open a file if its directory path is safe
FILE* find_path(const char* path) {
    // Define the safe directory path
    const char* safe_path = "/safedir";

    // Get the directory name from the given path
    char* dir_name = strdup(path);
    char* last_slash = strrchr(dir_name, '/');
    if (last_slash != NULL) {
        *last_slash = '\0'; // Replace the last slash with null to get the directory name
    }

    // Check if the directory name matches the safe path
    if (strcmp(dir_name, safe_path) == 0) {
        // Open the file in read mode
        FILE* file = fopen(path, "r");
        if (file != NULL) {
            printf("safe!\n");
            free(dir_name); // Free allocated memory
            return file;
        } else {
            printf("Failed to open file: %s\n", path);
        }
    } else {
        printf("not safe!\n");
    }

    free(dir_name); // Free allocated memory
    return NULL;
}

int main() {
    // Test the function with different paths
    find_path("/safedir/random.txt");
    find_path("/home/codex/Documents/User_Study_UI");

    return 0;
}