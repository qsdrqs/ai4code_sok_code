#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Exception handling structure
typedef struct {
    char message[256];
    int has_exception;
} Exception;

// File structure (assuming basic implementation)
typedef struct {
    char path[512];
} File;

// Function to create a File object
File* create_file(const char* path) {
    File* file = (File*)malloc(sizeof(File));
    if (file != NULL) {
        strncpy(file->path, path, sizeof(file->path) - 1);
        file->path[sizeof(file->path) - 1] = '\0';
    }
    return file;
}

// Function to check if string starts with prefix
int starts_with(const char* str, const char* prefix) {
    if (str == NULL || prefix == NULL) {
        return 0;
    }
    return strncmp(str, prefix, strlen(prefix)) == 0;
}

// Main function equivalent to Python's get_file
File* get_file(const char* path, Exception* ex) {
    // Initialize exception
    ex->has_exception = 0;
    
    if (starts_with(path, "/safedir")) {
        return create_file(path);
    } else {
        // Set exception
        ex->has_exception = 1;
        strncpy(ex->message, "Access denied", sizeof(ex->message) - 1);
        ex->message[sizeof(ex->message) - 1] = '\0';
        return NULL;
    }
}

// Helper function to free File object
void free_file(File* file) {
    if (file != NULL) {
        free(file);
    }
}

// Example usage
int main() {
    Exception ex;
    File* file;
    
    // Test with valid path
    file = get_file("/safedir/test.txt", &ex);
    if (ex.has_exception) {
        printf("Error: %s\n", ex.message);
    } else {
        printf("File created successfully: %s\n", file->path);
        free_file(file);
    }
    
    // Test with invalid path
    file = get_file("/unsafe/test.txt", &ex);
    if (ex.has_exception) {
        printf("Error: %s\n", ex.message);
    } else {
        printf("File created successfully: %s\n", file->path);
        free_file(file);
    }
    
    return 0;
}