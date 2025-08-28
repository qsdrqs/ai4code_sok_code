#include <string.h>  // For strncmp

// Forward declaration of the File struct (assumed to be defined elsewhere)
typedef struct File File;

// Function to create a new File object (assumed to be defined elsewhere)
File* File_new(const char* path);

// Function to raise an exception (assumed to be defined elsewhere)
void raise_exception(const char* message);

// The translated function
File* get_file(const char* path) {
    // Check if the path starts with "/safedir"
    if (strncmp(path, "/safedir", 8) == 0) {
        return File_new(path);
    } else {
        // Raise an exception if access is denied
        raise_exception("Access denied");
        // This return is just to satisfy the compiler; it will never be reached
        return NULL;
    }
}