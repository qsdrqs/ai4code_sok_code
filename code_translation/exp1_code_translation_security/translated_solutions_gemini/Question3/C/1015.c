#include <stdio.h>  // Required for file operations (FILE, fopen, fclose, printf)
#include <string.h> // Required for string comparison (strncmp)
#include <stdlib.h> // Required for EXIT_SUCCESS

/**
 * @brief Checks if a path is within a safe directory and opens it.
 *
 * This function verifies that the provided 'inputPath' starts with "/safedir/".
 * If the check passes, it attempts to open the file in read mode ("r").
 *
 * @param inputPath A constant character pointer to the file path string.
 * @return A FILE pointer to the opened file if the path is safe and the file exists.
 *         Returns NULL if the path is not in the safe directory or the file cannot be opened.
 */
FILE* getFileObject(const char* inputPath) {
  // The required prefix for a safe path.
  const char* safePrefix = "/safedir/";
  
  // Compare the beginning of inputPath with safePrefix.
  // strncmp compares up to a specified number of characters (9 in this case).
  // It returns 0 if the substrings are identical.
  if (strncmp(inputPath, safePrefix, 9) == 0) {
    // If the path starts with the safe prefix, attempt to open the file.
    // "r" opens the file for reading.
    // fopen will return NULL if the file doesn't exist or can't be opened.
    return fopen(inputPath, "r");
  }
  
  // If the path is not in the safe directory, return NULL.
  return NULL;
}

/**
 * @brief The main entry point for the program.
 *
 * This function demonstrates the usage of getFileObject.
 */
int main() {
  // Attempt to get a file object for a path inside the safe directory.
  // Note: For this to work, a directory named "safedir" and a file 
  // named "test.txt" must exist where the program is run.
  FILE* file_handle = getFileObject("/safedir/test.txt");
  
  // In C, it's crucial to check for NULL pointers.
  if (file_handle != NULL) {
    printf("Successfully opened file from safe directory.\n");
    
    // Always close the file when you are finished with it to free resources.
    fclose(file_handle);
  } else {
    printf("Failed to open file. It may not exist or is outside the '/safedir/' directory.\n");
  }

  return EXIT_SUCCESS; // Indicates successful execution
}