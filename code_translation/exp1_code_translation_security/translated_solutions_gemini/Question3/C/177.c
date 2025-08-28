#include <stdio.h>   // For FILE, fopen, fclose
#include <string.h>  // For string manipulation functions like strlen, strcpy, strrchr, strcmp
#include <stdlib.h>  // For dynamic memory allocation (malloc, free)

/*
 On Windows, paths use '\' while on Unix-like systems they use '/'.
 This macro helps make the code more portable, though the original Python
 code implicitly uses '/' as the separator.
*/
#ifdef _WIN32
#define PATH_SEPARATOR '\\'
#else
#define PATH_SEPARATOR '/'
#endif

/**
 * @brief Translates the Python path2obj function.
 *
 * This function checks if any parent directory in the given file path is named "safedir".
 * If it is, and the file can be opened, it returns a file pointer to that file.
 * Otherwise, it returns NULL.
 *
 * @param filePath The path to the file to be checked and opened.
 * @return A FILE pointer to the opened file if a parent directory is "safedir", otherwise NULL.
 */
FILE* path2obj(const char* filePath) {
    // 1. Attempt to open the file in read mode. If the file doesn't exist or
    //    can't be opened, we can't proceed. This corresponds to `f = open(...)`.
    FILE* f = fopen(filePath, "r");
    if (f == NULL) {
        return NULL; // File could not be opened.
    }

    // 2. Create a mutable copy of the file path. C strings passed as `const char*`
    //    should not be modified. We need our own buffer to manipulate the path.
    size_t path_len = strlen(filePath);
    char* directory = (char*)malloc(path_len + 1); // +1 for the null terminator
    if (directory == NULL) {
        fclose(f); // Allocation failed, clean up the opened file and return.
        return NULL;
    }
    strcpy(directory, filePath);

    // 3. Loop by repeatedly trimming the path to its parent directory, simulating
    //    the Python loop `while directory: directory = os.path.dirname(directory)`.
    while (1) {
        // Find the last path separator in the current path string.
        char* last_sep = strrchr(directory, PATH_SEPARATOR);

        if (last_sep == NULL) {
            // No more separators found. We've reached the top of the relative path
            // (e.g., "file.txt") or the path is empty. Stop the loop.
            break;
        }

        // To prevent an infinite loop on a path like "/", where `dirname("/")` is "/",
        // we check if the separator is the very first character and the path is only that long.
        if (last_sep == directory && *(last_sep + 1) == '\0') {
            break; // Path is the root ("/"), so we can't go up further.
        }

        // Truncate the string at the last separator to get the parent directory.
        // This is the C equivalent of `directory = os.path.dirname(directory)`.
        // For example, "/path/to/file" becomes "/path/to".
        *last_sep = '\0';

        // Now, get the basename of this new, shorter path. This is equivalent to
        // `os.path.basename(directory)` in the Python loop.
        char* component_to_check_sep = strrchr(directory, PATH_SEPARATOR);
        char* component;
        if (component_to_check_sep == NULL) {
            // If there are no more separators, the entire remaining string is the component.
            // e.g., for "safedir"
            component = directory;
        } else {
            // Otherwise, the component is the part after the last separator.
            // e.g., for "/path/to/safedir", the component is "safedir"
            component = component_to_check_sep + 1;
        }

        // Check if the directory component is "safedir".
        if (strcmp(component, "safedir") == 0) {
            free(directory); // Free the memory we allocated for the path copy.
            return f;        // Success! Return the file pointer.
        }
        
        // If the path has been truncated to an empty string, we are done.
        if (strlen(directory) == 0) {
            break;
        }
    }

    // 4. If the loop completes, "safedir" was not found in any parent directory.
    // We must clean up resources before returning NULL.
    free(directory); // Free the allocated memory.
    fclose(f);       // Close the file handle to prevent a resource leak.
    return NULL;     // Return NULL to indicate failure.
}