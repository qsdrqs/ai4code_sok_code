#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Dependency: A struct to represent the File object.
// In Python, this would be a class. In C, it's a struct.
typedef struct {
    char* path;
} File;

// Dependency: A "constructor" function to create and initialize a File object.
// This function allocates memory for the File struct and its path member.
File* create_file(const char* path) {
    // Allocate memory for the File struct itself
    File* new_file = (File*)malloc(sizeof(File));
    if (new_file == NULL) {
        perror("Failed to allocate memory for File struct");
        return NULL;
    }

    // Allocate memory for the path string and copy it
    new_file->path = (char*)malloc(strlen(path) + 1); // +1 for the null terminator
    if (new_file->path == NULL) {
        perror("Failed to allocate memory for path string");
        free(new_file); // Clean up the partially allocated struct
        return NULL;
    }
    strcpy(new_file->path, path);

    return new_file;
}

// Dependency: A "destructor" function to free the memory used by a File object.
void destroy_file(File* file) {
    if (file != NULL) {
        free(file->path); // Free the path string first
        free(file);       // Then free the struct itself
    }
}

/**
 * @brief Attempts to get a file object for a given path.
 *
 * This function translates the Python logic. It checks if the path starts with
 * "/safedir". If it does, it returns a pointer to a new File object.
 * If not, it simulates the "Exception" by printing an error message to stderr
 * and returning NULL.
 *
 * @param path The file path to check.
 * @return A pointer to a new File object on success, or NULL on failure.
 *         The caller is responsible for freeing the returned File object
 *         using destroy_file().
 */
File* get_file(const char* path) {
    const char* prefix = "/safedir";
    // C equivalent of path.startswith('/safedir')
    if (strncmp(path, prefix, strlen(prefix)) == 0) {
        // C equivalent of 'return File(path)'
        return create_file(path);
    } else {
        // C equivalent of 'raise Exception('Access denied')'
        fprintf(stderr, "Access denied\n");
        return NULL;
    }
}

// --- Example Usage ---
int main() {
    printf("Attempting to access a valid path...\n");
    File* f1 = get_file("/safedir/my_document.txt");

    if (f1 != NULL) {
        printf("Success! Got file with path: %s\n", f1->path);
        destroy_file(f1); // Clean up memory
    }

    printf("\n--------------------------------------\n\n");

    printf("Attempting to access a restricted path...\n");
    File* f2 = get_file("/etc/passwd");

    if (f2 == NULL) {
        printf("Failed to get file as expected.\n");
    }
    // No need to call destroy_file since f2 is NULL

    return 0;
}