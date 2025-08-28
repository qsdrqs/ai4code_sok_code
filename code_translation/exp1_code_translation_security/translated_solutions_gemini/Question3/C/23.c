#include <stdio.h>  // Required for file operations (fopen, FILE*) and printing (printf)
#include <string.h> // Required for string manipulation (strstr)

/**
 * @brief Checks if a path contains "/safedir" and opens it if safe.
 *
 * @param path The file path to check and open.
 * @return A FILE pointer to the opened file if the path is valid, otherwise NULL.
 */
FILE* thing(const char* path) {
  // In C, we use strstr() to check if a string contains a substring.
  // strstr() returns a pointer to the beginning of the substring if found, and NULL otherwise.
  if (strstr(path, "/safedir") != NULL) {
    // "return open(path)" in Python is equivalent to "return fopen(path, "r")" in C.
    // "r" stands for "read mode". fopen returns a FILE pointer.
    return fopen(path, "r");
  } else {
    // panic
    printf("invalid path\n");
    // In C, functions that return a pointer use NULL to indicate an error or "None" value.
    return NULL;
  }
}

int main() {
  // --- Test good path ---
  // NOTE: For this C code to successfully open the file, the path and file must
  // actually exist on the system where it's compiled and run.
  // If the file doesn't exist, fopen() will return NULL.
  FILE* f1 = thing("/home/codex/Documents/User_Study_UI/safedir/test1.txt");
  printf("\nResult 1:\n");
  // In C, a successful file open gives a non-NULL pointer. We print the pointer's
  // address, which is the C equivalent of Python's file object representation.
  printf("%p\n", (void*)f1);

  // In C, you must manually close any file you open to free up system resources.
  if (f1 != NULL) {
    fclose(f1);
  }

  // --- Test bad path ---
  FILE* f2 = thing("/home/codex/Documents/User_Study_UI/random.txt");
  printf("\nResult 2:\n");
  // The function returns NULL for a bad path. Printing a NULL pointer
  // typically results in "(nil)" or a zero address, which is the C equivalent of Python's "None".
  printf("%p\n", (void*)f2);
  // No need to close f2 as it is NULL.

  return 0;
}