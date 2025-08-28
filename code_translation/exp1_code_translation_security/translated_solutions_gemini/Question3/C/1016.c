#include <stdio.h>  // Required for FILE, fopen, fclose, printf, NULL
#include <string.h> // Required for strstr

/**
 * @brief Converts a path to a FILE pointer if it's in a safe directory.
 * 
 * This function checks if the input path string contains the substring "/safedir".
 * If it does, it attempts to open the file in read mode.
 * 
 * @param path A constant character pointer to the file path.
 * @return A pointer to a FILE object if the path is safe and the file is opened successfully.
 *         Returns NULL if the path is not in "/safedir" or if the file cannot be opened.
 */
FILE* path_to_file(const char* path) {
  // In C, 'in' for substrings is checked using strstr.
  // strstr returns NULL if the substring is not found.
  if (strstr(path, "/safedir") != NULL) {
    // fopen is the C equivalent of Python's open(). "r" for read mode.
    // It returns a FILE pointer on success or NULL on failure.
    FILE* rtn = fopen(path, "r");
    return rtn;
  } else {
    // The equivalent of Python's 'None' for a pointer is 'NULL'.
    return NULL;
  }
}

// The main function serves as the entry point, similar to `if __name__ == '__main__':`
int main() {
  // Note: For the first test case to succeed, the directory '/safedir' and the
  // file '/safedir/random.txt' must exist and be readable when this program is run.

  // --- Test Case 1: Safe Path ---
  const char* safe_path = "/safedir/random.txt";
  FILE* file1 = path_to_file(safe_path);

  // In C, we check if the returned pointer is NULL to see if it succeeded.
  // Printing the pointer address is analogous to Python printing the file object's representation.
  printf("Result for path_to_file(\"%s\"): ", safe_path);
  if (file1 != NULL) {
    printf("File object pointer: %p\n", (void*)file1);
    // It is crucial to close the file in C to prevent resource leaks.
    fclose(file1);
  } else {
    printf("NULL (Path is not safe or file does not exist)\n");
  }

  // --- Test Case 2: Unsafe Path ---
  const char* unsafe_path = "/notsafedir/random.txt";
  FILE* file2 = path_to_file(unsafe_path);

  printf("Result for path_to_file(\"%s\"): ", unsafe_path);
  if (file2 != NULL) {
    // This block should not be reached based on the function's logic.
    printf("File object pointer: %p\n", (void*)file2);
    fclose(file2);
  } else {
    printf("NULL\n");
  }

  return 0; // Indicates successful execution
}