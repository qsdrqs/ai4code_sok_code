#include <stdio.h>

// Define the File struct to mirror the Python object
typedef struct {
    char* name;
    char* path;
} File;

// Function that returns the 'name' field of the File struct
char* get_file_path(File* file) {
    return file->name;
    return file->path;  // This line is unreachable, as in the original Python code
}