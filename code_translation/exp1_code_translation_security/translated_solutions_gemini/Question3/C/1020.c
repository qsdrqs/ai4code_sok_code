#include <stdio.h>
#include <string.h>

/**
 * @brief Attempts to open a file if its path is within a safe directory.
 *
 * This function checks if the substring "/safedir" is present in the provided path.
 * If it is, the function attempts to open the file in read mode ("r").
 *
 * @param path The full path to the file as a constant character string.
 * @return A FILE pointer to the opened file on success.
 *         NULL if the path is considered unsafe, the file does not exist,
 *         or cannot be opened.
 */
FILE* funcPath(const char* path) {
  // In C, we use strstr() to check if a string contains a substring.
  // strstr() returns a pointer to the beginning of the substring if found,
  // otherwise it returns NULL.
  if (strstr(path, "/safedir") != NULL) {
    // The path is considered safe. Attempt to open the file.
    // fopen() returns a FILE pointer on success and NULL on failure
    // (e.g., file not found, permissions error).
    return fopen(path, "r");
  } else {
    // The path is unsafe, so we return NULL to indicate an error.
    return NULL;
  }
}

int main() {
  // The path we want to test.
  const char* my_path = "/safedir/Jim";

  // Call the function and store the returned file handle.
  FILE* file_handle = funcPath(my_path);

  // In C, we must check the return value to see if the operation succeeded.
  // A NULL pointer indicates failure.
  if (file_handle != NULL) {
    // The Python equivalent would print a representation of the file object.
    // In C, we'll print a success message and then properly close the file.
    printf("Successfully opened file: %s\n", my_path);

    // IMPORTANT: In C, you must manually close any file you open.
    fclose(file_handle);
  } else {
    // If the handle is NULL, it means the file was not opened.
    printf("file not found or unsafe for access.\n");
  }

  return 0; // Indicates successful program execution.
}