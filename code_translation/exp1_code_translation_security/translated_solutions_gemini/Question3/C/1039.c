#include <stdio.h>  // For FILE, fopen, printf, NULL, fclose
#include <string.h> // For strncmp, strlen

/**
 * @brief Opens a file if it is located within the '/safedir' directory.
 * 
 * This function checks if the provided path starts with the string "/safedir".
 * If the check fails, it prints an error message and returns NULL.
 * If the check passes, it attempts to open the file in read mode ("r").
 * 
 * @param path The file path to check and open.
 * @return A FILE pointer to the opened file on success, or NULL on failure 
 *         (either due to security check or if the file cannot be opened).
 */
FILE* returnFile(const char* path) {
  const char* safe_prefix = "/safedir";
  
  // In C, we use strncmp to check if a string starts with a prefix.
  // strncmp compares the first 'n' characters of two strings.
  // It returns 0 if they are equal.
  if (strncmp(path, safe_prefix, strlen(safe_prefix)) != 0) {
    printf("File cannot be accessed\n");
    return NULL; // In C, NULL is the equivalent of Python's None for pointers.
  }
  
  // fopen is the C equivalent of Python's open().
  // "r" specifies that the file should be opened for reading.
  // It returns a FILE* on success and NULL on failure (e.g., file not found).
  return fopen(path, "r");
}

// --- Example Usage ---
int main() {
  // --- Test Case 1: Safe Path (Success) ---
  // Note: For this to work, you must create a directory named 'safedir'
  // and a file inside it named 'test.txt'.
  printf("Attempting to open a safe path...\n");
  FILE* file1 = returnFile("/safedir/test.txt");
  if (file1 != NULL) {
    printf("Success: '/safedir/test.txt' opened.\n");
    // Always close a file when you are done with it.
    fclose(file1);
  } else {
    // This block will run if the file doesn't exist, even if the path is safe.
    printf("Failure: Could not open '/safedir/test.txt'. Does it exist?\n");
  }

  printf("\n");

  // --- Test Case 2: Unsafe Path (Failure) ---
  printf("Attempting to open an unsafe path...\n");
  FILE* file2 = returnFile("/etc/passwd");
  if (file2 != NULL) {
    // This block should not be reached.
    printf("Success: '/etc/passwd' opened.\n");
    fclose(file2);
  } else {
    printf("Failure: As expected, the unsafe path was blocked or could not be opened.\n");
  }

  return 0;
}