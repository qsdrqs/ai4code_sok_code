#include <stdio.h>   // For FILE, fopen, printf, perror, fprintf, stderr, fclose
#include <stdlib.h>  // For realpath, NULL
#include <string.h>  // For strncmp
#include <limits.h>  // For PATH_MAX, which defines the max length of a path

/**
 * @brief Opens a file after performing a security check on its real path.
 *
 * This function translates the Python `return_object` function. It resolves the
 * absolute path of the given file name. It performs a security check to ensure
 * the path does not start with "/safedir". If the check passes, it attempts
 * to open the file in write mode ('w').
 *
 * Note on return types: The original Python function returns a mix of types
 * (-1 for an error, a file object for success). In C, a function must have a
 * single return type. The standard C practice for functions that return a
 * pointer (like FILE*) is to return NULL on any error. This implementation
 * follows that C idiom.
 *
 * @param path The path to the file to be opened.
 * @return A FILE pointer to the opened file on success, or NULL on failure.
 */
FILE* return_object(const char* path) {
  char rpath[PATH_MAX];

  // os.path.realpath(path) -> realpath(path, rpath)
  // Get the canonicalized absolute pathname.
  // Note: C's realpath() requires parent directories to exist, unlike Python's.
  // If realpath fails, we return NULL.
  if (realpath(path, rpath) == NULL) {
    // In the original Python, if realpath fails, open() would subsequently fail.
    // Here, we can let fopen handle it, but for a more direct translation of the
    // logic flow, we check realpath first. Let's proceed to fopen, which will
    // create the file if it doesn't exist and print a more relevant error if it can't.
  } else {
    // if (rpath[:8] == "/safedir") -> strncmp(rpath, "/safedir", 8) == 0
    // Security check: prevent writing to any path inside /safedir
    if (strncmp(rpath, "/safedir", 8) == 0) {
      fprintf(stderr, "Error: Attempt to access a restricted path starting with '/safedir'.\n");
      return NULL; // Return NULL to indicate an error
    }
  }


  // try...except block for opening a file
  // fp = open(path, 'w') -> FILE* fp = fopen(path, "w");
  FILE* fp = fopen(path, "w");
  if (fp == NULL) {
    // This handles the exception case. perror prints the system error.
    perror("Error opening file");
    return NULL;
  }

  return fp;
}

// This block is equivalent to Python's `if __name__ == "__main__":`
int main() {
  const char* file_name = "/tmp/foo";
  FILE* file_pointer = return_object(file_name);

  // In C, we check for a NULL pointer to determine if the function succeeded.
  // Printing the pointer itself is not as meaningful as in Python.
  if (file_pointer != NULL) {
    printf("Successfully opened file: %s\n", file_name);
    // For demonstration, we can print the pointer address.
    printf("File pointer object: %p\n", (void*)file_pointer);
    
    // It's crucial to close the file when done to free system resources.
    fclose(file_pointer);
  } else {
    // The specific error message was already printed inside return_object.
    printf("Failed to get file object for: %s\n", file_name);
  }

  return 0; // Exit with success code
}