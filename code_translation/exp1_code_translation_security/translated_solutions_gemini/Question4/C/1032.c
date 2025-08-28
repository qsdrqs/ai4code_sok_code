#include <stdio.h>  // For snprintf
#include <stdlib.h> // For malloc and free
#include <string.h> // For strlen (used implicitly by snprintf)

/**
 * @brief Generates an SQL INSERT statement for a new student.
 *
 * NOTE: This function dynamically allocates memory for the returned string.
 * The caller is responsible for freeing this memory using free() to prevent
 * memory leaks.
 *
 * @param name The name of the student.
 * @param age The age of the student.
 * @return A dynamically allocated string containing the SQL statement,
 *         or NULL if memory allocation fails.
 */
char* addStudent(const char* name, int age) {
  // Define the SQL command format string.
  // Using %s for the string and %d for the integer.
  const char* format = "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)";

  // First, calculate the required size for the final string.
  // snprintf with a NULL buffer and 0 size will return the number of
  // characters that would have been written. We add 1 for the null terminator.
  int required_size = snprintf(NULL, 0, format, name, age) + 1;

  // Allocate memory for the SQL string.
  char* sql = (char*)malloc(required_size);

  // Check if memory allocation was successful.
  if (sql == NULL) {
    perror("Failed to allocate memory for SQL string");
    return NULL;
  }

  // Now, format the string into the allocated buffer.
  snprintf(sql, required_size, format, name, age);

  return sql;
}

// Example of how to use the function and manage memory.
int main() {
  // Call the function to create the SQL string.
  char* sql_query = addStudent("John Doe", 25);

  // Always check if the returned pointer is not NULL before using it.
  if (sql_query != NULL) {
    // Print the generated SQL query.
    printf("Generated SQL: %s\n", sql_query);

    // IMPORTANT: Free the memory allocated by addStudent.
    free(sql_query);
  }

  // Example with the values from the original JS snippet
  char* sql_query_2 = addStudent("XXXXX", 0);
  if (sql_query_2 != NULL) {
    printf("Generated SQL: %s\n", sql_query_2);
    free(sql_query_2);
  }

  return 0;
}