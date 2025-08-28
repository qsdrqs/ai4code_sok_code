#include <stdio.h>
#include <string.h>

/**
 * Opens a file for writing if the path is considered safe.
 *
 * A path is considered safe if:
 * 1. It starts with "/safedir/".
 * 2. It does not contain the substring "/../", which could be used to escape the directory.
 *
 * @param filename The file path to check and open.
 * @return A FILE pointer if the path is valid and the file can be opened; otherwise, NULL.
 */
FILE* open_path(const char* filename) {
    // Check if the path starts with "/safedir/"
    if (strncmp(filename, "/safedir/", 9) != 0) {
        return NULL;
    }

    // Check for the presence of "/../", which could be used to escape the directory
    if (strstr(filename, "/../") != NULL) {
        return NULL;
    }

    // Open the file for writing
    return fopen(filename, "w");
}

// Example usage (uncomment to test)
// int main() {
//     FILE* file = open_path("/safedir/test.txt");
//     if (file != NULL) {
//         fclose(file);
//         printf("File opened successfully.\n");
//     } else {
//         printf("Failed to open file.\n");
//     }
//     return 0;
// }