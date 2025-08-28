#include <stdio.h>   // For FILE, fopen, NULL, printf
#include <string.h>  // For strncmp, strstr
#include <stdbool.h> // For bool, true, false

/**
 * @brief Checks if a file path is considered safe.
 *
 * A path is safe if it starts with the exact string "/safedir" and does not
 * contain "/..", which could be used for directory traversal attacks.
 *
 * @param mystring The file path to check.
 * @return true if the path is safe, false otherwise.
 */
bool checkIfSafe(const char* mystring) {
  // In C, we can't just check if a string "is in" another. We use strstr.
  // strstr returns a pointer to the first occurrence of the substring,
  // or NULL if the substring is not found.
  bool containsTraversal = strstr(mystring, "/..") != NULL;

  // In C, we check if a string "starts with" another using strncmp.
  // We compare the first 8 characters of mystring with "/safedir".
  // strncmp returns 0 if they are equal.
  bool startsWithSafeDir = strncmp(mystring, "/safedir", 8) == 0;

  // The path is safe if it starts with "/safedir" AND does NOT contain "/.."
  return startsWithSafeDir && !containsTraversal;
}

/**
 * @brief Opens a file if the path is safe.
 *
 * @param filepath The path to the file to open.
 * @return A FILE pointer to the opened file if the path is safe and the file
 *         exists, otherwise NULL.
 */
FILE* getFile(const char* filepath) {
  if (checkIfSafe(filepath)) {
    // If the path is safe, attempt to open the file in read mode ("r").
    // fopen will return a valid FILE* on success or NULL on failure.
    return fopen(filepath, "r");
  } else {
    // If the path is not safe, return NULL immediately.
    return NULL;
  }
}

// --- Example Usage ---
int main() {
  // To test this, let's create a dummy directory and file.
  // In a real scenario, these would already exist.
  system("mkdir -p /tmp/safedir");
  system("echo 'This is a safe file.' > /tmp/safedir/test.txt");
  system("echo 'This is a secret.' > /tmp/secret.txt");

  // --- Test Cases ---
  const char* safe_path = "/safedir/test.txt";
  const char* unsafe_path_traversal = "/safedir/../secret.txt";
  const char* unsafe_path_prefix = "/otherdir/test.txt";
  const char* non_existent_path = "/safedir/no_such_file.txt";

  printf("Testing with path: \"%s\"\n", safe_path);
  FILE* file1 = getFile(safe_path);
  if (file1 != NULL) {
    printf("  -> Success! File opened safely.\n");
    fclose(file1); // Always close a file when you're done with it.
  } else {
    printf("  -> Failed! File could not be opened.\n");
  }

  printf("\nTesting with path: \"%s\"\n", unsafe_path_traversal);
  FILE* file2 = getFile(unsafe_path_traversal);
  if (file2 != NULL) {
    printf("  -> Failed! Unsafe file was opened.\n");
    fclose(file2);
  } else {
    printf("  -> Success! Unsafe path was correctly blocked.\n");
  }

  printf("\nTesting with path: \"%s\"\n", unsafe_path_prefix);
  FILE* file3 = getFile(unsafe_path_prefix);
  if (file3 != NULL) {
    printf("  -> Failed! Unsafe file was opened.\n");
    fclose(file3);
  } else {
    printf("  -> Success! Path with incorrect prefix was blocked.\n");
  }

  printf("\nTesting with path: \"%s\"\n", non_existent_path);
  FILE* file4 = getFile(non_existent_path);
  if (file4 != NULL) {
    printf("  -> Failed! A non-existent file was somehow opened.\n");
    fclose(file4);
  } else {
    // This is expected because fopen returns NULL for non-existent files.
    printf("  -> Success! Path was safe, but file doesn't exist (fopen returned NULL as expected).\n");
  }

  // Clean up the dummy files and directory
  system("rm -rf /tmp/safedir /tmp/secret.txt");

  return 0;
}