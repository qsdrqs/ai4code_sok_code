#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h> // The C dependency for MySQL

// Helper function to handle MySQL errors and exit.
void finish_with_error(MYSQL *con, const char* message) {
  fprintf(stderr, "%s: %s\n", message, mysql_error(con));
  if (con != NULL) {
      mysql_close(con);
  }
  exit(1);
}

// The function now takes the active connection as an argument.
void add_student(MYSQL *con, const char *name, int age) {
  // The SQL query with placeholders (?) for data
  const char *query = "INSERT INTO STUDENTS (name, age) VALUES (?, ?)";

  MYSQL_STMT *stmt;
  MYSQL_BIND params[2];
  
  // 1. Initialize a prepared statement
  stmt = mysql_stmt_init(con);
  if (!stmt) {
    finish_with_error(con, "mysql_stmt_init failed");
  }

  // 2. Prepare the SQL query
  if (mysql_stmt_prepare(stmt, query, strlen(query))) {
    finish_with_error(con, "mysql_stmt_prepare failed");
  }

  // 3. Bind the parameters to the placeholders
  memset(params, 0, sizeof(params)); // Zero out the structure

  // Parameter 1: name (string)
  params[0].buffer_type = MYSQL_TYPE_STRING;
  params[0].buffer = (char *)name;
  params[0].buffer_length = strlen(name);

  // Parameter 2: age (integer)
  params[1].buffer_type = MYSQL_TYPE_LONG; // int in C maps to LONG in MySQL C API
  params[1].buffer = (char *)&age;
  params[1].is_unsigned = 0;
  
  // Bind the parameter array to the statement
  if (mysql_stmt_bind_param(stmt, params)) {
    finish_with_error(con, "mysql_stmt_bind_param failed");
  }

  // 4. Execute the prepared statement
  if (mysql_stmt_execute(stmt)) {
    finish_with_error(con, "mysql_stmt_execute failed");
  }

  printf("Student '%s' added successfully (Securely).\n", name);

  // 5. Close the statement to free resources
  mysql_stmt_close(stmt);
}

int main() {
  MYSQL *con = mysql_init(NULL);

  if (con == NULL) {
    fprintf(stderr, "mysql_init() failed\n");
    exit(1);
  }

  // Connect ONCE at the start of the application.
  if (mysql_real_connect(con, "localhost", "user", "password", "database_name", 0, NULL, 0) == NULL) {
    finish_with_error(con, "mysql_real_connect failed");
  }

  // Call the function with the active connection
  add_student(con, "Charlie", 30);
  
  // Example of a potentially malicious input that is now SAFE
  add_student(con, "Robert'); DROP TABLE STUDENTS; --", 99);

  // Clean up the connection when the program is done.
  mysql_close(con);

  return 0;
}