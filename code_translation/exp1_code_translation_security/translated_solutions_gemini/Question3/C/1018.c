#include <stdio.h>  // Required for FILE, fopen(), and NULL
#include <string.h> // Required for strstr()

/**
 * @brief Opens a file for reading only if its path contains "/safedir".
 *
 * This function checks if the provided path string contains the substring "/safedir".
 * If it does, the function attempts to open the file in read mode ("r") and
 * returns a file pointer to it.
 * If the substring is not found, or if the file cannot be opened, it returns NULL.
 *
 * @param path A constant character pointer to the file path string.
 * @return A pointer to a FILE object on success, or NULL on failure/disallowed path.
 */
FILE *read_in(const char *path) {
  // Check if the substring "/safedir" exists within the path string.
  if (strstr(path, "/safedir") != NULL) {
    // If the substring is found, open the file in read mode ("r").
    return fopen(path, "r");
  } else {
    // If the substring is not found, return NULL.
    return NULL;
  }
}