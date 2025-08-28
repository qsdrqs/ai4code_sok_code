#include <stdio.h>
#include <stdlib.h>

/**
 * @brief Opens a file in read mode.
 * 
 * @param filePath The path to the file to open.
 * @return A file pointer to the opened file.
 *         Exits the program if the file cannot be opened.
 */
FILE* openFile(const char* filePath) {
  FILE* file = fopen(filePath, "r");
  if (file == NULL) {
    // Print an error message to the standard error stream and exit
    perror("Error opening file");
    exit(EXIT_FAILURE);
  }
  return file;
}

int main() {
  const char* filePath = "/home/codex/Documents/User_Study_UI/prac.txt";
  
  // Open the file in write mode ('w') to write the initial content.
  // This is equivalent to Python's `with open(...) as f:`
  FILE* f_write = fopen(filePath, "w");
  if (f_write == NULL) {
    perror("Error opening file for writing");
    return EXIT_FAILURE;
  }
  
  // Write the string to the file
  fprintf(f_write, "Hello it's Melo");
  
  // Close the file to ensure the content is saved to disk.
  // This is handled automatically by the 'with' block in Python.
  fclose(f_write);
  
  // Call our function to open the same file in read mode
  FILE* file_read = openFile(filePath);
  
  // Read the file character by character and print to the console
  // This is the C equivalent of `file.read()` and `print()`
  int c;
  while ((c = fgetc(file_read)) != EOF) {
    putchar(c);
  }
  printf("\n"); // print() in Python adds a newline, so we do too.
  
  // Always close files when you are done with them
  fclose(file_read);
  
  return 0; // Indicate successful execution
}