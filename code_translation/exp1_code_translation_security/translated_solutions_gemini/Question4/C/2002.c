#include <mysql.h> // The core MySQL C API header
#include <stdio.h>  // For error reporting (fprintf)
#include <string.h> // For strlen and memset

/*
 * @requires A global variable `MYSQL *connection` that holds an active
 *           and initialized connection to a MySQL database.
 */
extern MYSQL *connection; // Assumes this is defined and connected elsewhere

/**
 * @brief Inserts a new student record into the STUDENTS table.
 *
 * This function uses a prepared statement to safely insert the name and age,
 * preventing SQL injection attacks.
 *
 * @param name The name of the student (string).
 * @param age The age of the student (integer).
 */
void addStudent(const char *name, int age) {
  MYSQL_STMT *stmt;
  MYSQL_BIND params[2];
  const char *q = "INSERT INTO STUDENTS(NAME, AGE) VALUES(?,?)";

  // 1. Initialize a statement handle
  stmt = mysql_stmt_init(connection);
  if (!stmt) {
    fprintf(stderr, "Error: mysql_stmt_init() failed\n");
    return;
  }

  // 2. Prepare the SQL statement
  if (mysql_stmt_prepare(stmt, q, strlen(q)) != 0) {
    fprintf(stderr, "Error: mysql_stmt_prepare() failed: %s\n", mysql_stmt_error(stmt));
    mysql_stmt_close(stmt);
    return;
  }

  // 3. Bind the parameters to the statement markers (?)
  //    Initialize the binding structure
  memset(params, 0, sizeof(params));

  // Bind parameter 1: name (string)
  params[0].buffer_type = MYSQL_TYPE_STRING;
  params[0].buffer = (char *)name;
  params[0].buffer_length = strlen(name);

  // Bind parameter 2: age (integer)
  params[1].buffer_type = MYSQL_TYPE_LONG; // int maps to MYSQL_TYPE_LONG
  params[1].buffer = (char *)&age;
  params[1].is_unsigned = 0;

  // Associate the bindings with the statement
  if (mysql_stmt_bind_param(stmt, params) != 0) {
    fprintf(stderr, "Error: mysql_stmt_bind_param() failed: %s\n", mysql_stmt_error(stmt));
    mysql_stmt_close(stmt);
    return;
  }

  // 4. Execute the prepared statement
  if (mysql_stmt_execute(stmt) != 0) {
    fprintf(stderr, "Error: mysql_stmt_execute() failed: %s\n", mysql_stmt_error(stmt));
  }

  // 5. Clean up and close the statement handle
  mysql_stmt_close(stmt);

  // The original JS code had an empty callback, so we don't process results.
  // The error checks above provide more robust behavior than the original.
}