#include <stdio.h>   // For file operations (fopen, fclose, fgetc, printf) and constants like NULL, EOF
#include <stdlib.h>  // For realpath(), EXIT_SUCCESS, EXIT_FAILURE
#include <string.h>  // For string manipulation (strlen, strncmp)
#include <limits.h>  // For the PATH_MAX constant, which defines the max length of a path

// Define the safe directory as a constant string
const char* SAFE_DIR = "/safedir";

/**
 * @brief Safely opens a file, ensuring it resides within SAFE_DIR.
 *
 * This function resolves the real, canonical path of the given filename.
 * It then checks if this resolved path starts with the SAFE_DIR prefix.
 * If it does, the file is opened in read mode. Otherwise, it fails.
 *
 * @param fname The path to the file to open.
 * @return A FILE pointer on success, or NULL on failure (e.g., path is
 *         unsafe, file doesn't exist, or cannot be opened).
 */
FILE* safe_load_file(const char* fname) {
  // Buffer to store the resolved, absolute path.
  // PATH_MAX is the recommended size for this.
  char resolved_path[PATH_MAX];

  // realpath() resolves all symbolic links, '..', '.', etc., and returns
  // the canonical absolute path. This is the C equivalent of Python's os.path.realpath().
  // If the path is invalid, realpath returns NULL.
  if (realpath(fname, resolved_path) == NULL) {
    // perror("realpath failed"); // Uncomment for more detailed error info
    printf("FAILED: Invalid path or file does not exist.\n");
    return NULL;
  }

  // Get the length of the safe directory prefix.
  size_t safe_dir_len = strlen(SAFE_DIR);

  // Check if the resolved path starts with the SAFE_DIR prefix.
  // strncmp() compares the first 'n' characters of two strings.
  // If they are equal, it returns 0.
  if (strncmp(resolved_path, SAFE_DIR, safe_dir_len) != 0) {
    printf("FAILED: Path is outside the safe directory.\n");
    return NULL;
  }

  // The path is safe, so we can open the file.
  // fopen() is the C equivalent of Python's open(). "r" means read mode.
  FILE* file_handle = fopen(resolved_path, "r");
  if (file_handle == NULL) {
    printf("FAILED: Could not open file (check permissions).\n");
    return NULL;
  }

  return file_handle;
}

int main(void) {
  // This block is the C equivalent of `if __name__ == "__main__":`
  printf("Attempting to load /safedir/test.txt...\n");

  // Call the function to get the file handle
  FILE* f = safe_load_file("/safedir/test.txt");

  // In C, it's crucial to check for NULL pointers to avoid crashes.
  // The Python script would crash here if `f` were None.
  if (f == NULL) {
    // The error message was already printed inside safe_load_file
    return EXIT_FAILURE; // Exit with an error code
  }

  // Read the file character by character and print it to the console.
  // This is the C equivalent of `f.read()` followed by `print()`.
  int c;
  while ((c = fgetc(f)) != EOF) {
    putchar(c); // putchar is efficient for printing single characters
  }

  // fclose() is the C equivalent of f.close()
  fclose(f);

  return EXIT_SUCCESS; // Exit successfully
}