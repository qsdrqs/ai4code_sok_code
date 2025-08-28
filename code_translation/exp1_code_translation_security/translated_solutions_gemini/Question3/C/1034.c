#include <stdio.h>   // For FILE, fopen, printf, fclose
#include <stdlib.h>  // For exit()
#include <stdbool.h> // For the bool type (true, false)
#include <string.h>  // For strlen()

// Forward declaration for check_dir, as it's used by get_file_object
bool check_dir(const char* path);

/**
 * @brief Opens a file if it's in a permitted directory.
 *
 * This function corresponds to the Python function `get_file_object`.
 * It checks if the path is valid using check_dir. If valid, it attempts
 * to open the file. It prints an error and exits if the directory is not
 * permitted or if the file cannot be opened.
 *
 * @param path The path to the file.
 * @return A FILE pointer to the opened file.
 */
FILE* get_file_object(const char* path) {
  // Corresponds to: if check_dir(path):
  if (check_dir(path)) {
    // Corresponds to the 'try' block:
    //   file_object = open(path, 'r')
    // In C, fopen() returns NULL on error, which replaces the try/except block.
    FILE* file_object = fopen(path, "r");

    // This 'if' block corresponds to the 'except IOError' block.
    if (file_object == NULL) {
      printf("Error\n");
      exit(1); // Exit with a non-zero status to indicate failure.
    }
    return file_object;
  } else {
    // Corresponds to the 'else' block where check_dir is false.
    exit(1);
  }
}

/**
 * @brief Checks if a path starts with the "/safedir" prefix.
 *
 * This function is a direct translation of the Python `check_dir` function.
 *
 * @param path The file path to check.
 * @return `true` if the path starts with "/safedir", `false` otherwise.
 */
bool check_dir(const char* path) {
  // A safety check to prevent reading out of bounds if the path is too short.
  // The original Python code would raise an IndexError in this case.
  if (path == NULL || strlen(path) < 8) {
      return false;
  }

  // This is a literal translation of the character-by-character check.
  if (path[0] == '/' && path[1] == 's' && path[2] == 'a' && path[3] == 'f' &&
      path[4] == 'e' && path[5] == 'd' && path[6] == 'i' && path[7] == 'r') {
    return true;
  } else {
    return false;
  }
}

/**
 * @brief Main function to execute the test case.
 *
 * This corresponds to the Python line:
 * print(get_file_object("/safedir/test1.txt"))
 */
int main() {
  const char* path = "/safedir/test1.txt";

  // NOTE: For this program to run successfully, you must first create the
  // directory and file it tries to open. For example, on a Unix-like system:
  // $ sudo mkdir /safedir
  // $ sudo touch /safedir/test1.txt
  // $ sudo chmod -R 777 /safedir

  FILE* file = get_file_object(path);

  // The Python `print(file_object)` prints a representation of the object.
  // In C, we can't print the object's contents directly, but we can print
  // its memory address (the pointer) to confirm it was created successfully.
  printf("Successfully opened file. File pointer: %p\n", (void*)file);

  // CRITICAL: In C, files must be manually closed when you are done with them.
  fclose(file);

  return 0; // Exit with a success status.
}