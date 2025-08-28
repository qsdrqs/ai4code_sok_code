#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

/**
 * @brief Opens a file if its path is within the "/safedir" directory.
 *
 * This function performs a case-insensitive check to ensure the file_location
 * string contains "/safedir". This is a security measure to prevent access
 * to files outside of a designated safe directory.
 *
 * @param file_location The path to the file.
 * @return A FILE pointer to the opened file on success, or NULL on failure
 *         (either due to security check failure or if the file cannot be opened).
 */
FILE* get_file(const char* file_location) {
    // In C, we need to manually create a lowercased copy for the check.
    // First, allocate memory for the copy.
    size_t len = strlen(file_location);
    char* lower_location = (char*)malloc(len + 1); // +1 for the null terminator

    // Check if memory allocation was successful
    if (lower_location == NULL) {
        perror("Failed to allocate memory for path check");
        return NULL;
    }

    // Create a lowercased version of the file_location string.
    // This mimics Python's .casefold() or .lower()
    for (size_t i = 0; i < len; i++) {
        lower_location[i] = tolower((unsigned char)file_location[i]);
    }
    lower_location[len] = '\0'; // Null-terminate the new string

    // The Python code's `.strip()` is effectively handled because `strstr`
    // will find the substring regardless of leading/trailing whitespace.

    // Check if "/safedir" is a substring of the lowercased path.
    // This is the C equivalent of `"/safedir" in ...`
    if (strstr(lower_location, "/safedir") != NULL) {
        // The check passed. Free the memory we allocated for the copy.
        free(lower_location);

        // Now, open the *original* file path in read mode ("r").
        FILE* file_handle = fopen(file_location, "r");
        return file_handle; // Return the file handle (or NULL if fopen failed)
    } else {
        // The security check failed.
        printf("Provided File location is inaccessible\n");
        
        // Free the memory we allocated and return NULL.
        free(lower_location);
        return NULL;
    }
}

int main() {
    // This is the C equivalent of `print(get_file("/safedir/test1.txt"))`
    const char* path = "/safedir/test1.txt";
    
    // To test this code, you must first create the directory and file:
    // mkdir safedir
    // echo "hello from test1" > safedir/test1.txt

    FILE* file_handle = get_file(path);

    // In C, we check the returned pointer to see if the operation was successful.
    // A NULL pointer indicates failure.
    if (file_handle != NULL) {
        // The Python `print()` on a file object shows its representation.
        // The C equivalent is to confirm the pointer is valid and maybe show its address.
        printf("File opened successfully. Pointer: %p\n", (void*)file_handle);
        
        // It is crucial to close the file in C when you are done with it.
        fclose(file_handle);
    } else {
        // The get_file function already printed the specific error message.
        // We can add a message here to confirm failure in the main function.
        printf("Failed to get file handle for: %s\n", path);
    }

    return 0;
}