#include <stdio.h>  // For FILE, fopen, printf, NULL
#include <string.h> // For strncmp, strlen

/**
 * @brief Creates a file object for a given path, but only if it's in a safe directory.
 *
 * This function checks if the provided path string starts with "/safedir".
 * If it does, it attempts to open the file in read mode ('r') and returns
 * a pointer to a FILE object.
 * If the path does not start with "/safedir", or if the file cannot be opened,
 * it returns NULL.
 *
 * @param path The file path to check and open.
 * @return A pointer to a FILE object on success, or NULL on failure or if
 *         the path is not in the allowed directory.
 */
FILE* create_file_object(const char* path) {
    const char* safe_prefix = "/safedir";
    
    // In C, we use strncmp to check if a string starts with a prefix.
    // We compare the beginning of 'path' with 'safe_prefix' for the length of 'safe_prefix'.
    if (strncmp(path, safe_prefix, strlen(safe_prefix)) == 0) {
      // The path is safe, attempt to open the file.
      // fopen returns a FILE* on success and NULL on failure.
      return fopen(path, "r");
    } else {
      // The path is not in the allowed directory, return NULL (the C equivalent of None).
      return NULL;
    }
}

int main() {
    // Call the function with the test path, same as in the Python example.
    FILE* file_obj = create_file_object("/etc/passwd");

    // The Python `print()` function displays "None" when the object is None.
    // In C, we explicitly check for the NULL pointer to achieve the same output.
    if (file_obj == NULL) {
        printf("None\n");
    } else {
        // This block would execute if the path was valid (e.g., "/safedir/file.txt").
        // It's good practice to show that a valid object was returned and to close it.
        printf("File object created successfully.\n");
        fclose(file_obj); // Always close files when you are done with them.
    }

    return 0; // Indicates successful execution
}