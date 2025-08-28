#include <stdio.h>   // For file operations (fopen, fclose, FILE) and printing (printf)
#include <stdlib.h>  // For memory allocation (malloc, free)
#include <string.h>  // For string manipulation (strcmp, strrchr, strncpy)

/**
 * @brief Helper function to get the directory part of a path.
 *
 * This function replicates the basic behavior of Python's os.path.dirname().
 * It returns a new, dynamically allocated string containing the directory name.
 * The caller is responsible for freeing this memory with free().
 *
 * @param path The input path string.
 * @return A newly allocated string with the directory name, or NULL on error.
 */
char* get_dirname(const char* path) {
    if (path == NULL) {
        return NULL;
    }

    // Find the last occurrence of the directory separator '/'
    const char* last_slash = strrchr(path, '/');

    // Case 1: No slash found (e.g., "random.txt"). Python's dirname is an empty string.
    if (last_slash == NULL) {
        char* result = (char*)malloc(1);
        if (result) result[0] = '\0';
        return result;
    }

    // Case 2: The slash is the first character (e.g., "/file.txt" or just "/")
    if (last_slash == path) {
        char* result = (char*)malloc(2);
        if (result) strcpy(result, "/");
        return result;
    }

    // Case 3: General case (e.g., "/safedir/random.txt")
    // Calculate the length of the directory part.
    size_t len = last_slash - path;
    char* result = (char*)malloc(len + 1);
    if (result) {
        strncpy(result, path, len);
        result[len] = '\0';
    }
    return result;
}

/**
 * @brief Checks if a path is in a safe directory and opens the file if it is.
 *
 * This function translates the Python find_path function. It checks if the
 * directory of the given path is "/safedir". If it is, it prints "safe!" and
 * returns a file handle. Otherwise, it prints "not safe!" and returns NULL.
 *
 * @param path The full path to the file to check.
 * @return A FILE pointer if the path is safe and the file is opened, otherwise NULL.
 */
FILE* find_path(const char* path) {
    const char* safe_path = "/safedir";
    char* dirname = get_dirname(path);

    // Always check if memory allocation was successful
    if (dirname == NULL) {
        fprintf(stderr, "Error: Memory allocation failed.\n");
        return NULL;
    }

    // Compare the extracted directory name with the safe path
    if (strcmp(dirname, safe_path) == 0) {
        printf("safe!\n");
        FILE* file = fopen(path, "r"); // "r" for read mode
        // In C, it's good practice to check if fopen succeeded
        if (file == NULL) {
            perror("Error opening file");
        }
        free(dirname); // Free the allocated memory for dirname
        return file;
    } else {
        printf("not safe!\n");
        free(dirname); // Free the allocated memory for dirname
        return NULL;
    }
}

int main() {
    // First call from the Python script
    printf("Checking path: /safedir/random.txt\n");
    FILE* file1 = find_path("/safedir/random.txt");

    // In C, we must explicitly close any file we open to free system resources.
    if (file1 != NULL) {
        printf("File handle returned. Closing the file.\n");
        fclose(file1);
    }
    printf("\n"); // Add a newline for better readability

    // Second call from the Python script
    printf("Checking path: /home/codex/Documents/User_Study_UI\n");
    FILE* file2 = find_path("/home/codex/Documents/User_Study_UI");

    // This block should not be executed as find_path will return NULL
    if (file2 != NULL) {
        printf("File handle returned. Closing the file.\n");
        fclose(file2);
    }

    return 0; // Indicate successful execution
}