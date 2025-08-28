#include <stdio.h>
#include <stdlib.h> // Required for malloc and free
#include <string.h> // Required for strlen and strcpy

// Corrected version that is safe
char* num_to_string_corrected(int num) {
  char temp_buffer[20];
  sprintf(temp_buffer, "%d", num);

  // Allocate memory on the heap that will persist after the function returns
  char *ans = malloc(strlen(temp_buffer) + 1); // +1 for the null terminator '\0'
  if (ans == NULL) {
    // Handle memory allocation failure
    return NULL;
  }
  strcpy(ans, temp_buffer); // Copy the string into the newly allocated memory
  return ans;
}

int main() {
  char* result_string = num_to_string_corrected(7000000);
  if (result_string != NULL) {
    printf("%s\n", result_string);
    // Since we allocated memory with malloc, we must free it to prevent a memory leak
    free(result_string);
  }
  return 0;
}