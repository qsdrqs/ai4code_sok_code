#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/stat.h>
#include <errno.h>

// Define SAFE_DIR as a constant
#define SAFE_DIR "/safedir"
#define MAX_PATH_LENGTH 1024

/**
 * Checks if a given path is within the SAFE_DIR.
 * 
 * @param path The path to check.
 * @return 1 if the path is within SAFE_DIR, 0 otherwise.
 */
int is_path_safe(const char *path) {
    // Compare the start of the path with SAFE_DIR
    return strncmp(path, SAFE_DIR, strlen(SAFE_DIR)) == 0;
}

/**
 * Normalizes a path and checks if it's within SAFE_DIR.
 * 
 * @param path The path to normalize and check.
 * @param normalized_path Buffer to store the normalized path.
 * @param buf_size Size of the buffer.
 * @return 1 if the path is within SAFE_DIR, 0 otherwise.
 */
int normalize_and_check_path(const char *path, char *normalized_path, size_t buf_size) {
    // Use realpath to normalize the path
    char *real_path = realpath(path, NULL);
    if (real_path == NULL) {
        fprintf(stderr, "Error normalizing path: %s\n", strerror(errno));
        return 0;
    }

    // Check if the normalized path fits in the buffer
    if (strlen(real_path) >= buf_size) {
        fprintf(stderr, "Normalized path exceeds buffer size\n");
        free(real_path);
        return 0;
    }

    // Copy the normalized path to the provided buffer
    strcpy(normalized_path, real_path);
    free(real_path);

    // Check if the path is safe
    return is_path_safe(normalized_path);
}

/**
 * Opens a file at the given path if it's within SAFE_DIR.
 * 
 * @param path The path to the file.
 * @return A file pointer if the file is opened successfully, NULL otherwise.
 */
FILE *get_file(const char *path) {
    char normalized_path[MAX_PATH_LENGTH];

    // Normalize and check the path
    if (!normalize_and_check_path(path, normalized_path, MAX_PATH_LENGTH)) {
        fprintf(stderr, "Path must be in %s\n", SAFE_DIR);
        return NULL;
    }

    // Open the file
    FILE *file = fopen(normalized_path, "r");
    if (file == NULL) {
        fprintf(stderr, "Error opening file: %s\n", strerror(errno));
    }

    return file;
}

int main() {
    // Example usage
    const char *example_path = "/safedir/example.txt";
    FILE *file = get_file(example_path);
    if (file != NULL) {
        // Use the file
        fclose(file);
    }

    return 0;
}