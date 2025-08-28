#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <errno.h>

// In C, we don't have custom exception classes. A common pattern is to use
// an enum for custom error codes and a global variable to store the last error.
typedef enum {
  GET_FILE_PATH_SUCCESS = 0,
  PATH_NOT_ALLOWED,
  FILE_NOT_FOUND_ERROR, // To distinguish from other fopen errors
  OTHER_FOPEN_ERROR
} GetFilePathError;

// This global variable will hold the status of the last getFilePath call,
// similar to how `errno` works for standard library functions.
GetFilePathError g_last_error;

/**
 * @brief Opens a file only if it's within the "/safedir/" directory.
 *
 * This function checks if the provided path starts with "/safedir/".
 * If it does, it attempts to open the file. If not, it sets a custom
 * error and returns NULL.
 *
 * @param path The full path to the file to open.
 * @return A FILE pointer on success, or NULL on failure. If NULL is returned,
 *         check the global `g_last_error` variable for the specific error reason.
 */
FILE* getFilePath(const char* path) {
  const char* safe_prefix = "/safedir/";
  g_last_error = GET_FILE_PATH_SUCCESS; // Reset error state

  // Check if the path starts with the required prefix.
  // strncmp is a safe way to check for a prefix.
  if (strncmp(path, safe_prefix, strlen(safe_prefix)) != 0) {
    // This is the equivalent of `raise PathNotAllowed(...)`
    fprintf(stderr, "Error: filepath not allowed, only %s... file paths are accessible\n", safe_prefix);
    g_last_error = PATH_NOT_ALLOWED;
    return NULL;
  }

  // Attempt to open the file
  FILE* f = fopen(path, "r");
  if (f == NULL) {
    // This is the equivalent of a FileNotFoundError or other OS error
    if (errno == ENOENT) { // ENOENT means "Error, No Entry" (file not found)
        g_last_error = FILE_NOT_FOUND_ERROR;
    } else {
        g_last_error = OTHER_FOPEN_ERROR;
    }
    // We can use perror to print the system error message (e.g., "No such file or directory")
    perror("fopen failed");
    return NULL;
  }

  return f;
}

// Helper function to create dummy files for the test
void setup_test_files(const char* paths[], int count) {
    for (int i = 0; i < count; ++i) {
        FILE* f = fopen(paths[i], "w");
        if (f) {
            fprintf(f, "This is a test file.\n");
            fclose(f);
        }
    }
}

// Helper function to clean up dummy files
void cleanup_test_files(const char* paths[], int count) {
    for (int i = 0; i < count; ++i) {
        remove(paths[i]);
    }
}


int main() {
  // In C, we define the array of strings directly.
  const char* goodPaths[] = {
    "/safedir/test1.txt",
    "/safedir/test.txt"
  };
  int numGoodPaths = sizeof(goodPaths) / sizeof(goodPaths[0]);

  // Create the dummy files for the "good" test cases to succeed
  // Note: The "/safedir/" directory must exist for this to work.
  // On Linux/macOS, you can run `mkdir -p /safedir` first.
  // On Windows, this path is invalid; you might use "C:/safedir/" instead.
  setup_test_files(goodPaths, numGoodPaths);


  printf("--- Running Test 1: Good Paths ---\n");
  // should open without exception
  for (int i = 0; i < numGoodPaths; ++i) {
    const char* path = goodPaths[i];
    printf("Attempting to open: %s\n", path);
    FILE* f = getFilePath(path);
    if (f != NULL) {
      printf("  Success! File opened.\n");
      fclose(f);
    } else {
      printf("  FAILURE! File could not be opened. Error code: %d\n", g_last_error);
    }
  }
  printf("\n");


  printf("--- Running Test 2: File Not Found ---\n");
  // files not existing should fail as expected
  // This is the C equivalent of the `try...except FileNotFoundError` block
  FILE* f_noexist = getFilePath("/safedir/noexist.txt");
  if (f_noexist == NULL && g_last_error == FILE_NOT_FOUND_ERROR) {
    printf("  Success! Function correctly failed with FILE_NOT_FOUND_ERROR.\n");
  } else {
    printf("  FAILURE! Expected FILE_NOT_FOUND_ERROR but got a different result.\n");
  }
  // Note: The buggy `finally { f.close() }` from Python is avoided.
  // We don't try to close a NULL pointer.
  printf("\n");


  printf("--- Running Test 3: Path Not Allowed ---\n");
  // files outside of not allowed path should raise PathNotAllowed exception
  // This is the C equivalent of the `try...except PathNotAllowed` block
  FILE* f_badpath = getFilePath("not/a/good/path.txt");
  if (f_badpath == NULL && g_last_error == PATH_NOT_ALLOWED) {
    printf("  Success! Function correctly failed with PATH_NOT_ALLOWED.\n");
  } else {
    printf("  FAILURE! Expected PATH_NOT_ALLOWED but got a different result.\n");
  }
  printf("\n");

  // Clean up the dummy files
  cleanup_test_files(goodPaths, numGoodPaths);

  return 0;
}