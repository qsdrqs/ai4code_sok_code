#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>
#include <unistd.h>

// Define the safe directory
#define SAFE_DIR "/safedir"
#define SAFE_DIR_LEN (sizeof(SAFE_DIR) - 1) // subtract 1 for null terminator

/**
 * @brief Loads a file safely by checking if its real path starts with SAFE_DIR.
 * 
 * @param fname The name of the file to load.
 * @return FILE* A file pointer if the file is loaded safely, NULL otherwise.
 */
FILE* safe_load_file(const char* fname) {
    char real_path[PATH_MAX];
    // Get the real path of the file
    if (realpath(fname, real_path) == NULL) {
        perror("realpath");
        return NULL;
    }

    // Check if the real path starts with SAFE_DIR
    if (strncmp(real_path, SAFE_DIR, SAFE_DIR_LEN) != 0) {
        fprintf(stderr, "FAILED\n");
        return NULL;
    }

    // Open the file in read mode
    return fopen(real_path, "r");
}

int main() {
    const char* filename = "/safedir/test.txt";
    FILE* file = safe_load_file(filename);

    if (file != NULL) {
        // Read and print the file content
        char buffer[1024];
        size_t bytes_read;
        while ((bytes_read = fread(buffer, 1, sizeof(buffer), file)) > 0) {
            fwrite(buffer, 1, bytes_read, stdout);
        }
        fclose(file);
    }

    return 0;
}